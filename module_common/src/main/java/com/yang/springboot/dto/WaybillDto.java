package com.yang.springboot.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author yanghao
 * @date 2019-04-18 16:52
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "Waybill", description = "运单")
public class WaybillDto implements Serializable {


    @ApiModelProperty(value = "运单号")
    private String billCode;

    @ApiModelProperty(value = "创建时间")
    private Date createdTime;

    @ApiModelProperty(value = "承运人姓名")
    private String carrierName;

    @ApiModelProperty(value = "承运人邮箱")
    private String carrierEmail;

}
