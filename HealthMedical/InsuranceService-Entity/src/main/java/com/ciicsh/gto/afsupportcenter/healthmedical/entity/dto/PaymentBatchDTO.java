package com.ciicsh.gto.afsupportcenter.healthmedical.entity.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 申请支付批次
 * </p>
 *
 * @author chenpb
 * @since 2018-01-31
 */
public class PaymentBatchDTO implements Serializable {
    /**
     * 支付申请详情
     */
    List<PaymentDetailDTO> employeeList;
    /**
     * 支付批次号
     */
	private Integer businessPkId;
    /**
     * 部门名称
     */
	private String departmentName;
    /**
     * 是否财务部（0-否，1-是）
        默认0
     */
	private Integer isFinancedept;
    /**
     * 业务类型：11 -> AF雇员报销
     */
	private Integer businessType;
    /**
     * 付款方式
     */
	private Integer payWay;
    /**
     * 申请支付总金额
     */
	private BigDecimal payAmount;
    /**
     * 收款方名称（固定值：个人）
     */
	private String receiver;
    /**
     * 申请人（固定值：系统）
     */
	private String applyer;
    /**
     * 申请日期（2018-01-05）
     */
	private Date applyDate;
    /**
     * 付款用途（2018-01-05补充医疗报销）
     */
	private String payPurpose;
    /**
     * 付款原因（2018-01-05补充医疗报销）
     */
	private String payReason;

    /**
     * 总经理
     */
    private String president;
    /**
     * 分管领导
     */
    private String leader;
    /**
     * 部门经理
     */
    private String departmentManager;
    /**
     * 审核人
     */
    private String reviewer;


    public List<PaymentDetailDTO> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<PaymentDetailDTO> employeeList) {
        this.employeeList = employeeList;
    }

    public Integer getBusinessPkId() {
        return businessPkId;
    }

    public void setBusinessPkId(Integer businessPkId) {
        this.businessPkId = businessPkId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Integer getIsFinancedept() {
        return isFinancedept;
    }

    public void setIsFinancedept(Integer isFinancedept) {
        this.isFinancedept = isFinancedept;
    }

    public Integer getBusinessType() {
        return businessType;
    }

    public void setBusinessType(Integer businessType) {
        this.businessType = businessType;
    }

    public Integer getPayWay() {
        return payWay;
    }

    public void setPayWay(Integer payWay) {
        this.payWay = payWay;
    }

    public BigDecimal getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getApplyer() {
        return applyer;
    }

    public void setApplyer(String applyer) {
        this.applyer = applyer;
    }

    public Date getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(Date applyDate) {
        this.applyDate = applyDate;
    }

    public String getPayPurpose() {
        return payPurpose;
    }

    public void setPayPurpose(String payPurpose) {
        this.payPurpose = payPurpose;
    }

    public String getPayReason() {
        return payReason;
    }

    public void setPayReason(String payReason) {
        this.payReason = payReason;
    }

    public String getPresident() {
        return president;
    }

    public void setPresident(String president) {
        this.president = president;
    }

    public String getLeader() {
        return leader;
    }

    public void setLeader(String leader) {
        this.leader = leader;
    }

    public String getDepartmentManager() {
        return departmentManager;
    }

    public void setDepartmentManager(String departmentManager) {
        this.departmentManager = departmentManager;
    }

    public String getReviewer() {
        return reviewer;
    }

    public void setReviewer(String reviewer) {
        this.reviewer = reviewer;
    }

    @Override
    public String toString() {
        return "PaymentBatchDTO{" +
            "employeeList=" + employeeList +
            ", businessPkId=" + businessPkId +
            ", departmentName='" + departmentName + '\'' +
            ", isFinancedept=" + isFinancedept +
            ", businessType=" + businessType +
            ", payWay=" + payWay +
            ", payAmount=" + payAmount +
            ", receiver='" + receiver + '\'' +
            ", applyer='" + applyer + '\'' +
            ", applyDate=" + applyDate +
            ", payPurpose='" + payPurpose + '\'' +
            ", payReason='" + payReason + '\'' +
            ", president='" + president + '\'' +
            ", leader='" + leader + '\'' +
            ", departmentManager='" + departmentManager + '\'' +
            ", reviewer='" + reviewer + '\'' +
            '}';
    }
}
