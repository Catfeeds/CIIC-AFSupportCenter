package com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.dao;

import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.bo.AmArchiveBO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.entity.AmArchive;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 雇员档案表，1个雇员理应只有1份档案 Mapper 接口
 * </p>
 */
public interface AmArchiveMapper extends BaseMapper<AmArchive> {

    List<AmArchiveBO>  queryAmArchive(String employeeId);

    List<AmArchiveBO>  queryAmArchiveList(Map<String,Object>  param);

}
