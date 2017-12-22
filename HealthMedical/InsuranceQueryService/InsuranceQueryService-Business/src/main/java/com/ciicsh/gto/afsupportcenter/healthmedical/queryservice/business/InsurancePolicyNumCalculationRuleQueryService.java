package com.ciicsh.gto.afsupportcenter.healthmedical.queryservice.business;

import com.baomidou.mybatisplus.service.IService;
import com.ciicsh.gto.afsupportcenter.healthmedical.queryservice.entity.po.InsurancePolicyNumCalculationRulePO;

import java.util.List;

/**
 * <p>
 * 保单号保费计算规则表 服务类
 * </p>
 *
 * @author zhaogang
 * @since 2017-12-20
 */
public interface InsurancePolicyNumCalculationRuleQueryService extends IService<InsurancePolicyNumCalculationRulePO> {
    List<InsurancePolicyNumCalculationRulePO> getInsurancePolicyNumCalculationRuleListByID(String ruleid);
}
