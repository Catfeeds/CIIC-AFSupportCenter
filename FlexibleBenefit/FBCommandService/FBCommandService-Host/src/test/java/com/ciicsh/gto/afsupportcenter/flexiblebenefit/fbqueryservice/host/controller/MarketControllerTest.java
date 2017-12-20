package com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbqueryservice.host.controller;

import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice.host.Luncher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * @author xiweizhen
 * @date 2017/12/6 11:08
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Luncher.class)
@AutoConfigureMockMvc
public class MarketControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void giftListTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/marketCommandService/findById/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}