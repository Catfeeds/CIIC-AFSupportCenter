package com.ciicsh.gto.afsupportcenter.healthmedical.entity.dto.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: guwei
 * @Description:
 * @Date: Created in 14:34 2018/3/10
 */
@ExcelTarget("MedicalRelationTransformExcel")
public class MedicalRelationTransformExcelDTO implements Serializable {

    @Excel(name = "雇员编号")
    private String employeeId;
    @Excel(name = "雇员姓名")
    private String employeeName;
    @Excel(name = "证件号码")
    private String idNum;
    @Excel(name = "公司编号")
    private String companyId;
    @Excel(name = "公司名称")
    private String companyName;
    @Excel(name = "转出日期", format = "yyyy-MM-dd")
    private Date turnOutDate;
    @Excel(name = "转出地点")
    private String turnOutAddress;
    @Excel(name = "转回日期", format = "yyyy-MM-dd")
    private Date turnBackDate;
    @Excel(name = "备注")
    private String remark;

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

    public Date getTurnOutDate() {
        return turnOutDate;
    }

    public void setTurnOutDate(Date turnOutDate) {
        this.turnOutDate = turnOutDate;
    }

    public String getTurnOutAddress() {
        return turnOutAddress;
    }

    public void setTurnOutAddress(String turnOutAddress) {
        this.turnOutAddress = turnOutAddress;
    }

    public Date getTurnBackDate() {
        return turnBackDate;
    }

    public void setTurnBackDate(Date turnBackDate) {
        this.turnBackDate = turnBackDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "MedicalRelationTransformExcelDTO{" +
            "employeeId='" + employeeId + '\'' +
            ", employeeName='" + employeeName + '\'' +
            ", idNum='" + idNum + '\'' +
            ", companyId='" + companyId + '\'' +
            ", companyName='" + companyName + '\'' +
            ", turnOutDate=" + turnOutDate +
            ", turnOutAddress='" + turnOutAddress + '\'' +
            ", turnBackDate=" + turnBackDate +
            ", remark='" + remark + '\'' +
            '}';
    }
}
