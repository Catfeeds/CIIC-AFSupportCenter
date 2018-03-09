package com.ciicsh.gto.afsupportcenter.housefund.fundservice.business.impl;

import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.HFStatementCompareBO;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.business.HFStatementCompareService;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.dao.HfStatementCompareMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 对账单业务接口实现类
 */
@Service
public class HFStatementCompareServiceImpl implements HFStatementCompareService
{

    @Autowired
    private HfStatementCompareMapper baseMapper;

    /**
     * 根据筛选条件获取公积金对账单记录
     *
     * @param hfMonth
     * @param hfComAccount
     * @return
     */
    @Override
    public List<HFStatementCompareBO> getHFStatementCompareRecord(String hfMonth, String hfComAccount) {
        return baseMapper.getHFStatementCompareRecord(hfMonth,hfComAccount);
    }
}
