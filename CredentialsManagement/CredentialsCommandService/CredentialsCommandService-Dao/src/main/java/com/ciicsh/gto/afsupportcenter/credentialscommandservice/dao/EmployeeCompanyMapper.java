package com.ciicsh.gto.afsupportcenter.credentialscommandservice.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.entity.po.Company;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.entity.po.EmployeeCompany;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: guwei
 * @Description:
 * @Date: Created in 16:32 2018/2/12
 */
@Repository
public interface EmployeeCompanyMapper extends BaseMapper<EmployeeCompany> {

    /**
     * 查询雇员列表
     * @param page
     * @param employeeCompany
     * @return
     */
    List<Company> select(Page page, EmployeeCompany employeeCompany);
}
