package com.ciicsh.gto.afsupportcenter.housefund.fundservice.dao;

import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.*;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.transfer.EmpTaskTransferBo;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.HfEmpTask;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 雇员任务单总表 Mapper 接口
 * </p>
 */
@Mapper
@Component
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

    /**
     *  查询雇员转移任务单
     *  @param empTaskTransferBo
     * @return
     */
    List<EmpTaskTransferBo> queryEmpTaskTransfer(EmpTaskTransferBo empTaskTransferBo);

    /**
     *  查询雇员转移任务单新增任务单雇员查询
     *  @param empTaskTransferBo
     * @return
     */
    List<EmpTaskTransferBo> queryEmpTaskTransferNewTask(EmpTaskTransferBo empTaskTransferBo);

    List<Map<String,String>> fetchPrintInfo(Long empTaskId);

    Integer getExistHandleRemarkCount(HfEmpTaskBo hfEmpTaskBo);

    List<HfEmpTaskExportBo> queryHistoryEmpTask(@Param("isSelf") Boolean isSelf, @Param("empTaskId") Long empTaskId);

    List<HfEmpTaskExportBo> getOriginEmpTask(@Param("companyId") String companyId, @Param("employeeId") String employeeId, @Param("hfType") Integer hfType, @Param("empTaskId") Long empTaskId);

    List<String> getEmpTransferEndMonth(EmpTaskTransferBo empTaskTransferBo);

    List<EmpTaskTransferBo> getEmpTaskTransferByEmpCompanyId(@Param("empCompanyId") Long empCompanyId, @Param("hfType") Integer hfType);

    EmpTaskDetailBO getEmpTaskDetailByTaskId(@Param("taskId") String taskId);
}
