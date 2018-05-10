package com.ciicsh.gto.afsupportcenter.employmanagement.employservice.business;

import com.baomidou.mybatisplus.service.IService;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.bo.AmCustomBO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.entity.AmEmpCustom;

/**
 * <p>
 * 客户信息表 服务类
 * </p>
 *
 * @author xsj
 * @since 2018-03-27
 */
public interface IAmEmpCustomService extends IService<AmEmpCustom> {

    AmCustomBO getCustom(Long empTaskId);

}
