package com.ciicsh.gto.afsupportcenter.housefund.fundservice.api.dto;/**
 * Created by zhengj on 2018/5/14
 */

import java.math.BigDecimal;
import java.util.List;

/**
 * 公积金输出参数Class
 * @author: zhengj
 * @date: 2018/5/14 18:42
 **/
public class HfEmpInfoDTO {
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
    private String hfMonth;
    /**
     * 公积金合计
     */
    private BigDecimal empAmountTotal;
    /**
     * 险种明细
     */
    private List<HfEmpInfoDetailDTO> hfEmpInfoDetailDTOList;

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

    public BigDecimal getEmpAmountTotal() {
        return empAmountTotal;
    }

    public void setEmpAmountTotal(BigDecimal empAmountTotal) {
        this.empAmountTotal = empAmountTotal;
    }

    public List<HfEmpInfoDetailDTO> getHfEmpInfoDetailDTOList() {
        return hfEmpInfoDetailDTOList;
    }

    public void setHfEmpInfoDetailDTOList(List<HfEmpInfoDetailDTO> hfEmpInfoDetailDTOList) {
        this.hfEmpInfoDetailDTOList = hfEmpInfoDetailDTOList;
    }
}
