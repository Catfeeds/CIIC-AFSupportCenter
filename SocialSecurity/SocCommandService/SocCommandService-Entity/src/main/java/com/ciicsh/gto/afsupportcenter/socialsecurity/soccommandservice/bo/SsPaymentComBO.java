package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.bo;

import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.SsPaymentCom;

/**
 * <p>
 * 本地社保中，中智公司与社保局的对账单（各一条记录）
 * </p>
 *
 * @author wengxk
 * @since 2017-12-08
 */
public class SsPaymentComBO extends SsPaymentCom {

    private static final long serialVersionUID = 1L;


    /**
     * 出账批号
     */
    private String paymentBatchNum;

    /**
     * 账户类型：1:中智大库 2中智外包 3独立户
     */
    private Integer ssAccountType;

    /**
     * 公司名(社保账户名)
     */
    private String comAccountName;

    /**
     * 客户名
     */
    private String title;

    /**
     * 最小支付年月
     */
    private String paymentMonthMin;

    /**
     * 最大支付年月
     */
    private String paymentMonthMax;
    private String ssAccount;

    public String getSsAccount() {
        return ssAccount;
    }

    public void setSsAccount(String ssAccount) {
        this.ssAccount = ssAccount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPaymentMonthMin() {
        return paymentMonthMin;
    }

    public void setPaymentMonthMin(String paymentMonthMin) {
        this.paymentMonthMin = paymentMonthMin;
    }

    public String getPaymentMonthMax() {
        return paymentMonthMax;
    }

    public void setPaymentMonthMax(String paymentMonthMax) {
        this.paymentMonthMax = paymentMonthMax;
    }

    public Integer getSsAccountType() {
        return ssAccountType;
    }

    public void setSsAccountType(Integer ssAccountType) {
        this.ssAccountType = ssAccountType;
    }

    public String getPaymentBatchNum() {
        return paymentBatchNum;
    }

    public void setPaymentBatchNum(String paymentBatchNum) {
        this.paymentBatchNum = paymentBatchNum;
    }

    public String getComAccountName() {
        return comAccountName;
    }

    public void setComAccountName(String comAccountName) {
        this.comAccountName = comAccountName;
    }
}
