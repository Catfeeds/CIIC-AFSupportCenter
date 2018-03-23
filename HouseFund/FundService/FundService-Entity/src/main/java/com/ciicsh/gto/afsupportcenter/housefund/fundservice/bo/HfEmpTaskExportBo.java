package com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.convertor.EmpTaskCategoryConverter;
import com.ciicsh.gto.afsupportcenter.util.constant.DictUtil;
import com.ciicsh.gto.afsupportcenter.util.constant.SocialSecurityConst;

import java.io.Serializable;
import java.time.LocalDateTime;

@ExcelTarget("hfEmpTask")
public class HfEmpTaskExportBo implements Serializable{
    private static final long serialVersionUID = 1L;

    private Long empTaskId;

    @Excel(name = "客户编号", orderNum = "6", width = 20)
    private String companyId;
    @Excel(name = "企业客户", orderNum = "5", width = 25)
    private String companyName;
    @Excel(name = "雇员编号", orderNum = "3", width = 20)
    private String employeeId;
    @Excel(name = "雇员", orderNum = "2", width = 20)
    private String employeeName;
    @Excel(name = "雇员证件号", orderNum = "4", width = 25)
    private String idNum;
    private Integer hfType;
    @Excel(name = "公积金类型", orderNum = "7", width = 15)
    private String hfTypeName;
    @Excel(name = "公积金账号", orderNum = "8", width = 20)
    private String hfEmpAccount;
    private Integer processCategory;
    private Integer dictTaskCategory;
    private Integer taskCategory;
    @Excel(name = "任务单类型", width = 15)
    private String taskCategoryName;
    private Integer urgent;
    private String urgentName;
    private Integer isChange;
    @Excel(name = "更正", orderNum = "1",  width = 8)
    private String isChangeName;
    @Excel(name = "发起人", orderNum = "9",  width = 20)
    private String submitterId;
    @Excel(name = "发起时间", orderNum = "10",  width = 20)
    private LocalDateTime submitTime;
    private Integer taskStatus;
    private String taskId;

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

    public Integer getDictTaskCategory() {
        if (this.dictTaskCategory == null) {
            this.dictTaskCategory = EmpTaskCategoryConverter.convertDictItemFromCategories(this);
        }
        return this.dictTaskCategory;
    }

    public void setDictTaskCategory(Integer dictTaskCategory) {
        this.dictTaskCategory = dictTaskCategory;
    }

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
        getDictTaskCategory();
        return DictUtil.getInstance().getTextByItemValueAndTypeValue(String.valueOf(this.dictTaskCategory), DictUtil.TYPE_VALUE_HF_LOCAL_TASK_CATEGORY, true);
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

    public String getUrgentName() {
        if (urgent != null && urgent == 1) {
            urgentName = "加急";
        }
        return urgentName;
    }

    public void setUrgentName(String urgentName) {
        this.urgentName = urgentName;
    }

    public Integer getIsChange() {
        return isChange;
    }

    public void setIsChange(Integer isChange) {
        this.isChange = isChange;
    }

    public String getIsChangeName() {
        if (isChange != null && isChange == 1) {
            isChangeName = "是";
        } else {
            isChangeName = "否";
        }
        return isChangeName;
    }

    public void setIsChangeName(String isChangeName) {
        this.isChangeName = isChangeName;
    }
}
