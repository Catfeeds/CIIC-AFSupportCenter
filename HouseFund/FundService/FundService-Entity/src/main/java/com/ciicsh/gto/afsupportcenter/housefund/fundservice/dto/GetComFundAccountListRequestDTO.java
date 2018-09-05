package com.ciicsh.gto.afsupportcenter.housefund.fundservice.dto;

/**
 * 获取公司公积金账户列表请求报文
 */
public class GetComFundAccountListRequestDTO
{
    /**
     * 客户编号
     */
    private String companyId;

    /**
     * 客户名称
     */
    private String companyName;


    /**
     * 公积金账户类型
     * 1 基本公积金、2 补充公积金
     */
    private Byte hfType;

    /**
     * 客户最后一次汇缴公积金的月份，格式yyyyMM
     */
    private String comHfMonth;

    /**
     * 公积金账户编号
     */
    private String accountNumber;
    private String leaderShipName;
    private String payBankValue;
    private String serviceCenterValue;

    public String getLeaderShipName() {
        return leaderShipName;
    }

    public void setLeaderShipName(String leaderShipName) {
        this.leaderShipName = leaderShipName;
    }

    public String getPayBankValue() {
        return payBankValue;
    }

    public void setPayBankValue(String payBankValue) {
        this.payBankValue = payBankValue;
    }

    public String getServiceCenterValue() {
        return serviceCenterValue;
    }

    public void setServiceCenterValue(String serviceCenterValue) {
        this.serviceCenterValue = serviceCenterValue;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Byte getHfType() {
        return hfType;
    }

    public void setHfType(Byte hfType) {
        this.hfType = hfType;
    }

    public String getComHfMonth() {
        return comHfMonth;
    }

    public void setComHfMonth(String comHfMonth) {
        this.comHfMonth = comHfMonth;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
}
