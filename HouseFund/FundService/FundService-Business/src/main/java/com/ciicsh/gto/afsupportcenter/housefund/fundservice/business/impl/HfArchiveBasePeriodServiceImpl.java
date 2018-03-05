package com.ciicsh.gto.afsupportcenter.housefund.fundservice.business.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.HfArchiveBasePeriodUpdateBo;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.business.HfArchiveBasePeriodService;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.dao.HfArchiveBasePeriodMapper;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.HfArchiveBasePeriod;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HfArchiveBasePeriodServiceImpl extends ServiceImpl<HfArchiveBasePeriodMapper, HfArchiveBasePeriod> implements HfArchiveBasePeriodService {
    @Override
    public int deleteHfArchiveBasePeriods(List<Long> empTaskIdList) {
        return baseMapper.deleteHfArchiveBasePeriods(empTaskIdList);
    }

    @Override
    public int updateHfArchiveBasePeriods(HfArchiveBasePeriodUpdateBo hfArchiveBasePeriodUpdateBo) {
        return baseMapper.updateHfArchiveBasePeriods(hfArchiveBasePeriodUpdateBo);
    }
}
