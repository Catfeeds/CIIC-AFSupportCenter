package com.ciicsh.gto.afsupportcenter.credentialscommandservice.dao;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.entity.po.Task;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
  * 证件办理任务单 Mapper 接口
 * </p>
 *
 * @author guwei
 * @since 2018-01-15
 */
@Repository
public interface TaskMapper extends BaseMapper<Task> {

    /**
     * 根据雇员id查询任务单列表
     * @param empId
     * @return
     */
    List<Task> selectByempId(String empId);
}