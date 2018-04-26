package com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.dao;

import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.api.dto.CompanyDTO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.api.dto.CompanyParamDTO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.bo.AmCompanySetBO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.entity.AmCompanySet;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 公司特殊情况设置表 Mapper 接口
 * </p>
 *
 * @author xsj
 * @since 2018-03-21
 */
public interface AmCompanySetMapper extends BaseMapper<AmCompanySet> {

    List<AmCompanySetBO> queryAmCompanySet(AmCompanySetBO amCompanySetBO);

    List<CompanyDTO>  queryCompanyDTO(CompanyParamDTO companyParamDTO);

}
