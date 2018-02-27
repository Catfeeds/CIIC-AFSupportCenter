package com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business;

import com.baomidou.mybatisplus.service.IService;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo.SsEmpTaskBO;
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
    PageRows<SsEmpTaskBO> employeeOperatorQuery(PageInfo pageInfo);
  //  List<SsEmpTaskBO> empOperatorQueryExport(SsEmpTaskBO ssEmpTaskBO);
    /**
     * 雇员日常操作查询(盘片转入转出)
     * @param pageInfo
     * @param isRollIn
     * @return
     */
    <T> PageRows<T> employeeDailyOperatorQueryForDisk(PageInfo pageInfo, boolean isRollIn);

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
    boolean saveHandleData(SsEmpTaskBO bo);

    /**
     * 查询最大社保序号
     * @param empTaskId
     * @return
     */
    String selectMaxSsSerialByTaskId(Long empTaskId);

//    /**
//     * 调用客服中心的完成任务接口
//     *
//     * @param taskSheetRequestDTO
//     * @return
//     */
//    com.ciicsh.gto.commonservice.util.bo.Result completeTask(@RequestBody TaskSheetRequestDTO taskSheetRequestDTO) throws Exception;
    /**
     * 批量查询任务单信息
     * @param ssEmpTaskBO
     */
    List<SsEmpTaskBO> queryBatchEmpArchiveByEmpTaskIds(SsEmpTaskBO ssEmpTaskBO);

    /**
     * 查询任务单信息
     * @param ssEmpTaskBO
     */
    List<SsEmpTaskBO> queryByTaskId(SsEmpTaskBO ssEmpTaskBO);

}
