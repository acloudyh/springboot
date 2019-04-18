package com.yang.springboot.service;

import com.yang.springboot.dto.WaybillDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author yanghao
 * @date 2019-04-18 16:43
 */
public interface WaybillService {

    /**
     * 创建运单
     *
     * @param dto
     * @return
     */
    WaybillDto createWaybill(WaybillDto dto);


    /**
     * 根据运单号删除运单
     *
     * @param billCode
     */
    void deleteWaybillByBillCode(String billCode);


    /**
     * 更改运单
     * @param dto
     * @return
     */
    WaybillDto updateWaybill(WaybillDto dto);


    /**
     * 根据运单号查询运单详情
     *
     * @param billCode
     * @return
     */
    WaybillDto getWaybillByBillCode(String billCode);

    /**
     * 运单分页接口
     * @param dto
     * @param pageable
     * @return
     */
    Page<WaybillDto> listWaybill(WaybillDto dto, Pageable pageable);
}
