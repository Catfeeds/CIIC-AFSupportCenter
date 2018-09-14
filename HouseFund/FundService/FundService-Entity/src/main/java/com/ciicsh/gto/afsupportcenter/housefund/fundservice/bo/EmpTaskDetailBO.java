package com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class EmpTaskDetailBO {
    private String idNum;
    private Long empArchiveId;
    private String hfEmpAccount;
    private Integer archiveStatus;
    private String startMonth;
    private String endMonth;
    private String hfMonth;
    private String hfMonthStop;
    private String hfMonthHandle;
    private Long empTaskId;
    private BigDecimal amount;
    private String handleRemark;
}
