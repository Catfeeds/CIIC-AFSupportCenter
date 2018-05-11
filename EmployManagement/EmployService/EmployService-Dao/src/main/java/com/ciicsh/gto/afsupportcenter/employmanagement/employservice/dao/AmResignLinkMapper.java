package com.ciicsh.gto.afsupportcenter.employmanagement.employservice.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.bo.AmResignLinkBO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.entity.AmResignLink;

import java.util.List;

/**
 * <p>
 * 退工联动表 Mapper 接口
 * </p>
 *
 * @author xsj
 * @since 2018-03-29
 */
public interface AmResignLinkMapper extends BaseMapper<AmResignLink> {

    List<AmResignLinkBO> queryAmResignLink(String taskId);

}
