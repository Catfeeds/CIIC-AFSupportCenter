package com.ciicsh.gto.afsupportcenter.credentialscommandservice.entity.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: guwei
 * @Description:
 * @Date: Created in 14:29 2018/2/26
 */
public class EmployeeDTO implements Serializable {

    private String companyId;

    private String employeeName;

    private Integer gender;

    private String countryCode;

    private Integer idCardType;

    private String idNum;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "+8")
    private Date birthday;

    private Integer marriageStatus;

    private String address;

    private String remark;

    public Integer getMarriageStatus() {
        return marriageStatus;
    }

    public void setMarriageStatus(Integer marriageStatus) {
        this.marriageStatus = marriageStatus;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public Integer getIdCardType() {
        return idCardType;
    }

    public void setIdCardType(Integer idCardType) {
        this.idCardType = idCardType;
    }

    public String getIdNum() {
        return idNum;
    }

    public void setIdNum(String idNum) {
        this.idNum = idNum;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "EmployeeDTO{" +
            "companyId='" + companyId + '\'' +
            ", employeeName='" + employeeName + '\'' +
            ", gender=" + gender +
            ", countryCode='" + countryCode + '\'' +
            ", idCardType=" + idCardType +
            ", idNum='" + idNum + '\'' +
            ", birthday=" + birthday +
            ", remark='" + remark + '\'' +
            '}';
    }
}
