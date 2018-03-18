package com.ciicsh.gto.afsupportcenter.housefund.fundservice.business.impl;

import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.toolkit.CollectionUtils;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.*;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.customer.ComAccountExtBo;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.customer.ComAccountParamExtBo;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.business.HfComAccountClassService;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.business.HfComAccountService;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.business.HfMonthChargeService;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.constant.HfMonthChargeConstant;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.dao.HfMonthChargeMapper;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.HfComAccountClass;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.HfMonthCharge;
import com.ciicsh.gto.afsupportcenter.util.CalculateSocialUtils;
import com.ciicsh.gto.afsupportcenter.util.PdfUtil;
import com.ciicsh.gto.afsupportcenter.util.exception.BusinessException;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageKit;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class HfMonthChargeServiceImpl extends ServiceImpl<HfMonthChargeMapper, HfMonthCharge> implements HfMonthChargeService {
    @Autowired
    private HfComAccountService hfComAccountService;
    @Autowired
    private HfComAccountClassService hfComAccountClassService;

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuuMM");
    private DateTimeFormatter dateTimeformatter = DateTimeFormatter.ofPattern("uuuu/MM/dd HH:mm:ss");

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

    private String getChangeTypeByPaymentType(int paymentType) {
        String changeType = "";
        switch (paymentType) {
            case HfMonthChargeConstant.PAYMENT_TYPE_NEW:
                changeType = HfMonthChargeConstant.DETAIL_LIST_TYPE_NEW;
                break;
            case HfMonthChargeConstant.PAYMENT_TYPE_TRANS_IN:
                changeType = HfMonthChargeConstant.DETAIL_LIST_TYPE_TRANS_IN;
                break;
            case HfMonthChargeConstant.PAYMENT_TYPE_OPEN:
            case HfMonthChargeConstant.PAYMENT_TYPE_ADJUST_OPEN:
                changeType = HfMonthChargeConstant.DETAIL_LIST_TYPE_OPEN;
                break;
            case HfMonthChargeConstant.PAYMENT_TYPE_TRANS_OUT:
                changeType = HfMonthChargeConstant.DETAIL_LIST_TYPE_TRANS_OUT;
                break;
            case HfMonthChargeConstant.PAYMENT_TYPE_CLOSE:
            case HfMonthChargeConstant.PAYMENT_TYPE_ADJUST_CLOSE:
                changeType = HfMonthChargeConstant.DETAIL_LIST_TYPE_CLOSE;
                break;
            case HfMonthChargeConstant.PAYMENT_TYPE_DEL:
                changeType = HfMonthChargeConstant.DETAIL_LIST_TYPE_DEL;
                break;
            default:
                break;
        }
        return changeType;
    }

    @Override
    public List<Map<String, Object>> getBasChgDetailsPageList(HFMonthChargeQueryBO hfMonthChargeQueryBO) {
        ComAccountParamExtBo comAccountParamExtBo = new ComAccountParamExtBo();
        comAccountParamExtBo.setCompanyId(hfMonthChargeQueryBO.getCompanyId());
        comAccountParamExtBo.setCompanyName(hfMonthChargeQueryBO.getCompanyName());
        comAccountParamExtBo.setHfAccountType(hfMonthChargeQueryBO.getHfAccountType());
        comAccountParamExtBo.setBasicHfComAccount(hfMonthChargeQueryBO.getBasicHfComAccount());
        comAccountParamExtBo.setAddedHfComAccount(hfMonthChargeQueryBO.getAddedHfComAccount());
        List<ComAccountExtBo> comAccountExtBoList = hfComAccountService.queryHfComAccountList(comAccountParamExtBo);

        if (CollectionUtils.isNotEmpty(comAccountExtBoList)) {
            if (comAccountExtBoList.size() > 1) {
                throw new BusinessException("仅支持一次导出某一个企业账户下的清册，请明确查询条件");
            }

            ComAccountExtBo comAccountExtBo = comAccountExtBoList.get(0);
            Map<String, Object> condition = new HashMap<>();
            condition.put("is_active", 1);
            condition.put("hf_type", 1);
            condition.put("com_account_id", comAccountExtBo.getComAccountId());
            List<HfComAccountClass> hfComAccountClassesList = hfComAccountClassService.selectByMap(condition);

            if (CollectionUtils.isNotEmpty(hfComAccountClassesList)) {
                if (comAccountExtBoList.size() > 1) {
                    throw new BusinessException("企业基本公积金账户数据不正确");
                }
            } else {
                throw new BusinessException("企业基本公积金账户数据不正确");
            }

            hfMonthChargeQueryBO.setPaymentTypes(StringUtils.join(new Integer[] {
                HfMonthChargeConstant.PAYMENT_TYPE_NEW,
                HfMonthChargeConstant.PAYMENT_TYPE_TRANS_IN,
                HfMonthChargeConstant.PAYMENT_TYPE_OPEN,
                HfMonthChargeConstant.PAYMENT_TYPE_ADJUST_OPEN,
            }, ','));
            List<HFMonthChargeReportBO> inHfMonthChargeReportBOList = baseMapper.queryHfMonthChargeReport(hfMonthChargeQueryBO);

            hfMonthChargeQueryBO.setPaymentTypes(StringUtils.join(new Integer[] {
                HfMonthChargeConstant.PAYMENT_TYPE_TRANS_OUT,
                HfMonthChargeConstant.PAYMENT_TYPE_CLOSE,
                HfMonthChargeConstant.PAYMENT_TYPE_ADJUST_CLOSE,
                HfMonthChargeConstant.PAYMENT_TYPE_DEL,
            }, ','));
            List<HFMonthChargeReportBO> outHfMonthChargeReportBOList = baseMapper.queryHfMonthChargeReport(hfMonthChargeQueryBO);

            List<HFMonthChargeBasChgDetailBO> hfMonthChargeBasChgDetailBOList = getBasChgDetailList(inHfMonthChargeReportBOList, outHfMonthChargeReportBOList);
            int allCount = hfMonthChargeBasChgDetailBOList.size();
            if (allCount == 0) {
                throw new BusinessException("未查询到符合条件的清册数据");
            }
            int lastPageCount = allCount % 10;
            int pages = allCount / 10;
            if (lastPageCount > 0) {
                pages++;

                for (int i = 0; i < 10 - lastPageCount; i++) {
                    hfMonthChargeBasChgDetailBOList.add(new HFMonthChargeBasChgDetailBO());
                }
            }

            String comAccountName = comAccountExtBo.getComAccountName();
            String basicHfComAccount = hfComAccountClassesList.get(0).getHfComAccount();
            String hfMonth = hfMonthChargeQueryBO.getHfMonth();
            String hfMonthYYYY = hfMonth.substring(0, 4);
            String hfMonthMM = hfMonth.substring(4, 6);

            List<Map<String, Object>> resultList = new ArrayList<>(pages - 1);
            BigDecimal inTotal = BigDecimal.ZERO;
            BigDecimal outTotal = BigDecimal.ZERO;
            int inCount = 0;
            int outCount = 0;
            String createdBy = "test"; // TODO
            String createdTime = LocalDateTime.now().format(dateTimeformatter);

            for(int page = 0; page < pages; page++) {
                Map<String, Object> pageMap = new HashMap<>();
                pageMap.put("comAccountName", comAccountName);
                pageMap.put("basicHfComAccount", basicHfComAccount);
                pageMap.put("hfMonthYYYY", hfMonthYYYY);
                pageMap.put("hfMonthMM", hfMonthMM);

                BigDecimal inSubTotal = BigDecimal.ZERO;
                BigDecimal outSubTotal = BigDecimal.ZERO;
                int inSubCount = 0;
                int outSubCount = 0;
                List<HFMonthChargeBasChgDetailBO> subList = hfMonthChargeBasChgDetailBOList.subList(page * 10, (page + 1) * 10);
                for(HFMonthChargeBasChgDetailBO hfMonthChargeBasChgDetailBO : subList) {
                    if (StringUtils.isNotEmpty(hfMonthChargeBasChgDetailBO.getInEmployeeName())
//                        && (StringUtils.isNotEmpty(hfMonthChargeBasChgDetailBO.getInHfEmpAccount())
//                            || StringUtils.isNotEmpty(hfMonthChargeBasChgDetailBO.getIdNum()))
                        && hfMonthChargeBasChgDetailBO.getInAmount() != null) {
                        inSubTotal = inSubTotal.add(hfMonthChargeBasChgDetailBO.getInAmount());
                        inSubCount++;
                    }
                    if (StringUtils.isNotEmpty(hfMonthChargeBasChgDetailBO.getOutEmployeeName())
//                        && StringUtils.isNotEmpty(hfMonthChargeBasChgDetailBO.getOutHfEmpAccount())
                        && hfMonthChargeBasChgDetailBO.getOutAmount() != null) {
                        outSubTotal = outSubTotal.add(hfMonthChargeBasChgDetailBO.getOutAmount());
                        outSubCount++;
                    }
                }
                inTotal = inTotal.add(inSubTotal);
                outTotal = outTotal.add(outSubTotal);
                inCount += inSubCount;
                outCount += outSubCount;
                pageMap.put("inSubCount", inSubCount);
                pageMap.put("inSubTotal", inSubTotal);
                pageMap.put("outSubCount", outSubCount);
                pageMap.put("outSubTotal", outSubTotal);
                pageMap.put("createdBy", createdBy);
                pageMap.put("createdTime", createdTime);
                pageMap.put("fillDataList", subList);
                resultList.add(pageMap);
            }

            for(Map<String, Object> map : resultList) {
                map.put("inTotal", inTotal);
                map.put("outTotal", outTotal);
                map.put("inCount", inCount);
                map.put("outCount", outCount);
            }
            return resultList;
        } else {
            throw new BusinessException("未查询到符合条件的企业账户信息");
        }
    }

    private List<HFMonthChargeBasChgDetailBO> getBasChgDetailList(List<HFMonthChargeReportBO> inHfMonthChargeReportBOList, List<HFMonthChargeReportBO> outHfMonthChargeReportBOList) {
        List<HFMonthChargeBasChgDetailBO> hfMonthChargeBasChgDetailBOList = new ArrayList<>();
        int length = 0;
        if (CollectionUtils.isNotEmpty(inHfMonthChargeReportBOList)) {
            length = inHfMonthChargeReportBOList.size();
        }
        boolean isOutNotEmpty = CollectionUtils.isNotEmpty(outHfMonthChargeReportBOList);

        if (isOutNotEmpty && length < outHfMonthChargeReportBOList.size()) {
            for (int i = 0; i < outHfMonthChargeReportBOList.size(); i++) {
                HFMonthChargeReportBO hfMonthChargeReportBO = outHfMonthChargeReportBOList.get(i);
                HFMonthChargeBasChgDetailBO hfMonthChargeBasChgDetailBO = new HFMonthChargeBasChgDetailBO();
                hfMonthChargeBasChgDetailBO.setRowNo(i + 1);
                hfMonthChargeBasChgDetailBO.setOutEmployeeName(hfMonthChargeReportBO.getEmployeeName());
                hfMonthChargeBasChgDetailBO.setOutHfEmpAccount(hfMonthChargeReportBO.getHfEmpAccount());
                hfMonthChargeBasChgDetailBO.setOutChangeType(getChangeTypeByPaymentType(hfMonthChargeReportBO.getPaymentType()));
                hfMonthChargeBasChgDetailBO.setOutAmount(hfMonthChargeReportBO.getAmount());
                hfMonthChargeBasChgDetailBOList.add(hfMonthChargeBasChgDetailBO);
            }

            if (length > 0) {
                for (int i = 0; i < inHfMonthChargeReportBOList.size(); i++) {
                    HFMonthChargeReportBO hfMonthChargeReportBO = inHfMonthChargeReportBOList.get(i);
                    hfMonthChargeBasChgDetailBOList.get(i).setInEmployeeName(hfMonthChargeReportBO.getEmployeeName());
                    hfMonthChargeBasChgDetailBOList.get(i).setInHfEmpAccount(hfMonthChargeReportBO.getHfEmpAccount());
                    hfMonthChargeBasChgDetailBOList.get(i).setInChangeType(getChangeTypeByPaymentType(hfMonthChargeReportBO.getPaymentType()));
                    hfMonthChargeBasChgDetailBOList.get(i).setInAmount(hfMonthChargeReportBO.getAmount());
                    hfMonthChargeBasChgDetailBOList.get(i).setIdNum(hfMonthChargeReportBO.getIdNum());
                    hfMonthChargeBasChgDetailBOList.get(i).setBase(hfMonthChargeReportBO.getBase());
                    hfMonthChargeBasChgDetailBOList.get(i).setRatio(CalculateSocialUtils.digitInSimpleFormat(hfMonthChargeReportBO.getRatio().multiply(BigDecimal.valueOf(100))));
                }
            }
        } else {
            if (length > 0) {
                for (int i = 0; i < inHfMonthChargeReportBOList.size(); i++) {
                    HFMonthChargeReportBO hfMonthChargeReportBO = inHfMonthChargeReportBOList.get(i);
                    HFMonthChargeBasChgDetailBO hfMonthChargeBasChgDetailBO = new HFMonthChargeBasChgDetailBO();
                    hfMonthChargeBasChgDetailBO.setRowNo(i + 1);
                    hfMonthChargeBasChgDetailBO.setInEmployeeName(hfMonthChargeReportBO.getEmployeeName());
                    hfMonthChargeBasChgDetailBO.setInHfEmpAccount(hfMonthChargeReportBO.getHfEmpAccount());
                    hfMonthChargeBasChgDetailBO.setInChangeType(getChangeTypeByPaymentType(hfMonthChargeReportBO.getPaymentType()));
                    hfMonthChargeBasChgDetailBO.setInAmount(hfMonthChargeReportBO.getAmount());
                    hfMonthChargeBasChgDetailBO.setIdNum(hfMonthChargeReportBO.getIdNum());
                    hfMonthChargeBasChgDetailBO.setBase(hfMonthChargeReportBO.getBase());
                    hfMonthChargeBasChgDetailBO.setRatio(CalculateSocialUtils.digitInSimpleFormat(hfMonthChargeReportBO.getRatio().multiply(BigDecimal.valueOf(100))));
                    hfMonthChargeBasChgDetailBOList.add(hfMonthChargeBasChgDetailBO);
                }
            }

            if (isOutNotEmpty) {
                for (int i = 0; i < outHfMonthChargeReportBOList.size(); i++) {
                    HFMonthChargeReportBO hfMonthChargeReportBO = outHfMonthChargeReportBOList.get(i);
                    hfMonthChargeBasChgDetailBOList.get(i).setOutEmployeeName(hfMonthChargeReportBO.getEmployeeName());
                    hfMonthChargeBasChgDetailBOList.get(i).setOutHfEmpAccount(hfMonthChargeReportBO.getHfEmpAccount());
                    hfMonthChargeBasChgDetailBOList.get(i).setOutChangeType(getChangeTypeByPaymentType(hfMonthChargeReportBO.getPaymentType()));
                    hfMonthChargeBasChgDetailBOList.get(i).setOutAmount(hfMonthChargeReportBO.getAmount());
                }
            }
        }

        return hfMonthChargeBasChgDetailBOList;
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
