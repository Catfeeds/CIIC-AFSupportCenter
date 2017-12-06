package com.ciicsh.gto.afsupportcenter.healthmedical.commandservice.business.impl;

import com.ciicsh.gto.afsupportcenter.healthmedical.commandservice.entity.po.MedicalRelationTransformPO;
import com.ciicsh.gto.afsupportcenter.healthmedical.commandservice.dao.MedicalRelationTransformMapper;
import com.ciicsh.gto.afsupportcenter.healthmedical.commandservice.business.MedicalRelationTransformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 医疗关系转移表 服务实现类
 * </p>
 *
 * @author zhaogang
 * @since 2017-12-04
 */
@Service
public class MedicalRelationTransformServiceImpl implements MedicalRelationTransformService {

    @Autowired
    private MedicalRelationTransformMapper medicalRelationTransformMapper;

    @Override
    public void save(MedicalRelationTransformPO medicalRelationTransform){

        medicalRelationTransformMapper.save(medicalRelationTransform);

    }
}
