package com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice.service;

import com.ciicsh.gt1.FileHandler;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.entity.po.GiftPO;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice.business.GiftCommandService;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice.host.Luncher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Luncher.class)
public class GiftCommandServiceTest {

    @Autowired
    private GiftCommandService giftCommandService;

    @Test
    public void insertGift() throws Exception {
    }


    @Test
    public void findById() {
        GiftPO entity = giftCommandService.selectById(1);
        System.out.println(entity.toString());
    }

    @Test
    public void updateMarketActivityTest() {
    }

    @Test
    public void testFileUpdate() {
        File f = new File("C:\\Users\\xiweizhen.CIIC\\Downloads\\河流.jpg");
        try {
            String filepath = FileHandler.uploadFile(f);
            System.out.printf(filepath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDeleteFile() {
        try {
            String filepath = "http://172.16.9.28:9100/3,bc763cfaa6";
            FileHandler.deleteFile(filepath);
            System.out.printf(filepath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}