package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.business;

import com.baomidou.mybatisplus.service.IService;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.SalCompany;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;

/**
 * <p>
 * 客户基础表 服务类
 * </p>
 *
 * @author HuangXing
 * @since 2017-12-11
 */
public interface SalCompanyService extends IService<SalCompany> {

    /**
     * 查询客户基础信息
     *
     * @param pageInfo
     * @return
     */
    PageRows<SalCompany> companyQuery(PageInfo pageInfo);
}
