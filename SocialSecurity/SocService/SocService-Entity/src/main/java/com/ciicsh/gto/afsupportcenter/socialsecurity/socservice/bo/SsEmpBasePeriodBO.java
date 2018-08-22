package com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class SsEmpBasePeriodBO {
    private Long empArchiveId;
    private BigDecimal baseAmount;
    private String startMonth;
    private String endMonth;
    private Integer taskCategory;
    private Integer intervalYear;
}
