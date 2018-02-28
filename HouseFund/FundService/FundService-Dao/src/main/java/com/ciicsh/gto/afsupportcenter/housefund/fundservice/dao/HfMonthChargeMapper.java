package com.ciicsh.gto.afsupportcenter.housefund.fundservice.dao;

import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.HfMonthChargeBo;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.HfMonthCharge;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 雇员月度汇缴明细库，每个雇员每一月份一条记录
当任务单状态为已办，?该表就应该有对应的明细数据，包含调整数据 Mapper 接口
 * </p>
 */
public interface HfMonthChargeMapper extends BaseMapper<HfMonthCharge> {

    /**
     * 更新雇员月度汇缴明细库记录
     *
     * @param hfMonthChargeBo
     * @return
     */
    int updateHfMonthCharge(HfMonthChargeBo hfMonthChargeBo);

    /**
     * 根据任务单ID删除雇员月度汇缴明细库记录
     * @param empTaskIdList
     * @return
     */
    int deleteHfMonthCharges(List<Long> empTaskIdList);
}
