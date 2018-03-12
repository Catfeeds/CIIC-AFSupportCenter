package com.ciicsh.gto.afsupportcenter.housefund.fundservice.business.impl;

import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.HFStatementCompareBO;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.business.HFStatementCompareService;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.dao.HfStatementCompareMapper;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.dto.FundStatementDetailDTO;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.dto.FundStatementItemDTO;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.dto.NewStatementDTO;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.FundStatementDetailPO;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.FundStatementItemPO;
import com.ciicsh.gto.afsupportcenter.util.kit.JsonKit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import static java.util.stream.Collectors.groupingBy;

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

    /**
     * 添加新的对账
     *
     * @param filePath
     * @return
     */
    @Override
    public void addStatement(String filePath) {
        //TODO:
    }

    /**
     * 获取对账单明细记录
     *
     * @param statementId
     * @return
     */
    @Override
    public FundStatementDetailDTO getStatementDetail(long statementId) {
        FundStatementDetailDTO result = new FundStatementDetailDTO();
        List<FundStatementItemPO> lst = baseMapper.getStatementItems(statementId);
        Map<FundStatementDetailPO, List<FundStatementItemPO>> map = lst.stream().collect(groupingBy(FundStatementItemPO::toKey));
        for(Entry<FundStatementDetailPO, List<FundStatementItemPO>> entry: map.entrySet()){
            result.setStatementId(entry.getKey().getStatementId());
            result.setComAccountName(entry.getKey().getComAccountName());
            result.setDiffCount((entry.getKey().getDiffCount()));
            result.setHfMonth(entry.getKey().getHfMonth());
            result.setImpRecordCount(entry.getKey().getImpRecordCount());
            result.setItems(JsonKit.castToList(entry.getValue(), FundStatementItemDTO.class));
        } //for

        return result;
    }

    /**
     * 删除对账单
     *
     * @param statementId
     * @return
     */
    @Override
    public int delStatement(long statementId) {
        return baseMapper.delStatement(statementId);
    }

    /**
     * 执行对账
     *
     * @param statementId
     * @return
     */
    @Override
    public boolean execStatementDetail(long statementId) {
        return false;
    }
}
