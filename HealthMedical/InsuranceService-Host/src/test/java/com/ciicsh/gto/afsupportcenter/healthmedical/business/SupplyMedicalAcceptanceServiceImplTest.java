//package com.ciicsh.gto.afsupportcenter.healthmedical.business;
//
//import com.ciicsh.gto.afsupportcenter.healthmedical.InsuranceApplication;
//import com.ciicsh.gto.afsupportcenter.healthmedical.customer.po.SupplyMedicalAcceptance;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.io.*;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = InsuranceApplication.class)
//public class SupplyMedicalAcceptanceServiceImplTest {
//    @Autowired
//    private SupplyMedicalAcceptanceService supplyMedicalAcceptanceService;
//
//    @Test
//    public void syncAcceptanceSummary() {
//        supplyMedicalAcceptanceService.syncAcceptanceSummaryDetail();
//    }
//
//    @Test
//    public void importAcceptanceXls() {
//        File file = new File("C:\\Users\\xiweizhen.CIIC\\Desktop\\test\\02-JY201801231002.xlsx");
//        try {
//            InputStream inputStream = new FileInputStream(file);
//            supplyMedicalAcceptanceService.importAcceptanceXls(inputStream);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Test
//    public void test() {
//        SupplyMedicalAcceptance supplyMedicalAcceptance = supplyMedicalAcceptanceService.selectById("201801150415-2");
//        if (supplyMedicalAcceptance != null) {
//            System.out.println(supplyMedicalAcceptance.toString());
//        }
//    }
//}