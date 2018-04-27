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
        1, "新进
        2, "转入
        3, "调整
        4, "补缴
        5, "转出
        6, "封存
        7, "退账
        9, "特殊操作
        12, "翻牌新进
        13, "翻牌转入
        14, "翻牌转出
        15, "翻牌封存
    */

    final Integer EMP_TASK_STATUS_2 = 2;
    final Integer EMP_TASK_STATUS_3 = 3;
    // task_status: 2 已办 3已做
    //archive_status:  1-已办  2-已做 3-转出
    // archive_task_status 0-未办理 1-已办  2-已做 3-转出
    @Override
    public void updateTaskStatus(String ssMonth) {

        List<Map> empTaskList= taskStatusMapper.getEmpTaskList(ssMonth);
        empTaskList.stream().forEach(map->{
            int taskCategory=(int) map.get("task_category");
            switch (taskCategory){
                case 1:
                case 2:
                case 12:
                case 13: {
                    SsEmpArchive ssEmpArchive=new SsEmpArchive();
                    ssEmpArchive.setArchiveStatus(2);
                    ssEmpArchive.setArchiveTaskStatus(2);
                    ssEmpArchive.setEmpArchiveId((long)map.get("emp_archive_id"));
                    ssEmpArchiveMapper.updateById(ssEmpArchive);
                    taskStatusMapper.updateEmpTask("3",map.get("emp_task_id").toString());
                }
                break;
                case 5:
                case 6:
                case 14:
                case 15:{
                    SsEmpArchive ssEmpArchive = new SsEmpArchive();
                    ssEmpArchive.setArchiveStatus(3);
                    ssEmpArchive.setArchiveTaskStatus(3);
                    ssEmpArchive.setEmpArchiveId((long) map.get("emp_archive_id"));
                    ssEmpArchiveMapper.updateById(ssEmpArchive);
                    taskStatusMapper.updateEmpTask("3",map.get("emp_task_id").toString());
                }
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
