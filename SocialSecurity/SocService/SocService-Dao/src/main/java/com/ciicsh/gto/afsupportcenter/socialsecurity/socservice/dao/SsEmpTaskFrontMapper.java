package com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.dao;

import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo.SsEmpTaskFrontBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.SsEmpTaskFront;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 雇员任务单前道传递信息,创建任务单的同时，就要把前道的传递信息复制到这表，当前表复制前道cmy_af_emp_socia Mapper 接口
 * </p>
 */
public interface SsEmpTaskFrontMapper extends BaseMapper<SsEmpTaskFront> {

    Integer getEmpTaskDetailCount(@Param("businessInterfaceId") String businessInterfaceId);

    List<SsEmpTaskFrontBO> getOriginEmpTask(@Param("empArchiveId") Long empArchiveId);
}
