package com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.custom;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by houwanhua on 2018/2/26.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ComAccountExtPO {
    /**
     * 企业社保账户Id
     */
    private Long comAccountId;
    /**
     * 多租户Id
     */
    private String customerId;
    /**
     * 供应商Id, 取自gtosupplierdb.SUP_Supplier
     */
    private String supplierId;
    /**
     * 账户类型：1:中智大库 2中智外包 3独立户
     */
    private Integer ssAccountType;
    /**
     * 参保户登记码（账号）
     */
    private String ssAccount;
    /**
     * 银行账号ID
     */
    private Long bankAccountId;
    /**
     * 银行账号(牡丹卡号)
     */
    private String bankAccount;
    /**
     * 养老金账户公司名称
     */
    private String comAccountName;
    /**
     * 结算区县(社保局所在上海地区)
     */
    private String settlementArea;
    /**
     * 付款银行(一般情况是工商银行)
     */
    private String paymentBank;
    /**
     * 付款方式：.
     1-我司代付款
     2-客户自付
     3-我司垫付
     */
    private Integer paymentWay;
    /**
     * 社保帐单寄到哪里: 1 我司，2 客户公司
     */
    private Integer billReceiver;
    /**
     * 客户交付社保费用给中智的截止日期
     */
    private Integer expireDate;
    /**
     * 养老金独立开户用户名（使用U盾登陆的用户名）
     */
    private String ssUsername;
    /**
     * 养老金独立开户密码（使用U盾登陆的密码）
     */
    private String ssPwd;
    /**
     * 初期余额
     */
    private BigDecimal initialBalance;
    /**
     * 初期欠费
     */
    private BigDecimal initialDebt;
    /**
     * 来源地:1-新开；2-AF转入；3-其他供应商转入
     */
    private Integer originPlace;
    /**
     * 来源地备注
     */
    private String originPlaceRemark;
    /**
     * 查询账号
     */
    private String queryAccount;
    /**
     * 交予方式:1-交客服  2-传真  3-邮寄
     */
    private String deliverWay;
    /**
     * 交予方式备注
     */
    private String deliverWayRemark;
    /**
     * 给凭证时间
     */
    private LocalDate provideCertificateTime;
    /**
     * 法人
     */
    private String legalPerson;
    /**
     * 联系地址
     */
    private String contactAddress;
    /**
     * 变更时间
     */
    private LocalDateTime changeTime;
    /**
     * 收到日期
     */
    private LocalDate receiveDate;
    /**
     * 转入日期
     */
    private LocalDate intoDate;
    /**
     * 终止日期
     */
    private LocalDate endDate;
    /**
     * 发出材料:正式通知书、预生成通知书、收据、银行对账单、汇总表、实时表
     */
    private String dispatchMaterial;
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

    /**
     * 企业工伤比例变更(新增)
     */
    private AccountRatioExtPO ssAccountRatio;

    /**
     * 来源表 sal_company(客户编号)
     */
    private String companyId;
    /**
     * 客户名称
     */
    private String title;
    /**
     * 历史企业任务单
     */
    private List<ComTaskExtPO> ssComTaskList;

    /**
     * 账户关联公司
     */
    private List<AccountComRelationExtPO> ssAccountComRelationList;
    /**
     * 企业工伤比例变更(查询) 1对多
     */
    private List<AccountRatioExtPO> ssAccountRatioList;
}
