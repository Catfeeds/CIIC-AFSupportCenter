package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.business.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.toolkit.CollectionUtils;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.business.ISsEmpBasePeriodService;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dao.SsEmpBaseDetailMapper;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dao.SsEmpBasePeriodMapper;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.SsEmpBaseDetail;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.SsEmpBasePeriod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 雇员正常汇缴社保的基数分段表(每段一个基数)， 每次社保基数变更（比如年调）或补缴都会更新这张表 服务实现类
 * </p>
 *
 * @author HuangXing
 * @since 2017-12-01
 */
@Service
public class SsEmpBasePeriodServiceImpl extends ServiceImpl<SsEmpBasePeriodMapper, SsEmpBasePeriod> implements ISsEmpBasePeriodService {

    @Autowired
    private SsEmpBaseDetailMapper ssEmpBaseDetailMapper;

    @Transactional
    @Override
    public void saveForEmpTaskId(List<SsEmpBasePeriod> periods, Long empTaskId) {
        SsEmpBasePeriod period = new SsEmpBasePeriod();
        period.setEmpTaskId(empTaskId);
        // 删除 old
        baseMapper.delete(new EntityWrapper(period));
        // 保存 new
        if (CollectionUtils.isNotEmpty(periods)) {
            this.insertBatch(periods);
        }
    }

    @Transactional
    @Override
    public void deleteByEmpTaskId(Long empTaskId) {
        SsEmpBasePeriod period = new SsEmpBasePeriod();
        period.setEmpTaskId(empTaskId);
        EntityWrapper wrapper = new EntityWrapper(period);
        List<SsEmpBasePeriod> periods = baseMapper.selectList(wrapper);

        periods.forEach(p -> {
            SsEmpBaseDetail detail = new SsEmpBaseDetail();
            detail.setEmpArchiveId(p.getEmpArchiveId());
            detail.setEmpBasePeriodId(p.getEmpBasePeriodId());
            ssEmpBaseDetailMapper.delete(new EntityWrapper(detail));
            baseMapper.delete(wrapper);
        });
    }

    @Override
    public List<SsEmpBasePeriod> queryPeriodByEmpArchiveId(String empArchiveId) {
        return baseMapper.queryPeriodByEmpArchiveId(empArchiveId);
    }
}
