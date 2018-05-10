package com.ciicsh.gto.afsupportcenter.credentialscommandservice.business.impl;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.business.OrgPolicyService;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.dao.CompanyExtMapper;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.dao.OrgPolicyMapper;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.entity.po.CompanyExt;
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
    @Autowired
    private CompanyExtMapper companyExtMapper;

    @Override
    public List<OrgPolicy> select(Page page, OrgPolicy orgPolicy) {
        return orgPolicyMapper.select(page, orgPolicy);
    }

    @Override
    public int deleteById(Integer id) {
        List<CompanyExt> companyExts = companyExtMapper.selecyByOrgpolicyId(id);
        if (companyExts != null && companyExts.size()>0) {
            return 1;
        }else {
            return orgPolicyMapper.deleteByOrgPolicyId(id) == 1 ? 0 :2;
        }
    }

    @Override
    public List<OrgPolicy> getByType(Integer type) {
        Wrapper<OrgPolicy> wr = new EntityWrapper<OrgPolicy>()
            .eq("type", type)
            .eq("is_active",1);
        return orgPolicyMapper.selectList(wr);
    }
}
