package com.ciicsh.gto.afsupportcenter.credentialscommandservice.business.impl;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.business.OrgPolicyService;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.dao.OrgPolicyMapper;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.entity.po.OrgPolicy;
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
public class OrgPolicyServiceImpl extends ServiceImpl<OrgPolicyMapper, OrgPolicy> implements OrgPolicyService {

    @Autowired
    private OrgPolicyMapper orgPolicyMapper;

    @Override
    public List<OrgPolicy> select(OrgPolicy orgPolicy) {
        return orgPolicyMapper.select(orgPolicy);
    }

    @Override
    public boolean deleteById(Integer id) {
        int row = orgPolicyMapper.deleteByOrgPolicyId(id);
        return row == 1?true : false;
    }
}
