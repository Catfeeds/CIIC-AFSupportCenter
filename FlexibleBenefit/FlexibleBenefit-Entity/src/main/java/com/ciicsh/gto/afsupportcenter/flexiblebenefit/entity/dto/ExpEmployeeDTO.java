package com.ciicsh.gto.afsupportcenter.flexiblebenefit.entity.dto;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;

import java.io.Serializable;

/**
 * @Author: guwei
 * @Description:
 * @Date: Created in 15:59 2018/3/2
 */
@ExcelTarget("employeeForm")
public class ExpEmployeeDTO implements Serializable{

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
     * 公司联系人
     */
    @Excel(name = "公司联系人")
    private String linkmanName;
    /**
     * 公司电话
     */
    @Excel(name = "电话")
    private String companyTelNum;
    /**
     * 公司地址
     */
    @Excel(name = "公司地址")
    private String companyAddr;
    /**
     * 邮编
     */
    @Excel(name = "邮编")
    private String companyZip;
    /**
     * 所在城市
     */
    @Excel(name = "城市")
    private String city;
    /**
     * 首次进入中智时间
     */
    @Excel(name = "首次进入中智时间", format = "yyyy-MM-dd")
    private String firstInDate;
    /**
     * 雇员姓名
     */
    @Excel(name = "雇员姓名")
    private String employeeName;
    /**
     * 雇员编号
     */
    @Excel(name = "雇员编号")
    private String employeeId;
    /**
     * 雇员性别
     */
    @Excel(name = "雇员性别", replace = {"男_0", "女_1"})
    private Integer gender;
    /**
     * 雇员手机
     */
    @Excel(name = "雇员手机号码")
    private String mobile;

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

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public String getLinkmanName() {
        return linkmanName;
    }

    public void setLinkmanName(String linkmanName) {
        this.linkmanName = linkmanName;
    }

    public String getCompanyTelNum() {
        return companyTelNum;
    }

    public void setCompanyTelNum(String companyTelNum) {
        this.companyTelNum = companyTelNum;
    }

    public String getCompanyAddr() {
        return companyAddr;
    }

    public void setCompanyAddr(String companyAddr) {
        this.companyAddr = companyAddr;
    }

    public String getCompanyZip() {
        return companyZip;
    }

    public void setCompanyZip(String companyZip) {
        this.companyZip = companyZip;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getFirstInDate() {
        return firstInDate;
    }

    public void setFirstInDate(String firstInDate) {
        this.firstInDate = firstInDate;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Override
    public String toString() {
        return "ExpEmployeeDTO{" +
            "manager='" + manager + '\'' +
            ", majordomo='" + majordomo + '\'' +
            ", companyId='" + companyId + '\'' +
            ", title='" + title + '\'' +
            ", productId='" + productId + '\'' +
            ", basicProductId='" + basicProductId + '\'' +
            ", productName='" + productName + '\'' +
            ", count=" + count +
            ", linkmanName='" + linkmanName + '\'' +
            ", companyTelNum='" + companyTelNum + '\'' +
            ", companyAddr='" + companyAddr + '\'' +
            ", companyZip='" + companyZip + '\'' +
            ", city='" + city + '\'' +
            ", firstInDate='" + firstInDate + '\'' +
            ", employeeName='" + employeeName + '\'' +
            ", employeeId='" + employeeId + '\'' +
            ", gender=" + gender +
            ", mobile='" + mobile + '\'' +
            '}';
    }
}
