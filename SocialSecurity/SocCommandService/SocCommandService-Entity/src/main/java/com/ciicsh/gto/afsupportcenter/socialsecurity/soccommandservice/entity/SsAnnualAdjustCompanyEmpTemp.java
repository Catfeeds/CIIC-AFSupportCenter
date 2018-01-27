package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity;

import java.math.BigDecimal;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import cn.afterturn.easypoi.handler.inter.IExcelModel;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.*;
import java.io.Serializable;

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
	@TableField("id_num")
    @Excel(name = "身份证号", orderNum = "4")
    @NotBlank(message = "身份证号不能为空")
    @Pattern(regexp="^(\\d{15}$|^\\d{18}$|^\\d{17}(\\d|X|x))$", message = "身份证号格式不正确")
	private String idNum;
	@TableField("archive_status")
    @Excel(name = "社保状态", replace = {"初始_0", "有效_1", "终止_2", "封存_3"}, orderNum = "5")
    @Pattern(regexp="^[0-3]{1}$", message = "社保状态不正确")
	private String archiveStatus;
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
    @Excel(name = "账户类型", replace = {"中智大库_1", "中智外包_2", "独立户_3"}, orderNum = "6")
    @Pattern(regexp="^[1-3]{1}$", message = "账户类型不正确")
	private String ssAccountType;
	@TableField("emp_classify")
    @Excel(name = "人员属性", replace = {"本地_1", "外地_2", "外籍三险_3", "外籍五险_4", "延迟退休人员_5"}, orderNum = "7")
    @Pattern(regexp="^[1-5]{1}$", message = "人员属性不正确")
	private String empClassify;
    /**
     * 结算区县(社保局所在上海地区)
     */
	@TableField("settlement_area")
    @Excel(name = "结算区县", orderNum = "9")
    @Length(max=20, message = "结算区县最大长度为20位")
	private String settlementArea;
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
    @Excel(name = "所属小组", orderNum = "14")
    private String lowDepartmentName;
    @TableField("high_department_name")
    @Excel(name = "所属大组", orderNum = "15")
    private String highDepartmentName;
    @TableField("error_msg")
	private String errorMsg;

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
		this.archiveStatus = archiveStatus;
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
		this.ssAccountType = ssAccountType;
	}

	public String getEmpClassify() {
		return empClassify;
	}

	public void setEmpClassify(String empClassify) {
		this.empClassify = empClassify;
	}

	public String getSettlementArea() {
		return settlementArea;
	}

	public void setSettlementArea(String settlementArea) {
		this.settlementArea = settlementArea;
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

    @Override
	public String toString() {
		return "SsAnnualAdjustCompanyEmpTemp{" +
            ", annualAdjustCompanyEmpTempId=" + annualAdjustCompanyEmpTempId +
			", annualAdjustCompanyId=" + annualAdjustCompanyId +
			", employeeId=" + employeeId +
			", employeeName=" + employeeName +
			", ssSerial=" + ssSerial +
			", salary=" + salary +
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
			"}";
	}
}
