package com.ciicsh.gto.afsupportcenter.socialsecurity.siteservice.host.controller;

import com.ciicsh.gto.afcompanycenter.queryservice.api.dto.employee.AfEmployeeCompanyDTO;
import com.ciicsh.gto.afcompanycenter.queryservice.api.dto.employee.AfEmployeeInfoDTO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo.SsEmpTaskBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business.SsEmpTaskFrontService;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.dao.SsEmpTaskMapper;
import com.ciicsh.gto.settlementcenter.payment.cmdapi.EmployeeMonthlyDataProxy;
//import com.ciicsh.gto.settlementcenter.payment.cmdapi.dto.EmployeeMonthlyDataProxyDTO;
import com.ciicsh.gto.sheetservice.api.dto.TaskCreateMsgDTO;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 本 controller 测试专用，不做其他业务处理使用
 */
@RestController
@RequestMapping("/api/soccommandservice/test")
public class TestController {
    @Autowired
    SsEmpTaskMapper empTaskService;

    @RequestMapping(value = "/exprot")
    public void download(HttpServletRequest request, HttpServletResponse response, SsEmpTaskBO dto) {
        String fileName = "test.xlsx";
        try {
            FileInputStream is = new FileInputStream("D:\\word\\" + fileName);
//            Workbook workbook = ExcelKit.createWorkbookForResponse(fileName, is, response);
//            workbook.write(response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //处理文件上传
    @PostMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file, @RequestParam(name = "companyId", required = false) String companyId) {
        return "";
    }
    @Autowired
SsEmpTaskFrontService ssEmpTaskFrontService;
    //处理文件上传

    @RequestMapping(value = "/test")
    public void test() throws Exception {
        TaskCreateMsgDTO taskMsgDTO=new TaskCreateMsgDTO(); Integer taskCategory=3;
            AfEmployeeInfoDTO dto =new AfEmployeeInfoDTO();
        AfEmployeeCompanyDTO companyDTO=new AfEmployeeCompanyDTO();
        companyDTO.setCompanyId("KH0000003");
        companyDTO.setEmployeeId("00000003044444444");
        dto.setEmployeeCompany(companyDTO);
//        ssEmpTaskFrontService.insertTaskTb(taskMsgDTO,taskCategory,0,dto);
    }

    @Test
    @RequestMapping(value = "/test1")
    public void test1(){
        long id=231;
        BigDecimal d;
        List<Map<String,BigDecimal>> list=empTaskService.fetchInjuryRatio(id ,"201712");
        System.out.println(list.size());
    }

    @Autowired
    private EmployeeMonthlyDataProxy employeeMonthlyDataProxy;

    @Autowired
    com.ciicsh.gto.settlementcenter.payment.cmdapi.PayapplyServiceProxy PayapplyServiceProxy;
/*

    @RequestMapping(value = "/mytest")
    public  void mytest() {
//        PayApplyProxyDTO aaa = new PayApplyProxyDTO();
//        Object aaaaaa = PayapplyServiceProxy.addShSocialInsurancePayApply(aaa);

        EmployeeMonthlyDataProxyDTO a = new EmployeeMonthlyDataProxyDTO();
        Object o = employeeMonthlyDataProxy.employeeCanPay(a);
     System.out.println("=============");
    }
*/

}
