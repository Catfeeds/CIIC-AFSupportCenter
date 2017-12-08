package com.ciicsh.gto.afsupportcenter.healthmedical.queryservice.business;

import com.ciicsh.gto.afsupportcenter.healthmedical.queryservice.entity.po.FragmentaryReimbursementPO;


/**
 * <p>
 * 零星报销表 服务类
 * </p>
 *
 * @author zhaogang
 * @since 2017-12-04
 */
public interface FragmentaryReimbursementQueryService {
    void save(FragmentaryReimbursementPO fragmentaryReimbursement);
    void edit(FragmentaryReimbursementPO fragmentaryReimbursement);
}
