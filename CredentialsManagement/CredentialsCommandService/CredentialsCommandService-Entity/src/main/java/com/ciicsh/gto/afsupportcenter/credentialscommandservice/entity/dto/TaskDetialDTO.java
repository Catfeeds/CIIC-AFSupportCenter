package com.ciicsh.gto.afsupportcenter.credentialscommandservice.entity.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author: guwei
 * @Description:
 * @Date: Created in 15:42 2018/1/23
 */
public class TaskDetialDTO implements Serializable{

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
     * 材料ids
     */
    private String materialIds;
    /**
     * 公司材料
     */
    private String comp;
    /**
     * 婚姻状况
     */
    private String marryStatus;
    /**
     * 是否有随员
     */
    private String hasFollower;
    /**
     * 配偶及子女相关材料
     */
    private String familerMaterials;
    /**
     * 申请地址变更材料
     */
    private String applyAddrChange;
    /**
     * 随员类型
     */
    private String followerType;
    /**
     * 住所证明
     */
    private String addr;
    /**
     * 随员
     */
    private String follower;
    /**
     * 子女是否随迁
     */
    private String hasChildFollow;
    /**
     * 配偶是否随迁
     */
    private String hasSpouseFollow;
    /**
     * 婚育状况
     */
    private String married;
    /**
     * 职称材料
     */
    private String jobMaterials;
    /**
     * 是否为科创人才
     */
    private String hasGooder;
    /**
     * 教育经历材料
     */
    private String educate;
    /**
     * 随迁材料
     */
    private String followMaterials;
    /**
     *  不随迁材料
     */
    private String notFollowMaterials;
    /**
     * 证件类型
     */
    private String credentialsType;
    /**
     * 证件办理类型
     */
    private String credentialsDealType;
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
     * 收费金额
     */
    private BigDecimal chargeAmount;
    /**
     * 付款方式(1：台账、2：现金、3：转账、4：POS机)
     */
    private Integer payType;
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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "+8")
    private Date createdTime;
    /**
     * 最后更新时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "+8")
    private Date modifiedTime;
    /**
     * 创建者登录名
     */
    private String createdBy;
    /**
     * 修改者登录名
     */
    private String modifiedBy;

    public String getComp() {
        return comp;
    }

    public void setComp(String comp) {
        this.comp = comp;
    }

    public String getMarryStatus() {
        return marryStatus;
    }

    public void setMarryStatus(String marryStatus) {
        this.marryStatus = marryStatus;
    }

    public String getHasFollower() {
        return hasFollower;
    }

    public void setHasFollower(String hasFollower) {
        this.hasFollower = hasFollower;
    }

    public String getFamilerMaterials() {
        return familerMaterials;
    }

    public void setFamilerMaterials(String familerMaterials) {
        this.familerMaterials = familerMaterials;
    }

    public String getApplyAddrChange() {
        return applyAddrChange;
    }

    public void setApplyAddrChange(String applyAddrChange) {
        this.applyAddrChange = applyAddrChange;
    }

    public String getFollowerType() {
        return followerType;
    }

    public void setFollowerType(String followerType) {
        this.followerType = followerType;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getFollower() {
        return follower;
    }

    public void setFollower(String follower) {
        this.follower = follower;
    }

    public String getHasChildFollow() {
        return hasChildFollow;
    }

    public void setHasChildFollow(String hasChildFollow) {
        this.hasChildFollow = hasChildFollow;
    }

    public String getHasSpouseFollow() {
        return hasSpouseFollow;
    }

    public void setHasSpouseFollow(String hasSpouseFollow) {
        this.hasSpouseFollow = hasSpouseFollow;
    }

    public String getMarried() {
        return married;
    }

    public void setMarried(String married) {
        this.married = married;
    }

    public String getJobMaterials() {
        return jobMaterials;
    }

    public void setJobMaterials(String jobMaterials) {
        this.jobMaterials = jobMaterials;
    }

    public String getHasGooder() {
        return hasGooder;
    }

    public void setHasGooder(String hasGooder) {
        this.hasGooder = hasGooder;
    }

    public String getEducate() {
        return educate;
    }

    public void setEducate(String educate) {
        this.educate = educate;
    }

    public String getFollowMaterials() {
        return followMaterials;
    }

    public void setFollowMaterials(String followMaterials) {
        this.followMaterials = followMaterials;
    }

    public String getNotFollowMaterials() {
        return notFollowMaterials;
    }

    public void setNotFollowMaterials(String notFollowMaterials) {
        this.notFollowMaterials = notFollowMaterials;
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

    public String getMaterialIds() {
        return materialIds;
    }

    public void setMaterialIds(String materialIds) {
        this.materialIds = materialIds;
    }

    public String getCredentialsType() {
        return credentialsType;
    }

    public void setCredentialsType(String credentialsType) {
        this.credentialsType = credentialsType;
    }

    public String getCredentialsDealType() {
        return credentialsDealType;
    }

    public void setCredentialsDealType(String credentialsDealType) {
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

    @Override
    public String toString() {
        return "TaskDetialDTO{" +
            "taskId=" + taskId +
            ", employeeId='" + employeeId + '\'' +
            ", companyId='" + companyId + '\'' +
            ", materialIds='" + materialIds + '\'' +
            ", comp='" + comp + '\'' +
            ", marryStatus='" + marryStatus + '\'' +
            ", hasFollower='" + hasFollower + '\'' +
            ", familerMaterials='" + familerMaterials + '\'' +
            ", applyAddrChange='" + applyAddrChange + '\'' +
            ", followerType='" + followerType + '\'' +
            ", addr='" + addr + '\'' +
            ", follower='" + follower + '\'' +
            ", hasChildFollow='" + hasChildFollow + '\'' +
            ", hasSpouseFollow='" + hasSpouseFollow + '\'' +
            ", married='" + married + '\'' +
            ", jobMaterials='" + jobMaterials + '\'' +
            ", hasGooder='" + hasGooder + '\'' +
            ", educate='" + educate + '\'' +
            ", followMaterials='" + followMaterials + '\'' +
            ", notFollowMaterials='" + notFollowMaterials + '\'' +
            ", credentialsType='" + credentialsType + '\'' +
            ", credentialsDealType='" + credentialsDealType + '\'' +
            ", education='" + education + '\'' +
            ", materialBackTime=" + materialBackTime +
            ", callsTime=" + callsTime +
            ", applyTime=" + applyTime +
            ", liveAgeLimit=" + liveAgeLimit +
            ", shiftLetterSendTime=" + shiftLetterSendTime +
            ", talentBackTime=" + talentBackTime +
            ", talentBackReason='" + talentBackReason + '\'' +
            ", dealTime=" + dealTime +
            ", chargeTime=" + chargeTime +
            ", receiveFileTime=" + receiveFileTime +
            ", originalBackTime=" + originalBackTime +
            ", originalBackReason='" + originalBackReason + '\'' +
            ", integralBillPrintTime=" + integralBillPrintTime +
            ", chargeAmount=" + chargeAmount +
            ", payType=" + payType +
            ", permitCompanyName='" + permitCompanyName + '\'' +
            ", telephone='" + telephone + '\'' +
            ", remark='" + remark + '\'' +
            ", isActive=" + isActive +
            ", createdTime=" + createdTime +
            ", modifiedTime=" + modifiedTime +
            ", createdBy='" + createdBy + '\'' +
            ", modifiedBy='" + modifiedBy + '\'' +
            '}';
    }
}
