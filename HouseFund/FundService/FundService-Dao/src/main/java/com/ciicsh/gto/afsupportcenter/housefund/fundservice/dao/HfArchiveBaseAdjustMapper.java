package com.ciicsh.gto.afsupportcenter.housefund.fundservice.dao;

import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.HfArchiveBaseAdjust;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 雇员公积金历史月份调整差异表 Mapper 接口
 * </p>
 */
public interface HfArchiveBaseAdjustMapper extends BaseMapper<HfArchiveBaseAdjust> {

    /**
     * 根据任务单ID删除雇员公积金历史月份调整差异记录
     *
     * @param empTaskIdList
     * @return
     */
    int deleteHfArchiveBaseAdjusts(List<Long> empTaskIdList);
}
