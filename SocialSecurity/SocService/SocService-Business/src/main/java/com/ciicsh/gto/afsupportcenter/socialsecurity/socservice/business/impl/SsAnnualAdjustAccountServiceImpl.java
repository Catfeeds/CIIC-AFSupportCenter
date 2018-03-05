package com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business.impl;

import com.baomidou.mybatisplus.toolkit.CollectionUtils;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo.SsAnnualAdjustAccountBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.dto.SsAnnualAdjustAccountDTO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.SsAnnualAdjustAccount;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.dao.SsAnnualAdjustAccountMapper;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business.SsAnnualAdjustAccountService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageKit;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  年调社保账户服务实现类
 * </p>
 */
@Service
public class SsAnnualAdjustAccountServiceImpl extends ServiceImpl<SsAnnualAdjustAccountMapper, SsAnnualAdjustAccount> implements SsAnnualAdjustAccountService {

    @Override
    public List<SsAnnualAdjustAccount> queryAnnualAdjustAccount(SsAnnualAdjustAccountDTO ssAnnualAdjustAccountDTO) {
        return baseMapper.queryAnnualAdjustAccount(ssAnnualAdjustAccountDTO);
    }

    @Override
    public PageRows<SsAnnualAdjustAccount> queryAnnualAdjustAccountInPage(PageInfo pageInfo) {
        SsAnnualAdjustAccountDTO ssAnnualAdjustAccountDTO = pageInfo.toJavaObject(SsAnnualAdjustAccountDTO.class);
        PageRows<SsAnnualAdjustAccount> pageRows = PageKit.doSelectPage(pageInfo, () -> baseMapper.queryAnnualAdjustAccount(ssAnnualAdjustAccountDTO));

        for(SsAnnualAdjustAccount ssAnnualAdjustAccount : pageRows.getRows()) {
            String ssImportTotal = "0 / 0";
            ssAnnualAdjustAccount.setAfImportTotal(this.getCountByComAccountId(ssAnnualAdjustAccount.getComAccountId()));
            List<SsAnnualAdjustAccountBO> ssImportTotalList = this.getCountOfAccountStatus(ssAnnualAdjustAccount.getAnnualAdjustAccountId());
            if (CollectionUtils.isNotEmpty(ssImportTotalList)) {
                for (SsAnnualAdjustAccountBO ssAnnualAdjustAccountBO : ssImportTotalList) {
                    if (ssAnnualAdjustAccountBO.getAccountStatus() == 1) { //TODO Constants
                        ssImportTotal = ssImportTotal.replaceFirst("0", String.valueOf(ssAnnualAdjustAccountBO.getCnt()));
                    } else if (ssAnnualAdjustAccountBO.getAccountStatus() == 2) { //TODO Constants
                        ssImportTotal = ssImportTotal.substring(0, ssImportTotal.length() - 1) + String.valueOf(ssAnnualAdjustAccountBO.getCnt());
                    }
                }
                ssAnnualAdjustAccount.setSsImportTotal(ssImportTotal);
            }

            ssAnnualAdjustAccount.setMatchTotal(0);
            ssAnnualAdjustAccount.setUnMatchTotal(0);
            List<SsAnnualAdjustAccountBO> matchStatusList =this.getCountOfMatchStatus(ssAnnualAdjustAccount.getAnnualAdjustAccountId());
            if (CollectionUtils.isNotEmpty(matchStatusList)) {
                for (SsAnnualAdjustAccountBO ssAnnualAdjustAccountBO : matchStatusList) {
                    if (ssAnnualAdjustAccountBO.getMatchStatus() == 0) { //TODO Constants
                        ssAnnualAdjustAccount.setUnMatchTotal(ssAnnualAdjustAccountBO.getCnt());
                    } else if (ssAnnualAdjustAccountBO.getMatchStatus() == 1) { //TODO Constants
                        ssAnnualAdjustAccount.setMatchTotal(ssAnnualAdjustAccountBO.getCnt());
                    }
                }
            }
        }
        return pageRows;
    }

    @Override
    public List<SsAnnualAdjustAccountBO> getCountOfAccountStatus(Long annualAdjustAccountId) {
        return baseMapper.getCountOfAccountStatus(annualAdjustAccountId);
    }

    @Override
    public List<SsAnnualAdjustAccountBO> getCountOfMatchStatus(Long annualAdjustAccountId) {
        return baseMapper.getCountOfMatchStatus(annualAdjustAccountId);
    }

    @Override
    public int getCountByComAccountId(Long comAccountId) {
        return baseMapper.getCountByComAccountId(comAccountId);
    }

    @Override
    public List<SsAnnualAdjustAccountBO> getUnitAvgMonthSalaryByAnnualAdjustAccountId(Long annualAdjustAccountId) {
        return baseMapper.getUnitAvgMonthSalaryByAnnualAdjustAccountId(annualAdjustAccountId);
    }
}
