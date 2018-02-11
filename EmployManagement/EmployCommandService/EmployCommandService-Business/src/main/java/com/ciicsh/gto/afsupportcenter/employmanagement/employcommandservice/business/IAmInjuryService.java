package com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.business;

import com.baomidou.mybatisplus.service.IService;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.bo.AmInjuryBO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.entity.AmInjury;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;

/**
 * Created by zhangzhiwen on 2018/2/7.
 */
public interface IAmInjuryService extends IService<AmInjury> {

    PageRows<AmInjury> queryAmInjury(PageInfo pageInfo);

    boolean deleteAmInjury(Long injuryId);
}
