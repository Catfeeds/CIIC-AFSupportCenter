package com.ciicsh.gto.afsupportcenter.socjob.service.impl;

import com.ciicsh.gto.afsupportcenter.socjob.dao.SsComAccountMapper;
import com.ciicsh.gto.afsupportcenter.socjob.dao.SsEmpBaseAdjustDetailMapper;
import com.ciicsh.gto.afsupportcenter.socjob.dao.SsEmpBaseDetailMapper;
import com.ciicsh.gto.afsupportcenter.socjob.dao.SsEmpBasePeriodMapper;
import com.ciicsh.gto.afsupportcenter.socjob.dao.SsMonthChargeItemMapper;
import com.ciicsh.gto.afsupportcenter.socjob.dao.SsMonthChargeMapper;
import com.ciicsh.gto.afsupportcenter.socjob.dao.SsMonthEmpChangeDetailMapper;
import com.ciicsh.gto.afsupportcenter.socjob.dao.SsMonthEmpChangeMapper;
import com.ciicsh.gto.afsupportcenter.socjob.dao.SsPaymentComMapper;
import com.ciicsh.gto.afsupportcenter.socjob.entity.SsEmpBaseAdjustDetail;
import com.ciicsh.gto.afsupportcenter.socjob.entity.SsEmpBaseDetail;
import com.ciicsh.gto.afsupportcenter.socjob.entity.SsMonthCharge;
import com.ciicsh.gto.afsupportcenter.socjob.entity.SsMonthChargeItem;
import com.ciicsh.gto.afsupportcenter.socjob.entity.SsMonthEmpChange;
import com.ciicsh.gto.afsupportcenter.socjob.entity.SsMonthEmpChangeDetail;
import com.ciicsh.gto.afsupportcenter.socjob.entity.SsPaymentCom;
import com.ciicsh.gto.afsupportcenter.socjob.entity.custom.SsAccountComExt;
import com.ciicsh.gto.afsupportcenter.socjob.entity.custom.SsEmpBaseArchiveExt;
import com.ciicsh.gto.afsupportcenter.socjob.entity.custom.SsEmpBasePeriodExt;
import com.ciicsh.gto.afsupportcenter.socjob.entity.custom.SsMonthChargeExt;
import com.ciicsh.gto.afsupportcenter.socjob.service.SsPaymentComService;
import com.ciicsh.gto.afsupportcenter.socjob.service.enums.ComputeType;
import com.ciicsh.gto.afsupportcenter.socjob.util.CommonUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    @Autowired
    private SsEmpBaseAdjustDetailMapper empBaseAdjustDetailMapper;

    @Autowired
    private SsMonthEmpChangeMapper monthEmpChangeMapper;

    @Autowired
    private SsMonthEmpChangeDetailMapper monthEmpChangeDetailMapper;

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

                        /*****生成雇员社保明细****/
                        //如果数据已经存在，先删除已经存在的数据
                        this.delMonthChangeInfos(accountComExt.getComAccountId(),paymentMonth);

                        //生成标准明细
                        List<SsEmpBaseArchiveExt> empBaseArchiveExts = empBasePeriodMapper.getEmpBaseArchiveExts(accountComExt.getComAccountId(),paymentMonth);
                        if(null != empBaseArchiveExts && empBaseArchiveExts.size() > 0){
                            empBaseArchiveExts.forEach(ext->this.createStandardMonthChange(ext,paymentMonth));
                        }

                        //生成非标准明细
                        List<SsEmpBasePeriodExt> empBasePeriodExts = convertNewEmpBasePeriodExt(accountComExt.getComAccountId(),paymentMonth);
                        if(null != empBasePeriodExts && empBasePeriodExts.size()>0){
                            empBasePeriodExts.forEach(ext->this.createNoStandardMonthChange(ext));
                        }

                        /******生成变更汇总表*****/
                        //如果变更汇总表已经存在，先删除存在的数据
                        this.delMonthEmpChangeInfos(accountComExt.getComAccountId(),paymentMonth);
                        //生成变更汇总表
                        this.createMonthEmpCharge(accountComExt.getComAccountId(),paymentMonth);

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
     * @param comAccountId 企业社保账户
     * @param paymentMonth 支付年月
     */
    private void delMonthChangeInfos(long comAccountId, String paymentMonth){
        List<SsMonthCharge> monthCharges = monthChargeMapper.getMonthChangesByCondition(comAccountId,paymentMonth);
        if(null != monthCharges && monthCharges.size() > 0){
            monthChargeMapper.delByCondition(comAccountId,paymentMonth);
            monthCharges.forEach(x->monthChargeItemMapper.delByMonthChargeId(x.getMonthChargeId()));
        }
    }

    /**
     * 根据条件删除雇员月度变更主表信息以及详细信息
     * @param comAccountId 企业社保账户
     * @param paymentMonth 支付年月
     */
    private void delMonthEmpChangeInfos(long comAccountId, String paymentMonth){
        List<SsMonthEmpChange> monthEmpChanges = monthEmpChangeMapper.getMonthEmpChangesByCondition(comAccountId,paymentMonth);
        if(null != monthEmpChanges && monthEmpChanges.size() > 0){
           monthEmpChangeMapper.delMonthEmpChangeByfCondition(comAccountId,paymentMonth);
           monthEmpChanges.forEach(x->monthEmpChangeDetailMapper.delByMonthEmpChangeId(x.getMonthEmpChangeId()));
        }
    }

    /**
     * 根据费用类别转换新数据
     * @param comAccountId 企业社保账户
     * @param paymentMonth 支付年月
     * @return 返回新的EmpBasePeriodExt类别
     */
    private List<SsEmpBasePeriodExt> convertNewEmpBasePeriodExt(Long comAccountId,String paymentMonth){
        List<SsEmpBasePeriodExt> newEmpBasePeriodExts = new ArrayList<>();
        List<SsEmpBasePeriodExt> empBasePeriodExts = empBasePeriodMapper.getEmpBasePeriodExts(comAccountId,paymentMonth);
        if(null != empBasePeriodExts && empBasePeriodExts.size()>0){
            empBasePeriodExts.forEach(ext->{
                if(ext.getCategory().equals("补缴")){
                    this.addEmpBasePeriodExtToList(newEmpBasePeriodExts,ext);
                }
                else if(ext.getCategoryName().equals("逆向调整")){
                    this.addEmpBasePeriodExtToList(newEmpBasePeriodExts,ext);
                }
                else{
                    empBasePeriodExts.add(ext);
                }
            });
        }
        return newEmpBasePeriodExts;
    }

    /**
     * 把新的EmpBasePeriodExt加入列表中
     * @param empBasePeriodExts
     * @param ext
     */
    private void addEmpBasePeriodExtToList(List<SsEmpBasePeriodExt> empBasePeriodExts,SsEmpBasePeriodExt ext){
        try {
            List<String> months = CommonUtils.getMonths(ext.getStartMonth(),ext.getEndMonth());
            if(null != months && months.size() > 0){
                months.forEach(x->empBasePeriodExts.add(cloneSsEmpBasePeriodExt(ext,x)));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }


    /**
     * 克隆SsEmpBasePeriodExt
     * @param basePeriodExt 旧的SsEmpBasePeriodExt
     * @param ssMonthBelong 所属月份
     * @return 返回新的SsEmpBasePeriodExt
     */
    private SsEmpBasePeriodExt cloneSsEmpBasePeriodExt(SsEmpBasePeriodExt basePeriodExt,String ssMonthBelong){
        SsEmpBasePeriodExt ext = new SsEmpBasePeriodExt();
        BeanUtils.copyProperties(basePeriodExt,ext);
        ext.setSsMonthBelong(ssMonthBelong);
        return ext;
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
        return monthCharge;
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
     */
    private void createNoStandardMonthChange(SsEmpBasePeriodExt ext){
        switch (ext.getCategory()){
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
                this.createNoMonthChangeInfoByBasePeriod(ext);
                break;
            case 8:
                this.createNoStandardMonthChargeByRefund(ext);
                break;
            case 9:
                this.createNoMonthChangeInfoByBaseAdjust(ext);
                break;
            default:
                break;
        }
    }

    /**
     * 根据雇员社保汇缴基数明细信息生成月度缴费明细
     */
    private void createNoMonthChangeInfoByBasePeriod(SsEmpBasePeriodExt ext){
        List<SsEmpBaseDetail> baseDetails = empBaseDetailMapper.getEmpBaseDetailsByPeriodId(ext.getEmpBasePeriodId());
        if(null != baseDetails && baseDetails.size() > 0){
            BigDecimal totalAmount = baseDetails.stream()
                .map(i -> i.getComAmount().add(i.getComAdditionAmount()).add(i.getEmpAmount()).add(i.getEmpAdditionAmount()))
                .reduce(new BigDecimal(0), (x, y)-> x.add(y));

            SsMonthCharge monthCharge = addNoStandardMonthChargeByBasePeriod(ext,totalAmount);
            if(null != monthCharge){
                baseDetails.forEach(x->addNoStandardMonthChargeItemByBasePeriod(x,monthCharge.getMonthChargeId(),ext.getCategory()));
            }
        }
    }

    /**
     * 根据雇员社保基数调整历史月差异信息生成月度缴费明细
     */
    private void createNoMonthChangeInfoByBaseAdjust(SsEmpBasePeriodExt ext){
        List<SsEmpBaseAdjustDetail> adjustDetails = empBaseAdjustDetailMapper.getEmpBaseAdjustDetailByBaseAdjustId(ext.getEmpBasePeriodId());
        if(null != adjustDetails && adjustDetails.size() > 0){
            BigDecimal totalAmount = adjustDetails.stream()
                .map(i -> i.getComAmount().add(i.getComAdditionAmount()).add(i.getEmpAmount()).add(i.getEmpAdditionAmount()))
                .reduce(new BigDecimal(0), (x, y)-> x.add(y));

            SsMonthCharge monthCharge = addNoStandardMonthChargeByBasePeriod(ext,totalAmount);
            if(null != monthCharge){
                adjustDetails.forEach(x->addNoStandardMonthChargeItemByBaseAdjust(x,monthCharge.getMonthChargeId()));
            }
        }
    }


    /**
     * 添加非标准月度缴费明细（By BasePeriod）
     * @param ext 雇员正常汇缴社保的基数分段扩展信息
     * @param totalAmount 社保总金额
     * @return 返回月度缴费明细对象
     */
    private SsMonthCharge addNoStandardMonthChargeByBasePeriod(SsEmpBasePeriodExt ext,BigDecimal totalAmount){
        SsMonthCharge monthCharge = new SsMonthCharge();
        monthCharge.setComAccountId(ext.getComAccountId());
        monthCharge.setSsMonthBelong(ext.getSsMonthBelong());
        monthCharge.setSsMonth(ext.getSsMonth());
        monthCharge.setEmployeeId(ext.getEmployeeId());
        monthCharge.setEmpArchiveId(ext.getEmpArchiveId().toString());
        monthCharge.setBaseAmount(ext.getBaseAmount()); //社保基数
        if(ext.getCategory() == 6 || ext.getCategory() == 7 ){
            totalAmount = totalAmount.negate();
        }
        monthCharge.setTotalAmount(totalAmount); //总金额
        monthCharge.setCostCategory(ext.getCategory());
        monthCharge.setActive(true);
        monthCharge.setCreatedTime(LocalDateTime.now());
        monthCharge.setCreatedBy("system");
        monthCharge.setModifiedTime(LocalDateTime.now());
        monthCharge.setModifiedBy("system");
        monthChargeMapper.insert(monthCharge);
        return monthCharge;
    }



    /**
     * 添加非标准月度缴费明细（By Refund）
     * @param ext 雇员正常汇缴社保的基数分段扩展信息
     * @return 返回月度缴费明细对象
     */
    private void createNoStandardMonthChargeByRefund(SsEmpBasePeriodExt ext){
        SsMonthCharge monthCharge = new SsMonthCharge();
        monthCharge.setComAccountId(ext.getComAccountId());
        monthCharge.setSsMonthBelong(ext.getSsMonthBelong());
        monthCharge.setSsMonth(ext.getSsMonth());
        monthCharge.setEmployeeId(ext.getEmployeeId());
        monthCharge.setEmpArchiveId(ext.getEmpArchiveId().toString());
        monthCharge.setBaseAmount(BigDecimal.valueOf(0)); //社保基数
        monthCharge.setTotalAmount(ext.getTotalAmount()); //总金额
        monthCharge.setCostCategory(ext.getCategory());
        monthCharge.setActive(true);
        monthCharge.setCreatedTime(LocalDateTime.now());
        monthCharge.setCreatedBy("system");
        monthCharge.setModifiedTime(LocalDateTime.now());
        monthCharge.setModifiedBy("system");
        monthChargeMapper.insert(monthCharge);
    }


    /**
     * 添加非标准月度缴费明细详细（By BasePeriod）
     * @param empBaseDetail
     * @param monthChargeId
     */
    private void addNoStandardMonthChargeItemByBasePeriod(SsEmpBaseDetail empBaseDetail,long monthChargeId,Integer category){
        SsMonthChargeItem monthChargeItem = new SsMonthChargeItem();
        monthChargeItem.setMonthChargeId(monthChargeId);
        monthChargeItem.setSsType(empBaseDetail.getSsType());
        monthChargeItem.setSsTypeName(empBaseDetail.getSsTypeName());
        BigDecimal comAmount = empBaseDetail.getComAmount().add(empBaseDetail.getComAdditionAmount());
        BigDecimal empAmount = empBaseDetail.getEmpAmount().add(empBaseDetail.getEmpAdditionAmount());
        BigDecimal totalAmount = empBaseDetail.getComAmount().add(empBaseDetail.getComAdditionAmount()).add(empBaseDetail.getEmpAmount()).add(empBaseDetail.getEmpAdditionAmount());
        //转出或者封存
        if(category == 6 || category == 7){
            comAmount = comAmount.negate();
            empAmount = empAmount.negate();
            totalAmount = totalAmount.negate();
        }
        monthChargeItem.setComAmount(comAmount);
        monthChargeItem.setEmpAmount(empAmount);
        monthChargeItem.setSubTotalAmount(totalAmount);
        monthChargeItem.setActive(true);
        monthChargeItem.setCreatedTime(LocalDateTime.now());
        monthChargeItem.setCreatedBy("system");
        monthChargeItem.setModifiedTime(LocalDateTime.now());
        monthChargeItem.setModifiedBy("system");
        monthChargeItemMapper.insert(monthChargeItem);
    }


    /**
     * 添加非标准月度缴费明细详细（By BaseAdjust）
     * @param baseAdjustDetail
     * @param monthChargeId
     */
    private void addNoStandardMonthChargeItemByBaseAdjust(SsEmpBaseAdjustDetail baseAdjustDetail,long monthChargeId){
        SsMonthChargeItem monthChargeItem = new SsMonthChargeItem();
        monthChargeItem.setMonthChargeId(monthChargeId);
        monthChargeItem.setSsType(baseAdjustDetail.getSsType());
        monthChargeItem.setSsTypeName(baseAdjustDetail.getSsTypeName());
        BigDecimal comAmount = baseAdjustDetail.getComAmount().add(baseAdjustDetail.getComAdditionAmount());
        BigDecimal empAmount = baseAdjustDetail.getEmpAmount().add(baseAdjustDetail.getEmpAdditionAmount());
        BigDecimal totalAmount = baseAdjustDetail.getComAmount().add(baseAdjustDetail.getComAdditionAmount()).add(baseAdjustDetail.getEmpAmount()).add(baseAdjustDetail.getEmpAdditionAmount());
        monthChargeItem.setComAmount(comAmount);
        monthChargeItem.setEmpAmount(empAmount);
        monthChargeItem.setSubTotalAmount(totalAmount);
        monthChargeItem.setActive(true);
        monthChargeItem.setCreatedTime(LocalDateTime.now());
        monthChargeItem.setCreatedBy("system");
        monthChargeItem.setModifiedTime(LocalDateTime.now());
        monthChargeItem.setModifiedBy("system");
        monthChargeItemMapper.insert(monthChargeItem);
    }


    /**
     * 生成雇员月度变更信息
     * @param comAccountId 企业社保账户
     * @param paymentMonth
     */
    private void createMonthEmpCharge(long comAccountId, String paymentMonth){
        List<SsMonthChargeExt> monthChargeExts = monthChargeMapper.getSsMonthChargeExts(comAccountId,paymentMonth);
        List<SsMonthChargeExt> yysMonthChargeExts = new ArrayList<>();
        List<SsMonthChargeExt> gsyMonthChargeExts = new ArrayList<>();
        if(null != monthChargeExts && monthChargeExts.size()>0){
            yysMonthChargeExts = monthChargeExts.stream().filter(x->x.getSsType().equals("1")||x.getSsType().equals("2")||x.getSsType().equals("5")).collect(Collectors.toList());
            gsyMonthChargeExts = monthChargeExts.stream().filter(x->x.getSsType().equals("3")||x.getSsType().equals("4")).collect(Collectors.toList());
        }

        if(null != yysMonthChargeExts && yysMonthChargeExts.size() > 0){
            SsMonthEmpChange monthEmpChange = addMonthEmpChange(comAccountId,paymentMonth, ComputeType.YYS.toString());
            if(monthEmpChange != null){
                this.createSsMonthEmpChangeDetial(monthEmpChange,yysMonthChargeExts);
            }
        }

        if(null != gsyMonthChargeExts && gsyMonthChargeExts.size() > 0){
            SsMonthEmpChange monthEmpChange = addMonthEmpChange(comAccountId,paymentMonth, ComputeType.GSY.toString());
            if(monthEmpChange != null){
                this.createSsMonthEmpChangeDetial(monthEmpChange,gsyMonthChargeExts);
            }

        }
    }


    /**
     * 增加雇员月度变更主表信息
     * @param comAccountId 企业社保账户
     * @param paymentMonth 支付年月
     * @param computeType 变更汇总表类型
     * @return
     */
    private SsMonthEmpChange addMonthEmpChange(long comAccountId,String paymentMonth,String computeType){
        SsMonthEmpChange ssMonthEmpChange = new SsMonthEmpChange();
        ssMonthEmpChange.setSsMonth(paymentMonth);
        ssMonthEmpChange.setLastComputeTime(LocalDateTime.now());
        ssMonthEmpChange.setComputeUserId("system");
        ssMonthEmpChange.setComputeType(computeType);
        ssMonthEmpChange.setComAccountId(comAccountId);
        ssMonthEmpChange.setActive(true);
        ssMonthEmpChange.setCreatedTime(LocalDateTime.now());
        ssMonthEmpChange.setCreatedBy("system");
        ssMonthEmpChange.setModifiedTime(LocalDateTime.now());
        ssMonthEmpChange.setModifiedBy("system");
        monthEmpChangeMapper.insert(ssMonthEmpChange);
        return ssMonthEmpChange;
    }


    /**
     * 创建雇员月度变更表明细信息
     * @param monthEmpChange 雇员月度变更主表信息
     * @param chargeExts
     */
    private void createSsMonthEmpChangeDetial(SsMonthEmpChange monthEmpChange,List<SsMonthChargeExt> chargeExts){
        //按雇员分组
        Map<String,List<SsMonthChargeExt>> empMap = chargeExts.stream().collect(Collectors.groupingBy(SsMonthChargeExt::getEmployeeId));
        if(null != empMap && empMap.size() > 0){
            empMap.forEach((employeeId,groupByEmpValue)->{
                //按费用类别分组
                Map<Integer,List<SsMonthChargeExt>> categoryMap = groupByEmpValue.stream().collect(Collectors.groupingBy(SsMonthChargeExt::getCategory));
                if(null != categoryMap && categoryMap.size() > 0){
                    categoryMap.forEach((category,groupByCategoryValue)->{
                        //按社保类型分组
                        Map<String,List<SsMonthChargeExt>> ssTypeMap = groupByCategoryValue.stream().collect(Collectors.groupingBy(SsMonthChargeExt::getSsType));
                        if(null != ssTypeMap && ssTypeMap.size()>0){
                            ssTypeMap.forEach((ssType,groupBySsTypeValue)->{
                                if(groupBySsTypeValue.size() > 0){
                                    this.addSsMonthEmpChangeDetial(monthEmpChange.getMonthEmpChangeId(),employeeId,category,groupBySsTypeValue);
                                }
                            });
                        }
                    });
                }
            });
        }
    }

    /**
     * 增加雇员月度变更表明细
     * @param monthEmpChangeId 雇员月度变更主表ID
     * @param employeeId 雇员ID
     * @param chargeExts
     */
    private void addSsMonthEmpChangeDetial(long monthEmpChangeId,String employeeId,Integer category,List<SsMonthChargeExt> chargeExts){
        SsMonthChargeExt chargeExt = chargeExts.get(0);
        SsMonthEmpChangeDetail changeDetail = new SsMonthEmpChangeDetail();
        changeDetail.setMonthEmpChangeId(monthEmpChangeId);
        changeDetail.setEmployeeId(employeeId);
        changeDetail.setChangeType(category);
        changeDetail.setChangeTypeName(chargeExt.getCategoryName());
        changeDetail.setBaseAmount(chargeExt.getBaseAmount());
        changeDetail.setSsType(chargeExt.getSsType());
        changeDetail.setSsTypeName(chargeExt.getSsTypeName());
        if(category != 4 && category != 9){
            changeDetail.setComAmount(chargeExts.stream().map(p->p.getComAmount()).reduce(new BigDecimal(0),(x,y)->x.add(y)));
            changeDetail.setEmpAmount(chargeExts.stream().map(p->p.getEmpAmount()).reduce(new BigDecimal(0),(x,y)->x.add(y)));
        }
        else{
            changeDetail.setComCompensateAmount(chargeExts.stream().map(p->p.getComAmount()).reduce(new BigDecimal(0),(x,y)->x.add(y)));
            changeDetail.setEmpCompensateAmount(chargeExts.stream().map(p->p.getEmpAmount()).reduce(new BigDecimal(0),(x,y)->x.add(y)));
        }

        changeDetail.setActive(true);
        changeDetail.setCreatedTime(LocalDateTime.now());
        changeDetail.setCreatedBy("system");
        changeDetail.setModifiedTime(LocalDateTime.now());
        changeDetail.setModifiedBy("system");
        monthEmpChangeDetailMapper.insert(changeDetail);
    }


    /**
     * 社保缴纳通知单序号
     */
    private Hashtable<String,Integer> paySeqTable(){
        Hashtable<String,Integer> table = new Hashtable<>();
        table.put("单位应缴纳社会保险费",1);
        table.put("单位应补缴历年社会保险费",2);
        table.put("个人应缴纳社会保险费",3);
        table.put("个人应补缴历月社会保险费",4);
        table.put("其他应缴纳社会保险费",5);
        table.put("预缴社会保险费",6);
        table.put("单位缓缴社会保险费",8);
        return table;
    }
}