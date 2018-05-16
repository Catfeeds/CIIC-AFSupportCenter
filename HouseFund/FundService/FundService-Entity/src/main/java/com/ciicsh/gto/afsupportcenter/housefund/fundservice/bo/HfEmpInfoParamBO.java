package com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo;/**
 * Created by baofeng on 2018/5/14
 */

/**
 * 公积金输入BO
 * @author: baofeng
 * @date: 2018-5-15 19:58:15
 **/
public class HfEmpInfoParamBO {

    /**
     * 雇员编号
     */
    private String employeeId;
    /**
     * 客户编号
     */
    private String companyId;
    /**
     * 公积金所属月份 yyyyMM
     */
    private String hfMonthBelong;

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

    public String getHfMonthBelong() {
        return hfMonthBelong;
    }

    public void setHfMonthBelong(String hfMonthBelong) {
        this.hfMonthBelong = hfMonthBelong;
    }
}
