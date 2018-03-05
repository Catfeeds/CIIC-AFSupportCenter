package com.ciicsh.gto.afsupportcenter.healthmedical.entity.po;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

/**
 * <p>
 * TPA任务单记录表
 * </p>
 *
 * @author zhaogang
 * @since 2018-02-20
 */
@TableName("hm_af_tpa_task")
public class AfTpaTaskPO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 任务单编号
     */
    @TableField("af_task_id")
    private Integer afTaskId;
    /**
     * 任务单类型（1-投保任务单，2-退保任务单，3-变更保任务单）
     */
    @TableField("task_type")
    private String taskType;

    /**
     * 流程ID
     */
    @TableField("process_id")
    private String processId;
    /**
     * 状态（1-信息待完善，2-待处理，3-暂缓，4-已审核，5-已完成，6-已批退）
     */
    private Integer status;
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
     * 公司编号
     */
    @TableField("company_id")
    private String companyId;
    /**
     * 公司名称
     */
    @TableField("company_name")
    private String companyName;
    /**
     * 产品编号
     */
    @TableField("af_product_id")
    private String afProductId;
    /**
     * 产品名称
     */
    @TableField("product_name")
    private String productName;
    /**
     * 投保日期(yyyy-MM-dd格式)
     */
    @TableField("start_confirm_date")
    private LocalDate startConfirmDate;
    /**
     * 退保开始日期(yyyy-MM-dd格式)
     */
    @TableField("end_confirm_date")
    private LocalDate endConfirmDate;
    /**
     * 类型（1-雇员，2-子女，3-配偶）
     */
    private Integer type;
    /**
     * 连带人编号
     */
    @TableField("associated_insurant_id")
    private String associatedInsurantId;
    /**
     * 连带人姓名
     */
    @TableField("associated_insurant_name")
    private String associatedInsurantName;
    /**
     * 被保险人身份证（）
     */
    @TableField("id_num")
    private String idNum;
    /**
     * 被保险人性别  1:男 0:女
     */
    private Integer gender;
    /**
     * 被保险人年龄
     */
    private Integer age;
    /**
     * 被保险人出生日期
     */
    @TableField("birth_date")
    private LocalDate birthDate;
    /**
     * 价格
     */
    private BigDecimal price;
    /**
     * 服务选项
     */
    @TableField("service_items")
    private String serviceItems;
    /**
     * 保额类型（1-固定金额，2-赔付比例）
     */
    @TableField("key_type")
    private Integer keyType;
    /**
     * 保额
     */
    @TableField("key_value")
    private BigDecimal keyValue;
    /**
     * 备注
     */
    private String remark;
    /**
     * 是否可用
     */
    @TableField("is_active")
    private Boolean isActive;
    /**
     * 创建时间
     */
    @TableField("created_time")
    private LocalTime createdTime;
    /**
     * 最后更新时间
     */
    @TableField("modified_time")
    private LocalTime modifiedTime;
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

    public String getProcessId() {
        return processId;
    }

    public void setProcessId(String processId) {
        this.processId = processId;
    }

    public Integer getAfTaskId() {
        return afTaskId;
    }

    public void setAfTaskId(Integer afTaskId) {
        this.afTaskId = afTaskId;
    }

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public String getAfProductId() {
        return afProductId;
    }

    public void setAfProductId(String afProductId) {
        this.afProductId = afProductId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public LocalDate getStartConfirmDate() {
        return startConfirmDate;
    }

    public void setStartConfirmDate(LocalDate startConfirmDate) {
        this.startConfirmDate = startConfirmDate;
    }

    public LocalDate getEndConfirmDate() {
        return endConfirmDate;
    }

    public void setEndConfirmDate(LocalDate endConfirmDate) {
        this.endConfirmDate = endConfirmDate;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getAssociatedInsurantId() {
        return associatedInsurantId;
    }

    public void setAssociatedInsurantId(String associatedInsurantId) {
        this.associatedInsurantId = associatedInsurantId;
    }

    public String getAssociatedInsurantName() {
        return associatedInsurantName;
    }

    public void setAssociatedInsurantName(String associatedInsurantName) {
        this.associatedInsurantName = associatedInsurantName;
    }

    public String getIdNum() {
        return idNum;
    }

    public void setIdNum(String idNum) {
        this.idNum = idNum;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getServiceItems() {
        return serviceItems;
    }

    public void setServiceItems(String serviceItems) {
        this.serviceItems = serviceItems;
    }

    public Integer getKeyType() {
        return keyType;
    }

    public void setKeyType(Integer keyType) {
        this.keyType = keyType;
    }

    public BigDecimal getKeyValue() {
        return keyValue;
    }

    public void setKeyValue(BigDecimal keyValue) {
        this.keyValue = keyValue;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public LocalTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalTime createdTime) {
        this.createdTime = createdTime;
    }

    public LocalTime getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(LocalTime modifiedTime) {
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
    public String toString() {
        return "AfTpaTaskPO{" +
            ", afTaskId=" + afTaskId +
            ", taskType=" + taskType +
            ", status=" + status +
            ", employeeId=" + employeeId +
            ", employeeName=" + employeeName +
            ", companyId=" + companyId +
            ", companyName=" + companyName +
            ", afProductId=" + afProductId +
            ", productName=" + productName +
            ", startConfirmDate=" + startConfirmDate +
            ", endConfirmDate=" + endConfirmDate +
            ", type=" + type +
            ", associatedInsurantId=" + associatedInsurantId +
            ", associatedInsurantName=" + associatedInsurantName +
            ", idNum=" + idNum +
            ", gender=" + gender +
            ", age=" + age +
            ", birthDate=" + birthDate +
            ", price=" + price +
            ", serviceItems=" + serviceItems +
            ", keyType=" + keyType +
            ", keyValue=" + keyValue +
            ", remark=" + remark +
            ", isActive=" + isActive +
            ", createdTime=" + createdTime +
            ", modifiedTime=" + modifiedTime +
            ", createdBy=" + createdBy +
            ", modifiedBy=" + modifiedBy +
            "}";
    }
}
