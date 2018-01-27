package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dao;

import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dto.SsAnnualAdjustAccountEmpTempDTO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.SsAnnualAdjustAccountEmpTemp;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 */
public interface SsAnnualAdjustAccountEmpTempMapper extends BaseMapper<SsAnnualAdjustAccountEmpTemp> {

    /**
     * 批量更新repeatingColumn字段重复的记录的errorMsg字段，追加错误信息
     * @param ssAnnualAdjustAccountEmpTempDTO
     */
    void updateErrorMsgForRepeatingEmployeeId(SsAnnualAdjustAccountEmpTempDTO ssAnnualAdjustAccountEmpTempDTO);
}
