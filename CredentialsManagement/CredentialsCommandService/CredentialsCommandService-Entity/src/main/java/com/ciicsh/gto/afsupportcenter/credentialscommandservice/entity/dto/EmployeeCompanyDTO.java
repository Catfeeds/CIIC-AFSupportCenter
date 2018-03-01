package com.ciicsh.gto.afsupportcenter.credentialscommandservice.entity.dto;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

/**
 * @Author: guwei
 * @Description:
 * @Date: Created in 15:35 2018/2/12
 */
public class EmployeeCompanyDTO {

    /**
     * 雇员编号
     */
    private String employeeId;
    /**
     * 雇员姓名
     */
    private String employeeName;
    /**
     * 证件类型
     */
    private Integer idCardType;
    /**
     * 证件号码
     */
    private String idNum;
    /**
     * 客户编号
     */
    private String companyId;
    /**
     * 客户名称
     */
    private String companyName;
    /**
     * 入离职状态
     */
    private Integer status;
    /**
     * 入离职状态UI
     */
    private String statusUI;
    /**
     * 雇员类型 1：af 2：bpo 3：fc
     */
    private String type;

    public Integer getIdCardType() {
        return idCardType;
    }

    public void setIdCardType(Integer idCardType) {
        this.idCardType = idCardType;
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

    public String getIdNum() {
        return idNum;
    }

    public void setIdNum(String idNum) {
        this.idNum = idNum;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatusUI() {
        return statusUI;
    }

    public void setStatusUI(String statusUI) {
        this.statusUI = statusUI;
    }

    @Override
    public String toString() {
        return "EmployeeCompany{" +
            "employeeId='" + employeeId + '\'' +
            ", employeeName='" + employeeName + '\'' +
            ", idCardType='" + idCardType + '\'' +
            ", idNum='" + idNum + '\'' +
            ", companyId='" + companyId + '\'' +
            ", companyName='" + companyName + '\'' +
            ", type='" + type + '\'' +
            ", status=" + status +
            '}';
    }
}
