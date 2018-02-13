package com.ciicsh.gto.afsupportcenter.credentialscommandservice.business;

import com.baomidou.mybatisplus.service.IService;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.entity.po.Employee;

/**
 * <p>
 * 雇员基本信息表
 雇员的公共信息存放在此表，此表的雇员信息为唯一数据，AF、BPO、FC雇员信息分别在各自的扩展信息表中 服务类
 * </p>
 *
 * @author guwei
 * @since 2018-02-12
 */
public interface EmployeeService extends IService<Employee> {


}