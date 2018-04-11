package com.ciicsh.gto.afsupportcenter.credentialscommandservice.business;

import com.baomidou.mybatisplus.service.IService;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.entity.po.TaskType;

import java.util.List;

/**
 * @Author: guwei
 * @Description:
 * @Date: Created in 16:59 2018/4/3
 */
public interface TaskTypeService extends IService<TaskType> {

    /**
     * 查询证件类型
     * @param pid
     * @return
     */
    List<TaskType> findTaskType(String pid);
}
