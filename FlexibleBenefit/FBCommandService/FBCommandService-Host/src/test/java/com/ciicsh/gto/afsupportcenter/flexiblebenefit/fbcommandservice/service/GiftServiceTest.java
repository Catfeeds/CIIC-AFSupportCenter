package com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice.service;

import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice.entity.po.GiftPO;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice.business.GiftService;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice.host.Luncher;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Luncher.class)
@SuppressWarnings("SpringJavaAutowiringInspection")
public class GiftServiceTest {

    @Autowired
    private GiftService giftService;

    @Test
    public void insertGift() throws Exception {
        GiftPO entity = new GiftPO();
        entity.setGiftName("2017年11月16日18:42:02");
        entity.setPrice(BigDecimal.valueOf(231));
        entity.setRightPerson((byte) 0);
        entity.setGiftType((byte) 0);
        entity.setColor("红色");
        entity.setNumber(100);
        entity.setApplyMaxnum(51);
        entity.setRemarks("ceshi");
        entity.setCreatedBy("xiweizhen");
        entity.setModifiedBy("ceshi");
        int t = giftService.insertGift(entity);
        Assert.assertEquals(t, 1);
    }

    @Test
    public void findByEntityTest() throws Exception {
        GiftPO entity = new GiftPO();
        entity.setId(1);
        List<GiftPO> list = giftService.findByEntity(entity);
        System.out.println(list.toString());
        int t = list.get(0).getId();
        Assert.assertEquals(t, 1);
    }

    @Test
    public void findById() throws Exception {
        GiftPO entity = giftService.findById(1);
        int t = entity.getId();
        Assert.assertEquals(t, 1);
    }

}