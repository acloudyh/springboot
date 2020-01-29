package com.yang.springboot.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 其中继承BaseRowModel，仅用于测试阿里easyexcel导出文件的model
 *
 * @author yanghao
 * @date 2019-04-18 16:52
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "Waybill", description = "运单")
public class WaybillDTO extends BaseRowModel implements Serializable {

    @ApiModelProperty(value = "运单号")
    @ExcelProperty(value = "运单号", index = 0)
    private String billCode;

    @ApiModelProperty(value = "创建时间")
    @ExcelProperty(value = "创建时间", index = 1, format = "yyyy-MM-dd-HH-mm-ss")
    private Date createdTime;

    @ApiModelProperty(value = "承运人姓名")
    @ExcelProperty(value = "承运人姓名", index = 2)
    private String carrierName;

    @ApiModelProperty(value = "承运人邮箱")
    @ExcelProperty(value = "承运人邮箱", index = 3)
    private String carrierEmail;


}
