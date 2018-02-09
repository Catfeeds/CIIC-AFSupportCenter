package com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.dao;

import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.entity.AmArchiveUse;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 档案借出利用 Mapper 接口
 * </p>
 */
public interface AmArchiveUseMapper extends BaseMapper<AmArchiveUse> {

   List<AmArchiveUse> queryAmArchiveUseList(AmArchiveUse amArchiveUse);

   int  deleteAmArchiveUse(AmArchiveUse amArchiveUse);

}
