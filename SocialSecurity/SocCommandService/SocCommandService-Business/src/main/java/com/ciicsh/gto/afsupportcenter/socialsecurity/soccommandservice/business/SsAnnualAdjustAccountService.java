package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.business;

import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.bo.SsAnnualAdjustAccountBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dto.SsAnnualAdjustAccountDTO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.SsAnnualAdjustAccount;
import com.baomidou.mybatisplus.service.IService;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;

import java.util.List;

/**
 * <p>
 *  年调社保账户信息操作服务类
 * </p>
 */
public interface SsAnnualAdjustAccountService extends IService<SsAnnualAdjustAccount> {
    /**
     * 查询年调社保账户信息
     * @param ssAnnualAdjustAccountDTO
     * @return
     */
    List<SsAnnualAdjustAccount> queryAnnualAdjustAccount(SsAnnualAdjustAccountDTO ssAnnualAdjustAccountDTO);

    /**
     * 分页查询年调社保账户信息
     * @param pageInfo
     * @return
     */
    PageRows<SsAnnualAdjustAccount> queryAnnualAdjustAccountInPage(PageInfo pageInfo);

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
