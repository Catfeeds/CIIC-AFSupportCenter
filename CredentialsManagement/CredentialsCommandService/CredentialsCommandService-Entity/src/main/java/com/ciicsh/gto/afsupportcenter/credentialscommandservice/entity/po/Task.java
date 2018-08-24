package com.ciicsh.gto.afsupportcenter.credentialscommandservice.entity.po;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author: guwei
 * @Description: 证件办理任务单
 * @Date: Created in 15:12 2018/1/15
 */
@TableName("cm_task")
public class Task extends Model<Task> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键：证件办理任务单id
     */
    @TableId(value="task_id", type= IdType.AUTO)
    private Long taskId;
    /**
     * 雇员id
     */
    @TableField("employee_id")
    private String employeeId;
    /**
     * 客户id
     */
    @TableField("company_id")
    private String companyId;
    /**
     * 证件类型(1：积分办理、2：居住证B证、3：留学生落户、4：居转户、5：夫妻分局、6：人才引进)
     */
    @TableField("credentials_type")
    private Integer credentialsType;
    /**
     * 证件办理类型(1：积分申请、2：积分确认、3：信息变更、4：随员、5：重置密码、6：新办、7：继办、8：随员、9：个人信息修改、10：挂失、11、补办)
     */
    @TableField("credentials_deal_type")
    private Integer credentialsDealType;
    /**
     * 学历
     */
    private Integer qualification;
    /**
     * 学位
     */
    private Integer degree;
    /**
     * 学历认定时间
     */
    @TableField("education_time")
    private Date educationTime;
    /**
     * 学历认定
     */
    private String education;
    /**
     * 材料退回时间
     */
    @TableField("material_back_time")
    private Date materialBackTime;
    /**
     * 催交日期
     */
    @TableField("calls_time")
    private Date callsTime;
    /**
     * 申报日期
     */
    @TableField("apply_time")
    private Date applyTime;
    /**
     * 居住证年限
     */
    @TableField("live_age_limit")
    private Integer liveAgeLimit;
    /**
     * 调档函开出时间
     */
    @TableField("shift_letter_send_time")
    private Date shiftLetterSendTime;
    /**
     * 人才退回时间
     */
    @TableField("talent_back_time")
    private Date talentBackTime;
    /**
     * 人才退回原因
     */
    @TableField("talent_back_reason")
    private String talentBackReason;
    /**
     * 办理日期
     */
    @TableField("deal_time")
    private Date dealTime;
    /**
     * 收费日期
     */
    @TableField("charge_time")
    private Date chargeTime;
    /**
     * 到档时间
     */
    @TableField("receive_file_time")
    private Date receiveFileTime;
    /**
     * 原件退回时间
     */
    @TableField("original_back_time")
    private Date originalBackTime;
    /**
     * 原件退回原因
     */
    @TableField("original_back_reason")
    private String originalBackReason;
    /**
     * 积分单打印日期
     */
    @TableField("integral_bill_print_time")
    private Date integralBillPrintTime;
    /**
     * 积分单通知日期
     */
    @TableField("integral_bill_call_time")
    private Date integralBillCallTime;
    /**
     * 雇员批复领取时间
     */
    @TableField("emp_back_time")
    private Date empBackTime;
    /**
     * 收费金额
     */
    @TableField("charge_amount")
    private BigDecimal chargeAmount;
    /**
     * 付款方式(1：台账、2：现金、3：转账、4：POS机)
     */
    @TableField("pay_type")
    private Integer payType;
    /**
     * 人数
     */
    @TableField("people_num")
    private String peopleNum;
    /**
     * 办证公司名称
     */
    @TableField("permit_company_name")
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
    @TableField("is_active")
    private Boolean isActive;
    /**
     * 创建时间
     */
    @TableField("created_time")
    private Date createdTime;
    /**
     * 最后更新时间
     */
    @TableField("modified_time")
    private Date modifiedTime;
    /**
     * 创建者登录名
     */
    @TableField("created_by")
    private String createdBy;
    /**
     * 修改者登录名
     */
    @TableField("modified_by")
    private String modifiedBy;


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

    public Integer getCredentialsDealType() {
        return credentialsDealType;
    }

    public void setCredentialsDealType(Integer credentialsDealType) {
        this.credentialsDealType = credentialsDealType;
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

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
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

    public void setActive(Boolean isActive) {
        this.isActive = isActive;
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

    public Integer getQualification() {
        return qualification;
    }

    public void setQualification(Integer qualification) {
        this.qualification = qualification;
    }

    public Integer getDegree() {
        return degree;
    }

    public void setDegree(Integer degree) {
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

    @Override
    protected Serializable pkVal() {
        return this.taskId;
    }

    @Override
    public String toString() {
        return "Task{" +
            "taskId=" + taskId +
            ", employeeId=" + employeeId +
            ", companyId=" + companyId +
            ", credentialsType=" + credentialsType +
            ", credentialsDealType=" + credentialsDealType +
            ", education=" + education +
            ", materialBackTime=" + materialBackTime +
            ", callsTime=" + callsTime +
            ", applyTime=" + applyTime +
            ", liveAgeLimit=" + liveAgeLimit +
            ", shiftLetterSendTime=" + shiftLetterSendTime +
            ", talentBackTime=" + talentBackTime +
            ", talentBackReason=" + talentBackReason +
            ", dealTime=" + dealTime +
            ", chargeTime=" + chargeTime +
            ", receiveFileTime=" + receiveFileTime +
            ", originalBackTime=" + originalBackTime +
            ", originalBackReason=" + originalBackReason +
            ", integralBillPrintTime=" + integralBillPrintTime +
            ", chargeAmount=" + chargeAmount +
            ", payType=" + payType +
            ", permitCompanyName=" + permitCompanyName +
            ", telephone=" + telephone +
            ", remark=" + remark +
            ", isActive=" + isActive +
            ", createdTime=" + createdTime +
            ", modifiedTime=" + modifiedTime +
            ", createdBy=" + createdBy +
            ", modifiedBy=" + modifiedBy +
            "}";
    }
}
