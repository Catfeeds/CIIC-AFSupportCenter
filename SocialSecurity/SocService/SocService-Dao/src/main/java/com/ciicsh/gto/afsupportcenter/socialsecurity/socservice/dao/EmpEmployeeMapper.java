package com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.dao;

import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.EmpEmployee;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 雇员基本信息表
雇员的公共信息存放在此表，此表的雇员信息为唯一数据，AF、BPO、FC雇员信息分别在各自的扩展信息表中 Mapper 接口
 * </p>
 *
 * @author HuangXing
 * @since 2017-12-11
 */
@Mapper
@Component
public interface EmpEmployeeMapper extends BaseMapper<EmpEmployee> {
    /**
     * 根据企业社保账户获取雇员
     * @param comAccountId 企业社保账号
     * @param employeeName 雇员名称
     * @param ssSerial 序号
     * @return
     */
    EmpEmployee getEmployeeByName(@Param("comAccountId") Long comAccountId,@Param("employeeName") String employeeName,@Param("ssSerial") String ssSerial);

}
