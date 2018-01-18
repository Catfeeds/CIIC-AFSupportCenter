package com.ciicsh.gto.afsupportcenter.socjob.entity.custom;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * Created by houwanhua on 2018/1/15.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SsEmpBasePeriodExt {
    /**
     * 记录Id
     */
    private Long empBasePeriodId;
    /**
     * 外键，雇员本地社保档案Id
     */
    private Long empArchiveId;
    /**
     * 基数, 五险合一(基数一致）时有效
     */
    private BigDecimal baseAmount;

    /**
     * 总金额
     */
    private BigDecimal totalAmount;

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
     * 所属社保月份，格式为yyyyMM
     */
    private String ssMonthBelong;
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
     * 费用类别：1标准 2 新进 3 转入  4 补缴 5 调整 6 转出 7封存 8 退账
     */
    private Integer category;
    /**
     * 费用类别名称
     */
    private String categoryName;
}
