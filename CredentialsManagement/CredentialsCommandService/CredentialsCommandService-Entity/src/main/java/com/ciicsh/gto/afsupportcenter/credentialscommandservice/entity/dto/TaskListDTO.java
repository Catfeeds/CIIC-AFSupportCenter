package com.ciicsh.gto.afsupportcenter.credentialscommandservice.entity.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author: guwei
 * @Description:
 * @Date: Created in 14:25 2018/1/23
 */
public class TaskListDTO implements Serializable {

    /**
     * 主键：证件办理任务单id
     */
    private Long taskId;
    /**
     * 雇员id
     */
    private String employeeId;
    /**
     * 客户id
     */
    private String companyId;
    /**
     * 证件类型(1：积分办理、2：居住证B证、3：留学生落户、4：居转户、5：夫妻分局、6：人才引进)
     */
    private Integer credentialsType;
    /**
     * 证件类型UI
     */
    private String credentialsTypeN;
    /**
     * 证件办理类型(1：积分申请、2：积分确认、3：信息变更、4：随员、5：重置密码、6：新办、7：继办、8：随员、9：个人信息修改、10：挂失、11、补办)
     */
    private Integer credentialsDealType;
    /**
     * 证件办理类型UI
     */
    private String credentialsDealTypeN;
    private String basicProductId;
    private String productId;
    private BigDecimal money;
    /**
     * 学历
     */
    private String qualification;
    private String qualificationName;
    /**
     * 学位
     */
    private String degree;
    private String degreeName;
    /**
     * 学历认定时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "+8")
    private Date educationTime;
    /**
     * 学历认定
     */
    private String education;
    /**
     * 材料退回时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "+8")
    private Date materialBackTime;
    /**
     * 催交日期
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "+8")
    private Date callsTime;
    /**
     * 申报日期
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "+8")
    private Date applyTime;
    /**
     * 居住证年限
     */
    private Integer liveAgeLimit;
    /**
     * 调档函开出时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "+8")
    private Date shiftLetterSendTime;
    /**
     * 人才退回时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "+8")
    private Date talentBackTime;
    /**
     * 人才退回原因
     */
    private String talentBackReason;
    /**
     * 办理日期
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "+8")
    private Date dealTime;
    /**
     * 收费日期
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "+8")
    private Date chargeTime;
    /**
     * 到档时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "+8")
    private Date receiveFileTime;
    /**
     * 原件退回时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "+8")
    private Date originalBackTime;
    /**
     * 原件退回原因
     */
    private String originalBackReason;
    /**
     * 积分单打印日期
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "+8")
    private Date integralBillPrintTime;
    /**
     * 积分单通知日期
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "+8")
    private Date integralBillCallTime;
    /**
     * 雇员批复领取时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "+8")
    private Date empBackTime;
    /**
     * 收费金额
     */
    private BigDecimal chargeAmount;
    /**
     * 人数
     */
    private String peopleNum;
    /**
     * 付款方式(1：台账、2：现金、3：转账、4：POS机)
     */
    private String payType;
    /**
     * 办证公司名称
     */
    private String permitCompanyName;
    /**
     * 手机
     */
    private String telephone;
    /**
     * 备注
     */
    private String remark;
    /**
     * 是否可用
     */
    private Boolean isActive;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createdTime;
    /**
     * 创建者登录名
     */
    private String createdBy;

    public String getBasicProductId() {
        return basicProductId;
    }

    public void setBasicProductId(String basicProductId) {
        this.basicProductId = basicProductId;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public Integer getCredentialsType() {
        return credentialsType;
    }

    public void setCredentialsType(Integer credentialsType) {
        this.credentialsType = credentialsType;
    }

    public String getCredentialsTypeN() {
        return credentialsTypeN;
    }

    public void setCredentialsTypeN(String credentialsTypeN) {
        this.credentialsTypeN = credentialsTypeN;
    }

    public Integer getCredentialsDealType() {
        return credentialsDealType;
    }

    public void setCredentialsDealType(Integer credentialsDealType) {
        this.credentialsDealType = credentialsDealType;
    }

    public String getCredentialsDealTypeN() {
        return credentialsDealTypeN;
    }

    public void setCredentialsDealTypeN(String credentialsDealTypeN) {
        this.credentialsDealTypeN = credentialsDealTypeN;
    }

    public String getQualificationName() {
        return qualificationName;
    }

    public void setQualificationName(String qualificationName) {
        this.qualificationName = qualificationName;
    }

    public String getDegreeName() {
        return degreeName;
    }

    public void setDegreeName(String degreeName) {
        this.degreeName = degreeName;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public Date getMaterialBackTime() {
        return materialBackTime;
    }

    public void setMaterialBackTime(Date materialBackTime) {
        this.materialBackTime = materialBackTime;
    }

    public Date getCallsTime() {
        return callsTime;
    }

    public void setCallsTime(Date callsTime) {
        this.callsTime = callsTime;
    }

    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    public Integer getLiveAgeLimit() {
        return liveAgeLimit;
    }

    public void setLiveAgeLimit(Integer liveAgeLimit) {
        this.liveAgeLimit = liveAgeLimit;
    }

    public Date getShiftLetterSendTime() {
        return shiftLetterSendTime;
    }

    public void setShiftLetterSendTime(Date shiftLetterSendTime) {
        this.shiftLetterSendTime = shiftLetterSendTime;
    }

    public Date getTalentBackTime() {
        return talentBackTime;
    }

    public void setTalentBackTime(Date talentBackTime) {
        this.talentBackTime = talentBackTime;
    }

    public String getTalentBackReason() {
        return talentBackReason;
    }

    public void setTalentBackReason(String talentBackReason) {
        this.talentBackReason = talentBackReason;
    }

    public Date getDealTime() {
        return dealTime;
    }

    public void setDealTime(Date dealTime) {
        this.dealTime = dealTime;
    }

    public Date getChargeTime() {
        return chargeTime;
    }

    public void setChargeTime(Date chargeTime) {
        this.chargeTime = chargeTime;
    }

    public Date getReceiveFileTime() {
        return receiveFileTime;
    }

    public void setReceiveFileTime(Date receiveFileTime) {
        this.receiveFileTime = receiveFileTime;
    }

    public Date getOriginalBackTime() {
        return originalBackTime;
    }

    public void setOriginalBackTime(Date originalBackTime) {
        this.originalBackTime = originalBackTime;
    }

    public String getOriginalBackReason() {
        return originalBackReason;
    }

    public void setOriginalBackReason(String originalBackReason) {
        this.originalBackReason = originalBackReason;
    }

    public Date getIntegralBillPrintTime() {
        return integralBillPrintTime;
    }

    public void setIntegralBillPrintTime(Date integralBillPrintTime) {
        this.integralBillPrintTime = integralBillPrintTime;
    }

    public BigDecimal getChargeAmount() {
        return chargeAmount;
    }

    public void setChargeAmount(BigDecimal chargeAmount) {
        this.chargeAmount = chargeAmount;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getPermitCompanyName() {
        return permitCompanyName;
    }

    public void setPermitCompanyName(String permitCompanyName) {
        this.permitCompanyName = permitCompanyName;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public Date getEducationTime() {
        return educationTime;
    }

    public void setEducationTime(Date educationTime) {
        this.educationTime = educationTime;
    }

    public Date getIntegralBillCallTime() {
        return integralBillCallTime;
    }

    public void setIntegralBillCallTime(Date integralBillCallTime) {
        this.integralBillCallTime = integralBillCallTime;
    }

    public Date getEmpBackTime() {
        return empBackTime;
    }

    public void setEmpBackTime(Date empBackTime) {
        this.empBackTime = empBackTime;
    }

    public String getPeopleNum() {
        return peopleNum;
    }

    public void setPeopleNum(String peopleNum) {
        this.peopleNum = peopleNum;
    }
}
