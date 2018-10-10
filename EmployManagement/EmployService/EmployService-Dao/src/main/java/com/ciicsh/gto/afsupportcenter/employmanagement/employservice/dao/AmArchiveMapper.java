package com.ciicsh.gto.afsupportcenter.employmanagement.employservice.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.bo.AmArchiveBO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.bo.AmPostBO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.entity.AmArchive;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 雇员档案表，1个雇员理应只有1份档案 Mapper 接口
 * </p>
 */
public interface AmArchiveMapper extends BaseMapper<AmArchive> {

    List<AmArchiveBO> queryAmArchiveList(Map<String, Object> param);

    List<AmArchiveBO> queryAmArchiveBatch(AmArchiveBO amArchiveBO);

    List<AmArchive>  queryArchiveByEmpId(AmPostBO amPostBO);

}
