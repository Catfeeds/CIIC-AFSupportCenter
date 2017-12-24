package com.ciicsh.gto.afsupportcenter.healthmedical.queryservice.business;

import com.baomidou.mybatisplus.service.IService;
import com.ciicsh.gto.afsupportcenter.healthmedical.queryservice.entity.po.InsurancePolicyBatchPO;

import java.util.List;

/**
 * <p>
 * 保单号批次表 服务类
 * </p>
 *
 * @author zhaogang
 * @since 2017-12-20
 */
public interface InsurancePolicyBatchQueryService extends IService<InsurancePolicyBatchPO> {
    List<InsurancePolicyBatchPO> getPolicyBatchByid(String policynumid);
}
