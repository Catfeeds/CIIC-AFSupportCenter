package com.ciicsh.gto.afsupportcenter.credentialscommandservice.business.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.business.EmployeeCompanyService;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.dao.EmployeeCompanyMapper;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.entity.po.EmployeeCompany;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: guwei
 * @Description:
 * @Date: Created in 16:20 2018/2/12
 */
@Service
public class EmployeeCompanyServiceImpl extends ServiceImpl<EmployeeCompanyMapper, EmployeeCompany> implements EmployeeCompanyService{

    @Autowired
    private EmployeeCompanyMapper employeeCompanyMapper;

    @Override
    public List<EmployeeCompany> select(Page page, EmployeeCompany employeeCompany) {
        return employeeCompanyMapper.select(page, employeeCompany);
    }
}
