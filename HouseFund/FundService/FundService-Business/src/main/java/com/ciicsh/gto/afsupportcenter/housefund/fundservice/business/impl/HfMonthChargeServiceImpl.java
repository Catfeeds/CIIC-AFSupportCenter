package com.ciicsh.gto.afsupportcenter.housefund.fundservice.business.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.*;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.business.HfMonthChargeService;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.dao.HfMonthChargeMapper;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.HfMonthCharge;
import com.ciicsh.gto.afsupportcenter.util.CalculateSocialUtils;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageKit;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class HfMonthChargeServiceImpl extends ServiceImpl<HfMonthChargeMapper, HfMonthCharge> implements HfMonthChargeService {

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuuMM");

    @Override
    public int updateHfMonthCharge(HfMonthChargeBo hfMonthChargeBo) {
        return baseMapper.updateHfMonthCharge(hfMonthChargeBo);
    }

    @Override
    public int deleteHfMonthCharges(List<Long> empTaskIdList) {
        return baseMapper.deleteHfMonthCharges(empTaskIdList);
    }

    @Override
    public HfMonthChargeDiffBo getHfMonthChargeDiffSum(HfMonthChargeBo hfMonthChargeBo) {
        return baseMapper.getHfMonthChargeDiffSum(hfMonthChargeBo);
    }

    @Override
    public PageRows<HFMonthChargeReportBO> queryHfMonthChargeReport(PageInfo pageInfo) {
        HFMonthChargeQueryBO hfMonthChargeQueryBO = pageInfo.toJavaObject(HFMonthChargeQueryBO.class);
        return PageKit.doSelectPage(pageInfo, () -> baseMapper.queryHfMonthChargeReport(hfMonthChargeQueryBO));
    }

    /**
     * 整理补缴清册数据
     *
     * @param hfMonthChargeReportBOList 雇员每月汇缴明细数据列表
     * @param weightRoundType 权重进位方式
     * @return 补缴清册数据列表
     */
    private List<HFMonthChargeRepairDetailBO> getRepairDetailList(List<HFMonthChargeReportBO> hfMonthChargeReportBOList, int weightRoundType) {
        List<HFMonthChargeRepairDetailBO> hfMonthChargeRepairDetailBOList = new ArrayList<>();
        // 添加一个临时非正式HFMonthChargeReportBO对象，方便处理最后的正式数据
        HFMonthChargeReportBO tempHfMonthChargeReportBO = new HFMonthChargeReportBO();
        tempHfMonthChargeReportBO.setEmployeeId("");
        hfMonthChargeReportBOList.add(tempHfMonthChargeReportBO);

        HFMonthChargeReportBO hfMonthChargeReportBO = hfMonthChargeReportBOList.get(0);
        HFMonthChargeRepairDetailBO hfMonthChargeRepairDetailBO = new HFMonthChargeRepairDetailBO();
        hfMonthChargeRepairDetailBO.setEmployeeId(hfMonthChargeReportBO.getEmployeeId());
        hfMonthChargeRepairDetailBO.setEmployeeName(hfMonthChargeReportBO.getEmployeeName());
        hfMonthChargeRepairDetailBO.setIdNum(hfMonthChargeReportBO.getIdNum());
        hfMonthChargeRepairDetailBO.setRepairReason(hfMonthChargeReportBO.getRepairReason());
        hfMonthChargeRepairDetailBO.setStartMonth(hfMonthChargeReportBO.getSsMonthBelong());
        hfMonthChargeRepairDetailBO.setEndMonth(hfMonthChargeReportBO.getSsMonthBelong());
        hfMonthChargeRepairDetailBO.setAmount(hfMonthChargeReportBO.getAmount());
        hfMonthChargeRepairDetailBO.setRatio(hfMonthChargeReportBO.getRatio());
        hfMonthChargeRepairDetailBO.setHfEmpAccount(hfMonthChargeReportBO.getHfEmpAccount());
        hfMonthChargeRepairDetailBO.setMonths(1);
        hfMonthChargeRepairDetailBOList.add(hfMonthChargeRepairDetailBO);

        BigDecimal totalAmount = BigDecimal.ZERO;
        for (int i = 1; i < hfMonthChargeReportBOList.size(); i++) {
            hfMonthChargeRepairDetailBO = hfMonthChargeRepairDetailBOList.get(hfMonthChargeRepairDetailBOList.size() - 1);
            YearMonth detailEndMonth = YearMonth.parse(hfMonthChargeRepairDetailBO.getEndMonth(), formatter);
            hfMonthChargeReportBO = hfMonthChargeReportBOList.get(i);
            YearMonth ssMonthBelong = YearMonth.parse(hfMonthChargeReportBO.getSsMonthBelong(), formatter);

            if (hfMonthChargeRepairDetailBO.getEmployeeId().equals(hfMonthChargeReportBO.getEmployeeId())) {
                if (hfMonthChargeRepairDetailBO.getRepairReason().equals(hfMonthChargeReportBO.getRepairReason())
                    && hfMonthChargeRepairDetailBO.getRatio().equals(hfMonthChargeReportBO.getRatio())
                    && hfMonthChargeRepairDetailBO.getAmount().equals(hfMonthChargeReportBO.getAmount())
                    && detailEndMonth.plusMonths(1).equals(ssMonthBelong)) {
                    hfMonthChargeRepairDetailBO.setEndMonth(hfMonthChargeReportBO.getSsMonthBelong());
                    hfMonthChargeRepairDetailBO.plusOneMonth();
                } else {
                    hfMonthChargeRepairDetailBO.setSubTotalAmount(
                        CalculateSocialUtils.calculateByRoundType(
                            hfMonthChargeRepairDetailBO.getAmount()
                                .multiply(BigDecimal.valueOf(hfMonthChargeRepairDetailBO.getMonths()))
                            , weightRoundType));

                    HFMonthChargeRepairDetailBO newHFMonthChargeRepairDetailBO = new HFMonthChargeRepairDetailBO();
                    newHFMonthChargeRepairDetailBO.setEmployeeId(hfMonthChargeReportBO.getEmployeeId());
                    newHFMonthChargeRepairDetailBO.setEmployeeName(hfMonthChargeReportBO.getEmployeeName());
                    newHFMonthChargeRepairDetailBO.setIdNum(hfMonthChargeReportBO.getIdNum());
                    newHFMonthChargeRepairDetailBO.setRepairReason(hfMonthChargeReportBO.getRepairReason());
                    newHFMonthChargeRepairDetailBO.setStartMonth(hfMonthChargeReportBO.getSsMonthBelong());
                    newHFMonthChargeRepairDetailBO.setEndMonth(hfMonthChargeReportBO.getSsMonthBelong());
                    newHFMonthChargeRepairDetailBO.setAmount(hfMonthChargeReportBO.getAmount());
                    newHFMonthChargeRepairDetailBO.setRatio(hfMonthChargeReportBO.getRatio());
                    newHFMonthChargeRepairDetailBO.setHfEmpAccount(hfMonthChargeReportBO.getHfEmpAccount());
                    newHFMonthChargeRepairDetailBO.setMonths(1);
                    hfMonthChargeRepairDetailBOList.add(newHFMonthChargeRepairDetailBO);
                }
                totalAmount.add(hfMonthChargeRepairDetailBO.getSubTotalAmount());
            } else {
                hfMonthChargeRepairDetailBO.setSubTotalAmount(
                    CalculateSocialUtils.calculateByRoundType(
                        hfMonthChargeRepairDetailBO.getAmount()
                            .multiply(BigDecimal.valueOf(hfMonthChargeRepairDetailBO.getMonths()))
                        , weightRoundType));
                String employeeId = hfMonthChargeRepairDetailBO.getEmployeeId();
                totalAmount.add(hfMonthChargeRepairDetailBO.getSubTotalAmount());

                final BigDecimal tempTotalAmount = totalAmount;
                hfMonthChargeRepairDetailBOList.stream().filter(e -> e.getEmployeeId().equals(employeeId)).forEach(
                    e -> e.setTotalAmount(CalculateSocialUtils.calculateByRoundType(tempTotalAmount, weightRoundType))
                );
                totalAmount = BigDecimal.ZERO;

                HFMonthChargeRepairDetailBO newHFMonthChargeRepairDetailBO = new HFMonthChargeRepairDetailBO();
                newHFMonthChargeRepairDetailBO.setEmployeeId(hfMonthChargeReportBO.getEmployeeId());
                newHFMonthChargeRepairDetailBO.setEmployeeName(hfMonthChargeReportBO.getEmployeeName());
                newHFMonthChargeRepairDetailBO.setIdNum(hfMonthChargeReportBO.getIdNum());
                newHFMonthChargeRepairDetailBO.setRepairReason(hfMonthChargeReportBO.getRepairReason());
                newHFMonthChargeRepairDetailBO.setStartMonth(hfMonthChargeReportBO.getSsMonthBelong());
                newHFMonthChargeRepairDetailBO.setEndMonth(hfMonthChargeReportBO.getSsMonthBelong());
                newHFMonthChargeRepairDetailBO.setAmount(hfMonthChargeReportBO.getAmount());
                newHFMonthChargeRepairDetailBO.setRatio(hfMonthChargeReportBO.getRatio());
                newHFMonthChargeRepairDetailBO.setHfEmpAccount(hfMonthChargeReportBO.getHfEmpAccount());
                newHFMonthChargeRepairDetailBO.setMonths(1);
                hfMonthChargeRepairDetailBOList.add(newHFMonthChargeRepairDetailBO);
            }
        }

        // 去除最后一个临时的对象
        hfMonthChargeRepairDetailBOList.remove(hfMonthChargeRepairDetailBOList.size() - 1);
        return  hfMonthChargeRepairDetailBOList;
    }
}
