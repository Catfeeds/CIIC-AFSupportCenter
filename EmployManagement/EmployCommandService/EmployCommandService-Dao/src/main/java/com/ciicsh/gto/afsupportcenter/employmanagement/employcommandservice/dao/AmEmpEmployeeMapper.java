package com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.dao;

import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.bo.AmEmpEmployeeBO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.entity.AmEmpEmployee;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 雇员信息表 Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since 2018-04-09
 */
public interface AmEmpEmployeeMapper extends BaseMapper<AmEmpEmployee> {

    List<AmEmpEmployeeBO> queryAmEmployeeByTaskId(Long empTaskId);

}
