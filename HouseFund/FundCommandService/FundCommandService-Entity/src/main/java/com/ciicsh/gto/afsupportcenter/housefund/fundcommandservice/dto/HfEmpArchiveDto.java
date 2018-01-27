package com.ciicsh.gto.afsupportcenter.housefund.fundcommandservice.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
public class HfEmpArchiveDto  {

    private String companyId;
    private String title;
    private String comAccountId;
    private String belongVendor;
    private String hfAccountTyp;
    private String serviceCenter;
    private String empStatus;
    private String hfAccountType;
    private String employeeId;
    private String employeeName;
    private String paymentBank;
    private String idNum;
    private String hfEmpAccount;
    private String archiveTaskStatus;
    private String hfEmpAccountBc;
    private String archiveTaskStatusBc;
    private String operationRemind;
    private String serviceManager;
    private Date operationRemindDate;

}
