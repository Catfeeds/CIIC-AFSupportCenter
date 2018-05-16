package com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo;/**
 * Created by baofeng on 2018/5/14
 */

import java.math.BigDecimal;

/**
 * 公积金明细BO
 *
 * @author: baofeng
 * @date: 2018-5-15 19:59:20
 **/
public class HfEmpInfoDetailBO {
    /**
     * 类型1：基本公积金2：补充公积金
     */
    private String hfType;
    /**
     * 名称
     */
    private String hfTypeName;
    /**
     * 个人部分金额
     */
    private BigDecimal empAmount;

    private String employeeId;
    private String companyId;
    private String hfMonth;
    private String hfMonthBelong;

    public String getHfType() {
        return hfType;
    }

    public void setHfType(String hfType) {
        this.hfType = hfType;
    }

    public String getHfTypeName() {
        return hfTypeName;
    }

    public void setHfTypeName(String hfTypeName) {
        this.hfTypeName = hfTypeName;
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

    public String getHfMonth() {
        return hfMonth;
    }

    public void setHfMonth(String hfMonth) {
        this.hfMonth = hfMonth;
    }

    public String getHfMonthBelong() {
        return hfMonthBelong;
    }

    public void setHfMonthBelong(String hfMonthBelong) {
        this.hfMonthBelong = hfMonthBelong;
    }
}
