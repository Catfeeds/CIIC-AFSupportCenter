package com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Created by houwanhua on 2018/2/13.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class PayapplyEmployeeProxyDTO {
    private BigDecimal taxAmount;
    private Integer businessItemId;
    private String companyName;
    private String serviceMonth;
    private BigDecimal receivableAmount;
    private BigDecimal receivedAmount;
    private BigDecimal payableAmount;
    private BigDecimal paidAmount;
    private Integer businessType;
    private Integer payapplyEmployeeId;
    private Integer payapplyId;
    private String companyId;
    private String employeeId;
    private String employeeName;
    private String employeeBankAccount;
    private String bankAccountName;
    private String bankName;
    private Integer bankId;
    private String provinceName;
    private String cityName;
    private String areaCode;
    private String payMonth;
    private BigDecimal payAmount;
    private String businessTypeDesc;
    private Integer isAdvance;
    private Boolean isActive;
    private String remark;
    private LocalDateTime createdTime;
    private String createdBy;
    private LocalDateTime modifiedTime;
    private String modifiedBy;
    private Integer updateCount;
}
