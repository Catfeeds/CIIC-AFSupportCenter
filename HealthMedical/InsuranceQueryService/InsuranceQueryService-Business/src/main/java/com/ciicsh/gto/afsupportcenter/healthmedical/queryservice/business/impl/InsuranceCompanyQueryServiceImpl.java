package com.ciicsh.gto.afsupportcenter.healthmedical.queryservice.business.impl;

import com.ciicsh.gto.afsupportcenter.healthmedical.queryservice.entity.po.InsuranceCompanyPO;
import com.ciicsh.gto.afsupportcenter.healthmedical.queryservice.dao.InsuranceCompanyMapper;
import com.ciicsh.gto.afsupportcenter.healthmedical.queryservice.business.InsuranceCompanyQueryService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 保险公司表 服务实现类
 * </p>
 *
 * @author zhaogang
 * @since 2017-12-13
 */
@Service
public class InsuranceCompanyQueryServiceImpl extends ServiceImpl<InsuranceCompanyMapper,InsuranceCompanyPO> implements InsuranceCompanyQueryService {
    @Autowired
    private InsuranceCompanyMapper insuranceCompanyMapper;

    @Override
    public InsuranceCompanyPO getEntityByID(String id) {
        return insuranceCompanyMapper.selectById(id);
    }

    @Override
    public List<InsuranceCompanyPO> getAll() {
        return insuranceCompanyMapper.getList();
    }
}
