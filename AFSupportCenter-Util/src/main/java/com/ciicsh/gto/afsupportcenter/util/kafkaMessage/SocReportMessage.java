package com.ciicsh.gto.afsupportcenter.util.kafkaMessage;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by houwanhua on 2018/2/1.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SocReportMessage {
    private String ssMonth;
    private Long comAccountId;
    private String generalMethod;
    private String lastComputeUser;
}
