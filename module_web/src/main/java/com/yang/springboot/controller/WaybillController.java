package com.yang.springboot.controller;

import com.yang.springboot.dto.WaybillDto;
import com.yang.springboot.req.WaybillCreateRequest;
import com.yang.springboot.req.WaybillQueryRequest;
import com.yang.springboot.req.WaybillUpdateRequest;
import com.yang.springboot.service.WaybillService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @author yanghao
 * @date 2019-04-18 16:50
 */
@RestController
@Slf4j
@RequestMapping(value = "/web/neo/waybill")
@Api(tags = "运单管理", description = "运单管理")
public class WaybillController {
    @Resource
    private WaybillService waybillService;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("创建运单")
    public WaybillDto createWaybill(@RequestBody @Valid WaybillCreateRequest request) {
        WaybillDto dto = new WaybillDto();
        BeanUtils.copyProperties(request, dto);
        return waybillService.createWaybill(dto);
    }

    @RequestMapping(value = "/{billCode}", method = RequestMethod.DELETE)
    @ApiOperation("删除运单")
    public void deleteWaybill(@PathVariable String billCode) {
        waybillService.deleteWaybillByBillCode(billCode);
    }

    @RequestMapping(value = "/{billCode}", method = RequestMethod.PUT)
    @ApiOperation("更新运单")
    public WaybillDto updateWaybill(@PathVariable String billCode,
                                    @RequestBody @Valid WaybillUpdateRequest request) {
        WaybillDto dto = new WaybillDto();
        BeanUtils.copyProperties(request, dto);
        dto.setBillCode(billCode);
        return waybillService.updateWaybill(dto);
    }

    @RequestMapping(value = "/{billCode}", method = RequestMethod.GET)
    @ApiOperation("根据运单号查询运单详情")
    public WaybillDto getWaybillDetails(@PathVariable String billCode) {
        return waybillService.getWaybillByBillCode(billCode);
    }

    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation("根据运单号查询运单详情")
    public Page<WaybillDto> listWaybill(WaybillQueryRequest request,
                                        @PageableDefault(sort = {"createdTime"}, direction = Sort.Direction.DESC) Pageable pageable) {
        WaybillDto dto = new WaybillDto();
        BeanUtils.copyProperties(request, dto);
        return waybillService.listWaybill(dto, pageable);
    }

}
