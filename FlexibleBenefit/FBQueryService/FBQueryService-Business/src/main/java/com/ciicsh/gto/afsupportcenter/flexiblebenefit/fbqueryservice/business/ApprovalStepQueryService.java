package com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbqueryservice.business;

import com.baomidou.mybatisplus.service.IService;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.entity.po.ApprovalStepPO;

import java.util.List;

/**
 * <p>
 * 审批步骤记录表 服务类
 * </p>
 *
 * @author xiweizhen
 */
public interface ApprovalStepQueryService extends IService<ApprovalStepPO> {

    /**
     * 查询集合
     *
     * @param approvalStep
     * @return
     */
    List<ApprovalStepPO> selectApprovalStepList(ApprovalStepPO approvalStep);
}
