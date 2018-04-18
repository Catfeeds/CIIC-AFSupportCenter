package com.ciicsh.gto.afsupportcenter.housefund.fundservice.business.impl;

import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.HFStatementCompareBO;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.business.HFStatementCompareService;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.dao.HfStatementCompareImpMapper;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.dao.HfStatementCompareMapper;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.dao.HfStatementCompareResultMapper;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.dto.FundStatementDetailDTO;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.dto.FundStatementItemDTO;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.dto.NewStatementDTO;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.dto.NewStatementExcelItemDTO;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.EmployeeIdPO;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.EmployeeSysAmountPO;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.FundStatementDetailPO;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.FundStatementItemPO;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.HfStatementCompareImpPO;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.HfStatementComparePO;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.HfStatementCompareResultPO;
import com.ciicsh.gto.afsupportcenter.util.ExcelUtil;
import com.ciicsh.gto.afsupportcenter.util.StringUtil;
import com.ciicsh.gto.afsupportcenter.util.interceptor.authenticate.UserContext;
import com.ciicsh.gto.afsupportcenter.util.kit.JsonKit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

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

    @Autowired
    private HfStatementCompareResultMapper statementResultMapper;


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
    public long addStatement(NewStatementDTO newStatement, MultipartFile file) throws Exception {
        List<NewStatementExcelItemDTO> lst = ExcelUtil.importExcel(file,0,1,NewStatementExcelItemDTO.class,true);
        if(lst == null) {
            throw new Exception("文件记录读取为空");
        }
        long statementId = 0;

        HfStatementComparePO po = new HfStatementComparePO();
        po.setHfMonth(newStatement.getHfMonth());
        po.setHfType(newStatement.getHfType());
        po.setComAccountId(newStatement.getComAccountId());
        po.setHfAccountType(newStatement.getHfAccountType());
        po.setImpPath(file.getOriginalFilename());
        po.setImpRecordCount(lst.size());
        po.setActive(true);
        po.setCreatedBy(UserContext.getUser().getDisplayName());
        po.setModifiedBy(UserContext.getUser().getDisplayName());

        baseMapper.insert(po);
        statementId = po.getStatementCompareId();
        for(NewStatementExcelItemDTO item : lst) {
            HfStatementCompareImpPO po2 = new HfStatementCompareImpPO();
            po2.setStatementCompareId(statementId);
            po2.setComAccount(newStatement.getHfComAccount());
            po2.setEmpAccount(item.getPersonalAccount());
            po2.setEmpName(item.getEmpName());
            po2.setEmpCardNum(item.getIdNum());
            po2.setEmployeeId(getEmployeeId(item.getEmpName(),item.getIdNum(),item.getPersonalAccount()));
            po2.setMonthlyAmount(item.getMonthlyAmount());
            po2.setActive(true);
            po2.setCreatedBy(newStatement.getCreatedBy());
            po2.setModifiedBy(newStatement.getCreatedBy());
            statementImpMapper.insert(po2);
        } //for

        return statementId;

    }


    private String getEmployeeId(String employeeName,String idNum,String empAccount){
        if(!StringUtil.empty(employeeName) && !StringUtil.empty(idNum) && !StringUtil.empty(empAccount)){
            EmployeeIdPO employeeIdPO = baseMapper.getEmployeeIdFromArchive(employeeName,idNum,empAccount);
            return null != employeeIdPO ? employeeIdPO.getEmployeeId() : UUID.randomUUID().toString();
        }
        else{
            return UUID.randomUUID().toString();
        }
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
    public void execStatement(long statementId) throws Exception{

        // 取出导入的对账单明细条目数据
        try {
            Map<String, Object> condition = new HashMap<>(1);
            condition.put("statement_compare_id", statementId);
            List<HfStatementCompareImpPO> compareImpPOList = statementImpMapper.selectByMap(condition);
            baseMapper.delStatementResult(statementId);
            HfStatementComparePO statementPO = baseMapper.selectById(statementId);
            if(statementPO == null) {
                throw new Exception("对账单记录不存在");
            }

            Map<String,EmployeeSysAmountPO> empSysAmountMap = baseMapper.getEmployeeSysAmount(statementPO.getHfType(),statementPO.getComAccountId(),statementPO.getHfMonth());
            int diffCount = 0;

            for (HfStatementCompareImpPO impPO : compareImpPOList) {
                HfStatementCompareResultPO resultPO = new HfStatementCompareResultPO();
                resultPO.setStatementCompareId(statementId);
                resultPO.setImpAmount(impPO.getMonthlyAmount());
                resultPO.setActive(true);
                resultPO.setCreatedTime(LocalDateTime.now());
                resultPO.setCreatedBy(UserContext.getUserId());
                resultPO.setModifiedTime(LocalDateTime.now());
                resultPO.setModifiedBy(UserContext.getUserId());
                resultPO.setEmployeeId(impPO.getEmployeeId());

                if(null != impPO.getEmployeeId()){
                    EmployeeSysAmountPO sysAmountPO = empSysAmountMap.get(impPO.getEmployeeId());
                    resultPO.setSysAmount(sysAmountPO != null ? sysAmountPO.getSysAmount() : BigDecimal.ZERO);
                }
                else {
                    resultPO.setSysAmount(BigDecimal.ZERO);
                }

                BigDecimal diffAmount = resultPO.getSysAmount().subtract(resultPO.getImpAmount());
                resultPO.setDiffAmount(diffAmount);
                if(diffAmount.compareTo(BigDecimal.ZERO) != 0) {
                    diffCount ++;
                }
                statementResultMapper.insert(resultPO);
            } //for
            statementPO.setDiffCount(diffCount);
            statementPO.setCompareOperateId(UserContext.getUserId());
            statementPO.setCompareOperateName(UserContext.getUser().getDisplayName());
            statementPO.setCompareTime(LocalDateTime.now());
            statementPO.setModifiedBy(UserContext.getUserId());
            statementPO.setModifiedTime(LocalDateTime.now());
            baseMapper.updateById(statementPO);

        }
        catch (Exception e){
            throw new Exception("对账异常");
        }

    }
}
