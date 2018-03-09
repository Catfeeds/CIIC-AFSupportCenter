package com.ciicsh.gto.afsupportcenter.healthmedical.business;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.dto.FragmentaryReimbursementDTO;
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.po.FragmentaryReimbursementPO;


/**
 * <p>
 * 零星报销表 服务类
 * </p>
 *
 * @author zhaogang
 * @since 2017-12-04
 */
public interface FragmentaryReimbursementQueryService extends IService<FragmentaryReimbursementPO> {
    int save(FragmentaryReimbursementPO fragmentaryReimbursement);

    int edit(FragmentaryReimbursementPO fragmentaryReimbursement);

    FragmentaryReimbursementPO getById(String id);

    Page<FragmentaryReimbursementPO> getEntityList(Page<FragmentaryReimbursementPO> page, FragmentaryReimbursementDTO dto);
}
