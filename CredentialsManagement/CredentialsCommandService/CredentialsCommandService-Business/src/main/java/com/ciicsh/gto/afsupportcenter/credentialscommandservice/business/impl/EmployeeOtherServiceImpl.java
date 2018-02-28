package com.ciicsh.gto.afsupportcenter.credentialscommandservice.business.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.business.EmployeeOtherService;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.dao.EmployeeOtherMapper;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.entity.po.EmployeeOther;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: guwei
 * @Description:
 * @Date: Created in 14:14 2018/2/26
 */
@Service
public class EmployeeOtherServiceImpl extends ServiceImpl<EmployeeOtherMapper, EmployeeOther> implements EmployeeOtherService {

    @Autowired
    private EmployeeOtherMapper employeeOtherMapper;

    @Override
    public int addEmployeeOther(EmployeeOther employeeOther) {
        return employeeOtherMapper.insert(employeeOther);
    }
}
