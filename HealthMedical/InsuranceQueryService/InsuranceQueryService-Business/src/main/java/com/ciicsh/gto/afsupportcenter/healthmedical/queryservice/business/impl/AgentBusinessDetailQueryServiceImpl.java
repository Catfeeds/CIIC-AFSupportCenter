package com.ciicsh.gto.afsupportcenter.healthmedical.queryservice.business.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.ciicsh.gto.afsupportcenter.healthmedical.queryservice.entity.po.AgentBusinessDetailPO;
import com.ciicsh.gto.afsupportcenter.healthmedical.queryservice.entity.po.AgentBusinessPO;
import com.ciicsh.gto.afsupportcenter.healthmedical.queryservice.dao.AgentBusinessDetailMapper;
import com.ciicsh.gto.afsupportcenter.healthmedical.queryservice.business.impl.AgentBusinessQueryServiceImpl;
import com.ciicsh.gto.afsupportcenter.healthmedical.queryservice.business.AgentBusinessDetailQueryService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
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
public class AgentBusinessDetailQueryServiceImpl extends ServiceImpl<AgentBusinessDetailMapper, AgentBusinessDetailPO> implements AgentBusinessDetailQueryService {

    @Autowired
    private AgentBusinessQueryServiceImpl agentBusinessQueryServiceImpl;

    @Override
    public List<AgentBusinessDetailPO> getAgentBusinessDetailListByIpId(String agentbusinessipid, String month) {
        //获取agent_business_id

        List<AgentBusinessPO> iplist = agentBusinessQueryServiceImpl.getAgentBusinessIPListByIdMonth(agentbusinessipid, month);
        AgentBusinessPO po = iplist.get(0);

        if (po != null) {
            // 依据agent_business_id 返回AgentBusinessDetail 列表
            EntityWrapper<AgentBusinessDetailPO> entityWrapper = new EntityWrapper<>();
            entityWrapper.where("agent_business_id={0}", po.getAgentBusinessId());
            return selectList(entityWrapper);
        }
        return Collections.emptyList();
    }
}
