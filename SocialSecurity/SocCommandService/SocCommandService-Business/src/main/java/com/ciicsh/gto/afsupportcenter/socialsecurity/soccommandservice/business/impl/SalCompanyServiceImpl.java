package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.business.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.business.SalCompanyService;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dao.SalCompanyMapper;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.SalCompany;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageKit;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 客户基础表 服务实现类
 * </p>
 *
 * @author HuangXing
 * @since 2017-12-11
 */
@Service
public class SalCompanyServiceImpl extends ServiceImpl<SalCompanyMapper, SalCompany> implements SalCompanyService {

    @Override
    public PageRows<SalCompany> companyQuery(PageInfo pageInfo) {
        SalCompany dto = pageInfo.toJavaObject(SalCompany.class);
        return PageKit.doSelectPage(pageInfo, () -> baseMapper.companyQuery(dto));
    }
}
