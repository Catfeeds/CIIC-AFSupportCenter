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
 * 用工主表
 * </p>
 */
@TableName("am_employment")
public class AmEmployment implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	@TableId(value="employment_id", type= IdType.AUTO)
	private Long employmentId;
    /**
     * 客户Id
     */
	@TableField("company_id")
	private String companyId;
    /**
     * 雇员id
     */
	@TableField("employee_id")
	private String employeeId;
    /**
     * 外键 预增雇员信息
     */
	@TableField("emp_prior_id")
	private Long empPriorId;
    /**
     * 实际录用日期
     */
	@TableField("employ_date")
	private LocalDate employDate;
    /**
     * 开AF单日期 (档案部办理日期)
     */
	@TableField("open_af_date")
	private LocalDate openAfDate;
    /**
     * 下拉内容：空、1全日制、2 其它
     */
	@TableField("employ_style")
	private String employStyle;
    /**
     * 空、外来从业人员、居住证、调档、属地管理、市人才、梅园路、商城路、漕虹路、区人才、高校、经营者、厂长经理人才、农民工、退休、协保、退工不调档、用工不调档、其他、非全日制、中智、徐职、公司自行保管
     */
	@TableField("handle_type")
	private String handleType;
    /**
     * 空、中智、外包、独立
     */
	@TableField("employ_property")
	private String employProperty;
    /**
     * 空、Ukey、集体转入,用工自办、翻牌、无材料用工、网办无材料、转人员性质、新进转人员性质、送外区办、修改信息、外来新进、外来转入
     */
	@TableField("employ_way")
	private String employWay;
    /**
     * 用工操作员
     */
	@TableField("employ_operate_man")
	private String employOperateMan;
    /**
     * 是否可用
     */
	@TableField("is_active")
	private Integer isActive;
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

    @TableField("emp_task_id")
    private Long  empTaskId;

    public Long getEmpTaskId() {
        return empTaskId;
    }

    public void setEmpTaskId(Long empTaskId) {
        this.empTaskId = empTaskId;
    }

	public Long getEmploymentId() {
		return employmentId;
	}

	public void setEmploymentId(Long employmentId) {
		this.employmentId = employmentId;
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

	public Long getEmpPriorId() {
		return empPriorId;
	}

	public void setEmpPriorId(Long empPriorId) {
		this.empPriorId = empPriorId;
	}

	public LocalDate getEmployDate() {
		return employDate;
	}

	public void setEmployDate(LocalDate employDate) {
		this.employDate = employDate;
	}

	public LocalDate getOpenAfDate() {
		return openAfDate;
	}

	public void setOpenAfDate(LocalDate openAfDate) {
		this.openAfDate = openAfDate;
	}

	public String getEmployStyle() {
		return employStyle;
	}

	public void setEmployStyle(String employStyle) {
		this.employStyle = employStyle;
	}

	public String getHandleType() {
		return handleType;
	}

	public void setHandleType(String handleType) {
		this.handleType = handleType;
	}

	public String getEmployProperty() {
		return employProperty;
	}

	public void setEmployProperty(String employProperty) {
		this.employProperty = employProperty;
	}

	public String getEmployWay() {
		return employWay;
	}

	public void setEmployWay(String employWay) {
		this.employWay = employWay;
	}

	public String getEmployOperateMan() {
		return employOperateMan;
	}

	public void setEmployOperateMan(String employOperateMan) {
		this.employOperateMan = employOperateMan;
	}

	public Integer getIsActive() {
		return isActive;
	}

	public void setIsActive(Integer isActive) {
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
		return "AmEmployment{" +
			", employmentId=" + employmentId +
			", companyId=" + companyId +
			", employeeId=" + employeeId +
			", empPriorId=" + empPriorId +
			", employDate=" + employDate +
			", openAfDate=" + openAfDate +
			", employStyle=" + employStyle +
			", handleType=" + handleType +
			", employProperty=" + employProperty +
			", employWay=" + employWay +
			", employOperateMan=" + employOperateMan +
			", isActive=" + isActive +
			", createdTime=" + createdTime +
			", modifiedTime=" + modifiedTime +
			", createdBy=" + createdBy +
			", modifiedBy=" + modifiedBy +
			"}";
	}
}
