package com.ciicsh.gto.afsupportcenter.housefund.siteservice.bo;

import com.ciicsh.gto.afsupportcenter.housefund.siteservice.entity.HfComTask;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
public class HfComTaskBo extends HfComTask {

    /**
     * 任务单id
     */
    private Long comTaskId;

    /**
     * 1 开户 2 转入  3 变更 4 终止 5销户
     */
    private String taskCategoryName;

    /**
     * 1 基本公积金、2 补充公积金
     */
    private String hfTypeName;

    /**
     * 客户Id
     */
    private String companyId;

    /**
     * 客户名称
     */
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
    private String submitterName;

    /**
     * 发起时间
     */
    private Date submitTime;

    /**
     * 任务发起人备注
     */
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

}
