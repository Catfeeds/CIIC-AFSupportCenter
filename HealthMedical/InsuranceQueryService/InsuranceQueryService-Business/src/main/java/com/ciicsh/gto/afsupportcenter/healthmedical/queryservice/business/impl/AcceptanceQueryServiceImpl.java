package com.ciicsh.gto.afsupportcenter.healthmedical.queryservice.business.impl;

import com.ciicsh.gto.afsupportcenter.healthmedical.queryservice.dao.AcceptanceMapper;
import com.ciicsh.gto.afsupportcenter.healthmedical.queryservice.business.AcceptanceQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * <p>
 * 补充医疗理赔表 服务实现类
 * </p>
 *
 * @author zhaogang
 * @since 2017-12-04
 */
@Service
public class AcceptanceQueryServiceImpl implements AcceptanceQueryService {

    @Autowired
    private AcceptanceMapper AcceptanceMapper;

    @Override
    public int countAll() {
        return AcceptanceMapper.countAll();
    }
}
