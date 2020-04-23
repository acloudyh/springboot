package com.yang.springboot.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @author yanghao
 * @date 2019-04-18 16:49
 */
@Data
@ApiModel
public class WaybillCreateRequest {

    @NotNull(message = "运单号不能为空")
    @Pattern(regexp = "^[0-9a-zA-Z_]{1,32}$", message = "billCode只能由1-32位字母数字下划线组成")
    @ApiModelProperty(value = "运单号")
    private String billCode;

    @ApiModelProperty(value = "承运人姓名")
    private String carrierName;

    @Email(message = "邮箱格式校验失败")
    @ApiModelProperty(value = "承运人邮箱")
    private String carrierEmail;
}
