package com.ciicsh.gto.afsupportcenter.healthmedical.entity.po;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 体检任务单表
 * </p>
 *
 * @author 顾伟
 * @since 2018-03-07
 */
@TableName("hm_af_pe_task")
public class AfPeTask extends Model<AfPeTask> {

    private static final long serialVersionUID = 1L;

    /**
     * 体检任务单编号
     */
    @TableId(value="pe_task_id", type= IdType.AUTO)
    private Integer peTaskId;
    /**
     * 流程编号
     */
    @TableField("process_id")
    private String processId;
    @TableField("pe_rule_id")
    private Integer peRuleId;
    /**
     * 任务单状态（1-待发放，2-已同步至中盈，3-未预约，4-已预约，5-预约失败，6-已到检，7-已出报告，8-已过期，9-已退订）
     */
    private Integer status;
    /**
     * 老系统公司id
     */
    @TableField("com_no")
    private String comNo;
    /**
     * 体检预约id
     */
    @TableField("bespeak_pe_id")
    private String bespeakPeId;
    /**
     * 体检产品编号
     */
    @TableField("product_id")
    private String productId;
    /**
     * 客户编号
     */
    @TableField("company_id")
    private String companyId;
    /**
     * 客户名称
     */
    @TableField("company_name")
    private String companyName;
    /**
     * 雇员编号
     */
    @TableField("employee_id")
    private String employeeId;
    /**
     * 雇员姓名
     */
    @TableField("employee_name")
    private String employeeName;
    /**
     * 雇员性别（1-男，2-女）
     */
    private Integer gender;
    /**
     * 证件类型
     */
    @TableField("id_type")
    private Integer idType;
    /**
     * 雇员身份证
     */
    @TableField("id_num")
    private String idNum;
    /**
     * 雇员生日
     */
    private Date birthday;
    /**
     * 雇员邮箱
     */
    private String email;
    /**
     * 雇员婚姻状况（1-已婚，2-未婚）
     */
    private Integer marital;
    /**
     * 支付类型（必填1个人支付，2公司支付）
     */
    @TableField("pay_type")
    private Integer payType;
    /**
     * 体检类别（必填1普通体检，2入职体检）
     */
    @TableField("pe_type")
    private Integer peType;
    /**
     * 体检任务单有效期开始时间
     */
    @TableField("effect_start_date")
    private Date effectStartDate;
    /**
     * 体检任务单有效期结束时间
     */
    @TableField("effect_end_date")
    private Date effectEndDate;
    /**
     * 是否企业统一收取报告
     */
    @TableField("company_report")
    private Boolean companyReport;
    /**
     * 预约体检日期
     */
    @TableField("ti_jian_date")
    private Date tiJianDate;
    /**
     * 到检日期
     */
    @TableField("dao_jian_date")
    private Date daoJianDate;
    /**
     * 出报告日期
     */
    @TableField("report_date")
    private Date reportDate;
    /**
     * 发送通知时间
     */
    @TableField("send_time")
    private Date sendTime;
    /**
     * 体检产品名称
     */
    @TableField("product_name")
    private String productName;
    /**
     * 结算价格（成本价）
     */
    @TableField("sale_value")
    private BigDecimal saleValue;
    /**
     * 体检机构
     */
    @TableField("pe_orginzation")
    private String peOrginzation;
    /**
     * 体检地址
     */
    @TableField("pe_address")
    private String peAddress;
    /**
     * 是否可用
     */
    @TableField("is_active")
    private Boolean isActive;
    /**
     * 创建时间
     */
    @TableField("created_time")
    private Date createdTime;
    /**
     * 最后更新时间
     */
    @TableField("modified_time")
    private Date modifiedTime;
    /**
     * 创建者登录名
     */
    @TableField("created_by")
    private String createdBy;
    /**
     * 修改者登录名
     */
    @TableField("modified_by")
    private String modifiedBy;


    public Integer getPeTaskId() {
        return peTaskId;
    }

    public void setPeTaskId(Integer peTaskId) {
        this.peTaskId = peTaskId;
    }

    public String getProcessId() {
        return processId;
    }

    public void setProcessId(String processId) {
        this.processId = processId;
    }

    public Integer getPeRuleId() {
        return peRuleId;
    }

    public void setPeRuleId(Integer peRuleId) {
        this.peRuleId = peRuleId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getComNo() {
        return comNo;
    }

    public void setComNo(String comNo) {
        this.comNo = comNo;
    }

    public String getBespeakPeId() {
        return bespeakPeId;
    }

    public void setBespeakPeId(String bespeakPeId) {
        this.bespeakPeId = bespeakPeId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Integer getIdType() {
        return idType;
    }

    public void setIdType(Integer idType) {
        this.idType = idType;
    }

    public String getIdNum() {
        return idNum;
    }

    public void setIdNum(String idNum) {
        this.idNum = idNum;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getMarital() {
        return marital;
    }

    public void setMarital(Integer marital) {
        this.marital = marital;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public Integer getPeType() {
        return peType;
    }

    public void setPeType(Integer peType) {
        this.peType = peType;
    }

    public Date getEffectStartDate() {
        return effectStartDate;
    }

    public void setEffectStartDate(Date effectStartDate) {
        this.effectStartDate = effectStartDate;
    }

    public Date getEffectEndDate() {
        return effectEndDate;
    }

    public void setEffectEndDate(Date effectEndDate) {
        this.effectEndDate = effectEndDate;
    }

    public Boolean getCompanyReport() {
        return companyReport;
    }

    public void setCompanyReport(Boolean companyReport) {
        this.companyReport = companyReport;
    }

    public Date getTiJianDate() {
        return tiJianDate;
    }

    public void setTiJianDate(Date tiJianDate) {
        this.tiJianDate = tiJianDate;
    }

    public Date getDaoJianDate() {
        return daoJianDate;
    }

    public void setDaoJianDate(Date daoJianDate) {
        this.daoJianDate = daoJianDate;
    }

    public Date getReportDate() {
        return reportDate;
    }

    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getSaleValue() {
        return saleValue;
    }

    public void setSaleValue(BigDecimal saleValue) {
        this.saleValue = saleValue;
    }

    public String getPeOrginzation() {
        return peOrginzation;
    }

    public void setPeOrginzation(String peOrginzation) {
        this.peOrginzation = peOrginzation;
    }

    public String getPeAddress() {
        return peAddress;
    }

    public void setPeAddress(String peAddress) {
        this.peAddress = peAddress;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Date modifiedTime) {
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

    @Override
    protected Serializable pkVal() {
        return this.peTaskId;
    }

    @Override
    public String toString() {
        return "AfPeTask{" +
            "peTaskId=" + peTaskId +
            ", processId=" + processId +
            ", peRuleId=" + peRuleId +
            ", status=" + status +
            ", comNo=" + comNo +
            ", bespeakPeId=" + bespeakPeId +
            ", productId=" + productId +
            ", companyId=" + companyId +
            ", companyName=" + companyName +
            ", employeeId=" + employeeId +
            ", employeeName=" + employeeName +
            ", gender=" + gender +
            ", idType=" + idType +
            ", idNum=" + idNum +
            ", birthday=" + birthday +
            ", email=" + email +
            ", marital=" + marital +
            ", payType=" + payType +
            ", peType=" + peType +
            ", effectStartDate=" + effectStartDate +
            ", effectEndDate=" + effectEndDate +
            ", companyReport=" + companyReport +
            ", tiJianDate=" + tiJianDate +
            ", daoJianDate=" + daoJianDate +
            ", reportDate=" + reportDate +
            ", sendTime=" + sendTime +
            ", productName=" + productName +
            ", saleValue=" + saleValue +
            ", peOrginzation=" + peOrginzation +
            ", peAddress=" + peAddress +
            ", isActive=" + isActive +
            ", createdTime=" + createdTime +
            ", modifiedTime=" + modifiedTime +
            ", createdBy=" + createdBy +
            ", modifiedBy=" + modifiedBy +
            "}";
    }
}
