package com.ciicsh.gto.afsupportcenter.credentialscommandservice.business.impl;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.business.TaskFollowService;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.dao.TaskFollowMapper;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.entity.po.TaskFollow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 任务跟进 服务实现类
 * </p>
 *
 * @author guwei
 * @since 2018-01-15
 */
@Service
public class TaskFollowServiceImpl extends ServiceImpl<TaskFollowMapper, TaskFollow> implements TaskFollowService {

    @Autowired
    private TaskFollowMapper taskFollowMapper;

    @Override
    public List<TaskFollow> selectList(int taskId) {
        return taskFollowMapper.selectListByTaskId(taskId);
    }
}
