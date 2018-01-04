package com.ciicsh.gto.afsupportcenter.healthmedical.commandservice.business;

import com.ciicsh.gto.afsupportcenter.healthmedical.commandservice.entity.bo.InsurancePolicyNumBO;
import com.ciicsh.gto.afsupportcenter.healthmedical.commandservice.entity.po.InsurancePolicyNumPO;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 保单号表 服务类
 * </p>
 *
 * @author zhaogang
 * @since 2017-12-20
 */
public interface InsurancePolicyNumCommandService extends IService<InsurancePolicyNumPO> {
    boolean add(InsurancePolicyNumBO policyNumBO);
    boolean update(InsurancePolicyNumBO policyNumBO);
}
