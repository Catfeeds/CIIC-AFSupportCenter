package com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.payment;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class HFNetBankExportBO {

    private String hfEmpAccount;
    private String companyId;
    private String employeeId;
    private String employeeName;
    private String idNum;
    private LocalDate birthday;
    private Integer hfType;
    private Boolean gender;
    private String startMonth;
    private String endMonth;
    private BigDecimal amount;
    private BigDecimal empAmount;
    private BigDecimal comAmount;
    private BigDecimal ratio;
    private BigDecimal ratioEmp;
    private BigDecimal ratioCom;
    private BigDecimal base;
}
