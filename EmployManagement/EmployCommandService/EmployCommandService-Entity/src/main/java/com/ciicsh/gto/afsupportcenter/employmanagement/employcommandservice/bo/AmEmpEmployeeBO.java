package com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.bo;

import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.entity.AmEmpEmployee;

/**
 * Created by zhangzhiwen on 2018/4/9.
 */
public class AmEmpEmployeeBO extends AmEmpEmployee {

    private  String sex;

    /**
     * 是否无期限合同
     */
    private String isUnlimitedContract;
    /**
     * 派遣年限
     */
    private String sendCondemnationYears;

    /**
     * 人员性质
     */
    private String employeeNature;
    /**
     * 档案方向
     */
    private String archiveDirection;
    /**
     * 用工属性
     */
    private String employProperty;

    /**
     * 用工公司特殊情况
     */
    private String employSpecial;

    /**
     * Ukey类别
     */
    private String keyType;
    /**
     * Ukey编码
     */
    private String keyCode;
    /**
     * Ukey密码
     */
    private String keyPwd;

    /**
     * Ukey状态
     */
    private String keyStatus;

    private String laborStartDateStr;

    private String laborEndDateStr;

    private String firstInCompanyDateStr;

    private String firstInDateStr;

    /**
     * 是否邮寄退工单
     */
    private Boolean mailContinue;
    /**
     * 邮寄退工单地址
     */
    private String mailAdress;
    /**
     * 邮寄退工单收件人
     */
    private String recipient;
    /**
     * 邮寄退工单邮编
     */
    private String postCode;
    /**
     * 邮寄退工单电话
     */
    private String phone;

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

    public String getFirstInDateStr() {
        return firstInDateStr;
    }

    public void setFirstInDateStr(String firstInDateStr) {
        this.firstInDateStr = firstInDateStr;
    }

    public String getLaborStartDateStr() {
        return laborStartDateStr;
    }

    public void setLaborStartDateStr(String laborStartDateStr) {
        this.laborStartDateStr = laborStartDateStr;
    }

    public String getLaborEndDateStr() {
        return laborEndDateStr;
    }

    public void setLaborEndDateStr(String laborEndDateStr) {
        this.laborEndDateStr = laborEndDateStr;
    }

    public String getFirstInCompanyDateStr() {
        return firstInCompanyDateStr;
    }

    public void setFirstInCompanyDateStr(String firstInCompanyDateStr) {
        this.firstInCompanyDateStr = firstInCompanyDateStr;
    }

    public String getKeyStatus() {
        return keyStatus;
    }

    public void setKeyStatus(String keyStatus) {
        this.keyStatus = keyStatus;
    }

    public String getEmploySpecial() {
        return employSpecial;
    }

    public void setEmploySpecial(String employSpecial) {
        this.employSpecial = employSpecial;
    }

    public String getKeyType() {
        return keyType;
    }

    public void setKeyType(String keyType) {
        this.keyType = keyType;
    }

    public String getKeyCode() {
        return keyCode;
    }

    public void setKeyCode(String keyCode) {
        this.keyCode = keyCode;
    }

    public String getKeyPwd() {
        return keyPwd;
    }

    public void setKeyPwd(String keyPwd) {
        this.keyPwd = keyPwd;
    }

    public String getEmployProperty() {
        return employProperty;
    }

    public void setEmployProperty(String employProperty) {
        this.employProperty = employProperty;
    }

    public String getArchiveDirection() {
        return archiveDirection;
    }

    public void setArchiveDirection(String archiveDirection) {
        this.archiveDirection = archiveDirection;
    }

    public String getEmployeeNature() {
        return employeeNature;
    }

    public void setEmployeeNature(String employeeNature) {
        this.employeeNature = employeeNature;
    }

    public String getIsUnlimitedContract() {
        return isUnlimitedContract;
    }

    public void setIsUnlimitedContract(String isUnlimitedContract) {
        this.isUnlimitedContract = isUnlimitedContract;
    }

    public String getSendCondemnationYears() {
        return sendCondemnationYears;
    }

    public void setSendCondemnationYears(String sendCondemnationYears) {
        this.sendCondemnationYears = sendCondemnationYears;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
