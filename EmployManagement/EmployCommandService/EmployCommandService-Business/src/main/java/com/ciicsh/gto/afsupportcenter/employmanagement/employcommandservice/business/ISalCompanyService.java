package com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.business;

import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.bo.SalCompanyBO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.entity.SalCompany;
import com.baomidou.mybatisplus.service.IService;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
import org.springframework.stereotype.Service;

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
