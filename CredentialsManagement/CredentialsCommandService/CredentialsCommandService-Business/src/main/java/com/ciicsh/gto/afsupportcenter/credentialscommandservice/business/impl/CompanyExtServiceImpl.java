package com.ciicsh.gto.afsupportcenter.credentialscommandservice.business.impl;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.business.CompanyExtService;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.dao.CompanyExtMapper;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.entity.po.CompanyExtPO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 客户数据维护（客户扩展表） 服务实现类
 * </p>
 *
 * @author guwei
 * @since 2018-01-15
 */
@Service
public class CompanyExtServiceImpl extends ServiceImpl<CompanyExtMapper, CompanyExtPO> implements CompanyExtService {

    @Override
    public List<CompanyExtPO> selectBycompanyId(String companyId) {
        return null;
    }

    @Override
    public boolean updateBycompanyIdAndType(String companyId, String credentialsType) {
        return false;
    }
}
