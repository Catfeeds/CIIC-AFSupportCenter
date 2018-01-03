package com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbqueryservice.business;


import com.baomidou.mybatisplus.service.IService;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.entity.po.ApplyRecordDetailPO;

import java.util.List;

/**
 * <p>
 * 申请记录详细表（一个赠送对象一条记录，存放记录的审批和发放记录） 服务类
 * </p>
 *
 * @author xiweizhen
 * @since 2017-12-18
 */
public interface ApplyRecordDetailQueryService extends IService<ApplyRecordDetailPO> {

    /**
     * 根据实体查询详情
     *
     * @param applyRecordDetailPO
     * @return
     */
    ApplyRecordDetailPO queryApplyRecordDetail(ApplyRecordDetailPO applyRecordDetailPO);

    List<ApplyRecordDetailPO> queryApplyRecordDetailList(ApplyRecordDetailPO applyRecordDetailPO);
}
