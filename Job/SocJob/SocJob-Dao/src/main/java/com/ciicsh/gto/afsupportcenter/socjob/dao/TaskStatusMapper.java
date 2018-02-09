package com.ciicsh.gto.afsupportcenter.socjob.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.ciicsh.gto.afsupportcenter.socjob.entity.SsEmpArchive;
import com.ciicsh.gto.afsupportcenter.socjob.entity.SsReportStatus;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 每月定时更新
 * </p>
 */
@Mapper
@Component
public interface TaskStatusMapper {
     List<Map> getEmpTaskList(@Param("ssMonth")String ssMonth);
     void  updateEmpArchive(@Param("empArchiveId")String empArchiveId);
     void  updateEmpTask(@Param("taskStatus")String taskStatus,@Param("empTaskId") String empTaskId);

}
