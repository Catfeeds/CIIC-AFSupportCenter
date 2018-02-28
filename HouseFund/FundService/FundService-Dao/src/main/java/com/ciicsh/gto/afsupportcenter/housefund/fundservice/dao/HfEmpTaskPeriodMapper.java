package com.ciicsh.gto.afsupportcenter.housefund.fundservice.dao;

import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.HfEmpTaskPeriodInactiveBo;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.HfEmpTaskPeriod;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 任务单费用段表,记录任务单历史办理记录,不受将来的调整影响
从雇员公积金详细中跳转的任务表单,应从该表获取数 Mapper 接口
 * </p>
 */
public interface HfEmpTaskPeriodMapper extends BaseMapper<HfEmpTaskPeriod> {
    /**
     * 根据任务单ID逻辑删除任务单费用段记录
     *
     * @param hfEmpTaskPeriodInactiveBo
     * @return
     */
    int inactiveHfEmpTaskPeriods(HfEmpTaskPeriodInactiveBo hfEmpTaskPeriodInactiveBo);
}
