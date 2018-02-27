package com.ciicsh.gto.afsupportcenter.housefund.fundservice.business.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.HfMonthChargeBo;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.business.HfMonthChargeService;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.dao.HfMonthChargeMapper;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.HfMonthCharge;
import org.springframework.stereotype.Service;

@Service
public class HfMonthChargeServiceImpl extends ServiceImpl<HfMonthChargeMapper, HfMonthCharge> implements HfMonthChargeService {
    @Override
    public int inactiveHfMonthCharge(HfMonthChargeBo hfMonthChargeBo) {
        return baseMapper.inactiveHfMonthCharge(hfMonthChargeBo);
    }
}
