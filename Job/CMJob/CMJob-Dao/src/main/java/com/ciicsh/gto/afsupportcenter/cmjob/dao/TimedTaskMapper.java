package com.ciicsh.gto.afsupportcenter.cmjob.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.ciicsh.gto.afsupportcenter.cmjob.bo.TimedTaskBO;
import com.ciicsh.gto.afsupportcenter.cmjob.po.TimedTask;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
  * 定时任务表 Mapper 接口
 * </p>
 *
 * @author guwei
 * @since 2018-09-18
 */
@Repository
public interface TimedTaskMapper extends BaseMapper<TimedTask> {

    List<TimedTaskBO> selectTimeTaskList();
}