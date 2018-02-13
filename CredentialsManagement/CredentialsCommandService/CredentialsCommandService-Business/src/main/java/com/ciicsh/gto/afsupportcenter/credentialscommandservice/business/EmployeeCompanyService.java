package com.ciicsh.gto.afsupportcenter.credentialscommandservice.business;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.entity.po.Company;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.entity.po.EmployeeCompany;

import java.util.List;

/**
 * @Author: guwei
 * @Description:
 * @Date: Created in 16:19 2018/2/12
 */
public interface EmployeeCompanyService extends IService<EmployeeCompany> {

    /**
     * 获取雇员列表
     * @param page
     * @param employeeCompany
     * @return
     */
    List<Company> select(Page page, EmployeeCompany employeeCompany);

}
