package com.ciicsh.gto.afsupportcenter.housefund.fundservice.dao;

import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.HfArchiveBasePeriodUpdateBo;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.HfArchiveBasePeriod;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 雇员公积金汇缴月份段 Mapper 接口
 * </p>
 */
public interface HfArchiveBasePeriodMapper extends BaseMapper<HfArchiveBasePeriod> {

    /**
     * 根据任务单ID删除雇员公积金汇缴月份段记录
     *
     * @param empTaskIdList
     * @return
     */
    int deleteHfArchiveBasePeriods(List<Long> empTaskIdList);

    /**
     * 更新雇员公积金汇缴月份段记录
     * @param hfArchiveBasePeriodUpdateBo
     * @return
     */
    int updateHfArchiveBasePeriods(HfArchiveBasePeriodUpdateBo hfArchiveBasePeriodUpdateBo);
}
