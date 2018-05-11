package com.ciicsh.gto.afsupportcenter.employmanagement.employservice.business;

import com.baomidou.mybatisplus.service.IService;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.bo.AmResignLinkBO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.entity.AmResignLink;

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
