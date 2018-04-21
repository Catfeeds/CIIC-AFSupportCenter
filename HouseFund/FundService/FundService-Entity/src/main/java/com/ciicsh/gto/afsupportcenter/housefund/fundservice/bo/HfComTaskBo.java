package com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo;


import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.HfComTask;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@ExcelTarget("HfComTaskBo")
public class HfComTaskBo extends HfComTask {

    private Integer ukeyStore;
    private Long comAccountClassId;
    private Long comAccountId;
    /**
     * 任务单id
     */
    @Excel(name = "任务单编号", orderNum = "1")
    private Long comTaskId;


    /**
     * 1 开户 2 转入  3 变更 4 终止 5销户
     */
    @Excel(name = "任务单类型", orderNum = "2")
    private String taskCategoryName;
    private Integer taskCategory;
    /**
     * 1 基本公积金、2 补充公积金
     */
    private String hfTypeName;

    private String hfAccountType;
    /**
     * 客户Id
     */
    @Excel(name = "客户编号", orderNum = "3")
    private String companyId;

    /**
     * 客户名称
     */
    @Excel(name = "客户名称", orderNum = "4")
    private String companyName;

    /**
     * 任务处理人用户Id
     */
    private String handleUserId;

    /**
     * 经办人姓名
     */
    private String handleUserName;

    /**
     * 发起人用户名
     */
    private String submitterId;

    /**
     * 发起人姓名
     */
    @Excel(name = "发起人",orderNum = "5")
    private String submitterName;

    /**
     * 发起时间
     */
    @Excel(name = "发起时间", orderNum = "6")
    private String submitTimeString;

    private Date[] submitTimeArray;
    /**
     * 任务发起人备注
     */
    @Excel(name = "备注",orderNum = "7")
    private String submitRemark;

    /**
     * 付款方式代码名称:
     1 自付（客户自己汇缴给银行，雇员由中智办理）
     2 我司付款（客户预付）
     3 垫付（前道传递）
     */
    private String comTaskPaymentWayName;

    /**
     * 客户缴费起始年月（前道传递）
     */
    private String comStartMonth;

    /**
     * 客户公积金账户 每月的关账到哪一天1-31
     */
    private Integer closeDay;

    /**
     * 企业账户名称（前道传递）
     */
    private String comAccountName;

    /**
     * 企业基本/补充公积金账号
     */
    private String hfComAccount;

    /**
     * 账户状态:0初始 1有效 2 终止
     */
    private String comAccountStateValue;

    /**
     * 缴费银行：1 徐汇—X、2 西郊—C、3 东方路—P、4 卢湾—L、5 黄浦—H
     */
    private String paymentBankValue;
    private Integer paymentBank;
    /**
     * 截止缴费年月（截单日）
     */
    private String endMonth;

    /**
     * 公积金企业U盾代管
     */
    private String ukStoreValue;

    /**
     * 1 独立户 2 大库、3 外包
     */
    private String typeValue;

    /**
     * 1 销户 2 公司自做 3 转其他代理商（前道传递）
     */
    private String endTypeValue;

    /**
     * 任务单状态条件字符串
     */
    private String taskStatusString;
    private  Integer taskStatus;

    /**
     * 受审时间
     */
    private String startHandleDateString;

    /**
     * 送审时间
     */
    private String sendCheckDateString;

    /**
     * 完成时间
     */
    private String finishDateString;

    /**
     * comAccount remark
     */
    private String comAccountRemark;
    //专员操作开始月份
    private String operateStartMonth;

    private Integer endType;

    private  String taskRemark;

    private String comAccountNameTask;

    private String paymentWayTask;
    private String leaderShipName;
}
