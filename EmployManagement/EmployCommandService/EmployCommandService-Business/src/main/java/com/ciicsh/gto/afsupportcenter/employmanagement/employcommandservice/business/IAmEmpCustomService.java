package com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.business;

import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.bo.AmCustomBO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.entity.AmEmpCustom;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 客户信息表 服务类
 * </p>
 *
 * @author xsj
 * @since 2018-03-27
 */
public interface IAmEmpCustomService extends IService<AmEmpCustom> {

    AmCustomBO getCustom(AmCustomBO amCustomBO);

}
