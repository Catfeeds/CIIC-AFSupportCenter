package com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.dao;

import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.dto.SsAnnualAdjustCompanyEmpDTO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.dto.SsAnnualAdjustCompanyEmpTempDTO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.SsAnnualAdjustCompanyEmp;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  年调客户雇员信息Mapper 接口
 * </p>
 */
public interface SsAnnualAdjustCompanyEmpMapper extends BaseMapper<SsAnnualAdjustCompanyEmp> {

    /**
     * 查询年调客户雇员信息（根据年调客户信息ID，雇员姓名，雇员身份证号，社保基数范围查询）
     * @param ssAnnualAdjustCompanyEmpDTO
     * @return
     */
    List<SsAnnualAdjustCompanyEmp> queryAnnualAdjustCompanyEmp(SsAnnualAdjustCompanyEmpDTO ssAnnualAdjustCompanyEmpDTO);

    /**
     * 将年调客户雇员临时表中没有错误信息的数据插入正式表
     * @param ssAnnualAdjustCompanyEmpTempDTO
     */
    void insertDataWithoutErrorMsg(SsAnnualAdjustCompanyEmpTempDTO ssAnnualAdjustCompanyEmpTempDTO);
}
