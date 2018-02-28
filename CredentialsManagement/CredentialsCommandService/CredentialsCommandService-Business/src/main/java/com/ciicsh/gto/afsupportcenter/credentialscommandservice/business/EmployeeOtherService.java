package com.ciicsh.gto.afsupportcenter.credentialscommandservice.business;

import com.baomidou.mybatisplus.service.IService;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.entity.po.EmployeeOther;

/**
 * @Author: guwei
 * @Description:
 * @Date: Created in 14:14 2018/2/26
 */
public interface EmployeeOtherService extends IService<EmployeeOther> {

    /**
     * 添加单项雇员其他信息
     * @param employeeOther
     * @return
     */
    int addEmployeeOther(EmployeeOther employeeOther);
}
