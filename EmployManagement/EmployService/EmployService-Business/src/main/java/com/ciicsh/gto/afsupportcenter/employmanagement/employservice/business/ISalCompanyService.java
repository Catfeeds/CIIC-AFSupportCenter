package com.ciicsh.gto.afsupportcenter.employmanagement.employservice.business;

import com.baomidou.mybatisplus.service.IService;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.bo.SalCompanyBO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.entity.SalCompany;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;

import java.util.List;

/**
 * <p>
 * 客户基础表 服务类
 * </p>
 */

public interface ISalCompanyService extends IService<SalCompany> {

    PageRows<SalCompanyBO> querySalCompanyList(PageInfo pageInfo);

    List<SalCompanyBO> getSalCompanyList(SalCompanyBO salCompanyBO);

}
