package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.business.impl;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.bo.SsMonthChargeBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.business.SsMonthChargeItemService;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.business.SsMonthChargeService;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dao.SsMonthChargeMapper;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.SsMonthCharge;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.SsMonthChargeItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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
    public void deleteOldDate(String employeeId, String paymentMonth, String handleMonth) {
        List<SsMonthCharge> ssMonthChargeList = baseMapper.selectOldDate(employeeId,paymentMonth,handleMonth);
        if(ssMonthChargeList.size()==0) return;
        ssMonthChargeList.forEach(p->{
            EntityWrapper<SsMonthChargeItem> ew =  new EntityWrapper<SsMonthChargeItem>();
            ew.where("month_charge_id={0}",p.getMonthChargeId());
            ssMonthChargeItemService.delete(ew);
        });
        baseMapper.deleteOldDate(employeeId,paymentMonth,handleMonth);
    }

    /**
     * 获得标准的缴纳历史差额
     * @param employeeId
     * @param paymentMonth
     */
    @Override
    public  List<SsMonthChargeBO> selectTotalFromOld(String employeeId, String paymentMonth) {
        return baseMapper.selectTotalFromOld(employeeId,paymentMonth);
    }
}