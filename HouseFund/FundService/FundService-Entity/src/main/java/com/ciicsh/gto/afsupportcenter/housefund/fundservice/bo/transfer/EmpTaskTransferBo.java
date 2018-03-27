package com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.transfer;

import lombok.Data;

import java.text.SimpleDateFormat;
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
    private String hfEmpAccount;
    private Integer status;
    private Integer taskStatus;
    private Integer archiveStatus;
    private Date inDate;
    private String submitTime;
    private String submitterId;
    private String comAccountId;
    private Integer ssAccountType;
    private Long empArchiveId;
    private Integer processCategory;
    private Integer taskCategory;
    private String transferOutUnit;
    private String transferOutUnitAccount;
    private String transferInUnit;
    private String transferInUnitAccount;
    private Date transferDate;
    private String transferDateFormat;
    private Date feedbackDate;
    private Date operateDate;
    private String handleRemark;
    private Integer paymentBank;
    private Integer hfAccountType;

    public String getTransferDateFormat() {
        if (this.transferDate != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            return sdf.format(this.transferDate);
        }
        return transferDateFormat;
    }
}
