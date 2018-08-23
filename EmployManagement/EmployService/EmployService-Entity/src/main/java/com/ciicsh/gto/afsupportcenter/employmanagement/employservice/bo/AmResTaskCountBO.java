package com.ciicsh.gto.afsupportcenter.employmanagement.employservice.bo;

import lombok.Data;

/**
 * Created by zhangzhiwen on 2018/2/1.
 */
@Data
public class AmResTaskCountBO {

    private  Integer  noFeedback;
    private  Integer  refuseWaitFinished;
    private  Integer  refuseFinished;
    private  Integer  refuseBeforeWithFile;
    private  Integer  refuseTicketStampNoReturn;
    private  Integer  refuseFailed;
    private  Integer  beforeBatchNeedRefuse;
    private  Integer  other;
    private  Integer amount;

}
