package com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice.dao;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.entity.po.ApplyMarketActivityRecordPO;

/**
 * <p>
 * 市场活动申请礼品表 Mapper 接口
 * </p>
 *
 * @author xiweizhen
 * @since 2017-12-18
 */
public interface ApplyMarketActivityRecordCommandMapper extends BaseMapper<ApplyMarketActivityRecordPO> {
    /**
     * 新增方法
     *
     * @param applyMarketActivityRecordPO
     * @return
     */
    Integer insertSelective(ApplyMarketActivityRecordPO applyMarketActivityRecordPO);
}