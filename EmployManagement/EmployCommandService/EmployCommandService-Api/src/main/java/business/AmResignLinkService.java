package com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.business;

import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.bo.AmResignLinkBO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.entity.AmResignLink;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 * 退工联动表 服务类
 * </p>
 *
 * @author xsj
 * @since 2018-03-29
 */
public interface AmResignLinkService extends IService<AmResignLink> {

    List<AmResignLinkBO> queryAmResignLink(String taskId);

}
