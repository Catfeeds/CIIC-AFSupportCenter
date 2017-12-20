package com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbqueryservice.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.entity.po.GiftPO;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbqueryservice.business.GiftQueryService;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbqueryservice.host.MainApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MainApplication.class)
public class GiftQueryServiceTest {

    @Autowired
    private GiftQueryService giftQueryService;

    @Test
    public void selectByIdTest() {
        Page<GiftPO> page = new Page<>(1, 5);
        GiftPO entity = new GiftPO();
        entity.setGiftName("test");
        page = giftQueryService.queryGiftList(page, entity);
        System.out.println(page.toString());
    }

}