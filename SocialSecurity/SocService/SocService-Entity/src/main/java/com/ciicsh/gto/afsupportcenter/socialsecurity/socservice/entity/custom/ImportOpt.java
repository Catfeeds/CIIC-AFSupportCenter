package com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.custom;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * Created by houwanhua on 2018/1/25.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ImportOpt {
    private String employeeId;
    private String employeeName;
    private String ssSerial;
    private Integer changeType;
    private String changeTypeName;
    private BigDecimal BaseAmount;
}
