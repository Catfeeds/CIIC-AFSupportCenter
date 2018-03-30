package com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.business.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.bo.AmResignLinkBO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.business.AmResignLinkService;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.dao.AmResignLinkMapper;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.entity.AmResignLink;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhangzhiwen on 2018/3/29.
 */

@Service
public class AmResignLinkServiceImpl extends ServiceImpl<AmResignLinkMapper, AmResignLink> implements AmResignLinkService {

    @Override
    public List<AmResignLinkBO> queryAmResignLink(String taskId) {
        return baseMapper.queryAmResignLink(taskId);
    }
}
