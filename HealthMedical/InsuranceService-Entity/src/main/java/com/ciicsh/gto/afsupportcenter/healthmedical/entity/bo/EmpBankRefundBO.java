package com.ciicsh.gto.afsupportcenter.healthmedical.entity.bo;

import java.math.BigDecimal;

/**
 * <p>
 * 雇员付款退回
 * </p>
 *
 * @author chenpb
 * @since 2018-02-01
 */
public class EmpBankRefundBO {

    /** 付款申请记录编号 */
    private Integer applyId;
    /** 业务ID */
    private Integer businessId;
    /** 公司编号 */
    private String companyId;
    /** 雇员编号 */
    private String employeeId;
    /** 客户经理 */
    private String customerManager;
    /** 服务中心 */
    private String serviceCenter;
    /** 银行卡ID */
    private String bankcardId;
    /** 支付金额 */
    private BigDecimal payAmount;
    /** 退票原因 */
    private String remark;

    public Integer getApplyId() {
        return applyId;
    }

    public void setApplyId(Integer applyId) {
        this.applyId = applyId;
    }

    public Integer getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Integer businessId) {
        this.businessId = businessId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getCustomerManager() {
        return customerManager;
    }

    public void setCustomerManager(String customerManager) {
        this.customerManager = customerManager;
    }

    public String getServiceCenter() {
        return serviceCenter;
    }

    public void setServiceCenter(String serviceCenter) {
        this.serviceCenter = serviceCenter;
    }

    public String getBankcardId() {
        return bankcardId;
    }

    public void setBankcardId(String bankcardId) {
        this.bankcardId = bankcardId;
    }

    public BigDecimal getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "EmpBankRefundBO{" +
            "applyId=" + applyId +
            ", businessId=" + businessId +
            ", companyId='" + companyId + '\'' +
            ", employeeId='" + employeeId + '\'' +
            ", customerManager='" + customerManager + '\'' +
            ", serviceSenter='" + serviceCenter + '\'' +
            ", bankcardId='" + bankcardId + '\'' +
            ", payAmount=" + payAmount +
            ", remark='" + remark + '\'' +
            '}';
    }
}
