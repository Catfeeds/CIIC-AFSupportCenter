package com.ciicsh.gto.afsupportcenter.socjob.dao;

import org.apache.ibatis.annotations.Mapper;
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
     List<Map> getEmpTaskList(String ssMonth);
     void  updateEmpArchive(String empArchiveId);
     void  updateEmpTask(String taskStatus,String empTaskId);

}
