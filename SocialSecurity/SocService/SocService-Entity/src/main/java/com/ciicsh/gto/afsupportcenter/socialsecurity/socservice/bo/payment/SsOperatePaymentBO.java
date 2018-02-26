package com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo.payment;

/**
 * <p>
 * 本地社保中，中智公司与社保局的对账单（各一条记录）
 * </p>
 */
public class SsOperatePaymentBO {

    private static final long serialVersionUID = 1L;

    /**
     * 批次ID
     */
    private Long paymentId;

    /**
     * 申请备注
     */
    private String applyRemark;
    /**
     * 批退备注
     */
    private String rejectionRemark;

    public Long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }

    public String getApplyRemark() {
        return applyRemark;
    }

    public void setApplyRemark(String applyRemark) {
        this.applyRemark = applyRemark;
    }

    public String getRejectionRemark() {
        return rejectionRemark;
    }

    public void setRejectionRemark(String rejectionRemark) {
        this.rejectionRemark = rejectionRemark;
    }
}
