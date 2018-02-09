package com.ciicsh.gto.afsupportcenter.socjob.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.ciicsh.gto.afsupportcenter.socjob.dao.SsEmpArchiveMapper;
import com.ciicsh.gto.afsupportcenter.socjob.dao.TaskStatusMapper;
import com.ciicsh.gto.afsupportcenter.socjob.entity.SsEmpArchive;
import com.ciicsh.gto.afsupportcenter.socjob.service.TaskStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Wrapper;
import java.util.List;
import java.util.Map;

/**
 * Created by linhui on 2018/1/22
 */
@Service
public class TaskStatusServiceImpl implements TaskStatusService {

    @Autowired
    private SsEmpArchiveMapper ssEmpArchiveMapper;
    @Autowired
    private TaskStatusMapper taskStatusMapper;

    @Override
    public void updateTaskStatus(String ssMonth) {
        List<Map> empTaskList= taskStatusMapper.getEmpTaskList(ssMonth);
        empTaskList.stream().forEach(map -> {
            System.out.println(map.get("emp_archive_id").toString());
        });
        empTaskList.stream().forEach(map->{
            int taskCategory=(int) map.get("task_category");
            if(taskCategory==1 || taskCategory==2 ){  //新进、转入
                SsEmpArchive ssEmpArchive=new SsEmpArchive();
                ssEmpArchive.setArchiveStatus(2);
                ssEmpArchive.setArchiveTaskStatus(2);
                ssEmpArchive.setEmpArchiveId((long)map.get("emp_archive_id"));
                ssEmpArchiveMapper.updateById(ssEmpArchive);
              /*  EntityWrapper<SsEmpArchive> wrapper=new EntityWrapper();
                wrapper.setEntity(ssEmpArchive);
                ssEmpArchiveMapper.update(ssEmpArchive, wrapper);*/
            }else if(taskCategory== 5 || taskCategory== 6) {// 转出 封存
                SsEmpArchive ssEmpArchive=new SsEmpArchive();
                ssEmpArchive.setArchiveStatus(3);
                ssEmpArchive.setArchiveTaskStatus(3);
                ssEmpArchive.setEmpArchiveId((long)map.get("emp_archive_id"));
                ssEmpArchiveMapper.updateById(ssEmpArchive);
            }else{ //其他
                taskStatusMapper.updateEmpArchive(map.get("emp_archive_id").toString());
            }
            taskStatusMapper.updateEmpTask("3",map.get("emp_task_id").toString());
        });
    }
}
