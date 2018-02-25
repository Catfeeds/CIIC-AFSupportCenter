package com.ciicsh.gto.afsupportcenter.housefund.siteservice.dao;

import com.ciicsh.gto.afsupportcenter.housefund.siteservice.entity.HfMonthCharge;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 * 雇员月度汇缴明细库，每个雇员每一月份一条记录
当任务单状态为已办，?该表就应该有对应的明细数据，包含调整数据 Mapper 接口
 * </p>
 */
public interface HfMonthChargeMapper extends BaseMapper<HfMonthCharge> {

}
