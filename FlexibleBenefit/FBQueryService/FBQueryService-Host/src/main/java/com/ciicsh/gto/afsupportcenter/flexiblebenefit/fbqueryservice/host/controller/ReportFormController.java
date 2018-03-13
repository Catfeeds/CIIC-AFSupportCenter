package com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbqueryservice.host.controller;

import com.ciicsh.gto.afcompanycenter.queryservice.api.dto.company.AfProductWithCompanyDTO;
import com.ciicsh.gto.afcompanycenter.queryservice.api.dto.employee.AfProductWithEmployeeDTO;
import com.ciicsh.gto.afcompanycenter.queryservice.api.dto.request.AfProductParamsDTO;
import com.ciicsh.gto.afcompanycenter.queryservice.api.proxy.AfProductPublicProxy;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.entity.dto.ExpBirthday1DTO;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.entity.dto.ExpBirthday2DTO;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.entity.dto.ExpCompanyDTO;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.entity.dto.ExpEmployeeDTO;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.entity.dto.ExpLiteratureDTO;
import com.ciicsh.gto.afsupportcenter.util.ExcelUtil;
import com.ciicsh.gto.afsupportcenter.util.result.JsonResult;
import com.ciicsh.gto.basicdataservice.api.CityServiceProxy;
import com.ciicsh.gto.basicdataservice.api.dto.CityDTO;
import com.ciicsh.gto.salecenter.apiservice.api.dto.company.AfCompanyDetailResponseDTO;
import com.ciicsh.gto.salecenter.apiservice.api.dto.linkman.LinkmanListRequestDTO;
import com.ciicsh.gto.salecenter.apiservice.api.dto.linkman.LinkmanListResponseDTO;
import com.ciicsh.gto.salecenter.apiservice.api.proxy.CompanyProxy;
import com.ciicsh.gto.salecenter.apiservice.api.proxy.SalLinkmanProxy;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

    @Autowired
    private CompanyProxy companyProxy;

    @Autowired
    private CityServiceProxy cityServiceProxy;

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
//        afProductParamsDTO.set
        List<AfProductWithEmployeeDTO> productWithEmployee = afProductPublicProxy.getProductWithEmployee(afProductParamsDTO);
        ArrayList<ExpEmployeeDTO> expEmployeeDTOS = new ArrayList<>();
        productWithEmployee.stream().forEach( i -> {
            ExpEmployeeDTO expEmployeeDTO = new ExpEmployeeDTO();
            BeanUtils.copyProperties(i,expEmployeeDTO);
            CityDTO city = cityServiceProxy.selectByCityCode(i.getWorkCityCode());
            expEmployeeDTO.setCity(city.getCityName());

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

    /**
     * 生日基础
     * @return
     */
    @GetMapping("/get2")
    public JsonResult exportBirthday1(String companyId, String companyName,
                                      String manager,String birthday,
                                      HttpServletResponse response) throws ParseException {
        AfProductParamsDTO afProductParamsDTO = new AfProductParamsDTO();
        //todo birthday
        if (birthday != "") {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = sdf.parse(birthday);
        }

        afProductParamsDTO.setCompanyId(companyId);
        afProductParamsDTO.setProductId("CPDFL1800059");
        List<AfProductWithEmployeeDTO> productWithEmployee = afProductPublicProxy.getProductWithEmployee(afProductParamsDTO);
        List<ExpBirthday1DTO> expBirthday1DTOS = new ArrayList<>();
        productWithEmployee.stream().forEach( i -> {
            ExpBirthday1DTO expBirthday1DTO = new ExpBirthday1DTO();
            BeanUtils.copyProperties(i,expBirthday1DTO);

            AfCompanyDetailResponseDTO companyDetail = companyProxy.afDetail(i.getCompanyId()).getObject();
            BeanUtils.copyProperties(companyDetail,expBirthday1DTO);
            expBirthday1DTO.setCompanyAddr(companyDetail.getRegisteredAddress());
            expBirthday1DTOS.add(expBirthday1DTO);
        });
        ExcelUtil.exportExcel(expBirthday1DTOS,"参加生日基础服务的雇员","sheet1",ExpBirthday1DTO.class,"参加生日基础服务雇员报表.xls",response);
        return JsonResult.success(null);
    }

    /**
     * 生日标准
     * @return
     */
    @GetMapping("/get3")
    public JsonResult exportBirthday2(String companyId, String companyName,
                                      String manager,String birthday,
                                      HttpServletResponse response) throws ParseException {
        AfProductParamsDTO afProductParamsDTO = new AfProductParamsDTO();
        if (birthday != "") {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = sdf.parse(birthday);
        }

        //todo birthday
        afProductParamsDTO.setCompanyId(companyId);
        afProductParamsDTO.setProductId("CPDFL1800060");
        List<AfProductWithEmployeeDTO> productWithEmployee = afProductPublicProxy.getProductWithEmployee(afProductParamsDTO);
        List<ExpBirthday2DTO> expBirthday2DTOS = new ArrayList<>();
        productWithEmployee.stream().forEach( i -> {
            ExpBirthday2DTO expBirthday2DTO = new ExpBirthday2DTO();
            BeanUtils.copyProperties(i,expBirthday2DTO);
            expBirthday2DTOS.add(expBirthday2DTO);
        });
        ExcelUtil.exportExcel(expBirthday2DTOS,"参加生日标准服务的雇员","sheet1",ExpBirthday2DTO.class,"参加生日标准服务雇员报表.xls",response);
        return JsonResult.success(null);
    }

    /**
     * 文艺欣赏
     * @return
     */
    @GetMapping("/get7")
    public JsonResult exportBirthday2(String companyId, String companyName,
                                      String employeeId,HttpServletResponse response){
        AfProductParamsDTO afProductParamsDTO = new AfProductParamsDTO();
        afProductParamsDTO.setCompanyId(companyId);
        afProductParamsDTO.setProductId("CPDFL1800088");
        List<AfProductWithEmployeeDTO> productWithEmployee1 = afProductPublicProxy.getProductWithEmployee(afProductParamsDTO);
        AfProductParamsDTO afProductParamsDTO1 = new AfProductParamsDTO();
        afProductParamsDTO1.setCompanyId(companyId);
        afProductParamsDTO1.setProductId("CPDFL1800089");
        List<AfProductWithEmployeeDTO> productWithEmployee2 = afProductPublicProxy.getProductWithEmployee(afProductParamsDTO);
        List<AfProductWithEmployeeDTO> productWithEmployee = new ArrayList<>();
        boolean b = productWithEmployee.addAll(productWithEmployee1);
        boolean b1 = productWithEmployee.addAll(productWithEmployee2);

        List<ExpLiteratureDTO> expLiteratureDTOs = new ArrayList<>();
        productWithEmployee.stream().forEach( i -> {
            ExpLiteratureDTO expLiteratureDTO = new ExpLiteratureDTO();
            BeanUtils.copyProperties(i,expLiteratureDTO);
            expLiteratureDTOs.add(expLiteratureDTO);
        });
        ExcelUtil.exportExcel(expLiteratureDTOs,"参加文艺欣赏服务的雇员","sheet1",ExpLiteratureDTO.class,"参加文艺欣赏服务雇员报表.xls",response);
        return JsonResult.success(null);
    }


    /**
     * 获取产品列表
     */
}
