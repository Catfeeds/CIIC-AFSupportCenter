package com.ciicsh.gto.afsupportcenter.healthmedical.business;

import com.ciicsh.gto.afsupportcenter.healthmedical.entity.po.MedicalRelationTransformPO;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;

/**
 * <p>
 * 医疗关系转移表 服务类
 * </p>
 *
 * @author zhaogang
 * @since 2017-12-04
 */
public interface MedicalRelationTransformQueryService {
    int save(MedicalRelationTransformPO medicalRelationTransform);
    int edit(MedicalRelationTransformPO medicalRelationTransform);
    MedicalRelationTransformPO getById(String id);
    PageRows<MedicalRelationTransformPO> medicalRelationTransformMapperQuery(PageInfo po);

}
