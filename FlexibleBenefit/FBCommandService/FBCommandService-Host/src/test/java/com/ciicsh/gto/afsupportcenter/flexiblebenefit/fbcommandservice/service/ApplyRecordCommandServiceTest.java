package com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice.service;

import com.ciicsh.gto.afsupportcenter.flexiblebenefit.entity.po.ApplyRecordPO;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice.Luncher;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice.business.ApplyRecordCommandService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Luncher.class)
public class ApplyRecordCommandServiceTest {
    @Autowired
    private ApplyRecordCommandService applyRecordCommandService;

    @Test
    public void insertSelective() throws Exception {
        ApplyRecordPO applyRecordPO = new ApplyRecordPO();
        applyRecordPO.setApplyTime(new Date());
        applyRecordPO.setApplyType(2);
        Integer t = applyRecordCommandService.insertSelective(applyRecordPO);
        System.out.println(t);
        System.out.println(applyRecordPO.toString());
    }

}