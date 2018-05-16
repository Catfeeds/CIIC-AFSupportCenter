package com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo;/**
 * Created by baofeng on 2018/5/16
 */

import java.math.BigDecimal;

/**
 * @author: baofeng
 * @date: 2018-5-16 09:29:15
 **/
public class SsEmpInfoDetailBO {
    /**
     * 险种类型
     */
    private String ssType;
    /**
     * 险种名称
     */
    private String ssTypeName;
    /**
     * 个人部分金额
     */
    private BigDecimal empAmount;
    /**
     * 雇员编号
     */
    private String employeeId;
    /**
     * 公司编号
     */
    private String companyId;
    /**
     * 汇缴月份
     */
    private String ssMonth;
    /**
     * 所属月份
     */
    private String ssMonthBelong;

    public String getSsType() {
        return ssType;
    }

    public void setSsType(String ssType) {
        this.ssType = ssType;
    }

    public String getSsTypeName() {
        return ssTypeName;
    }

    public void setSsTypeName(String ssTypeName) {
        this.ssTypeName = ssTypeName;
    }

    public BigDecimal getEmpAmount() {
        return empAmount;
    }

    public void setEmpAmount(BigDecimal empAmount) {
        this.empAmount = empAmount;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getSsMonth() {
        return ssMonth;
    }

    public void setSsMonth(String ssMonth) {
        this.ssMonth = ssMonth;
    }

    public String getSsMonthBelong() {
        return ssMonthBelong;
    }

    public void setSsMonthBelong(String ssMonthBelong) {
        this.ssMonthBelong = ssMonthBelong;
    }
}
