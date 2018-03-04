package com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbqueryservice.host.controller;

import com.ciicsh.gto.afcompanycenter.queryservice.api.dto.company.AfProductWithCompanyDTO;
import com.ciicsh.gto.afcompanycenter.queryservice.api.dto.employee.AfProductWithEmployeeDTO;
import com.ciicsh.gto.afcompanycenter.queryservice.api.dto.request.AfProductParamsDTO;
import com.ciicsh.gto.afcompanycenter.queryservice.api.proxy.AfProductPublicProxy;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.entity.dto.ExpCompanyDTO;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.entity.dto.ExpEmployeeDTO;
import com.ciicsh.gto.afsupportcenter.util.ExcelUtil;
import com.ciicsh.gto.afsupportcenter.util.result.JsonResult;
import com.ciicsh.gto.salecenter.apiservice.api.dto.LinkmanDTO;
import com.ciicsh.gto.salecenter.apiservice.api.dto.linkman.LinkmanListRequestDTO;
import com.ciicsh.gto.salecenter.apiservice.api.dto.linkman.LinkmanListResponseDTO;
import com.ciicsh.gto.salecenter.apiservice.api.proxy.SalLinkmanProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: guwei
 * @Description:
 * @Date: Created in 11:40 2018/3/1
 */
@RestController
@RequestMapping("/test")
public class TestFeign {

    @Autowired
    private AfProductPublicProxy afProductPublicProxy;

    @Autowired
    private SalLinkmanProxy salLinkmanProxy;

    @GetMapping("/getCompany")
    public JsonResult test1(){
        AfProductParamsDTO afProductParamsDTO = new AfProductParamsDTO();
        List<AfProductWithCompanyDTO> productWithCompany = afProductPublicProxy.getProductWithCompany(afProductParamsDTO);
        return JsonResult.success(productWithCompany);
    }

    @GetMapping("/getEmployee")
    public JsonResult test3(){
        AfProductParamsDTO afProductParamsDTO = new AfProductParamsDTO();
        List<AfProductWithEmployeeDTO> productWithEmployee = afProductPublicProxy.getProductWithEmployee(afProductParamsDTO);
        return JsonResult.success(productWithEmployee);
    }

    @GetMapping("/getLinkMan")
    public JsonResult test2(){
        LinkmanListRequestDTO linkmanListRequestDTO = new LinkmanListRequestDTO();
        linkmanListRequestDTO.setId("KH0000007");
        linkmanListRequestDTO.setType(2);
        LinkmanListResponseDTO linkman = salLinkmanProxy.list(linkmanListRequestDTO).getObject().getRecords().get(0);
        return JsonResult.success(linkman);
    }

    @GetMapping("/export")
    public void exportExcel(HttpServletResponse response) throws Exception{

        List<ExpEmployeeDTO> expCompanyDTOS = new ArrayList<>();

        ExpEmployeeDTO e = new ExpEmployeeDTO();
        e.setFirstInDate(null);
        e.setTitle("美国杰米公司");
        e.setGender(1);
        expCompanyDTOS.add(e);
        System.out.println(e.toString());

        ExcelUtil.exportExcel(expCompanyDTOS,"花名册","人员",ExpCompanyDTO.class,"公司报表.xls",response);
    }
}
