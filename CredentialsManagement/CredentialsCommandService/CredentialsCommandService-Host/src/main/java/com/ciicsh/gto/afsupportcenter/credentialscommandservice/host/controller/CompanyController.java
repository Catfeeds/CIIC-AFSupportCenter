package com.ciicsh.gto.afsupportcenter.credentialscommandservice.host.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.business.CompanyService;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.entity.dto.CompanyDTO;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.entity.po.Company;
import com.ciicsh.gto.afsupportcenter.util.page.PageUtil;
import com.ciicsh.gto.afsupportcenter.util.result.JsonResult;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: guwei
 * @Description:
 * @Date: Created in 13:58 2018/2/12
 */
@RestController
@RequestMapping("/api/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @GetMapping("/get")
    public JsonResult getCompanyPage(Integer pageNum, Integer pageSize, String companyName, String companyId){
        Page page = new Page(PageUtil.setPageNum(pageNum), PageUtil.setPageSize(pageSize));
        Company company = new Company();
        company.setCompanyId(companyId);
        company.setCompanyName(companyName);
        List<Company> list = companyService.select(page, company);
        ArrayList<CompanyDTO> resultList = new ArrayList<CompanyDTO>();
        list.stream().forEach(i -> {
            CompanyDTO companyDTO = new CompanyDTO();
            BeanUtils.copyProperties(i,companyDTO);
            resultList.add(companyDTO);
        });
        page.setRecords(resultList);
        return JsonResult.success(page);
    }
}
