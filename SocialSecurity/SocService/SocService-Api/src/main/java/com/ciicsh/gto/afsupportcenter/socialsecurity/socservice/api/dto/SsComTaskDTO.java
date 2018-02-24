package com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.api.dto;

import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.api.entity.SsComMaterial;

import java.util.Date;
import java.util.List;

public class SsComTaskDTO{
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
    /**
     * 是否可用
     */
    private Boolean isActive;
    /**
     * 创建时间
     */
    private Date createdTime;
    /**
     * 最后更新时间
     */
    private Date modifiedTime;
    /**
     * 创建者登录名
     */
    private String createdBy;
    /**
     * 修改者登录名
     */
    private String modifiedBy;
    //客户名称
    private String companyName;

    //任务发起时间段的 首段
    private Date submitTimeStart;

    //任务发起时间的 尾段
    private Date submitTimeEnd;

    //账户类型
    private String accountType;

    //结算区县
    private String regionValue;

    //客户经理
    private String customerManager;

    //材料清单
     private List<SsComMaterial> materialList;

    //账户
    private SsComAccountDTO ssComAccountDTO;

    //操作类型  1 开户 2 转移 3 变更 4 终止
    private String operatorType;

    //终止操作时 的终止日期
    private Date endDate;

    //转移日期
    private String transferDate;
    //变更的type
    private String changeContentValue;
//    //变更的支付方式
//    private String paymentWay;
    //行业类型
    private String belongsIndustry;
    //企业工伤比例
    private String companyWorkInjuryPercentage;
    //开始年月
//    private Date startMonth;

    //帐单接收方
//    private String billReceiver;
    //发出材料（前道传递）
    private String dispatchMaterial;

    /**
     *  判断是否是完成状态即 在任务单办理页面时候查询没有完成的任务  在完成tab查看已完成的
     *  如果为空则是在完成tab中查询，否则是在开户办理时查询信息（信息为任务状态!=3的）
     */

    private String isComplete;

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

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Date getSubmitTimeStart() {
        return submitTimeStart;
    }

    public void setSubmitTimeStart(Date submitTimeStart) {
        this.submitTimeStart = submitTimeStart;
    }

    public Date getSubmitTimeEnd() {
        return submitTimeEnd;
    }

    public void setSubmitTimeEnd(Date submitTimeEnd) {
        this.submitTimeEnd = submitTimeEnd;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getRegionValue() {
        return regionValue;
    }

    public void setRegionValue(String regionValue) {
        this.regionValue = regionValue;
    }

    public String getCustomerManager() {
        return customerManager;
    }

    public void setCustomerManager(String customerManager) {
        this.customerManager = customerManager;
    }

    public List<SsComMaterial> getMaterialList() {
        return materialList;
    }

    public void setMaterialList(List<SsComMaterial> materialList) {
        this.materialList = materialList;
    }

    public SsComAccountDTO getSsComAccountDTO() {
        return ssComAccountDTO;
    }

    public void setSsComAccountDTO(SsComAccountDTO ssComAccountDTO) {
        this.ssComAccountDTO = ssComAccountDTO;
    }

    public String getOperatorType() {
        return operatorType;
    }

    public void setOperatorType(String operatorType) {
        this.operatorType = operatorType;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getTransferDate() {
        return transferDate;
    }

    public void setTransferDate(String transferDate) {
        this.transferDate = transferDate;
    }

    public String getChangeContentValue() {
        return changeContentValue;
    }

    public void setChangeContentValue(String changeContentValue) {
        this.changeContentValue = changeContentValue;
    }

    public String getBelongsIndustry() {
        return belongsIndustry;
    }

    public void setBelongsIndustry(String belongsIndustry) {
        this.belongsIndustry = belongsIndustry;
    }

    public String getCompanyWorkInjuryPercentage() {
        return companyWorkInjuryPercentage;
    }

    public void setCompanyWorkInjuryPercentage(String companyWorkInjuryPercentage) {
        this.companyWorkInjuryPercentage = companyWorkInjuryPercentage;
    }

    public String getDispatchMaterial() {
        return dispatchMaterial;
    }

    public void setDispatchMaterial(String dispatchMaterial) {
        this.dispatchMaterial = dispatchMaterial;
    }

    public String getIsComplete() {
        return isComplete;
    }

    public void setIsComplete(String isComplete) {
        this.isComplete = isComplete;
    }
}
