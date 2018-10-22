package com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business.impl;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo.SsMonthChargeBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo.SsYysReportBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo.SsYysReportParamBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business.SsMonthChargeItemService;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business.SsMonthChargeService;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.dao.SsMonthChargeMapper;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.SsMonthCharge;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.SsMonthChargeItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 月度缴费明细
系统在每月26日晚上自动生成每月的标准和非标明细数据，用户也可重新生成， 服务实现类
 * </p>
 *
 * @author xsj
 * @since 2018-02-05
 */
@Service
public class SsMonthChargeServiceImpl extends ServiceImpl<SsMonthChargeMapper, SsMonthCharge> implements SsMonthChargeService {
    @Autowired
    SsMonthChargeItemService ssMonthChargeItemService;
    /**
     * 删除非标数据
     * @param employeeId
     * @param paymentMonth
     * @param handleMonth
     */
    @Override
    public int deleteOldDate(String employeeId, String paymentMonth, String handleMonth,Integer costCategory, String modifiedBy) {
        List<SsMonthCharge> ssMonthChargeList = baseMapper.selectOldDate(employeeId,paymentMonth,handleMonth,costCategory);
        if(ssMonthChargeList.size()==0) return 0;
        ssMonthChargeList.forEach(p->{
            SsMonthChargeItem ssMonthChargeItem = new SsMonthChargeItem();
            ssMonthChargeItem.setActive(false);
            ssMonthChargeItem.setModifiedTime(LocalDateTime.now());
            ssMonthChargeItem.setModifiedBy(modifiedBy);

            EntityWrapper<SsMonthChargeItem> ew =  new EntityWrapper<SsMonthChargeItem>();
            ew.where("month_charge_id={0}",p.getMonthChargeId());
            ssMonthChargeItemService.update(ssMonthChargeItem, ew);
        });
        return baseMapper.deleteOldDate(false, employeeId,paymentMonth,handleMonth,costCategory, modifiedBy);
    }

    /**
     * 获得标准的缴纳历史差额
     * @param employeeId
     * @param paymentMonth
     */
    @Override
    public  List<SsMonthChargeBO> selectTotalFromOld(String employeeId, String paymentMonth, Integer costCategory) {
        return baseMapper.selectTotalFromOld(employeeId,paymentMonth, costCategory);
    }

    @Override
    public List<SsYysReportBO> queryYysReport(SsYysReportParamBO ssYysReportParamBO) {
        return baseMapper.queryYysReport(ssYysReportParamBO);
    }

    @Override
    public List<SsMonthCharge> getSocialSecurityChangeInformation(String companyId, String employeeId, String paymentMonth, String year) {
        return baseMapper.getSocialSecurityChangeInformation(companyId, employeeId, paymentMonth, year);
    }

    @Override
    public int updateOldDate(Boolean isActive, String employeeId, String paymentMonth, String handleMonth,Integer costCategory, String modifiedBy) {
        List<SsMonthCharge> ssMonthChargeList = baseMapper.selectOldDate(employeeId,paymentMonth,handleMonth,costCategory);
        if(ssMonthChargeList.size()==0) return 0;
        ssMonthChargeList.forEach(p->{
            SsMonthChargeItem ssMonthChargeItem = new SsMonthChargeItem();
            ssMonthChargeItem.setActive(isActive);
            ssMonthChargeItem.setModifiedTime(LocalDateTime.now());
            ssMonthChargeItem.setModifiedBy(modifiedBy);

            EntityWrapper<SsMonthChargeItem> ew =  new EntityWrapper<SsMonthChargeItem>();
            ew.where("month_charge_id={0}",p.getMonthChargeId());
            ssMonthChargeItemService.update(ssMonthChargeItem, ew);
        });
        return baseMapper.deleteOldDate(isActive, employeeId,paymentMonth,handleMonth,costCategory, modifiedBy);
    }
}
