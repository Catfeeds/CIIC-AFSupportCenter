package com.ciicsh.gto.afsupportcenter.healthmedical.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.dto.FragmentaryReimbursementDTO;
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.po.FragmentaryReimbursementPO;

import java.util.List;

/**
 * <p>
 * 零星报销表 Mapper 接口
 * </p>
 *
 * @author zhaogang
 * @since 2017-12-04
 */
public interface FragmentaryReimbursementMapper extends BaseMapper<FragmentaryReimbursementPO> {

    /**
     * 根据
     *
     * @param id
     * @return
     */
    FragmentaryReimbursementPO getById(String id);

    /**
     * 查询
     *
     * @param page
     * @param fragmentaryReimbursementDTO
     * @return
     */
    List<FragmentaryReimbursementPO> fragmentaryReimbursementMapperQuery(Page<FragmentaryReimbursementPO> page, FragmentaryReimbursementDTO fragmentaryReimbursementDTO);

}