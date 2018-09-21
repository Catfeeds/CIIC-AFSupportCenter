package com.ciicsh.gto.afsupportcenter.employmanagement.employservice.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.bo.SalCompanyBO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.custom.IndependentExportOpt;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.entity.SalCompany;

import java.util.List;

/**
 * <p>
 * 客户基础表 Mapper 接口
 * </p>
 */
public interface SalCompanyMapper extends BaseMapper<SalCompany> {

    List<SalCompanyBO> querySalCompanyList(SalCompanyBO salCompanyBO);

    List<SalCompanyBO> taskCount(SalCompanyBO salCompanyBO);

    List<IndependentExportOpt> querySalOptList(SalCompanyBO salCompanyBO);

}
