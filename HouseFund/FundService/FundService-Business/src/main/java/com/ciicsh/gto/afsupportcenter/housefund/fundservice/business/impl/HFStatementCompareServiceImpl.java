package com.ciicsh.gto.afsupportcenter.housefund.fundservice.business.impl;

import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.HFStatementCompareBO;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.business.HFStatementCompareService;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.dao.HfStatementCompareImpMapper;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.dao.HfStatementCompareMapper;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.dto.FundStatementDetailDTO;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.dto.FundStatementItemDTO;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.dto.NewStatementDTO;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.dto.NewStatementExcelItemDTO;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.FundStatementDetailPO;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.FundStatementItemPO;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.HfStatementCompareImpPO;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.HfStatementComparePO;
import com.ciicsh.gto.afsupportcenter.util.ExcelUtil;
import com.ciicsh.gto.afsupportcenter.util.kit.JsonKit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import static java.util.stream.Collectors.groupingBy;

/**
 * 对账单业务接口实现类
 */
@Transactional
@Service
public class HFStatementCompareServiceImpl implements HFStatementCompareService
{

    @Autowired
    private HfStatementCompareMapper baseMapper;

    @Autowired
    private HfStatementCompareImpMapper statementImpMapper;

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
     * @param newStatement
     * @param file
     */
    @Override
    public void addStatement(NewStatementDTO newStatement, MultipartFile file) throws Exception {
        List<NewStatementExcelItemDTO> lst = ExcelUtil.importExcel(file,0,1,NewStatementExcelItemDTO.class,true);
        if(lst == null) {
            throw new Exception("文件记录读取为空");
        }
        HfStatementComparePO po = new HfStatementComparePO();
        po.setHfMonth(newStatement.getHfMonth());
        po.setHfType(newStatement.getHfType());
        po.setComAccountId(newStatement.getComAccountId());
        po.setHfAccountType(newStatement.getHfAccountType());
        po.setImpRecordCount(lst.size());
        po.setActive(true);
        po.setCreatedBy(newStatement.getCreatedBy());
        po.setModifiedBy(newStatement.getCreatedBy());


        baseMapper.insert(po);
        long statementId = po.getStatementCompareId();
        for(NewStatementExcelItemDTO item : lst) {
            HfStatementCompareImpPO po2 = new HfStatementCompareImpPO();
            po2.setStatementCompareId(statementId);
            //TODO: 取comAccount 公积金客户账号
            po2.setComAccount("");
            po2.setEmpName(item.getEmpName());
            po2.setEmpCardNum(item.getIdNum());
            po2.setMonthlyAmount(BigDecimal.valueOf(item.getMonthlyAmount()));
            po2.setActive(true);
            po2.setCreatedBy(newStatement.getCreatedBy());
            po2.setModifiedBy(newStatement.getCreatedBy());
            statementImpMapper.insert(po2);
        } //for

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
