package com.ciicsh.gto.afsupportcenter.credentialscommandservice.business;


import com.baomidou.mybatisplus.service.IService;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.entity.po.CompanyExt;

import java.util.List;

/**
 * <p>
 * 客户数据维护（客户扩展表） 服务类
 * </p>
 *
 * @author guwei
 * @since 2018-01-15
 */
public interface CompanyExtService extends IService<CompanyExt> {

    /**
     * 根据客户code查询客户证件办理信息列表
     * @param companyId
     * @return
     */
    List<CompanyExt> selectBycompanyId(String companyId);

    /**
     * 根据客户code和证件类型查询办证信息
     * @param companyId
     * @param credentialsType
     * @return
     */
    CompanyExt selectItem(String companyId, String credentialsType);

    /**
     * 根据客户code和证件类型保存或更新客户证件办理信息
     * @param companyExt
     * @return
     */
    @Override
    boolean insertOrUpdate(CompanyExt companyExt);

}
