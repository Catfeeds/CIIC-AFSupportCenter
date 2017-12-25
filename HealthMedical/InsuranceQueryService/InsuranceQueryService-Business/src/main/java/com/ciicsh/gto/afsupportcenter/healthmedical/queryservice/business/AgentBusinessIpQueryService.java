package com.ciicsh.gto.afsupportcenter.healthmedical.queryservice.business;

import com.ciicsh.gto.afsupportcenter.healthmedical.queryservice.entity.po.AgentBusinessIpPO;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;

import java.util.List;

/**
 * <p>
 * 代收代付保单表（不提供维护界面，跑脚本录入） 服务类
 * </p>
 *
 * @author zhaogang
 * @since 2017-12-13
 */
public interface AgentBusinessIpQueryService {
    List<AgentBusinessIpPO> getList(AgentBusinessIpPO po);
}
