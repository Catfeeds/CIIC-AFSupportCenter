package com.ciicsh.gto.afsupportcenter.housefund.fundcommandservice.business;

import com.ciicsh.gto.afsupportcenter.housefund.fundcommandservice.entity.HfEmpMonthCharge;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 雇员月度汇缴明细库，每个雇员每一月份一条记录
当任务单状态为已办，?该表就应该有对应的明细数据，包含调整数据 服务类
 * </p>
 */
public interface IHfEmpMonthChargeService extends IService<HfEmpMonthCharge> {

}
