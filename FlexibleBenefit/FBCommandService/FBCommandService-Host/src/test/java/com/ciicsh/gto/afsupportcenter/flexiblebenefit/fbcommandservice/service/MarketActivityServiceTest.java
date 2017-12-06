package com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice.service;


import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice.business.MarketActivityService;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice.entity.po.MarketActivityPO;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice.host.Luncher;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * created by xwz on 2017年12月4日10:46:48
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Luncher.class)
public class MarketActivityServiceTest {
    @Autowired
    private MarketActivityService marketActivityService;

    @Test
    public void findByIdTest() {
        MarketActivityPO entity = marketActivityService.findById(1);
        System.out.printf(entity.toString());
        int t = entity.getId();
        Assert.assertEquals(1, t);
    }

    @Test
    public void addMarketActivityTest() {
        MarketActivityPO entity = new MarketActivityPO();
        entity.setActivityTitle("ceshi");
        entity.setPublisher("xwz");
        entity.setGiftForm("1,2,3");
        entity.setSendWay("1,2");
        entity.setBeginTime(new Date());
        entity.setEndTime(new Date());
        entity.setContent("测试");
        int t = marketActivityService.addMarketActivity(entity);
        System.out.printf("添加" + t + "条数据");
        Assert.assertEquals(1, t);
    }

    @Test
    public void updateMarketActivityTest() {
        MarketActivityPO entity = new MarketActivityPO();
        entity.setId(10);
        entity.setContent("测试");
        int t = marketActivityService.addMarketActivity(entity);
        System.out.printf("更新第" + t + "条数据成功");
        Assert.assertEquals(1, t);
    }
}
