package com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.api.dto.payment;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <p>
 * 本地社保中，中智公司与社保局的对账单（各一条记录）
 * </p>
 *
 * @author wengxk
 * @since 2017-12-08ew
 */
public class SsPaymentComDTO {

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
    /**
     * 记录Id
     */
    private Long paymentComId;
    private Long paymentId;
    /**
     * 大库、独立库账户Id
     */
    private Long comAccountId;
    /**
     * 客户Id,能关联到客户和社保账户,
     比如欧莱雅10家分公司分开支付
     */
    private String companyId;
    /**
     * 支付年月,格式yyyyMM
     */
    private String paymentMonth;
    /**
     * 应缴纳金额
     */
    private BigDecimal oughtAmount;
    /**
     * 申请支付的金额合计,=TotalComPayAmount+TotalEmpPayAmount+extra_amount
     */
    private BigDecimal totalPayAmount;
    /**
     * 申请支付的公司部分的总金额
     */
    private BigDecimal totalComPayAmount;
    /**
     * 申请支付的雇员部分的总金额
     */
    private BigDecimal totalEmpPayAmount;
    /**
     * 退账抵扣费用
     */
    private BigDecimal refundDeducted;
    /**
     * 调整抵扣费用
     */
    private BigDecimal adjustDeducted;
    /**
     * 额外金
     */
    private BigDecimal extraAmount;
    /**
     * 加入批次人的系统用户
     */
    private String joinPaymentUser;
    /**
     * 加入批次日期
     */
    private LocalDate joinPaymentDate;
    /**
     * 抵扣费用是否纳入支付申请: 0-不纳入 1-纳入
     */
    private Integer ifDeductedIntoPay;
    /**
     * 申请备注
     */
    private String remark;
    /**
     * 财务实际支付日期
     */
    private LocalDate actualPaymentDate;
    /**
     * 支付总人头数
     */
    private Integer empCount;
    /**
     * 支付状态: 1,未到帐  2,无需支付  3 ,可付 4,申请中  5,内部审批批退 6,已申请到财务部  7,财务部批退  8,财务部支付成功
     */
    private Integer paymentState;
    /**
     * 是否可用
     */
    private Boolean isActive;
    /**
     * 创建时间
     */
    private LocalDateTime createdTime;
    /**
     * 最后更新时间
     */
    private LocalDateTime modifiedTime;
    /**
     * 创建者登录名
     */
    private String createdBy;
    /**
     * 修改者登录名
     */
    private String modifiedBy;


    public Long getPaymentComId() {
        return paymentComId;
    }

    public void setPaymentComId(Long paymentComId) {
        this.paymentComId = paymentComId;
    }

    public Long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }

    public Long getComAccountId() {
        return comAccountId;
    }

    public void setComAccountId(Long comAccountId) {
        this.comAccountId = comAccountId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getPaymentMonth() {
        return paymentMonth;
    }

    public void setPaymentMonth(String paymentMonth) {
        this.paymentMonth = paymentMonth;
    }

    public BigDecimal getOughtAmount() {
        return oughtAmount;
    }

    public void setOughtAmount(BigDecimal oughtAmount) {
        this.oughtAmount = oughtAmount;
    }

    public BigDecimal getTotalPayAmount() {
        return totalPayAmount;
    }

    public void setTotalPayAmount(BigDecimal totalPayAmount) {
        this.totalPayAmount = totalPayAmount;
    }

    public BigDecimal getTotalComPayAmount() {
        return totalComPayAmount;
    }

    public void setTotalComPayAmount(BigDecimal totalComPayAmount) {
        this.totalComPayAmount = totalComPayAmount;
    }

    public BigDecimal getTotalEmpPayAmount() {
        return totalEmpPayAmount;
    }

    public void setTotalEmpPayAmount(BigDecimal totalEmpPayAmount) {
        this.totalEmpPayAmount = totalEmpPayAmount;
    }

    public BigDecimal getRefundDeducted() {
        return refundDeducted;
    }

    public void setRefundDeducted(BigDecimal refundDeducted) {
        this.refundDeducted = refundDeducted;
    }

    public BigDecimal getAdjustDeducted() {
        return adjustDeducted;
    }

    public void setAdjustDeducted(BigDecimal adjustDeducted) {
        this.adjustDeducted = adjustDeducted;
    }

    public BigDecimal getExtraAmount() {
        return extraAmount;
    }

    public void setExtraAmount(BigDecimal extraAmount) {
        this.extraAmount = extraAmount;
    }

    public String getJoinPaymentUser() {
        return joinPaymentUser;
    }

    public void setJoinPaymentUser(String joinPaymentUser) {
        this.joinPaymentUser = joinPaymentUser;
    }

    public LocalDate getJoinPaymentDate() {
        return joinPaymentDate;
    }

    public void setJoinPaymentDate(LocalDate joinPaymentDate) {
        this.joinPaymentDate = joinPaymentDate;
    }

    public Integer getIfDeductedIntoPay() {
        return ifDeductedIntoPay;
    }

    public void setIfDeductedIntoPay(Integer ifDeductedIntoPay) {
        this.ifDeductedIntoPay = ifDeductedIntoPay;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public LocalDate getActualPaymentDate() {
        return actualPaymentDate;
    }

    public void setActualPaymentDate(LocalDate actualPaymentDate) {
        this.actualPaymentDate = actualPaymentDate;
    }

    public Integer getEmpCount() {
        return empCount;
    }

    public void setEmpCount(Integer empCount) {
        this.empCount = empCount;
    }

    public Integer getPaymentState() {
        return paymentState;
    }

    public void setPaymentState(Integer paymentState) {
        this.paymentState = paymentState;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public LocalDateTime getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(LocalDateTime modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
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
