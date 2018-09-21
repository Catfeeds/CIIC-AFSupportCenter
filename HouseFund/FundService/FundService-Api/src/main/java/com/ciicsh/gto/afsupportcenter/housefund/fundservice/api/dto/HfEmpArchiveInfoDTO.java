package com.ciicsh.gto.afsupportcenter.housefund.fundservice.api.dto;

import java.util.Date;

/**
 * 雇员档案信息
 */
public class HfEmpArchiveInfoDTO {
    /**
     * 雇员档案ID
     */
    private Long empArchiveId;
    /**
     * 客户编号
     */
    private String companyId;
    /**
     * 雇员编号
     */
    private String employeeId;
    /**
     * 企业公积金账户Id, 关联至HF_ComAccount
     */
    private Integer comAccountId;
    /**
     * 外键：企业公积金账户分类
     */
    private Long comAccountClassId;
    /**
     * 公积金类型: 1 基本  2 补充
     */
    private Integer hfType;
    /**
     * 雇员基本补充公积金账号
     */
    private String hfEmpAccount;
    /**
     * 公积金状态 : 0-未办理  1-已办  2-已做 3-封存
     */
    private Integer archiveStatus;
    /**
     * 任务单公积金状态 : 1-已办  2-已做 3-封存
     */
    private Integer archiveTaskStatus;
    /**
     * 公积金起缴月份
     */
    private String startMonth;
    /**
     * 公积金转出封存月份
     */
    private String endMonth;
    /**
     * 操作提示:  1 要做、2 中心、3 中智、4 原单位、5 其他独立开户公司、6 外包
     */
    private Integer operationRemind;
    /**
     * 操作提示日期
     */
    private Date operationRemindDate;
    /**
     * 所属供应商：1 af 2  bpo
     */
    private String belongVendor;
    /**
     * 所属基本公积金档案ID,如果记录是补充公积金,该字段必填
     */
    private Long belongEmpArchiveId;
    /**
     * 福利办理方
     */
    private Integer welfareUnit;
    /**
     * 服务中心ID
     */
    private Integer serviceCenterId;
    /**
     * 服务中心
     */
    private String serviceCenter;
    /**
     * 入职日期
     */
    private Date inDate;
    /**
     * 离职时间
     */
    private Date outDate;
    /**
     * 入离职ID
     */
    private Long empCompanyId;
    /**
     * 缴纳银行（缴纳区县）
     */
    private String paymentBank;

    public Long getEmpArchiveId() {
        return empArchiveId;
    }

    public void setEmpArchiveId(Long empArchiveId) {
        this.empArchiveId = empArchiveId;
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

    public Integer getComAccountId() {
        return comAccountId;
    }

    public void setComAccountId(Integer comAccountId) {
        this.comAccountId = comAccountId;
    }

    public Long getComAccountClassId() {
        return comAccountClassId;
    }

    public void setComAccountClassId(Long comAccountClassId) {
        this.comAccountClassId = comAccountClassId;
    }

    public Integer getHfType() {
        return hfType;
    }

    public void setHfType(Integer hfType) {
        this.hfType = hfType;
    }

    public String getHfEmpAccount() {
        return hfEmpAccount;
    }

    public void setHfEmpAccount(String hfEmpAccount) {
        this.hfEmpAccount = hfEmpAccount;
    }

    public Integer getArchiveStatus() {
        return archiveStatus;
    }

    public void setArchiveStatus(Integer archiveStatus) {
        this.archiveStatus = archiveStatus;
    }

    public Integer getArchiveTaskStatus() {
        return archiveTaskStatus;
    }

    public void setArchiveTaskStatus(Integer archiveTaskStatus) {
        this.archiveTaskStatus = archiveTaskStatus;
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

    public String getBelongVendor() {
        return belongVendor;
    }

    public void setBelongVendor(String belongVendor) {
        this.belongVendor = belongVendor;
    }

    public Long getBelongEmpArchiveId() {
        return belongEmpArchiveId;
    }

    public void setBelongEmpArchiveId(Long belongEmpArchiveId) {
        this.belongEmpArchiveId = belongEmpArchiveId;
    }

    public Integer getWelfareUnit() {
        return welfareUnit;
    }

    public void setWelfareUnit(Integer welfareUnit) {
        this.welfareUnit = welfareUnit;
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

    public Date getInDate() {
        return inDate;
    }

    public void setInDate(Date inDate) {
        this.inDate = inDate;
    }

    public Date getOutDate() {
        return outDate;
    }

    public void setOutDate(Date outDate) {
        this.outDate = outDate;
    }

    public Long getEmpCompanyId() {
        return empCompanyId;
    }

    public void setEmpCompanyId(Long empCompanyId) {
        this.empCompanyId = empCompanyId;
    }

    public String getPaymentBank() {
        return paymentBank;
    }

    public void setPaymentBank(String paymentBank) {
        this.paymentBank = paymentBank;
    }
}
