package com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbqueryservice.host.controller;

import com.ciicsh.gto.afcompanycenter.queryservice.api.dto.company.AfProductWithCompanyDTO;
import com.ciicsh.gto.afcompanycenter.queryservice.api.dto.request.AfProductParamsDTO;
import com.ciicsh.gto.afcompanycenter.queryservice.api.proxy.AfProductPublicProxy;
import com.ciicsh.gto.afsupportcenter.util.result.JsonResult;
import com.ciicsh.gto.salecenter.apiservice.api.dto.LinkmanDTO;
import com.ciicsh.gto.salecenter.apiservice.api.proxy.SalLinkmanProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/getLinkMan")
    public JsonResult test2(){
        LinkmanDTO linkmanDTO = new LinkmanDTO();
        linkmanDTO.setCompanyName("美国杰米公司");
        linkmanDTO = salLinkmanProxy.getContacts(linkmanDTO).getObject().get(0);
        return JsonResult.success(linkmanDTO);
    }
}
