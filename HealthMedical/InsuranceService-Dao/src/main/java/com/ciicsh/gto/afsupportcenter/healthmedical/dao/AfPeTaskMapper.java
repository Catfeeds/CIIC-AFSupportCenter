package com.ciicsh.gto.afsupportcenter.healthmedical.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.po.AfPeTask;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 体检任务单表 Mapper 接口
 * </p>
 *
 * @author 顾伟
 * @since 2018-03-07
 */
@Repository
public interface AfPeTaskMapper extends BaseMapper<AfPeTask> {

    /**
     * 获取近两天的任务单
     * @return
     */
    List<AfPeTask> selectListByBespeakPeIds();

    /**
     * 获取体检进行中的任务单
     * @return
     */
    List<AfPeTask> selectListByStatus();
}
