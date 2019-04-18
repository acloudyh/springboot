package com.yang.springboot.service;

import com.yang.springboot.domain.Waybill;
import com.yang.springboot.dto.WaybillDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

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


    /**
     * 测试用
     * @param waybills
     */
    void createBatchWaybill(List<Waybill> waybills);

    /**
     * 导出运单
     *
     * @param dto
     * @param response
     * @throws IOException
     */
    void exportWaybill(WaybillDto dto, HttpServletResponse response) throws IOException;


}
