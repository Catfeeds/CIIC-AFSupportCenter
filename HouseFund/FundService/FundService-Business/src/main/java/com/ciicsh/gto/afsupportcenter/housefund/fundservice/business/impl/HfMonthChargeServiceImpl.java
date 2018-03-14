package com.ciicsh.gto.afsupportcenter.housefund.fundservice.business.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.HFMonthChargeQueryBO;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.HFMonthChargeReportBO;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.HfMonthChargeBo;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.HfMonthChargeDiffBo;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.business.HfMonthChargeService;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.dao.HfMonthChargeMapper;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.HfMonthCharge;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageKit;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HfMonthChargeServiceImpl extends ServiceImpl<HfMonthChargeMapper, HfMonthCharge> implements HfMonthChargeService {
    @Override
    public int updateHfMonthCharge(HfMonthChargeBo hfMonthChargeBo) {
        return baseMapper.updateHfMonthCharge(hfMonthChargeBo);
    }

    @Override
    public int deleteHfMonthCharges(List<Long> empTaskIdList) {
        return baseMapper.deleteHfMonthCharges(empTaskIdList);
    }

    @Override
    public HfMonthChargeDiffBo getHfMonthChargeDiffSum(HfMonthChargeBo hfMonthChargeBo) {
        return baseMapper.getHfMonthChargeDiffSum(hfMonthChargeBo);
    }

    @Override
    public PageRows<HFMonthChargeReportBO> queryHfMonthChargeReport(PageInfo pageInfo) {
        HFMonthChargeQueryBO hfMonthChargeQueryBO = pageInfo.toJavaObject(HFMonthChargeQueryBO.class);
        return PageKit.doSelectPage(pageInfo, () -> baseMapper.queryHfMonthChargeReport(hfMonthChargeQueryBO));
    }
}
