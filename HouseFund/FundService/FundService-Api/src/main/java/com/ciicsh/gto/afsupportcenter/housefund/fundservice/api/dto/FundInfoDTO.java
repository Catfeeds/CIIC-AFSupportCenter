package com.ciicsh.gto.afsupportcenter.housefund.fundservice.api.dto;

/**
 * Created by houwanhua on 2018/4/9.
 */
public class FundInfoDTO {
    /**
     * 1 基本公积金、2 补充公积金
     */
    private Integer hfType;

    /**
     * 企业基本补充公积金账号（前道传递）
     */
    private String hfComAccount;
    /**
     * 客户缴费起始年月（前道传递）
     */
    private String comStartMonth;
    /**
     * 截止缴费年月（截单日）（前道传递）
     */
    private String endMonth;

    /**
     * 1 销户 2 公司自做 3 转其他代理商（前道传递）
     */
    private Integer endType;

    /**
     * 备注（前道传递）
     */
    private String remark;

    public Integer getHfType() {
        return hfType;
    }

    public FundInfoDTO setHfType(Integer hfType) {
        this.hfType = hfType;
        return this;
    }

    public String getHfComAccount() {
        return hfComAccount;
    }

    public FundInfoDTO setHfComAccount(String hfComAccount) {
        this.hfComAccount = hfComAccount;
        return this;
    }

    public String getComStartMonth() {
        return comStartMonth;
    }

    public FundInfoDTO setComStartMonth(String comStartMonth) {
        this.comStartMonth = comStartMonth;
        return this;
    }

    public String getEndMonth() {
        return endMonth;
    }

    public FundInfoDTO setEndMonth(String endMonth) {
        this.endMonth = endMonth;
        return this;
    }

    public Integer getEndType() {
        return endType;
    }

    public FundInfoDTO setEndType(Integer endType) {
        this.endType = endType;
        return this;
    }

    public String getRemark() {
        return remark;
    }

    public FundInfoDTO setRemark(String remark) {
        this.remark = remark;
        return this;
    }
}
