package com.ciicsh.gto.afsupportcenter.housefund.fundservice.business.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.toolkit.CollectionUtils;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.*;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.customer.ComAccountExtBo;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.customer.ComAccountParamExtBo;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.payment.HFNetBankExportBO;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.payment.HFNetBankQueryBO;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.business.HfComAccountClassService;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.business.HfComAccountService;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.business.HfMonthChargeService;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.constant.HfEmpTaskConstant;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.constant.HfMonthChargeConstant;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.dao.HfMonthChargeMapper;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.dao.HfPaymentAccountMapper;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.dao.HfPaymentComMapper;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.dao.HfPaymentMapper;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.HfComAccountClass;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.HfMonthCharge;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.HfPayment;
import com.ciicsh.gto.afsupportcenter.util.CalculateSocialUtils;
import com.ciicsh.gto.afsupportcenter.util.exception.BusinessException;
import com.ciicsh.gto.afsupportcenter.util.interceptor.authenticate.UserContext;
import com.ciicsh.gto.afsupportcenter.util.logService.LogApiUtil;
import com.ciicsh.gto.afsupportcenter.util.logService.LogMessage;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageKit;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class HfMonthChargeServiceImpl extends ServiceImpl<HfMonthChargeMapper, HfMonthCharge> implements HfMonthChargeService {
    @Autowired
    private HfComAccountService hfComAccountService;
    @Autowired
    private HfComAccountClassService hfComAccountClassService;
    @Autowired
    private HfPaymentAccountMapper hfPaymentAccountMapper;
    @Autowired
    private HfPaymentMapper hfPaymentMapper;
    @Autowired
    private LogApiUtil logApiUtil;
    @Autowired
    private HfPaymentComMapper hfPaymentComMapper;

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuuMM");
    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("uuuu/MM/dd HH:mm:ss");
    private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("uuuuMMdd");
    private char[] repairReasonIcons = {'\u2460', '\u2461', '\u2462', '\u2463', '\u2464', '\u2465'};

    @Override
    public int updateHfMonthCharge(HfMonthChargeBo hfMonthChargeBo) {
        return baseMapper.updateHfMonthCharge(hfMonthChargeBo);
    }

    @Override
    public int deleteHfMonthCharges(List<Long> empTaskIdList) {
        return baseMapper.deleteHfMonthCharges(empTaskIdList);
    }

    @Override
    public List<HfMonthChargeDiffBo> getHfMonthChargeDiffSum(HfMonthChargeBo hfMonthChargeBo) {
        return baseMapper.getHfMonthChargeDiffSum(hfMonthChargeBo);
    }

    @Override
    public PageRows<HFMonthChargeReportBO> queryHfMonthChargeReport(PageInfo pageInfo, String userId) {
        HFMonthChargeQueryBO hfMonthChargeQueryBO = pageInfo.toJavaObject(HFMonthChargeQueryBO.class);
        hfMonthChargeQueryBO.setUserId(userId);
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

    /**
     * 获取基本/补充公积金汇缴变更清册导出数据列表
     *
     * @param hfMonthChargeQueryBO 雇员每月汇缴明细数据查询条件
     * @return 基本/补充公积金汇缴变更清册导出数据列表
     */
    @Override
    public List<Map<String, Object>> getChgDetailsPageList(HFMonthChargeQueryBO hfMonthChargeQueryBO, boolean isPageByComAccount) {
        ComAccountParamExtBo comAccountParamExtBo = new ComAccountParamExtBo();
        comAccountParamExtBo.setCompanyId(hfMonthChargeQueryBO.getCompanyId());
        comAccountParamExtBo.setCompanyName(hfMonthChargeQueryBO.getCompanyName());
        comAccountParamExtBo.setHfAccountType(hfMonthChargeQueryBO.getHfAccountType());
        comAccountParamExtBo.setBasicHfComAccount(hfMonthChargeQueryBO.getBasicHfComAccount());
        comAccountParamExtBo.setAddedHfComAccount(hfMonthChargeQueryBO.getAddedHfComAccount());
        comAccountParamExtBo.setUserId(hfMonthChargeQueryBO.getUserId());
        comAccountParamExtBo.setHfMonth(hfMonthChargeQueryBO.getHfMonth());
        comAccountParamExtBo.setHfType(hfMonthChargeQueryBO.getHfType());
        comAccountParamExtBo.setPaymentTypes(hfMonthChargeQueryBO.getPaymentTypes());
        List<ComAccountExtBo> comAccountExtBoList = null;
        //如果是汇缴支付给发起的报表清册
        if (Optional.ofNullable(hfMonthChargeQueryBO.getPaymentId()).isPresent()) {
            HfPayment hfPayment = new HfPayment();
            hfPayment.setPaymentId(hfMonthChargeQueryBO.getPaymentId());
            hfPayment = hfPaymentMapper.selectOne(hfPayment);
            hfMonthChargeQueryBO.setHfMonth(hfPayment.getPaymentMonth());
            comAccountParamExtBo.setHfMonth(hfPayment.getPaymentMonth());

            Map<String, Object> map = new HashMap<>();
            List<String> BasicListAccounts = new ArrayList<>();
            List<String> AddListAccounts = new ArrayList<>();
            map.put("payment_id", hfMonthChargeQueryBO.getPaymentId());
            hfPaymentAccountMapper.getComAccountByPaymentId(hfMonthChargeQueryBO.getPaymentId()).forEach(
                acc -> {
                    if (acc.getHfType() == 1) {
                        BasicListAccounts.add(acc.getHfComAccount());
                    }
                    if (acc.getHfType() == 2) {
                        AddListAccounts.add(acc.getHfComAccount());
                    }
                }
            );
            if (hfMonthChargeQueryBO.getHfType() == 1) {
                comAccountParamExtBo.setBasicComAccountArray(BasicListAccounts.toArray(new String[BasicListAccounts.size()]));
            }
            if (hfMonthChargeQueryBO.getHfType() == 2) {
                comAccountParamExtBo.setAddedComAccountArray(AddListAccounts.toArray(new String[AddListAccounts.size()]));
            }

            if ((comAccountParamExtBo.getHfType() == 1 && !ArrayUtils.isEmpty(comAccountParamExtBo.getBasicComAccountArray()))
                || (comAccountParamExtBo.getHfType() == 2 && !ArrayUtils.isEmpty(comAccountParamExtBo.getAddedComAccountArray()))) {
                comAccountExtBoList = hfComAccountService.queryHfComAccountList(comAccountParamExtBo);
            }
        } else {
            comAccountExtBoList = hfComAccountService.queryHfComAccountList(comAccountParamExtBo);
        }

        List<Map<String, Object>> resultList = new ArrayList<>();

        if (CollectionUtils.isNotEmpty(comAccountExtBoList)) {
            for (ComAccountExtBo comAccountExtBo : comAccountExtBoList) {
//            ComAccountExtBo comAccountExtBo = comAccountExtBoList.get(0);
                Map<String, Object> condition = new HashMap<>();
                condition.put("is_active", 1);
                condition.put("hf_type", hfMonthChargeQueryBO.getHfType());
                condition.put("com_account_id", comAccountExtBo.getComAccountId());
                List<HfComAccountClass> hfComAccountClassesList = hfComAccountClassService.selectByMap(condition);

                String hfTypeName;
                String comAccountName = comAccountExtBo.getComAccountName();

                if (hfMonthChargeQueryBO.getHfType() == HfEmpTaskConstant.HF_TYPE_BASIC) {
                    hfTypeName = "基本";
                } else {
                    hfTypeName = "补充";
                }

                if (CollectionUtils.isNotEmpty(hfComAccountClassesList)) {
                    if (hfComAccountClassesList.size() > 1) {
                        logApiUtil.warn(LogMessage.create().setTitle("获取基本/补充公积金汇缴变更清册导出数据列表")
                            .setContent("企业[" + comAccountName + "]" + hfTypeName + "公积金账户数据不正确"));
                        continue;
                    }
                } else {
                    logApiUtil.warn(LogMessage.create().setTitle("获取基本/补充公积金汇缴变更清册导出数据列表")
                        .setContent("企业[" + comAccountName + "]" + hfTypeName + "公积金账户数据不正确"));
                    continue;
                }
                String hfComAccount = hfComAccountClassesList.get(0).getHfComAccount();
                if (StringUtils.isEmpty(hfComAccount)) {
                    logApiUtil.warn(LogMessage.create().setTitle("获取基本/补充公积金汇缴变更清册导出数据列表")
                        .setContent("企业[" + comAccountName + "]" + hfComAccountClassesList.get(0).getComAccountId() + "企业公积金账号为空"));
                    continue;
                }

                List<Map<String, Object>> list = null;
                try {
                    list = getChgDetailsPageListOfOneComAccount(hfMonthChargeQueryBO, comAccountName, hfComAccount, isPageByComAccount);
                } catch (BusinessException e) {
                    logApiUtil.warn(LogMessage.create().setTitle("获取基本/补充公积金汇缴变更清册导出数据列表")
                        .setContent(e.getMessage()));
                }

                if (list != null) {
                    resultList.addAll(list);
                }
            }

            if (resultList.size() == 0) {
                Map<String, Object> pageMap = new HashMap<>();
                resultList.add(pageMap);
            }
        } else {
            Map<String, Object> pageMap = new HashMap<>();
            resultList.add(pageMap);
        }
        return resultList;
    }

    private List<Map<String, Object>> getChgDetailsPageListOfOneComAccount(HFMonthChargeQueryBO hfMonthChargeQueryBO,
                                                                           String comAccountName,
                                                                           String hfComAccount,
                                                                           boolean isPageByComAccount) {
        if (hfMonthChargeQueryBO.getHfType() == HfEmpTaskConstant.HF_TYPE_BASIC) {
            hfMonthChargeQueryBO.setBasicHfComAccount(hfComAccount);
        } else {
            hfMonthChargeQueryBO.setAddedHfComAccount(hfComAccount);
        }

        hfMonthChargeQueryBO.setPaymentTypes(StringUtils.join(new Integer[]{
            HfMonthChargeConstant.PAYMENT_TYPE_NEW,
            HfMonthChargeConstant.PAYMENT_TYPE_TRANS_IN,
            HfMonthChargeConstant.PAYMENT_TYPE_OPEN,
            HfMonthChargeConstant.PAYMENT_TYPE_ADJUST_OPEN,
        }, ','));
        List<HFMonthChargeReportBO> inHfMonthChargeReportBOList = baseMapper.queryHfMonthChargeReport(hfMonthChargeQueryBO);

        hfMonthChargeQueryBO.setPaymentTypes(StringUtils.join(new Integer[]{
            HfMonthChargeConstant.PAYMENT_TYPE_TRANS_OUT,
            HfMonthChargeConstant.PAYMENT_TYPE_CLOSE,
            HfMonthChargeConstant.PAYMENT_TYPE_ADJUST_CLOSE,
            HfMonthChargeConstant.PAYMENT_TYPE_DEL,
        }, ','));
        List<HFMonthChargeReportBO> outHfMonthChargeReportBOList = baseMapper.queryHfMonthChargeReport(hfMonthChargeQueryBO);

        int allCount;
        List<HFMonthChargeBasChgDetailBO> hfMonthChargeBasChgDetailBOList = null;
        List<HFMonthChargeAddChgDetailBO> hfMonthChargeAddChgDetailBOList = null;
        Set<String> companyIdSet = new HashSet<>();

        if (hfMonthChargeQueryBO.getHfType() == HfEmpTaskConstant.HF_TYPE_BASIC) {
            hfMonthChargeBasChgDetailBOList = getBasChgDetailList(companyIdSet, inHfMonthChargeReportBOList, outHfMonthChargeReportBOList);
            allCount = hfMonthChargeBasChgDetailBOList.size();
        } else {
            hfMonthChargeAddChgDetailBOList = getAddChgDetailList(companyIdSet, inHfMonthChargeReportBOList, outHfMonthChargeReportBOList);
            allCount = hfMonthChargeAddChgDetailBOList.size();
        }
        if (allCount == 0) {
            throw new BusinessException("未查询到符合条件的清册数据");
        }
        int lastPageCount = allCount % 10;
        int pages = allCount / 10;
        if (lastPageCount > 0) {
            pages++;

            if (hfMonthChargeQueryBO.getHfType() == HfEmpTaskConstant.HF_TYPE_BASIC) {
                for (int i = 0; i < 10 - lastPageCount; i++) {
                    hfMonthChargeBasChgDetailBOList.add(new HFMonthChargeBasChgDetailBO());
                }
            } else {
                for (int i = 0; i < 10 - lastPageCount; i++) {
                    hfMonthChargeAddChgDetailBOList.add(new HFMonthChargeAddChgDetailBO());
                }
            }
        }

        String hfMonth = hfMonthChargeQueryBO.getHfMonth();
        String hfMonthYYYY = hfMonth.substring(0, 4);
        String hfMonthMM = hfMonth.substring(4, 6);

        List<Map<String, Object>> resultList = new ArrayList<>(pages - 1);
        BigDecimal inTotal = BigDecimal.ZERO;
        BigDecimal outTotal = BigDecimal.ZERO;
        int inCount = 0;
        int outCount = 0;
        String createdTime = LocalDateTime.now().format(dateTimeFormatter);

        for (int page = 0; page < pages; page++) {
            Map<String, Object> pageMap = new HashMap<>();
            pageMap.put("comAccountName", comAccountName);
            pageMap.put("hfComAccount", hfComAccount);
            pageMap.put("hfMonthYYYY", hfMonthYYYY);
            pageMap.put("hfMonthMM", hfMonthMM);

            BigDecimal inSubTotal = BigDecimal.ZERO;
            BigDecimal outSubTotal = BigDecimal.ZERO;
            int inSubCount = 0;
            int outSubCount = 0;

            if (hfMonthChargeQueryBO.getHfType() == HfEmpTaskConstant.HF_TYPE_BASIC) {
                List<HFMonthChargeBasChgDetailBO> subBasList = hfMonthChargeBasChgDetailBOList.subList(page * 10, (page + 1) * 10);
                for (HFMonthChargeBasChgDetailBO hfMonthChargeBasChgDetailBO : subBasList) {
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
                pageMap.put("fillDataList", subBasList);
            } else {
                List<HFMonthChargeAddChgDetailBO> subAddList = hfMonthChargeAddChgDetailBOList.subList(page * 10, (page + 1) * 10);
                for (HFMonthChargeAddChgDetailBO hfMonthChargeAddChgDetailBO : subAddList) {
                    if (StringUtils.isNotEmpty(hfMonthChargeAddChgDetailBO.getInEmployeeName())
//                        && (StringUtils.isNotEmpty(hfMonthChargeAddChgDetailBO.getInAddedHfEmpAccount())
//                            || StringUtils.isNotEmpty(hfMonthChargeAddChgDetailBO.getBasicHfEmpAccount()))
                        && hfMonthChargeAddChgDetailBO.getInAmount() != null) {
                        inSubTotal = inSubTotal.add(hfMonthChargeAddChgDetailBO.getInAmount());
                        inSubCount++;
                    }
                    if (StringUtils.isNotEmpty(hfMonthChargeAddChgDetailBO.getOutEmployeeName())
//                        && StringUtils.isNotEmpty(hfMonthChargeAddChgDetailBO.getOutAddedHfEmpAccount())
                        && hfMonthChargeAddChgDetailBO.getOutAmount() != null) {
                        outSubTotal = outSubTotal.add(hfMonthChargeAddChgDetailBO.getOutAmount());
                        outSubCount++;
                    }
                }
                pageMap.put("fillDataList", subAddList);
            }
            inTotal = inTotal.add(inSubTotal);
            outTotal = outTotal.add(outSubTotal);
            inCount += inSubCount;
            outCount += outSubCount;
            pageMap.put("inSubCount", inSubCount);
            pageMap.put("inSubTotal", inSubTotal);
            pageMap.put("outSubCount", outSubCount);
            pageMap.put("outSubTotal", outSubTotal);
            pageMap.put("createdBy", UserContext.getUser().getDisplayName());
            pageMap.put("createdTime", createdTime);
            pageMap.put("companyIds", StringUtils.join(companyIdSet, ' '));
            if (isPageByComAccount) {
                pageMap.put("totalPage", String.valueOf(pages));
                pageMap.put("page", String.valueOf(page + 1));
            }
            resultList.add(pageMap);
        }

        for (Map<String, Object> map : resultList) {
            map.put("inTotal", inTotal);
            map.put("outTotal", outTotal);
            map.put("inCount", inCount);
            map.put("outCount", outCount);
        }

        return resultList;
    }

    /**
     * 组装基本公积金汇缴变更清册表体数据列表
     *
     * @param companyIdSet                 所关联的客户ID的集合
     * @param inHfMonthChargeReportBOList  增加汇缴部分雇员汇缴明细数据列表
     * @param outHfMonthChargeReportBOList 减少汇缴部分雇员汇缴明细数据列表
     * @return 基本公积金汇缴变更清册表体数据列表
     */
    private List<HFMonthChargeBasChgDetailBO> getBasChgDetailList(Set<String> companyIdSet,
                                                                  List<HFMonthChargeReportBO> inHfMonthChargeReportBOList,
                                                                  List<HFMonthChargeReportBO> outHfMonthChargeReportBOList) {
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

                companyIdSet.add(hfMonthChargeReportBO.getCompanyId());
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
                    hfMonthChargeBasChgDetailBOList.get(i).setRatio(
                        CalculateSocialUtils.digitInSimpleFormat(
                            hfMonthChargeReportBO.getRatio()
                                .divide(BigDecimal.valueOf(2), 5, BigDecimal.ROUND_HALF_UP)
                                .multiply(BigDecimal.valueOf(100))) + "%");

                    companyIdSet.add(hfMonthChargeReportBO.getCompanyId());
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
                    hfMonthChargeBasChgDetailBO.setRatio(
                        CalculateSocialUtils.digitInSimpleFormat(
                            hfMonthChargeReportBO.getRatio()
                                .divide(BigDecimal.valueOf(2), 5, BigDecimal.ROUND_HALF_UP)
                                .multiply(BigDecimal.valueOf(100))) + "%");
                    hfMonthChargeBasChgDetailBOList.add(hfMonthChargeBasChgDetailBO);

                    companyIdSet.add(hfMonthChargeReportBO.getCompanyId());
                }
            }

            if (isOutNotEmpty) {
                for (int i = 0; i < outHfMonthChargeReportBOList.size(); i++) {
                    HFMonthChargeReportBO hfMonthChargeReportBO = outHfMonthChargeReportBOList.get(i);
                    hfMonthChargeBasChgDetailBOList.get(i).setOutEmployeeName(hfMonthChargeReportBO.getEmployeeName());
                    hfMonthChargeBasChgDetailBOList.get(i).setOutHfEmpAccount(hfMonthChargeReportBO.getHfEmpAccount());
                    hfMonthChargeBasChgDetailBOList.get(i).setOutChangeType(getChangeTypeByPaymentType(hfMonthChargeReportBO.getPaymentType()));
                    hfMonthChargeBasChgDetailBOList.get(i).setOutAmount(hfMonthChargeReportBO.getAmount());

                    companyIdSet.add(hfMonthChargeReportBO.getCompanyId());
                }
            }
        }

        return hfMonthChargeBasChgDetailBOList;
    }

    /**
     * 组装补充公积金汇缴变更清册表体数据列表
     *
     * @param companyIdSet                 所关联的客户ID的集合
     * @param inHfMonthChargeReportBOList  增加汇缴部分雇员汇缴明细数据列表
     * @param outHfMonthChargeReportBOList 减少汇缴部分雇员汇缴明细数据列表
     * @return 补充公积金汇缴变更清册表体数据列表
     */
    private List<HFMonthChargeAddChgDetailBO> getAddChgDetailList(Set<String> companyIdSet,
                                                                  List<HFMonthChargeReportBO> inHfMonthChargeReportBOList,
                                                                  List<HFMonthChargeReportBO> outHfMonthChargeReportBOList) {
        List<HFMonthChargeAddChgDetailBO> hfMonthChargeAddChgDetailBOList = new ArrayList<>();
        int length = 0;
        if (CollectionUtils.isNotEmpty(inHfMonthChargeReportBOList)) {
            length = inHfMonthChargeReportBOList.size();
        }
        boolean isOutNotEmpty = CollectionUtils.isNotEmpty(outHfMonthChargeReportBOList);

        if (isOutNotEmpty && length < outHfMonthChargeReportBOList.size()) {
            for (int i = 0; i < outHfMonthChargeReportBOList.size(); i++) {
                HFMonthChargeReportBO hfMonthChargeReportBO = outHfMonthChargeReportBOList.get(i);
                HFMonthChargeAddChgDetailBO hfMonthChargeAddChgDetailBO = new HFMonthChargeAddChgDetailBO();
                hfMonthChargeAddChgDetailBO.setRowNo(i + 1);
                hfMonthChargeAddChgDetailBO.setOutEmployeeName(hfMonthChargeReportBO.getEmployeeName());
                hfMonthChargeAddChgDetailBO.setOutAddedHfEmpAccount(hfMonthChargeReportBO.getHfEmpAccount());
                hfMonthChargeAddChgDetailBO.setOutChangeType(getChangeTypeByPaymentType(hfMonthChargeReportBO.getPaymentType()));
                hfMonthChargeAddChgDetailBO.setOutAmount(hfMonthChargeReportBO.getAmount());
                hfMonthChargeAddChgDetailBOList.add(hfMonthChargeAddChgDetailBO);

                companyIdSet.add(hfMonthChargeReportBO.getCompanyId());
            }

            if (length > 0) {
                for (int i = 0; i < inHfMonthChargeReportBOList.size(); i++) {
                    HFMonthChargeReportBO hfMonthChargeReportBO = inHfMonthChargeReportBOList.get(i);
                    hfMonthChargeAddChgDetailBOList.get(i).setInEmployeeName(hfMonthChargeReportBO.getEmployeeName());
                    hfMonthChargeAddChgDetailBOList.get(i).setInAddedHfEmpAccount(hfMonthChargeReportBO.getHfEmpAccount());
                    hfMonthChargeAddChgDetailBOList.get(i).setInChangeType(getChangeTypeByPaymentType(hfMonthChargeReportBO.getPaymentType()));
                    hfMonthChargeAddChgDetailBOList.get(i).setInAmount(hfMonthChargeReportBO.getAmount());
                    hfMonthChargeAddChgDetailBOList.get(i).setBasicHfEmpAccount(hfMonthChargeReportBO.getBasicHfEmpAccount());
                    hfMonthChargeAddChgDetailBOList.get(i).setRatio(
                        CalculateSocialUtils.digitInSimpleFormat(hfMonthChargeReportBO.getRatio()
                            .divide(BigDecimal.valueOf(2), 5, BigDecimal.ROUND_HALF_UP)
                            .multiply(BigDecimal.valueOf(100))) + "%");

                    companyIdSet.add(hfMonthChargeReportBO.getCompanyId());
                }
            }
        } else {
            if (length > 0) {
                for (int i = 0; i < inHfMonthChargeReportBOList.size(); i++) {
                    HFMonthChargeReportBO hfMonthChargeReportBO = inHfMonthChargeReportBOList.get(i);
                    HFMonthChargeAddChgDetailBO hfMonthChargeAddChgDetailBO = new HFMonthChargeAddChgDetailBO();
                    hfMonthChargeAddChgDetailBO.setRowNo(i + 1);
                    hfMonthChargeAddChgDetailBO.setInEmployeeName(hfMonthChargeReportBO.getEmployeeName());
                    hfMonthChargeAddChgDetailBO.setInAddedHfEmpAccount(hfMonthChargeReportBO.getHfEmpAccount());
                    hfMonthChargeAddChgDetailBO.setInChangeType(getChangeTypeByPaymentType(hfMonthChargeReportBO.getPaymentType()));
                    hfMonthChargeAddChgDetailBO.setInAmount(hfMonthChargeReportBO.getAmount());
                    hfMonthChargeAddChgDetailBO.setBasicHfEmpAccount(hfMonthChargeReportBO.getBasicHfEmpAccount());
                    hfMonthChargeAddChgDetailBO.setRatio(
                        CalculateSocialUtils.digitInSimpleFormat(
                            hfMonthChargeReportBO.getRatio()
                                .divide(BigDecimal.valueOf(2), 5, BigDecimal.ROUND_HALF_UP)
                                .multiply(BigDecimal.valueOf(100))) + "%");
                    hfMonthChargeAddChgDetailBOList.add(hfMonthChargeAddChgDetailBO);

                    companyIdSet.add(hfMonthChargeReportBO.getCompanyId());
                }
            }

            if (isOutNotEmpty) {
                for (int i = 0; i < outHfMonthChargeReportBOList.size(); i++) {
                    HFMonthChargeReportBO hfMonthChargeReportBO = outHfMonthChargeReportBOList.get(i);
                    hfMonthChargeAddChgDetailBOList.get(i).setOutEmployeeName(hfMonthChargeReportBO.getEmployeeName());
                    hfMonthChargeAddChgDetailBOList.get(i).setOutAddedHfEmpAccount(hfMonthChargeReportBO.getHfEmpAccount());
                    hfMonthChargeAddChgDetailBOList.get(i).setOutChangeType(getChangeTypeByPaymentType(hfMonthChargeReportBO.getPaymentType()));
                    hfMonthChargeAddChgDetailBOList.get(i).setOutAmount(hfMonthChargeReportBO.getAmount());

                    companyIdSet.add(hfMonthChargeReportBO.getCompanyId());
                }
            }
        }

        return hfMonthChargeAddChgDetailBOList;
    }

    /**
     * 获取基本/补充公积金补缴清册导出数据列表
     *
     * @param hfMonthChargeQueryBO 雇员每月汇缴明细数据查询条件
     * @return 基本/补充公积金补缴清册导出数据列表
     */
    @Override
    public List<Map<String, Object>> getRepairDetailsPageList(HFMonthChargeQueryBO hfMonthChargeQueryBO, boolean isPageByComAccount) {
        ComAccountParamExtBo comAccountParamExtBo = new ComAccountParamExtBo();
        comAccountParamExtBo.setCompanyId(hfMonthChargeQueryBO.getCompanyId());
        comAccountParamExtBo.setCompanyName(hfMonthChargeQueryBO.getCompanyName());
        comAccountParamExtBo.setHfAccountType(hfMonthChargeQueryBO.getHfAccountType());
        comAccountParamExtBo.setBasicHfComAccount(hfMonthChargeQueryBO.getBasicHfComAccount());
        comAccountParamExtBo.setAddedHfComAccount(hfMonthChargeQueryBO.getAddedHfComAccount());
        comAccountParamExtBo.setHfMonth(hfMonthChargeQueryBO.getHfMonth());
        comAccountParamExtBo.setHfType(hfMonthChargeQueryBO.getHfType());
        comAccountParamExtBo.setPaymentTypes(hfMonthChargeQueryBO.getPaymentTypes());
        comAccountParamExtBo.setUserId(hfMonthChargeQueryBO.getUserId());
        List<ComAccountExtBo> comAccountExtBoList = null;
        //如果是汇缴支付给发起的报表清册
        if (Optional.ofNullable(hfMonthChargeQueryBO.getPaymentId()).isPresent()) {
            HfPayment hfPayment = new HfPayment();
            hfPayment.setPaymentId(hfMonthChargeQueryBO.getPaymentId());
            hfPayment = hfPaymentMapper.selectOne(hfPayment);
            hfMonthChargeQueryBO.setHfMonth(hfPayment.getPaymentMonth());
            comAccountParamExtBo.setHfMonth(hfPayment.getPaymentMonth());

            Map<String, Object> map = new HashMap<>();
            List<String> BasicListAccounts = new ArrayList<>();
            List<String> AddListAccounts = new ArrayList<>();
            map.put("payment_id", hfMonthChargeQueryBO.getPaymentId());
            hfPaymentAccountMapper.getComAccountByPaymentId(hfMonthChargeQueryBO.getPaymentId()).forEach(
                acc -> {
                    if (acc.getHfType() == 1) {
                        BasicListAccounts.add(acc.getHfComAccount());
                    }
                    if (acc.getHfType() == 2) {
                        AddListAccounts.add(acc.getHfComAccount());
                    }
                }
            );
            if (hfMonthChargeQueryBO.getHfType() == 1) {
                comAccountParamExtBo.setBasicComAccountArray(BasicListAccounts.toArray(new String[BasicListAccounts.size()]));
            }
            if (hfMonthChargeQueryBO.getHfType() == 2) {
                comAccountParamExtBo.setAddedComAccountArray(AddListAccounts.toArray(new String[AddListAccounts.size()]));
            }

            if ((comAccountParamExtBo.getHfType() == 1 && !ArrayUtils.isEmpty(comAccountParamExtBo.getBasicComAccountArray()))
                || (comAccountParamExtBo.getHfType() == 2 && !ArrayUtils.isEmpty(comAccountParamExtBo.getAddedComAccountArray()))) {
                comAccountExtBoList = hfComAccountService.queryHfComAccountList(comAccountParamExtBo);
            }
        } else {
            comAccountExtBoList = hfComAccountService.queryHfComAccountList(comAccountParamExtBo);
        }

        List<Map<String, Object>> resultList = new ArrayList<>();

        if (CollectionUtils.isNotEmpty(comAccountExtBoList)) {
//            if (comAccountExtBoList.size() > 1) {
//                throw new BusinessException("仅支持一次导出某一个企业账户下的清册，请明确查询条件");
//            }

            for (ComAccountExtBo comAccountExtBo : comAccountExtBoList) {
                Map<String, Object> condition = new HashMap<>();
                condition.put("is_active", 1);
                condition.put("hf_type", hfMonthChargeQueryBO.getHfType());
                condition.put("com_account_id", comAccountExtBo.getComAccountId());
                List<HfComAccountClass> hfComAccountClassesList = hfComAccountClassService.selectByMap(condition);

                String hfTypeName;
                String comAccountName = comAccountExtBo.getComAccountName();

                if (hfMonthChargeQueryBO.getHfType() == HfEmpTaskConstant.HF_TYPE_BASIC) {
                    hfTypeName = "基本";
                } else {
                    hfTypeName = "补充";
                }

                if (CollectionUtils.isNotEmpty(hfComAccountClassesList)) {
                    if (hfComAccountClassesList.size() > 1) {
                        logApiUtil.warn(LogMessage.create().setTitle("获取基本/补充公积金补缴清册导出数据列表")
                            .setContent("企业[" + comAccountName + "]" + hfTypeName + "公积金账户数据不正确"));
                        continue;
                    }
                } else {
                    logApiUtil.warn(LogMessage.create().setTitle("获取基本/补充公积金补缴清册导出数据列表")
                        .setContent("企业[" + comAccountName + "]" + hfTypeName + "公积金账户数据不正确"));
                    continue;
                }

                String hfComAccount = hfComAccountClassesList.get(0).getHfComAccount();
                if (StringUtils.isEmpty(hfComAccount)) {
                    logApiUtil.warn(LogMessage.create().setTitle("获取基本/补充公积金补缴清册导出数据列表")
                        .setContent("企业[" + comAccountName + "]" + hfComAccountClassesList.get(0).getComAccountId() + "企业公积金账号为空"));
                    continue;
                }

                List<Map<String, Object>> list = null;
                try {
                    list = getRepairDetailsPageListOfOneComAccount(hfMonthChargeQueryBO, comAccountName, hfComAccount, isPageByComAccount);
                } catch (BusinessException e) {
                    logApiUtil.warn(LogMessage.create().setTitle("获取基本/补充公积金补缴清册导出数据列表")
                        .setContent(e.getMessage()));
                }
                if (list != null) {
                    resultList.addAll(list);
                }
            }

            if (resultList.size() == 0) {
                Map<String, Object> pageMap = new HashMap<>();
                resultList.add(pageMap);
            }
        } else {
            Map<String, Object> pageMap = new HashMap<>();
            resultList.add(pageMap);
        }
        return resultList;
    }

    @Override
    public List<HFNetBankExportBO> queryNetBankData(HFNetBankQueryBO hfNetBankQueryBO) {
        return baseMapper.queryNetBankData(hfNetBankQueryBO);
    }

    private List<Map<String, Object>> getRepairDetailsPageListOfOneComAccount(HFMonthChargeQueryBO hfMonthChargeQueryBO,
                                                                              String comAccountName,
                                                                              String hfComAccount,
                                                                              boolean isPageByComAccount) {
        if (hfMonthChargeQueryBO.getHfType() == HfEmpTaskConstant.HF_TYPE_BASIC) {
            hfMonthChargeQueryBO.setBasicHfComAccount(hfComAccount);
        } else {
            hfMonthChargeQueryBO.setAddedHfComAccount(hfComAccount);
        }

        hfMonthChargeQueryBO.setPaymentTypes(StringUtils.join(new Integer[]{
            HfMonthChargeConstant.PAYMENT_TYPE_REPAIR,
            HfMonthChargeConstant.PAYMENT_TYPE_DIFF_REPAIR
        }, ','));
        hfMonthChargeQueryBO.setExceptRepairReason(HfMonthChargeConstant.REPAIR_OFF_BALANCE_PAYMENT);  // 账外补缴不导出清册
        List<HFMonthChargeReportBO> hfMonthChargeReportBOList = baseMapper.queryHfMonthChargeReport(hfMonthChargeQueryBO);

        if (CollectionUtils.isEmpty(hfMonthChargeReportBOList)) {
            throw new BusinessException("未查询到符合条件的清册数据");
        }

        int allCount;
        Set<String> companyIdSet = new HashSet<>();
        List<HFMonthChargeRepairDetailBO> hfMonthChargeRepairDetailBOList = getRepairDetailList(companyIdSet, hfMonthChargeReportBOList);
        allCount = hfMonthChargeRepairDetailBOList.size();

        if (allCount == 0) {
            throw new BusinessException("未查询到符合条件的清册数据");
        }
        int lastPageCount = allCount % 10;
        int pages = allCount / 10;
        if (lastPageCount > 0) {
            pages++;

            for (int i = 0; i < 10 - lastPageCount; i++) {
                hfMonthChargeRepairDetailBOList.add(new HFMonthChargeRepairDetailBO());
            }
        }

        List<Map<String, Object>> resultList = new ArrayList<>(pages - 1);
        BigDecimal total = BigDecimal.ZERO;
        int count = 0;
        String createdTime = LocalDateTime.now().format(dateFormatter);
        String createdTimeYYYY = createdTime.substring(0, 4);
        String createdTimeMM = createdTime.substring(4, 6);
        String createdTimeDD = createdTime.substring(6, 8);

        for (int page = 0; page < pages; page++) {
            Map<String, Object> pageMap = new HashMap<>();
            pageMap.put("comAccountName", comAccountName);
            pageMap.put("hfComAccount", hfComAccount);
            pageMap.put("createdTimeYYYY", createdTimeYYYY);
            pageMap.put("createdTimeMM", createdTimeMM);
            pageMap.put("createdTimeDD", createdTimeDD);

            BigDecimal subTotal = BigDecimal.ZERO;
            int subCount = 0;

            List<HFMonthChargeRepairDetailBO> subBasList = hfMonthChargeRepairDetailBOList.subList(page * 10, (page + 1) * 10);
            for (HFMonthChargeRepairDetailBO hfMonthChargeRepairDetailBO : subBasList) {
                if (StringUtils.isNotEmpty(hfMonthChargeRepairDetailBO.getEmployeeName())
//                        && StringUtils.isNotEmpty(hfMonthChargeRepairDetailBO.getHfEmpAccount())
                    && StringUtils.isNotEmpty(hfMonthChargeRepairDetailBO.getIdNum())
                    && hfMonthChargeRepairDetailBO.getTotalAmount() != null) {
                    subTotal = subTotal.add(hfMonthChargeRepairDetailBO.getTotalAmount());
                    subCount++;
                }
            }
            pageMap.put("fillDataList", subBasList);

            total = total.add(subTotal);
            count += subCount;
            pageMap.put("subCount", subCount);
            pageMap.put("subTotal", subTotal);
            pageMap.put("createdBy", UserContext.getUser().getDisplayName());
            pageMap.put("companyIds", StringUtils.join(companyIdSet, ' '));
            if (isPageByComAccount) {
                pageMap.put("totalPage", String.valueOf(pages));
                pageMap.put("page", String.valueOf(page + 1));
            }
            resultList.add(pageMap);
        }

        for (Map<String, Object> map : resultList) {
            map.put("total", total);
            map.put("count", count);
        }
        return resultList;
    }

    /**
     * 整理补缴清册数据
     *
     * @param companyIdSet              所关联的客户ID的集合
     * @param hfMonthChargeReportBOList 雇员每月汇缴明细数据列表
     * @return 补缴清册数据列表
     */
    private List<HFMonthChargeRepairDetailBO> getRepairDetailList(Set<String> companyIdSet, List<HFMonthChargeReportBO> hfMonthChargeReportBOList) {
        List<HFMonthChargeRepairDetailBO> hfMonthChargeRepairDetailBOList = new ArrayList<>();
        try {
            // 添加一个临时非正式HFMonthChargeReportBO对象，方便处理最后的正式数据
            HFMonthChargeReportBO tempHfMonthChargeReportBO = new HFMonthChargeReportBO();
            hfMonthChargeReportBOList.add(tempHfMonthChargeReportBO);

            HFMonthChargeReportBO hfMonthChargeReportBO = hfMonthChargeReportBOList.get(0);
            HFMonthChargeRepairDetailBO hfMonthChargeRepairDetailBO = new HFMonthChargeRepairDetailBO();
            hfMonthChargeRepairDetailBO.setEmployeeName(hfMonthChargeReportBO.getEmployeeName());
            hfMonthChargeRepairDetailBO.setIdNum(hfMonthChargeReportBO.getIdNum());
            hfMonthChargeRepairDetailBO.setRepairReason(String.valueOf(repairReasonIcons[hfMonthChargeReportBO.getRepairReason() - 1]));
            hfMonthChargeRepairDetailBO.setStartMonth(hfMonthChargeReportBO.getSsMonthBelong());
            hfMonthChargeRepairDetailBO.setEndMonth(hfMonthChargeReportBO.getSsMonthBelong());
            hfMonthChargeRepairDetailBO.setAmountFir(hfMonthChargeReportBO.getAmount());
            hfMonthChargeRepairDetailBO.setRatioFir(CalculateSocialUtils.digitInSimpleFormat(hfMonthChargeReportBO.getRatio()
                .divide(BigDecimal.valueOf(2), 5, BigDecimal.ROUND_HALF_UP)
                .multiply(BigDecimal.valueOf(100))) + "%");
            hfMonthChargeRepairDetailBO.setHfEmpAccount(hfMonthChargeReportBO.getHfEmpAccount());
            hfMonthChargeRepairDetailBO.setMonths(1);
            hfMonthChargeRepairDetailBO.setRowNo(1);
            hfMonthChargeRepairDetailBOList.add(hfMonthChargeRepairDetailBO);

            for (int i = 1; i < hfMonthChargeReportBOList.size(); i++) {
                hfMonthChargeRepairDetailBO = hfMonthChargeRepairDetailBOList.get(hfMonthChargeRepairDetailBOList.size() - 1);
                YearMonth detailEndMonth = YearMonth.parse(hfMonthChargeRepairDetailBO.getEndMonth(), formatter);
                hfMonthChargeReportBO = hfMonthChargeReportBOList.get(i);
                String ratio = null;
                String repairReason = null;

                if (hfMonthChargeReportBO.getRatio() != null) {
                    ratio = CalculateSocialUtils.digitInSimpleFormat(hfMonthChargeReportBO.getRatio()
                        .divide(BigDecimal.valueOf(2), 5, BigDecimal.ROUND_HALF_UP)
                        .multiply(BigDecimal.valueOf(100))) + "%";
                }

                if (hfMonthChargeReportBO.getRepairReason() != null) {
                    repairReason = String.valueOf(repairReasonIcons[hfMonthChargeReportBO.getRepairReason() - 1]);
                }

                if (hfMonthChargeRepairDetailBO.getEmployeeName().equals(hfMonthChargeReportBO.getEmployeeName())
//                && hfMonthChargeRepairDetailBO.getHfEmpAccount().equals(hfMonthChargeReportBO.getHfEmpAccount())
                    && hfMonthChargeRepairDetailBO.getIdNum().equals(hfMonthChargeReportBO.getIdNum())) {
                    YearMonth ssMonthBelong = YearMonth.parse(hfMonthChargeReportBO.getSsMonthBelong(), formatter);

                    if (hfMonthChargeRepairDetailBO.getRepairReason().equals(repairReason)
                        && hfMonthChargeRepairDetailBO.getRatioFir().equals(ratio)
                        && hfMonthChargeRepairDetailBO.getAmountFir().equals(hfMonthChargeReportBO.getAmount())
                        && detailEndMonth.plusMonths(1).equals(ssMonthBelong)) {
                        hfMonthChargeRepairDetailBO.setEndMonth(hfMonthChargeReportBO.getSsMonthBelong());
                        hfMonthChargeRepairDetailBO.plusOneMonth();
                    } else {
                        repairPeriodEnd(hfMonthChargeRepairDetailBO, hfMonthChargeReportBO, repairReason, ratio, hfMonthChargeRepairDetailBOList);
                    }
                } else {
                    if (StringUtils.isNotEmpty(hfMonthChargeReportBO.getCompanyId())) {
                        companyIdSet.add(hfMonthChargeReportBO.getCompanyId());
                    }
                    repairPeriodEnd(hfMonthChargeRepairDetailBO, hfMonthChargeReportBO, repairReason, ratio, hfMonthChargeRepairDetailBOList);
                }
            }

            // 去除最后一个临时的对象
            hfMonthChargeRepairDetailBOList.remove(hfMonthChargeRepairDetailBOList.size() - 1);
        } catch (Exception e) {
            logApiUtil.error(LogMessage.create().setTitle("获取基本/补充公积金补缴清册导出数据列表")
                .setContent(e.getMessage()));
            throw e;
        }
        return hfMonthChargeRepairDetailBOList;
    }

    /**
     * 雇员补缴导出数据结束段数据处理
     *
     * @param hfMonthChargeRepairDetailBO     当前处理的雇员补缴导出数据
     * @param hfMonthChargeReportBO           不属于当前处理的补缴段的雇员每月汇缴明细数据
     * @param repairReason                    补缴原因
     * @param ratio                           缴存比例
     * @param hfMonthChargeRepairDetailBOList 雇员补缴导出数据列表
     */
    private void repairPeriodEnd(HFMonthChargeRepairDetailBO hfMonthChargeRepairDetailBO,
                                 HFMonthChargeReportBO hfMonthChargeReportBO,
                                 String repairReason,
                                 String ratio,
                                 List<HFMonthChargeRepairDetailBO> hfMonthChargeRepairDetailBOList) {
        hfMonthChargeRepairDetailBO.setSubAmountFir(
            hfMonthChargeRepairDetailBO.getAmountFir()
                .multiply(BigDecimal.valueOf(hfMonthChargeRepairDetailBO.getMonths())));
        String startMonth = hfMonthChargeRepairDetailBO.getStartMonth();
        String endMonth = hfMonthChargeRepairDetailBO.getEndMonth();
        StringBuilder sb = new StringBuilder();
        sb = sb.append(startMonth.substring(0, 4)).append(".").append(startMonth.substring(4, 6))
            .append("-").append(endMonth.substring(0, 4)).append(".").append(endMonth.substring(4, 6));
        hfMonthChargeRepairDetailBO.setRepairPeriodFir(sb.toString());
        hfMonthChargeRepairDetailBO.setTotalAmount(hfMonthChargeRepairDetailBO.getSubAmountFir());

        HFMonthChargeRepairDetailBO newHFMonthChargeRepairDetailBO = new HFMonthChargeRepairDetailBO();
        newHFMonthChargeRepairDetailBO.setEmployeeName(hfMonthChargeReportBO.getEmployeeName());
        newHFMonthChargeRepairDetailBO.setIdNum(hfMonthChargeReportBO.getIdNum());
        newHFMonthChargeRepairDetailBO.setRepairReason(repairReason);
        newHFMonthChargeRepairDetailBO.setStartMonth(hfMonthChargeReportBO.getSsMonthBelong());
        newHFMonthChargeRepairDetailBO.setEndMonth(hfMonthChargeReportBO.getSsMonthBelong());
        newHFMonthChargeRepairDetailBO.setAmountFir(hfMonthChargeReportBO.getAmount());
        newHFMonthChargeRepairDetailBO.setRatioFir(ratio);
        newHFMonthChargeRepairDetailBO.setHfEmpAccount(hfMonthChargeReportBO.getHfEmpAccount());
        newHFMonthChargeRepairDetailBO.setMonths(1);
        newHFMonthChargeRepairDetailBO.setRowNo(hfMonthChargeRepairDetailBOList.size() + 1);
        hfMonthChargeRepairDetailBOList.add(newHFMonthChargeRepairDetailBO);
    }
    /**
     * 导出公积金汇缴支付编辑/详情操作数据
     *
     * @param pageInfo 查询条件
     * @return 导出结果
     */
    @Override
    public PageRows<HfPaymentAccountReportBo> getOperateDetailReport(PageInfo pageInfo, String userId) {
        HfPaymentAccountBo hfPaymentAccountBo = pageInfo.toJavaObject(HfPaymentAccountBo.class);
        PageRows<HfPaymentAccountReportBo> result = PageKit.doSelectPage(pageInfo, () -> baseMapper.getOperateDetailReport(hfPaymentAccountBo));
        List<HfPaymentAccountReportBo> reportList = result.getRows();
        for(int i = 0; i < reportList.size(); i++){
            StringBuffer companyId = new StringBuffer();
            StringBuffer title = new StringBuffer();
            List<HfPaymentComBo> companyIdList = hfPaymentComMapper.getCompanyIdList(hfPaymentAccountBo.getPaymentId(),reportList.get(i).getComAccountId());
            for(HfPaymentComBo comBo : companyIdList){
                companyId = companyId.append(comBo.getCompanyId()).append(",");
                title = title.append(comBo.getTitle()).append(",");
            }
            reportList.get(i).setCompanyId(companyId.toString().substring(0, companyId.length() - 1));
            reportList.get(i).setTitle(title.toString().substring(0, title.length() - 1));
        }
        result.setRows(reportList);
        return result;
    }

    @Override
    public void getMonthChargeByInOut(HfEmpTaskHandleBo hfEmpTaskHandleBo) {
        HfMonthChargeBo paramBo = new HfMonthChargeBo();
        paramBo.setCompanyId(hfEmpTaskHandleBo.getCompanyId());
        paramBo.setEmployeeId(hfEmpTaskHandleBo.getEmployeeId());

        paramBo.setHfType(HfEmpTaskConstant.HF_TYPE_BASIC);
        HfMonthChargeBo resultBo = baseMapper.getMonthChargeByInOut(paramBo);

        if (resultBo != null) {
            hfEmpTaskHandleBo.setEmpBasStartMonth(resultBo.getSsMonthBelongStart());
            hfEmpTaskHandleBo.setEmpBasHandleMonth(resultBo.getHfMonth());
            hfEmpTaskHandleBo.setEmpBasEndMonth(resultBo.getSsMonthBelongEnd());
            hfEmpTaskHandleBo.setEmpBasStopHandleMonth(resultBo.getHfStopMonth());
        }
        resultBo = baseMapper.getMonthChargeByIn(paramBo);

        if (resultBo != null) {
            hfEmpTaskHandleBo.setEmpBasStartMonth(resultBo.getSsMonthBelongStart());
            hfEmpTaskHandleBo.setEmpBasHandleMonth(resultBo.getHfMonth());
        }
        resultBo = baseMapper.getMonthChargeByOut(paramBo);

        if (resultBo != null) {
            hfEmpTaskHandleBo.setEmpBasEndMonth(resultBo.getSsMonthBelongEnd());
            hfEmpTaskHandleBo.setEmpBasStopHandleMonth(resultBo.getHfStopMonth());
        }

        paramBo.setHfType(HfEmpTaskConstant.HF_TYPE_ADDED);
        resultBo = baseMapper.getMonthChargeByInOut(paramBo);

        if (resultBo != null) {
            hfEmpTaskHandleBo.setEmpAddStartMonth(resultBo.getSsMonthBelongStart());
            hfEmpTaskHandleBo.setEmpAddHandleMonth(resultBo.getHfMonth());
            hfEmpTaskHandleBo.setEmpAddEndMonth(resultBo.getSsMonthBelongEnd());
            hfEmpTaskHandleBo.setEmpAddStopHandleMonth(resultBo.getHfStopMonth());
        }
        resultBo = baseMapper.getMonthChargeByIn(paramBo);

        if (resultBo != null) {
            hfEmpTaskHandleBo.setEmpAddStartMonth(resultBo.getSsMonthBelongStart());
            hfEmpTaskHandleBo.setEmpAddHandleMonth(resultBo.getHfMonth());
        }
        resultBo = baseMapper.getMonthChargeByOut(paramBo);

        if (resultBo != null) {
            hfEmpTaskHandleBo.setEmpAddEndMonth(resultBo.getSsMonthBelongEnd());
            hfEmpTaskHandleBo.setEmpAddStopHandleMonth(resultBo.getHfStopMonth());
        }
    }

    @Override
    public List<HfEmpLastPaymentBO> searchByLastPaymentMonth(String companyId, String employeeId, String hfMonth) {
        return baseMapper.searchByLastPaymentMonth(companyId, employeeId, hfMonth);
    }
}
