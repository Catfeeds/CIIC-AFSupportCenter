package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dao;

import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dto.SsAnnualAdjustEmployeeDTO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.SsAnnualAdjustEmployee;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  雇员社保年调表Mapper接口
 * </p>
 */
public interface SsAnnualAdjustEmployeeMapper extends BaseMapper<SsAnnualAdjustEmployee> {

    /**
     * 查询雇员社保年调表（可分别根据社保账户类别，社保账户ID，客户ID进行查询）
     * @param ssAnnualAdjustEmployeeDTO
     * @return
     */
    List<SsAnnualAdjustEmployee> queryAnnualAdjustEmployee(SsAnnualAdjustEmployeeDTO ssAnnualAdjustEmployeeDTO);
}
