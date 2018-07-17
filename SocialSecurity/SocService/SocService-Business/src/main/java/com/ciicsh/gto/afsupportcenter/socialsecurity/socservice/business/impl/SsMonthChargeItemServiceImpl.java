package com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business.impl;

import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo.SsEmpLastPaymentBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo.SsMonthChargeItemBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.SsMonthChargeItem;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.dao.SsMonthChargeItemMapper;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business.SsMonthChargeItemService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * <p>
 * 雇员月度费用明细项目 服务实现类
 * </p>
 *
 * @author HuangXing
 * @since 2017-12-23
 */
@Service
public class SsMonthChargeItemServiceImpl extends ServiceImpl<SsMonthChargeItemMapper, SsMonthChargeItem> implements SsMonthChargeItemService {

    @Override
    public List<SsMonthChargeItemBO> queryEmlpyeeMonthFeeDetail(SsMonthChargeItemBO ssMonthChargeItemBO) {
        List<SsMonthChargeItemBO> ssMonthChargeItemBOList =baseMapper.queryEmlpyeeMonthFeeDetail(ssMonthChargeItemBO);
         return ssMonthChargeItemBOList;
    }

    @Override
    public List<SsEmpLastPaymentBO> searchDetailLastPaymentMonth(String companyId, String employeeId, String ssMonth) {
        return baseMapper.searchDetailLastPaymentMonth(companyId, employeeId, ssMonth);
    }

}
