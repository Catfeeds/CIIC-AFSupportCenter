package com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.transfer;

import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class EmpTaskTransferBo {
    private Long empTaskId;
    private Integer hfType;
    private String companyId;
    private String title;
    private String employeeId;
    private String employeeName;
    private String idNum;
    private Integer status;
    private Integer taskStatus;
    private Date inDate;
    private String submitTime;
    private String submitterId;
    private String transferInUnit;
    private String transferOutUnit;
    private String comAccountId;
    private Integer ssAccountType;
    private String hfEmpAccount;
    private Long empArchiveId;
    private Integer processCategory;
    private Integer taskCategory;
    private String transferInUnitAccount;
    private String transferOutUnitAccount;
    private Date transferDate;
    private Date feedbackDate;
    private Date operateDate;
    private String handleRemark;
}
