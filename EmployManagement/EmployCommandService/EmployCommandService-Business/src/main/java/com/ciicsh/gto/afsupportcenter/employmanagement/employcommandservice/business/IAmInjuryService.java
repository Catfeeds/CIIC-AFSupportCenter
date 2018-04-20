package com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.business;

import com.baomidou.mybatisplus.service.IService;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.bo.AmInjuryBO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.entity.AmInjury;

import java.util.List;

/**
 * Created by zhangzhiwen on 2018/2/7.
 */
public interface IAmInjuryService extends IService<AmInjury> {

    List<AmInjuryBO> queryAmInjury(AmInjuryBO amInjuryBO);

}
