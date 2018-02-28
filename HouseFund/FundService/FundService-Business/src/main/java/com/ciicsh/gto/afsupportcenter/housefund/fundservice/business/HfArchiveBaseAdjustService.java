package com.ciicsh.gto.afsupportcenter.housefund.fundservice.business;

import com.baomidou.mybatisplus.service.IService;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.HfArchiveBaseAdjust;

import java.util.List;

public interface HfArchiveBaseAdjustService extends IService<HfArchiveBaseAdjust> {

    /**
     * 根据任务单ID删除雇员公积金历史月份调整差异记录
     *
     * @param empTaskIdList
     * @return
     */
    int deleteHfArchiveBaseAdjusts(List<Long> empTaskIdList);
}
