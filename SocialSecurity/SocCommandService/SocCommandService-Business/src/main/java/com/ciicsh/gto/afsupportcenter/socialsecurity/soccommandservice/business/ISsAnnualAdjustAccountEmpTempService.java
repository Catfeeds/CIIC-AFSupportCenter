package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.business;

import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dto.SsAnnualAdjustAccountEmpTempDTO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.SsAnnualAdjustAccountEmpTemp;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 */
public interface ISsAnnualAdjustAccountEmpTempService extends IService<SsAnnualAdjustAccountEmpTemp> {

    /**
     * 批量更新repeatingColumn字段重复的记录的errorMsg字段，追加错误信息（repeatingColumn字段可选：身份证号，社保序号）
     * @param ssAnnualAdjustAccountEmpTempDTO
     */
    void updateErrorMsgForRepeatingEmployeeId(SsAnnualAdjustAccountEmpTempDTO ssAnnualAdjustAccountEmpTempDTO);
}
