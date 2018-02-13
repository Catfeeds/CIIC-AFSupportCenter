package com.ciicsh.gto.afsupportcenter.credentialscommandservice.business.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.business.CompanyService;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.dao.CompanyMapper;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.entity.po.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: guwei
 * @Description:
 * @Date: Created in 13:41 2018/2/12
 */
@Service
public class CompanyServiceImpl extends ServiceImpl<CompanyMapper, Company> implements CompanyService {

    @Autowired
    private CompanyMapper companyMapper;

    @Override
    public List<Company> select(Page page, Company company) {
        return companyMapper.select(page, company);
    }
}
