package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.business;

import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.SsEmpTask;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.SsEmpTaskPeriod;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 * 任务单费用段表,记录任务单历史办理记录,不受将来的调整影响
 * 从雇员社保详细中跳转的任务表单,应从该表获取数据 服务类
 * </p>
 *
 * @author HuangXing
 * @since 2017-12-01
 */
public interface ISsEmpTaskPeriodService extends IService<SsEmpTaskPeriod> {

    /**
     * 查询任务单费用段，根据雇员任务 id
     *
     * @param empTaskId
     * @return
     */
    List<SsEmpTaskPeriod> queryByEmpTaskId(Long empTaskId);

    /**
     * 根据雇员任务 保存数据
     * @param periods
     * @param empTaskId
     */
    void saveForEmpTaskId(List<SsEmpTaskPeriod> periods, Long empTaskId);
}
