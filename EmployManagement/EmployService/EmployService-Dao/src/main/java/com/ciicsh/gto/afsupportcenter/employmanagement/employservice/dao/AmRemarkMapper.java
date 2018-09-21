package com.ciicsh.gto.afsupportcenter.employmanagement.employservice.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.api.dto.RemarkDTO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.api.dto.RemarkParamDTO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.bo.AmRemarkBO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.entity.AmRemark;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 */
public interface AmRemarkMapper extends BaseMapper<AmRemark> {

    List<AmRemarkBO> queryAmRemark(AmRemarkBO amRemarkBO);

    int  deleteAmRemark(Long remarkId);

    List<RemarkDTO> queryRemarkList(RemarkParamDTO remarkParamDTO);

}
