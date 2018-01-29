package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.business.impl;

import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.business.SsAnnualAdjustEmployeeService;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dao.SsAnnualAdjustEmployeeMapper;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dto.SsAnnualAdjustEmployeeDTO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.SsAnnualAdjustEmployee;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageKit;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  雇员社保年调表查询服务实现类
 * </p>
 */
@Service
public class SsAnnualAdjustEmployeeServiceImpl extends ServiceImpl<SsAnnualAdjustEmployeeMapper, SsAnnualAdjustEmployee> implements SsAnnualAdjustEmployeeService {

    @Override
    public List<SsAnnualAdjustEmployee> queryAnnualAdjustEmployee(SsAnnualAdjustEmployeeDTO ssAnnualAdjustEmployeeDTO) {
        return baseMapper.queryAnnualAdjustEmployee(ssAnnualAdjustEmployeeDTO);
    }

    @Override
    public PageRows<SsAnnualAdjustEmployee> queryAnnualAdjustEmployeeInPage(PageInfo pageInfo) {
        SsAnnualAdjustEmployeeDTO ssAnnualAdjustEmployeeDTO = pageInfo.toJavaObject(SsAnnualAdjustEmployeeDTO.class);
        return PageKit.doSelectPage(pageInfo, () -> baseMapper.queryAnnualAdjustEmployee(ssAnnualAdjustEmployeeDTO));
    }
}
