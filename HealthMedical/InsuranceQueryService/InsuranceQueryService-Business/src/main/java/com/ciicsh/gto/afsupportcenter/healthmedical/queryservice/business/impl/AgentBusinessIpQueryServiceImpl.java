package com.ciicsh.gto.afsupportcenter.healthmedical.queryservice.business.impl;

import com.ciicsh.gto.afsupportcenter.healthmedical.queryservice.entity.po.AgentBusinessIpPO;
import com.ciicsh.gto.afsupportcenter.healthmedical.queryservice.dao.AgentBusinessIpMapper;
import com.ciicsh.gto.afsupportcenter.healthmedical.queryservice.business.AgentBusinessIpQueryService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageKit;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 保险公司表 服务实现类
 * </p>
 *
 * @author zhaogang
 * @since 2017-12-13
 */
@Service
public class AgentBusinessIpQueryServiceImpl extends ServiceImpl<AgentBusinessIpMapper,AgentBusinessIpPO> implements AgentBusinessIpQueryService {


    @Autowired
    private AgentBusinessIpMapper agentBusinessIpMapper;

    @Override
    public List<AgentBusinessIpPO> getList(AgentBusinessIpPO po) {

        List<AgentBusinessIpPO> list =agentBusinessIpMapper.getList(po);
        return list;
    }
}
