package com.ciicsh.gto.afsupportcenter.credentialscommandservice.business.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.business.EmployeeService;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.dao.EmployeeMapper;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.entity.po.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 雇员基本信息表
 雇员的公共信息存放在此表，此表的雇员信息为唯一数据，AF、BPO、FC雇员信息分别在各自的扩展信息表中 服务实现类
 * </p>
 *
 * @author guwei
 * @since 2018-02-12
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public int addEmployee(Employee employee) {
        return employeeMapper.insert(employee);
    }

    @Override
    public boolean findEmpByIdCard(Integer idCardType, String idNum) {
        List<Employee> employees = employeeMapper.findEmpByIdCard(idCardType, idNum);
        return employees == null || employees.size() == 0 ? true : false;
    }
}