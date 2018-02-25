package com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.dao;

import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.SsEmpTaskPeriod;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 * 任务单费用段表,记录任务单历史办理记录,不受将来的调整影响
从雇员社保详细中跳转的任务表单,应从该表获取数据 Mapper 接口
 * </p>
 *
 * @author HuangXing
 * @since 2017-12-01
 */
public interface SsEmpTaskPeriodMapper extends BaseMapper<SsEmpTaskPeriod> {

}
