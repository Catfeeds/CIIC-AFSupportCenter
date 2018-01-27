package com.ciicsh.gto.afsupportcenter.credentialscommandservice.dao;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.entity.po.TaskFollow;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
  * 任务跟进 Mapper 接口
 * </p>
 *
 * @author guwei
 * @since 2018-01-15
 */
@Repository
public interface TaskFollowMapper extends BaseMapper<TaskFollow> {

    /**
     * 查询任务单所有跟进记录
     * @param taskId
     * @return
     */
    List<TaskFollow> selectListByTaskId(int taskId);
}