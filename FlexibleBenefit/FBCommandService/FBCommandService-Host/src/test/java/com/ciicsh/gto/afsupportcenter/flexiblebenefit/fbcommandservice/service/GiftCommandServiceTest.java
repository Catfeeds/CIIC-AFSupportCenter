//package com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice.service;
//
//import com.ciicsh.gt1.FileHandler;
//import com.ciicsh.gto.afsupportcenter.flexiblebenefit.customer.po.GiftPO;
//import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice.FlexibleCommandApplication;
//import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice.business.GiftCommandService;
//import org.junit.Assert;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.io.File;
//import java.io.IOException;
//import java.math.BigDecimal;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = FlexibleCommandApplication.class)
//public class GiftCommandServiceTest {
//    @Autowired
//    private GiftCommandService giftCommandService;
//
//    @Test
//    public void giftInsert() {
//        GiftPO customer = new GiftPO();
//        customer.setGiftName("2017年11月16日18:42:02");
//        customer.setPrice(BigDecimal.valueOf(231));
//        customer.setRightPerson(0);
//        customer.setGiftType(0);
//        customer.setColor("红色");
//        customer.setNewTag(true);
//        customer.setNumber(100);
//        customer.setApplyMaxnum(51);
//        customer.setRemarks("ceshi");
//        customer.setCreatedBy("xiweizhen");
//        customer.setModifiedBy("ceshi");
//        boolean flag = giftCommandService.insert(customer);
//        Assert.assertEquals(flag, true);
//    }
//
//    @Test
//    public void findById() {
//        GiftPO customer = giftCommandService.findById(1);
//        System.out.println(customer.toString());
//    }
//
//    @Test
//    public void updateMarketActivityTest() {
//    }
//
//    @Test
//    public void testFileUpdate() {
//        File f = new File("C:\\Users\\xiweizhen.CIIC\\Downloads\\河流.jpg");
//        try {
//            String filepath = FileHandler.uploadFile(f);
//            System.out.printf(filepath);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Test
//    public void testDeleteFile() {
//        try {
//            String filepath = "http://172.16.9.28:9100/3,bc763cfaa6";
//            FileHandler.deleteFile(filepath);
//            System.out.printf(filepath);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//}