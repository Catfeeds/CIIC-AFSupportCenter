package com.ciicsh.gto.afsupportcenter.credentialscommandservice.business;

import com.baomidou.mybatisplus.service.IService;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.entity.po.Task;

import java.util.List;

/**
 * <p>
 * 证件办理任务单 服务类
 * </p>
 *
 * @author guwei
 * @since 2018-01-15
 */
public interface TaskService extends IService<Task> {

    /**
     * 根据雇员查询任务单列表
     * @param empId
     * @return
     */
    List<Task> selectByempId(String empId);

    /**
     * 保存或更新任务单
     * @param entity
     * @return
     */
    @Override
    boolean insertOrUpdate(Task entity);
}
