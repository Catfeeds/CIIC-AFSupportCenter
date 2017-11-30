package com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice.service;

import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbqueryservice.business.GiftService;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbqueryservice.business.MarketActivityService;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbqueryservice.entity.po.GiftPO;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbqueryservice.entity.po.MarketActivityPO;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbqueryservice.host.MainApplication;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MainApplication.class)
@SuppressWarnings("SpringJavaAutowiringInspection")
public class MarketServiceQueryTest {

    @Autowired
    private MarketActivityService marketActivityService;

    @Test
    public void findByEntityTest() throws Exception {
        MarketActivityPO entity = new MarketActivityPO();
        entity.setId(1);
        List<MarketActivityPO> list = marketActivityService.findByEntity(entity);
        System.out.println(list.toString());
        int t = list.get(0).getId();
        Assert.assertEquals(t, 1);
    }

}