package com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.dao;

import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.bo.AmEmpTaskBO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.bo.AmRemarkBO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.entity.AmRemark;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 */
public interface AmRemarkMapper extends BaseMapper<AmRemark> {

    List<AmRemarkBO> queryAmRemark(AmRemarkBO amRemarkBO);

    int  deleteAmRemark(Long remarkId);

}
