package com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business;

import com.baomidou.mybatisplus.service.IService;
import com.ciicsh.common.entity.JsonResult;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo.SsEmpTaskBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.dto.SsEmpTaskArchiveDTO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.SsEmpTask;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;

import java.util.List;

/**
 * <p>
 * 本地社保的雇员任务单 服务类
 * </p>
 *
 * @author HuangXing
 * @since 2017-12-01
 */
public interface SsEmpTaskService extends IService<SsEmpTask> {

    /**
     * 雇员日常操作查询
     *
     * @param pageInfo
     * @return
     */
    PageRows<SsEmpTaskBO> employeeOperatorQuery(PageInfo pageInfo, String userId);
  //  List<SsEmpTaskBO> empOperatorQueryExport(SsEmpTaskBO ssEmpTaskBO);
    /**
     * 雇员日常操作查询(盘片转入转出)
     * @param pageInfo
     * @param isRollIn
     * @return
     */
    <T> PageRows<T> employeeDailyOperatorQueryForDisk(PageInfo pageInfo, String userId, boolean isRollIn);

    /**
     * 通过社保档案ID 查询历史任务单
     * @param empArchiveId
     * @return
     */
    List<SsEmpTask> queryTaskByEmpArchiveId(String empArchiveId);


    /**
     * 雇员日常办理操作
     * @param bo
     * @return
     */
    String saveHandleData(SsEmpTaskBO bo,boolean isBatch);

    /**
     * 查询最大社保序号
     * @param empTaskId
     * @return
     */
    @Deprecated
    String selectMaxSsSerialByTaskId(Long empTaskId);

    /**
     * 获得社保序号
     * @param comAccountId
     * @return
     */
    Long getSerial(Long comAccountId);

    /**
     * 批量查询任务单信息
     * @param ssEmpTaskBO
     */
    List<SsEmpTaskBO> queryBatchEmpArchiveByEmpTaskIds(SsEmpTaskBO ssEmpTaskBO);

    /**
     * 通过条件查询批量任务信息
     * @param ssEmpTaskBO
     * @return
     */
    List<SsEmpTaskBO> queryBatchTaskByCondition(SsEmpTaskBO ssEmpTaskBO);

    /**
     * 查询任务单信息
     * @param ssEmpTaskBO
     */
    List<SsEmpTaskBO> queryByTaskId(SsEmpTaskBO ssEmpTaskBO);

    /**
     * 查询任务单信息
     * @param ssEmpTaskBO
     */
    List<SsEmpTaskBO> queryByBusinessInterfaceId(SsEmpTaskBO ssEmpTaskBO);

    SsEmpTaskBO selectIdNumByEmployeeId(String employeeId);

    List<SsEmpTask> queryEmpTaskById(Long empTaskId, String userId);

    Boolean batchRejection(List<Long> task,String remark, String userId, String userName);

    boolean autoOffset(String companyId, String employeeId, Integer offsetType);

     SsEmpTaskArchiveDTO apiGetSsEmpTaskByTaskId(String taskId);
}
