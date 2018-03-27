package com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.business.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.bo.SalCompanyBO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.business.ISalCompanyService;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.dao.SalCompanyMapper;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.entity.SalCompany;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageKit;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhangzhiwen on 2018/3/20.
 */
@Service
public class SalCompanyServiceImpl extends ServiceImpl<SalCompanyMapper, SalCompany> implements ISalCompanyService {

    @Override
    public PageRows<SalCompanyBO> querySalCompanyList(PageInfo pageInfo) {
        SalCompanyBO salCompanyBO = pageInfo.toJavaObject(SalCompanyBO.class);
        return PageKit.doSelectPage(pageInfo,() -> baseMapper.querySalCompanyList(salCompanyBO));
    }

    @Override
    public List<SalCompanyBO> getSalCompanyList(SalCompanyBO salCompanyBO) {
        return baseMapper.querySalCompanyList(salCompanyBO);
    }
}
