package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dto;

import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.SsEmpTask;

import java.time.LocalDateTime;

/**
 * SsEmpTask DTO
 */
public class SsEmpTaskDTO extends SsEmpTask {
    // ohter
    // 操作类型，用于区分操作
    private Integer operatorType;
    // 任务类型
    private Integer[] taskCategories;
    // 任务发起开始时间
    private LocalDateTime beginSubmitTime;
    // 任务发起结束时间
    private LocalDateTime endSubmitTime;

    // 来源表 cmy_employee
    // 雇员姓名
    private String empName;
    // 雇员编号
    private String employeeId;
    // 雇员证件号
    private String idNum;

    // 来源表 cmy_company
    // 客户编号
    private String companyId;
    // 客户名称
    private String title;

    // 来源表 ss_com_account
    // 企业社保账户Id
    private Long comAccountId;
    // 养老金独立开户密码（使用U盾登陆的密码）
    private String ssPwd;
    // 结算区县(社保局所在上海地区)
    private String settlementArea;
    // 账户类型：1:中智大库 2中智外包 3独立户
    private Integer ssAccountType;
    // 参保户登记码（账号）
    private String ssAccount;

    // 来源表 ss_emp_archive
    // 人员属性
    private Integer empClassify;
    // 社保起缴月份
    private String startMonth;

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getIdNum() {
        return idNum;
    }

    public void setIdNum(String idNum) {
        this.idNum = idNum;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getComAccountId() {
        return comAccountId;
    }

    public void setComAccountId(Long comAccountId) {
        this.comAccountId = comAccountId;
    }

    public String getSsPwd() {
        return ssPwd;
    }

    public void setSsPwd(String ssPwd) {
        this.ssPwd = ssPwd;
    }

    public String getSettlementArea() {
        return settlementArea;
    }

    public void setSettlementArea(String settlementArea) {
        this.settlementArea = settlementArea;
    }

    public Integer getSsAccountType() {
        return ssAccountType;
    }

    public void setSsAccountType(Integer ssAccountType) {
        this.ssAccountType = ssAccountType;
    }

    public Integer getEmpClassify() {
        return empClassify;
    }

    public void setEmpClassify(Integer empClassify) {
        this.empClassify = empClassify;
    }

    public String getStartMonth() {
        return startMonth;
    }

    public void setStartMonth(String startMonth) {
        this.startMonth = startMonth;
    }

    public String getSsAccount() {
        return ssAccount;
    }

    public void setSsAccount(String ssAccount) {
        this.ssAccount = ssAccount;
    }

    public Integer getOperatorType() {
        return operatorType;
    }

    public void setOperatorType(Integer operatorType) {
        this.operatorType = operatorType;
    }

    public Integer[] getTaskCategories() {
        return taskCategories;
    }

    public void setTaskCategories(Integer[] taskCategories) {
        this.taskCategories = taskCategories;
    }

    public LocalDateTime getBeginSubmitTime() {
        return beginSubmitTime;
    }

    public void setBeginSubmitTime(LocalDateTime beginSubmitTime) {
        this.beginSubmitTime = beginSubmitTime;
    }

    public LocalDateTime getEndSubmitTime() {
        return endSubmitTime;
    }

    public void setEndSubmitTime(LocalDateTime endSubmitTime) {
        this.endSubmitTime = endSubmitTime;
    }
}
