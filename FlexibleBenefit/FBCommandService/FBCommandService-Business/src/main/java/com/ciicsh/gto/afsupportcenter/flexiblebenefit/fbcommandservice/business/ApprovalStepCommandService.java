package com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice.business;

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
public interface ApprovalStepCommandService extends IService<ApprovalStepPO> {

    /**
     * 查询集合数据
     *
     * @param id
     * @return
     */
    List<ApprovalStepPO> selectList(Integer id);
}
