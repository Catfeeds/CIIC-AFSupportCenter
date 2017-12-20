package com.ciicsh.gto.afsupportcenter.healthmedical.queryservice.business;

import com.ciicsh.gto.afsupportcenter.healthmedical.queryservice.entity.po.AgentBusinessDetailPO;
import com.baomidou.mybatisplus.service.IService;
import com.ciicsh.gto.afsupportcenter.util.core.PageParam;
import com.ciicsh.gto.afsupportcenter.util.core.Result;

import java.util.List;

/**
 * <p>
 * 代收代付发放明细表 服务类
 * </p>
 *
 * @author zhaogang
 * @since 2017-12-13
 */
public interface AgentBusinessDetailQueryService  extends IService<AgentBusinessDetailPO> {
    List<AgentBusinessDetailPO> getAgentBusinessDetailListByIpId(String agentbusinessipid,String month);
}
