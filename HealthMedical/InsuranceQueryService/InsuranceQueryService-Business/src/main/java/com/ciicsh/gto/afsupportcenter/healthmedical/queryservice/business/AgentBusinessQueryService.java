package com.ciicsh.gto.afsupportcenter.healthmedical.queryservice.business;

import com.ciicsh.gto.afsupportcenter.healthmedical.queryservice.entity.po.AgentBusinessPO;
import com.ciicsh.gto.afsupportcenter.healthmedical.queryservice.entity.bo.AgentBusinessBO;
import com.baomidou.mybatisplus.service.IService;
import com.ciicsh.gto.afsupportcenter.util.core.Result;

import java.util.List;

/**
 * <p>
 * 代收代付发放表 服务类
 * </p>
 *
 * @author zhaogang
 * @since 2017-12-13
 */
public interface AgentBusinessQueryService extends IService<AgentBusinessPO> {
    List<AgentBusinessBO> getAgentBusinessIPListById(String agentbusinessipid);
    List<AgentBusinessPO> getAgentBusinessIPListByIdMonth(String id,String month);

}
