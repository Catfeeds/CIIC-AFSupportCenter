package com.ciicsh.gto.afsupportcenter.healthmedical.business.impl;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ciicsh.gto.afsupportcenter.healthmedical.business.SupplyMedicalInvoiceService;
import com.ciicsh.gto.afsupportcenter.healthmedical.dao.SupplyMedicalInvoiceMapper;
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.po.SupplyMedicalInvoice;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

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
    public void deleteByEntity(SupplyMedicalInvoice detailEntity) {
        EntityWrapper<SupplyMedicalInvoice> entityWrapper = new EntityWrapper<>();
        entityWrapper.where(StringUtils.isNotBlank(detailEntity.getAcceptanceId()),"acceptance_id={0}",detailEntity.getAcceptanceId());
        baseMapper.delete(entityWrapper);
    }
}
