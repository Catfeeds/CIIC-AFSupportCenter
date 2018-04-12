package com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.dao;

import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.bo.AmCustomBO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.entity.AmEmpCustom;
import com.baomidou.mybatisplus.mapper.BaseMapper;

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
