package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.bo.SsEmpTaskBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.SsEmpTask;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 本地社保的雇员任务单 Mapper 接口
 * </p>
 *
 * @author HuangXing
 * @since 2017-12-01
 */
public interface SsEmpTaskMapper extends BaseMapper<SsEmpTask> {

    /**
     * 雇员日常操作查询
     *
     * @param ssComTaskDTO
     * @return
     */
    List<SsEmpTaskBO> employeeDailyOperatorQuery(SsEmpTaskBO ssComTaskDTO);
    /**
     * 雇员特殊操作查询
     *
     * @param ssComTaskDTO
     * @return
     */
    List<SsEmpTaskBO> employeeSpecialOperatorQuery(SsEmpTaskBO ssComTaskDTO);

    /**
     * 通过社保档案ID 查询历史任务单
     * @param empArchiveId
     * @return
     */
    List<SsEmpTask> queryTaskByEmpArchiveId(@Param("empArchiveId")String empArchiveId);

    /**
     * 通过ID修改 修改某些字段 不需要判断是否为空
     * @param ssComTaskDTO
     * @return
     */
    Integer updateMyselfColumnById(SsEmpTaskBO ssComTaskDTO);

    String selectMaxSsSerialByTaskId(@Param("empTaskId") Long empTaskId);
}
