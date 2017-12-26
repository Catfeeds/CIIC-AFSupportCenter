package com.ciicsh.gto.afsupportcenter.healthmedical.queryservice.dao;

import com.ciicsh.gto.afsupportcenter.healthmedical.queryservice.entity.po.InsuranceCompanyPO;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
  * 保险公司表 Mapper 接口
 * </p>
 *
 * @author zhaogang
 * @since 2017-12-13
 */
public interface InsuranceCompanyMapper extends BaseMapper<InsuranceCompanyPO> {

       List<InsuranceCompanyPO> getList();

}