package com.yang.springboot.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Yang Hao
 * @date 2020/4/27
 */
@Data
@ApiModel
public class WaybillInfoVo {
    @ApiModelProperty(value = "运单号")
    private String billCode;

    @ApiModelProperty(value = "承运人姓名")
    private String carrierName;

    @ApiModelProperty(value = "承运人邮箱")
    private String carrierEmail;
}
