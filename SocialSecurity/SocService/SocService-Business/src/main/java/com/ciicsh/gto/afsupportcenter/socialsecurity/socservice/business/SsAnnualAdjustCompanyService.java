package com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business;

import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.dto.SsAnnualAdjustCompanyDTO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.SsAnnualAdjustCompany;
import com.baomidou.mybatisplus.service.IService;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;

import java.util.List;

/**
 * <p>
 *  年调客户信息操作服务类
 * </p>
 */
public interface SsAnnualAdjustCompanyService extends IService<SsAnnualAdjustCompany> {

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

    /**
     * 根据社保账户ID更新年调客户信息的社保账户单位平均工资相关部分
     * @param ssAnnualAdjustCompany
     */
    void updateAnnualAdjustCompanysByComAccountId(SsAnnualAdjustCompany ssAnnualAdjustCompany);
}
