package com.yang.springboot.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author yanghao
 * @date 2019-04-18 17:23
 */
@Data
public class WaybillQueryRequest {
    @ApiModelProperty(value = "运单号")
    private String billCode;

    @ApiModelProperty(value = "承运人姓名")
    private String carrierName;

    private String carrierEmail;
}
