//package com.ciicsh.gto.afsupportcenter.healthmedical.business;
//
//import com.ciicsh.gto.afsupportcenter.healthmedical.InsuranceApplication;
//import com.ciicsh.gto.afsupportcenter.healthmedical.entity.po.SupplyMedicalAcceptance;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.io.IOException;
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
////        supplyMedicalAcceptanceService.importAcceptanceXls();
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