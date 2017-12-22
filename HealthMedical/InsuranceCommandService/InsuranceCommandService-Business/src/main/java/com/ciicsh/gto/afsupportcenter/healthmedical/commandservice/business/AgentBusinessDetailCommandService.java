package com.ciicsh.gto.afsupportcenter.healthmedical.commandservice.business;

import com.baomidou.mybatisplus.service.IService;
import com.ciicsh.gto.afsupportcenter.healthmedical.commandservice.entity.po.AgentBusinessDetailPO;

import java.util.List;

/**
 * <p>
 * 代收代付发放明细表 服务类
 * </p>
 *
 * @author zhaogang
 * @since 2017-12-13
 */
public interface AgentBusinessDetailCommandService  extends IService<AgentBusinessDetailPO> {
    List<AgentBusinessDetailPO> getAgentBusinessDetailListByIpId(String agentbusinessipid, String month);
}
