package com.ciicsh.gto.afsupportcenter.healthmedical.queryservice.dao;

import com.ciicsh.gto.afsupportcenter.healthmedical.queryservice.entity.po.AgentBusinessPO;
import com.ciicsh.gto.afsupportcenter.healthmedical.queryservice.entity.bo.AgentBusinessBO;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
/**
 * <p>
  * 代收代付发放表 Mapper 接口
 * </p>
 *
 * @author zhaogang
 * @since 2017-12-13
 */
public interface AgentBusinessMapper extends BaseMapper<AgentBusinessPO> {

    List<AgentBusinessBO> getAgentBusinessIPListById(@Param("id") String id);


}