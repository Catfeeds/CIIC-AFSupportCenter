package com.ciicsh.gto.afsupportcenter.housefund.fundservice.business.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.HfEmpTaskPeriodInactiveBo;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.business.HfEmpTaskPeriodService;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.dao.HfEmpTaskPeriodMapper;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.HfEmpTaskPeriod;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HfEmpTaskPeriodServiceImpl extends ServiceImpl<HfEmpTaskPeriodMapper, HfEmpTaskPeriod> implements HfEmpTaskPeriodService {
    @Override
    public int inactiveHfEmpTaskPeriods(HfEmpTaskPeriodInactiveBo hfEmpTaskPeriodInactiveBo) {
        return baseMapper.inactiveHfEmpTaskPeriods(hfEmpTaskPeriodInactiveBo);
    }
}
