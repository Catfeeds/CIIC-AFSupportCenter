package com.ciicsh.gto.afsupportcenter.housefund.fundcommandservice.business.impl;

import com.ciicsh.gto.afsupportcenter.housefund.fundcommandservice.entity.HfEmpTaskPeriod;
import com.ciicsh.gto.afsupportcenter.housefund.fundcommandservice.dao.HfEmpTaskPeriodMapper;
import com.ciicsh.gto.afsupportcenter.housefund.fundcommandservice.business.IHfEmpTaskPeriodService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 任务单费用段表,记录任务单历史办理记录,不受将来的调整影响
从雇员公积金详细中跳转的任务表单,应从该表获取数 服务实现类
 * </p>
 */
@Service
public class HfEmpTaskPeriodServiceImpl extends ServiceImpl<HfEmpTaskPeriodMapper, HfEmpTaskPeriod> implements IHfEmpTaskPeriodService {

}
