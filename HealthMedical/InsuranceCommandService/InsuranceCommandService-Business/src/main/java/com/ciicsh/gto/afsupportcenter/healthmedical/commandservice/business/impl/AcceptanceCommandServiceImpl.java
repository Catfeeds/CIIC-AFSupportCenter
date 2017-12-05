package com.ciicsh.gto.afsupportcenter.healthmedical.commandservice.business.impl;

import com.ciicsh.gto.afsupportcenter.healthmedical.commandservice.entity.po.AcceptancePO;
import com.ciicsh.gto.afsupportcenter.healthmedical.commandservice.dao.AcceptanceMapper;
import com.ciicsh.gto.afsupportcenter.healthmedical.commandservice.business.AcceptanceCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * <p>
 * 补充医疗理赔表 服务实现类
 * </p>
 *
 * @author zhaogang
 * @since 2017-12-04
 */
@Service
public class AcceptanceCommandServiceImpl implements AcceptanceCommandService {

    @Autowired
    private AcceptanceMapper AcceptanceMapper;

    @Override
    public int countAll() {
        return AcceptanceMapper.countAll();
    }
}
