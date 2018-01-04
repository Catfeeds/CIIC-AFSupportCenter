package com.ciicsh.gto.afsupportcenter.healthmedical.queryservice.business.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ciicsh.gto.afsupportcenter.healthmedical.queryservice.business.InsurancePolicyBatchQueryService;
import com.ciicsh.gto.afsupportcenter.healthmedical.queryservice.dao.InsurancePolicyBatchMapper;
import com.ciicsh.gto.afsupportcenter.healthmedical.queryservice.entity.po.InsurancePolicyBatchPO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 保单号批次表 服务实现类
 * </p>
 *
 * @author zhaogang
 * @since 2017-12-20
 */
@Service
public class InsurancePolicyBatchQueryServiceImpl extends ServiceImpl<InsurancePolicyBatchMapper, InsurancePolicyBatchPO> implements InsurancePolicyBatchQueryService {

    @Override
    public List<InsurancePolicyBatchPO> getPolicyBatchByid(String policynumid)
    {
        EntityWrapper<InsurancePolicyBatchPO> entityWrapper = new EntityWrapper<>();
        entityWrapper.where("insurance_policy_num_id={0}", policynumid);
        return selectList(entityWrapper);

    }
}
