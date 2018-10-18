package com.ciicsh.gto.afsupportcenter.employmanagement.employservice.dao;

import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.entity.AmEmployeChange;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 雇员信息变更表 Mapper 接口
 * </p>
 */
public interface AmEmployeChangeMapper extends BaseMapper<AmEmployeChange> {

    List<AmEmployeChange> getEmployeeChange(Long empTaskId);

}
