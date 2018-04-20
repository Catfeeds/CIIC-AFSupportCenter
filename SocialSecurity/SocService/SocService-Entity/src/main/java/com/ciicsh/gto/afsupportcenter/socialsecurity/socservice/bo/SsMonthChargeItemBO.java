package com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.SsMonthChargeItem;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 雇员月度费用明细项目
 * </p>
 */

@ExcelTarget("SsMonthChargeItemBO")
public class SsMonthChargeItemBO extends SsMonthChargeItem{
    //企业账户ID
    private Long comAccountId;
    //报表年月
    @Excel(name = "社保缴纳月", orderNum = "6")
    private String ssMonth;
    //社保序号
    @Excel(name = "社保序号", orderNum = "3")
    private String ssSerial;
    //雇员姓名
    @Excel(name = "雇员姓名", orderNum = "2")
    private String employeeName;
    //雇员ID
    @Excel(name = "雇员编号", orderNum = "1")
    private String employeeId;
    //公司编号
    @Excel(name = "客户编号", orderNum = "4")
    private String companyId;
    //社保账户类型
    @Excel(name = "社保账户类型", orderNum = "5", replace = {"中智大库_1","中智外包_2","独立户_3"})
    private Integer ssAccountType;
    //雇员属性
    private Integer empClassify;

    @Excel(name = "所属社保月份", orderNum = "7")
    private String ssMonthBelong;
    //费用种类：1标准 2 新进 3 转入  4 补缴 5 调整 （顺调)）6 转出 7封存 8 退账 9 调整（倒调）
    @Excel(name = "费用种类", orderNum = "8",replace = {"标准_1","新进_2","转入_3","补缴_4","调整 （顺调)_5","转出_6","封存_7","退账_8","调整（倒调）_9"} )
    private Integer costCategory;
    //社保基数
    @Excel(name = "社保基数", orderNum = "9")
    private BigDecimal baseAmount;
    //总计费用
    @Excel(name = "总计费用", orderNum = "25")
    private BigDecimal totalAmount;

    //养老金公司缴费
    @Excel(name = "养老金公司缴费", orderNum = "10")
    private BigDecimal pensionComFee;
    //养老金个人缴费
    @Excel(name = "养老金个人缴费", orderNum = "11")
    private BigDecimal pensionEmpFee;
    //养老金总计
    @Excel(name = "养老金总计", orderNum = "12")
    private BigDecimal pensionTotalFee;

    //医疗公司缴费
    @Excel(name = "医疗公司缴费", orderNum = "13")
    private BigDecimal medicalComFee;
    //医疗个人缴费
    @Excel(name = "医疗个人缴费", orderNum = "14")
    private BigDecimal medicalEmpFee;
    //医疗总计
    @Excel(name = "医疗总计", orderNum = "15")
    private BigDecimal medicalTotalFee;

    //失业公司缴费
    @Excel(name = "失业公司缴费", orderNum = "16")
    private BigDecimal unemploymentComFee;
    //失业个人缴费
    @Excel(name = "失业个人缴费", orderNum = "17")
    private BigDecimal unemploymentEmpFee;
    //失业总计
    @Excel(name = "失业总计", orderNum = "18")
    private BigDecimal unemploymentTotalFee;


    //工伤公司缴费
    @Excel(name = "工伤公司缴费", orderNum = "19")
    private BigDecimal injuryOnJobComFee;
    // 工伤个人缴费
    @Excel(name = "工伤个人缴费", orderNum = "20")
    private BigDecimal injuryOnJobEmpFee;
    //工伤总计
    @Excel(name = "工伤总计", orderNum = "21")
    private BigDecimal injuryOnJobTotalFee;

    //生育公司缴费
    @Excel(name = "生育公司缴费", orderNum = "22")
    private BigDecimal birthRiskComFee;
    //生育个人缴费
    @Excel(name = "生育个人缴费", orderNum = "23")
    private BigDecimal birthRiskEmpFee;
    //生育总计
    @Excel(name = "生育总计", orderNum = "24")
    private BigDecimal birthRiskTotalFee;
    //
    private String ssAccount;

    public String getSsMonth() {
        return ssMonth;
    }

    public void setSsMonth(String ssMonth) {
        this.ssMonth = ssMonth;
    }

    public Long getComAccountId() {
        return comAccountId;
    }

    public void setComAccountId(Long comAccountId) {
        this.comAccountId = comAccountId;
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

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public Integer getSsAccountType() {
        return ssAccountType;
    }

    public void setSsAccountType(Integer ssAccountType) {
        this.ssAccountType = ssAccountType;
    }

    public Integer getEmpClassify() {
        return empClassify;
    }

    public void setEmpClassify(Integer empClassify) {
        this.empClassify = empClassify;
    }

    public String getSsMonthBelong() {
        return ssMonthBelong;
    }

    public void setSsMonthBelong(String ssMonthBelong) {
        this.ssMonthBelong = ssMonthBelong;
    }

    public Integer getCostCategory() {
        return costCategory;
    }

    public void setCostCategory(Integer costCategory) {
        this.costCategory = costCategory;
    }

    public BigDecimal getBaseAmount() {
        return baseAmount;
    }

    public void setBaseAmount(BigDecimal baseAmount) {
        this.baseAmount = baseAmount;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getPensionComFee() {
        return pensionComFee;
    }

    public void setPensionComFee(BigDecimal pensionComFee) {
        this.pensionComFee = pensionComFee;
    }

    public BigDecimal getPensionEmpFee() {
        return pensionEmpFee;
    }

    public void setPensionEmpFee(BigDecimal pensionEmpFee) {
        this.pensionEmpFee = pensionEmpFee;
    }

    public BigDecimal getPensionTotalFee() {
        return pensionTotalFee;
    }

    public void setPensionTotalFee(BigDecimal pensionTotalFee) {
        this.pensionTotalFee = pensionTotalFee;
    }

    public BigDecimal getMedicalComFee() {
        return medicalComFee;
    }

    public void setMedicalComFee(BigDecimal medicalComFee) {
        this.medicalComFee = medicalComFee;
    }

    public BigDecimal getMedicalEmpFee() {
        return medicalEmpFee;
    }

    public void setMedicalEmpFee(BigDecimal medicalEmpFee) {
        this.medicalEmpFee = medicalEmpFee;
    }

    public BigDecimal getMedicalTotalFee() {
        return medicalTotalFee;
    }

    public void setMedicalTotalFee(BigDecimal medicalTotalFee) {
        this.medicalTotalFee = medicalTotalFee;
    }

    public BigDecimal getUnemploymentComFee() {
        return unemploymentComFee;
    }

    public void setUnemploymentComFee(BigDecimal unemploymentComFee) {
        this.unemploymentComFee = unemploymentComFee;
    }

    public BigDecimal getUnemploymentEmpFee() {
        return unemploymentEmpFee;
    }

    public void setUnemploymentEmpFee(BigDecimal unemploymentEmpFee) {
        this.unemploymentEmpFee = unemploymentEmpFee;
    }

    public BigDecimal getUnemploymentTotalFee() {
        return unemploymentTotalFee;
    }

    public void setUnemploymentTotalFee(BigDecimal unemploymentTotalFee) {
        this.unemploymentTotalFee = unemploymentTotalFee;
    }

    public BigDecimal getInjuryOnJobComFee() {
        return injuryOnJobComFee;
    }

    public void setInjuryOnJobComFee(BigDecimal injuryOnJobComFee) {
        this.injuryOnJobComFee = injuryOnJobComFee;
    }

    public BigDecimal getInjuryOnJobEmpFee() {
        return injuryOnJobEmpFee;
    }

    public void setInjuryOnJobEmpFee(BigDecimal injuryOnJobEmpFee) {
        this.injuryOnJobEmpFee = injuryOnJobEmpFee;
    }

    public BigDecimal getInjuryOnJobTotalFee() {
        return injuryOnJobTotalFee;
    }

    public void setInjuryOnJobTotalFee(BigDecimal injuryOnJobTotalFee) {
        this.injuryOnJobTotalFee = injuryOnJobTotalFee;
    }

    public BigDecimal getBirthRiskComFee() {
        return birthRiskComFee;
    }

    public void setBirthRiskComFee(BigDecimal birthRiskComFee) {
        this.birthRiskComFee = birthRiskComFee;
    }

    public BigDecimal getBirthRiskEmpFee() {
        return birthRiskEmpFee;
    }

    public void setBirthRiskEmpFee(BigDecimal birthRiskEmpFee) {
        this.birthRiskEmpFee = birthRiskEmpFee;
    }

    public BigDecimal getBirthRiskTotalFee() {
        return birthRiskTotalFee;
    }

    public void setBirthRiskTotalFee(BigDecimal birthRiskTotalFee) {
        this.birthRiskTotalFee = birthRiskTotalFee;
    }

    public String getSsAccount() {
        return ssAccount;
    }

    public void setSsAccount(String ssAccount) {
        this.ssAccount = ssAccount;
    }
}
