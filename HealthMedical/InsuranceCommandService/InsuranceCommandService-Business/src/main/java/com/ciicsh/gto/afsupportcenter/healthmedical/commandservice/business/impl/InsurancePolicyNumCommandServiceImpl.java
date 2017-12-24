package com.ciicsh.gto.afsupportcenter.healthmedical.commandservice.business.impl;

import com.ciicsh.gto.afsupportcenter.healthmedical.commandservice.entity.bo.InsurancePolicyNumBO;
import com.ciicsh.gto.afsupportcenter.healthmedical.commandservice.entity.po.InsurancePolicyNumPO;
import com.ciicsh.gto.afsupportcenter.healthmedical.commandservice.dao.InsurancePolicyNumMapper;
import com.ciicsh.gto.afsupportcenter.healthmedical.commandservice.business.InsurancePolicyNumCommandService;
import com.ciicsh.gto.afsupportcenter.healthmedical.commandservice.business.impl.InsurancePolicyNumCommandServiceImpl;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 保单号表 服务实现类
 * </p>
 *
 * @author zhaogang
 * @since 2017-12-20
 */
@Service
public class InsurancePolicyNumCommandServiceImpl extends ServiceImpl<InsurancePolicyNumMapper, InsurancePolicyNumPO> implements InsurancePolicyNumCommandService {



   @Override
   public boolean add(InsurancePolicyNumBO policyNumBO)
   {
      InsurancePolicyNumPO po = new InsurancePolicyNumPO();
      BeanUtils.copyProperties(policyNumBO,po);
      boolean re=  insert(po);
      return re;
   }

   @Override
   public boolean update(InsurancePolicyNumBO policyNumBO)
   {
      InsurancePolicyNumPO po = new InsurancePolicyNumPO();
      BeanUtils.copyProperties(policyNumBO,po);
      boolean re=  insertOrUpdate(po);
      return re;

   }
}
