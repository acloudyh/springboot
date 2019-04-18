package com.yang.springboot.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Email;

/**
 * @author yanghao
 * @date 2019-04-18 17:22
 */
@Data
public class WaybillUpdateRequest {


    @ApiModelProperty(value = "承运人姓名")
    private String carrierName;

    @Email(message = "邮箱格式校验失败")
    @ApiModelProperty(value = "承运人邮箱")
    private String carrierEmail;
}
