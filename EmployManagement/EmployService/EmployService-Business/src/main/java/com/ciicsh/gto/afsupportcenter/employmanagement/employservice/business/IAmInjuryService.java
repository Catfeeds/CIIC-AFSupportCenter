package com.ciicsh.gto.afsupportcenter.employmanagement.employservice.business;

import com.baomidou.mybatisplus.service.IService;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.bo.AmInjuryBO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.entity.AmInjury;

import java.util.List;

/**
 * Created by zhangzhiwen on 2018/2/7.
 */
public interface IAmInjuryService extends IService<AmInjury> {

    List<AmInjuryBO> queryAmInjury(AmInjuryBO amInjuryBO);

}
