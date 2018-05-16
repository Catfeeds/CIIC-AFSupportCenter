package com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo;/**
 * Created by baofeng on 2018/5/16
 */

import java.math.BigDecimal;
import java.util.List;

/**
 * 社保输出BO
 * @author: baofeng
 * @date: 2018-5-16 09:26:49
 **/
public class SsEmpInfoBO {
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
    /**
     * 社保汇缴月份 yyyyMM
     */
    private String ssMonth;
    /**
     * 社保合计
     */
    private BigDecimal empAmountTotal;
    /**
     * 险种明细
     */
    private List<SsEmpInfoDetailBO> ssEmpInfoDetailBOList;

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

    public String getSsMonthBelong() { return ssMonthBelong; }

    public void setSsMonthBelong(String ssMonthBelong) { this.ssMonthBelong = ssMonthBelong; }

    public BigDecimal getEmpAmountTotal() {
        return empAmountTotal;
    }

    public void setEmpAmountTotal(BigDecimal empAmountTotal) {
        this.empAmountTotal = empAmountTotal;
    }

    public List<SsEmpInfoDetailBO> getSsEmpInfoDetailBOList() {
        return ssEmpInfoDetailBOList;
    }

    public void setSsEmpInfoDetailBOList(List<SsEmpInfoDetailBO> ssEmpInfoDetailBOList) {
        this.ssEmpInfoDetailBOList = ssEmpInfoDetailBOList;
    }

    public String getSsMonth() {
        return ssMonth;
    }

    public void setSsMonth(String ssMonth) {
        this.ssMonth = ssMonth;
    }
}
