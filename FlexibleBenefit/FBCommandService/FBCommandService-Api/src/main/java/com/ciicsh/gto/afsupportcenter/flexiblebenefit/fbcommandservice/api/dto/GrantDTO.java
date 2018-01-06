package com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice.api.dto;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;

import java.util.Date;

public class GrantDTO {
    /**
     * 申请记录详细编号
     */
    @TableId(value = "apply_record_detail_id", type = IdType.AUTO)
    private Integer applyRecordDetailId;
    /**
     * 申请记录编号
     */
    @TableField("apply_record_id")
    private Integer applyRecordId;
    /**
     * 赠送对象类型：
     * 0-管理方
     * 1-客户
     * 2-联系人
     * 3-雇员
     * 4-其他
     */
    @TableField("presenting_object_type")
    private Integer presentingObjectType;
    /**
     * 赠送对象主键ID
     */
    @TableField("presenting_object_id")
    private String presentingObjectId;
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
    private String contactPhoneNum;
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
     * 审批步骤主键
     */
    @TableId(value = "step_id", type = IdType.AUTO)
    private Integer stepId;
    /**
     * 审批人编号（sm_user_info中的user_id）
     */
    @TableField("approver_id")
    private String approverId;
    /**
     * 审批人姓名
     */
    @TableField("approve_name")
    private String approveName;
    /**
     * 审批时间
     */
    @TableField("approve_time")
    private Date approveTime;
    /**
     * 审批动作：
     * 1-同意
     * 2-不同意
     */
    @TableField("approve_action")
    private Integer approveAction;
    /**
     * 审批备注
     */
    @TableField("approve_remark")
    private String approveRemark;

    public Integer getApplyRecordDetailId() {
        return applyRecordDetailId;
    }

    public void setApplyRecordDetailId(Integer applyRecordDetailId) {
        this.applyRecordDetailId = applyRecordDetailId;
    }

    public Integer getApplyRecordId() {
        return applyRecordId;
    }

    public void setApplyRecordId(Integer applyRecordId) {
        this.applyRecordId = applyRecordId;
    }

    public Integer getPresentingObjectType() {
        return presentingObjectType;
    }

    public void setPresentingObjectType(Integer presentingObjectType) {
        this.presentingObjectType = presentingObjectType;
    }

    public String getPresentingObjectId() {
        return presentingObjectId;
    }

    public void setPresentingObjectId(String presentingObjectId) {
        this.presentingObjectId = presentingObjectId;
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

    public String getContactPhoneNum() {
        return contactPhoneNum;
    }

    public void setContactPhoneNum(String contactPhoneNum) {
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

    public String getSendRemark() {
        return sendRemark;
    }

    public void setSendRemark(String sendRemark) {
        this.sendRemark = sendRemark;
    }

    public Integer getStepId() {
        return stepId;
    }

    public void setStepId(Integer stepId) {
        this.stepId = stepId;
    }

    public String getApproverId() {
        return approverId;
    }

    public void setApproverId(String approverId) {
        this.approverId = approverId;
    }

    public String getApproveName() {
        return approveName;
    }

    public void setApproveName(String approveName) {
        this.approveName = approveName;
    }

    public Date getApproveTime() {
        return approveTime;
    }

    public void setApproveTime(Date approveTime) {
        this.approveTime = approveTime;
    }

    public Integer getApproveAction() {
        return approveAction;
    }

    public void setApproveAction(Integer approveAction) {
        this.approveAction = approveAction;
    }

    public String getApproveRemark() {
        return approveRemark;
    }

    public void setApproveRemark(String approveRemark) {
        this.approveRemark = approveRemark;
    }

    @Override
    public String toString() {
        return "GrantDTO{" +
            "applyRecordDetailId=" + applyRecordDetailId +
            ", applyRecordId=" + applyRecordId +
            ", presentingObjectType=" + presentingObjectType +
            ", presentingObjectId='" + presentingObjectId + '\'' +
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
            ", contactPhoneNum='" + contactPhoneNum + '\'' +
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
            ", stepId=" + stepId +
            ", approverId='" + approverId + '\'' +
            ", approveName='" + approveName + '\'' +
            ", approveTime=" + approveTime +
            ", approveAction=" + approveAction +
            ", approveRemark='" + approveRemark + '\'' +
            '}';
    }
}
