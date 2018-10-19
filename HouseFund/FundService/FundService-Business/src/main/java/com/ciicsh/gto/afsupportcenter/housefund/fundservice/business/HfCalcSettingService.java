package com.ciicsh.gto.afsupportcenter.housefund.fundservice.business;

import com.baomidou.mybatisplus.service.IService;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.HfCalcSetting;

import java.math.BigDecimal;
import java.util.List;

public interface HfCalcSettingService extends IService<HfCalcSetting> {
    List<HfCalcSetting> getShComSettingByMonth(Integer hfType, String startMonth);
    BigDecimal[] getEmpAmountAndRatio(BigDecimal base, BigDecimal comRatio, BigDecimal empRatio, Integer hfType, String startMonth);
}
