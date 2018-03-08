package com.ciicsh.gto.afsupportcenter.healthmedical.business;

import com.baomidou.mybatisplus.service.IService;
import com.ciicsh.gto.afsupportcenter.healthmedical.entity.po.AfPeTask;

import java.util.List;

/**
 * <p>
 * 体检任务单表 服务类
 * </p>
 *
 * @author 顾伟
 * @since 2018-03-07
 */
public interface AfPeTaskService extends IService<AfPeTask> {

    /**
     * 批量入库体检任务单
     *  入职体检->同步至中盈
     *  年度体检->已发放
     * @param list
     * @return
     */
    boolean insertBatchTask(List<AfPeTask> list);

    /**
     * 更新体检任务单
     * @param afPeTask
     * @return
     */
    boolean update(AfPeTask afPeTask);

    /**
     * 获取近两天的任务单list
     * @return
     */
    List<AfPeTask> getListByBespeakPeIds();

    /**
     * 获取体检进行中的任务单
     * @return
     */
    List<AfPeTask> getListByStatus();
}