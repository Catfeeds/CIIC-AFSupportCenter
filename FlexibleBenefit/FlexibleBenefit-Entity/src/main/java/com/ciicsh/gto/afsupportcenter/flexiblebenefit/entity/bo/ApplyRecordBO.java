package com.ciicsh.gto.afsupportcenter.flexiblebenefit.entity.bo;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;

import java.util.Date;

/**
 * <p>
 * 申请记录表
 * </p>
 *
 * @author xiweizhen
 * @since 2017-12-18
 */
public class ApplyRecordBO {

    /**
     * 申请记录编号
     */
    private Integer applyRecordId;
    /**
     * 申请人编号
     */
    @TableField("applyer_id")
    private Integer applyerId;
    /**
     * 申请时间
     */
    @TableField("apply_time")
    private Date applyTime;
    /**
     * 申请类型：
     * 1-礼品
     * 2-市场活动
     */
    @TableField("apply_type")
    private Integer applyType;
    /**
     * 项目主题（待定）
     */
    @TableField("project_topics")
    private String projectTopics;
    /**
     * 审批状态（0-审批中，1-同意，2-不同意，3-部分同意）
     */
    @TableField("record_approval_reason")
    private String recordApprovalReason;
    /**
     * 是否可用
     */
    @TableField("is_active")
    private Boolean isActive;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;
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

    /**
     * 赠送对象类型：
     * 0-管理方
     * 1-公司
     * 2-联系人
     * 3-雇员
     * 4-其他
     */
    @TableField("presenting_object_type")
    private Integer presentingObjectType;
    /**
     * 申请人类型（1-个人，2-公司，仅给赠送人--其他使用）
     */
    @TableField("applicant_type")
    private Integer applicantType;
    /**
     * 申请人（仅给赠送人--其他使用）
     */
    private String applicant;
    /**
     * 申请人分机号（仅给赠送人--其他使用）
     */
    @TableField("applicant_extension")
    private String applicantExtension;
    /**
     * 公司名称
     */
    @TableField("company_name")
    private String companyName;
    /**
     * 公司地址
     */
    @TableField("company_address")
    private String companyAddress;
    /**
     * 公司电话
     */
    @TableField("company_telephone")
    private Integer companyTelephone;
    /**
     * 公司传真
     */
    @TableField("company_fax")
    private Integer companyFax;
    /**
     * 联系人姓名
     */
    @TableField("contact_name")
    private String contactName;
    /**
     * 联系人部门
     */
    @TableField("contact_dept_name")
    private String contactDeptName;
    /**
     * 联系人岗位
     */
    @TableField("contact_position")
    private String contactPosition;
    /**
     * 联系人出生日期
     */
    @TableField("contact_brithday")
    private Date contactBrithday;
    /**
     * 联系人手机号码
     */
    @TableField("contact_phone_num")
    private Integer contactPhoneNum;
    /**
     * 联系人邮件地址
     */
    @TableField("contact_email")
    private String contactEmail;
    /**
     * 联系人家庭邮编
     */
    @TableField("contact_home_postcode")
    private Integer contactHomePostcode;
    /**
     * 联系人家庭电话
     */
    @TableField("contact_home_telephone")
    private Integer contactHomeTelephone;
    /**
     * 联系人家庭地址
     */
    @TableField("contact_home_address")
    private String contactHomeAddress;
    /**
     * 分机号
     */
    @TableField("extension_number")
    private Integer extensionNumber;
    /**
     * 申请事由
     */
    @TableField("apply_reason")
    private String applyReason;
    /**
     * 审批状态
     */
    @TableField("approval_status")
    private Integer approvalStatus;
    /**
     * 发放状态（1-未处理，2-已发放，3-已批退）
     */
    @TableField("send_status")
    private Integer sendStatus;
    /**
     * 发放时间
     */
    @TableField("send_time")
    private Date sendTime;
    /**
     * 发放备注
     */
    @TableField("send_remark")
    private String sendRemark;
    /**
     * 主键
     */
    @TableId(value = "gift_record_id", type = IdType.AUTO)
    private Integer giftRecordId;
    /**
     * 礼品主键
     */
    @TableField("gift_id")
    private Integer giftId;

    /**
     * 申请礼品数量，默认是1
     */
    @TableField("apply_num")
    private Integer applyNum;

    /**
     * 赠送市场活动编号
     */
    @TableId(value = "markert_activity_record_id", type = IdType.AUTO)
    private Integer markertActivityRecordId;
    /**
     * 活动申请表主键
     */
    @TableField("apply_record_detail_id")
    private Integer applyRecordDetailId;
    @TableField("activity_id")
    private Integer activityId;
    /**
     * 申请数量
     */
    private Integer number;
    /**
     * 礼品形式
     */
    @TableField("gift_form")
    private Integer giftForm;
    /**
     * 派送方式
     */
    @TableField("send_way")
    private Integer sendWay;
    /**
     * 派送地址
     */
    @TableField("delivery_address")
    private String deliveryAddress;

    public Integer getApplyRecordId() {
        return applyRecordId;
    }

    public void setApplyRecordId(Integer applyRecordId) {
        this.applyRecordId = applyRecordId;
    }

    public Integer getApplyerId() {
        return applyerId;
    }

    public void setApplyerId(Integer applyerId) {
        this.applyerId = applyerId;
    }

    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    public Integer getApplyType() {
        return applyType;
    }

    public void setApplyType(Integer applyType) {
        this.applyType = applyType;
    }

    public String getProjectTopics() {
        return projectTopics;
    }

    public void setProjectTopics(String projectTopics) {
        this.projectTopics = projectTopics;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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

    public Integer getPresentingObjectType() {
        return presentingObjectType;
    }

    public void setPresentingObjectType(Integer presentingObjectType) {
        this.presentingObjectType = presentingObjectType;
    }

    public Integer getApplicantType() {
        return applicantType;
    }

    public void setApplicantType(Integer applicantType) {
        this.applicantType = applicantType;
    }

    public String getApplicant() {
        return applicant;
    }

    public void setApplicant(String applicant) {
        this.applicant = applicant;
    }

    public String getApplicantExtension() {
        return applicantExtension;
    }

    public void setApplicantExtension(String applicantExtension) {
        this.applicantExtension = applicantExtension;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public Integer getCompanyTelephone() {
        return companyTelephone;
    }

    public void setCompanyTelephone(Integer companyTelephone) {
        this.companyTelephone = companyTelephone;
    }

    public Integer getCompanyFax() {
        return companyFax;
    }

    public void setCompanyFax(Integer companyFax) {
        this.companyFax = companyFax;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactDeptName() {
        return contactDeptName;
    }

    public void setContactDeptName(String contactDeptName) {
        this.contactDeptName = contactDeptName;
    }

    public String getContactPosition() {
        return contactPosition;
    }

    public void setContactPosition(String contactPosition) {
        this.contactPosition = contactPosition;
    }

    public Date getContactBrithday() {
        return contactBrithday;
    }

    public void setContactBrithday(Date contactBrithday) {
        this.contactBrithday = contactBrithday;
    }

    public Integer getContactPhoneNum() {
        return contactPhoneNum;
    }

    public void setContactPhoneNum(Integer contactPhoneNum) {
        this.contactPhoneNum = contactPhoneNum;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public Integer getContactHomePostcode() {
        return contactHomePostcode;
    }

    public void setContactHomePostcode(Integer contactHomePostcode) {
        this.contactHomePostcode = contactHomePostcode;
    }

    public Integer getContactHomeTelephone() {
        return contactHomeTelephone;
    }

    public void setContactHomeTelephone(Integer contactHomeTelephone) {
        this.contactHomeTelephone = contactHomeTelephone;
    }

    public String getContactHomeAddress() {
        return contactHomeAddress;
    }

    public void setContactHomeAddress(String contactHomeAddress) {
        this.contactHomeAddress = contactHomeAddress;
    }

    public Integer getExtensionNumber() {
        return extensionNumber;
    }

    public void setExtensionNumber(Integer extensionNumber) {
        this.extensionNumber = extensionNumber;
    }

    public String getApplyReason() {
        return applyReason;
    }

    public void setApplyReason(String applyReason) {
        this.applyReason = applyReason;
    }

    public Integer getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(Integer approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public Integer getSendStatus() {
        return sendStatus;
    }

    public void setSendStatus(Integer sendStatus) {
        this.sendStatus = sendStatus;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public Integer getGiftRecordId() {
        return giftRecordId;
    }

    public void setGiftRecordId(Integer giftRecordId) {
        this.giftRecordId = giftRecordId;
    }

    public Integer getGiftId() {
        return giftId;
    }

    public void setGiftId(Integer giftId) {
        this.giftId = giftId;
    }

    public Integer getApplyNum() {
        return applyNum;
    }

    public void setApplyNum(Integer applyNum) {
        this.applyNum = applyNum;
    }

    public Integer getMarkertActivityRecordId() {
        return markertActivityRecordId;
    }

    public void setMarkertActivityRecordId(Integer markertActivityRecordId) {
        this.markertActivityRecordId = markertActivityRecordId;
    }

    public Integer getApplyRecordDetailId() {
        return applyRecordDetailId;
    }

    public void setApplyRecordDetailId(Integer applyRecordDetailId) {
        this.applyRecordDetailId = applyRecordDetailId;
    }

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getGiftForm() {
        return giftForm;
    }

    public void setGiftForm(Integer giftForm) {
        this.giftForm = giftForm;
    }

    public Integer getSendWay() {
        return sendWay;
    }

    public void setSendWay(Integer sendWay) {
        this.sendWay = sendWay;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getSendRemark() {
        return sendRemark;
    }

    public void setSendRemark(String sendRemark) {
        this.sendRemark = sendRemark;
    }

    public String getRecordApprovalReason() {
        return recordApprovalReason;
    }

    public void setRecordApprovalReason(String recordApprovalReason) {
        this.recordApprovalReason = recordApprovalReason;
    }

    @Override
    public String toString() {
        return "ApplyRecordBO{" +
            "applyRecordId=" + applyRecordId +
            ", applyerId=" + applyerId +
            ", applyTime=" + applyTime +
            ", applyType=" + applyType +
            ", projectTopics='" + projectTopics + '\'' +
            ", recordApprovalReason='" + recordApprovalReason + '\'' +
            ", isActive=" + isActive +
            ", createTime=" + createTime +
            ", modifiedTime=" + modifiedTime +
            ", createdBy='" + createdBy + '\'' +
            ", modifiedBy='" + modifiedBy + '\'' +
            ", presentingObjectType=" + presentingObjectType +
            ", applicantType=" + applicantType +
            ", applicant='" + applicant + '\'' +
            ", applicantExtension='" + applicantExtension + '\'' +
            ", companyName='" + companyName + '\'' +
            ", companyAddress='" + companyAddress + '\'' +
            ", companyTelephone=" + companyTelephone +
            ", companyFax=" + companyFax +
            ", contactName='" + contactName + '\'' +
            ", contactDeptName='" + contactDeptName + '\'' +
            ", contactPosition='" + contactPosition + '\'' +
            ", contactBrithday=" + contactBrithday +
            ", contactPhoneNum=" + contactPhoneNum +
            ", contactEmail='" + contactEmail + '\'' +
            ", contactHomePostcode=" + contactHomePostcode +
            ", contactHomeTelephone=" + contactHomeTelephone +
            ", contactHomeAddress='" + contactHomeAddress + '\'' +
            ", extensionNumber=" + extensionNumber +
            ", applyReason='" + applyReason + '\'' +
            ", approvalStatus=" + approvalStatus +
            ", sendStatus=" + sendStatus +
            ", sendTime=" + sendTime +
            ", sendRemark='" + sendRemark + '\'' +
            ", giftRecordId=" + giftRecordId +
            ", giftId=" + giftId +
            ", applyNum=" + applyNum +
            ", markertActivityRecordId=" + markertActivityRecordId +
            ", applyRecordDetailId=" + applyRecordDetailId +
            ", activityId=" + activityId +
            ", number=" + number +
            ", giftForm=" + giftForm +
            ", sendWay=" + sendWay +
            ", deliveryAddress='" + deliveryAddress + '\'' +
            '}';
    }
}
