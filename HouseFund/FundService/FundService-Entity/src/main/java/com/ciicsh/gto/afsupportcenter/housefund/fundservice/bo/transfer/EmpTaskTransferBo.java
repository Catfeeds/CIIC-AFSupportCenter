package com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.transfer;

import com.ciicsh.gto.afsupportcenter.util.DateUtil;
import com.ciicsh.gto.afsupportcenter.util.constant.DictUtil;
import com.ciicsh.gto.afsupportcenter.util.constant.SocialSecurityConst;
import lombok.Data;

import java.text.SimpleDateFormat;
import java.time.LocalDate;

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
    private LocalDate inDate;
    private LocalDate submitTime;
    private String submitterId;
    private String comAccountId;
    private Integer ssAccountType;
    private Long empArchiveId;
    private Long belongEmpArchiveId;
    private Integer processCategory;
    private Integer taskCategory;
    private String transferOutUnit;
    private String transferOutUnitAccount;
    private String transferInUnit;
    private String transferInUnitAccount;
    private LocalDate transferDate;
    private String transferDateFormat;
    private LocalDate feedbackDate;
    private LocalDate operateDate;
    private String handleRemark;
    private Integer paymentBank;
    private Integer hfAccountType;
    private String createdDisplayName;
    private Integer serviceCenterValue;
    private String handleUserId;
    private String handleUserName;
    private String paymentBankName;
    private Integer empStatus;
    private Integer archiveTaskStatus;

    private String endMonth;
    private String lastHandleMonth;
    private String comHfMonth;

    private Long empCompanyId;

    public String getTransferDateFormat() {
        if (this.transferDate != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            return sdf.format(DateUtil.localDateToDate(this.transferDate));
        }
        return transferDateFormat;
    }

    public String getPaymentBankName() {
        if (this.paymentBank != null) {
            return DictUtil.getInstance().getTextByItemValueAndTypeValue(String.valueOf(this.paymentBank), SocialSecurityConst.PAY_BANK_KEY, false);
        }
        return null;
    }
}
