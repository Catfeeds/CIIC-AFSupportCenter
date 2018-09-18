package com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class EmpTaskDetailBO {
    /**
     * 雇员任务单ID
     */
    private Long empTaskId;
    /**
     * 客户编号
     */
    private String companyId;
    /**
     * 雇员编号
     */
    private String employeeId;
    /**
     * 任务类型：1 新增(新开) 、2 新增（转入） 、3 新增（启封）、4 调整（封存）、5 调整（启封）、
     6 补缴、7 离职（转出）、8 离职（封存）、9 转移、 10 特殊任务  11 集体转入  12  集体转出  13 翻牌
     */
    private Integer taskCategory;
    /**
     * 办理状态：1、未处理 2 、处理中(已办)  3 已完成(已做) 4、批退 5、不需处理
     */
    private Integer taskStatus;
    /**
     * 雇员基数
     */
    private BigDecimal empBase;
    /**
     * 个人比例
     */
    private BigDecimal ratioEmp;
    /**
     * 企业比例
     */
    private BigDecimal ratioCom;
    /**
     * 金额
     */
    private BigDecimal amount;
    /**
     * 操作提示:  1 要做、2 中心、3 中智、4 原单位、5 其他独立开户公司、6 外包
     */
    private Integer operationRemind;
    /**
     * 操作提示日期
     */
    private LocalDate operationRemindDate;
    /**
     * 外键:雇员公积金档案主表
     */
    private Long empArchiveId;
    /**
     * 是否更正 1 是 0 否
     */
    private Integer isChange;
    /**
     * 公积金类型:1 基本 2 补充
     */
    private Integer hfType;
    /**
     * 缴费段开始月份YYYYMM
     */
    private String startMonth;
    /**
     * 缴费段结束月份YYYYMM
     */
    private String endMonth;
    /**
     * 办理年月
     */
    private String handleMonth;
    /**
     * 办理备注
     */
    private String handleRemark;
    /**
     * 批退备注
     */
    private String rejectionRemark;
    /**
     * 福利办理方
     */
    private Integer welfareUnit;
    /**
     * 发起人ID
     */
    private String submitterId;
    /**
     * 发起时间
     */
    private LocalDate submitTime;
    /**
     * 创建时间
     */
    private LocalDateTime createdTime;
    /**
     * 最后更新时间
     */
    private LocalDateTime modifiedTime;
    /**
     * 创建者登录名
     */
    private String createdBy;
    /**
     * 创建者姓名
     */
    private String createdDisplayName;
    /**
     * 修改者ID
     */
    private String modifiedBy;
    /**
     * 修改者姓名
     */
    private String modifiedDisplayName;
    /**
     * 领导ID
     */
    private String leaderShipId;
    /**
     * 领导姓名
     */
    private String leaderShipName;
    /**
     * 服务中心ID
     */
    private Integer serviceCenterId;
    /**
     * 服务中心
     */
    private String serviceCenter;
    /**
     * 旧城市编码
     */
    private String oldCityCode;
    /**
     * 新城市编码
     */
    private String newCityCode;
    /**
     * 入离职ID
     */
    private Long empCompanyId;
}
