package com.yang.springboot.lamda.groupBy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author Yang Hao
 * @date 2020-11-19 20:02
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Invoice {

    private Long id;

    private Long globalSimId;

    private String gIccid;

    private BigDecimal actualUsage;

    private Long servicePackageId;

    private String servicePackageName;

    private String countryCode;
    private Long serviceCategoryId;
}
