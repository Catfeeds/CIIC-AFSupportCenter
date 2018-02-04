package com.ciicsh.gto.afsupportcenter.healthmedical.business;

import com.alibaba.fastjson.JSON;
import com.ciicsh.gto.afsupportcenter.healthmedical.InsuranceApplication;
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.po.SupplyMedicalInvoice;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = InsuranceApplication.class)
public class SupplyMedicalInvoiceServiceTest {
    @Autowired
    private SupplyMedicalInvoiceService supplyMedicalInvoiceService;

    @Test
    public void deleteByEntity() {
        SupplyMedicalInvoice supplyMedicalInvoice = new SupplyMedicalInvoice();
        supplyMedicalInvoice.setAcceptanceId("201801090523-2");
        supplyMedicalInvoiceService.deleteByAcceptanceId("201801090523-2");
    }

    @Test
    public void queryMedicalInvoiceList() {
        List<SupplyMedicalInvoice> supplyMedicalInvoiceList = supplyMedicalInvoiceService.queryMedicalInvoiceList("201801150281-2");
        String str = JSON.toJSONString(supplyMedicalInvoiceList);
        System.out.println(str);
    }
}