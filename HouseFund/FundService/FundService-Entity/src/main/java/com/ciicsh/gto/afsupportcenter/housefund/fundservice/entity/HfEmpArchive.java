package com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity;

import com.baomidou.mybatisplus.enums.FieldStrategy;
import com.baomidou.mybatisplus.enums.IdType;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotations.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 雇员本地公积金档案主表,
由中智代缴过社保的雇员在此表必有一条记录，如果雇员跳槽到另外一家客户，就会在此表产
 * </p>
 */
@TableName("hf_emp_archive")
public class HfEmpArchive implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 雇员本地社保档案Id
     */
	@TableId(value="emp_archive_id", type= IdType.AUTO)
	private Long empArchiveId;
    /**
     * 客户主表ID
     */
	@TableField("company_id")
	private String companyId;
    /**
     * 雇员主表ID
     */
	@TableField("employee_id")
	private String employeeId;
    /**
     * 企业公积金账户Id, 关联至HF_ComAccount
     */
	@TableField("com_account_id")
	private Integer comAccountId;
    /**
     * 外键：企业公积金账户分类
     */
	@TableField("com_account_class_id")
	private Long comAccountClassId;
    /**
     * 公积金类型: 1 基本  2 补充
            
     */
	@TableField("hf_type")
	private Integer hfType;
    /**
     * 雇员基本补充公积金账号
     */

    @TableField(value="hf_emp_account")
	private String hfEmpAccount;
    /**
     * 公积金状态 : 0-未办理  1-已办  2-已做 3-封存
     */
	@TableField("archive_status")
	private Integer archiveStatus;
    /**
     * 任务单公积金状态 : 1-已办  2-已做 3-封存
            
     */
	@TableField("archive_task_status")
	private Integer archiveTaskStatus;
    /**
     * 公积金起缴月份
     */
	@TableField("start_month")
	private String startMonth;
    /**
     * 公积金转出封存月份
     */
	@TableField("end_month")
	private String endMonth;
    /**
     * 操作提示:  1 要做、2 中心、3 中智、4 原单位、5 其他独立开户公司、6 外包 
     */
	@TableField("operation_remind")
	private Integer operationRemind;
    /**
     * 操作提示日期
     */
	@TableField("operation_remind_date")
	private LocalDate operationRemindDate;
    /**
     * 所属供应商：1 af 2  bpo
     */
	@TableField("belong_vendor")
	private String belongVendor;
    /**
     * 所属基本公积金档案ID,如果记录是补充公积金,该字段必填 
     */
	@TableField("belong_emp_archive_id")
	private Long belongEmpArchiveId;
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
     * 福利办理方
     */
    @TableField("welfare_unit")
    private Integer welfareUnit;
    /**
     * 服务中心ID
     */
    @TableField("service_center_id")
    private Integer serviceCenterId;
    /**
     * 服务中心
     */
    @TableField("service_center")
    private String serviceCenter;
    /**
     * 入职日期
     */
    @TableField("in_date")
    private LocalDate inDate;
    /**
     * 离职时间
     */
    @TableField("out_date")
    private LocalDate outDate;


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

	public LocalDate getOperationRemindDate() {
		return operationRemindDate;
	}

	public void setOperationRemindDate(LocalDate operationRemindDate) {
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

    public LocalDate getInDate() {
        return inDate;
    }

    public void setInDate(LocalDate inDate) {
        this.inDate = inDate;
    }

    public LocalDate getOutDate() {
        return outDate;
    }

    public void setOutDate(LocalDate outDate) {
        this.outDate = outDate;
    }

    @Override
	public String toString() {
		return "HfEmpArchive{" +
			", empArchiveId=" + empArchiveId +
			", companyId=" + companyId +
			", employeeId=" + employeeId +
			", comAccountId=" + comAccountId +
			", comAccountClassId=" + comAccountClassId +
			", hfType=" + hfType +
			", hfEmpAccount=" + hfEmpAccount +
			", archiveStatus=" + archiveStatus +
			", archiveTaskStatus=" + archiveTaskStatus +
			", startMonth=" + startMonth +
			", endMonth=" + endMonth +
            ", inDate=" + inDate +
            ", outDate" + outDate +
			", operationRemind=" + operationRemind +
			", operationRemindDate=" + operationRemindDate +
			", belongVendor=" + belongVendor +
			", belongEmpArchiveId=" + belongEmpArchiveId +
			", isActive=" + isActive +
			", createdTime=" + createdTime +
			", modifiedTime=" + modifiedTime +
			", createdBy=" + createdBy +
			", modifiedBy=" + modifiedBy +
            ", welfareUnit=" + welfareUnit +
            ", serviceCenterId=" + serviceCenterId +
            ", serviceCenter=" + serviceCenter +
			"}";
	}
}
