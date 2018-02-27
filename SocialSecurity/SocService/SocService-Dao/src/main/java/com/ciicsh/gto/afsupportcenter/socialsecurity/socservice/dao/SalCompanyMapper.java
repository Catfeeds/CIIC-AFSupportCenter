package com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.SalCompany;

import java.util.List;

/**
 * <p>
 * 客户基础表 Mapper 接口
 * </p>
 *
 * @author HuangXing
 * @since 2017-12-11
 */
public interface SalCompanyMapper extends BaseMapper<SalCompany> {

    /**
     * 查询客户基础信息
     *
     * @param dto
     * @return
     */
    List<SalCompany> companyQuery(SalCompany dto);
}
