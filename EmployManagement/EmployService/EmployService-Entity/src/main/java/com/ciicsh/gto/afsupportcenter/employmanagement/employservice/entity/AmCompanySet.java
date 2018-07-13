package com.ciicsh.gto.afsupportcenter.employmanagement.employservice.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 公司特殊情况设置表
 * </p>
 *
 * @author ${author}
 * @since 2018-04-25
 */
@TableName("am_company_set")
public class AmCompanySet implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	@TableId(value="company_set_id", type= IdType.AUTO)
	private Long companySetId;
    /**
     * 客户ID
     */
	@TableField("company_id")
	private String companyId;
    /**
     * 无材料用工
     */
	@TableField("company_special0")
	private Integer companySpecial0;
    /**
     * 提交材料复印件件无材料用工
     */
	@TableField("company_special1")
	private Integer companySpecial1;
    /**
     * 用工自办
     */
	@TableField("company_special2")
	private Integer companySpecial2;
    /**
     * 公司更名
     */
	@TableField("company_special3")
	private Integer companySpecial3;
    /**
     * 有盖章录用名册
     */
	@TableField("company_special4")
	private Integer companySpecial4;
    /**
     * 公司自开录用名册
     */
	@TableField("company_special5")
	private Integer companySpecial5;
    /**
     * 用工成功录用名册归还公司
     */
	@TableField("company_special6")
	private Integer companySpecial6;
    /**
     * 公司不同意名册盖章（电子章）
     */
	@TableField("company_special7")
	private Integer companySpecial7;
    /**
     * 具有档案保管资质
     */
	@TableField("company_special8")
	private Integer companySpecial8;
    /**
     * 公司自行保管档案
     */
	@TableField("company_special9")
	private Integer companySpecial9;
    /**
     * 用工不调档
     */
	@TableField("company_special10")
	private Integer companySpecial10;
    /**
     * 客服跟催档案
     */
	@TableField("company_special11")
	private Integer companySpecial11;
    /**
     * 公司自开退工单
     */
	@TableField("company_special12")
	private Integer companySpecial12;
    /**
     * 退工材料公司自行归档
     */
	@TableField("company_special13")
	private Integer companySpecial13;
    /**
     * 公司自留存根联
     */
	@TableField("company_special14")
	private Integer companySpecial14;
    /**
     * 退工自办
     */
	@TableField("company_special15")
	private Integer companySpecial15;
    /**
     * 上海人退工单
     */
	@TableField("company_special16")
	private Integer companySpecial16;
    /**
     * 外来从业人员退工单
     */
	@TableField("company_special17")
	private Integer companySpecial17;
    /**
     * 退工单人事章
     */
	@TableField("company_special18")
	private Integer companySpecial18;
    /**
     * 不寄退工单
     */
	@TableField("company_special19")
	private Integer companySpecial19;

    /**
     * 社保自办
     */
	@TableField("company_special21")
	private Integer companySpecial21;
    /**
     * 公司特殊情况
     */
	@TableField("company_special22")
	private Integer companySpecial22;
    /**
     * 备注
     */
	private String remark;
    /**
     * 用工公司特殊情况字符串
     */
	@TableField("employ_special")
	private String employSpecial;
    /**
     * 退工公司特殊情况字符串
     */
	@TableField("refuse_special")
	private String refuseSpecial;
    /**
     * 档案公司特殊情况字符串
     */
	@TableField("archive_special")
	private String archiveSpecial;
    /**
     * 社保公司特殊情况字符串
     */
	@TableField("social_special")
	private String socialSpecial;
    /**
     * 是否有特殊
     */
	private String special;
    /**
     * 是否邮寄退工单
     */
	@TableField("mail_continue")
	private Boolean mailContinue;
    /**
     * 邮寄退工单地址
     */
	@TableField("mail_adress")
	private String mailAdress;
    /**
     * 邮寄退工单收件人
     */
	private String recipient;
    /**
     * 邮寄退工单邮编
     */
	@TableField("post_code")
	private String postCode;
    /**
     * 邮寄退工单电话
     */
	private String phone;
    /**
     * 是否可用
     */
	@TableField("is_active")
	private Boolean isActive;
    /**
     * 创建者
     */
	@TableField("created_by")
	private String createdBy;
    /**
     * 创建时间
     */
	@TableField("created_time")
	private LocalDateTime createdTime;
    /**
     * 最后修改者
     */
	@TableField("modified_by")
	private String modifiedBy;
    /**
     * 最后修改时间
     */
	@TableField("modified_time")
	private LocalDateTime modifiedTime;


	public Long getCompanySetId() {
		return companySetId;
	}

	public void setCompanySetId(Long companySetId) {
		this.companySetId = companySetId;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public Integer getCompanySpecial0() {
		return companySpecial0;
	}

	public void setCompanySpecial0(Integer companySpecial0) {
		this.companySpecial0 = companySpecial0;
	}

	public Integer getCompanySpecial1() {
		return companySpecial1;
	}

	public void setCompanySpecial1(Integer companySpecial1) {
		this.companySpecial1 = companySpecial1;
	}

	public Integer getCompanySpecial2() {
		return companySpecial2;
	}

	public void setCompanySpecial2(Integer companySpecial2) {
		this.companySpecial2 = companySpecial2;
	}

	public Integer getCompanySpecial3() {
		return companySpecial3;
	}

	public void setCompanySpecial3(Integer companySpecial3) {
		this.companySpecial3 = companySpecial3;
	}

	public Integer getCompanySpecial4() {
		return companySpecial4;
	}

	public void setCompanySpecial4(Integer companySpecial4) {
		this.companySpecial4 = companySpecial4;
	}

	public Integer getCompanySpecial5() {
		return companySpecial5;
	}

	public void setCompanySpecial5(Integer companySpecial5) {
		this.companySpecial5 = companySpecial5;
	}

	public Integer getCompanySpecial6() {
		return companySpecial6;
	}

	public void setCompanySpecial6(Integer companySpecial6) {
		this.companySpecial6 = companySpecial6;
	}

	public Integer getCompanySpecial7() {
		return companySpecial7;
	}

	public void setCompanySpecial7(Integer companySpecial7) {
		this.companySpecial7 = companySpecial7;
	}

	public Integer getCompanySpecial8() {
		return companySpecial8;
	}

	public void setCompanySpecial8(Integer companySpecial8) {
		this.companySpecial8 = companySpecial8;
	}

	public Integer getCompanySpecial9() {
		return companySpecial9;
	}

	public void setCompanySpecial9(Integer companySpecial9) {
		this.companySpecial9 = companySpecial9;
	}

	public Integer getCompanySpecial10() {
		return companySpecial10;
	}

	public void setCompanySpecial10(Integer companySpecial10) {
		this.companySpecial10 = companySpecial10;
	}

	public Integer getCompanySpecial11() {
		return companySpecial11;
	}

	public void setCompanySpecial11(Integer companySpecial11) {
		this.companySpecial11 = companySpecial11;
	}

	public Integer getCompanySpecial12() {
		return companySpecial12;
	}

	public void setCompanySpecial12(Integer companySpecial12) {
		this.companySpecial12 = companySpecial12;
	}

	public Integer getCompanySpecial13() {
		return companySpecial13;
	}

	public void setCompanySpecial13(Integer companySpecial13) {
		this.companySpecial13 = companySpecial13;
	}

	public Integer getCompanySpecial14() {
		return companySpecial14;
	}

	public void setCompanySpecial14(Integer companySpecial14) {
		this.companySpecial14 = companySpecial14;
	}

	public Integer getCompanySpecial15() {
		return companySpecial15;
	}

	public void setCompanySpecial15(Integer companySpecial15) {
		this.companySpecial15 = companySpecial15;
	}

	public Integer getCompanySpecial16() {
		return companySpecial16;
	}

	public void setCompanySpecial16(Integer companySpecial16) {
		this.companySpecial16 = companySpecial16;
	}

	public Integer getCompanySpecial17() {
		return companySpecial17;
	}

	public void setCompanySpecial17(Integer companySpecial17) {
		this.companySpecial17 = companySpecial17;
	}

	public Integer getCompanySpecial18() {
		return companySpecial18;
	}

	public void setCompanySpecial18(Integer companySpecial18) {
		this.companySpecial18 = companySpecial18;
	}

	public Integer getCompanySpecial19() {
		return companySpecial19;
	}

	public void setCompanySpecial19(Integer companySpecial19) {
		this.companySpecial19 = companySpecial19;
	}

	public Integer getCompanySpecial21() {
		return companySpecial21;
	}

	public void setCompanySpecial21(Integer companySpecial21) {
		this.companySpecial21 = companySpecial21;
	}

	public Integer getCompanySpecial22() {
		return companySpecial22;
	}

	public void setCompanySpecial22(Integer companySpecial22) {
		this.companySpecial22 = companySpecial22;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getEmploySpecial() {
		return employSpecial;
	}

	public void setEmploySpecial(String employSpecial) {
		this.employSpecial = employSpecial;
	}

	public String getRefuseSpecial() {
		return refuseSpecial;
	}

	public void setRefuseSpecial(String refuseSpecial) {
		this.refuseSpecial = refuseSpecial;
	}

	public String getArchiveSpecial() {
		return archiveSpecial;
	}

	public void setArchiveSpecial(String archiveSpecial) {
		this.archiveSpecial = archiveSpecial;
	}

	public String getSocialSpecial() {
		return socialSpecial;
	}

	public void setSocialSpecial(String socialSpecial) {
		this.socialSpecial = socialSpecial;
	}

	public String getSpecial() {
		return special;
	}

	public void setSpecial(String special) {
		this.special = special;
	}

    public Boolean getMailContinue() {
        return mailContinue;
    }

    public void setMailContinue(Boolean mailContinue) {
        this.mailContinue = mailContinue;
    }

    public String getMailAdress() {
		return mailAdress;
	}

	public void setMailAdress(String mailAdress) {
		this.mailAdress = mailAdress;
	}

	public String getRecipient() {
		return recipient;
	}

	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Boolean getActive() {
		return isActive;
	}

	public void setActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public LocalDateTime getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(LocalDateTime createdTime) {
		this.createdTime = createdTime;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public LocalDateTime getModifiedTime() {
		return modifiedTime;
	}

	public void setModifiedTime(LocalDateTime modifiedTime) {
		this.modifiedTime = modifiedTime;
	}

	@Override
	public String toString() {
		return "AmCompanySet{" +
			"companySetId=" + companySetId +
			", companyId=" + companyId +
			", companySpecial0=" + companySpecial0 +
			", companySpecial1=" + companySpecial1 +
			", companySpecial2=" + companySpecial2 +
			", companySpecial3=" + companySpecial3 +
			", companySpecial4=" + companySpecial4 +
			", companySpecial5=" + companySpecial5 +
			", companySpecial6=" + companySpecial6 +
			", companySpecial7=" + companySpecial7 +
			", companySpecial8=" + companySpecial8 +
			", companySpecial9=" + companySpecial9 +
			", companySpecial10=" + companySpecial10 +
			", companySpecial11=" + companySpecial11 +
			", companySpecial12=" + companySpecial12 +
			", companySpecial13=" + companySpecial13 +
			", companySpecial14=" + companySpecial14 +
			", companySpecial15=" + companySpecial15 +
			", companySpecial16=" + companySpecial16 +
			", companySpecial17=" + companySpecial17 +
			", companySpecial18=" + companySpecial18 +
			", companySpecial19=" + companySpecial19 +
			", companySpecial21=" + companySpecial21 +
			", companySpecial22=" + companySpecial22 +
			", remark=" + remark +
			", employSpecial=" + employSpecial +
			", refuseSpecial=" + refuseSpecial +
			", archiveSpecial=" + archiveSpecial +
			", socialSpecial=" + socialSpecial +
			", special=" + special +
			", mailAdress=" + mailAdress +
			", recipient=" + recipient +
			", postCode=" + postCode +
			", phone=" + phone +
			", isActive=" + isActive +
			", createdBy=" + createdBy +
			", createdTime=" + createdTime +
			", modifiedBy=" + modifiedBy +
			", modifiedTime=" + modifiedTime +
			"}";
	}
}
