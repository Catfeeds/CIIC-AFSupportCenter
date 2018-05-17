package com.ciicsh.gto.afsupportcenter.employmanagement.employservice.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <p>
 * 档案借出利用
 * </p>
 */
@TableName("am_archive_use")
public class AmArchiveUse implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	@TableId(value="archive_use_id", type= IdType.AUTO)
	private Long archiveUseId;
    /**
     * 材料使用材料借出
     */
	@TableField("use_borrow")
	private Integer useBorrow;

    /**
     * 使用材料:档案、党员材料、学籍资料、职工登记表、劳动力登记表、学生登记表、职称评定表、劳动手册、上家退工单、存档卡、落户通知书、其他
            借出材料:档案、党员材料、劳动手册、上家退工单、存档卡、其他
            
     */
	private String material;
    /**
     * 使用用途:归档材料、政审、档案借阅、认定工龄、查询信息、复印材料、扫描材料、开存档证明、其他
            借出用途:党员转正、社保核查、公司查阅、私人事务、其他
     */
	private String purpose;
    /**
     * 使用借出材料人
     */
	@TableField("use_man")
	private String useMan;
    /**
     * 使用借出日期
     */
	@TableField("use_date")
	private LocalDate useDate;
    /**
     * 材料使用借出经办人
     */
	@TableField("handle_man")
	private String handleMan;
    /**
     * 归还日期
     */
	@TableField("return_date")
	private LocalDate returnDate;
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
    /**
     * 外健
     */
	@TableField("archive_id")
	private String archiveId;

    public Long getArchiveUseId() {
		return archiveUseId;
	}

	public void setArchiveUseId(Long archiveUseId) {
		this.archiveUseId = archiveUseId;
	}

	public Integer getUseBorrow() {
		return useBorrow;
	}

	public void setUseBorrow(Integer useBorrow) {
		this.useBorrow = useBorrow;
	}


	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public String getUseMan() {
		return useMan;
	}

	public void setUseMan(String useMan) {
		this.useMan = useMan;
	}

	public LocalDate getUseDate() {
		return useDate;
	}

	public void setUseDate(LocalDate useDate) {
		this.useDate = useDate;
	}

	public String getHandleMan() {
		return handleMan;
	}

	public void setHandleMan(String handleMan) {
		this.handleMan = handleMan;
	}

	public LocalDate getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(LocalDate returnDate) {
		this.returnDate = returnDate;
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

	public String getArchiveId() {
		return archiveId;
	}

	public void setArchiveId(String archiveId) {
		this.archiveId = archiveId;
	}

	@Override
	public String toString() {
		return "AmArchiveUse{" +
			", archiveUseId=" + archiveUseId +
			", useBorrow=" + useBorrow +
			", material=" + material +
			", purpose=" + purpose +
			", useMan=" + useMan +
			", useDate=" + useDate +
			", handleMan=" + handleMan +
			", returnDate=" + returnDate +
			", remark=" + remark +
			", isActive=" + isActive +
			", createdTime=" + createdTime +
			", modifiedTime=" + modifiedTime +
			", createdBy=" + createdBy +
			", modifiedBy=" + modifiedBy +
			", archiveId=" + archiveId +
			"}";
	}
}
