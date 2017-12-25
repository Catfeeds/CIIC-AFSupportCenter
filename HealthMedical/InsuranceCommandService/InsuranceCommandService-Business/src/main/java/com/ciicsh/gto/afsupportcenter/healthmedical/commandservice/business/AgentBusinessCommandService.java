package com.ciicsh.gto.afsupportcenter.healthmedical.commandservice.business;

import com.baomidou.mybatisplus.service.IService;
import com.ciicsh.gto.afsupportcenter.healthmedical.commandservice.entity.bo.AgentBusinessBO;
import com.ciicsh.gto.afsupportcenter.healthmedical.commandservice.entity.po.AgentBusinessPO;

import java.util.List;

/**
 * <p>
 * 代收代付发放表 服务类
 * </p>
 *
 * @author zhaogang
 * @since 2017-12-13
 */
public interface AgentBusinessCommandService extends IService<AgentBusinessPO> {
    List<AgentBusinessBO> getAgentBusinessIPListById(String agentbusinessipid);
    List<AgentBusinessPO> getAgentBusinessIPListByIdMonth(String id, String month);

}
