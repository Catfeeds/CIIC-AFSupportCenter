package com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice.api.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 礼品申请记录表
 * </p>
 *
 * @author xiweizhen
 * @since 2017-12-18
 */
public class GiftApplyDTO {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Integer giftRecordId;

    /**
     * 礼品主键
     */
    private Integer giftId;
    /**
     * 申请礼品数量，默认是1
     */
    private Integer applyNum;
    /**
     * 是否可用
     */
    private Boolean isActive;
    /**
     * 创建时间
     */
    private Date createTime;
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


    /**
     * 申请记录详细编号
     */
    private Integer applyRecordDetailId;
    /**
     * 赠送对象类型：
     * 1-公司
     * 2-联系人
     * 3-雇员
     * 4-其他
     */
    private Integer presentingObjectType;
    /**
     * 赠送对象主键ID
     */
    private String presentingObjectId;
    /**
     * 申请人类型（1-个人，2-公司，仅给赠送人--其他使用）
     */
    private Integer applicantType;
    /**
     * 申请人（仅给赠送人--其他使用）
     */
    private String applicant;
    /**
     * 申请人分机号（仅给赠送人--其他使用）
     */
    private String applicantExtension;
    /**
     * 公司名称
     */
    private String companyName;
    /**
     * 公司地址
     */
    private String companyAddress;
    /**
     * 公司电话
     */
    private Integer companyTelephone;
    /**
     * 公司传真
     */
    private Integer companyFax;
    /**
     * 联系人姓名
     */
    private String contactName;
    /**
     * 联系人部门
     */
    private String contactDeptName;
    /**
     * 联系人岗位
     */
    private String contactPosition;
    /**
     * 联系人出生日期
     */
    private Date contactBrithday;
    /**
     * 联系人手机号码
     */
    private String contactPhoneNum;
    /**
     * 联系人邮件地址
     */
    private String contactEmail;
    /**
     * 联系人家庭邮编
     */
    private Integer contactHomePostcode;
    /**
     * 联系人家庭电话
     */
    private Integer contactHomeTelephone;
    /**
     * 联系人家庭地址
     */
    private String contactHomeAddress;
    /**
     * 分机号
     */
    private Integer extensionNumber;
    /**
     * 申请事由
     */
    private String applyReason;
    /**
     * 审批状态
     */
    private Integer approvalStatus;
    /**
     * 发放状态（1-未处理，2-已发放，3-已批退）
     */
    private Integer sendStatus;
    /**
     * 发放时间
     */
    private Date sendTime;
    /**
     * 发放备注
     */
    private String sendRemark;

    /**
     * 申请记录编号
     */
    private Integer applyRecordId;
    /**
     * 申请人编号
     */
    private Integer applyerId;
    /**
     * 申请时间
     */
    private Date applyTime;
    /**
     * 申请类型：
     * 1-礼品
     * 2-市场活动
     */
    private Integer applyType;
    /**
     * 项目主题（待定）
     */
    private String projectTopics;

    private Integer id;
    /**
     * 礼品名称
     */
    private String giftName;
    /**
     * 礼品价格
     */
    private BigDecimal price;
    /**
     * 适用人群：男士适用0，女士适用1，男女通用2
     */
    private Integer rightPerson;
    /**
     * 礼品类型：票券0,办公用品1,生活用品,2，食品3，饰品4，数码周边5，儿童用品6
     */
    private Integer giftType;
    /**
     * 礼品颜色
     */
    private String color;
    /**
     * 礼品数量
     */
    private Integer number;
    /**
     * 限制申请的最大数量
     */
    private Integer applyMaxnum;
    /**
     * 图片地址
     */
    private String pictureUrl;
    /**
     * 礼品介绍
     */
    private String remarks;
    /**
     * 0，正常；1，已下架
     */
    private Integer status;

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

    public Integer getApplyRecordDetailId() {
        return applyRecordDetailId;
    }

    public void setApplyRecordDetailId(Integer applyRecordDetailId) {
        this.applyRecordDetailId = applyRecordDetailId;
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

    public String getSendRemark() {
        return sendRemark;
    }

    public void setSendRemark(String sendRemark) {
        this.sendRemark = sendRemark;
    }

    public String getPresentingObjectId() {
        return presentingObjectId;
    }

    public void setPresentingObjectId(String presentingObjectId) {
        this.presentingObjectId = presentingObjectId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGiftName() {
        return giftName;
    }

    public void setGiftName(String giftName) {
        this.giftName = giftName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getRightPerson() {
        return rightPerson;
    }

    public void setRightPerson(Integer rightPerson) {
        this.rightPerson = rightPerson;
    }

    public Integer getGiftType() {
        return giftType;
    }

    public void setGiftType(Integer giftType) {
        this.giftType = giftType;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getApplyMaxnum() {
        return applyMaxnum;
    }

    public void setApplyMaxnum(Integer applyMaxnum) {
        this.applyMaxnum = applyMaxnum;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
