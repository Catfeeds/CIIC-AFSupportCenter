//package com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbqueryservice.service;
//
//import com.baomidou.mybatisplus.plugins.Page;
//import com.ciicsh.gto.afsupportcenter.flexiblebenefit.customer.po.MarketActivityPO;
//import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbqueryservice.FlexibleQueryApplication;
//import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbqueryservice.business.MarketActivityQueryService;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = FlexibleQueryApplication.class)
//public class MarketServiceQueryTest {
//
//    @Autowired
//    private MarketActivityQueryService marketActivityQueryService;
//
//    @Test
//    public void findByEntityTest() throws Exception {
//        Page<MarketActivityPO> page = new Page<>(1, 5);
//        MarketActivityPO customer = new MarketActivityPO();
//        customer.setActivityTitle("测试");
//        page = marketActivityQueryService.queryMarketList(page, customer);
//        System.out.println(page.getRecords().toString());
//    }
//
//}