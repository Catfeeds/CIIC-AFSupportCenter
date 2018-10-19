package com.ciicsh.gto.afsupportcenter.employmanagement.employservice.business.impl;

import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.entity.AmArchiveLink;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.dao.AmArchiveLinkMapper;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.business.IAmArchiveLinkService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 档案轨迹 服务实现类
 * </p>
 */
@Service
public class AmArchiveLinkServiceImpl extends ServiceImpl<AmArchiveLinkMapper, AmArchiveLink> implements IAmArchiveLinkService {

    @Override
    public boolean saveAmArchiveLink(AmArchiveLink amArchiveLink) {
        int i  = baseMapper.insert(amArchiveLink);
        return i>0?true:false;
    }

    @Override
    public List<AmArchiveLink> queryByArchiveId(Long archiveId) {
        return baseMapper.queryByArchiveId(archiveId);
    }
}
