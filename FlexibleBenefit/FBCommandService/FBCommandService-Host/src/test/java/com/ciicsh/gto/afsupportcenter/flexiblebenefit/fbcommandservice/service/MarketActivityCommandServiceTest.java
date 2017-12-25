package com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice.service;


import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice.Luncher;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice.business.MarketActivityCommandService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * created by xwz on 2017年12月4日10:46:48
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Luncher.class)
public class MarketActivityCommandServiceTest {
    @Autowired
    private MarketActivityCommandService marketActivityCommandService;

    @Test
    public void findByIdTest() {
    }

    @Test
    public void addMarketActivityTest() {
    }

    @Test
    public void updateMarketActivityTest() {
    }
}
