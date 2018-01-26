package com.ciicsh.gto.afsupportcenter.housefund.fundcommandservice.business.impl;

import com.ciicsh.gto.afsupportcenter.housefund.fundcommandservice.entity.EmpEmployee;
import com.ciicsh.gto.afsupportcenter.housefund.fundcommandservice.dao.EmpEmployeeMapper;
import com.ciicsh.gto.afsupportcenter.housefund.fundcommandservice.business.IEmpEmployeeService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 雇员基本信息表
雇员的公共信息存放在此表，此表的雇员信息为唯一数据，AF、BPO、FC雇员信息分别在各自的扩展信息表中 服务实现类
 * </p>
 */
@Service
public class EmpEmployeeServiceImpl extends ServiceImpl<EmpEmployeeMapper, EmpEmployee> implements IEmpEmployeeService {

}
