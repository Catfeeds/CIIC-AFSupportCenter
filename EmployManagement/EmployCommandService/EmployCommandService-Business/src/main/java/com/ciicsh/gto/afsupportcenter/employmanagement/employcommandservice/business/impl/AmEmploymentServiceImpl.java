package com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.business.impl;

import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.bo.AmEmploymentBO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.entity.AmEmployment;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.dao.AmEmploymentMapper;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.business.IAmEmploymentService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageKit;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用工主表 服务实现类
 * </p>
 */
@Service
public class AmEmploymentServiceImpl extends ServiceImpl<AmEmploymentMapper, AmEmployment> implements IAmEmploymentService {
    @Override
    public PageRows<AmEmploymentBO> queryAmEmployment(PageInfo pageInfo) {
        AmEmploymentBO amEmploymentBO = pageInfo.toJavaObject(AmEmploymentBO.class);

        return PageKit.doSelectPage(pageInfo,() -> baseMapper.queryAmEmployment(amEmploymentBO));
    }

    @Override
    public AmEmploymentBO queryAmEmployment(String amEmploymentId) {
        return baseMapper.queryAmEmploymentById(amEmploymentId);
    }
}
