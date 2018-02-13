package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Created by houwanhua on 2018/2/13.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class PayapplyCompanyProxyDTO {
    private Integer payapplyCompanyId;
    private Integer payapplyId;
    private String companyId;
    private String companyName;
    private Integer companyBankAccountId;
    private String companyBankAccount;
    private String payMonth;
    private BigDecimal payAmount;
    private String businessTypeDesc;
    private BigDecimal noticebillAmount;
    private BigDecimal deductionAmount;
    private Integer isAdvance;
    private Boolean isActive;
    private String remark;
    private LocalDateTime createdTime;
    private String createdBy;
    private LocalDateTime modifiedTime;
    private String modifiedBy;
    private Integer updateCount;
}
