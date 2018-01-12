package com.ciicsh.gto.afsupportcenter.socjob.service.impl;

import com.ciicsh.gto.afsupportcenter.socjob.dao.SsComAccountMapper;
import com.ciicsh.gto.afsupportcenter.socjob.dao.SsEmpBaseDetailMapper;
import com.ciicsh.gto.afsupportcenter.socjob.dao.SsEmpBasePeriodMapper;
import com.ciicsh.gto.afsupportcenter.socjob.dao.SsMonthChargeItemMapper;
import com.ciicsh.gto.afsupportcenter.socjob.dao.SsMonthChargeMapper;
import com.ciicsh.gto.afsupportcenter.socjob.dao.SsPaymentComMapper;
import com.ciicsh.gto.afsupportcenter.socjob.entity.SsEmpBaseDetail;
import com.ciicsh.gto.afsupportcenter.socjob.entity.SsMonthCharge;
import com.ciicsh.gto.afsupportcenter.socjob.entity.SsMonthChargeItem;
import com.ciicsh.gto.afsupportcenter.socjob.entity.SsPaymentCom;
import com.ciicsh.gto.afsupportcenter.socjob.entity.custom.SsAccountComExt;
import com.ciicsh.gto.afsupportcenter.socjob.entity.custom.SsEmpBaseArchiveExt;
import com.ciicsh.gto.afsupportcenter.socjob.service.SsPaymentComService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by houwanhua on 2018/1/11.
 */
@Service
public class SsPaymentComServiceImpl implements SsPaymentComService {

    @Autowired
    private SsComAccountMapper accountMapper;

    @Autowired
    private SsPaymentComMapper paymentComMapper;

    @Autowired
    private SsMonthChargeMapper monthChargeMapper;

    @Autowired
    private SsMonthChargeItemMapper monthChargeItemMapper;

    @Autowired
    private SsEmpBasePeriodMapper empBasePeriodMapper;

    @Autowired
    private SsEmpBaseDetailMapper empBaseDetailMapper;

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void generateSocPaymentInfo(String paymentMonth) {
        List<SsAccountComExt> accountComExts = accountMapper.getSsComAccounts();
        if(null != accountComExts && accountComExts.size() > 0){
            accountComExts.forEach(accountComExt -> {
                Integer val = paymentComMapper.ifExistPayment(accountComExt.getComAccountId(),paymentMonth);
                if(val <= 0){

                    //新增支付信息
                    Integer result = addPaymentCom(accountComExt,paymentMonth);
                    if(result > 0){
                        //生成缴费明细
                        List<SsEmpBaseArchiveExt> empBaseArchiveExts = empBasePeriodMapper.getEmpBaseArchiveExts(accountComExt.getComAccountId(),paymentMonth);
                        if(null != empBaseArchiveExts && empBaseArchiveExts.size() > 0){
                            empBaseArchiveExts.forEach(ext->{
                                //如果数据已经存在，先删除已经存在的数据
                                this.delMonthChangeInfos(ext,paymentMonth);
                                //生成标准明细
                                this.createStandardMonthChange(ext,paymentMonth);
                                //生成非标准明细
                                this.createNoStandardMonthChange(ext,paymentMonth);
                            });
                        }

                        //生成变更汇总表

                        //生成社保通知书
                    }
                }
            });
        }
    }

    /**
     * 新增支付信息
     * @param ext 企业账户扩展信息
     * @param paymentMonth 支付年月
     * @return
     */
    private Integer addPaymentCom(SsAccountComExt ext, String paymentMonth){
        SsPaymentCom paymentCom = new SsPaymentCom();
        paymentCom.setComAccountId(ext.getComAccountId());
        paymentCom.setCompanyId(ext.getCompanyId());
        paymentCom.setPaymentMonth(paymentMonth);
        paymentCom.setPaymentState(1);
        paymentCom.setActive(true);
        paymentCom.setCreatedTime(LocalDateTime.now());
        paymentCom.setCreatedBy("system");
        paymentCom.setModifiedTime(LocalDateTime.now());
        paymentCom.setModifiedBy("system");
        return paymentComMapper.insert(paymentCom);
    }

    /**
     * 根据条件删除月度缴费明细信息以及详细信息
     * @param ext 雇员本地社保档案扩展信息
     * @param paymentMonth 支付年月
     */
    private void delMonthChangeInfos(SsEmpBaseArchiveExt ext, String paymentMonth){
        List<SsMonthCharge> monthCharges = monthChargeMapper.getMonthChangesByCondition(ext.getComAccountId(),ext.getEmployeeId(),paymentMonth);
        if(null != monthCharges && monthCharges.size() > 0){
            monthChargeMapper.delByCondition(ext.getComAccountId(),ext.getEmployeeId(),paymentMonth);
            monthCharges.forEach(x->{
                monthChargeItemMapper.delByMonthChargeId(x.getMonthChargeId());
            });
        }
    }

    /**
     * 生成标准月度缴费明细信息以及详细信息
     * @param ext 雇员本地社保档案扩展信息
     * @param paymentMonth 支付年月
     */
    private void createStandardMonthChange(SsEmpBaseArchiveExt ext, String paymentMonth){
        List<SsEmpBaseDetail> baseDetails = empBaseDetailMapper.getEmpBaseDetailsByPeriodId(ext.getEmpBasePeriodId());
        if(null != baseDetails && baseDetails.size() > 0){
            BigDecimal totalAmount = baseDetails.stream()
                    .map(i -> i.getComAmount().add(i.getComAdditionAmount()).add(i.getEmpAmount()).add(i.getEmpAdditionAmount()))
                    .reduce(new BigDecimal(0), (x, y)-> x.add(y));

            SsMonthCharge monthCharge = addStandardMonthCharge(ext,paymentMonth,totalAmount);
            if(null != monthCharge){
                baseDetails.forEach(x->addStandardMonthChargeItem(x,monthCharge.getMonthChargeId()));
            }
        }
    }

    /**
     * 添加标准月度缴费明细
     * @param ext 雇员本地社保档案扩展信息
     * @param paymentMonth 支付年月
     * @param totalAmount 社保总金额
     * @return 返回月度缴费明细对象
     */
    private SsMonthCharge addStandardMonthCharge(SsEmpBaseArchiveExt ext, String paymentMonth,BigDecimal totalAmount){
        SsMonthCharge monthCharge = new SsMonthCharge();
        monthCharge.setComAccountId(ext.getComAccountId());
        monthCharge.setSsMonthBelong(paymentMonth);
        monthCharge.setSsMonth(paymentMonth);
        monthCharge.setEmployeeId(ext.getEmployeeId());
        monthCharge.setEmpArchiveId(ext.getEmpArchiveId().toString());
        monthCharge.setBaseAmount(ext.getBaseAmount()); //社保基数
        monthCharge.setTotalAmount(totalAmount); //总金额
        monthCharge.setCostCategory(1);
        monthCharge.setActive(true);
        monthCharge.setCreatedTime(LocalDateTime.now());
        monthCharge.setCreatedBy("system");
        monthCharge.setModifiedTime(LocalDateTime.now());
        monthCharge.setModifiedBy("system");
        monthChargeMapper.insert(monthCharge);
        return monthChargeMapper.selectOne(monthCharge);
    }


    /**
     * 添加标准月度缴费明细详细
     * @param empBaseDetail
     * @param monthChargeId
     */
    private void addStandardMonthChargeItem(SsEmpBaseDetail empBaseDetail,long monthChargeId){
        SsMonthChargeItem monthChargeItem = new SsMonthChargeItem();
        monthChargeItem.setMonthChargeId(monthChargeId);
        monthChargeItem.setSsType(empBaseDetail.getSsType());
        monthChargeItem.setSsTypeName(empBaseDetail.getSsTypeName());
        monthChargeItem.setComAmount(empBaseDetail.getComAmount().add(empBaseDetail.getComAdditionAmount()));
        monthChargeItem.setEmpAmount(empBaseDetail.getEmpAmount().add(empBaseDetail.getEmpAdditionAmount()));
        monthChargeItem.setSubTotalAmount(empBaseDetail.getComAmount().add(empBaseDetail.getComAdditionAmount()).add(empBaseDetail.getEmpAmount()).add(empBaseDetail.getEmpAdditionAmount()));
        monthChargeItem.setActive(true);
        monthChargeItem.setCreatedTime(LocalDateTime.now());
        monthChargeItem.setCreatedBy("system");
        monthChargeItem.setModifiedTime(LocalDateTime.now());
        monthChargeItem.setModifiedBy("system");
        monthChargeItemMapper.insert(monthChargeItem);
    }

    /**
     * 生成非标准月度缴费明细信息以及详细信息
     * @param ext 雇员本地社保档案扩展信息
     * @param paymentMonth 支付年月
     */
    private void createNoStandardMonthChange(SsEmpBaseArchiveExt ext, String paymentMonth){

    }


}
