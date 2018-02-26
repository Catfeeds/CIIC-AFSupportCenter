package com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo.payment;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 本地社保中，中智公司与社保局的对账单（各一条记录）
 * </p>
 *
 * @author wengxk
 * @since 2017-12-08
 */
public class SsPaymentBO {

    private static final long serialVersionUID = 1L;

    /**
     * 最小支付年月
     */
    private String paymentMonthMin;

    /**
     * 最大支付年月
     */
    private String paymentMonthMax;

    /**
     * 状态List
     */
    private List<Integer> paymentStateList;


    private Long paymentId;
    /**
     * 出账批号
     */
    private String paymentBatchNum;
    /**
     * 申请总金额
     */
    private BigDecimal totalApplicationAmount;
    /**
     * 支付年月YYYYMM
     */
    private String paymentMonth;
    /**
     * 支付状态: 3 ,可付(默认)   4,申请中  5,内部审批批退 6,已申请到财务部  7,财务部批退  8,财务部支付成功
     */
    private Integer paymentState;
    /**
     * 制单人
     */
    private String createPaymentUser;
    /**
     * 制单日期
     */
    private LocalDate createPaymentDate;
    /**
     * 财务支付日期
     */
    private LocalDate financePaymentDate;
    /**
     * 1 大库、2 外包、3独立户
     */
    private Integer accountType;
    /**
     * 总雇员数
     */
    private Integer totalEmpCount;
    /**
     * 账户总数
     */
    private Integer totalAccount;
    /**
     * 客户总数
     */
    private Integer totalCom;
    /**
     * 申请备注
     */
    private String applyRemark;
    /**
     * 批退备注
     */
    private String rejectionRemark;
    /**
     * 批退历史备份
     [
     {
     总雇员数：
     账户总数：
     客户总数：
     批退备注：
     批退人：
     批退时间：
     },
     ]
     */
    private String rejectionHis;
    /**
     * 申请人
     */
    private String requestUser;
    /**
     * 申请日期
     */
    private LocalDate requestDate;
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



    public String getPaymentBatchNum() {
        return paymentBatchNum;
    }

    public void setPaymentBatchNum(String paymentBatchNum) {
        this.paymentBatchNum = paymentBatchNum;
    }

    public BigDecimal getTotalApplicationAmount() {
        return totalApplicationAmount;
    }

    public void setTotalApplicationAmount(BigDecimal totalApplicationAmount) {
        this.totalApplicationAmount = totalApplicationAmount;
    }

    public String getPaymentMonth() {
        return paymentMonth;
    }

    public void setPaymentMonth(String paymentMonth) {
        this.paymentMonth = paymentMonth;
    }

    public Integer getPaymentState() {
        return paymentState;
    }

    public void setPaymentState(Integer paymentState) {
        this.paymentState = paymentState;
    }

    public String getCreatePaymentUser() {
        return createPaymentUser;
    }

    public void setCreatePaymentUser(String createPaymentUser) {
        this.createPaymentUser = createPaymentUser;
    }

    public LocalDate getCreatePaymentDate() {
        return createPaymentDate;
    }

    public void setCreatePaymentDate(LocalDate createPaymentDate) {
        this.createPaymentDate = createPaymentDate;
    }

    public LocalDate getFinancePaymentDate() {
        return financePaymentDate;
    }

    public void setFinancePaymentDate(LocalDate financePaymentDate) {
        this.financePaymentDate = financePaymentDate;
    }

    public Integer getAccountType() {
        return accountType;
    }

    public void setAccountType(Integer accountType) {
        this.accountType = accountType;
    }

    public Integer getTotalEmpCount() {
        return totalEmpCount;
    }

    public void setTotalEmpCount(Integer totalEmpCount) {
        this.totalEmpCount = totalEmpCount;
    }

    public Integer getTotalAccount() {
        return totalAccount;
    }

    public void setTotalAccount(Integer totalAccount) {
        this.totalAccount = totalAccount;
    }

    public Integer getTotalCom() {
        return totalCom;
    }

    public void setTotalCom(Integer totalCom) {
        this.totalCom = totalCom;
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

    public String getRejectionHis() {
        return rejectionHis;
    }

    public void setRejectionHis(String rejectionHis) {
        this.rejectionHis = rejectionHis;
    }

    public String getRequestUser() {
        return requestUser;
    }

    public void setRequestUser(String requestUser) {
        this.requestUser = requestUser;
    }

    public LocalDate getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(LocalDate requestDate) {
        this.requestDate = requestDate;
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

    public Long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }

    public List<Integer> getPaymentStateList() {
        return paymentStateList;
    }

    public void setPaymentStateList(List<Integer> paymentStateList) {
        this.paymentStateList = paymentStateList;
    }
}
