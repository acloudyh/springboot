package com.yang.springboot.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Yang Hao
 * @date 2020/1/29 21:38
 */
@Data
@ApiModel("WaybillVO")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WaybillVO extends BaseVO {

    @ApiModelProperty(value = "运单号")
    private String billCode;

    @ApiModelProperty(value = "承运人姓名")
    private String carrierName;

    @ApiModelProperty(value = "承运人邮箱")
    private String carrierEmail;
}
