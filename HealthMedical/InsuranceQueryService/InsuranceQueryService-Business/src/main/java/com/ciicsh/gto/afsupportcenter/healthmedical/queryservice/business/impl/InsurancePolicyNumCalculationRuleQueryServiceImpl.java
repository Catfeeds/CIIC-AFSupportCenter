package com.ciicsh.gto.afsupportcenter.healthmedical.queryservice.business.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ciicsh.gto.afsupportcenter.healthmedical.queryservice.business.InsurancePolicyNumCalculationRuleQueryService;
import com.ciicsh.gto.afsupportcenter.healthmedical.queryservice.dao.InsurancePolicyNumCalculationRuleMapper;
import com.ciicsh.gto.afsupportcenter.healthmedical.queryservice.entity.po.InsurancePolicyNumCalculationRulePO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 保单号保费计算规则表 服务实现类
 * </p>
 *
 * @author zhaogang
 * @since 2017-12-20
 */
@Service
public class InsurancePolicyNumCalculationRuleQueryServiceImpl extends ServiceImpl<InsurancePolicyNumCalculationRuleMapper, InsurancePolicyNumCalculationRulePO> implements InsurancePolicyNumCalculationRuleQueryService {

    @Override
    public List<InsurancePolicyNumCalculationRulePO> getInsurancePolicyNumCalculationRuleListByID(String ruleid) {
        EntityWrapper<InsurancePolicyNumCalculationRulePO> entityIpWrapper = new EntityWrapper<>();
        entityIpWrapper.where("insurance_policy_num_id={0}", ruleid);
        return selectList(entityIpWrapper);
    }
}
