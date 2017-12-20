package com.ciicsh.gto.afsupportcenter.healthmedical.queryservice.dao;

import com.ciicsh.gto.afsupportcenter.healthmedical.queryservice.entity.po.AgentBusinessIpPO;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
  * 代收代付保单表（不提供维护界面，跑脚本录入） Mapper 接口
 * </p>
 *
 * @author zhaogang
 * @since 2017-12-13
 */
public interface AgentBusinessIpMapper extends BaseMapper<AgentBusinessIpPO> {
    List<AgentBusinessIpPO> getList(AgentBusinessIpPO po);
}