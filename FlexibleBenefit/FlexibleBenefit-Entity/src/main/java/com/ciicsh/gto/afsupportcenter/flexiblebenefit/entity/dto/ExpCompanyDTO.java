package com.ciicsh.gto.afsupportcenter.flexiblebenefit.entity.dto;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;

import java.io.Serializable;

/**
 * @Author: guwei
 * @Description:
 * @Date: Created in 15:25 2018/2/28
 */
@ExcelTarget("companyForm")
public class ExpCompanyDTO implements Serializable {

    /**
     * 公司编号
     */
    @Excel(name = "公司编号")
    private String companyId;
    /**
     * 公司名称
     */
    @Excel(name = "公司名称")
    private String title;
    /**
     * 服务产品id
     */
    private String productId;
    /**
     * 基础产品id
     */
    private String basicProductId;
    /**
     * 服务产品名称
     */
    @Excel(name = "服务产品")
    private String productName;
    /**
     * 人数
     */
    @Excel(name = "人数")
    private Long count;
    /**
     * 客户经理
     */
    @Excel(name = "客户经理")
    private String manager;
    /**
     * 客户总监
     */
    @Excel(name = "客户总监")
    private String majordomo;
    /**
     * 公司联系人
     */
    @Excel(name = "公司联系人")
    private String linkmanName;
    /**
     * 手机
     */
    @Excel(name = "手机")
    private String mobile;
    /**
     * 电话
     */
    @Excel(name = "电话")
    private String telNum;
    /**
     * 地址
     */
    @Excel(name = "地址")
    private String address;
    /**
     * 邮编
     */
    @Excel(name = "邮编")
    private String postCode;
    /**
     * 电子邮箱
     */
    @Excel(name = "电子邮箱")
    private String email;
    /**
     * 传真号码
     */
    @Excel(name = "传真号码")
    private String telautogramNum;

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getMajordomo() {
        return majordomo;
    }

    public void setMajordomo(String majordomo) {
        this.majordomo = majordomo;
    }

    public String getLinkmanName() {
        return linkmanName;
    }

    public void setLinkmanName(String linkmanName) {
        this.linkmanName = linkmanName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getTelNum() {
        return telNum;
    }

    public void setTelNum(String telNum) {
        this.telNum = telNum;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelautogramNum() {
        return telautogramNum;
    }

    public void setTelautogramNum(String telautogramNum) {
        this.telautogramNum = telautogramNum;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getBasicProductId() {
        return basicProductId;
    }

    public void setBasicProductId(String basicProductId) {
        this.basicProductId = basicProductId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @Override
    public String toString() {
        return "ExpCompanyDTO{" +
            "companyId='" + companyId + '\'' +
            ", title='" + title + '\'' +
            ", productId='" + productId + '\'' +
            ", basicProductId='" + basicProductId + '\'' +
            ", productName='" + productName + '\'' +
            ", count=" + count +
            ", manager='" + manager + '\'' +
            ", majordomo='" + majordomo + '\'' +
            ", linkmanName='" + linkmanName + '\'' +
            ", mobile='" + mobile + '\'' +
            ", telNum='" + telNum + '\'' +
            ", address='" + address + '\'' +
            ", postCode='" + postCode + '\'' +
            ", email='" + email + '\'' +
            ", telautogramNum='" + telautogramNum + '\'' +
            '}';
    }
}
