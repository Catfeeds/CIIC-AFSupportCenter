package com.ciicsh.gto.afsupportcenter.housefund.fundservice.dao;

import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.*;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.HfEmpTask;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 雇员任务单总表 Mapper 接口
 * </p>
 */
public interface HfEmpTaskMapper extends BaseMapper<HfEmpTask> {

    /**
     * 查询雇员任务单信息
     * @param hfEmpTaskBo
     * @return
     */
    List<HfEmpTaskExportBo> queryHfEmpTask(HfEmpTaskBo hfEmpTaskBo);

    /**
     * 查询批退雇员任务单信息
     * @param hfEmpTaskBo
     * @return
     */
    List<HfEmpTaskRejectExportBo> queryHfEmpTaskReject(HfEmpTaskBo hfEmpTaskBo);

    /**
     * 查询雇员任务单办理信息
     * @param hfEmpTaskHandleBo
     * @return
     */
    List<HfEmpTaskHandleBo> getEmpTaskHandleData(HfEmpTaskHandlePostBo hfEmpTaskHandleBo);
    /**
     * 查询任务单信息
     *
     * @param hfEmpTask
     */
    List<HfEmpTask> queryByTaskId(HfEmpTask hfEmpTask);

    /**
     * 保存雇员公积金任务单
     * @param hfEmpTask
     * @return
     */
    int insertHfEmpTask(HfEmpTask hfEmpTask);

    /**
     *  根据现有的任务单创建转移任务单
     *  @param hfEmpTaskCreateTransBo
     * @return
     */
    int createTransEmpTask(HfEmpTaskCreateTransBo hfEmpTaskCreateTransBo);
}
