package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import java.time.LocalTime;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 雇员材料收缴
 * </p>
 *
 * @author HuangXing
 * @since 2017-12-01
 */
@TableName("ss_emp_material")
public class SsEmpMaterial implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 雇员任务单编号
     */
	@TableId(value="emp_material_id", type= IdType.AUTO)
	private Long empMaterialId;
    /**
     * 本地社保的雇员任务单Id
     */
	@TableField("emp_task_id")
	private String empTaskId;
    /**
     * 材料类型：原件、复印件、扫描件
     */
	@TableField("material_type")
	private Integer materialType;
    /**
     * 任务单提交人所属部门Id
     */
	@TableField("material_name")
	private String materialName;
    /**
     * 发起时间
     */
	@TableField("submit_time")
	private LocalTime submitTime;
    /**
     * 任务发起人备注
     */
	@TableField("receive_time")
	private LocalTime receiveTime;
    /**
     * 
            {
            "新进":{"开AF单日期":"2017-05-06","存档地":"外来从业人员","用工状态":"","用工日期":"","任务执行日期":"","基数段":[{"社保备注":"","基数":"","执行日期":""}]},
            "转入":{"开AF单日期":"2017-05-06","存档地":"外来从业人员","用工状态":"","用工日期":"","任务执行日期":"","基数段":[{"社保备注":"","基数":"","执行日期":""}]},
            "补缴":{"社保补缴基数":"","社保起缴月份":"201706","任务执行日期":""},
            "调整":{"新社保基数":"","调整月份":"201706","任务执行日期":""},
            "转出":{"转出月份":"201706","任务执行日期":""},
            "退账":{},
            "终止":{},
            "提取":{}
            }
     */
	private String remark;
	private Integer status;
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


	public Long getEmpMaterialId() {
		return empMaterialId;
	}

	public void setEmpMaterialId(Long empMaterialId) {
		this.empMaterialId = empMaterialId;
	}

	public String getEmpTaskId() {
		return empTaskId;
	}

	public void setEmpTaskId(String empTaskId) {
		this.empTaskId = empTaskId;
	}

	public Integer getMaterialType() {
		return materialType;
	}

	public void setMaterialType(Integer materialType) {
		this.materialType = materialType;
	}

	public String getMaterialName() {
		return materialName;
	}

	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}

	public LocalTime getSubmitTime() {
		return submitTime;
	}

	public void setSubmitTime(LocalTime submitTime) {
		this.submitTime = submitTime;
	}

	public LocalTime getReceiveTime() {
		return receiveTime;
	}

	public void setReceiveTime(LocalTime receiveTime) {
		this.receiveTime = receiveTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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
		return "SsEmpMaterial{" +
			", empMaterialId=" + empMaterialId +
			", empTaskId=" + empTaskId +
			", materialType=" + materialType +
			", materialName=" + materialName +
			", submitTime=" + submitTime +
			", receiveTime=" + receiveTime +
			", remark=" + remark +
			", status=" + status +
			", isActive=" + isActive +
			", createdTime=" + createdTime +
			", modifiedTime=" + modifiedTime +
			", createdBy=" + createdBy +
			", modifiedBy=" + modifiedBy +
			"}";
	}
}
