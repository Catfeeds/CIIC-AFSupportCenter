package com.ciicsh.gto.afsupportcenter.socialsecurity.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.*;

/**
 * 本地社保应付金额交易记录主表,每月1号生成此表记录，用户也可以人工生成此表记录
 */
@Table(name = "SS_Payment")
public class SsPayment implements Serializable {
    /**
     * 记录Id
     */
    @Id
    @Column(name = "PaymentId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;

    /**
     * 大库、独立库账户Id
     */
    @Column(name = "ComAccountId")
    private String comAccountId;

    /**
     * 客户Id,能关联到客户和社保账户,
            比如欧莱雅10家分公司分开支付
     */
    @Column(name = "CompanyId")
    private String companyId;

    /**
     * 支付年月,格式yyyyMM
     */
    @Column(name = "PaymentMonth")
    private String paymentMonth;

    /**
     * 申请支付的金额合计,=TotalComPayAmount+TotalEmpPayAmount
     */
    @Column(name = "TotalPayAmount")
    private BigDecimal totalPayAmount;

    /**
     * 申请支付的公司部分的总金额
     */
    @Column(name = "TotalComPayAmount")
    private BigDecimal totalComPayAmount;

    /**
     * 申请支付的雇员部分的总金额
     */
    @Column(name = "TotalEmpPayAmount")
    private BigDecimal totalEmpPayAmount;

    /**
     * 额外金
     */
    @Column(name = "extraAmount")
    private BigDecimal extraAmount;

    /**
     * 申请人的系统用户ID
     */
    @Column(name = "ApplierID")
    private String applierID;

    /**
     * 申请日期
     */
    @Column(name = "ApplicationDate")
    private LocalDate applicationDate;

    /**
     * 申请备注
     */
    @Column(name = "Remark")
    private String remark;

    /**
     * 财务实际支付日期
     */
    @Column(name = "ActualPaymentDate")
    private LocalDate actualPaymentDate;

    /**
     * 支付总人头数
     */
    @Column(name = "EmpHeadCount")
    private Integer empHeadCount;

    /**
     * 支付状态: 0-已创建 1 申请中 2完成支付 3 批退
     */
    @Column(name = "PaymentStatus")
    private Byte paymentStatus;

    /**
     * 是否可用
     */
    @Column(name = "IsActive")
    private Boolean isActive;

    /**
     * 创建时间
     */
    @Column(name = "DataChange_CreateTime")
    private LocalDateTime dataChangeCreateTime;

    /**
     * 最后更新时间
     */
    @Column(name = "DataChange_LastTime")
    private LocalDateTime dataChangeLastTime;

    /**
     * 创建者登录名
     */
    @Column(name = "CreatedBy")
    private String createdBy;

    /**
     * 修改者登录名
     */
    @Column(name = "ModifiedBy")
    private String modifiedBy;

    private static final long serialVersionUID = 1L;

    /**
     * 获取记录Id
     *
     * @return PaymentId - 记录Id
     */
    public Long getPaymentId() {
        return paymentId;
    }

    /**
     * 设置记录Id
     *
     * @param paymentId 记录Id
     */
    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }

    /**
     * 获取大库、独立库账户Id
     *
     * @return ComAccountId - 大库、独立库账户Id
     */
    public String getComAccountId() {
        return comAccountId;
    }

    /**
     * 设置大库、独立库账户Id
     *
     * @param comAccountId 大库、独立库账户Id
     */
    public void setComAccountId(String comAccountId) {
        this.comAccountId = comAccountId == null ? null : comAccountId.trim();
    }

    /**
     * 获取客户Id,能关联到客户和社保账户,
            比如欧莱雅10家分公司分开支付
     *
     * @return CompanyId - 客户Id,能关联到客户和社保账户,
            比如欧莱雅10家分公司分开支付
     */
    public String getCompanyId() {
        return companyId;
    }

    /**
     * 设置客户Id,能关联到客户和社保账户,
            比如欧莱雅10家分公司分开支付
     *
     * @param companyId 客户Id,能关联到客户和社保账户,
            比如欧莱雅10家分公司分开支付
     */
    public void setCompanyId(String companyId) {
        this.companyId = companyId == null ? null : companyId.trim();
    }

    /**
     * 获取支付年月,格式yyyyMM
     *
     * @return PaymentMonth - 支付年月,格式yyyyMM
     */
    public String getPaymentMonth() {
        return paymentMonth;
    }

    /**
     * 设置支付年月,格式yyyyMM
     *
     * @param paymentMonth 支付年月,格式yyyyMM
     */
    public void setPaymentMonth(String paymentMonth) {
        this.paymentMonth = paymentMonth == null ? null : paymentMonth.trim();
    }

    /**
     * 获取申请支付的金额合计,=TotalComPayAmount+TotalEmpPayAmount
     *
     * @return TotalPayAmount - 申请支付的金额合计,=TotalComPayAmount+TotalEmpPayAmount
     */
    public BigDecimal getTotalPayAmount() {
        return totalPayAmount;
    }

    /**
     * 设置申请支付的金额合计,=TotalComPayAmount+TotalEmpPayAmount
     *
     * @param totalPayAmount 申请支付的金额合计,=TotalComPayAmount+TotalEmpPayAmount
     */
    public void setTotalPayAmount(BigDecimal totalPayAmount) {
        this.totalPayAmount = totalPayAmount;
    }

    /**
     * 获取申请支付的公司部分的总金额
     *
     * @return TotalComPayAmount - 申请支付的公司部分的总金额
     */
    public BigDecimal getTotalComPayAmount() {
        return totalComPayAmount;
    }

    /**
     * 设置申请支付的公司部分的总金额
     *
     * @param totalComPayAmount 申请支付的公司部分的总金额
     */
    public void setTotalComPayAmount(BigDecimal totalComPayAmount) {
        this.totalComPayAmount = totalComPayAmount;
    }

    /**
     * 获取申请支付的雇员部分的总金额
     *
     * @return TotalEmpPayAmount - 申请支付的雇员部分的总金额
     */
    public BigDecimal getTotalEmpPayAmount() {
        return totalEmpPayAmount;
    }

    /**
     * 设置申请支付的雇员部分的总金额
     *
     * @param totalEmpPayAmount 申请支付的雇员部分的总金额
     */
    public void setTotalEmpPayAmount(BigDecimal totalEmpPayAmount) {
        this.totalEmpPayAmount = totalEmpPayAmount;
    }

    /**
     * 获取额外金
     *
     * @return extraAmount - 额外金
     */
    public BigDecimal getExtraAmount() {
        return extraAmount;
    }

    /**
     * 设置额外金
     *
     * @param extraAmount 额外金
     */
    public void setExtraAmount(BigDecimal extraAmount) {
        this.extraAmount = extraAmount;
    }

    /**
     * 获取申请人的系统用户ID
     *
     * @return ApplierID - 申请人的系统用户ID
     */
    public String getApplierID() {
        return applierID;
    }

    /**
     * 设置申请人的系统用户ID
     *
     * @param applierID 申请人的系统用户ID
     */
    public void setApplierID(String applierID) {
        this.applierID = applierID == null ? null : applierID.trim();
    }

    /**
     * 获取申请日期
     *
     * @return ApplicationDate - 申请日期
     */
    public LocalDate getApplicationDate() {
        return applicationDate;
    }

    /**
     * 设置申请日期
     *
     * @param applicationDate 申请日期
     */
    public void setApplicationDate(LocalDate applicationDate) {
        this.applicationDate = applicationDate;
    }

    /**
     * 获取申请备注
     *
     * @return Remark - 申请备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置申请备注
     *
     * @param remark 申请备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * 获取财务实际支付日期
     *
     * @return ActualPaymentDate - 财务实际支付日期
     */
    public LocalDate getActualPaymentDate() {
        return actualPaymentDate;
    }

    /**
     * 设置财务实际支付日期
     *
     * @param actualPaymentDate 财务实际支付日期
     */
    public void setActualPaymentDate(LocalDate actualPaymentDate) {
        this.actualPaymentDate = actualPaymentDate;
    }

    /**
     * 获取支付总人头数
     *
     * @return EmpHeadCount - 支付总人头数
     */
    public Integer getEmpHeadCount() {
        return empHeadCount;
    }

    /**
     * 设置支付总人头数
     *
     * @param empHeadCount 支付总人头数
     */
    public void setEmpHeadCount(Integer empHeadCount) {
        this.empHeadCount = empHeadCount;
    }

    /**
     * 获取支付状态: 0-已创建 1 申请中 2完成支付 3 批退
     *
     * @return PaymentStatus - 支付状态: 0-已创建 1 申请中 2完成支付 3 批退
     */
    public Byte getPaymentStatus() {
        return paymentStatus;
    }

    /**
     * 设置支付状态: 0-已创建 1 申请中 2完成支付 3 批退
     *
     * @param paymentStatus 支付状态: 0-已创建 1 申请中 2完成支付 3 批退
     */
    public void setPaymentStatus(Byte paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    /**
     * 获取是否可用
     *
     * @return IsActive - 是否可用
     */
    public Boolean getIsActive() {
        return isActive;
    }

    /**
     * 设置是否可用
     *
     * @param isActive 是否可用
     */
    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    /**
     * 获取创建时间
     *
     * @return DataChange_CreateTime - 创建时间
     */
    public LocalDateTime getDataChangeCreateTime() {
        return dataChangeCreateTime;
    }

    /**
     * 设置创建时间
     *
     * @param dataChangeCreateTime 创建时间
     */
    public void setDataChangeCreateTime(LocalDateTime dataChangeCreateTime) {
        this.dataChangeCreateTime = dataChangeCreateTime;
    }

    /**
     * 获取最后更新时间
     *
     * @return DataChange_LastTime - 最后更新时间
     */
    public LocalDateTime getDataChangeLastTime() {
        return dataChangeLastTime;
    }

    /**
     * 设置最后更新时间
     *
     * @param dataChangeLastTime 最后更新时间
     */
    public void setDataChangeLastTime(LocalDateTime dataChangeLastTime) {
        this.dataChangeLastTime = dataChangeLastTime;
    }

    /**
     * 获取创建者登录名
     *
     * @return CreatedBy - 创建者登录名
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * 设置创建者登录名
     *
     * @param createdBy 创建者登录名
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy == null ? null : createdBy.trim();
    }

    /**
     * 获取修改者登录名
     *
     * @return ModifiedBy - 修改者登录名
     */
    public String getModifiedBy() {
        return modifiedBy;
    }

    /**
     * 设置修改者登录名
     *
     * @param modifiedBy 修改者登录名
     */
    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy == null ? null : modifiedBy.trim();
    }
}