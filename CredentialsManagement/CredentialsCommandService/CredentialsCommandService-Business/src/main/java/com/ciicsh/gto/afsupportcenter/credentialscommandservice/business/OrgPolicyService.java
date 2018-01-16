package com.ciicsh.gto.afsupportcenter.credentialscommandservice.business;


import com.baomidou.mybatisplus.service.IService;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.entity.po.OrgPolicyPO;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 办理机构政策 服务类
 * </p>
 *
 * @author guwei
 * @since 2018-01-15
 */
public interface OrgPolicyService extends IService<OrgPolicyPO> {

    /**
     * 条件查询办理机构政策列表
     * @param orgPolicyPO
     * @return
     */
    List<OrgPolicyPO> select(OrgPolicyPO orgPolicyPO);

    /**
     * 条件查询办理机构政策
     * @param orgPolicyPO
     * @return
     */
    OrgPolicyPO selectItem(OrgPolicyPO orgPolicyPO);

    /**
     * 插入或更新办理机构政策
     * @param entity
     * @return
     */
    @Override
    boolean insertOrUpdate(OrgPolicyPO entity);

    /**
     * 删除办理机构政策
     * @param id
     * @return
     */
    @Override
    boolean deleteById(Serializable id);
}
