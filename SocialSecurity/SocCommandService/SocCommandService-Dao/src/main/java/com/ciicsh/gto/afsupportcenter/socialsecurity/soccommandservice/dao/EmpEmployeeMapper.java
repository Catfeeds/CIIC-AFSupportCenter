package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dao;

import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.EmpEmployee;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 * 雇员基本信息表
雇员的公共信息存放在此表，此表的雇员信息为唯一数据，AF、BPO、FC雇员信息分别在各自的扩展信息表中 Mapper 接口
 * </p>
 *
 * @author HuangXing
 * @since 2017-12-11
 */
public interface EmpEmployeeMapper extends BaseMapper<EmpEmployee> {

}
