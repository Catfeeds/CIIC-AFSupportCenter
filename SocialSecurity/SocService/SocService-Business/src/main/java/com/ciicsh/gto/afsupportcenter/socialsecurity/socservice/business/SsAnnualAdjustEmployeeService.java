package com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business;

import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.dto.SsAnnualAdjustEmployeeDTO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.SsAnnualAdjustEmployee;
import com.baomidou.mybatisplus.service.IService;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;

import java.util.List;

/**
 * <p>
 *  雇员社保年调表查询服务类
 * </p>
 */
public interface SsAnnualAdjustEmployeeService extends IService<SsAnnualAdjustEmployee> {

    /**
     * 雇员社保年调表查询
     * @param ssAnnualAdjustEmployeeDTO
     * @return
     */
    List<SsAnnualAdjustEmployee> queryAnnualAdjustEmployee(SsAnnualAdjustEmployeeDTO ssAnnualAdjustEmployeeDTO);

    /**
     * 雇员社保年调表分页查询
     * @param pageInfo
     * @return
     */
    PageRows<SsAnnualAdjustEmployee> queryAnnualAdjustEmployeeInPage(PageInfo pageInfo);
}
