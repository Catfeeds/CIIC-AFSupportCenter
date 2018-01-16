package com.ciicsh.gto.afsupportcenter.socjob.entity.custom;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * Created by houwanhua on 2018/1/16.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SsMonthChargeExt {
    private Long monthChargeId;
    private Long comAccountId;
    private String ssMonthBelong;
    private String ssMonth;
    private String employeeId;
    private BigDecimal baseAmount;
    private String ssType;
    private String ssTypeName;
    private BigDecimal comAmount;
    private BigDecimal empAmount;
    private BigDecimal subTotalAmount;
    /**
     * 费用类别：1标准 2 新进 3 转入  4 补缴 5 调整 6 转出 7封存 8 退账
     */
    private Integer category;
    /**
     * 费用类别名称
     */
    private String categoryName;
}
