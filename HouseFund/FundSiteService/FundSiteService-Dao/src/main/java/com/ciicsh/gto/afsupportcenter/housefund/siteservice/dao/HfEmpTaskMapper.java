package com.ciicsh.gto.afsupportcenter.housefund.siteservice.dao;

import com.ciicsh.gto.afsupportcenter.housefund.siteservice.bo.HfEmpTaskBo;
import com.ciicsh.gto.afsupportcenter.housefund.siteservice.dto.HfEmpTaskDTO;
import com.ciicsh.gto.afsupportcenter.housefund.siteservice.entity.HfEmpTask;
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
     * @param hfEmpTaskDTO
     * @return
     */
    List<HfEmpTaskBo> queryHfEmpTask(HfEmpTaskDTO hfEmpTaskDTO);
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
}
