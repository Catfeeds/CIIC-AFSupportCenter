package com.ciicsh.gto.afsupportcenter.employmanagement.employservice.business;

import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.entity.AmArchiveLink;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 * 档案轨迹 服务类
 * </p>
 */
public interface IAmArchiveLinkService extends IService<AmArchiveLink> {

    boolean saveAmArchiveLink(AmArchiveLink amArchiveLink);

    List<AmArchiveLink> queryByArchiveId(Long archiveId);

}
