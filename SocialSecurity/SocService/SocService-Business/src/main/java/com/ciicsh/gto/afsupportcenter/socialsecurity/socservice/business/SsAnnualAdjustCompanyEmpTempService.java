package com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business;

import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.dto.SsAnnualAdjustCompanyEmpTempDTO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.SsAnnualAdjustCompanyEmpTemp;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 */
public interface SsAnnualAdjustCompanyEmpTempService extends IService<SsAnnualAdjustCompanyEmpTemp> {

    /**
     * 批量更新repeatingColumn字段重复的记录的errorMsg字段，追加错误信息（repeatingColumn字段可选：雇员编号，身份证号，社保序号）
     * @param ssAnnualAdjustCompanyEmpTempDTO
     */
    void updateErrorMsgForRepeatingEmployeeId(SsAnnualAdjustCompanyEmpTempDTO ssAnnualAdjustCompanyEmpTempDTO);

    /**
     * 批量更新与年调雇员表关键信息不符的记录的errorMsg字段（关键信息：雇员编号，雇员姓名，社保序号）
     * @param ssAnnualAdjustCompanyEmpTempDTO
     */
    void updateErrorMsgForNotExistsEmployee(SsAnnualAdjustCompanyEmpTempDTO ssAnnualAdjustCompanyEmpTempDTO);

}
