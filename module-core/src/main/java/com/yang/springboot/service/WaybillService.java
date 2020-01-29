package com.yang.springboot.service;

import com.yang.springboot.jpa.WaybillDO;
import com.yang.springboot.dto.WaybillDTO;
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
    WaybillDTO createWaybill(WaybillDTO dto);


    /**
     * 根据运单号删除运单
     *
     * @param billCode
     */
    void deleteWaybillByBillCode(String billCode);


    /**
     * 更改运单
     *
     * @param dto
     * @return
     */
    WaybillDTO updateWaybill(WaybillDTO dto);


    /**
     * 根据运单号查询运单详情
     *
     * @param billCode
     * @return
     */
    WaybillDTO getWaybillByBillCode(String billCode);

    /**
     * 运单分页接口
     *
     * @param dto
     * @param pageable
     * @return
     */
    Page<WaybillDTO> listWaybill(WaybillDTO dto, Pageable pageable);


    /**
     * 测试用
     *
     * @param waybillDOS
     */
    void createBatchWaybill(List<WaybillDO> waybillDOS);

    /**
     * 导出运单
     *
     * @param dto
     * @param response
     * @throws IOException
     */
    void exportWaybill(WaybillDTO dto, HttpServletResponse response) throws IOException;

    /**
     * 导出运单
     * alibaba 开源项目
     *
     * @param dto
     * @param response
     * @throws IOException
     */
    void exportWaybillByAlibaba(WaybillDTO dto, HttpServletResponse response) throws IOException;


}
