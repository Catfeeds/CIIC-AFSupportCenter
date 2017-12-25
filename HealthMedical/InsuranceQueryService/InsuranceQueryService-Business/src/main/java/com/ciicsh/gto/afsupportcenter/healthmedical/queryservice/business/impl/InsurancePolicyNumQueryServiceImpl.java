package com.ciicsh.gto.afsupportcenter.healthmedical.queryservice.business.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ciicsh.gto.afsupportcenter.healthmedical.queryservice.business.InsurancePolicyNumQueryService;
import com.ciicsh.gto.afsupportcenter.healthmedical.queryservice.dao.InsurancePolicyNumMapper;
import com.ciicsh.gto.afsupportcenter.healthmedical.queryservice.entity.bo.InsurancePolicyNumBO;
import com.ciicsh.gto.afsupportcenter.healthmedical.queryservice.entity.po.InsurancePolicyNumPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 保单号表 服务实现类
 * </p>
 *
 * @author zhaogang
 * @since 2017-12-20
 */
@Service
public class InsurancePolicyNumQueryServiceImpl extends ServiceImpl<InsurancePolicyNumMapper, InsurancePolicyNumPO> implements InsurancePolicyNumQueryService {

    @Autowired
    InsurancePolicyNumCalculationRuleQueryServiceImpl ruleQueryServiceImpl;

    @Override
    public List<InsurancePolicyNumPO> getInsurancePolicyNumListByID(String insurancepolicyid) {
        EntityWrapper<InsurancePolicyNumPO> entityIpWrapper = new EntityWrapper<>();
        entityIpWrapper.where("insurance_policy_id={0}", insurancepolicyid);
        return selectList(entityIpWrapper);
    }

    @Override
    public InsurancePolicyNumBO getInsurancePolicyNumByID(String insurancepolicynumid)
    {
         InsurancePolicyNumBO bo = baseMapper.getInsurancePolicyNumByID(insurancepolicynumid);

        bo.setInsurancePolicyNumCalculationRulePOList( ruleQueryServiceImpl.getInsurancePolicyNumCalculationRuleListByID(insurancepolicynumid));
        return bo;

    }
}
