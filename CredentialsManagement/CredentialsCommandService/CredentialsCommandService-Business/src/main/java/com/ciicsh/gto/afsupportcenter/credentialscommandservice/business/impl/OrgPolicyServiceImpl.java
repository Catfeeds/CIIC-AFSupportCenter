package com.ciicsh.gto.afsupportcenter.credentialscommandservice.business.impl;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.business.OrgPolicyService;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.dao.OrgPolicyMapper;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.entity.po.OrgPolicyPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 办理机构政策 服务实现类
 * </p>
 *
 * @author guwei
 * @since 2018-01-15
 */
@Service
public class OrgPolicyServiceImpl extends ServiceImpl<OrgPolicyMapper, OrgPolicyPO> implements OrgPolicyService {

    @Autowired
    private OrgPolicyMapper orgPolicyMapper;

    @Override
    public List<OrgPolicyPO> select(OrgPolicyPO orgPolicyPO) {
        return orgPolicyMapper.select(orgPolicyPO);
    }

    @Override
    public OrgPolicyPO selectItem(OrgPolicyPO orgPolicyPO) {
        return orgPolicyMapper.selectItem(orgPolicyPO);
    }
}
