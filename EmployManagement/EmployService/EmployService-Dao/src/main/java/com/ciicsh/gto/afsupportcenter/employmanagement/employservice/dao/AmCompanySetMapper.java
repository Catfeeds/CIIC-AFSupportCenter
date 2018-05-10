package com.ciicsh.gto.afsupportcenter.employmanagement.employservice.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;

import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.bo.AmCompanySetBO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.entity.AmCompanySet;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.api.dto.CompanyDTO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.api.dto.CompanyParamDTO;

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
