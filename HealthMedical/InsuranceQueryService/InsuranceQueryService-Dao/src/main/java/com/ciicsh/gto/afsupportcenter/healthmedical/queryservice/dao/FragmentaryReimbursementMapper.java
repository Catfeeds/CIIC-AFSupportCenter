package com.ciicsh.gto.afsupportcenter.healthmedical.queryservice.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.healthmedical.queryservice.entity.po.FragmentaryReimbursementPO;
import com.ciicsh.gto.afsupportcenter.healthmedical.queryservice.api.dto.FragmentaryReimbursementDTO;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * <p>
  * 零星报销表 Mapper 接口
 * </p>
 *
 * @author zhaogang
 * @since 2017-12-04
 */
@Repository
@SuppressWarnings("all")
public interface FragmentaryReimbursementMapper extends BaseMapper<FragmentaryReimbursementPO> {

    FragmentaryReimbursementPO getById(String id);
    List<FragmentaryReimbursementPO> fragmentaryReimbursementMapperQuery(FragmentaryReimbursementPO pageInfo);

}