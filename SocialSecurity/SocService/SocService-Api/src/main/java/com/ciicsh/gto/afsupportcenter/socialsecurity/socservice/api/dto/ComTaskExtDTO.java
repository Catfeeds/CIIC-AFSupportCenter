package com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.api.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * Created by houwanhua on 2018/3/1.
 */
public class ComTaskExtDTO {
    /**
     * 任务单编号
     */
    private Long comTaskId;
    /**
     * 多租户Id
     */
    private String customerId;
    /**
     * 外键,企业社保账户Id
     */
    private Long comAccountId;
    /**
     * 客户Id
     */
    private String companyId;
    /**
     * 引用：DicItem.DicItemValue 1:开户：2：转移 3：变更 4：终止
     */
    private String taskCategory;
    /**
     * 发起人要求任务完成截止日期
     */
    private Date expireDate;
    /**
     * 发起人id
     */
    private String submitterId;
    /**
     * 发起人姓名
     */
    private String submitterName;
    /**
     * 发起人当时所在部门Id
     */
    private String submitterDeptId;
    /**
     * 发起人当时所在部门名称
     */
    private String submitterDeptName;
    /**
     * 发起时间
     */
    private Date submitTime;
    /**
     * 发起人备注信息
     */
    private String submitRemark;
    /**
     * 任务单上前道系统传递过来的内容，Json格式
     */
    private String taskFormContent;
    /**
     * 对话记录, Json,
     格式：部门名称 姓名 时间 内容
     */
    private String chatHistory;
    /**
     * 动态扩展办理数据
     */
    private String dynamicExtend;
    /**
     * 任务单处理状态：0、初始（材料收缴） 1、受理中  2、送审中  3 、已完成  4、批退
     */
    private Integer taskStatus;
    /**
     * 任务处理人用户Id
     */
    private String handleUserId;
    private String handleUserName;
    /**
     * 受理日期
     */
    private Date startHandleDate;
    /**
     * 送审日期
     */
    private Date sendCheckDate;
    /**
     * 完成日期
     */
    private Date finishDate;
    /**
     * 办理备注
     */
    private String handleRemark;
    /**
     * 批退备注
     */
    private String rejectionRemark;
    /**
     * 业务接口ID
     */
    private String businessInterfaceId;
    private String taskId;
    /**
     * 参保户登记码（前道传递）
     */
    private String ssAccount;
    /**
     * 银行账号(牡丹卡号)（前道传递）
     */
    private String bankAccount;
    /**
     * 养老金账户公司名称（前道传递）
     */
    private String comAccountName;
    /**
     * 付款银行(一般情况是工商银行)（前道传递）
     */
    private String paymentBank;
    /**
     * 付款方式：.（前道传递）
     1-我司代付款
     2-客户自付
     3-我司垫付
     */
    private Integer paymentWay;
    /**
     * 社保帐单寄到哪里: 1 我司，2 客户公司（前道传递）
     */
    private Integer billReceiver;
    /**
     * 行业类别（前道传递）
     */
    private String industryCategory;
    /**
     * 开始月份（前道传递）
     */
    private String startMonth;
    /**
     * 客户交付社保费用给中智的截止日（前道传递）
     */
    private Integer expireDateFront;
    /**
     * 结算区县(社保局所在上海地区)（前道传递）
     */
    private String settlementArea;
    /**
     * 法人(前道传递)
     */
    private String legalPerson;
    /**
     * 联系地址(前道传递)
     */
    private String contactAddress;


    private String dispatchMaterial;
    private Integer majorCom;

    private String supplierId;
    /**
     * 账户类型：1:中智大库 2中智外包 3独立户
     */
    private Integer ssAccountType;

    /**
     * 银行账号ID
     */
    private Long bankAccountId;


    private String ssUsername;
    /**
     * 养老金独立开户密码（使用U盾登陆的密码）
     */
    private String ssPwd;
    /**
     * 初期余额
     */
    private BigDecimal initialBalance;
    /**
     * 初期欠费
     */
    private BigDecimal initialDebt;
    /**
     * 来源地:1-新开；2-AF转入；3-其他供应商转入
     */
    private Integer originPlace;
    /**
     * 来源地备注
     */
    private String originPlaceRemark;
    /**
     * 查询账号
     */
    private String queryAccount;
    /**
     * 交予方式:1-交客服  2-传真  3-邮寄
     */
    private String deliverWay;
    /**
     * 交予方式备注
     */
    private String deliverWayRemark;
    /**
     * 给凭证时间
     */
    private LocalDate provideCertificateTime;

    /**
     * 变更时间
     */
    private LocalDateTime changeTime;
    /**
     * 收到日期
     */
    private LocalDate receiveDate;
    /**
     * 转入日期
     */
    private LocalDate intoDate;
    /**
     * 终止日期
     */
    private LocalDate endDate;
    /**
     * 备注
     */
    private String remark;
    /**
     * 账户状态:0初始 1有效 2 终止
     */
    private Integer state;


    public Long getComTaskId() {
        return comTaskId;
    }

    public void setComTaskId(Long comTaskId) {
        this.comTaskId = comTaskId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Long getComAccountId() {
        return comAccountId;
    }

    public void setComAccountId(Long comAccountId) {
        this.comAccountId = comAccountId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getTaskCategory() {
        return taskCategory;
    }

    public void setTaskCategory(String taskCategory) {
        this.taskCategory = taskCategory;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public String getSubmitterId() {
        return submitterId;
    }

    public void setSubmitterId(String submitterId) {
        this.submitterId = submitterId;
    }

    public String getSubmitterName() {
        return submitterName;
    }

    public void setSubmitterName(String submitterName) {
        this.submitterName = submitterName;
    }

    public String getSubmitterDeptId() {
        return submitterDeptId;
    }

    public void setSubmitterDeptId(String submitterDeptId) {
        this.submitterDeptId = submitterDeptId;
    }

    public String getSubmitterDeptName() {
        return submitterDeptName;
    }

    public void setSubmitterDeptName(String submitterDeptName) {
        this.submitterDeptName = submitterDeptName;
    }

    public Date getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }

    public String getSubmitRemark() {
        return submitRemark;
    }

    public void setSubmitRemark(String submitRemark) {
        this.submitRemark = submitRemark;
    }

    public String getTaskFormContent() {
        return taskFormContent;
    }

    public void setTaskFormContent(String taskFormContent) {
        this.taskFormContent = taskFormContent;
    }

    public String getChatHistory() {
        return chatHistory;
    }

    public void setChatHistory(String chatHistory) {
        this.chatHistory = chatHistory;
    }

    public String getDynamicExtend() {
        return dynamicExtend;
    }

    public void setDynamicExtend(String dynamicExtend) {
        this.dynamicExtend = dynamicExtend;
    }

    public Integer getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(Integer taskStatus) {
        this.taskStatus = taskStatus;
    }

    public String getHandleUserId() {
        return handleUserId;
    }

    public void setHandleUserId(String handleUserId) {
        this.handleUserId = handleUserId;
    }

    public String getHandleUserName() {
        return handleUserName;
    }

    public void setHandleUserName(String handleUserName) {
        this.handleUserName = handleUserName;
    }

    public Date getStartHandleDate() {
        return startHandleDate;
    }

    public void setStartHandleDate(Date startHandleDate) {
        this.startHandleDate = startHandleDate;
    }

    public Date getSendCheckDate() {
        return sendCheckDate;
    }

    public void setSendCheckDate(Date sendCheckDate) {
        this.sendCheckDate = sendCheckDate;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    public String getHandleRemark() {
        return handleRemark;
    }

    public void setHandleRemark(String handleRemark) {
        this.handleRemark = handleRemark;
    }

    public String getRejectionRemark() {
        return rejectionRemark;
    }

    public void setRejectionRemark(String rejectionRemark) {
        this.rejectionRemark = rejectionRemark;
    }

    public String getBusinessInterfaceId() {
        return businessInterfaceId;
    }

    public void setBusinessInterfaceId(String businessInterfaceId) {
        this.businessInterfaceId = businessInterfaceId;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getSsAccount() {
        return ssAccount;
    }

    public void setSsAccount(String ssAccount) {
        this.ssAccount = ssAccount;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getComAccountName() {
        return comAccountName;
    }

    public void setComAccountName(String comAccountName) {
        this.comAccountName = comAccountName;
    }

    public String getPaymentBank() {
        return paymentBank;
    }

    public void setPaymentBank(String paymentBank) {
        this.paymentBank = paymentBank;
    }

    public Integer getPaymentWay() {
        return paymentWay;
    }

    public void setPaymentWay(Integer paymentWay) {
        this.paymentWay = paymentWay;
    }

    public Integer getBillReceiver() {
        return billReceiver;
    }

    public void setBillReceiver(Integer billReceiver) {
        this.billReceiver = billReceiver;
    }

    public String getIndustryCategory() {
        return industryCategory;
    }

    public void setIndustryCategory(String industryCategory) {
        this.industryCategory = industryCategory;
    }

    public String getStartMonth() {
        return startMonth;
    }

    public void setStartMonth(String startMonth) {
        this.startMonth = startMonth;
    }

    public Integer getExpireDateFront() {
        return expireDateFront;
    }

    public void setExpireDateFront(Integer expireDateFront) {
        this.expireDateFront = expireDateFront;
    }

    public String getSettlementArea() {
        return settlementArea;
    }

    public void setSettlementArea(String settlementArea) {
        this.settlementArea = settlementArea;
    }

    public String getLegalPerson() {
        return legalPerson;
    }

    public void setLegalPerson(String legalPerson) {
        this.legalPerson = legalPerson;
    }

    public String getContactAddress() {
        return contactAddress;
    }

    public void setContactAddress(String contactAddress) {
        this.contactAddress = contactAddress;
    }

    public String getDispatchMaterial() {
        return dispatchMaterial;
    }

    public void setDispatchMaterial(String dispatchMaterial) {
        this.dispatchMaterial = dispatchMaterial;
    }

    public Integer getMajorCom() {
        return majorCom;
    }

    public void setMajorCom(Integer majorCom) {
        this.majorCom = majorCom;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public Integer getSsAccountType() {
        return ssAccountType;
    }

    public void setSsAccountType(Integer ssAccountType) {
        this.ssAccountType = ssAccountType;
    }

    public Long getBankAccountId() {
        return bankAccountId;
    }

    public void setBankAccountId(Long bankAccountId) {
        this.bankAccountId = bankAccountId;
    }

    public String getSsUsername() {
        return ssUsername;
    }

    public void setSsUsername(String ssUsername) {
        this.ssUsername = ssUsername;
    }

    public String getSsPwd() {
        return ssPwd;
    }

    public void setSsPwd(String ssPwd) {
        this.ssPwd = ssPwd;
    }

    public BigDecimal getInitialBalance() {
        return initialBalance;
    }

    public void setInitialBalance(BigDecimal initialBalance) {
        this.initialBalance = initialBalance;
    }

    public BigDecimal getInitialDebt() {
        return initialDebt;
    }

    public void setInitialDebt(BigDecimal initialDebt) {
        this.initialDebt = initialDebt;
    }

    public Integer getOriginPlace() {
        return originPlace;
    }

    public void setOriginPlace(Integer originPlace) {
        this.originPlace = originPlace;
    }

    public String getOriginPlaceRemark() {
        return originPlaceRemark;
    }

    public void setOriginPlaceRemark(String originPlaceRemark) {
        this.originPlaceRemark = originPlaceRemark;
    }

    public String getQueryAccount() {
        return queryAccount;
    }

    public void setQueryAccount(String queryAccount) {
        this.queryAccount = queryAccount;
    }

    public String getDeliverWay() {
        return deliverWay;
    }

    public void setDeliverWay(String deliverWay) {
        this.deliverWay = deliverWay;
    }

    public String getDeliverWayRemark() {
        return deliverWayRemark;
    }

    public void setDeliverWayRemark(String deliverWayRemark) {
        this.deliverWayRemark = deliverWayRemark;
    }

    public LocalDate getProvideCertificateTime() {
        return provideCertificateTime;
    }

    public void setProvideCertificateTime(LocalDate provideCertificateTime) {
        this.provideCertificateTime = provideCertificateTime;
    }

    public LocalDateTime getChangeTime() {
        return changeTime;
    }

    public void setChangeTime(LocalDateTime changeTime) {
        this.changeTime = changeTime;
    }

    public LocalDate getReceiveDate() {
        return receiveDate;
    }

    public void setReceiveDate(LocalDate receiveDate) {
        this.receiveDate = receiveDate;
    }

    public LocalDate getIntoDate() {
        return intoDate;
    }

    public void setIntoDate(LocalDate intoDate) {
        this.intoDate = intoDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}