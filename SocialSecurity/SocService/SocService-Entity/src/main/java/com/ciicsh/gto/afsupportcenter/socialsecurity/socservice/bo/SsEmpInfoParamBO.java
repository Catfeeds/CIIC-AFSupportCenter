package com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo;/**
 * Created by baofeng on 2018/5/16
 */

/**
 * 社保输入BO
 * @author: baofeng
 * @date: 2018-5-16 09:28:04
 **/
public class SsEmpInfoParamBO {

    /**
     * 雇员编号
     */
    private String employeeId;
    /**
     * 客户编号
     */
    private String companyId;
    /**
     * 社保所属月份 yyyyMM
     */
    private String ssMonthBelong;

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

    public String getSsMonthBelong() {
        return ssMonthBelong;
    }

    public void setSsMonthBelong(String ssMonthBelong) {
        this.ssMonthBelong = ssMonthBelong;
    }

}
