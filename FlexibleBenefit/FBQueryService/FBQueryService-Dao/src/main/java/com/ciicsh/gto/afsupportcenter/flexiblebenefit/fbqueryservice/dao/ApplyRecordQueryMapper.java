package com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbqueryservice.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.entity.bo.ApplyRecordBO;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.entity.po.ApplyRecordPO;

import java.util.List;

/**
 * <p>
 * 申请记录表 Mapper 接口
 * </p>
 *
 * @author xiweizhen
 * @since 2017-12-18
 */
public interface ApplyRecordQueryMapper extends BaseMapper<ApplyRecordPO> {

    /**
     * 查询礼品申请列表信息
     *
     * @param page
     * @param applyRecordBO
     * @return
     */
    List<ApplyRecordBO> selectGiftApplyList(Page<ApplyRecordBO> page, ApplyRecordBO applyRecordBO);

    /**
     * 查询活动申请列表信息
     *
     * @param page
     * @param applyRecordBO
     * @return
     */
    List<ApplyRecordBO> selectMarketApplyList(Page<ApplyRecordBO> page, ApplyRecordBO applyRecordBO);

}