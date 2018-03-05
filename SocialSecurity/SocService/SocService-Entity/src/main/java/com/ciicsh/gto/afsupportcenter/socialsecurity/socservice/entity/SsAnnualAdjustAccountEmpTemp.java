package com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.handler.inter.IExcelModel;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 */
@TableName("ss_annual_adjust_account_emp_temp")
public class SsAnnualAdjustAccountEmpTemp implements Serializable, IExcelModel {

    private static final long serialVersionUID = 1L;

	@TableId(value="annual_adjust_account_emp_temp_id", type= IdType.AUTO)
	private Long annualAdjustAccountEmpTempId;
	@TableField("annual_adjust_account_id")
	private Long annualAdjustAccountId;
	@TableField("account_status")
	private Integer accountStatus;
	@TableField("ss_serial")
    @Excel(name = "号")
    @NotBlank(message = "序号不能为空")
    @Pattern(regexp="^[1-9]([0-9]{0,9})?$", message = "序号格式不正确")
	private String ssSerial;
    @Excel(name = "姓名", orderNum = "1")
    @NotBlank(message = "姓名不能为空")
    @Length(max=100, message = "姓名最大长度为100位")
	@TableField("employee_name")
	private String employeeName;
    @Excel(name = "身份证号", orderNum = "2")
    @NotBlank(message = "身份证号不能为空")
    @Length(max=18, message = "身份证号最大长度为18位")
	@TableField("id_num")
	private String idNum;
    @Excel(name = "本单位缴费月数", orderNum = "3")
    @Pattern(regexp="^[1-9]([0-9]{0,3})?$", message = "本单位缴费月数格式不正确")
	@TableField("payment_months")
	private String paymentMonths;
	@TableField("avg_month_salary")
	private String avgMonthSalary;
	@TableField("error_msg")
	private String errorMsg;
    @TableField("order_num")
    private Integer orderNum;

	public Long getAnnualAdjustAccountEmpTempId() {
		return annualAdjustAccountEmpTempId;
	}

	public void setAnnualAdjustAccountEmpTempId(Long annualAdjustAccountEmpTempId) {
		this.annualAdjustAccountEmpTempId = annualAdjustAccountEmpTempId;
	}

	public Long getAnnualAdjustAccountId() {
		return annualAdjustAccountId;
	}

	public void setAnnualAdjustAccountId(Long annualAdjustAccountId) {
		this.annualAdjustAccountId = annualAdjustAccountId;
	}

	public Integer getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(Integer accountStatus) {
		this.accountStatus = accountStatus;
	}

	public String getSsSerial() {
		return ssSerial;
	}

	public void setSsSerial(String ssSerial) {
		this.ssSerial = ssSerial;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getIdNum() {
		return idNum;
	}

	public void setIdNum(String idNum) {
		this.idNum = idNum;
	}

	public String getPaymentMonths() {
		return paymentMonths;
	}

	public void setPaymentMonths(String paymentMonths) {
		this.paymentMonths = paymentMonths;
	}

	public String getAvgMonthSalary() {
		return avgMonthSalary;
	}

	public void setAvgMonthSalary(String avgMonthSalary) {
		this.avgMonthSalary = avgMonthSalary;
	}

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
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
		return "SsAnnualAdjustAccountEmpTemp{" +
			", annualAdjustAccountEmpTempId=" + annualAdjustAccountEmpTempId +
			", annualAdjustAccountId=" + annualAdjustAccountId +
			", accountStatus=" + accountStatus +
			", ssSerial=" + ssSerial +
			", employeeName=" + employeeName +
			", idNum=" + idNum +
			", paymentMonths=" + paymentMonths +
			", avgMonthSalary=" + avgMonthSalary +
			", errorMsg=" + errorMsg +
            ", orderNum=" + orderNum +
			"}";
	}
}
