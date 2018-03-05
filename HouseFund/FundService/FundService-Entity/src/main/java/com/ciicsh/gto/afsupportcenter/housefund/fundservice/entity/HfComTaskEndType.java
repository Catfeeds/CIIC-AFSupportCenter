package com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableLogic;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 企业公积金任务单终止类型数据表
 * </p>
 *
 * @author 沈健
 * @since 2018-03-01
 */
@TableName("hf_com_task_end_type")
public class HfComTaskEndType extends Model<HfComTaskEndType> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	@TableId(value="com_task_end_type_id", type= IdType.AUTO)
	private Long comTaskEndTypeId;
    /**
     * 企业公积金任务单终止类型编号
     */
	@TableField("end_type_code")
	private Integer endTypeCode;
    /**
     * 企业公积金任务单终止类型值
     */
	@TableField("end_type_value")
	private String endTypeValue;
    /**
     * 是否可用
     */
	@TableField("is_active")
    @TableLogic
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


	public Long getComTaskEndTypeId() {
		return comTaskEndTypeId;
	}

	public void setComTaskEndTypeId(Long comTaskEndTypeId) {
		this.comTaskEndTypeId = comTaskEndTypeId;
	}

	public Integer getEndTypeCode() {
		return endTypeCode;
	}

	public void setEndTypeCode(Integer endTypeCode) {
		this.endTypeCode = endTypeCode;
	}

	public String getEndTypeValue() {
		return endTypeValue;
	}

	public void setEndTypeValue(String endTypeValue) {
		this.endTypeValue = endTypeValue;
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
		return this.comTaskEndTypeId;
	}

	@Override
	public String toString() {
		return "HfComTaskEndType{" +
			", comTaskEndTypeId=" + comTaskEndTypeId +
			", endTypeCode=" + endTypeCode +
			", endTypeValue=" + endTypeValue +
			", isActive=" + isActive +
			", createdTime=" + createdTime +
			", modifiedTime=" + modifiedTime +
			", createdBy=" + createdBy +
			", modifiedBy=" + modifiedBy +
			"}";
	}
}
