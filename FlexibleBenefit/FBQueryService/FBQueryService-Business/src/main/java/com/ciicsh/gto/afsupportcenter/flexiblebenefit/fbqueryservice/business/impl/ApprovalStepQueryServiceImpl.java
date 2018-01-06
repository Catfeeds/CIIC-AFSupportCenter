package com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbqueryservice.business.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.entity.po.ApprovalStepPO;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbqueryservice.business.ApprovalStepQueryService;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbqueryservice.dao.ApprovalStepQueryMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 审批步骤记录表 服务实现类
 * </p>
 *
 * @author xiweizhen
 */
@Service
public class ApprovalStepQueryServiceImpl extends ServiceImpl<ApprovalStepQueryMapper, ApprovalStepPO> implements ApprovalStepQueryService {

    @Override
    public List<ApprovalStepPO> selectApprovalStepList(ApprovalStepPO approvalStep) {
        EntityWrapper<ApprovalStepPO> entityWrapper = new EntityWrapper<>();
        entityWrapper.where(approvalStep.getApplyRecordDetailId() != null, "apply_record_detail_id={0}", approvalStep.getApplyRecordDetailId());
        return baseMapper.selectList(entityWrapper);
    }
}
