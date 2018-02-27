package com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.customer;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * Created by houwanhua on 2018/2/27.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ComAccountExtBo {
    /**
     * 主键
     */
    private Long comAccountId;
    /**
     * 企业账户名称
     */
    private String comAccountName;
    /**
     * 付款方式:
     * 1 自付（客户自己汇缴给银行，雇员由中智办理）
     * 2 我司付款（客户预付）
     * 3 垫付
     */
    private Integer paymentWay;
    /**
     * 1 独立户 2 大库、3 外包
     */
    private Integer hfAccountType;
    /**
     * 客户公积金账户 每月的关账到哪一天1-31
     */
    private Integer closeDay;
    /**
     * 公积金企业U盾代管
     */
    private Integer ukeyStore;
    /**
     * 缴费区县：1 徐汇—X、2 西郊—C、3 东方路—P、4 卢湾—L、5 黄浦—H
     */
    private Integer paymentBank;
    /**
     * 备注
     */
    private String remark;
    /**
     * 账户状态:0初始 1有效 2 终止
     */
    private Integer state;
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
    private String Column15;

    // 客户编号
    private String companyId;
    // 客户名称
    private String title;
}
