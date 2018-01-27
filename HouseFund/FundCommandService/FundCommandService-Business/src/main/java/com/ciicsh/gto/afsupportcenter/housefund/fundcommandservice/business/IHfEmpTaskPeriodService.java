package com.ciicsh.gto.afsupportcenter.housefund.fundcommandservice.business;

import com.ciicsh.gto.afsupportcenter.housefund.fundcommandservice.entity.HfEmpTaskPeriod;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 任务单费用段表,记录任务单历史办理记录,不受将来的调整影响
从雇员公积金详细中跳转的任务表单,应从该表获取数 服务类
 * </p>
 */
public interface IHfEmpTaskPeriodService extends IService<HfEmpTaskPeriod> {

}
