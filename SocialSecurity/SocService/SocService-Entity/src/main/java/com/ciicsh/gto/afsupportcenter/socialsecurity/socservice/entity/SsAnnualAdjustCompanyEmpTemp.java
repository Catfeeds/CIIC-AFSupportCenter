package com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import cn.afterturn.easypoi.handler.inter.IExcelModel;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.ciicsh.gto.afsupportcenter.util.constant.DictUtil;
import com.ciicsh.gto.afsupportcenter.util.constant.SocialSecurityConst;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Map;
import java.util.Optional;

/**
 * <p>
 * 
 * </p>
 */
@TableName("ss_annual_adjust_company_emp_temp")
@ExcelTarget("annualAdjustCompanyEmpTemp")
public class SsAnnualAdjustCompanyEmpTemp implements Serializable, IExcelModel {

    private static final long serialVersionUID = 1L;

    @TableId(value="annual_adjust_company_emp_temp_id", type= IdType.AUTO)
    private Long annualAdjustCompanyEmpTempId;

	@TableField("annual_adjust_company_id")
	private Long annualAdjustCompanyId;
    /**
     * 雇员编号ID
     */
	@TableField("employee_id")
    @Excel(name = "雇员编号")
    @NotBlank(message = "雇员编号不能为空")
    @Length(max=16, message = "雇员编号最大长度为16位")
	private String employeeId;
	@TableField("employee_name")
    @Excel(name = "雇员姓名", orderNum = "1")
    @NotBlank(message = "雇员姓名不能为空")
    @Length(max=100, message = "雇员姓名最大长度为100位")
	private String employeeName;
    /**
     * 社保序号
     */
    @TableField("ss_serial")
    @Excel(name = "社保序号", orderNum = "2")
    @NotBlank(message = "社保序号不能为空")
    @Length(max=10, message = "社保序号最大长度为10位")
	private String ssSerial;
    @Excel(name = "工资", orderNum = "3")
    @NotNull(message = "工资不能为空")
    @Pattern(regexp="(^[1-9]([0-9]{1,7})?(\\.[0-9]{1,2})?$)|(^(0){1}$)|(^[0-9]\\.[0-9]([0-9])?$)", message = "工资格式不正确")
	private String salary;
    @TableField("chg_salary")
    @Excel(name = "待调工资", orderNum = "3")
    @NotNull(message = "待调工资不能为空")
    @Pattern(regexp="(^[1-9]([0-9]{1,7})?(\\.[0-9]{1,2})?$)|(^(0){1}$)|(^[0-9]\\.[0-9]([0-9])?$)", message = "待调工资格式不正确")
    private String chgSalary;
	@TableField("id_num")
    @Excel(name = "身份证号", orderNum = "4")
    @NotBlank(message = "身份证号不能为空")
    @Pattern(regexp="^(\\d{15}$|^\\d{18}$|^\\d{17}(\\d|X|x))$", message = "身份证号格式不正确")
	private String idNum;
	@TableField("archive_status")
    @Excel(name = "社保状态", orderNum = "5")
    @Pattern(regexp="^[0-3]{1}$", message = "社保状态不正确")
	private String archiveStatus;
    @TableField(exist = false)
	private String archiveStatusName;
    /**
     * 基数, 五险合一(基数一致）时有效
     */
	@TableField("base_amount")
    @Excel(name = "社保基数", orderNum = "8")
    @NotNull(message = "社保基数不能为空")
    @Pattern(regexp="(^[1-9]([0-9]{1,5})?(\\.[0-9]{1,2})?$)|(^(0){1}$)|(^[0-9]\\.[0-9]([0-9])?$)", message = "社保基数格式不正确")
	private String baseAmount;
    /**
     * 账户类型：1:中智大库 2中智外包 3独立户
     */
	@TableField("ss_account_type")
    @Excel(name = "账户类型", orderNum = "6")
    @Pattern(regexp="^[1-3]{1}$", message = "账户类型不正确")
	private String ssAccountType;
	@TableField(exist = false)
	private String ssAccountTypeName;
	@TableField("emp_classify")
    @Excel(name = "人员属性", orderNum = "7")
    @Pattern(regexp="^[1-5]{1}$", message = "人员属性不正确")
	private String empClassify;
	@TableField(exist = false)
	private String empClassifyName;
    /**
     * 结算区县(社保局所在上海地区)
     */
	@TableField("settlement_area")
    @Excel(name = "结算区县", orderNum = "9")
    @Length(max=20, message = "结算区县最大长度为20位")
	private String settlementArea;
	@TableField(exist = false)
	private String settlementAreaName;
    /**
     * 企业社保账户
     */
	@TableField("ss_account")
    @Excel(name = "企业社保账户", orderNum = "10")
    @Length(max=20, message = "企业社保账户最大长度为20位")
	private String ssAccount;
    /**
     * 养老金独立开户用户名（使用U盾登陆的用户名）
     */
	@TableField("ss_username")
    @Excel(name = "养老金独立开户用户名", orderNum = "11")
    @Length(max=20, message = "养老金独立开户用户名最大长度为20位")
	private String ssUsername;
    /**
     * 养老金独立开户密码（使用U盾登陆的密码）
     */
	@TableField("ss_pwd")
    @Excel(name = "养老金独立开户密码", orderNum = "12")
    @Length(max=20, message = "养老金独立开户密码最大长度为20位")
	private String ssPwd;

    @TableField("company_id")
    @Excel(name = "客户编号", orderNum = "13")
    @NotBlank(message = "客户编号不能为空")
    @Length(max=16, message = "客户编号最大长度为16位")
	private String companyId;
    @TableField("low_department_name")
    @Excel(name = "客户经理", orderNum = "14")
    private String lowDepartmentName;
    @TableField("high_department_name")
    @Excel(name = "客户总监", orderNum = "15")
    private String highDepartmentName;
    @TableField("error_msg")
	private String errorMsg;
    @TableField("order_num")
    private Integer orderNum;

    public String getChgSalary() {
        return chgSalary;
    }

    public void setChgSalary(String chgSalary) {
        this.chgSalary = chgSalary;
    }

    public Long getAnnualAdjustCompanyId() {
		return annualAdjustCompanyId;
	}

	public void setAnnualAdjustCompanyId(Long annualAdjustCompanyId) {
		this.annualAdjustCompanyId = annualAdjustCompanyId;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getSsSerial() {
		return ssSerial;
	}

	public void setSsSerial(String ssSerial) {
		this.ssSerial = ssSerial;
	}

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}

	public String getIdNum() {
		return idNum;
	}

	public void setIdNum(String idNum) {
		this.idNum = idNum;
	}

	public String getArchiveStatus() {
		return archiveStatus;
	}

	public void setArchiveStatus(String archiveStatus) {
	    if (archiveStatus != null) {
//            Optional<Map.Entry<String, String>> optional = SocialSecurityConst.ACCOUNT_STATUS_MAP.entrySet().stream().filter(
//                t -> archiveStatus.equals(t.getValue())
//            ).findFirst();
//            if (optional.isPresent()) {
//                this.archiveStatus = optional.get().getKey();
//            } else {
//                this.archiveStatus = archiveStatus;
//            }
            this.archiveStatus = DictUtil.getInstance().getValueByItemTextAndTypeValue(archiveStatus, DictUtil.TYPE_VALUE_SOCIAL_SECURITY_STATUS, true);
        }
//        this.archiveStatus = archiveStatus;
	}

	public String getBaseAmount() {
		return baseAmount;
	}

	public void setBaseAmount(String baseAmount) {
		this.baseAmount = baseAmount;
	}

	public String getSsAccountType() {
		return ssAccountType;
	}

	public void setSsAccountType(String ssAccountType) {
        if (ssAccountType != null) {
//            Optional<Map.Entry<String, String>> optional = SocialSecurityConst.ACCOUNT_TYPE_MAP.entrySet().stream().filter(
//                t -> ssAccountType.equals(t.getValue())
//            ).findFirst();
//            if (optional.isPresent()) {
//                this.ssAccountType = optional.get().getKey();
//            } else {
//                this.ssAccountType = ssAccountType;
//            }
            this.ssAccountType = DictUtil.getInstance().getValueByItemTextAndTypeValue(ssAccountType, DictUtil.TYPE_VALUE_SOCIAL_SECURITY_ACCOUNT_TYPE, true);
        }
//        this.ssAccountType = ssAccountType;
	}

	public String getEmpClassify() {
		return empClassify;
	}

	public void setEmpClassify(String empClassify) {
        if (empClassify != null) {
//            Optional<Map.Entry<String, String>> optional = SocialSecurityConst.EMP_CLASSIFY_MAP.entrySet().stream().filter(
//                t -> empClassify.equals(t.getValue())
//            ).findFirst();
//            if (optional.isPresent()) {
//                this.empClassify = optional.get().getKey();
//            } else {
//                this.empClassify = empClassify;
//            }
            this.empClassify = DictUtil.getInstance().getValueByItemTextAndTypeValue(empClassify, DictUtil.TYPE_VALUE_SOCIAL_SECURITY_EMPLOYEE_CLASSIFY, true);
        }
//        this.empClassify = empClassify;
	}

	public String getSettlementArea() {
		return settlementArea;
	}

	public void setSettlementArea(String settlementArea) {
        if (settlementArea != null) {
            Optional<Map.Entry<String, String>> optional = SocialSecurityConst.DISTRICT_MAP.entrySet().stream().filter(
                t -> settlementArea.equals(t.getValue())
            ).findFirst();
            if (optional.isPresent()) {
                this.settlementArea = optional.get().getKey();
            } else {
                this.settlementArea = settlementArea;
            }
        }
	}

	public String getSsAccount() {
		return ssAccount;
	}

	public void setSsAccount(String ssAccount) {
		this.ssAccount = ssAccount;
	}

	public String getSsUsername() {
		return ssUsername;
	}

	public void setSsUsername(String ssUsername) {
		this.ssUsername = ssUsername;
	}

	public String getSsPwd() {
		return ssPwd;
	}

	public void setSsPwd(String ssPwd) {
		this.ssPwd = ssPwd;
	}

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public Long getAnnualAdjustCompanyEmpTempId() {
        return annualAdjustCompanyEmpTempId;
    }

    public void setAnnualAdjustCompanyEmpTempId(Long annualAdjustCompanyEmpTempId) {
        this.annualAdjustCompanyEmpTempId = annualAdjustCompanyEmpTempId;
    }

    public String getLowDepartmentName() {
        return lowDepartmentName;
    }

    public void setLowDepartmentName(String lowDepartmentName) {
        this.lowDepartmentName = lowDepartmentName;
    }

    public String getHighDepartmentName() {
        return highDepartmentName;
    }

    public void setHighDepartmentName(String highDepartmentName) {
        this.highDepartmentName = highDepartmentName;
    }

    @Override
    public String getErrorMsg() {
        return errorMsg;
    }

    @Override
    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public String getArchiveStatusName() {
        String archiveStatusName = DictUtil.getInstance().getTextByItemValueAndTypeValue(this.archiveStatus, DictUtil.TYPE_VALUE_SOCIAL_SECURITY_STATUS, false);
        if (archiveStatusName != null) {
            return archiveStatusName;
        } else {
            return this.archiveStatus;
        }
    }

    public void setArchiveStatusName(String archiveStatusName) {
        this.archiveStatusName = archiveStatusName;
    }

    public String getSsAccountTypeName() {
        String ssAccountTypeName = DictUtil.getInstance().getTextByItemValueAndTypeValue(this.ssAccountType, DictUtil.TYPE_VALUE_SOCIAL_SECURITY_ACCOUNT_TYPE, false);
        if (ssAccountTypeName != null) {
            return ssAccountTypeName;
        } else {
            return this.ssAccountType;
        }
    }

    public void setSsAccountTypeName(String ssAccountTypeName) {
        this.ssAccountTypeName = ssAccountTypeName;
    }

    public String getEmpClassifyName() {
        String empClassifyName = DictUtil.getInstance().getTextByItemValueAndTypeValue(this.empClassify, DictUtil.TYPE_VALUE_SOCIAL_SECURITY_EMPLOYEE_CLASSIFY, false);
        if (empClassifyName != null) {
            return empClassifyName;
        } else {
            return this.empClassify;
        }
    }

    public void setEmpClassifyName(String empClassifyName) {
        this.empClassifyName = empClassifyName;
    }

    public String getSettlementAreaName() {
        String settlementAreaName = SocialSecurityConst.DISTRICT_MAP.get(this.settlementArea);
        if (settlementAreaName != null) {
            return settlementAreaName;
        } else {
            return this.settlementArea;
        }
    }

    public void setSettlementAreaName(String settlementAreaName) {
        this.settlementAreaName = settlementAreaName;
    }

    @Override
	public String toString() {
		return "SsAnnualAdjustCompanyEmpTemp{" +
            ", annualAdjustCompanyEmpTempId=" + annualAdjustCompanyEmpTempId +
			", annualAdjustCompanyId=" + annualAdjustCompanyId +
			", employeeId=" + employeeId +
			", employeeName=" + employeeName +
			", ssSerial=" + ssSerial +
			", salary=" + salary +
            ", chgSalary=" + chgSalary +
			", idNum=" + idNum +
			", archiveStatus=" + archiveStatus +
			", baseAmount=" + baseAmount +
			", ssAccountType=" + ssAccountType +
			", empClassify=" + empClassify +
			", settlementArea=" + settlementArea +
			", ssAccount=" + ssAccount +
			", ssUsername=" + ssUsername +
			", ssPwd=" + ssPwd +
            ", lowDepartmentName=" + lowDepartmentName +
            ", highDepartmentName=" + highDepartmentName +
            ", companyId=" + companyId +
            ", errorMsg=" + errorMsg +
            ", orderNum=" + orderNum +
			"}";
	}
}
