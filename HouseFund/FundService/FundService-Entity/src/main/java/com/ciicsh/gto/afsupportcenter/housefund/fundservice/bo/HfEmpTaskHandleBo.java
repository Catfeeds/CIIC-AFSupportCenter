package com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo;

import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.HfArchiveBasePeriod;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.HfEmpTaskPeriod;
import com.ciicsh.gto.afsupportcenter.util.constant.DictUtil;
import com.ciicsh.gto.afsupportcenter.util.constant.SocialSecurityConst;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
public class HfEmpTaskHandleBo implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long empTaskId;
    private Long empArchiveId;
    private Integer hfType;
    private String hfTypeName;
    private Integer processCategory;
    private Integer taskCategory;
//    private Integer dictTaskCategory;
    private Integer taskStatus;
    private Integer isChange;
    private String comHandleStatusName;
    private String companyId;
    private String companyName;
    private Integer paymentBank;
    private String paymentBankName;
    private Integer state;
    private String stateName;
    private Integer paymentWay;
    private String paymentWayName;
    private Integer ukeyStore;
    private String ukeyStoreName;
    private Integer comAccountId;
    private String comAccountName;
    private Long basicComAccountClassId;
    private Long addedComAccountClassId;
    private Integer basicComTaskStatus;
//    private String basicComTaskStatusName;
    private Integer addedComTaskStatus;
//    private String addedComTaskStatusName;
    private Integer hfAccountType;
    private String hfAccountTypeName;
    private String basicHfComAccount;
    private Integer basicComHfMonth;
    private String basicEndMonth;
    private String addedHfComAccount;
    private Integer addedComHfMonth;
    private String addedEndMonth;
    private String employeeId;
    private String employeeName;
    private String idNum;
    private LocalDate inDate;
    private LocalDate outDate;
    private String hfEmpAccount;
    private String startMonth;
    private String endMonth;
    private BigDecimal amount;
    private BigDecimal empBase;
    private BigDecimal ratioCom;
    private BigDecimal ratioEmp;
    private String basicHfEmpAccount;
    private Long basicEmpArchiveId;
    private Integer basicArchiveStatus;
    private String basicArchiveStatusName;
    private String addedHfEmpAccount;
    private Long addedEmpArchiveId;
    private Integer addedArchiveStatus;
    private String addedArchiveStatusName;
    private String handleRemark;
    private String rejectionRemark;
    private Integer operationRemind;
    private LocalDate operationRemindDate;
    private List<HfArchiveBasePeriod> basicArchiveBasePeriods;
    private List<HfArchiveBasePeriod> addedArchiveBasePeriods;
    private List<HfEmpTaskPeriod> empTaskPeriods;
    private List<HfEmpTaskRemarkBo> empTaskRemarks;
    private boolean canHandle;
    private String leaderShipId;
    private String leaderShipName;
    private String createdBy;
    private String createdDisplayName;
    private Integer serviceCenterId;
    private String serviceCenter;
    private String empBasStartMonth;
    private String empBasHandleMonth;
    private String empBasEndMonth;
    private String empBasStopHandleMonth;
    private String empBasLastMonth;
    private String empAddStartMonth;
    private String empAddHandleMonth;
    private String empAddEndMonth;
    private String empAddStopHandleMonth;
    private String empAddLastMonth;

    public String getHfTypeName() {
        return DictUtil.getInstance().getTextByItemValueAndTypeValue(String.valueOf(this.hfType), SocialSecurityConst.FUND_TYPE_KEY, false);
    }

    public String getPaymentBankName() {
        return DictUtil.getInstance().getTextByItemValueAndTypeValue(String.valueOf(this.paymentBank), SocialSecurityConst.PAY_BANK_KEY, false);
    }

    public String getStateName() {
        return DictUtil.getInstance().getTextByItemValueAndTypeValue(String.valueOf(this.state), SocialSecurityConst.COM_ACCOUNT_STATE_KEY, false);
    }

    public String getPaymentWayName() {
        return DictUtil.getInstance().getTextByItemValueAndTypeValue(String.valueOf(this.paymentWay), SocialSecurityConst.COM_PAYMENT_WAY_KEY, false);
    }

    public String getUkeyStoreName() {
        return DictUtil.getInstance().getTextByItemValueAndTypeValue(String.valueOf(this.ukeyStore), SocialSecurityConst.COM_UKEY_STORE_KEY, false);
    }

    public String getComTaskStatusName() {
        Integer comTaskStatus = this.basicComTaskStatus;
        if (this.hfType == 2) {
            comTaskStatus = this.addedComTaskStatus;
        }
        if (comTaskStatus == null || comTaskStatus == 1) {
            return "(开户受理中)";
        } else if (comTaskStatus == 2) {
            return "(开户送审中)";
        } else if (comTaskStatus == 4) {
            return "(开户已批退)";
        } else {
            return null;
        }
    }

//    public Integer getDictTaskCategory () {
//        if (this.dictTaskCategory == null) {
//            this.dictTaskCategory = EmpTaskCategoryConverter.convertDictItemFromCategories(this);
//        }
//        return this.dictTaskCategory;
//    }

//    public String getBasicComTaskStatusName() {
//        return DictUtil.getInstance().getTextByItemValueAndTypeValue(String.valueOf(this.basicComTaskStatus), DictUtil.TYPE_VALUE_TASK_PROCESS_STATUS, false);
//    }
//
//    public String getAddedComTaskStatusName() {
//        return DictUtil.getInstance().getTextByItemValueAndTypeValue(String.valueOf(this.addedComTaskStatus), DictUtil.TYPE_VALUE_TASK_PROCESS_STATUS, false);
//    }

    public String getHfAccountTypeName() {
        return DictUtil.getInstance().getTextByItemValueAndTypeValue(String.valueOf(this.hfAccountType), DictUtil.TYPE_VALUE_SOCIAL_SECURITY_ACCOUNT_TYPE, false);
    }

    public String getBasicArchiveStatusName() {
        return DictUtil.getInstance().getTextByItemValueAndTypeValue(String.valueOf(this.basicArchiveStatus), SocialSecurityConst.EMP_ARCHIVE_STATUS, false);
    }

    public String getAddedArchiveStatusName() {
        return DictUtil.getInstance().getTextByItemValueAndTypeValue(String.valueOf(this.addedArchiveStatus), SocialSecurityConst.EMP_ARCHIVE_STATUS, false);
    }
}
