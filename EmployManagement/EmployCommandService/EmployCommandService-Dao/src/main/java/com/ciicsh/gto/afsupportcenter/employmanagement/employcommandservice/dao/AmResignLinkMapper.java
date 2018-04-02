package com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.dao;

import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.bo.AmResignLinkBO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.entity.AmResignLink;
import com.baomidou.mybatisplus.mapper.BaseMapper;

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
