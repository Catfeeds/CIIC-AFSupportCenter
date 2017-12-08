package com.ciicsh.gto.afsupportcenter.healthmedical.queryservice.dao;

import com.ciicsh.gto.afsupportcenter.healthmedical.queryservice.entity.po.FragmentaryReimbursementPO;


/**
 * <p>
  * 零星报销表 Mapper 接口
 * </p>
 *
 * @author zhaogang
 * @since 2017-12-04
 */
public interface FragmentaryReimbursementMapper{

    void save(FragmentaryReimbursementPO fragmentaryReimbursement);

    void edit(FragmentaryReimbursementPO fragmentaryReimbursement);

}