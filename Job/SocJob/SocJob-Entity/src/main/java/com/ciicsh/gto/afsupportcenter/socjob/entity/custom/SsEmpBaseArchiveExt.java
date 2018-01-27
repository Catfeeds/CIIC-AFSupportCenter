package com.ciicsh.gto.afsupportcenter.socjob.entity.custom;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Created by houwanhua on 2018/1/12.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SsEmpBaseArchiveExt {
    /**
     * 记录Id
     */
    private Long empBasePeriodId;
    /**
     * 外键，雇员本地社保档案Id
     */
    private Long empArchiveId;
    /**
     * 本地社保的雇员任务单Id
     */
    private Long empTaskId;
    /**
     * 基数, 五险合一(基数一致）时有效
     */
    private BigDecimal baseAmount;
    /**
     * 缴费段开始月份
     */
    private String startMonth;
    /**
     * 缴费段结束月份
     */
    private String endMonth;
    /**
     * 缴纳的社保月份
     */
    private String ssMonth;
    /**
     * 社保停缴办理月份和社保办理月份不可放在同一个字段。
     */
    private String ssMonthStop;
    /**
     * 缴纳方式:1 - 正常 2 - 补缴
     */
    private Integer remitWay;
    /**
     * 多租户
     */
    private String customerId;
    /**
     * 客户编号ID
     */
    private String companyId;
    /**
     * 雇员编号ID
     */
    private String employeeId;
    /**
     * 企业社保账户Id, 关联至SOC_SSAccount
     */
    private Long comAccountId;
    /**
     * 社保序号 :一个雇员在不同库之间序号可以相同,但在同一个库下面不可以重复,
     老系统的社保序号取数比较大而且还用到所有独立户,搞得序号特别稀有
     */
    private String ssSerial;
    /**
     * 实际工资
     */
    private BigDecimal salary;
    /**
     * 人员属性：本地、外地、外籍三险、外籍五险、延迟退休人员
     本地、外地、外籍五险：有五个险种
     外籍三险、延迟退休人员：有三个险种
     */
    private Integer empClassify;
    /**
     * 入职日期。为何要在此冗余添加雇员入离职表中的字段？原因是雇员入离职表被分开3张
     */
    private LocalDate inDate;
    /**
     * 离职时间
     */
    private LocalDate outDate;
    /**
     * 社保档案状态 : 0-未办理  1-已办  2-已做 3-转出
     */
    private Integer archiveStatus;
    /**
     * 社保档案任务状态 : 1-已办  2-已做 3-转出
     */
    private Integer archiveTaskStatus;

}
