package com.ciicsh.gto.afsupportcenter.housefund.fundservice.api.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 雇员任务单信息
 */
public class HfEmpTaskDetailInfoDTO {
    /**
     * 雇员任务单ID
     */
    private Long empTaskId;
    /**
     * 客户编号
     */
    private String companyId;
    /**
     * 雇员编号
     */
    private String employeeId;
    /**
     * 任务类型：1 新增(新开) 、2 新增（转入） 、3 新增（启封）、4 调整（封存）、5 调整（启封）、
     6 补缴、7 离职（转出）、8 离职（封存）、9 转移、 10 特殊任务  11 集体转入  12  集体转出  13 翻牌
     */
    private Integer taskCategory;
    /**
     * 办理状态：1、未处理 2 、处理中(已办)  3 已完成(已做) 4、批退 5、不需处理
     */
    private Integer taskStatus;
    /**
     * 雇员基数
     */
    private BigDecimal empBase;
    /**
     * 个人比例
     */
    private BigDecimal ratioEmp;
    /**
     * 企业比例
     */
    private BigDecimal ratioCom;
    /**
     * 金额
     */
    private BigDecimal amount;
    /**
     * 操作提示:  1 要做、2 中心、3 中智、4 原单位、5 其他独立开户公司、6 外包
     */
    private Integer operationRemind;
    /**
     * 操作提示日期
     */
    private Date operationRemindDate;
    /**
     * 外键:雇员公积金档案主表
     */
    private Long empArchiveId;
    /**
     * 是否更正 1 是 0 否
     */
    private Integer isChange;
    /**
     * 公积金类型:1 基本 2 补充
     */
    private Integer hfType;
    /**
     * 缴费段开始月份YYYYMM
     */
    private String startMonth;
    /**
     * 缴费段结束月份YYYYMM
     */
    private String endMonth;
    /**
     * 办理年月
     */
    private String handleMonth;
    /**
     * 办理备注
     */
    private String handleRemark;
    /**
     * 批退备注
     */
    private String rejectionRemark;
    /**
     * 福利办理方
     */
    private Integer welfareUnit;
    /**
     * 发起人ID
     */
    private String submitterId;
    /**
     * 发起时间
     */
    private Date submitTime;
    /**
     * 创建时间
     */
    private Date createdTime;
    /**
     * 最后更新时间
     */
    private Date modifiedTime;
    /**
     * 创建者登录名
     */
    private String createdBy;
    /**
     * 创建者姓名
     */
    private String createdDisplayName;
    /**
     * 修改者ID
     */
    private String modifiedBy;
    /**
     * 修改者姓名
     */
    private String modifiedDisplayName;
    /**
     * 领导ID
     */
    private String leaderShipId;
    /**
     * 领导姓名
     */
    private String leaderShipName;
    /**
     * 服务中心ID
     */
    private Integer serviceCenterId;
    /**
     * 服务中心
     */
    private String serviceCenter;
    /**
     * 旧城市编码
     */
    private String oldCityCode;
    /**
     * 新城市编码
     */
    private String newCityCode;
    /**
     * 入离职ID
     */
    private Long empCompanyId;

    public Long getEmpTaskId() {
        return empTaskId;
    }

    public void setEmpTaskId(Long empTaskId) {
        this.empTaskId = empTaskId;
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

    public Integer getTaskCategory() {
        return taskCategory;
    }

    public void setTaskCategory(Integer taskCategory) {
        this.taskCategory = taskCategory;
    }

    public Integer getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(Integer taskStatus) {
        this.taskStatus = taskStatus;
    }

    public BigDecimal getEmpBase() {
        return empBase;
    }

    public void setEmpBase(BigDecimal empBase) {
        this.empBase = empBase;
    }

    public BigDecimal getRatioEmp() {
        return ratioEmp;
    }

    public void setRatioEmp(BigDecimal ratioEmp) {
        this.ratioEmp = ratioEmp;
    }

    public BigDecimal getRatioCom() {
        return ratioCom;
    }

    public void setRatioCom(BigDecimal ratioCom) {
        this.ratioCom = ratioCom;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Integer getOperationRemind() {
        return operationRemind;
    }

    public void setOperationRemind(Integer operationRemind) {
        this.operationRemind = operationRemind;
    }

    public Date getOperationRemindDate() {
        return operationRemindDate;
    }

    public void setOperationRemindDate(Date operationRemindDate) {
        this.operationRemindDate = operationRemindDate;
    }

    public Long getEmpArchiveId() {
        return empArchiveId;
    }

    public void setEmpArchiveId(Long empArchiveId) {
        this.empArchiveId = empArchiveId;
    }

    public Integer getIsChange() {
        return isChange;
    }

    public void setIsChange(Integer isChange) {
        this.isChange = isChange;
    }

    public Integer getHfType() {
        return hfType;
    }

    public void setHfType(Integer hfType) {
        this.hfType = hfType;
    }

    public String getStartMonth() {
        return startMonth;
    }

    public void setStartMonth(String startMonth) {
        this.startMonth = startMonth;
    }

    public String getEndMonth() {
        return endMonth;
    }

    public void setEndMonth(String endMonth) {
        this.endMonth = endMonth;
    }

    public String getHandleMonth() {
        return handleMonth;
    }

    public void setHandleMonth(String handleMonth) {
        this.handleMonth = handleMonth;
    }

    public String getHandleRemark() {
        return handleRemark;
    }

    public void setHandleRemark(String handleRemark) {
        this.handleRemark = handleRemark;
    }

    public String getRejectionRemark() {
        return rejectionRemark;
    }

    public void setRejectionRemark(String rejectionRemark) {
        this.rejectionRemark = rejectionRemark;
    }

    public Integer getWelfareUnit() {
        return welfareUnit;
    }

    public void setWelfareUnit(Integer welfareUnit) {
        this.welfareUnit = welfareUnit;
    }

    public String getSubmitterId() {
        return submitterId;
    }

    public void setSubmitterId(String submitterId) {
        this.submitterId = submitterId;
    }

    public Date getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
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

    public String getCreatedDisplayName() {
        return createdDisplayName;
    }

    public void setCreatedDisplayName(String createdDisplayName) {
        this.createdDisplayName = createdDisplayName;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
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

    public Integer getServiceCenterId() {
        return serviceCenterId;
    }

    public void setServiceCenterId(Integer serviceCenterId) {
        this.serviceCenterId = serviceCenterId;
    }

    public String getServiceCenter() {
        return serviceCenter;
    }

    public void setServiceCenter(String serviceCenter) {
        this.serviceCenter = serviceCenter;
    }

    public String getOldCityCode() {
        return oldCityCode;
    }

    public void setOldCityCode(String oldCityCode) {
        this.oldCityCode = oldCityCode;
    }

    public String getNewCityCode() {
        return newCityCode;
    }

    public void setNewCityCode(String newCityCode) {
        this.newCityCode = newCityCode;
    }

    public Long getEmpCompanyId() {
        return empCompanyId;
    }

    public void setEmpCompanyId(Long empCompanyId) {
        this.empCompanyId = empCompanyId;
    }
}
