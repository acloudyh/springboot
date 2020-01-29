package com.yang.springboot.dto;

import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

import java.util.Date;

/**
 * easyExcel 无注解方式
 *
 * @author yanghao
 * @date 2019-05-13 17:19
 */
@Data
public class WaybillNoModel extends BaseRowModel {

    private String billCode;

    private Date createdTime;

    private String carrierName;

    private String carrierEmail;
}
