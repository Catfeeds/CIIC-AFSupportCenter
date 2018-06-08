package com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo;


import cn.afterturn.easypoi.excel.annotation.Excel;

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
public class SsPaymentComBO {

    private static final long serialVersionUID = 1L;
    /**
     * 出账批号
     */

    private String paymentBatchNum;
    /**
     * 账户类型：1:中智大库 2中智外包 3独立户
     */
    @Excel(name = "社保账户类型", orderNum = "8",replace = {"中智大库_1","中智外包_2","独立户_3"})
    private Integer ssAccountType;

    /**
     * 公司名(社保账户名)
     */
    private String comAccountName;
    @Excel(name = "企业社保账号", orderNum = "7")
    private String ssAccount;

    /**
     * 客户名
     */
    @Excel(name = "客户名称", orderNum = "3")
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
    @Excel(name = "客户编号", orderNum = "2")
    private String companyId;
    /**
     * 支付年月,格式yyyyMM
     */
    @Excel(name = "支付年月", orderNum = "1")
    private String paymentMonth;
    /**
     * 应缴纳金额
     */
    @Excel(name = "应缴纳金额", orderNum = "5")
    private BigDecimal oughtAmount;
    /**
     * 申请支付的金额合计,=TotalComPayAmount+TotalEmpPayAmount+extra_amount
     */
    @Excel(name = "申请支付总金额", orderNum = "6")
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
    @Excel(name = "退账抵扣费用", orderNum = "10")
    private BigDecimal refundDeducted;
    /**
     * 调整抵扣费用
     */
    @Excel(name = "调整抵扣费用", orderNum = "11")
    private BigDecimal adjustDeducted;
    /**
     * 额外金
     */
    @Excel(name = "额外金", orderNum = "9")
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
    @Excel(name = "额外金备注", orderNum = "13")
    private String remark;
    /**
     * 财务实际支付日期
     */
    @Excel(name = "财务实际支付日期", orderNum = "12")
    private LocalDate actualPaymentDate;
    /**
     * 支付总人头数
     */
    private Integer empCount;
    /**
     * 支付状态: 1,未到帐  2,无需支付  3 ,可付 4,申请中  5,内部审批批退 6,已申请到财务部  7,财务部批退  8,财务部支付成功
     */
    @Excel(name = "支付状态", orderNum = "4",replace = {"未到帐_1","无需支付_2","可付_3","申请中_4","内部审批批退_5","已申请到财务部_6","财务部批退_7","财务部支付成功_8"})
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

    private LocalDate financePaymentDate;

    //客服中心
    private Integer serviceCenterValue;

    public Integer getServiceCenterValue() {
        return serviceCenterValue;
    }

    public void setServiceCenterValue(Integer serviceCenterValue) {
        this.serviceCenterValue = serviceCenterValue;
    }

    public LocalDate getFinancePaymentDate() {
        return financePaymentDate;
    }

    public void setFinancePaymentDate(LocalDate financePaymentDate) {
        this.financePaymentDate = financePaymentDate;
    }

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

    public String getSsAccount() {
        return ssAccount;
    }

    public void setSsAccount(String ssAccount) {
        this.ssAccount = ssAccount;
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
