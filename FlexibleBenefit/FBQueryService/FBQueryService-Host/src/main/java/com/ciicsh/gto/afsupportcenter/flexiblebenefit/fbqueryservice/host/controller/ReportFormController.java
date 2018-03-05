package com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbqueryservice.host.controller;

import com.alibaba.fastjson.JSON;
import com.ciicsh.gto.afcompanycenter.queryservice.api.dto.company.AfProductWithCompanyDTO;
import com.ciicsh.gto.afcompanycenter.queryservice.api.dto.employee.AfProductWithEmployeeDTO;
import com.ciicsh.gto.afcompanycenter.queryservice.api.dto.request.AfProductParamsDTO;
import com.ciicsh.gto.afcompanycenter.queryservice.api.proxy.AfProductPublicProxy;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.entity.dto.ExpCompanyDTO;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.entity.dto.ExpEmployeeDTO;
import com.ciicsh.gto.afsupportcenter.util.ExcelUtil;
import com.ciicsh.gto.afsupportcenter.util.result.JsonResult;
import com.ciicsh.gto.salecenter.apiservice.api.dto.linkman.LinkmanListRequestDTO;
import com.ciicsh.gto.salecenter.apiservice.api.dto.linkman.LinkmanListResponseDTO;
import com.ciicsh.gto.salecenter.apiservice.api.proxy.SalLinkmanProxy;
import org.springframework.beans.BeanUtils;
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
     * 导出参加活动公司清单
     * @return
     */
    @GetMapping("/get4")
    public JsonResult getCompanyPage(String companyId, String companyName,
                                     String majordomo, String manager,
                                     String productId, HttpServletResponse response){
        AfProductParamsDTO afProductParamsDTO = new AfProductParamsDTO();
        afProductParamsDTO.setCompanyId(companyId);
        afProductParamsDTO.setProductId(productId);
        List<AfProductWithCompanyDTO> productWithCompany = afProductPublicProxy.getProductWithCompany(afProductParamsDTO);
        List<ExpCompanyDTO> expCompanyDTOS = new ArrayList<>();
        productWithCompany.stream().forEach( i -> {
            ExpCompanyDTO expCompanyDTO = new ExpCompanyDTO();
            BeanUtils.copyProperties(i,expCompanyDTO);
            LinkmanListRequestDTO linkmanListRequestDTO = new LinkmanListRequestDTO();
            linkmanListRequestDTO.setId(i.getCompanyId());
            linkmanListRequestDTO.setType(2);
            LinkmanListResponseDTO linkman = salLinkmanProxy.list(linkmanListRequestDTO).getObject().getRecords().get(0);
            BeanUtils.copyProperties(linkman,expCompanyDTO);
            expCompanyDTO.setTelautogramNum(linkman.getFax());
            expCompanyDTO.setTelNum(linkman.getCompanyTelNum());
            expCompanyDTO.setPostCode(linkman.getCompanyZip());
            expCompanyDTO.setAddress(linkman.getCompanyAddr());
            expCompanyDTOS.add(expCompanyDTO);
        });

        ExcelUtil.exportExcel(expCompanyDTOS,"参加活动的公司","sheet1",ExpCompanyDTO.class,"参加活动公司报表.xls",response);
        return JsonResult.success(null);
    }

    /**
     * 导出参加活动雇员清单
     * @return
     */
    @GetMapping("/get5")
    public JsonResult exportEmployee(String companyId, String companyName,
                                     String majordomo, String manager,
                                     String productId, HttpServletResponse response){
        AfProductParamsDTO afProductParamsDTO = new AfProductParamsDTO();
        afProductParamsDTO.setCompanyId(companyId);
        afProductParamsDTO.setProductId(productId);
        List<AfProductWithEmployeeDTO> productWithEmployee = afProductPublicProxy.getProductWithEmployee(afProductParamsDTO);
        ArrayList<ExpEmployeeDTO> expEmployeeDTOS = new ArrayList<>();
        productWithEmployee.stream().forEach( i -> {
            ExpEmployeeDTO expEmployeeDTO = new ExpEmployeeDTO();
            BeanUtils.copyProperties(i,expEmployeeDTO);
            //todo cityCode => city

            LinkmanListRequestDTO linkmanListRequestDTO = new LinkmanListRequestDTO();
            linkmanListRequestDTO.setId(i.getCompanyId());
            linkmanListRequestDTO.setType(2);
            LinkmanListResponseDTO linkman = salLinkmanProxy.list(linkmanListRequestDTO).getObject().getRecords().get(0);
            BeanUtils.copyProperties(linkman,expEmployeeDTO);

            expEmployeeDTOS.add(expEmployeeDTO);
        });

        ExcelUtil.exportExcel(expEmployeeDTOS,"参加活动的雇员","sheet1",ExpEmployeeDTO.class,"参加活动雇员报表.xls",response);
        return JsonResult.success(null);
    }

}
