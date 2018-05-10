package com.ciicsh.gto.afsupportcenter.employmanagement.employservice.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.bo.AmCustomBO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.entity.AmEmpCustom;

import java.util.List;

/**
 * <p>
 * 客户信息表 Mapper 接口
 * </p>
 *
 * @author xsj
 * @since 2018-03-27
 */
public interface AmEmpCustomMapper extends BaseMapper<AmEmpCustom> {

    List<AmCustomBO> getCustom(Long empTaskId);

}
