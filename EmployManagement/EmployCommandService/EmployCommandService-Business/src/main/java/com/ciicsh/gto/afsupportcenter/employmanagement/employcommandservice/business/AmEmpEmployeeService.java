package com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.business;

import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.bo.AmEmpEmployeeBO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.bo.AmTaskParamBO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.entity.AmEmpEmployee;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

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

}
