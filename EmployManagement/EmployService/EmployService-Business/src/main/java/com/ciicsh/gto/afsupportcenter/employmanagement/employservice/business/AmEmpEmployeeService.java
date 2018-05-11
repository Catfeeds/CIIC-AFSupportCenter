package com.ciicsh.gto.afsupportcenter.employmanagement.employservice.business;

import com.baomidou.mybatisplus.service.IService;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.bo.AmEmpEmployeeBO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.bo.AmTaskParamBO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.entity.AmEmpEmployee;

/**
 * <p>
 * 雇员信息表 服务类
 * </p>
 *
 * @author ${author}
 * @since 2018-04-09
 */
public interface AmEmpEmployeeService extends IService<AmEmpEmployee> {

    AmEmpEmployeeBO queryAmEmployeeByTaskId(Long empTaskId);

    AmEmpEmployeeBO queryAmEmployee(AmTaskParamBO amTaskParamBO);

    AmEmpEmployeeBO queryDefaultAmEmployee(AmTaskParamBO amTaskParamBO);

}
