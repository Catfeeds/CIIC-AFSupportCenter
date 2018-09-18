package com.ciicsh.gto.afsupportcenter.employmanagement.employservice.dto;

import java.util.List;

public class AmEmpDispatchExportPageDTO {


    // 上级部门主管
    private String superiorDepartment;

    // 单位名称
    private String companyName;

    // 单位性质
    private String companyType;

    // 组织机构代码
    private String organizationCode;

    // 单位地址
    private String companyAddress;

    // 邮政编码
    private String postalCode;

    // 行业类别
    private String industryCategory;

    // 隶属关系
    private String membership;

    // 单位联系人
    private String linkman;

    // 联系电话
    private String linkPhone;

    // 操作时间
    private String createdTime;

    // 操作人
    private String createdBy;

    /**
     * 公司社保登记码
     */
    private String ssAccount;

    /**
     * 结算区县
     */
    private String settlementArea;

    // 循环表格
    private List<AmEmpDispatchExportDTO> list;

    public String getSettlementArea() {
        return settlementArea;
    }

    public void setSettlementArea(String settlementArea) {
        this.settlementArea = settlementArea;
    }

    public String getSsAccount() {
        return ssAccount;
    }

    public void setSsAccount(String ssAccount) {
        this.ssAccount = ssAccount;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getSuperiorDepartment() {
        return superiorDepartment;
    }

    public void setSuperiorDepartment(String superiorDepartment) {
        this.superiorDepartment = superiorDepartment;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyType() {
        return companyType;
    }

    public void setCompanyType(String companyType) {
        this.companyType = companyType;
    }

    public String getOrganizationCode() {
        return organizationCode;
    }

    public void setOrganizationCode(String organizationCode) {
        this.organizationCode = organizationCode;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getIndustryCategory() {
        return industryCategory;
    }

    public void setIndustryCategory(String industryCategory) {
        this.industryCategory = industryCategory;
    }

    public String getMembership() {
        return membership;
    }

    public void setMembership(String membership) {
        this.membership = membership;
    }

    public String getLinkman() {
        return linkman;
    }

    public void setLinkman(String linkman) {
        this.linkman = linkman;
    }

    public String getLinkPhone() {
        return linkPhone;
    }

    public void setLinkPhone(String linkPhone) {
        this.linkPhone = linkPhone;
    }

    public List<AmEmpDispatchExportDTO> getList() {
        return list;
    }

    public void setList(List<AmEmpDispatchExportDTO> list) {
        this.list = list;
    }
}
