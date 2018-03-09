package com.ciicsh.gto.afsupportcenter.healthmedical.business;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.po.MedicalRelationTransformPO;

/**
 * <p>
 * 医疗关系转移表 服务类
 * </p>
 *
 * @author zhaogang
 * @since 2017-12-04
 */
public interface MedicalRelationTransformQueryService extends IService<MedicalRelationTransformPO> {
    int save(MedicalRelationTransformPO medicalRelationTransform);

    int edit(MedicalRelationTransformPO medicalRelationTransform);

    MedicalRelationTransformPO getById(String id);

    Page<MedicalRelationTransformPO> medicalRelationTransformMapperQuery(Page<MedicalRelationTransformPO> page, MedicalRelationTransformPO dto);

}
