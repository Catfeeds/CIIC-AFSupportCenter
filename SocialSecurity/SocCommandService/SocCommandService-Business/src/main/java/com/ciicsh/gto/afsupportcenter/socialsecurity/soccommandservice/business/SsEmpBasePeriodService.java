package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.business;

import com.baomidou.mybatisplus.service.IService;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.bo.SsEmpTaskBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.SsEmpBasePeriod;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 雇员正常汇缴社保的基数分段表(每段一个基数)， 每次社保基数变更（比如年调）或补缴都会更新这张表 服务类
 * </p>
 *
 * @author HuangXing
 * @since 2017-12-01
 */
public interface SsEmpBasePeriodService extends IService<SsEmpBasePeriod> {

    /**
     * 根据雇员任务 保存数据
     *
     * @param periods
     * @param empTaskId
     */
    void saveForEmpTaskId(List<SsEmpBasePeriod> periods, Long empTaskId);

    /**
     * 删除，根据雇员任务 ID
     *
     * @param empTaskId
     * @return
     */
    void deleteByEmpTaskId(Long empTaskId);

    /**
     * 修改和添加 调整办理的时间段
     * @param ssEmpBasePeriod
     * @param newEmpBasePeriodList
     */
    void saveAdjustmentPeriod(SsEmpBasePeriod ssEmpBasePeriod,List<SsEmpBasePeriod> newEmpBasePeriodList);
    /**
     * 通过社保档案ID 查询 雇员汇缴信息
     * @param empArchiveId
     * @return
     */
    List<SsEmpBasePeriod> queryPeriodByEmpArchiveId(@Param("empArchiveId")String empArchiveId);

    void updateEndMonthById(SsEmpBasePeriod ssEmpBasePeriod);

    /**
     * 补缴添加时间段到表中
     * @param newEmpBasePeriodList
     */
    void saveBackPeriod(List<SsEmpBasePeriod> newEmpBasePeriodList);

    /**
     * 转出时 将社保停缴月份和截止月份修改
     * @param ssEmpBasePeriod
     */
    void updateEndMonAndHandleMon(SsEmpBasePeriod ssEmpBasePeriod);

    /**
     * 还原转出操作时的修改
     * @param ssEmpBasePeriod
     */
    Integer updateReductionById(SsEmpBasePeriod ssEmpBasePeriod);
}
