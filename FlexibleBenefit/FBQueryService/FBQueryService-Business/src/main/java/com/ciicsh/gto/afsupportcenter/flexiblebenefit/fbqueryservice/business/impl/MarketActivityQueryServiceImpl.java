package com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbqueryservice.business.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.entity.bo.GiftFormSendWay;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.entity.bo.SelectEntity;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.entity.po.MarketActivityPO;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbqueryservice.business.MarketActivityQueryService;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbqueryservice.dao.MarketActivityQueryMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 市场活动表 服务实现类
 * </p>
 *
 * @author xiweizhen
 * @since 2017-12-18
 */
@Service
public class MarketActivityQueryServiceImpl extends ServiceImpl<MarketActivityQueryMapper, MarketActivityPO> implements MarketActivityQueryService {

    @Override
    public Page<MarketActivityPO> queryMarketList(Page<MarketActivityPO> page, MarketActivityPO entity) {
        EntityWrapper<MarketActivityPO> entityWrapper = new EntityWrapper<>();
        //排除活动主题空数据
        entityWrapper.like(StringUtils.isNotEmpty(entity.getActivityTitle()), "activity_title", entity.getActivityTitle())
            .and(entity.getStatus() != null, "status={0}", entity.getStatus());
        page.setRecords(baseMapper.selectPage(page, entityWrapper));
        return page;
    }

    @Override
    public MarketActivityPO queryMarketInformation(MarketActivityPO marketActivityPO) {
        return baseMapper.selectOne(marketActivityPO);
    }

    @Override
    public GiftFormSendWay queryGiftFormAndSendWayList(MarketActivityPO marketActivityPO) {
        GiftFormSendWay giftFormSendWay = new GiftFormSendWay();
        marketActivityPO = baseMapper.selectOne(marketActivityPO);

        List<SelectEntity> giftFormList = new ArrayList<>();
        List<SelectEntity> sendWayList = new ArrayList<>();

        String[] giftForms = marketActivityPO.getGiftForm().split(",");
        String[] sendWays = marketActivityPO.getSendWay().split(",");

        for (String str : giftForms) {
            SelectEntity entity = new SelectEntity();
            entity.setKey(str);
            if ("1".equals(str)) {
                entity.setValue("实物");
            } else if ("2".equals(str)) {
                entity.setValue("纸质票券");
            } else if ("3".equals(str)) {
                entity.setValue("电子票券");
            }
            giftFormList.add(entity);
        }

        for (String str : sendWays) {
            SelectEntity entity = new SelectEntity();
            entity.setKey(str);
            if ("0".equals(str)) {
                entity.setValue("送至中心");
            } else if ("1".equals(str)) {
                entity.setValue("委托派送");
            }
            sendWayList.add(entity);
        }

        giftFormSendWay.setGiftFormList(giftFormList);
        giftFormSendWay.setSendWayList(sendWayList);

        return giftFormSendWay;
    }

}
