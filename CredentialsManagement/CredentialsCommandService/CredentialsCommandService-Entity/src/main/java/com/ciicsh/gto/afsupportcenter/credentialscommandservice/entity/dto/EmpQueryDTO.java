package com.ciicsh.gto.afsupportcenter.credentialscommandservice.entity.dto;

import java.io.Serializable;

/**
 * @Author: guwei
 * @Description:
 * @Date: Created in 14:54 2018/1/20
 */
public class EmpQueryDTO implements Serializable{

    /**
     * 雇员编号
     */
    private String empCode;
    /**
     * 雇员姓名
     */
    private String empName;
    /**
     * 客户编号
     */
    private String companyCode;
    /**
     * 客户姓名
     */
    private String companyName;
    /**
     * 入离职状态（1：在职，2：离职）
     */
    private Integer status;

    public String getEmpCode() {
        return empCode;
    }

    public void setEmpCode(String empCode) {
        this.empCode = empCode;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
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

    @Override
    public String toString() {
        return "EmpQueryDTO{" +
            "empCode='" + empCode + '\'' +
            ", empName='" + empName + '\'' +
            ", companyCode='" + companyCode + '\'' +
            ", companyName='" + companyName + '\'' +
            ", status=" + status +
            '}';
    }
}
