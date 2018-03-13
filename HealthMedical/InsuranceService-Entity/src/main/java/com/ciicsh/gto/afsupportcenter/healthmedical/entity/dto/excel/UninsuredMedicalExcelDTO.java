package com.ciicsh.gto.afsupportcenter.healthmedical.entity.dto.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author: guwei
 * @Description:
 * @Date: Created in 13:46 2018/3/13
 */
@ExcelTarget("uninsureMedicalExcel")
public class UninsuredMedicalExcelDTO implements Serializable{

    @Excel(name = "受理编号")
    private Integer umAcceptanceId;
    @Excel(name = "雇员编号")
    private String employeeId;
    @Excel(name = "雇员姓名")
    private String employeeName;
    @Excel(name = "公司编号")
    private String companyId;
    @Excel(name = "公司名称")
    private String companyName;
    @Excel(name = "受理类型", replace = {"雇员_1", "子女_2", "配偶_3"})
    private Integer caseType;
    @Excel(name = "款项类型", replace = {"医疗费_1" ,"体检费用_2", "住院补贴_3", "大额理赔款_4", "其他_5"})
    private Integer moneyType;
    @Excel(name = "状态", replace = {"未受理_0", "已受理_1", "拒赔_2", "已审核未同步_3", "已同步未支付_4", "财务退回_5", "已同步已支付_6", "已退票_7", "已完成_8"})
    private Integer status;
    @Excel(name = "受理日期" ,format = "yyyy-MM-dd")
    private Date handlerDate;
    @Excel(name = "受理金额")
    private BigDecimal caseMoney;
    @Excel(name = "连带人")
    private String jointPersonName;
    @Excel(name = "受理人")
    private String handler;

    public Integer getUmAcceptanceId() {
        return umAcceptanceId;
    }

    public void setUmAcceptanceId(Integer umAcceptanceId) {
        this.umAcceptanceId = umAcceptanceId;
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

    public Integer getCaseType() {
        return caseType;
    }

    public void setCaseType(Integer caseType) {
        this.caseType = caseType;
    }

    public Integer getMoneyType() {
        return moneyType;
    }

    public void setMoneyType(Integer moneyType) {
        this.moneyType = moneyType;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getHandlerDate() {
        return handlerDate;
    }

    public void setHandlerDate(Date handlerDate) {
        this.handlerDate = handlerDate;
    }

    public BigDecimal getCaseMoney() {
        return caseMoney;
    }

    public void setCaseMoney(BigDecimal caseMoney) {
        this.caseMoney = caseMoney;
    }

    public String getJointPersonName() {
        return jointPersonName;
    }

    public void setJointPersonName(String jointPersonName) {
        this.jointPersonName = jointPersonName;
    }

    public String getHandler() {
        return handler;
    }

    public void setHandler(String handler) {
        this.handler = handler;
    }

    @Override
    public String toString() {
        return "UninsuredMedicalExcelDTO{" +
            "umAcceptanceId=" + umAcceptanceId +
            ", employeeId='" + employeeId + '\'' +
            ", employeeName='" + employeeName + '\'' +
            ", companyId='" + companyId + '\'' +
            ", companyName='" + companyName + '\'' +
            ", caseType=" + caseType +
            ", moneyType=" + moneyType +
            ", status=" + status +
            ", handlerDate=" + handlerDate +
            ", caseMoney=" + caseMoney +
            ", jointPersonName='" + jointPersonName + '\'' +
            ", handler='" + handler + '\'' +
            '}';
    }
}
