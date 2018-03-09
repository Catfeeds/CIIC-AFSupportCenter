package com.ciicsh.gto.afsupportcenter.healthmedical.business.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ciicsh.gto.afsupportcenter.healthmedical.business.FragmentaryReimbursementQueryService;
import com.ciicsh.gto.afsupportcenter.healthmedical.dao.FragmentaryReimbursementMapper;
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.dto.FragmentaryReimbursementDTO;
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.po.FragmentaryReimbursementPO;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageKit;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
public class FragmentaryReimbursementQueryServiceImpl extends ServiceImpl<FragmentaryReimbursementMapper, FragmentaryReimbursementPO> implements FragmentaryReimbursementQueryService {

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
        Integer returnNum= fragmentaryReimbursementMapper.updateAllColumnById(fragmentaryReimbursement);
        return returnNum;

    }
    @Override
    public FragmentaryReimbursementPO getById(String id) {
        return fragmentaryReimbursementMapper.selectById(id);
    }

    /*

        public Page<SupplyMedicalAcceptance> queryAcceptancePage(Page<SupplyMedicalAcceptance> page, SupplyMedicalAcceptanceDTO supplyMedicalAcceptanceDTO) {
        page.setRecords(baseMapper.queryAcceptancePage(page, supplyMedicalAcceptanceDTO));
        return page;
    }
     */


    @Override
    public Page<FragmentaryReimbursementPO> getEntityList(Page<FragmentaryReimbursementPO> page, FragmentaryReimbursementDTO fragmentaryReimbursementDTO) {
           page.setRecords(baseMapper.fragmentaryReimbursementMapperQuery(page,fragmentaryReimbursementDTO));
           return page;

    }

}

