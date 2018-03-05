package com.ciicsh.gto.afsupportcenter.housefund.fundservice.business;

import com.baomidou.mybatisplus.service.IService;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.HfEmpTaskPeriodInactiveBo;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.HfEmpTaskPeriod;

import java.util.List;

public interface HfEmpTaskPeriodService extends IService<HfEmpTaskPeriod> {
    /**
     * 根据任务单ID逻辑删除任务单费用段记录
     *
     * @param hfEmpTaskPeriodInactiveBo
     * @return
     */
    int inactiveHfEmpTaskPeriods(HfEmpTaskPeriodInactiveBo hfEmpTaskPeriodInactiveBo);
}
