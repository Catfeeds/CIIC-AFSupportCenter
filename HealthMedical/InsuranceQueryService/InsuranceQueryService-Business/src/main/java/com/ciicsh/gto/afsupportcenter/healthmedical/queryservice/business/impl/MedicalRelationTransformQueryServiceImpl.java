package com.ciicsh.gto.afsupportcenter.healthmedical.queryservice.business.impl;

import com.ciicsh.gto.afsupportcenter.healthmedical.queryservice.entity.po.MedicalRelationTransformPO;
import com.ciicsh.gto.afsupportcenter.healthmedical.queryservice.dao.MedicalRelationTransformMapper;
import com.ciicsh.gto.afsupportcenter.healthmedical.queryservice.business.MedicalRelationTransformQueryService;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageKit;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 * 医疗关系转移表 服务实现类
 * </p>
 *
 * @author zhaogang
 * @since 2017-12-04
 */
@Service
@Transactional
public class MedicalRelationTransformQueryServiceImpl extends ServiceImpl<MedicalRelationTransformMapper, MedicalRelationTransformPO> implements MedicalRelationTransformQueryService {

    @Autowired
    private MedicalRelationTransformMapper medicalRelationTransformMapper;

    @Override
    public MedicalRelationTransformPO getById(String id) {
        return medicalRelationTransformMapper.getById(id);
    }

    @Override
    public PageRows<MedicalRelationTransformPO> medicalRelationTransformMapperQuery(PageInfo pageInfo) {
        MedicalRelationTransformPO po = pageInfo.toJavaObject(MedicalRelationTransformPO.class);
        PageRows<MedicalRelationTransformPO> pageRow = PageKit.doSelectPage(pageInfo, () -> baseMapper.medicalRelationTransformMapperQuery(po) );
        return pageRow;
    }

}