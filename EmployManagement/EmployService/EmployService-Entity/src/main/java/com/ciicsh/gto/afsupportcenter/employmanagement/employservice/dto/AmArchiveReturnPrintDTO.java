package com.ciicsh.gto.afsupportcenter.employmanagement.employservice.dto;

import java.util.Date;
import java.util.List;

public class AmArchiveReturnPrintDTO {


    // 档案类别
    private String docType;

    // 档案编号
    private String docNum;

    /**
     * 雇员id
     */
    private String employeeId;

    /**
     * 雇员姓名
     */
    private String employeeName;

    /**
     * 基本公积金帐号
     */
    private String hfAccount;

    /**
     * 补充公积金帐号
     */
    private String hfAccountBC;

    /**
     * 雇员姓别
     */
    private Integer gender;

    /**
     * 开始日期
     */
    private Date startDate;

    /**
     * 用工形式
     */
    private String employStyle;

    /**
     * 结束日期
     */
    private Date endDate;

    /**
     * 终止类型:空、合同终止、合同解除
     */
    private String endType;

    /**
     * 身份证号
     */
    private String idNum;

    private String residentArea;
    private String residentStreet;
    private String residentWay;
    private String residentVillage;
    private String residentCard;
    private String residentRoom;

    /**
     * 转出时间
     */
    private Date outDate;

    /**
     * 转移方式
     */
    private String transferWay;

    /**
     * 劳动手册是否交被退人员
     */
    private Integer ifLaborManualReturnStr;

    // 组织机构代码
    private String organizationCode;

    // 经办人
    private String operationName;

    // 联系电话
    private String mobile;

    // 操作日期
    private Date operationDate;

    public String getDocType() {
        return docType;
    }

    public void setDocType(String docType) {
        this.docType = docType;
    }

    public String getDocNum() {
        return docNum;
    }

    public void setDocNum(String docNum) {
        this.docNum = docNum;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
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

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getEmployStyle() {
        return employStyle;
    }

    public void setEmployStyle(String employStyle) {
        this.employStyle = employStyle;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
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

    public Date getOutDate() {
        return outDate;
    }

    public void setOutDate(Date outDate) {
        this.outDate = outDate;
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

    public String getOperationName() {
        return operationName;
    }

    public void setOperationName(String operationName) {
        this.operationName = operationName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Date getOperationDate() {
        return operationDate;
    }

    public void setOperationDate(Date operationDate) {
        this.operationDate = operationDate;
    }

    public String getResidentArea() {
        return residentArea;
    }

    public void setResidentArea(String residentArea) {
        this.residentArea = residentArea;
    }

    public String getResidentStreet() {
        return residentStreet;
    }

    public void setResidentStreet(String residentStreet) {
        this.residentStreet = residentStreet;
    }

    public String getResidentWay() {
        return residentWay;
    }

    public void setResidentWay(String residentWay) {
        this.residentWay = residentWay;
    }

    public String getResidentVillage() {
        return residentVillage;
    }

    public void setResidentVillage(String residentVillage) {
        this.residentVillage = residentVillage;
    }

    public String getResidentCard() {
        return residentCard;
    }

    public void setResidentCard(String residentCard) {
        this.residentCard = residentCard;
    }

    public String getResidentRoom() {
        return residentRoom;
    }

    public void setResidentRoom(String residentRoom) {
        this.residentRoom = residentRoom;
    }
}
