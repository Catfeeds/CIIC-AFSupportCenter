package com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.dao;

import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo.SsAnnualAdjustAccountBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.dto.SsAnnualAdjustAccountDTO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.SsAnnualAdjustAccount;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 */
public interface SsAnnualAdjustAccountMapper extends BaseMapper<SsAnnualAdjustAccount> {

    List<SsAnnualAdjustAccount> queryAnnualAdjustAccount(SsAnnualAdjustAccountDTO ssAnnualAdjustAccountDTO);

    /**
     * 根据人员类别查询雇员数量
     * @param annualAdjustAccountId
     * @return
     */
    List<SsAnnualAdjustAccountBO> getCountOfAccountStatus(Long annualAdjustAccountId);

    /**
     * 根据匹配状态查询雇员数量
     * @param annualAdjustAccountId
     * @return
     */
    List<SsAnnualAdjustAccountBO> getCountOfMatchStatus(Long annualAdjustAccountId);

    /**
     * 根据社保账户ID查询雇员总数
     * @param comAccountId
     * @return
     */
    int getCountByComAccountId(Long comAccountId);

    /**
     * 根据社保账户ID查询社保账户单位平均工资相关信息
     * @param annualAdjustAccountId
     * @return
     */
    List<SsAnnualAdjustAccountBO> getUnitAvgMonthSalaryByAnnualAdjustAccountId(Long annualAdjustAccountId);
}
