package out.com.ciicsh.gto.afsupportcenter.healthmedical.po;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 客户基础表
 * </p>
 */
@TableName("sal_company")
public class Company extends Model<Company> {

    private static final long serialVersionUID = 1L;

    /**
     * 客户ID
     */
    @TableId("company_id")
    private String companyId;
    /**
     * 管理方ID
     */
    @TableField("management_id")
    private String managementId;
    /**
     * 公司名称
     */
    private String title;
    /**
     * 客户证件类型（1：三证  2：三证合一  3：其他）
     */
    @TableField("license_type")
    private Integer licenseType;
    /**
     * 营业执照编号
     */
    @TableField("license_code")
    private String licenseCode;
    /**
     * 营业执照生效开始时间
     */
    @TableField("license_start_time")
    private Date licenseStartTime;
    /**
     * 营业执照生效结束时间
     */
    @TableField("license_end_time")
    private Date licenseEndTime;
    /**
     * 组织机构编码
     */
    @TableField("organization_code")
    private String organizationCode;
    /**
     * 组织机构证件生效开始时间
     */
    @TableField("organization_start_time")
    private Date organizationStartTime;
    /**
     * 组织机构证件生效结束时间
     */
    @TableField("organization_end_time")
    private Date organizationEndTime;
    /**
     * 税务登记证号
     */
    @TableField("tax_registration_code")
    private String taxRegistrationCode;
    /**
     * 注册地址
     */
    @TableField("registered_address")
    private String registeredAddress;
    /**
     * 注册资金
     */
    @TableField("registered_capital")
    private String registeredCapital;
    /**
     * 法人
     */
    @TableField("legal_person")
    private String legalPerson;
    /**
     * 客户状态
     * 1-潜在
     * 2-审批通过
     * 3-正式
     * 4-冻结
     */
    private Integer status;
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
    private Date createdTime;
    /**
     * 最后修改者
     */
    @TableField("modified_by")
    private String modifiedBy;
    /**
     * 最后修改时间
     */
    @TableField("modified_time")
    private Date modifiedTime;


    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getManagementId() {
        return managementId;
    }

    public void setManagementId(String managementId) {
        this.managementId = managementId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getLicenseType() {
        return licenseType;
    }

    public void setLicenseType(Integer licenseType) {
        this.licenseType = licenseType;
    }

    public String getLicenseCode() {
        return licenseCode;
    }

    public void setLicenseCode(String licenseCode) {
        this.licenseCode = licenseCode;
    }

    public Date getLicenseStartTime() {
        return licenseStartTime;
    }

    public void setLicenseStartTime(Date licenseStartTime) {
        this.licenseStartTime = licenseStartTime;
    }

    public Date getLicenseEndTime() {
        return licenseEndTime;
    }

    public void setLicenseEndTime(Date licenseEndTime) {
        this.licenseEndTime = licenseEndTime;
    }

    public String getOrganizationCode() {
        return organizationCode;
    }

    public void setOrganizationCode(String organizationCode) {
        this.organizationCode = organizationCode;
    }

    public Date getOrganizationStartTime() {
        return organizationStartTime;
    }

    public void setOrganizationStartTime(Date organizationStartTime) {
        this.organizationStartTime = organizationStartTime;
    }

    public Date getOrganizationEndTime() {
        return organizationEndTime;
    }

    public void setOrganizationEndTime(Date organizationEndTime) {
        this.organizationEndTime = organizationEndTime;
    }

    public String getTaxRegistrationCode() {
        return taxRegistrationCode;
    }

    public void setTaxRegistrationCode(String taxRegistrationCode) {
        this.taxRegistrationCode = taxRegistrationCode;
    }

    public String getRegisteredAddress() {
        return registeredAddress;
    }

    public void setRegisteredAddress(String registeredAddress) {
        this.registeredAddress = registeredAddress;
    }

    public String getRegisteredCapital() {
        return registeredCapital;
    }

    public void setRegisteredCapital(String registeredCapital) {
        this.registeredCapital = registeredCapital;
    }

    public String getLegalPerson() {
        return legalPerson;
    }

    public void setLegalPerson(String legalPerson) {
        this.legalPerson = legalPerson;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Date getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    @Override
    protected Serializable pkVal() {
        return this.companyId;
    }

    @Override
    public String toString() {
        return "Company{" +
            ", companyId=" + companyId +
            ", managementId=" + managementId +
            ", title=" + title +
            ", licenseType=" + licenseType +
            ", licenseCode=" + licenseCode +
            ", licenseStartTime=" + licenseStartTime +
            ", licenseEndTime=" + licenseEndTime +
            ", organizationCode=" + organizationCode +
            ", organizationStartTime=" + organizationStartTime +
            ", organizationEndTime=" + organizationEndTime +
            ", taxRegistrationCode=" + taxRegistrationCode +
            ", registeredAddress=" + registeredAddress +
            ", registeredCapital=" + registeredCapital +
            ", legalPerson=" + legalPerson +
            ", status=" + status +
            ", isActive=" + isActive +
            ", createdBy=" + createdBy +
            ", createdTime=" + createdTime +
            ", modifiedBy=" + modifiedBy +
            ", modifiedTime=" + modifiedTime +
            "}";
    }
}
