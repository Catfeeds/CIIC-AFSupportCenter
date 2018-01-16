package com.ciicsh.gto.afsupportcenter.credentialscommandservice.business;


import com.baomidou.mybatisplus.service.IService;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.entity.po.CompanyExtPO;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 客户数据维护（客户扩展表） 服务类
 * </p>
 *
 * @author guwei
 * @since 2018-01-15
 */
public interface CompanyExtService extends IService<CompanyExtPO> {

    /**
     * 根据客户code查询客户证件办理信息列表
     * @param companyId
     * @return
     */
    List<CompanyExtPO> selectBycompanyId(String companyId);

    /**
     * 根据客户code和证件类型更新客户证件办理信息
     * @param companyId
     * @param credentialsType
     * @return
     */
    boolean updateBycompanyIdAndType(String companyId,String credentialsType);

}
