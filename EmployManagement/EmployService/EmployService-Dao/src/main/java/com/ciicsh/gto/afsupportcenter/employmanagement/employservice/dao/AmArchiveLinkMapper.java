package com.ciicsh.gto.afsupportcenter.employmanagement.employservice.dao;

import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.entity.AmArchiveLink;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 档案轨迹 Mapper 接口
 * </p>
 */
public interface AmArchiveLinkMapper extends BaseMapper<AmArchiveLink> {

    List<AmArchiveLink> queryByArchiveId(Long archiveId);

}
