package com.ciicsh.gto.afsupportcenter.socialsecurity.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.*;

/**
 * 企业社保账户信息表
企业社保账户分类 : 大库（中智大库、外包库）、独立户
 */
@Table(name = "SS_ComAccount")
public class SsComAccount implements Serializable {
    /**
     * 企业社保账户Id
     */
    @Id
    @Column(name = "ComAccountId")
    private Long comAccountId;

    /**
     * EntityId
     */
    @Column(name = "EntityId")
    private String entityId;

    /**
     * 多租户Id
     */
    @Column(name = "CustomerId")
    private String customerId;

    /**
     * 供应商Id, 取自gtosupplierdb.SUP_Supplier
     */
    @Column(name = "SupplierId")
    private String supplierId;

    /**
     * 账户类型：1:中智大库 2中智外包 3独立户
     */
    @Column(name = "SSAccountType")
    private Boolean SSAccountType;

    /**
     * 参保户登记码（账号）
     */
    @Column(name = "SSAccount")
    private String SSAccount;

    /**
     * 银行账号(牡丹卡号)
     */
    @Column(name = "BankAccount")
    private String bankAccount;

    /**
     * 养老金账户公司名称
     */
    @Column(name = "ComAccountName")
    private String comAccountName;

    /**
     * 结算区县(社保局所在上海地区)
     */
    @Column(name = "SettlementArea")
    private String settlementArea;

    /**
     * 付款银行(一般情况是工商银行)
     */
    @Column(name = "PaymentBank")
    private String paymentBank;

    /**
     * 付款方式：1-
            我司付款账单到他司  
            2-自己付款账单到我司  
            3-自己付款账单到他司  
            4-我司付款账单到我司  5-
            垫付
     */
    @Column(name = "PaymenWay")
    private Boolean paymenWay;

    /**
     * 客户交付社保费用给中智的截止日期
     */
    @Column(name = "ExpireDate")
    private LocalDate expireDate;

    /**
     * 养老金独立开户用户名（使用U盾登陆的用户名）
     */
    @Column(name = "SSUserName")
    private String SSUserName;

    /**
     * 养老金独立开户密码（使用U盾登陆的密码）
     */
    @Column(name = "SSPwd")
    private String SSPwd;

    /**
     * 初期余额
     */
    @Column(name = "InitialBalance")
    private BigDecimal initialBalance;

    /**
     * 初期欠费
     */
    @Column(name = "InitialDebt")
    private BigDecimal initialDebt;

    /**
     * 来源地:1-新开；2-AF转入；3-其他供应商转入
     */
    @Column(name = "OriginPlace")
    private Boolean originPlace;

    /**
     * 来源地备注
     */
    @Column(name = "OriginPlaceRemark")
    private String originPlaceRemark;

    /**
     * 查询账号
     */
    @Column(name = "QueryAccount")
    private String queryAccount;

    /**
     * 交予方式:1-交客服  2-传真  3-邮寄
     */
    @Column(name = "DeliverWay")
    private String deliverWay;

    /**
     * 交予方式备注
     */
    @Column(name = "DeliverWayRemark")
    private String deliverWayRemark;

    /**
     * 给凭证时间
     */
    @Column(name = "ProvideCertificateTime")
    private LocalDate provideCertificateTime;

    /**
     * 变更时间
     */
    @Column(name = "ChangeTime")
    private LocalDateTime changeTime;

    /**
     * 收到日期
     */
    @Column(name = "ReceiveDate")
    private LocalDate receiveDate;

    /**
     * 转入日期
     */
    @Column(name = "IntoDate")
    private LocalDate intoDate;

    /**
     * 发出材料:正式通知书、预生成通知书、收据、银行对账单、汇总表、实时表
     */
    @Column(name = "DispatchMaterial")
    private String dispatchMaterial;

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
     * 获取企业社保账户Id
     *
     * @return ComAccountId - 企业社保账户Id
     */
    public Long getComAccountId() {
        return comAccountId;
    }

    /**
     * 设置企业社保账户Id
     *
     * @param comAccountId 企业社保账户Id
     */
    public void setComAccountId(Long comAccountId) {
        this.comAccountId = comAccountId;
    }

    /**
     * 获取EntityId
     *
     * @return EntityId - EntityId
     */
    public String getEntityId() {
        return entityId;
    }

    /**
     * 设置EntityId
     *
     * @param entityId EntityId
     */
    public void setEntityId(String entityId) {
        this.entityId = entityId == null ? null : entityId.trim();
    }

    /**
     * 获取多租户Id
     *
     * @return CustomerId - 多租户Id
     */
    public String getCustomerId() {
        return customerId;
    }

    /**
     * 设置多租户Id
     *
     * @param customerId 多租户Id
     */
    public void setCustomerId(String customerId) {
        this.customerId = customerId == null ? null : customerId.trim();
    }

    /**
     * 获取供应商Id, 取自gtosupplierdb.SUP_Supplier
     *
     * @return SupplierId - 供应商Id, 取自gtosupplierdb.SUP_Supplier
     */
    public String getSupplierId() {
        return supplierId;
    }

    /**
     * 设置供应商Id, 取自gtosupplierdb.SUP_Supplier
     *
     * @param supplierId 供应商Id, 取自gtosupplierdb.SUP_Supplier
     */
    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId == null ? null : supplierId.trim();
    }

    /**
     * 获取账户类型：1:中智大库 2中智外包 3独立户
     *
     * @return SSAccountType - 账户类型：1:中智大库 2中智外包 3独立户
     */
    public Boolean getSSAccountType() {
        return SSAccountType;
    }

    /**
     * 设置账户类型：1:中智大库 2中智外包 3独立户
     *
     * @param SSAccountType 账户类型：1:中智大库 2中智外包 3独立户
     */
    public void setSSAccountType(Boolean SSAccountType) {
        this.SSAccountType = SSAccountType;
    }

    /**
     * 获取参保户登记码（账号）
     *
     * @return SSAccount - 参保户登记码（账号）
     */
    public String getSSAccount() {
        return SSAccount;
    }

    /**
     * 设置参保户登记码（账号）
     *
     * @param SSAccount 参保户登记码（账号）
     */
    public void setSSAccount(String SSAccount) {
        this.SSAccount = SSAccount == null ? null : SSAccount.trim();
    }

    /**
     * 获取银行账号(牡丹卡号)
     *
     * @return BankAccount - 银行账号(牡丹卡号)
     */
    public String getBankAccount() {
        return bankAccount;
    }

    /**
     * 设置银行账号(牡丹卡号)
     *
     * @param bankAccount 银行账号(牡丹卡号)
     */
    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount == null ? null : bankAccount.trim();
    }

    /**
     * 获取养老金账户公司名称
     *
     * @return ComAccountName - 养老金账户公司名称
     */
    public String getComAccountName() {
        return comAccountName;
    }

    /**
     * 设置养老金账户公司名称
     *
     * @param comAccountName 养老金账户公司名称
     */
    public void setComAccountName(String comAccountName) {
        this.comAccountName = comAccountName == null ? null : comAccountName.trim();
    }

    /**
     * 获取结算区县(社保局所在上海地区)
     *
     * @return SettlementArea - 结算区县(社保局所在上海地区)
     */
    public String getSettlementArea() {
        return settlementArea;
    }

    /**
     * 设置结算区县(社保局所在上海地区)
     *
     * @param settlementArea 结算区县(社保局所在上海地区)
     */
    public void setSettlementArea(String settlementArea) {
        this.settlementArea = settlementArea == null ? null : settlementArea.trim();
    }

    /**
     * 获取付款银行(一般情况是工商银行)
     *
     * @return PaymentBank - 付款银行(一般情况是工商银行)
     */
    public String getPaymentBank() {
        return paymentBank;
    }

    /**
     * 设置付款银行(一般情况是工商银行)
     *
     * @param paymentBank 付款银行(一般情况是工商银行)
     */
    public void setPaymentBank(String paymentBank) {
        this.paymentBank = paymentBank == null ? null : paymentBank.trim();
    }

    /**
     * 获取付款方式：1-
            我司付款账单到他司  
            2-自己付款账单到我司  
            3-自己付款账单到他司  
            4-我司付款账单到我司  5-
            垫付
     *
     * @return PaymenWay - 付款方式：1-
            我司付款账单到他司  
            2-自己付款账单到我司  
            3-自己付款账单到他司  
            4-我司付款账单到我司  5-
            垫付
     */
    public Boolean getPaymenWay() {
        return paymenWay;
    }

    /**
     * 设置付款方式：1-
            我司付款账单到他司  
            2-自己付款账单到我司  
            3-自己付款账单到他司  
            4-我司付款账单到我司  5-
            垫付
     *
     * @param paymenWay 付款方式：1-
            我司付款账单到他司  
            2-自己付款账单到我司  
            3-自己付款账单到他司  
            4-我司付款账单到我司  5-
            垫付
     */
    public void setPaymenWay(Boolean paymenWay) {
        this.paymenWay = paymenWay;
    }

    /**
     * 获取客户交付社保费用给中智的截止日期
     *
     * @return ExpireDate - 客户交付社保费用给中智的截止日期
     */
    public LocalDate getExpireDate() {
        return expireDate;
    }

    /**
     * 设置客户交付社保费用给中智的截止日期
     *
     * @param expireDate 客户交付社保费用给中智的截止日期
     */
    public void setExpireDate(LocalDate expireDate) {
        this.expireDate = expireDate;
    }

    /**
     * 获取养老金独立开户用户名（使用U盾登陆的用户名）
     *
     * @return SSUserName - 养老金独立开户用户名（使用U盾登陆的用户名）
     */
    public String getSSUserName() {
        return SSUserName;
    }

    /**
     * 设置养老金独立开户用户名（使用U盾登陆的用户名）
     *
     * @param SSUserName 养老金独立开户用户名（使用U盾登陆的用户名）
     */
    public void setSSUserName(String SSUserName) {
        this.SSUserName = SSUserName == null ? null : SSUserName.trim();
    }

    /**
     * 获取养老金独立开户密码（使用U盾登陆的密码）
     *
     * @return SSPwd - 养老金独立开户密码（使用U盾登陆的密码）
     */
    public String getSSPwd() {
        return SSPwd;
    }

    /**
     * 设置养老金独立开户密码（使用U盾登陆的密码）
     *
     * @param SSPwd 养老金独立开户密码（使用U盾登陆的密码）
     */
    public void setSSPwd(String SSPwd) {
        this.SSPwd = SSPwd == null ? null : SSPwd.trim();
    }

    /**
     * 获取初期余额
     *
     * @return InitialBalance - 初期余额
     */
    public BigDecimal getInitialBalance() {
        return initialBalance;
    }

    /**
     * 设置初期余额
     *
     * @param initialBalance 初期余额
     */
    public void setInitialBalance(BigDecimal initialBalance) {
        this.initialBalance = initialBalance;
    }

    /**
     * 获取初期欠费
     *
     * @return InitialDebt - 初期欠费
     */
    public BigDecimal getInitialDebt() {
        return initialDebt;
    }

    /**
     * 设置初期欠费
     *
     * @param initialDebt 初期欠费
     */
    public void setInitialDebt(BigDecimal initialDebt) {
        this.initialDebt = initialDebt;
    }

    /**
     * 获取来源地:1-新开；2-AF转入；3-其他供应商转入
     *
     * @return OriginPlace - 来源地:1-新开；2-AF转入；3-其他供应商转入
     */
    public Boolean getOriginPlace() {
        return originPlace;
    }

    /**
     * 设置来源地:1-新开；2-AF转入；3-其他供应商转入
     *
     * @param originPlace 来源地:1-新开；2-AF转入；3-其他供应商转入
     */
    public void setOriginPlace(Boolean originPlace) {
        this.originPlace = originPlace;
    }

    /**
     * 获取来源地备注
     *
     * @return OriginPlaceRemark - 来源地备注
     */
    public String getOriginPlaceRemark() {
        return originPlaceRemark;
    }

    /**
     * 设置来源地备注
     *
     * @param originPlaceRemark 来源地备注
     */
    public void setOriginPlaceRemark(String originPlaceRemark) {
        this.originPlaceRemark = originPlaceRemark == null ? null : originPlaceRemark.trim();
    }

    /**
     * 获取查询账号
     *
     * @return QueryAccount - 查询账号
     */
    public String getQueryAccount() {
        return queryAccount;
    }

    /**
     * 设置查询账号
     *
     * @param queryAccount 查询账号
     */
    public void setQueryAccount(String queryAccount) {
        this.queryAccount = queryAccount == null ? null : queryAccount.trim();
    }

    /**
     * 获取交予方式:1-交客服  2-传真  3-邮寄
     *
     * @return DeliverWay - 交予方式:1-交客服  2-传真  3-邮寄
     */
    public String getDeliverWay() {
        return deliverWay;
    }

    /**
     * 设置交予方式:1-交客服  2-传真  3-邮寄
     *
     * @param deliverWay 交予方式:1-交客服  2-传真  3-邮寄
     */
    public void setDeliverWay(String deliverWay) {
        this.deliverWay = deliverWay == null ? null : deliverWay.trim();
    }

    /**
     * 获取交予方式备注
     *
     * @return DeliverWayRemark - 交予方式备注
     */
    public String getDeliverWayRemark() {
        return deliverWayRemark;
    }

    /**
     * 设置交予方式备注
     *
     * @param deliverWayRemark 交予方式备注
     */
    public void setDeliverWayRemark(String deliverWayRemark) {
        this.deliverWayRemark = deliverWayRemark == null ? null : deliverWayRemark.trim();
    }

    /**
     * 获取给凭证时间
     *
     * @return ProvideCertificateTime - 给凭证时间
     */
    public LocalDate getProvideCertificateTime() {
        return provideCertificateTime;
    }

    /**
     * 设置给凭证时间
     *
     * @param provideCertificateTime 给凭证时间
     */
    public void setProvideCertificateTime(LocalDate provideCertificateTime) {
        this.provideCertificateTime = provideCertificateTime;
    }

    /**
     * 获取变更时间
     *
     * @return ChangeTime - 变更时间
     */
    public LocalDateTime getChangeTime() {
        return changeTime;
    }

    /**
     * 设置变更时间
     *
     * @param changeTime 变更时间
     */
    public void setChangeTime(LocalDateTime changeTime) {
        this.changeTime = changeTime;
    }

    /**
     * 获取收到日期
     *
     * @return ReceiveDate - 收到日期
     */
    public LocalDate getReceiveDate() {
        return receiveDate;
    }

    /**
     * 设置收到日期
     *
     * @param receiveDate 收到日期
     */
    public void setReceiveDate(LocalDate receiveDate) {
        this.receiveDate = receiveDate;
    }

    /**
     * 获取转入日期
     *
     * @return IntoDate - 转入日期
     */
    public LocalDate getIntoDate() {
        return intoDate;
    }

    /**
     * 设置转入日期
     *
     * @param intoDate 转入日期
     */
    public void setIntoDate(LocalDate intoDate) {
        this.intoDate = intoDate;
    }

    /**
     * 获取发出材料:正式通知书、预生成通知书、收据、银行对账单、汇总表、实时表
     *
     * @return DispatchMaterial - 发出材料:正式通知书、预生成通知书、收据、银行对账单、汇总表、实时表
     */
    public String getDispatchMaterial() {
        return dispatchMaterial;
    }

    /**
     * 设置发出材料:正式通知书、预生成通知书、收据、银行对账单、汇总表、实时表
     *
     * @param dispatchMaterial 发出材料:正式通知书、预生成通知书、收据、银行对账单、汇总表、实时表
     */
    public void setDispatchMaterial(String dispatchMaterial) {
        this.dispatchMaterial = dispatchMaterial == null ? null : dispatchMaterial.trim();
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