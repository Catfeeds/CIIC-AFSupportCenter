package com.ciicsh.gto.afsupportcenter.employmanagement.employservice.dto;

import java.util.Date;

/**
 * Created by liyuelong on 2018/7/24 批量打印退工单DTO.
 */
public class AmReturnListExportDTO {

    /**
     * 档案类型
     */
    private String docType;

    /**
     * 档案编号
     */
    private String docNumber;

    /**
     * 基本公积金帐号
     */
    private String hfAccount;

    /**
     * 补充公积金帐号
     */
    private String hfAccountBC;

    /**
     * 雇员名称
     */
    private String employeeName;

    /**
     * 雇员编号
     */
    private String employeeId;

    /**
     * 性别
     */
    private Integer gender;

    /**
     * 出生日期
     */
    private Date dateOfBirth;

    /**
     * 用工形式
     */
    private String employStyle;

    /**
     * 退工日期
     */
    private  String outDate;

    /**
     * 终止类型:空、合同终止、合同解除
     */
    private String endType;

    /**
     * 身份证号码
     */
    private String idNum;

    /**
     * 户口地址
     */
    private String address;

    /**
     * 退档日期 该员工档案转出时间
     */
    private Date returnDocDate;

    /**
     * 转移方式
     */
    private String transferWay;

    /**
     * 劳动手册是否交被退人员
     */
    private Integer ifLaborManualReturnStr;

    /**
     * 组织机构代码
     */
    private String organizationCode;

    /**
     * 经办人
     */
    private String createdBy;

    /**
     * 联系电话
     */
    private String tel;

    /**
     * 最后日期
     */
    private Date lastDate;

    /**
     * 公司名称
     */
    private String companyName;


    public String getDocType() {
        return docType;
    }

    public void setDocType(String docType) {
        this.docType = docType;
    }

    public String getDocNumber() {
        return docNumber;
    }

    public void setDocNumber(String docNumber) {
        this.docNumber = docNumber;
    }

    public String getHfAccount() {
        return hfAccount;
    }

    public void setHfAccount(String hfAccount) {
        this.hfAccount = hfAccount;
    }

    public String getHfAccountBC() {
        return hfAccountBC;
    }

    public void setHfAccountBC(String hfAccountBC) {
        this.hfAccountBC = hfAccountBC;
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

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmployStyle() {
        return employStyle;
    }

    public void setEmployStyle(String employStyle) {
        this.employStyle = employStyle;
    }

    public String getOutDate() {
        return outDate;
    }

    public void setOutDate(String outDate) {
        this.outDate = outDate;
    }

    public String getEndType() {
        return endType;
    }

    public void setEndType(String endType) {
        this.endType = endType;
    }

    public String getIdNum() {
        return idNum;
    }

    public void setIdNum(String idNum) {
        this.idNum = idNum;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getReturnDocDate() {
        return returnDocDate;
    }

    public void setReturnDocDate(Date returnDocDate) {
        this.returnDocDate = returnDocDate;
    }

    public String getTransferWay() {
        return transferWay;
    }

    public void setTransferWay(String transferWay) {
        this.transferWay = transferWay;
    }

    public Integer getIfLaborManualReturnStr() {
        return ifLaborManualReturnStr;
    }

    public void setIfLaborManualReturnStr(Integer ifLaborManualReturnStr) {
        this.ifLaborManualReturnStr = ifLaborManualReturnStr;
    }

    public String getOrganizationCode() {
        return organizationCode;
    }

    public void setOrganizationCode(String organizationCode) {
        this.organizationCode = organizationCode;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Date getLastDate() {
        return lastDate;
    }

    public void setLastDate(Date lastDate) {
        this.lastDate = lastDate;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
