package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.business.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.business.ISsEmpTaskPeriodService;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dao.SsEmpTaskPeriodMapper;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.SsEmpTask;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.SsEmpTaskPeriod;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 任务单费用段表,记录任务单历史办理记录,不受将来的调整影响
 * 从雇员社保详细中跳转的任务表单,应从该表获取数据 服务实现类
 * </p>
 *
 * @author HuangXing
 * @since 2017-12-01
 */
@Service
public class SsEmpTaskPeriodServiceImpl extends ServiceImpl<SsEmpTaskPeriodMapper, SsEmpTaskPeriod> implements ISsEmpTaskPeriodService {

    /**
     * 查询任务单费用段，根据雇员任务 id
     *
     * @param empTaskId
     * @return
     */
    @Override
    public List<SsEmpTaskPeriod> queryByEmpTaskId(Long empTaskId) {
        EntityWrapper<SsEmpTaskPeriod> wrapper = new EntityWrapper<>();
        SsEmpTaskPeriod period = new SsEmpTaskPeriod();
        period.setEmpTaskId(empTaskId);
        period.setActive(true);
        wrapper.setEntity(period);
        return baseMapper.selectList(wrapper);
    }

    @Transactional
    @Override
    public void saveForEmpTask(List<SsEmpTaskPeriod> periods, SsEmpTask empTask) {
        SsEmpTaskPeriod period = new SsEmpTaskPeriod();
        period.setEmpTaskId(empTask.getEmpTaskId());
        // 删除 old
        baseMapper.delete(new EntityWrapper(period));
        // 保存 new
        this.cleanPk(periods);
        this.insertBatch(periods);
    }

    /**
     * 清楚主键，方便更新
     *
     * @param periods
     */
    private void cleanPk(List<SsEmpTaskPeriod> periods) {
        periods.forEach(period -> period.setEmpTaskPeriodId(null));
    }
}
