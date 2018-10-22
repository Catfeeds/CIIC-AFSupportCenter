package com.ciicsh.gto.afsupportcenter.employmanagement.employservice.entity;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 档案轨迹
 * </p>
 */
@TableName("am_archive_link")
public class AmArchiveLink implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	@TableId(value="archive_link_id", type= IdType.AUTO)
	private Long archiveLinkId;
    /**
     * 外键
     */
	@TableField("archive_id")
	private Long archiveId;
    /**
     * 档案类别：空、A、Aa、B、Bb...Z、Zz...AB、AC、AD...AZ、BA、BC...BZ
     */
	@TableField("doc_type")
	private String docType;
    /**
     * 档案编号
     */
	@TableField("doc_num")
	private String docNum;
    /**
     * 用工反馈：3 用工成功、10 用工已办查无档、4 用工失败、11 Ukey外借、5 前道要求撤销用工、12 用工成功,重复任务单、13 用工已办,前道已中止(Ukey外借是中间状态，用工已办查无档、用工已办,前道已中止、用工成功、用工成功,重复任务单 代表用工成功其他失败)
     */
	@TableField("employ_feedback")
	private String employFeedback;
    /**
     * 创建时间
     */
	@TableField("created_time")
	private LocalDateTime createdTime;
    /**
     * 创建者登录名
     */
	@TableField("created_by")
	private String createdBy;


	public Long getArchiveLinkId() {
		return archiveLinkId;
	}

	public void setArchiveLinkId(Long archiveLinkId) {
		this.archiveLinkId = archiveLinkId;
	}

	public Long getArchiveId() {
		return archiveId;
	}

	public void setArchiveId(Long archiveId) {
		this.archiveId = archiveId;
	}

	public String getDocType() {
		return docType;
	}

	public void setDocType(String docType) {
		this.docType = docType;
	}

	public String getDocNum() {
		return docNum;
	}

	public void setDocNum(String docNum) {
		this.docNum = docNum;
	}

	public String getEmployFeedback() {
		return employFeedback;
	}

	public void setEmployFeedback(String employFeedback) {
		this.employFeedback = employFeedback;
	}

	public LocalDateTime getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(LocalDateTime createdTime) {
		this.createdTime = createdTime;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	@Override
	public String toString() {
		return "AmArchiveLink{" +
			", archiveLinkId=" + archiveLinkId +
			", archiveId=" + archiveId +
			", docType=" + docType +
			", docNum=" + docNum +
			", employFeedback=" + employFeedback +
			", createdTime=" + createdTime +
			", createdBy=" + createdBy +
			"}";
	}
}
