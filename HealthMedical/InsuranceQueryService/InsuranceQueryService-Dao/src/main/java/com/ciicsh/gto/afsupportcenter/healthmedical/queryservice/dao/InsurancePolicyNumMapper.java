package com.ciicsh.gto.afsupportcenter.healthmedical.queryservice.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.ciicsh.gto.afsupportcenter.healthmedical.queryservice.entity.bo.InsurancePolicyNumBO;
import com.ciicsh.gto.afsupportcenter.healthmedical.queryservice.entity.po.InsurancePolicyNumPO;

/**
 * <p>
  * 保单号表 Mapper 接口
 * </p>
 *
 * @author zhaogang
 * @since 2017-12-20
 */
public interface InsurancePolicyNumMapper extends BaseMapper<InsurancePolicyNumPO> {
    InsurancePolicyNumBO getInsurancePolicyNumByID(String insurancepolicynumid);
}