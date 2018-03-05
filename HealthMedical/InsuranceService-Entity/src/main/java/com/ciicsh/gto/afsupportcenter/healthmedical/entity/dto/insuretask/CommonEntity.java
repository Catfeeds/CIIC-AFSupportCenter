package com.ciicsh.gto.afsupportcenter.healthmedical.entity.dto.insuretask;

import java.util.List;

/**
 * 分页通用字段
 *
 * @author xiweizhen
 */
public class CommonEntity<T> {

    /**
     * 投保类型
     */
  private String insureType;

    /**
     * 投保日期
     */
    private String insureDate;


    /**
     * 公司编号
     */
    private String companyId;


    /**
     * 雇员编号
     */
    private String employeeId;

    /**
     * 雇员姓名
     */
    private String employeeName;

    /**
     * 备注
     */
    private String IDNum;

    /**
     * 备注
     */
    private String remark;


    public String getInsureType() {
        return insureType;
    }

    public void setInsureType(String insureType) {
        this.insureType = insureType;
    }

    public String getInsureDate() {
        return insureDate;
    }

    public void setInsureDate(String insureDate) {
        this.insureDate = insureDate;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
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

    public String getIDNum() {
        return IDNum;
    }

    public void setIDNum(String IDNum) {
        this.IDNum = IDNum;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
