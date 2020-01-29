package com.yang.springboot.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author Yang Hao
 * @date 2020/1/29 21:36
 */
@Data
@ApiModel("BaseVO")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseVO {
    @ApiModelProperty(value = "创建时间")
    private Date createdTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

}
