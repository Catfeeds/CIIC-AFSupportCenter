package com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.dto.AmEmpTaskDTO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.SsEmpBaseDetail;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.SsEmpBasePeriod;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.SsEmpTask;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.SsEmpTaskPeriod;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * SsEmpTask DTO
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ExcelTarget("SsEmpTaskBO")
public class SsEmpTaskBO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long empTaskId;
    private String customerId;
    private Long empArchiveId;
    private Integer isChange;
    @Excel(name = "任务单类型",replace = {"新进_1","转入_2","调整_3","补缴_4","转出_5","封存_6","退账_7","集体转入_10","集体转出_11"}, orderNum = "1")
    private Integer taskCategory;
    private Integer taskCategorySpecial;
    @Excel(name = "是否加急",replace={"是_1","否_0","否_null"}, orderNum = "2")
    private Integer urgent;
    private String submitterId;
    private String submitterName;
    private String submitterDeptId;
    private String submitterDeptName;
    @Excel(name = "发起时间", orderNum = "9")
    private LocalDateTime submitTime;
    private LocalDate expireDate;
    private String submitterRemark;
    private String handleUserId;
    private String handleUserName;
    private String empSsSerial;
    private Integer handleWay;
    private String handleMonth;
    @Excel(name = "备注", orderNum = "10")
    private String handleRemark;
    private String handleRemarkMan;
    private LocalDate handleRemarkDate;
    private String rejectionRemark;
    private String rejectionRemarkMan;
    private LocalDate rejectionRemarkDate;
    private Integer taskStatus;
    private Integer handleStatus;
    private String chatHistory;
    private LocalDate startHandleDate;
    private LocalDate sendCheckDate;
    private LocalDate finishDate;
    private String taskFormContent;
    private BigDecimal salary;
    private Integer empClassify;
    private BigDecimal empBase;
    private String endMonth;
    private LocalDate outDate;
    private String businessInterfaceId;
    private String taskId;
    private String policyDetailId;
    private Boolean isActive;
    private LocalDateTime createdTime;
    private LocalDateTime modifiedTime;
    private String createdBy;
    private String modifiedBy;
    //费用段
    private List<SsEmpBasePeriod> listEmpBasePeriod;
    // ohter
    // 操作类型，用于区分操作
    // operatorType 操作类型，1 常规操作、2 特殊操作，默认常规操作

    private Integer operatorType;
    // 任务类型
    private Integer[] taskCategories;
    // 任务发起开始时间

    private LocalDateTime beginSubmitTime;
    // 任务发起结束时间
    private LocalDateTime endSubmitTime;
    // 任务单费用段
    private List<SsEmpTaskPeriod> empTaskPeriods;
    // 来源表 emp_employee
    // 雇员姓名
    @Excel(name = "雇员姓名", orderNum = "3")
    private String employeeName;
    // 雇员编号
    @Excel(name = "雇员编号", orderNum = "4")
    private String employeeId;
    // 雇员证件号
    @Excel(name = "雇员证件号", orderNum = "5")
    private String idNum;
    // 来源表 sal_company
    // 客户编号
    @Excel(name = "客户编号", orderNum = "8")
    private String companyId;
    // 客户名称
    @Excel(name = "客户名称",width = 30,orderNum = "9")
    private String title;
    // 来源表 ss_com_account
    // 企业社保账户Id
    private Long comAccountId;
    // 养老金独立开户密码（使用U盾登陆的密码）
    @Excel(name = "UKEY密码", orderNum = "7")
    private String ssPwd;
    // 结算区县(社保局所在上海地区)
    private String settlementArea;
    // 账户类型：1:中智大库 2中智外包 3独立户
    private Integer ssAccountType;
    // 参保户登记码（账号）
    @Excel(name = "企业社保账号", width = 25,orderNum = "6")
    private String ssAccount;
    // 供应商Id
    private String supplierId;
    // 社保起缴月份
    private String startMonth;
    //入职日期
    private LocalDate inDate;
    //退账金额
    private BigDecimal refundAmount;
    //批量查询的id
    private List<Long> empTaskIdList;
    //存在相同已办任务单
    private List theSameTask;
    //顺调 和  倒调(1 顺调 0 倒调)
    private Integer adustType;

    //企业部分实缴金额（回调时使用）
    private BigDecimal companyConfirmAmount;

    //雇员部分实缴金额（回调使用）
    private BigDecimal personalConfirmAmount;

    //用退工信息
    private AmEmpTaskDTO amEmpTaskDTO;
    /**
     * 福利办理方
     */
    private Integer welfareUnit;
    //证件类型
    private Integer idCardType;
}
