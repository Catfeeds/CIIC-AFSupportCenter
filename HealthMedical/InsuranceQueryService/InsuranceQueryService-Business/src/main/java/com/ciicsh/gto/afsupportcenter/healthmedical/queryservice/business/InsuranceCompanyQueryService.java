package com.ciicsh.gto.afsupportcenter.healthmedical.queryservice.business;

import com.ciicsh.gto.afsupportcenter.healthmedical.queryservice.entity.po.InsuranceCompanyPO;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 * 保险公司表 服务类
 * </p>
 *
 * @author zhaogang
 * @since 2017-12-13
 */
public interface InsuranceCompanyQueryService {

    InsuranceCompanyPO getEntityByID(String id);

    List<InsuranceCompanyPO> getAll();

}
