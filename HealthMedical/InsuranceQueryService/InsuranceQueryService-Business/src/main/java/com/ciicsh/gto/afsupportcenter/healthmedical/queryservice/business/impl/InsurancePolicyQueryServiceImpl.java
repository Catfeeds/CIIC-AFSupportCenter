package com.ciicsh.gto.afsupportcenter.healthmedical.queryservice.business.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ciicsh.gto.afsupportcenter.healthmedical.queryservice.business.InsurancePolicyQueryService;
import com.ciicsh.gto.afsupportcenter.healthmedical.queryservice.dao.InsurancePolicyMapper;
import com.ciicsh.gto.afsupportcenter.healthmedical.queryservice.entity.po.InsurancePolicyPO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 保单表 服务实现类
 * </p>
 *
 * @author zhaogang
 * @since 2017-12-20
 */
@Service
public class InsurancePolicyQueryServiceImpl extends ServiceImpl<InsurancePolicyMapper, InsurancePolicyPO> implements InsurancePolicyQueryService {

    @Override
   public List<InsurancePolicyPO> getInsurancePolicyListByID(String imsurancecompanyid)
    {
        EntityWrapper<InsurancePolicyPO> entityIpWrapper = new EntityWrapper<>();
        entityIpWrapper.like("insurance_company_id", imsurancecompanyid);
        return selectList(entityIpWrapper);

    }
}
