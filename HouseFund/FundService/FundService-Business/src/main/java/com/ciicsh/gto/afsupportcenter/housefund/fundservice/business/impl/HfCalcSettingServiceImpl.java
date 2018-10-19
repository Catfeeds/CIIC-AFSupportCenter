package com.ciicsh.gto.afsupportcenter.housefund.fundservice.business.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.toolkit.CollectionUtils;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.business.HfCalcSettingService;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.dao.HfCalcSettingMapper;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.HfCalcSetting;
import com.ciicsh.gto.afsupportcenter.util.CalculateSocialUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class HfCalcSettingServiceImpl extends ServiceImpl<HfCalcSettingMapper, HfCalcSetting> implements HfCalcSettingService {
    @Override
    public List<HfCalcSetting> getShComSettingByMonth(Integer hfType, String startMonth) {
        return baseMapper.getShComSettingByMonth(hfType, startMonth);
    }

    @Override
    public BigDecimal[] getEmpAmountAndRatio(BigDecimal base, BigDecimal comRatio, BigDecimal empRatio, Integer hfType, String startMonth) {
        List<HfCalcSetting> hfCalcSettingList = getShComSettingByMonth(hfType, startMonth);
        BigDecimal ratio = comRatio.add(empRatio);
        BigDecimal halfRatio = ratio.divide(BigDecimal.valueOf(2.0), 3, BigDecimal.ROUND_HALF_UP);

        if (CollectionUtils.isNotEmpty(hfCalcSettingList)) {
            if (hfCalcSettingList.size() == 2) {

                BigDecimal amount;

                Integer comRoundType = hfCalcSettingList.get(0).getRoundType();
                Integer empRoundType = hfCalcSettingList.get(1).getRoundType();

                if (comRoundType.equals(empRoundType)) {
                    amount = CalculateSocialUtils.calculateByRoundType(base.multiply(halfRatio), comRoundType).multiply(BigDecimal.valueOf(2));
                } else {
                    amount = CalculateSocialUtils.calculateByRoundType(base.multiply(halfRatio), comRoundType)
                        .add(CalculateSocialUtils.calculateByRoundType(base.multiply(halfRatio), empRoundType));
                }

                return new BigDecimal[] {ratio, halfRatio, amount};
            }
        }

        return null;
    }
}
