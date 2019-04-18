package com.yang.springboot.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.yang.springboot.domain.Waybill;
import com.yang.springboot.dto.WaybillDto;
import com.yang.springboot.repo.WaybillRepo;
import com.yang.springboot.service.WaybillService;
import com.yang.springboot.utils.ExcelUtil;
import com.yang.springboot.utils.SqlUtils;
import io.jsonwebtoken.lang.Assert;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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

    @Override
    public WaybillDto createWaybill(WaybillDto dto) {
        Assert.isNull(waybillRepo.findOneByBillCode(dto.getBillCode()), "运单已存在");
        Waybill waybill = toDomain(dto, Waybill.class);
        waybill.setCreatedTime(new Date());
        return toDto(waybillRepo.save(waybill), WaybillDto.class);
    }


    @Override
    public void deleteWaybillByBillCode(String billCode) {
        waybillRepo.deleteByBillCode(billCode);
        log.info("删除运单：{} 成功", billCode);
    }

    @Override
    public WaybillDto updateWaybill(WaybillDto dto) {

        Waybill waybill = waybillRepo.findOneByBillCode(dto.getBillCode());
        Assert.notNull(waybill, "该运单不存在");
        if (dto.getCarrierName() != null) {
            waybill.setCarrierName(dto.getCarrierName());
        }
        if (dto.getCarrierEmail() != null) {
            waybill.setCarrierEmail(dto.getCarrierEmail());
        }

        return toDto(waybillRepo.save(waybill), WaybillDto.class);
    }

    @Override
    public WaybillDto getWaybillByBillCode(String billCode) {
        return toDto(waybillRepo.findOneByBillCode(billCode), WaybillDto.class);
    }

    @Override
    public Page<WaybillDto> listWaybill(WaybillDto dto, Pageable pageable) {
        Page<Waybill> waybills = waybillRepo.findAll((root, query, cb) -> {
            List<Predicate> predicates = getWaybillPredicates(dto, root, cb);

            return cb.and(predicates.stream().toArray(Predicate[]::new));
        }, pageable);

        return waybills.map(v -> toDto(v, WaybillDto.class));
    }

    private List<Predicate> getWaybillPredicates(WaybillDto dto, Root<Waybill> root, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();
        String billCode = dto.getBillCode();
        if (billCode != null) {
            predicates.add(cb.like(root.get("billCode"), SqlUtils.wrapLike(billCode)));
        }

        String carrierName = dto.getCarrierName();
        if (carrierName != null) {
            predicates.add(cb.like(root.get("carrierName"), SqlUtils.wrapLike(carrierName)));
        }

        String carrierEmail = dto.getCarrierEmail();
        if (carrierEmail != null) {
            predicates.add(cb.like(root.get("billCode"), SqlUtils.wrapLike(carrierEmail)));
        }
        return predicates;
    }

    @Override
    public void createBatchWaybill(List<Waybill> waybills) {
        waybillRepo.save(waybills);
    }

    @Override
    public void exportWaybill(WaybillDto dto, HttpServletResponse response) throws IOException {

        List<Waybill> waybills = waybillRepo.findAll((root, query, cb) -> {
            List<Predicate> predicates = getWaybillPredicates(dto, root, cb);
            return cb.and(predicates.stream().toArray(Predicate[]::new));
        });

        List waybillDtos = waybills.stream().map(x -> toDto(x, WaybillDto.class)).collect(Collectors.toList());


        if (CollectionUtils.isNotEmpty(waybillDtos)) {
            int count = waybillDtos.size();
            JSONArray jsonArray = new JSONArray(waybillDtos);
            String title = "运单信息导出";
            //获取属性-列头
            Map<String, String> headMap = new LinkedHashMap<>();
            headMap.put("id", "id");
            headMap.put("billCode", "运单号");
            headMap.put("carrierName", "承运人姓名");
            headMap.put("createdTime", "创建时间");
            headMap.put("carrierEmail", "推送邮箱");

            if (count < 100000) {
                ExcelUtil.downloadExcelFile(title, headMap, jsonArray, response);
            } else {
//                CompletableFuture.runAsync(() -> {
//                    InputStream inputStream = ExcelUtil.getExcelInputStream(title, headMap, jsonArray);
//                    MailUtil.MailParam param = new MailUtil.MailParam(mailProperties.getUsername(), securityService.currentEmail(), "运单信息", "运单数据已导出excel，请查收附件", inputStream);
//                    MailUtil.sendExcelMail(param, javaMailSender);
//                });
//                PrintWriter out = response.getWriter();
//                out.print("数据导出，下载地址稍后发送到您的邮箱");
//                out.flush();
            }
        }
    }


}
