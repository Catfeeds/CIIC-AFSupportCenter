package com.ciicsh.gto.afsupportcenter.housefund.fundcommandservice.business.impl;

import com.ciicsh.gto.afsupportcenter.housefund.fundcommandservice.entity.HfEmpMonthCharge;
import com.ciicsh.gto.afsupportcenter.housefund.fundcommandservice.dao.HfEmpMonthChargeMapper;
import com.ciicsh.gto.afsupportcenter.housefund.fundcommandservice.business.IHfEmpMonthChargeService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 雇员月度汇缴明细库，每个雇员每一月份一条记录
当任务单状态为已办，?该表就应该有对应的明细数据，包含调整数据 服务实现类
 * </p>
 */
@Service
public class HfEmpMonthChargeServiceImpl extends ServiceImpl<HfEmpMonthChargeMapper, HfEmpMonthCharge> implements IHfEmpMonthChargeService {

}
