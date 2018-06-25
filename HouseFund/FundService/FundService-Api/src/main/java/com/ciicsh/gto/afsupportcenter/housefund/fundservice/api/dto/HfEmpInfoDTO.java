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
    private String hfMonthBelong;

    /**
     * 公积金汇缴月份 yyyyMM
     */
    private String hfMonth;

    /**
     * 公积金合计
     */
    private BigDecimal empAmountTotal;
    // 基本公积金账号
    private String hfEmpAccount;
    // 补充公积金账号
    private String hfEmpAccountBC;


    /**
     * 险种明细
     */
    private List<HfEmpInfoDetailDTO> hfEmpInfoDetailDTOList;

    public String getHfEmpAccount() {
        return hfEmpAccount;
    }

    public void setHfEmpAccount(String hfEmpAccount) {
        this.hfEmpAccount = hfEmpAccount;
    }

    public String getHfEmpAccountBC() {
        return hfEmpAccountBC;
    }

    public void setHfEmpAccountBC(String hfEmpAccountBC) {
        this.hfEmpAccountBC = hfEmpAccountBC;
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

    public BigDecimal getEmpAmountTotal() {
        return empAmountTotal;
    }

    public void setEmpAmountTotal(BigDecimal empAmountTotal) {
        this.empAmountTotal = empAmountTotal;
    }

    public String getHfMonthBelong() {
        return hfMonthBelong;
    }

    public void setHfMonthBelong(String hfMonthBelong) {
        this.hfMonthBelong = hfMonthBelong;
    }

    public String getHfMonth() {
        return hfMonth;
    }

    public void setHfMonth(String hfMonth) {
        this.hfMonth = hfMonth;
    }

    public List<HfEmpInfoDetailDTO> getHfEmpInfoDetailDTOList() {
        return hfEmpInfoDetailDTOList;
    }

    public void setHfEmpInfoDetailDTOList(List<HfEmpInfoDetailDTO> hfEmpInfoDetailDTOList) {
        this.hfEmpInfoDetailDTOList = hfEmpInfoDetailDTOList;
    }
}
