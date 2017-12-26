package com.ciicsh.gto.afsupportcenter.healthmedical.queryservice.business;

import com.baomidou.mybatisplus.service.IService;
import com.ciicsh.gto.afsupportcenter.healthmedical.queryservice.entity.po.InsurancePolicyNumPO;
import com.ciicsh.gto.afsupportcenter.healthmedical.queryservice.entity.bo.InsurancePolicyNumBO;

import java.util.List;

/**
 * <p>
 * 保单号表 服务类
 * </p>
 *
 * @author zhaogang
 * @since 2017-12-20
 */
public interface InsurancePolicyNumQueryService extends IService<InsurancePolicyNumPO> {
    List<InsurancePolicyNumPO> getInsurancePolicyNumListByID(String insurancepolicyid);
    InsurancePolicyNumBO getInsurancePolicyNumByID(String insurancepolicynumid);
}
