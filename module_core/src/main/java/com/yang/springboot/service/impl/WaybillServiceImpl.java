package com.yang.springboot.service.impl;

import com.yang.springboot.domain.Waybill;
import com.yang.springboot.dto.WaybillDto;
import com.yang.springboot.repo.WaybillRepo;
import com.yang.springboot.service.WaybillService;
import com.yang.springboot.utils.SqlUtils;
import io.jsonwebtoken.lang.Assert;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

            return cb.and(predicates.stream().toArray(Predicate[]::new));
        }, pageable);

        return waybills.map(v -> toDto(v, WaybillDto.class));
    }


}
