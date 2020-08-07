package com.yang.springboot.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.yang.springboot.common.constants.ExportConstants;
import com.yang.springboot.common.utils.*;
import com.yang.springboot.config.MailConfig;
import com.yang.springboot.dto.EasyExcelWaybillDTO;
import com.yang.springboot.dto.WaybillDTO;
import com.yang.springboot.jpa.WaybillDO;
import com.yang.springboot.repo.WaybillRepo;
import com.yang.springboot.service.WaybillService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author yanghao
 * @date 2019-04-18 16:44
 */
@Service
@Slf4j
public class WaybillServiceImpl extends BaseServiceImpl implements WaybillService {

    @Resource
    private WaybillRepo waybillRepo;
    @Resource
    private MailConfig mailConfig;
    @Resource
    private TemplateEngine templateEngine;
    @Resource
    private JavaMailSender javaMailSender;


    @Resource(name = "redisTemplate")
    private RedisTemplate redisTemplate;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public WaybillDTO createWaybill(WaybillDTO dto) {
        DemoAssert.isNull(waybillRepo.findOneByBillCode(dto.getBillCode()), "运单已存在");
        WaybillDO waybillDO = toDomain(dto, WaybillDO.class);
        waybillDO.setCreatedTime(new Date());
        waybillDO.setUpdateTime(new Date());
        String key = RedisUtil.waybillKey(dto.getBillCode());
        WaybillDTO waybillDto = toDto(waybillRepo.save(waybillDO), WaybillDTO.class);
        RedisUtil.valueAdd(key, waybillDto, true, TimeUnit.MINUTES, redisTemplate);
        return waybillDto;
    }


    @Override
    public void deleteWaybillByBillCode(String billCode) {
        waybillRepo.deleteByBillCode(billCode);
        log.info("删除运单：{} 成功", billCode);
    }

    @Override
    public WaybillDTO updateWaybill(WaybillDTO dto) {

        WaybillDO waybillDO = waybillRepo.findOneByBillCode(dto.getBillCode());
        DemoAssert.notNull(waybillDO, "该运单不存在");
        if (dto.getCarrierName() != null) {
            waybillDO.setCarrierName(dto.getCarrierName());
        }
        if (dto.getCarrierEmail() != null) {
            waybillDO.setCarrierEmail(dto.getCarrierEmail());
        }
        waybillDO.setUpdateTime(new Date());
        return toDto(waybillRepo.save(waybillDO), WaybillDTO.class);
    }

    @Override
    public WaybillDTO getWaybillByBillCode(String billCode) {
        WaybillDTO waybillDto = new WaybillDTO();

        waybillDto = RedisUtil.valueGet(RedisUtil.waybillKey(billCode), redisTemplate);
        if (waybillDto != null) {
            log.info("走了redis缓存");
        } else {
            waybillDto = toDto(waybillRepo.findOneByBillCode(billCode), WaybillDTO.class);
            log.info("没有走redis缓存");
        }

        return waybillDto;
    }

    @Override
    public Page<WaybillDTO> listWaybill(WaybillDTO dto, Pageable pageable) {
        Page<WaybillDO> waybills = waybillRepo.findAll((root, query, cb) -> {
            List<Predicate> predicates = getWaybillPredicates(dto, root, cb);

            return cb.and(predicates.stream().toArray(Predicate[]::new));
        }, pageable);

        return waybills.map(v -> toDto(v, WaybillDTO.class));
    }

    private List<Predicate> getWaybillPredicates(WaybillDTO dto, Root<WaybillDO> root, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();
        String billCode = dto.getBillCode();
        if (billCode != null) {
            predicates.add(cb.like(root.get("billCode"), SqlUtil.wrapLike(billCode)));
        }

        String carrierName = dto.getCarrierName();
        if (carrierName != null) {
            predicates.add(cb.like(root.get("carrierName"), SqlUtil.wrapLike(carrierName)));
        }

        String carrierEmail = dto.getCarrierEmail();
        if (carrierEmail != null) {
            predicates.add(cb.like(root.get("billCode"), SqlUtil.wrapLike(carrierEmail)));
        }
        return predicates;
    }

    @Override
    public void createBatchWaybill(List<WaybillDO> waybillDOS) {
        waybillRepo.saveAll(waybillDOS);
    }

    @Override
    public void exportWaybill(WaybillDTO dto, HttpServletResponse response) throws IOException {

        List waybillDtos = getExportData(dto);


        if (CollectionUtils.isNotEmpty(waybillDtos)) {
            int count = waybillDtos.size();
            JSONArray jsonArray = new JSONArray(waybillDtos);
            String title = "运单信息导出";
            // 表头，key是获取数据时将要调用的get方法，value是显示的列标题
            Map<String, String> headMap = new LinkedHashMap<>();
            headMap.put("billCode", "运单号");
            headMap.put("carrierName", "承运人姓名");
            headMap.put("createdTime", "创建时间");
            headMap.put("carrierEmail", "推送邮箱");

            //超过限制数量后台下载以附件形式发送邮件到账户邮箱内
            if (count < ExportConstants.MID_LIMIT) {
                ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response);
            } else {
                CompletableFuture.runAsync(() -> {
                    InputStream inputStream = ExcelUtil.getExcelInputStream(title, headMap, jsonArray);

                    Context context = new Context();
//                    context.setVariable("createdTime", DateUtil.formatTimestampDate(new Date()));
                    String content = templateEngine.process("email", context);

                    MailUtil.MailParam param = new MailUtil.MailParam(mailConfig.getFrom(), mailConfig.getTo(), "运单信息", content, inputStream);
                    MailUtil.sendExcelMail(param, javaMailSender);
                });
                PrintWriter out = response.getWriter();
                out.print("数据导出，下载地址稍后发送到您的邮箱");
                out.flush();
            }
        }
    }

    @Override
    public void exportWaybillByAlibaba(WaybillDTO dto, HttpServletResponse response) throws IOException {
        List waybillDtos = getExportData(dto);
        EasyExcelUtil.export(waybillDtos, response, EasyExcelWaybillDTO.class);
//        EasyExcelUtil.exportNoModel(waybillDtos, response, WaybillDto.class);
    }


    private List getExportData(WaybillDTO dto) {
        List<WaybillDO> waybillDOS = waybillRepo.findAll((root, query, cb) -> {
            List<Predicate> predicates = getWaybillPredicates(dto, root, cb);
            return cb.and(predicates.stream().toArray(Predicate[]::new));
        });

        return waybillDOS.stream().map(x -> toDto(x, WaybillDTO.class)).collect(Collectors.toList());
    }


}
