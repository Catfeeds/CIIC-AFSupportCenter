package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dao;

import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.bo.SsAnnualAdjustAccountBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dto.SsAnnualAdjustAccountDTO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.SsAnnualAdjustAccount;
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
}
