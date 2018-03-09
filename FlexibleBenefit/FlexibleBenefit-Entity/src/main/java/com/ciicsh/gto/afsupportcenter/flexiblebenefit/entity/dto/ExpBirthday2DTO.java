package com.ciicsh.gto.afsupportcenter.flexiblebenefit.entity.dto;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;

import java.util.Date;

/**
 * @Author: guwei
 * @Description:
 * @Date: Created in 13:55 2018/3/4
 */
@ExcelTarget("birthday2Form")
public class ExpBirthday2DTO {

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
     * 客户经理
     */
    @Excel(name = "客户经理")
    private String manager;

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
    /**
     * 出生日期
     */
    @Excel(name = "出生日期", format = "yyyy-MM-dd")
    private Date birthday;
    /**
     * 资深雇员编号
     */
    private String oldEmpId;
    /**
     * 电子邮箱
     */
    @Excel(name = "电子邮箱")
    private String email;
    /**
     * 服务中心
     */
    @Excel(name = "服务中心")
    private String serviceCenter;
    /**
     * 员工工号
     */
    @Excel(name = "员工工号")
    private String empCode;
    /**
     * 服务类型
     */
    @Excel(name = "服务类型")
    private String serviceType;

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

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
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

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getOldEmpId() {
        return oldEmpId;
    }

    public void setOldEmpId(String oldEmpId) {
        this.oldEmpId = oldEmpId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getServiceCenter() {
        return serviceCenter;
    }

    public void setServiceCenter(String serviceCenter) {
        this.serviceCenter = serviceCenter;
    }

    public String getEmpCode() {
        return empCode;
    }

    public void setEmpCode(String empCode) {
        this.empCode = empCode;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    @Override
    public String toString() {
        return "ExpBirthday2DTO{" +
            "companyId='" + companyId + '\'' +
            ", title='" + title + '\'' +
            ", companyTelNum='" + companyTelNum + '\'' +
            ", companyAddr='" + companyAddr + '\'' +
            ", companyZip='" + companyZip + '\'' +
            ", manager='" + manager + '\'' +
            ", employeeName='" + employeeName + '\'' +
            ", employeeId='" + employeeId + '\'' +
            ", gender=" + gender +
            ", mobile='" + mobile + '\'' +
            ", birthday=" + birthday +
            ", oldEmpId='" + oldEmpId + '\'' +
            ", email='" + email + '\'' +
            ", serviceCenter='" + serviceCenter + '\'' +
            ", empCode='" + empCode + '\'' +
            ", serviceType='" + serviceType + '\'' +
            '}';
    }
}
