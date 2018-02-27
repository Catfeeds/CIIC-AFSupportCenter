package com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.custom;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * Created by houwanhua on 2018/2/26.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AccountComRelationExtPO {
    /**
     * 记录Id
     */
    private Long accountComRelationId;
    /**
     * 外键, 企业社保账户Id
     */
    private Long comAccountId;
    /**
     * 外键, 客户Id, 来自CMY_COMPANY
     */
    private String companyId;
    /**
     * 是否账户主客户
     */
    private Integer majorCom;
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
     * 公司名称
     */
    private String title;
    /**
     * 操作标志,1:保存,2:更新
     */
    private String saveflag;
}
