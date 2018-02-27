package com.ciicsh.gto.afsupportcenter.housefund.fundservice.business;

import com.baomidou.mybatisplus.service.IService;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.HfMonthChargeBo;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.HfMonthCharge;

public interface HfMonthChargeService extends IService<HfMonthCharge> {
    /**
     * 根据条件将雇员月度汇缴明细库记录逻辑删除
     *
     * @param hfMonthChargeBo
     * @return
     */
    int inactiveHfMonthCharge(HfMonthChargeBo hfMonthChargeBo);
}
