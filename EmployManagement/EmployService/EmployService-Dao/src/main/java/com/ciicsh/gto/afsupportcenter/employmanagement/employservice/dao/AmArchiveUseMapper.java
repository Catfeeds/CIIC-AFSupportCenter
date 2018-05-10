package com.ciicsh.gto.afsupportcenter.employmanagement.employservice.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.entity.AmArchiveUse;

import java.util.List;

/**
 * <p>
 * 档案借出利用 Mapper 接口
 * </p>
 */
public interface AmArchiveUseMapper extends BaseMapper<AmArchiveUse> {

   List<AmArchiveUse> queryAmArchiveUseList(AmArchiveUse amArchiveUse);

   int  deleteAmArchiveUse(AmArchiveUse amArchiveUse);

}
