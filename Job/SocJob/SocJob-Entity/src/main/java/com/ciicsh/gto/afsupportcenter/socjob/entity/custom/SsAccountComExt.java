package com.ciicsh.gto.afsupportcenter.socjob.entity.custom;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Created by houwanhua on 2018/1/11.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SsAccountComExt {
    private Long comAccountId;
    private String customerId;
    private String companyId;
    private String supplierId;
    private Integer ssAccountType;
    private String ssAccount;
    private String bankAccount;
    private String comAccountName;
    private String settlementArea;
    private String paymentBank;
    private Integer paymentWay;
    private Integer billReceiver;
    private Integer expireDate;
    private String ssUsername;
    private String ssPwd;
    private BigDecimal initialBalance;
    private BigDecimal initialDebt;
    private Integer originPlace;
    private String originPlaceRemark;
    private String queryAccount;
    private String deliverWay;
    private String deliverWayRemark;
    private LocalDate provideCertificateTime;
    private LocalDateTime changeTime;
    private LocalDate receiveDate;
    private LocalDate intoDate;
    private LocalDate endDate;
    private String dispatchMaterial;
    private Integer state;
}
