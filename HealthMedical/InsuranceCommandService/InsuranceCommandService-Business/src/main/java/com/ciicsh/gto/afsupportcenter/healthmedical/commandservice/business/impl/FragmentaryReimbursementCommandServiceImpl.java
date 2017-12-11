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
    public void save(FragmentaryReimbursementPO fragmentaryReimbursement)
    {
     //   fragmentaryReimbursement.setEmployeeId("17er");
     //   BigDecimal caseMoney = new BigDecimal("15000.48");
     //   fragmentaryReimbursement.setCaseMoney(caseMoney) ;
     //   fragmentaryReimbursement.setInvoiceNumber(2135);
        fragmentaryReimbursementMapper.save(fragmentaryReimbursement);

    }

    @Override
    public void edit(FragmentaryReimbursementPO fragmentaryReimbursement)
    {
     //   fragmentaryReimbursement.setId(10);
     //   fragmentaryReimbursement.setEmployeeId("17er");
     //   BigDecimal caseMoney = new BigDecimal("15000.48");
   //     fragmentaryReimbursement.setCaseMoney(caseMoney) ;
     //   fragmentaryReimbursement.setInvoiceNumber(5);
    //    fragmentaryReimbursement.setMedicalRemark("123wqe");
        fragmentaryReimbursementMapper.edit(fragmentaryReimbursement) ;

    }
}
