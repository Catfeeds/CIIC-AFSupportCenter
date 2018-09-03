package com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import com.ciicsh.gto.afsupportcenter.util.DateUtil;
import com.ciicsh.gto.afsupportcenter.util.constant.DictUtil;
import com.ciicsh.gto.afsupportcenter.util.constant.SocialSecurityConst;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@ExcelTarget("hfEmpTaskReject")
public class HfEmpTaskRejectExportBo implements Serializable{
    private static final long serialVersionUID = 1L;

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd");

    private Long empTaskId;

    @Excel(name = "客户编号", orderNum = "4", width = 20)
    private String companyId;
    @Excel(name = "企业客户", orderNum = "3", width = 25)
    private String companyName;
    @Excel(name = "雇员编号", orderNum = "2", width = 20)
    private String employeeId;
    @Excel(name = "雇员", orderNum = "1", width = 20)
    private String employeeName;
    @Excel(name = "雇员证件号", orderNum = "5", width = 25)
    private String idNum;
    private Integer hfType;
    @Excel(name = "公积金类型", orderNum = "6", width = 15)
    private String hfTypeName;
    @Excel(name = "公积金账号", orderNum = "7", width = 20)
    private String hfEmpAccount;
    private Integer processCategory;
//    private Integer dictTaskCategory;
    private Integer taskCategory;
    @Excel(name = "任务单类型", width = 15)
    private String taskCategoryName;
    private Integer urgent;
//    private String urgentName;
    private Integer isChange;
//    @Excel(name = "更正", orderNum = "1",  width = 8)
//    private String isChangeName;
    private String submitterId;
    private LocalDateTime submitTime;
    @Excel(name = "发起时间", orderNum = "24",  width = 20)
    private String submitTimeFormat;
    private Integer taskStatus;
    private String taskId;
    private String modifiedBy;
    @Excel(name = "批退人", orderNum = "25",  width = 20)
    private String modifiedDisplayName;
    private LocalDateTime modifiedTime;
    @Excel(name = "批退时间", orderNum = "26",  width = 20)
    private String modifiedTimeFormat;
    @Excel(name = "批退备注", orderNum = "27",  width = 30)
    private String rejectionRemark;
    @Excel(name = "开始年月", orderNum = "21")
    private String startMonth;
    @Excel(name = "截止年月", orderNum = "22")
    private String endMonth;
    @Excel(name = "缴费金额", orderNum = "8", width = 15 )
    private BigDecimal amount;
    @Excel(name = "基数", orderNum = "9", width = 18 )
    private BigDecimal empBase;
    @Excel(name = "个人比例", orderNum = "10")
    private BigDecimal ratioEmp;
    @Excel(name = "企业比例", orderNum = "11")
    private BigDecimal ratioCom;
    @Excel(name = "执行年月", orderNum = "12")
    private String handleDate;
    @Excel(name = "账户类型", orderNum = "13")
    private String hfAccountTypeName;
    @Excel(name = "企业账户名称", orderNum = "18", width = 25)
    private String comAccountName;

    @Excel(name = "是否终止", replace = {"否_0", "否_1", "是_2"}, orderNum = "20")
    private Integer state;
    @Excel(name = "企业账号", orderNum = "19", width = 20)
    private String hfComAccount;
    @Excel(name = "客户经理", orderNum = "14")
    private String leaderShipName;
    private Integer archiveStatus;
    @Excel(name = "公积金状态", orderNum = "15", width = 18)
    private String archiveStatusName;
    private LocalDate operationRemindDate;
    @Excel(name = "操作提示日期", orderNum = "16", width = 20)
    private String operationRemindDateFormat;
    private Integer operationRemind;
    @Excel(name = "操作提示", orderNum = "17", width = 20)
    private String operationRemindName;
    private String createdBy;
    @Excel(name = "发起人", orderNum = "23",  width = 20)
    private String createdDisplayName;

    private Integer hasOut;
    private Integer hfAccountType;

    public Long getEmpTaskId() {
        return empTaskId;
    }

    public void setEmpTaskId(Long empTaskId) {
        this.empTaskId = empTaskId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getIdNum() {
        return idNum;
    }

    public void setIdNum(String idNum) {
        this.idNum = idNum;
    }

    public Integer getHfType() {
        return hfType;
    }

    public void setHfType(Integer hfType) {
        this.hfType = hfType;
    }

    public String getHfTypeName() {
        return DictUtil.getInstance().getTextByItemValueAndTypeValue(String.valueOf(this.hfType), SocialSecurityConst.FUND_TYPE_KEY, true);
//        return hfTypeName;
    }

    public void setHfTypeName(String hfTypeName) {
        this.hfTypeName = hfTypeName;
    }

    public String getHfEmpAccount() {
        return hfEmpAccount;
    }

    public void setHfEmpAccount(String hfEmpAccount) {
        this.hfEmpAccount = hfEmpAccount;
    }

    public Integer getProcessCategory() {
        return processCategory;
    }

    public void setProcessCategory(Integer processCategory) {
        this.processCategory = processCategory;
    }

//    public Integer getDictTaskCategory() {
//        if (this.dictTaskCategory == null) {
//            this.dictTaskCategory = EmpTaskCategoryConverter.convertDictItemFromCategories(this);
//        }
//        return this.dictTaskCategory;
//    }

//    public void setDictTaskCategory(Integer dictTaskCategory) {
//        this.dictTaskCategory = dictTaskCategory;
//    }

    public Integer getTaskCategory() {
        return taskCategory;
    }

    public void setTaskCategory(Integer taskCategory) {
        this.taskCategory = taskCategory;
    }

    public Integer getUrgent() {
        return urgent;
    }

    public void setUrgent(Integer urgent) {
        this.urgent = urgent;
    }

    public String getSubmitterId() {
        return submitterId;
    }

    public void setSubmitterId(String submitterId) {
        this.submitterId = submitterId;
    }

    public Integer getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(Integer taskStatus) {
        this.taskStatus = taskStatus;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getTaskCategoryName() {

//        getDictTaskCategory();
        return DictUtil.getInstance().getTextByItemValueAndTypeValue(String.valueOf(this.taskCategory), DictUtil.TYPE_VALUE_HF_LOCAL_TASK_CATEGORY, true);
//        return taskCategoryName;
    }

    public void setTaskCategoryName(String taskCategoryName) {
        this.taskCategoryName = taskCategoryName;
    }

    public LocalDateTime getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(LocalDateTime submitTime) {
        this.submitTime = submitTime;
    }

//    public String getUrgentName() {
//        if (urgent != null && urgent == 1) {
//            urgentName = "加急";
//        }
//        return urgentName;
//    }
//
//    public void setUrgentName(String urgentName) {
//        this.urgentName = urgentName;
//    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public LocalDateTime getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(LocalDateTime modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public String getRejectionRemark() {
        return rejectionRemark;
    }

    public void setRejectionRemark(String rejectionRemark) {
        this.rejectionRemark = rejectionRemark;
    }

    public Integer getIsChange() {
        return isChange;
    }

    public void setIsChange(Integer isChange) {
        this.isChange = isChange;
    }
//    public String getIsChangeName() {
//        if (isChange != null && isChange == 1) {
//            isChangeName = "是";
//        } else {
//            isChangeName = "否";
//        }
//        return isChangeName;
//    }
//
//    public void setIsChangeName(String isChangeName) {
//        this.isChangeName = isChangeName;
//    }

    public String getModifiedDisplayName() {
        return modifiedDisplayName;
    }

    public void setModifiedDisplayName(String modifiedDisplayName) {
        this.modifiedDisplayName = modifiedDisplayName;
    }

    public String getSubmitTimeFormat() {
        if (this.submitTime != null) {
            return this.submitTime.format(formatter);
        }
        return submitTimeFormat;
    }

    public void setSubmitTimeFormat(String submitTimeFormat) {
        this.submitTimeFormat = submitTimeFormat;
    }

    public String getModifiedTimeFormat() {
        if (this.modifiedTime != null) {
            return this.modifiedTime.format(formatter);
        }
        return modifiedTimeFormat;
    }

    public void setModifiedTimeFormat(String modifiedTimeFormat) {
        this.modifiedTimeFormat = modifiedTimeFormat;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedDisplayName() {
        return createdDisplayName;
    }

    public void setCreatedDisplayName(String createdDisplayName) {
        this.createdDisplayName = createdDisplayName;
    }

    public Integer getHasOut() {
        return hasOut;
    }

    public void setHasOut(Integer hasOut) {
        this.hasOut = hasOut;
    }

    public Integer getHfAccountType() {
        return hfAccountType;
    }

    public void setHfAccountType(Integer hfAccountType) {
        this.hfAccountType = hfAccountType;
    }

    public String getStartMonth() {
        return startMonth;
    }

    public void setStartMonth(String startMonth) {
        this.startMonth = startMonth;
    }

    public String getEndMonth() {
        return endMonth;
    }

    public void setEndMonth(String endMonth) {
        this.endMonth = endMonth;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getEmpBase() {
        return empBase;
    }

    public void setEmpBase(BigDecimal empBase) {
        this.empBase = empBase;
    }

    public BigDecimal getRatioEmp() {
        return ratioEmp;
    }

    public void setRatioEmp(BigDecimal ratioEmp) {
        this.ratioEmp = ratioEmp;
    }

    public BigDecimal getRatioCom() {
        return ratioCom;
    }

    public void setRatioCom(BigDecimal ratioCom) {
        this.ratioCom = ratioCom;
    }

    public String getHandleDate() {
        return handleDate;
    }

    public void setHandleDate(String handleDate) {
        this.handleDate = handleDate;
    }

    public String getComAccountName() {
        return comAccountName;
    }

    public void setComAccountName(String comAccountName) {
        this.comAccountName = comAccountName;
    }

    public String getHfAccountTypeName() {
        return DictUtil.getInstance().getTextByItemValueAndTypeValue(String.valueOf(this.hfAccountType), DictUtil.TYPE_VALUE_SOCIAL_SECURITY_ACCOUNT_TYPE, false);
    }

    public void setHfAccountTypeName(String hfAccountTypeName) {
        this.hfAccountTypeName = hfAccountTypeName;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getHfComAccount() {
        return hfComAccount;
    }

    public void setHfComAccount(String hfComAccount) {
        this.hfComAccount = hfComAccount;
    }

    public String getLeaderShipName() {
        return leaderShipName;
    }

    public void setLeaderShipName(String leaderShipName) {
        this.leaderShipName = leaderShipName;
    }

    public Integer getArchiveStatus() {
        return archiveStatus;
    }

    public void setArchiveStatus(Integer archiveStatus) {
        this.archiveStatus = archiveStatus;
    }

    public String getArchiveStatusName() {
        return DictUtil.getInstance().getTextByItemValueAndTypeValue(String.valueOf(this.archiveStatus), SocialSecurityConst.EMP_ARCHIVE_STATUS, false);
    }

    public void setArchiveStatusName(String archiveStatusName) {
        this.archiveStatusName = archiveStatusName;
    }

    public LocalDate getOperationRemindDate() {
        return operationRemindDate;
    }

    public void setOperationRemindDate(LocalDate operationRemindDate) {
        this.operationRemindDate = operationRemindDate;
    }

    public String getOperationRemindDateFormat() {
        if (this.operationRemindDate != null) {
            return DateUtil.yyyyMMddHyphen(this.operationRemindDate);
        }
        return operationRemindDateFormat;
    }

    public void setOperationRemindDateFormat(String operationRemindDateFormat) {
        this.operationRemindDateFormat = operationRemindDateFormat;
    }

    public Integer getOperationRemind() {
        return operationRemind;
    }

    public void setOperationRemind(Integer operationRemind) {
        this.operationRemind = operationRemind;
    }

    public String getOperationRemindName() {
        if (this.operationRemind != null) {
            return DictUtil.getInstance().getTextByItemValueAndTypeValue(String.valueOf(this.operationRemind), SocialSecurityConst.OPERATION_REMIND_KEY, false);
        }
        return operationRemindName;
    }

    public void setOperationRemindName(String operationRemindName) {
        this.operationRemindName = operationRemindName;
    }
}
