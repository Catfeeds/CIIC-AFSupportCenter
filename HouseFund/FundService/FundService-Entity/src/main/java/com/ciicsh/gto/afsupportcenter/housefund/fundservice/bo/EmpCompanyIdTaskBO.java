package com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class EmpCompanyIdTaskBO {
    private Long empTaskId;
    private String companyId;
    private String employeeId;
    private Integer taskCategory;
    private BigDecimal empBase;
    private BigDecimal ratioCom;
    private BigDecimal ratioEmp;
    private BigDecimal amount;
    private String startMonth;
    private String endMonth;
    private String hfMonth;
    private Integer taskStatus;
    private String handleRemark;
    private String rejectionRemark;
    private String oldCityCode;
    private LocalDateTime modifiedTime;
    private String modifiedDisplayName;
}
