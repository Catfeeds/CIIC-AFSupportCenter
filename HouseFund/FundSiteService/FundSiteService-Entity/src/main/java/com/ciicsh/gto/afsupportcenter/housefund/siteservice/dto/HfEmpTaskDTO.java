package com.ciicsh.gto.afsupportcenter.housefund.siteservice.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class HfEmpTaskDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String companyId;
    private String employeeId;
    private String employeeName;
    private String idNum;
    private Integer hfAccountType;
    private Integer hfType;
    private Integer paymentBank;
    private Integer taskCategory;
    private Integer urgent;
    private Integer processStatus;
    private Integer taskStatus;
    private LocalDate[] submitTime;
    private LocalDate submitTimeStart;
    private LocalDate submitTimeEnd;
    private String exceptTaskCategories;

    public void setSubmitTime(LocalDate[] submitTime) {
        this.submitTime = submitTime;
        if (submitTime != null && submitTime.length == 2) {
            setSubmitTimeStart(submitTime[0]);
            setSubmitTimeEnd(submitTime[1]);
        }
    }
}
