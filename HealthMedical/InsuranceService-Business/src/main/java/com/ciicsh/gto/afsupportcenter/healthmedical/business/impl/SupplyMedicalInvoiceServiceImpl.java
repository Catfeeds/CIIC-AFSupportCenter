package com.ciicsh.gto.afsupportcenter.healthmedical.business.impl;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ciicsh.gto.afsupportcenter.healthmedical.business.SupplyMedicalInvoiceService;
import com.ciicsh.gto.afsupportcenter.healthmedical.dao.SupplyMedicalInvoiceMapper;
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.bo.EmployeeBO;
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.dto.SupplyMedicalAcceptanceDTO;
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.po.SupplyMedicalInvoice;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 补充医疗受理单相关发票表 服务实现类
 * </p>
 *
 * @author xiweizhen
 */
@Service
public class SupplyMedicalInvoiceServiceImpl extends ServiceImpl<SupplyMedicalInvoiceMapper, SupplyMedicalInvoice> implements SupplyMedicalInvoiceService {

    @Override
    public void deleteByAcceptanceId(String acceptanceId) {
        EntityWrapper<SupplyMedicalInvoice> entityWrapper = new EntityWrapper<>();
        entityWrapper.where(StringUtils.isNotBlank(acceptanceId), "acceptance_id={0}", acceptanceId);
        baseMapper.delete(entityWrapper);
    }

    @Override
    public List<SupplyMedicalInvoice> queryMedicalInvoiceList(String id) {
        EntityWrapper<SupplyMedicalInvoice> entityWrapper = new EntityWrapper<>();
        entityWrapper.where(StringUtils.isNotBlank(id), "acceptance_id={0}", id);
        return baseMapper.selectList(entityWrapper);
    }

    @Override
    public EmployeeBO queryEmployeeInfo(String employeeId) {
        return baseMapper.queryEmployeeInfo(employeeId);
    }

    @Override
    public Integer queryInvoiceCount(SupplyMedicalAcceptanceDTO supplyMedicalAcceptanceDTO) {
        return baseMapper.queryInvoiceCount(supplyMedicalAcceptanceDTO);
    }

}
