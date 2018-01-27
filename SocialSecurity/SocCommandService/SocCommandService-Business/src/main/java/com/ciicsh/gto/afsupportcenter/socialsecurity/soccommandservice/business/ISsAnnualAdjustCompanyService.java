package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.business;

import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dto.SsAnnualAdjustCompanyDTO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.SsAnnualAdjustCompany;
import com.baomidou.mybatisplus.service.IService;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;

import java.util.List;

/**
 * <p>
 *  年调客户信息操作服务类
 * </p>
 */
public interface ISsAnnualAdjustCompanyService extends IService<SsAnnualAdjustCompany> {

    /**
     * 查询年调客户信息
     * @param ssAnnualAdjustCompanyDTO
     * @return
     */
    List<SsAnnualAdjustCompany> queryAnnualAdjustCompany(SsAnnualAdjustCompanyDTO ssAnnualAdjustCompanyDTO);

    /**
     * 分页查询年调客户信息
     * @param pageInfo
     * @return
     */
    PageRows<SsAnnualAdjustCompany> queryAnnualAdjustCompanyInPage(PageInfo pageInfo);
}
