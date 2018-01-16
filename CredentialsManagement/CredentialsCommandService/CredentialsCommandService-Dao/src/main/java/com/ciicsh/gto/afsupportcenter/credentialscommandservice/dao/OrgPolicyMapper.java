package com.ciicsh.gto.afsupportcenter.credentialscommandservice.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.entity.po.OrgPolicyPO;

import java.util.List;

/**
 * <p>
  * 办理机构政策 Mapper 接口
 * </p>
 *
 * @author guwei
 * @since 2018-01-15
 */
public interface OrgPolicyMapper extends BaseMapper<OrgPolicyPO> {

    /**
     * 查询列表
     * @param orgPolicyPO
     * @return
     */
    List<OrgPolicyPO> select(OrgPolicyPO orgPolicyPO);

    /**
     * 根据办理机构查询政策信息
     * @param orgPolicyPO
     * @return
     */
    OrgPolicyPO selectItem(OrgPolicyPO orgPolicyPO);
}