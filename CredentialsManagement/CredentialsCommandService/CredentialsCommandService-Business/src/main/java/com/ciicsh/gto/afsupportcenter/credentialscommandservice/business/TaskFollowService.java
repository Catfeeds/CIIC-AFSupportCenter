package com.ciicsh.gto.afsupportcenter.credentialscommandservice.business;

import com.baomidou.mybatisplus.service.IService;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.entity.po.TaskFollow;

import java.util.List;

/**
 * <p>
 * 任务跟进 服务类
 * </p>
 *
 * @author guwei
 * @since 2018-01-15
 */
public interface TaskFollowService extends IService<TaskFollow> {

    /**
     * 查询任务单跟进列表
     * @param taskId
     * @return
     */
    List<TaskFollow> selectList(int taskId);

    /**
     * 保存或更新跟进记录
     * @param entity
     * @return
     */
    @Override
    boolean insertOrUpdate(TaskFollow entity);
}
