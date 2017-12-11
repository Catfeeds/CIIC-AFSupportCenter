package com.ciicsh.gto.afsupportcenter.healthmedical.queryservice.business;

import com.ciicsh.gto.afsupportcenter.healthmedical.queryservice.entity.po.MedicalRelationTransformPO;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
import java.util.List;


/**
 * <p>
 * 医疗关系转移表 服务类
 * </p>
 *
 * @author zhaogang
 * @since 2017-12-04
 */
public interface MedicalRelationTransformQueryService {

    MedicalRelationTransformPO getById(String id);
    PageRows<MedicalRelationTransformPO> medicalRelationTransformMapperQuery(PageInfo po);

}
