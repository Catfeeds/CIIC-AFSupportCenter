package com.ciicsh.gto.afsupportcenter.housefund.fundservice.business;

import com.baomidou.mybatisplus.service.IService;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.HfArchiveBasePeriod;

import java.util.List;

public interface HfArchiveBasePeriodService extends IService<HfArchiveBasePeriod> {

    /**
     * 根据任务单ID删除雇员公积金汇缴月份段记录
     *
     * @param empTaskIdList
     * @return
     */
    int deleteHfArchiveBasePeriods(List<Long> empTaskIdList);
}
