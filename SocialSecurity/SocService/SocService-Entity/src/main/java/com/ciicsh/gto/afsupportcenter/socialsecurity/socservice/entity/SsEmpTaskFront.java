package com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotations.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 雇员任务单前道传递信息,创建任务单的同时，就要把前道的传递信息复制到这表，当前表复制前道cmy_af_emp_socia
 * </p>
 *
 * @author xsj
 * @since 2018-01-19
 */
@TableName("ss_emp_task_front")
public class SsEmpTaskFront implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键(自增长)
     */
    @TableId(value="emp_task_front_id", type= IdType.AUTO)
    private Long empTaskFrontId;
    /**
     * 雇员任务单编号
     */
    @TableField("emp_task_id")
    private Long empTaskId;
    /**
     * 雇员报入职基数
     */
    @TableField("emp_company_base")
    private BigDecimal empCompanyBase;
    /**
     * 社保ID
     */
    @TableField("policy_id")
    private String policyId;
    /**
     * 数据字典ID
     */
    @TableField("item_dic_id")
    private String itemDicId;
    /**
     * 名称
     */
    @TableField("policy_name")
    private String policyName;
    /**
     * 企业比例
     */
    @TableField("company_ratio")
    private BigDecimal companyRatio;
    /**
     * 企业基数
     */
    @TableField("company_base")
    private BigDecimal companyBase;
    /**
     * 个人金额
     */
    @TableField("company_amount")
    private BigDecimal companyAmount;
    /**
     * 个人比例
     */
    @TableField("personal_ratio")
    private BigDecimal personalRatio;
    /**
     * 个人基数
     */
    @TableField("personal_base")
    private BigDecimal personalBase;
    /**
     * 个人金额
     */
    @TableField("personal_amount")
    private BigDecimal personalAmount;
    /**
     * 缴纳开始月(yyyyMM格式)
     */
    @TableField("start_month")
    private Integer startMonth;
    /**
     * 缴纳结束月(yyyyMM格式)
     */
    @TableField("end_month")
    private Integer endMonth;
    /**
     * 是否有效
     */
    @TableField("is_active")
    private Boolean isActive;
    /**
     * 数据创建时间
     */
    @TableField("created_time")
    private LocalDateTime createdTime;
    /**
     * 最后修改时间
     */
    @TableField("modified_time")
    private LocalDateTime modifiedTime;
    /**
     * 创建人
     */
    @TableField("created_by")
    private String createdBy;
    /**
     * 创建者姓名
     */
    @TableField("created_display_name")
    private String createdDisplayName;
    /**
     * 修改者ID
     */
    @TableField("modified_by")
    private String modifiedBy;
    /**
     * 修改者姓名
     */
    @TableField("modified_display_name")
    private String modifiedDisplayName;
    /**
     * 领导ID
     */
    @TableField("leader_ship_id")
    private String leaderShipId;
    /**
     * 领导姓名
     */
    @TableField("leader_ship_name")
    private String leaderShipName;


    public Long getEmpTaskFrontId() {
        return empTaskFrontId;
    }

    public void setEmpTaskFrontId(Long empTaskFrontId) {
        this.empTaskFrontId = empTaskFrontId;
    }

    public Long getEmpTaskId() {
        return empTaskId;
    }

    public void setEmpTaskId(Long empTaskId) {
        this.empTaskId = empTaskId;
    }

    public BigDecimal getEmpCompanyBase() {
        return empCompanyBase;
    }

    public void setEmpCompanyBase(BigDecimal empCompanyBase) {
        this.empCompanyBase = empCompanyBase;
    }

    public String getPolicyId() {
        return policyId;
    }

    public void setPolicyId(String policyId) {
        this.policyId = policyId;
    }

    public String getItemDicId() {
        return itemDicId;
    }

    public void setItemDicId(String itemDicId) {
        this.itemDicId = itemDicId;
    }

    public String getPolicyName() {
        return policyName;
    }

    public void setPolicyName(String policyName) {
        this.policyName = policyName;
    }

    public BigDecimal getCompanyRatio() {
        return companyRatio;
    }

    public void setCompanyRatio(BigDecimal companyRatio) {
        this.companyRatio = companyRatio;
    }

    public BigDecimal getCompanyBase() {
        return companyBase;
    }

    public void setCompanyBase(BigDecimal companyBase) {
        this.companyBase = companyBase;
    }

    public BigDecimal getCompanyAmount() {
        return companyAmount;
    }

    public void setCompanyAmount(BigDecimal companyAmount) {
        this.companyAmount = companyAmount;
    }

    public BigDecimal getPersonalRatio() {
        return personalRatio;
    }

    public void setPersonalRatio(BigDecimal personalRatio) {
        this.personalRatio = personalRatio;
    }

    public BigDecimal getPersonalBase() {
        return personalBase;
    }

    public void setPersonalBase(BigDecimal personalBase) {
        this.personalBase = personalBase;
    }

    public BigDecimal getPersonalAmount() {
        return personalAmount;
    }

    public void setPersonalAmount(BigDecimal personalAmount) {
        this.personalAmount = personalAmount;
    }

    public Integer getStartMonth() {
        return startMonth;
    }

    public void setStartMonth(Integer startMonth) {
        this.startMonth = startMonth;
    }

    public Integer getEndMonth() {
        return endMonth;
    }

    public void setEndMonth(Integer endMonth) {
        this.endMonth = endMonth;
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

    public String getCreatedDisplayName() {
        return createdDisplayName;
    }

    public void setCreatedDisplayName(String createdDisplayName) {
        this.createdDisplayName = createdDisplayName;
    }

    public String getModifiedDisplayName() {
        return modifiedDisplayName;
    }

    public void setModifiedDisplayName(String modifiedDisplayName) {
        this.modifiedDisplayName = modifiedDisplayName;
    }

    public String getLeaderShipId() {
        return leaderShipId;
    }

    public void setLeaderShipId(String leaderShipId) {
        this.leaderShipId = leaderShipId;
    }

    public String getLeaderShipName() {
        return leaderShipName;
    }

    public void setLeaderShipName(String leaderShipName) {
        this.leaderShipName = leaderShipName;
    }

    @Override
    public String toString() {
        return "SsEmpTaskFront{" +
            "empTaskFrontId=" + empTaskFrontId +
            ", empTaskId=" + empTaskId +
            ", empCompanyBase=" + empCompanyBase +
            ", policyId=" + policyId +
            ", itemDicId=" + itemDicId +
            ", policyName=" + policyName +
            ", companyRatio=" + companyRatio +
            ", companyBase=" + companyBase +
            ", companyAmount=" + companyAmount +
            ", personalRatio=" + personalRatio +
            ", personalBase=" + personalBase +
            ", personalAmount=" + personalAmount +
            ", startMonth=" + startMonth +
            ", endMonth=" + endMonth +
            ", isActive=" + isActive +
            ", createdTime=" + createdTime +
            ", modifiedTime=" + modifiedTime +
            ", createdBy=" + createdBy +
            ", createdDisplayName=" + createdDisplayName +
            ", modifiedBy=" + modifiedBy +
            ", modifiedDisplayName=" + modifiedDisplayName +
            ", leaderShipId=" + leaderShipId +
            ", leaderShipName=" + leaderShipName +
            "}";
    }
}
