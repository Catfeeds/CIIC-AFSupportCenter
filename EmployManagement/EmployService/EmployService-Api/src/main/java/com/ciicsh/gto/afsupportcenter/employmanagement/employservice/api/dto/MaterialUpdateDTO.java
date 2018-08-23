package com.ciicsh.gto.afsupportcenter.employmanagement.employservice.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * Created by zhangzhiwen on 2018/4/10.
 */
public class MaterialUpdateDTO {

    /**
     * 退工单主键
     */
    private Long empTaskId;

    /**
     * 签收人
     */
    private String receiveName;

    /**
     * 签收人Id
     */
    private String receiveId;
    /**
     * 签收日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8", locale = "zh")
    private Date receiveDate;

    /**
     * 操作类型 1 签收 2 批退 3 提交
     */
    private Integer operationType;

    /**
     * 批退人ID
     */
    private String rejectId;

    /**
     * 批退人姓名
     */
    private String rejectName;

    /**
     * 批退日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8", locale = "zh")
    private Date rejectDate;

    /**
     * 备注
     */
    private String remark;

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getOperationType() {
        return operationType;
    }

    public void setOperationType(Integer operationType) {
        this.operationType = operationType;
    }

    public String getReceiveId() {
        return receiveId;
    }

    public void setReceiveId(String receiveId) {
        this.receiveId = receiveId;
    }

    public Long getEmpTaskId() {
        return empTaskId;
    }

    public void setEmpTaskId(Long empTaskId) {
        this.empTaskId = empTaskId;
    }

    public String getReceiveName() {
        return receiveName;
    }

    public void setReceiveName(String receiveName) {
        this.receiveName = receiveName;
    }

    public Date getReceiveDate() {
        return receiveDate;
    }

    public void setReceiveDate(Date receiveDate) {
        this.receiveDate = receiveDate;
    }

    public String getRejectId() {
        return rejectId;
    }

    public void setRejectId(String rejectId) {
        this.rejectId = rejectId;
    }

    public String getRejectName() {
        return rejectName;
    }

    public void setRejectName(String rejectName) {
        this.rejectName = rejectName;
    }

    public Date getRejectDate() {
        return rejectDate;
    }

    public void setRejectDate(Date rejectDate) {
        this.rejectDate = rejectDate;
    }
}
