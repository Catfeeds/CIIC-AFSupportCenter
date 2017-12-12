package com.ciicsh.gto.afsupportcenter.healthmedical.commandservice.business.impl;

import com.ciicsh.gto.afsupportcenter.healthmedical.commandservice.business.FragmentaryReimbursementCommandService;
import com.ciicsh.gto.afsupportcenter.healthmedical.commandservice.entity.po.FragmentaryReimbursementPO;
import com.ciicsh.gto.afsupportcenter.healthmedical.commandservice.dao.FragmentaryReimbursementMapper;
import com.ciicsh.gto.afsupportcenter.healthmedical.commandservice.business.FragmentaryReimbursementCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * <p>
 * 零星报销表 服务实现类
 * </p>
 *
 * @author zhaogang
 * @since 2017-12-04
 */
@Service
@Transactional
public class FragmentaryReimbursementCommandServiceImpl implements FragmentaryReimbursementCommandService {

    @Autowired
    private FragmentaryReimbursementMapper fragmentaryReimbursementMapper;

    /**
     * 新增零星医疗报销
     * @param fragmentaryReimbursement 零星医疗报销
     * @param fragmentaryReimbursement
     */
    @Override
    public int save(FragmentaryReimbursementPO fragmentaryReimbursement)
    {
        Integer returnNum= fragmentaryReimbursementMapper.insert(fragmentaryReimbursement);
        return returnNum;

    }

    @Override
    public int edit(FragmentaryReimbursementPO fragmentaryReimbursement)
    {
        Integer returnNum= fragmentaryReimbursementMapper.updateById(fragmentaryReimbursement);
        return returnNum;

    }
}
