package com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import com.alibaba.fastjson.annotation.JSONField;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.SsComMaterial;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;


@Data
@EqualsAndHashCode(callSuper = false)
@ExcelTarget("SsComTaskBO")
public class SsComTaskBO {

    @Excel(name = "任务单编号", orderNum = "1")
    private Long comTaskId;

    private String customerId;

    private Long comAccountId;

    @Excel(name = "客户编号", orderNum = "3")
    private String companyId;

    @Excel(name = "任务单类型", replace = {"开户_1","转移_2","变更_3","终止_4"},orderNum = "2")
    private String taskCategory;

    @Excel(name = "完成截止日期",format = "yyyy-MM-dd", orderNum = "5")
    private LocalDate expireDate;

    @Excel(name = "发起人",orderNum = "6")
    private String submitterId;

    private String submitterName;

    private String submitterDeptId;

    private String submitterDeptName;

    @Excel(name = "发起时间",format = "yyyy-MM-dd", orderNum = "7")
    private LocalDateTime submitTime;

    @Excel(name = "备注",orderNum = "8")
    private String submitRemark;

    private String taskFormContent;

    private String chatHistory;

    private String dynamicExtend;

    private Integer taskStatus;

    private String handleUserId;

    private String handleUserName;

    private LocalDate startHandleDate;

    private LocalDate sendCheckDate;

    private LocalDate finishDate;

    private String handleRemark;

    private String rejectionRemark;

    private String businessInterfaceId;

    private String taskId;

    private String ssAccount;

    private String bankAccount;

    private String comAccountName;

    private String paymentBank;

    private Integer paymentWay;

    private Integer billReceiver;

    private String industryCategory;

    private String startMonth;

    private Integer expireDateFront;

    private String settlementArea;

    private String legalPerson;

    private String contactAddress;

    private Boolean isActive;

    private LocalDateTime createdTime;

    private LocalDateTime modifiedTime;

    private String createdBy;

    private String modifiedBy;


    //客户名称
    @Excel(name = "企业客户", orderNum = "4")
    private String companyName;

    //任务发起时间段的 首段
    @JSONField(format="yyyy-MM-dd")
    private Date submitTimeStart;

    //任务发起时间的 尾段
    @JSONField(format="yyyy-MM-dd")
    private Date submitTimeEnd;

    //账户类型
    private String accountType;

    //结算区县
    private String regionValue;

    //客户经理
    private String customerManager;

    //材料清单
     private List<SsComMaterial> materialList;

    //账户
    private SsComAccountBO ssComAccountBO;

    //操作类型  1 开户 2 转移 3 变更 4 终止
    private String operatorType;

    //终止操作时 的终止日期
    private LocalDate endDate;

    //转移日期
    private String transferDate;
    //变更的type
    private String changeContentValue;
    //行业类型
    private String belongsIndustry;
    //企业工伤比例
    private String companyWorkInjuryPercentage;    /**
     *  判断是否是完成状态即 在任务单办理页面时候查询没有完成的任务  在完成tab查看已完成的
     *  如果为空则是在完成tab中查询，否则是在开户办理时查询信息（信息为任务状态!=3的）
     */

    private String isComplete;

    private String dispatchMaterial;
}
