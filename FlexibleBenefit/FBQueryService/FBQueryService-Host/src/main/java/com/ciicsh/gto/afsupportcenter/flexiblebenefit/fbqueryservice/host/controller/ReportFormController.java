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
 * @Date: Created in 15:41 2018/2/24
 */
@RestController
@RequestMapping("/api/reportform")
public class ReportFormController {

    @Autowired
    private AfProductPublicProxy afProductPublicProxy;

    @Autowired
    private SalLinkmanProxy salLinkmanProxy;

    /**
     * 获取参加活动公司清单
     * @return
     */
    @GetMapping("/get")
    public JsonResult getCompanyPage(String companyId, String companyName, String majordomo, String manager, String productId){
        AfProductParamsDTO afProductParamsDTO = new AfProductParamsDTO();
        afProductParamsDTO.setCompanyId(companyId);
        afProductParamsDTO.setProductId(productId);
        List<AfProductWithCompanyDTO> productWithCompany = afProductPublicProxy.getProductWithCompany(afProductParamsDTO);
//        LinkmanDTO linkmanDTO = new LinkmanDTO();
//        linkmanDTO.setManagementName(manager);
//        salLinkmanProxy.getContacts(linkmanDTO);
        return JsonResult.success(productWithCompany);
    }

}
