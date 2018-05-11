package com.ciicsh.gto.afsupportcenter.employmanagement.employservice.business;

import com.baomidou.mybatisplus.service.IService;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.api.dto.CompanyDTO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.api.dto.CompanyParamDTO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.bo.AmCompanySetBO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.entity.AmCompanySet;

/**
 * <p>
 * 公司特殊情况设置表 服务类
 * </p>
 *
 * @author xsj
 * @since 2018-03-21
 */
public interface IAmCompanySetService extends IService<AmCompanySet> {

    AmCompanySetBO queryAmCompanySet(AmCompanySetBO amCompanySetBO);

    CompanyDTO  queryCompanyDTO(CompanyParamDTO companyParamDTO);

    boolean saveCompanyDTO(CompanyDTO companyDTO);
}
