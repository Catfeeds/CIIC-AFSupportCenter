package com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.custom;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Created by houwanhua on 2018/2/26.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ComTaskExtPO {
    private Long comTaskId;
    /**
     * 多租户Id
     */

    private String customerId;
    /**
     * 外键,企业社保账户Id
     */

    private Long comAccountId;
    /**
     * 客户Id
     */

    private String companyId;
    /**
     * 引用：DicItem.DicItemValue 1:开户：2：转移 3：变更 4：终止
     */

    private String taskCategory;
    /**
     * 发起人要求任务完成截止日期
     */

    private LocalDate expireDate;
    /**
     * 发起人id
     */

    private String submitterId;
    /**
     * 发起人姓名
     */

    private String submitterName;
    /**
     * 发起人当时所在部门Id
     */

    private String submitterDeptId;
    /**
     * 发起人当时所在部门名称
     */

    private String submitterDeptName;
    /**
     * 发起时间
     */

    private LocalDateTime submitTime;
    /**
     * 发起人备注信息
     */

    private String submitRemark;
    /**
     * 任务单上前道系统传递过来的内容，Json格式
     */

    private String taskFormContent;
    /**
     * 对话记录, Json,
     * 格式：部门名称 姓名 时间 内容
     */

    private String chatHistory;
    /**
     * 动态扩展办理数据
     */

    private String dynamicExtend;
    /**
     * 任务单处理状态：0、初始（材料收缴） 1、受理中  2、送审中  3 、已完成  4、批退
     */

    private Integer taskStatus;
    /**
     * 任务处理人用户Id
     */

    private String handleUserId;

    private String handleUserName;
    /**
     * 受理日期
     */

    private LocalDate startHandleDate;
    /**
     * 送审日期
     */

    private LocalDate sendCheckDate;
    /**
     * 完成日期
     */

    private LocalDate finishDate;
    /**
     * 办理备注
     */

    private String handleRemark;
    /**
     * 批退备注
     */

    private String rejectionRemark;
    /**
     * 业务接口ID
     */

    private String businessInterfaceId;

    private String taskId;
    /**
     * 参保户登记码（前道传递）
     */

    private String ssAccount;
    /**
     * 银行账号(牡丹卡号)（前道传递）
     */

    private String bankAccount;
    /**
     * 养老金账户公司名称（前道传递）
     */

    private String comAccountName;
    /**
     * 付款银行(一般情况是工商银行)（前道传递）
     */

    private String paymentBank;
    /**
     * 付款方式：.（前道传递）
     * 1-我司代付款
     * 2-客户自付
     * 3-我司垫付
     */

    private Integer paymentWay;
    /**
     * 社保帐单寄到哪里: 1 我司，2 客户公司（前道传递）
     */

    private Integer billReceiver;
    /**
     * 行业类别（前道传递）
     */

    private String industryCategory;
    /**
     * 开始月份（前道传递）
     */

    private String startMonth;
    /**
     * 客户交付社保费用给中智的截止日（前道传递）
     */

    private Integer expireDateFront;
    /**
     * 结算区县(社保局所在上海地区)（前道传递）
     */

    private String settlementArea;
    /**
     * 法人(前道传递)
     */

    private String legalPerson;
    /**
     * 联系地址(前道传递)
     */

    private String contactAddress;
    /**
     * 发出材料(前道传递):正式通知书、预生成通知书、收据、银行对账单、汇总表、实时表
     */

    private String dispatchMaterial;
    /**
     * 是否可用
     */

    private Boolean isActive;
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
     * 修改者登录名
     */

    private String modifiedBy;
}
