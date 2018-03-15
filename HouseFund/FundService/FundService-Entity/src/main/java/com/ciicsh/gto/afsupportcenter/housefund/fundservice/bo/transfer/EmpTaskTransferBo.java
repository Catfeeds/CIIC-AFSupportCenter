package com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.transfer;

import lombok.Data;

@Data
public class EmpTaskTransferBo {
    private String empTaskId;
    private String hfType;
    private String companyId;
    private String title;
    private String employeeId;
    private String employeeName;
    private String idNum;
    private String status;
    private String taskStatus;
    private String inDate;
    private String submitTime;
    private String submitterId;
    private String transferInUnit;
    private String transferOutUnit;
    private String comAccountId;
    private String ssAccountType;
    private String hfEmpAccount;
    private String empArchiveId;
}
