package com.ciicsh.gto.afsupportcenter.healthmedical.queryservice.dao;

import com.ciicsh.gto.afsupportcenter.healthmedical.queryservice.entity.po.AcceptancePO;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
  * 补充医疗理赔表 Mapper 接口
 * </p>
 *
 * @author zhaogang
 * @since 2017-12-04
 */
@Repository
public interface AcceptanceMapper {
    int countAll();
}