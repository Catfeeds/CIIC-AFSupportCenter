package com.ciicsh.gto.afsupportcenter.healthmedical.queryservice.business.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.ciicsh.gto.afsupportcenter.healthmedical.queryservice.entity.po.AgentBusinessPO;
import com.ciicsh.gto.afsupportcenter.healthmedical.queryservice.entity.bo.AgentBusinessBO;
import com.ciicsh.gto.afsupportcenter.healthmedical.queryservice.dao.AgentBusinessMapper;
import com.ciicsh.gto.afsupportcenter.healthmedical.queryservice.business.AgentBusinessQueryService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 保险公司表 服务实现类
 * </p>
 *
 * @author zhaogang
 * @since 2017-12-13
 */
@Service
public class AgentBusinessQueryServiceImpl extends ServiceImpl<AgentBusinessMapper, AgentBusinessPO> implements AgentBusinessQueryService {

    @Override
    public List<AgentBusinessBO> getAgentBusinessIPListById(String id) {
        return baseMapper.getAgentBusinessIPListById(id);
    }

    @Override
    public List<AgentBusinessPO> getAgentBusinessIPListByIdMonth(String id, String month) {
        //获取agent_business_id
        EntityWrapper<AgentBusinessPO> entityIpWrapper = new EntityWrapper<>();
        entityIpWrapper.where("agent_business_ip_id={0}", id)
                .and("payment_month={0}", month);
        return selectList(entityIpWrapper);

    }
}