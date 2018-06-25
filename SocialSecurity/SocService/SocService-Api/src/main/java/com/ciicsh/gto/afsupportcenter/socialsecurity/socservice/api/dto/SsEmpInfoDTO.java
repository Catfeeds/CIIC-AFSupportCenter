package com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.api.dto;/**
 * Created by zhengj on 2018/5/14
 */

import java.math.BigDecimal;
import java.util.List;

/**
 * 社保输出参数Class
 * @author: zhengj
 * @date: 2018/5/14 18:42
 **/
public class SsEmpInfoDTO {
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


    private Integer archiveStatus;
    private String archiveStatusName;

    /**
     * 险种明细
     */
    private List<SsEmpInfoDetailDTO> ssEmpInfoDetailDTOList;

    public Integer getArchiveStatus() {
        return archiveStatus;
    }

    public void setArchiveStatus(Integer archiveStatus) {
        this.archiveStatus = archiveStatus;
    }

    public String getArchiveStatusName() {
        return archiveStatusName;
    }

    public void setArchiveStatusName(String archiveStatusName) {
        this.archiveStatusName = archiveStatusName;
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

    public String getSsMonthBelong() { return ssMonthBelong; }

    public void setSsMonthBelong(String ssMonthBelong) { this.ssMonthBelong = ssMonthBelong; }

    public BigDecimal getEmpAmountTotal() {
        return empAmountTotal;
    }

    public void setEmpAmountTotal(BigDecimal empAmountTotal) {
        this.empAmountTotal = empAmountTotal;
    }

    public List<SsEmpInfoDetailDTO> getSsEmpInfoDetailDTOList() {
        return ssEmpInfoDetailDTOList;
    }

    public void setSsEmpInfoDetailDTOList(List<SsEmpInfoDetailDTO> ssEmpInfoDetailDTOList) {
        this.ssEmpInfoDetailDTOList = ssEmpInfoDetailDTOList;
    }

    public String getSsMonth() {
        return ssMonth;
    }

    public void setSsMonth(String ssMonth) {
        this.ssMonth = ssMonth;
    }
}
