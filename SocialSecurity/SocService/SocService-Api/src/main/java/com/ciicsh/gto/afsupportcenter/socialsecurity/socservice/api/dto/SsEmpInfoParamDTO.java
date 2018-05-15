package com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.api.dto;/**
 * Created by zhengj on 2018/5/14
 */

/**
 * 社保输入参数Class
 * @author: zhengj
 * @date: 2018/5/14 18:42
 **/
public class SsEmpInfoParamDTO {

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
    private String ssMonth;

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
}
