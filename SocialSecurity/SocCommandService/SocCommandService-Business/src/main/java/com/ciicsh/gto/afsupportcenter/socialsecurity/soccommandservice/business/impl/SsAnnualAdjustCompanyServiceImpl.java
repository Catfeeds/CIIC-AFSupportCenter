package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.business.impl;

import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.business.SsAnnualAdjustCompanyService;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dao.SsAnnualAdjustCompanyMapper;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dto.SsAnnualAdjustCompanyDTO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.SsAnnualAdjustCompany;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageKit;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  年调客户服务实现类
 * </p>
 */
@Service
public class SsAnnualAdjustCompanyServiceImpl extends ServiceImpl<SsAnnualAdjustCompanyMapper, SsAnnualAdjustCompany> implements SsAnnualAdjustCompanyService {

    @Override
    public PageRows<SsAnnualAdjustCompany> queryAnnualAdjustCompanyInPage(PageInfo pageInfo) {
        SsAnnualAdjustCompanyDTO ssAnnualAdjustCompanyDTO = pageInfo.toJavaObject(SsAnnualAdjustCompanyDTO.class);
        return PageKit.doSelectPage(pageInfo, () -> baseMapper.queryAnnualAdjustCompany(ssAnnualAdjustCompanyDTO));
    }

    @Override
    public List<SsAnnualAdjustCompany> queryAnnualAdjustCompany(SsAnnualAdjustCompanyDTO ssAnnualAdjustCompanyDTO) {
        return baseMapper.queryAnnualAdjustCompany(ssAnnualAdjustCompanyDTO);
    }
}
