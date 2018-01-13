package com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice.business.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.entity.po.ApprovalStepPO;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice.business.ApprovalStepCommandService;
import com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice.dao.ApprovalStepCommandMapper;
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
public class ApprovalStepCommandServiceImpl extends ServiceImpl<ApprovalStepCommandMapper, ApprovalStepPO> implements ApprovalStepCommandService {
    @Override
    public List<ApprovalStepPO> selectList(Integer id) {
        EntityWrapper<ApprovalStepPO> entityWrapper = new EntityWrapper();
        entityWrapper.where("apply_record_detail_id={0}", id);
        return baseMapper.selectList(entityWrapper);
    }
}
