package com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.dao;

import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.dto.SsAnnualAdjustCompanyEmpTempDTO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.SsAnnualAdjustCompanyEmpTemp;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 *  年调客户雇员信息临时表Mapper 接口
 * </p>
 */
public interface SsAnnualAdjustCompanyEmpTempMapper extends BaseMapper<SsAnnualAdjustCompanyEmpTemp> {

    /**
     * 批量更新repeatingColumn字段重复的记录的errorMsg字段，追加错误信息
     * @param ssAnnualAdjustCompanyEmpTempDTO
     */
    void updateErrorMsgForRepeatingEmployeeId(SsAnnualAdjustCompanyEmpTempDTO ssAnnualAdjustCompanyEmpTempDTO);

    /**
     * 批量更新与年调雇员表关键信息不符的记录的errorMsg字段，追加错误信息
     * @param ssAnnualAdjustCompanyEmpTempDTO
     */
    void updateErrorMsgForNotExistsEmployee(SsAnnualAdjustCompanyEmpTempDTO ssAnnualAdjustCompanyEmpTempDTO);
}
