//package com.ciicsh.gto.afsupportcenter.healthmedical.business;
//
//import com.ciicsh.gto.afsupportcenter.healthmedical.InsuranceApplication;
//import com.ciicsh.gto.afsupportcenter.healthmedical.entity.po.SupplyMedicalInvoice;
//import com.ciicsh.gto.employeecenter.apiservice.api.dto.EmployeeMemberDTO;
//import com.ciicsh.gto.employeecenter.apiservice.api.proxy.EmployeeInfoProxy;
//import com.ciicsh.gto.productcenter.apiservice.api.dto.JsonResult;
//import com.ciicsh.gto.productcenter.apiservice.api.dto.ProductExtDTO;
//import com.ciicsh.gto.productcenter.apiservice.api.proxy.ProductProxy;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = InsuranceApplication.class)
//public class SupplyMedicalInvoiceServiceTest {
//    @Autowired
//    private SupplyMedicalInvoiceService supplyMedicalInvoiceService;
//    @Autowired
//    private ProductProxy productProxy;
//    @Autowired
//    private EmployeeInfoProxy employeeInfoProxy;
//
//    @Test
//    public void deleteByEntity() {
//        SupplyMedicalInvoice supplyMedicalInvoice = new SupplyMedicalInvoice();
//        supplyMedicalInvoice.setAcceptanceId("201801090523-2");
//        supplyMedicalInvoiceService.deleteByAcceptanceId("201801090523-2");
//    }
//
//    @Test
//    public void queryMedicalInvoiceList() {
//        List<SupplyMedicalInvoice> supplyMedicalInvoiceList = supplyMedicalInvoiceService.queryMedicalInvoiceList(null);
//        List<SupplyMedicalInvoice> sdsd = supplyMedicalInvoiceList.stream().filter(t -> "201801310392".equals(t.getAcceptanceId())).collect(Collectors.toList());
//        String str = "sodas";
//        System.out.println(str);
//    }
//
//    @Test
//    public void getProductByProductIDs() {
//        List<String> ids = new ArrayList<>();
//        ids.add("CPDFL1800006");
//        JsonResult jsonResult = productProxy.getProductByProductIDs(ids);
//        List<ProductExtDTO> productExtDTOS = (List<ProductExtDTO>) jsonResult.getData();
//        String serviceItem = productExtDTOS.get(0).getServiceItems();
//        System.out.println(serviceItem);
//    }
//
//    @Test
//    public void getEmployeeMemberInfo() {
//        com.ciicsh.gto.employeecenter.util.JsonResult<List<EmployeeMemberDTO>> jsonResult = employeeInfoProxy.getEmployeeMemberInfo("1806940");
//        List<EmployeeMemberDTO> employeeMemberDTOS = jsonResult.getData();
//        System.out.println(employeeMemberDTOS.toString());
//    }
//}