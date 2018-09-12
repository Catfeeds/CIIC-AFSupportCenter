package com.ciicsh.gto.afsupportcenter.employmanagement.employservice.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.bo.AmArchiveAdvanceBO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.entity.AmArchiveAdvance;

import java.util.List;

/**
 * <p>
 * 档案预增表
 * </p>
 */
public interface AmArchiveAdvanceMapper extends BaseMapper<AmArchiveAdvance> {
    List<AmArchiveAdvance> queryAdvanceList(AmArchiveAdvanceBO bo);
}
