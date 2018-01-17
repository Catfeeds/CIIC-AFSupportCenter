package com.ciicsh.gto.afsupportcenter.credentialscommandservice.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.entity.po.OrgPolicy;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
  * 办理机构政策 Mapper 接口
 * </p>
 *
 * @author guwei
 * @since 2018-01-15
 */
@Repository
public interface OrgPolicyMapper extends BaseMapper<OrgPolicy> {

    /**
     * 查询列表
     * @param orgPolicy
     * @return
     */
    List<OrgPolicy> select(OrgPolicy orgPolicy);

    /**
     * 根据办理机构查询政策信息
     * @param orgPolicy
     * @return
     */
    OrgPolicy selectItem(OrgPolicy orgPolicy);
}