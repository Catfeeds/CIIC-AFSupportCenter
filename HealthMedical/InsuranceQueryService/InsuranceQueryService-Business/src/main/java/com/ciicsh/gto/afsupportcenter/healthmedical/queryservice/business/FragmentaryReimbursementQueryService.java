package com.ciicsh.gto.afsupportcenter.healthmedical.queryservice.business;

import com.ciicsh.gto.afsupportcenter.healthmedical.queryservice.entity.po.FragmentaryReimbursementPO;
import com.ciicsh.gto.afsupportcenter.healthmedical.queryservice.api.dto.FragmentaryReimbursementDTO;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;


/**
 * <p>
 * 零星报销表 服务类
 * </p>
 *
 * @author zhaogang
 * @since 2017-12-04
 */
public interface FragmentaryReimbursementQueryService {
    FragmentaryReimbursementPO getById(String id);
    PageRows<FragmentaryReimbursementPO> employeeOperatorQuery(PageInfo pageInfo);
}
