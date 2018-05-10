package com.ciicsh.gto.afsupportcenter.employmanagement.employservice.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.bo.AmEmpEmployeeBO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.bo.AmTaskParamBO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.entity.AmEmpEmployee;

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

    List<AmEmpEmployeeBO> queryAmEmployee(AmTaskParamBO amTaskParamBO);

}
