//package com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbqueryservice.controller;
//
//import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbqueryservice.FlexibleQueryApplication;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = FlexibleQueryApplication.class)
//@AutoConfigureMockMvc
//public class GiftQueryControllerTest {
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Test
//    public void giftListTest() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.get("/gift/giftList").param("giftid", "1"))
//                .andExpect(MockMvcResultMatchers.status().isOk());
//    }
//
//}