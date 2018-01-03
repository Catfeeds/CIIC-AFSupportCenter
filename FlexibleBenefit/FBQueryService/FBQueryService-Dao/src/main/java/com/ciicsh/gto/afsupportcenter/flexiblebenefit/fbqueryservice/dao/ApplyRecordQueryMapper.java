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
     * 查询申请列表信息
     *
     * @param page
     * @param applyRecordBO
     * @return
     */
    List<ApplyRecordBO> selectApplyList(Page<ApplyRecordBO> page, ApplyRecordBO applyRecordBO);

}