package com.ciicsh.gto.afsupportcenter.healthmedical.entity.bo;

/**
 * <p>
 * 雇员付款申请状态
 * </p>
 *
 * @author chenpb
 * @since 2018-02-23
 */
public class EmployeePaymentStatusBO {

    /** 批次序号 */
    private Integer batchId;
    /** 业务ID */
    private Integer businessId;
    /** 申请状态 */
    private Integer status;
    /** 当前状态 */
    private Integer currentStatus;
    /** 备注 */
    private String remark;
    /** 修改者 */
    private String modifiedBy;
    /** 受理单编号 */
    String acceptanceId;
    /** 申请编号 */
    Integer applyId;

    public Integer getBatchId() {
        return batchId;
    }

    public void setBatchId(Integer batchId) {
        this.batchId = batchId;
    }

    public Integer getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Integer businessId) {
        this.businessId = businessId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public String getAcceptanceId() {
        return acceptanceId;
    }

    public void setAcceptanceId(String acceptanceId) {
        this.acceptanceId = acceptanceId;
    }

    public Integer getApplyId() {
        return applyId;
    }

    public void setApplyId(Integer applyId) {
        applyId = applyId;
    }

    public Integer getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(Integer currentStatus) {
        this.currentStatus = currentStatus;
    }

    public EmployeePaymentStatusBO(Integer batchId, Integer businessId, Integer status, Integer currentStatus, String remark, String modifiedBy) {
        this.batchId = batchId;
        this.businessId = businessId;
        this.status = status;
        this.currentStatus = currentStatus;
        this.remark = remark;
        this.modifiedBy = modifiedBy;
    }

    public EmployeePaymentStatusBO(Integer batchId, Integer businessId, Integer status, String remark, String modifiedBy) {
        this.batchId = batchId;
        this.businessId = businessId;
        this.status = status;
        this.remark = remark;
        this.modifiedBy = modifiedBy;
    }

    public EmployeePaymentStatusBO(String acceptanceId, Integer status, String remark, String modifiedBy) {
        this.acceptanceId = acceptanceId;
        this.status = status;
        this.remark = remark;
        this.modifiedBy = modifiedBy;
    }

    public EmployeePaymentStatusBO(Integer applyId, Integer status, String remark, String modifiedBy) {
        this.applyId = applyId;
        this.status = status;
        this.remark = remark;
        this.modifiedBy = modifiedBy;
    }
}
