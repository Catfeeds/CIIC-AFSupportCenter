package com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotations.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 */
@TableName("am_remark")
public class AmRemark implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	@TableId(value="remark_id", type= IdType.AUTO)
	private Long remarkId;
    /**
     * 备注内容
     */
	@TableField("remark_content")
	private String remarkContent;
    /**
     * 1 用工备注 2 档案备注 3 退工备注
     */
	@TableField("remark_type")
	private Integer remarkType;
    /**
     * 备注人
     */
	@TableField("remark_man")
	private String remarkMan;
    /**
     * 备注时间
     */
	@TableField("remark_date")
	private LocalDate remarkDate;
    /**
     * 是否可用
     */
	@TableField("is_active")
	private Boolean isActive;
    /**
     * 创建时间
     */
	@TableField("created_time")
	private LocalDateTime createdTime;
    /**
     * 最后更新时间
     */
	@TableField("modified_time")
	private LocalDateTime modifiedTime;
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


	public Long getRemarkId() {
		return remarkId;
	}

	public void setRemarkId(Long remarkId) {
		this.remarkId = remarkId;
	}

	public String getRemarkContent() {
		return remarkContent;
	}

	public void setRemarkContent(String remarkContent) {
		this.remarkContent = remarkContent;
	}

	public Integer getRemarkType() {
		return remarkType;
	}

	public void setRemarkType(Integer remarkType) {
		this.remarkType = remarkType;
	}

	public String getRemarkMan() {
		return remarkMan;
	}

	public void setRemarkMan(String remarkMan) {
		this.remarkMan = remarkMan;
	}

	public LocalDate getRemarkDate() {
		return remarkDate;
	}

	public void setRemarkDate(LocalDate remarkDate) {
		this.remarkDate = remarkDate;
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

	@Override
	public String toString() {
		return "AmRemark{" +
			", remarkId=" + remarkId +
			", remarkContent=" + remarkContent +
			", remarkType=" + remarkType +
			", remarkMan=" + remarkMan +
			", remarkDate=" + remarkDate +
			", isActive=" + isActive +
			", createdTime=" + createdTime +
			", modifiedTime=" + modifiedTime +
			", createdBy=" + createdBy +
			", modifiedBy=" + modifiedBy +
			"}";
	}
}
