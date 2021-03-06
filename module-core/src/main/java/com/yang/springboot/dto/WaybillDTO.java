package com.yang.springboot.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Yang Hao
 * @date 2020/1/29 21:53
 */
@Data
@ApiModel("WaybillDTO")
public class WaybillDTO extends BaseDTO implements Serializable {

    private static final long serialVersionUID = 8935807941634142743L;

    @ApiModelProperty(value = "运单号")
    private String billCode;

    @ApiModelProperty(value = "承运人姓名")
    private String carrierName;

    @ApiModelProperty(value = "承运人邮箱")
    private String carrierEmail;
}
