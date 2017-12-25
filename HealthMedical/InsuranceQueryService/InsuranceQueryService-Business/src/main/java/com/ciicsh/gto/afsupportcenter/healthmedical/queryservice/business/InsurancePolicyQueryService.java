package com.ciicsh.gto.afsupportcenter.healthmedical.queryservice.business;

import com.baomidou.mybatisplus.service.IService;
import com.ciicsh.gto.afsupportcenter.healthmedical.queryservice.entity.po.InsurancePolicyPO;

import java.util.List;

/**
 * <p>
 * 保单表 服务类
 * </p>
 *
 * @author zhaogang
 * @since 2017-12-20
 */
public interface InsurancePolicyQueryService extends IService<InsurancePolicyPO> {
    List<InsurancePolicyPO> getInsurancePolicyListByID(String imsurancecompanyid);
}
