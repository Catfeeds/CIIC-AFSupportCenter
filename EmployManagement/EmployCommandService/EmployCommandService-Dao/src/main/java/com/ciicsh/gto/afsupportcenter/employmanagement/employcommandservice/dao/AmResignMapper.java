package com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.dao;

import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.bo.AmResignBO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.entity.AmResign;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 用工表 Mapper 接口
 * </p>
 */
public interface AmResignMapper extends BaseMapper<AmResign> {
    List<AmResignBO> queryAmResign(AmResignBO amResignBO);
}
