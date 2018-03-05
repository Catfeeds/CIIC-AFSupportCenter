//package com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbqueryservice.service;
//
//import com.baomidou.mybatisplus.plugins.Page;
//import com.ciicsh.gto.afsupportcenter.flexiblebenefit.customer.po.GiftPO;
//import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbqueryservice.FlexibleQueryApplication;
//import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbqueryservice.business.GiftQueryService;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = FlexibleQueryApplication.class)
//public class GiftQueryServiceTest {
//
//    @Autowired
//    private GiftQueryService giftQueryService;
//
//    @Test
//    public void selectByIdTest() {
//        Page<GiftPO> page = new Page<>(1, 5);
//        GiftPO customer = new GiftPO();
//        customer.setStatus(1);
//        page = giftQueryService.queryGiftList(page, customer);
//        System.out.println(page.toString());
//    }
//
//}