package com.ciicsh.gto.afsupportcenter.credentialscommandservice.host.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.business.EmployeeCompanyService;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.entity.po.Company;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.entity.po.EmployeeCompany;
import com.ciicsh.gto.afsupportcenter.util.page.PageUtil;
import com.ciicsh.gto.afsupportcenter.util.result.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: guwei
 * @Description:
 * @Date: Created in 16:36 2018/2/12
 */
@RestController
@RequestMapping("/api/emp")
public class EmployeeCompanyController {

    @Autowired
    private EmployeeCompanyService employeeCompanyService;

    /**
     * 获取雇员列表
     * @param pageNum
     * @param pageSize
     * @param companyId
     * @param employeeId
     * @param employeeName
     * @param idNum
     * @param status
     * @return
     */
    @GetMapping("/find")
    public JsonResult getEmpPage(Integer pageNum, Integer pageSize, String companyId, String employeeId,
                                 String employeeName, String idNum, Integer status) {
        Page page = new Page(PageUtil.setPageNum(pageNum), PageUtil.setPageSize(pageSize));
        EmployeeCompany employeeCompany = new EmployeeCompany();
        employeeCompany.setCompanyId(companyId);
        employeeCompany.setEmployeeId(employeeId);
        employeeCompany.setEmployeeName(employeeName);
        employeeCompany.setIdNum(idNum);
        employeeCompany.setStatus(status);
        List<Company> list = employeeCompanyService.select(page, employeeCompany);
        page.setRecords(list);
        return JsonResult.success(page);
    }
}
