package com.ciicsh.gto.afsupportcenter.housefund.fundservice.api.dto;

import java.util.List;

/**
 * Created by houwanhua on 2018/3/7.
 */
public class ComAccountExtDTO {
    /**
     * 企业账户名称（前道传递）
     */
    private String comAccountName;
    /**
     * 付款方式:
     * 1 自付（客户自己汇缴给银行，雇员由中智办理）
     * 2 我司付款（客户预付）
     * 3 垫付（前道传递）
     */
    private Integer paymentWay;

    /**
     * 客户公积金账户 每月的关账到哪一天1-31（前道传递）
     */
    private Integer closeDay;
    /**
     * 客户Id
     */
    private String companyId;

    private List<FundInfoDTO> fundInfos;

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getComAccountName() {
        return comAccountName;
    }

    public void setComAccountName(String comAccountName) {
        this.comAccountName = comAccountName;
    }

    public Integer getPaymentWay() {
        return paymentWay;
    }

    public void setPaymentWay(Integer paymentWay) {
        this.paymentWay = paymentWay;
    }

    public Integer getCloseDay() {
        return closeDay;
    }

    public void setCloseDay(Integer closeDay) {
        this.closeDay = closeDay;
    }

    public List<FundInfoDTO> getFundInfos() {
        return fundInfos;
    }

    public void setFundInfos(List<FundInfoDTO> fundInfos) {
        this.fundInfos = fundInfos;
    }
}
