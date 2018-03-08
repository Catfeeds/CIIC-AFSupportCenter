package com.ciicsh.gto.afsupportcenter.credentialscommandservice.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.entity.po.Employee;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 雇员基本信息表
 雇员的公共信息存放在此表，此表的雇员信息为唯一数据，AF、BPO、FC雇员信息分别在各自的扩展信息表中 Mapper 接口
 * </p>
 *
 * @author guwei
 * @since 2018-02-12
 */
@Repository
public interface EmployeeMapper extends BaseMapper<Employee> {
    /**
     * 根据身份证件查询雇员
     * @param idCardType
     * @param idNum
     * @return
     */
    List<Employee> findEmpByIdCard(@Param("idCardType") Integer idCardType, @Param("idNum") String idNum);
}