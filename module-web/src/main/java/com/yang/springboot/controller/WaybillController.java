package com.yang.springboot.controller;

import com.yang.springboot.dto.WaybillDTO;
import com.yang.springboot.jpa.WaybillDO;
import com.yang.springboot.req.WaybillCreateRequest;
import com.yang.springboot.req.WaybillQueryRequest;
import com.yang.springboot.req.WaybillUpdateRequest;
import com.yang.springboot.service.WaybillService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author yanghao
 * @date 2019-04-18 16:50
 */
@RestController
@Slf4j
@RequestMapping(value = "/web/neo/waybill")
@Api(tags = "运单管理")
public class WaybillController {
    @Resource
    private WaybillService waybillService;
    @Value("${test.number}")
    private Integer testNumber;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "创建运单", notes = "创建相关运单")
    public WaybillDTO createWaybill(@RequestBody @Valid WaybillCreateRequest request) {
        WaybillDTO dto = new WaybillDTO();
        BeanUtils.copyProperties(request, dto);
        return waybillService.createWaybill(dto);
    }

    @RequestMapping(value = "/{billCode}", method = RequestMethod.DELETE)
    @ApiOperation(value = "删除运单", notes = "删除相关运单")
    public void deleteWaybill(@PathVariable String billCode) {
        waybillService.deleteWaybillByBillCode(billCode);
    }

    @RequestMapping(value = "/{billCode}", method = RequestMethod.PUT)
    @ApiOperation("更新运单")
    public WaybillDTO updateWaybill(@PathVariable String billCode,
                                    @RequestBody @Valid WaybillUpdateRequest request) {
        WaybillDTO dto = new WaybillDTO();
        BeanUtils.copyProperties(request, dto);
        dto.setBillCode(billCode);
        return waybillService.updateWaybill(dto);
    }

    @RequestMapping(value = "/{billCode}", method = RequestMethod.GET)
    @ApiOperation("根据运单号查询运单详情")
    public WaybillDTO getWaybillDetails(@PathVariable String billCode) {
        return waybillService.getWaybillByBillCode(billCode);
    }

    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation("根据运单号查询运单详情")
    public Page<WaybillDTO> listWaybill(WaybillQueryRequest request,
                                        @PageableDefault(sort = {"createdTime"}, direction = Sort.Direction.DESC) Pageable pageable) {
        WaybillDTO dto = new WaybillDTO();
        BeanUtils.copyProperties(request, dto);
        return waybillService.listWaybill(dto, pageable);
    }

    @RequestMapping(value = "/batch", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("批量创建运单，测试导出文件的功能,内部写死")
    public void createBatchWaybill() {
        long startTime = System.currentTimeMillis();
        List<WaybillDO> waybillDOS = new ArrayList<>();
        int num = 80000;

        for (int i = 0; i < num; i++) {
            WaybillDO waybillDO = new WaybillDO();
            waybillDO.setBillCode("wb" + i);
            waybillDO.setCarrierEmail(i + "@qq.com");
            waybillDO.setCarrierName("王刚" + i);
            waybillDO.setCreatedTime(new Date());
            waybillDOS.add(waybillDO);
        }

        waybillService.createBatchWaybill(waybillDOS);

        long endTime = System.currentTimeMillis();
        log.info("插入{}条数据耗时:{} ms", num, endTime - startTime);
    }

    @RequestMapping(value = "/export", method = RequestMethod.GET)
    @ApiOperation("Java POI 导出excel文件")
    public void exportWaybills(WaybillQueryRequest request, HttpServletResponse response) throws IOException {
        long startTime = System.currentTimeMillis();
        WaybillDTO dto = new WaybillDTO();
        BeanUtils.copyProperties(request, dto);
        waybillService.exportWaybill(dto, response);
        long endTime = System.currentTimeMillis();
        log.info("导出条数据耗时:{} ms", endTime - startTime);
    }

    @RequestMapping(value = "/export/alibaba", method = RequestMethod.GET)
    @ApiOperation("阿里easyexcel 导出excel文件")
    public void exportWaybillsByAlibaba(WaybillQueryRequest request, HttpServletResponse response) throws IOException {
        long startTime = System.currentTimeMillis();
        WaybillDTO dto = new WaybillDTO();
        BeanUtils.copyProperties(request, dto);
        waybillService.exportWaybillByAlibaba(dto, response);
        long endTime = System.currentTimeMillis();
        log.info("导出数据耗时:{} ms", endTime - startTime);
    }


}
