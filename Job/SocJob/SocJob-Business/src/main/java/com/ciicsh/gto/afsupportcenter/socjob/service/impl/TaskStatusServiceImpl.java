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
    /*任务类型：
    1 新开
    2 转入
    3 启封
    4 转出
    5 封存
    6 补缴
    7 调整
    8 转移
    9 翻牌新开
    10 翻牌转入
    11 翻牌启封
    12 翻牌转出*/
    @Override
    public void updateTaskStatus(String ssMonth) {
// 2 已办 3已做
        List<Map> empTaskList= taskStatusMapper.getEmpTaskList(ssMonth);
        empTaskList.stream().forEach(map->{
            int taskCategory=(int) map.get("task_category");
            switch (taskCategory){
                case 1:
                case 2:
                case 3:
                case 9:
                case 10:
                case 11: {
                    SsEmpArchive ssEmpArchive=new SsEmpArchive();
                    ssEmpArchive.setArchiveStatus(2);
                    ssEmpArchive.setArchiveTaskStatus(2);
                    ssEmpArchive.setEmpArchiveId((long)map.get("emp_archive_id"));
                    ssEmpArchiveMapper.updateById(ssEmpArchive);
                    taskStatusMapper.updateEmpTask("3",map.get("emp_task_id").toString());
                }
                break;
                case 4:
                case 5:
                case 12:{
                    SsEmpArchive ssEmpArchive = new SsEmpArchive();
                    ssEmpArchive.setArchiveStatus(3);
                    ssEmpArchive.setArchiveTaskStatus(3);
                    ssEmpArchive.setEmpArchiveId((long) map.get("emp_archive_id"));
                    ssEmpArchiveMapper.updateById(ssEmpArchive);
                    taskStatusMapper.updateEmpTask("3",map.get("emp_task_id").toString());
                }
                break;
                case 8:
                    break;
                default:
                    taskStatusMapper.updateEmpArchive(map.get("emp_archive_id").toString());
                    taskStatusMapper.updateEmpTask("3",map.get("emp_task_id").toString());
                break;
            }

           /* if(taskCategory==1 || taskCategory==2 ){  //新进、转入
                SsEmpArchive ssEmpArchive=new SsEmpArchive();
                ssEmpArchive.setArchiveStatus(2);
                ssEmpArchive.setArchiveTaskStatus(2);
                ssEmpArchive.setEmpArchiveId((long)map.get("emp_archive_id"));
                ssEmpArchiveMapper.updateById(ssEmpArchive);
              *//*  EntityWrapper<SsEmpArchive> wrapper=new EntityWrapper();
                wrapper.setEntity(ssEmpArchive);
                ssEmpArchiveMapper.update(ssEmpArchive, wrapper);*//*
            }else if(taskCategory== 5 || taskCategory== 6 ) {// 转出 封存
                SsEmpArchive ssEmpArchive=new SsEmpArchive();
                ssEmpArchive.setArchiveStatus(3);
                ssEmpArchive.setArchiveTaskStatus(3);
                ssEmpArchive.setEmpArchiveId((long)map.get("emp_archive_id"));
                ssEmpArchiveMapper.updateById(ssEmpArchive);
            }else{ //其他
                taskStatusMapper.updateEmpArchive(map.get("emp_archive_id").toString());
            }
            taskStatusMapper.updateEmpTask("3",map.get("emp_task_id").toString());*/

        });
    }
}
