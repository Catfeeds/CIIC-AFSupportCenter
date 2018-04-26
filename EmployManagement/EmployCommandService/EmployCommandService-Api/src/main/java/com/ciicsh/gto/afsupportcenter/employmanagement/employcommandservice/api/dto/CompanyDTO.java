package com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.api.dto;

/**
 * Created by zhangzhiwen on 2018/4/25.
 */
public class CompanyDTO {

    /**
     * 客户ID
     */
    private String companyId;
    /**
     * 是否寄退工单
     */
    private  Boolean  mailContinue;
    /**
     * 邮寄退工单地址
     */
    private  String mailAdress;
    /**
     * 邮寄退工单收件人
     */
    private  String  recipient;
    /**
     * 邮寄退工单邮编
     */
    private  String postCode;
    /**
     * 邮寄退工单电话
     */
    private String  phone;

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public Boolean getMailContinue() {
        return mailContinue;
    }

    public void setMailContinue(Boolean mailContinue) {
        this.mailContinue = mailContinue;
    }

    public String getMailAdress() {
        return mailAdress;
    }

    public void setMailAdress(String mailAdress) {
        this.mailAdress = mailAdress;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
