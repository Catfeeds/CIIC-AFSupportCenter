package com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice.business;

import com.baomidou.mybatisplus.service.IService;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.entity.po.ApplyGiftRecordPO;

/**
 * <p>
 * 礼品申请记录表 服务类
 * </p>
 *
 * @author xiweizhen
 * @since 2017-12-18
 */
public interface ApplyGiftRecordCommandService extends IService<ApplyGiftRecordPO> {
    /**
     * 礼品申请
     *
     * @param applyGiftRecordPO
     * @return
     */
    Integer insertSelective(ApplyGiftRecordPO applyGiftRecordPO);

}
