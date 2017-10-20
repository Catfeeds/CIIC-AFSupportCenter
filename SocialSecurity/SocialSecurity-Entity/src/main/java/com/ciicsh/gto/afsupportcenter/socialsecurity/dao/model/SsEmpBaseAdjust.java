package com.ciicsh.gto.afsupportcenter.socialsecurity.dao.model;

import com.ciicsh.gto.afsupportcenter.util.model.BasicModel;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.*;

/**
 * 雇员社保基数调整历史月差异表
 */
@Table(name = "SS_EmpBaseAdjust")
public class SsEmpBaseAdjust extends BasicModel implements Serializable {
    /**
     * 记录Id
     */
    @Id
    @Column(name = "EmpBaseAdjustId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long empBaseAdjustId;

    /**
     * 外键，雇员社保档案Id
     */
    @Column(name = "EmpArchivedId")
    private String empArchivedId;

    /**
     * 雇员本地社保任务单Id
     */
    @Column(name = "EmployeeTaskId")
    private String employeeTaskId;

    /**
     * 处理方式 1 -网上申报 2 柜面办理
     */
    @Column(name = "ProcessWay")
    private Boolean processWay;

    /**
     * 调整新基数
     */
    @Column(name = "NewBaseAmount")
    private BigDecimal newBaseAmount;

    /**
     * 所属社保月份yyyyMM
     */
    @Column(name = "SOCMonth")
    private String SOCMonth;

    /**
     * 开始月份yyyyMM
     */
    @Column(name = "StartMonth")
    private String startMonth;

    /**
     * 结束月份yyyyMM
     */
    @Column(name = "EndMonth")
    private String endMonth;

    /**
     * 和上一次年调后的企业缴纳部分金额的差额合计
     */
    @Column(name = "ComDiffSumAmount")
    private BigDecimal comDiffSumAmount;

    /**
     * 和上一次年调后的雇员缴纳部分金额的差额合计
     */
    @Column(name = "EmpDiffSumAmount")
    private BigDecimal empDiffSumAmount;

    /**
     * 企业个人缴纳金额的差额合计
     */
    @Column(name = "ComEmpSumDiffAmount")
    private BigDecimal comEmpSumDiffAmount;

    private static final long serialVersionUID = 1L;

    /**
     * 获取记录Id
     *
     * @return EmpBaseAdjustId - 记录Id
     */
    public Long getEmpBaseAdjustId() {
        return empBaseAdjustId;
    }

    /**
     * 设置记录Id
     *
     * @param empBaseAdjustId 记录Id
     */
    public void setEmpBaseAdjustId(Long empBaseAdjustId) {
        this.empBaseAdjustId = empBaseAdjustId;
    }

    /**
     * 获取外键，雇员社保档案Id
     *
     * @return EmpArchivedId - 外键，雇员社保档案Id
     */
    public String getEmpArchivedId() {
        return empArchivedId;
    }

    /**
     * 设置外键，雇员社保档案Id
     *
     * @param empArchivedId 外键，雇员社保档案Id
     */
    public void setEmpArchivedId(String empArchivedId) {
        this.empArchivedId = empArchivedId == null ? null : empArchivedId.trim();
    }

    /**
     * 获取雇员本地社保任务单Id
     *
     * @return EmployeeTaskId - 雇员本地社保任务单Id
     */
    public String getEmployeeTaskId() {
        return employeeTaskId;
    }

    /**
     * 设置雇员本地社保任务单Id
     *
     * @param employeeTaskId 雇员本地社保任务单Id
     */
    public void setEmployeeTaskId(String employeeTaskId) {
        this.employeeTaskId = employeeTaskId == null ? null : employeeTaskId.trim();
    }

    /**
     * 获取处理方式 1 -网上申报 2 柜面办理
     *
     * @return ProcessWay - 处理方式 1 -网上申报 2 柜面办理
     */
    public Boolean getProcessWay() {
        return processWay;
    }

    /**
     * 设置处理方式 1 -网上申报 2 柜面办理
     *
     * @param processWay 处理方式 1 -网上申报 2 柜面办理
     */
    public void setProcessWay(Boolean processWay) {
        this.processWay = processWay;
    }

    /**
     * 获取调整新基数
     *
     * @return NewBaseAmount - 调整新基数
     */
    public BigDecimal getNewBaseAmount() {
        return newBaseAmount;
    }

    /**
     * 设置调整新基数
     *
     * @param newBaseAmount 调整新基数
     */
    public void setNewBaseAmount(BigDecimal newBaseAmount) {
        this.newBaseAmount = newBaseAmount;
    }

    /**
     * 获取所属社保月份yyyyMM
     *
     * @return SOCMonth - 所属社保月份yyyyMM
     */
    public String getSOCMonth() {
        return SOCMonth;
    }

    /**
     * 设置所属社保月份yyyyMM
     *
     * @param SOCMonth 所属社保月份yyyyMM
     */
    public void setSOCMonth(String SOCMonth) {
        this.SOCMonth = SOCMonth == null ? null : SOCMonth.trim();
    }

    /**
     * 获取开始月份yyyyMM
     *
     * @return StartMonth - 开始月份yyyyMM
     */
    public String getStartMonth() {
        return startMonth;
    }

    /**
     * 设置开始月份yyyyMM
     *
     * @param startMonth 开始月份yyyyMM
     */
    public void setStartMonth(String startMonth) {
        this.startMonth = startMonth == null ? null : startMonth.trim();
    }

    /**
     * 获取结束月份yyyyMM
     *
     * @return EndMonth - 结束月份yyyyMM
     */
    public String getEndMonth() {
        return endMonth;
    }

    /**
     * 设置结束月份yyyyMM
     *
     * @param endMonth 结束月份yyyyMM
     */
    public void setEndMonth(String endMonth) {
        this.endMonth = endMonth == null ? null : endMonth.trim();
    }

    /**
     * 获取和上一次年调后的企业缴纳部分金额的差额合计
     *
     * @return ComDiffSumAmount - 和上一次年调后的企业缴纳部分金额的差额合计
     */
    public BigDecimal getComDiffSumAmount() {
        return comDiffSumAmount;
    }

    /**
     * 设置和上一次年调后的企业缴纳部分金额的差额合计
     *
     * @param comDiffSumAmount 和上一次年调后的企业缴纳部分金额的差额合计
     */
    public void setComDiffSumAmount(BigDecimal comDiffSumAmount) {
        this.comDiffSumAmount = comDiffSumAmount;
    }

    /**
     * 获取和上一次年调后的雇员缴纳部分金额的差额合计
     *
     * @return EmpDiffSumAmount - 和上一次年调后的雇员缴纳部分金额的差额合计
     */
    public BigDecimal getEmpDiffSumAmount() {
        return empDiffSumAmount;
    }

    /**
     * 设置和上一次年调后的雇员缴纳部分金额的差额合计
     *
     * @param empDiffSumAmount 和上一次年调后的雇员缴纳部分金额的差额合计
     */
    public void setEmpDiffSumAmount(BigDecimal empDiffSumAmount) {
        this.empDiffSumAmount = empDiffSumAmount;
    }

    /**
     * 获取企业个人缴纳金额的差额合计
     *
     * @return ComEmpSumDiffAmount - 企业个人缴纳金额的差额合计
     */
    public BigDecimal getComEmpSumDiffAmount() {
        return comEmpSumDiffAmount;
    }

    /**
     * 设置企业个人缴纳金额的差额合计
     *
     * @param comEmpSumDiffAmount 企业个人缴纳金额的差额合计
     */
    public void setComEmpSumDiffAmount(BigDecimal comEmpSumDiffAmount) {
        this.comEmpSumDiffAmount = comEmpSumDiffAmount;
    }
}