package com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.custom;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Created by houwanhua on 2018/2/26.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AccountRatioExtPO {
    /**
     * 记录Id
     */
    private Long ssAccountRatioId;
    /**
     * 外键, 企业社保账户Id
     */
    private Long comAccountId;
    /**
     * 行业类别
     */
    private String industryCategory;
    /**
     * 企业比例
     */
    private BigDecimal comRatio;
    /**
     * 开始月份
     */
    private String startMonth;
    /**
     * 截至月份
     */
    private String endMonth;
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
