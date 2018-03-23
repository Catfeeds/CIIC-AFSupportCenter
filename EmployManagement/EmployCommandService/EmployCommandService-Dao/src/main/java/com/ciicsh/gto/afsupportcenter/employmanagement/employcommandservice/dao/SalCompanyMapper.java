package com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.dao;

import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.bo.SalCompanyBO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.entity.SalCompany;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 客户基础表 Mapper 接口
 * </p>
 */
public interface SalCompanyMapper extends BaseMapper<SalCompany> {

    List<SalCompanyBO> querySalCompanyList(SalCompanyBO salCompanyBO);

}
