package com.ciicsh.gto.afsupportcenter.housefund.fundservice.business.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.business.HfArchiveBaseAdjustService;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.dao.HfArchiveBaseAdjustMapper;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.HfArchiveBaseAdjust;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HfArchiveBaseAdjustServiceImpl extends ServiceImpl<HfArchiveBaseAdjustMapper, HfArchiveBaseAdjust> implements HfArchiveBaseAdjustService {
    @Override
    public int deleteHfArchiveBaseAdjusts(List<Long> empTaskIdList) {
        return baseMapper.deleteHfArchiveBaseAdjusts(empTaskIdList);
    }
}
