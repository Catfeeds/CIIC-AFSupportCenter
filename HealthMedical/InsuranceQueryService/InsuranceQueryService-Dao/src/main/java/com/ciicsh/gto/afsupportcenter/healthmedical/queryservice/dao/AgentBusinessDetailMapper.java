package com.ciicsh.gto.afsupportcenter.healthmedical.queryservice.dao;

import com.ciicsh.gto.afsupportcenter.healthmedical.queryservice.entity.po.AgentBusinessDetailPO;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
  * 代收代付发放明细表 Mapper 接口
 * </p>
 *
 * @author zhaogang
 * @since 2017-12-13
 */
public interface AgentBusinessDetailMapper extends BaseMapper<AgentBusinessDetailPO> {
    List<AgentBusinessDetailPO> getAgentBusinessDetailListById(@Param("id") String id);
}