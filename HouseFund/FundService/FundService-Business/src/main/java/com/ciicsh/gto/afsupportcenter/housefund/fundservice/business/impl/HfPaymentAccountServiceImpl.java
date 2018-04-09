package com.ciicsh.gto.afsupportcenter.housefund.fundservice.business.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.toolkit.StringUtils;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.HfPaymentAccountBo;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.payment.HFNetBankComAccountBO;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.payment.HFNetBankExportBO;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.constant.HfEmpTaskConstant;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.dao.HfPaymentAccountMapper;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.dao.HfPaymentComMapper;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.dao.HfPaymentMapper;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.HfPayment;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.HfPaymentAccount;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.business.HfPaymentAccountService;
import com.ciicsh.gto.afsupportcenter.util.CalculateSocialUtils;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageKit;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResult;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResultKit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * <p>
 * 公积金汇缴支付公司账户名单 服务实现类
 * </p>
 */
@Service
public class HfPaymentAccountServiceImpl extends ServiceImpl<HfPaymentAccountMapper, HfPaymentAccount> implements
    HfPaymentAccountService {

    @Autowired
    HfPaymentMapper hfPaymentMapper;
    @Autowired
    HfPaymentAccountMapper hfPaymentAccountMapper;
    @Autowired
    HfPaymentComMapper hfPaymentComMapper;

    private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("uuuu-MM-dd");
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuuMM");

    @Override
    public PageRows<HfPaymentAccountBo> getMakePayLists(PageInfo pageInfo){
        HfPaymentAccountBo hfPaymentAccountBo = pageInfo.toJavaObject(HfPaymentAccountBo.class);
        return PageKit.doSelectPage(pageInfo, () -> hfPaymentAccountMapper.getMakePayLists(hfPaymentAccountBo));
    }
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public JsonResult delHfPayment(String paymentId){
        //判断是否符合删除条件
        Integer paymentState=hfPaymentMapper.selectById(paymentId).getPaymentState();
        if(paymentState!=1 &&  paymentState!=4 &&  paymentState!=2){
            return JsonResultKit.ofError("删除操作失败！原因是支付状态不符合删除条件。");
        }
        try{
            //删除payment_com
            Map map=new HashMap();
            map.put("payment_id",paymentId);
            hfPaymentComMapper.deleteByMap(map);
            //更新payment_account  暂用Wrapper后续修改掉
            HfPaymentAccount hfPaymentAccount=new HfPaymentAccount();
            hfPaymentAccount.setPaymentId(Long.valueOf(0));
            Wrapper<HfPaymentAccount> wrapperAccount = new EntityWrapper();
            wrapperAccount.where(" is_active = 1 AND payment_id={0}", paymentId);
            hfPaymentAccountMapper.update(hfPaymentAccount,wrapperAccount);
            //更新payment   暂用Wrapper后续修改掉
            HfPayment hfPayment =new HfPayment();
            hfPayment.setActive(false);
            Wrapper<HfPayment> wrapperPayment = new EntityWrapper();
            wrapperPayment.where(" is_active = 1 AND payment_id={0}", paymentId);
            hfPaymentMapper.update(hfPayment,wrapperPayment);
        }catch (Exception e){
            return JsonResultKit.ofError("删除操作失败！");
        }
        return JsonResultKit.of("删除操作成功！");
    }

    @Override
    public List<HFNetBankComAccountBO> getComAccountByPaymentId(Long paymentId) {
        return baseMapper.getComAccountByPaymentId(paymentId);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public boolean updatePaymentInfo(Long pkId, String remark, Integer payStatus) {
        HfPayment hfPayment = new HfPayment();
        hfPayment.setPaymentId(pkId);
        hfPayment = hfPaymentMapper.selectOne(hfPayment);
        if(null != hfPayment){
            switch (payStatus){
                case -1:
                    hfPayment.setPaymentState(4);
                    hfPayment.setRejectionRemark(remark);
                    break;
                case 8:
                    hfPayment.setPaymentState(5);
                    break;
                case 9:
                    hfPayment.setFinancePaymentDate(new Date());
                    break;
            }
            hfPayment.setModifiedBy("system");
            hfPayment.setModifiedTime(new Date());
            Integer val = hfPaymentMapper.updateById(hfPayment);
            if(val > 0){
                return true;
            }
            else {
                return false;
            }
        }
        else{
            return false;
        }
    }

    /**
     * 设置大库（外包）补缴数据
     *
     * @param repairMap
     * @param hfComAccount
     * @param hfMonth
     * @param repairList
     */
    public void setBigStorageNetBankRepairData(Map<String, String> repairMap,
                                                  String hfComAccount,
                                                  Integer hfType,
                                                  String hfMonth,
                                                  List<HFNetBankExportBO> repairList) {
        if (hfType.equals(HfEmpTaskConstant.HF_TYPE_ADDED) && hfComAccount.endsWith("205")) {
            hfComAccount = hfComAccount.substring(0, hfComAccount.length() - 3);
        }
        if (hfComAccount.length() > 4) {
            hfComAccount = hfComAccount.substring(hfComAccount.length() - 4);
        }
        String key = hfComAccount + "BJ" + hfMonth.substring(4) + ".TXT";

        String repairStr = getEmpRepairData(repairList);

        if (StringUtils.isNotEmpty(repairStr)) {
            repairMap.put(key, repairStr);
        }
    }

    /**
     * 设置大库（外包）变更数据
     *
     * @param changeMap
     * @param hfComAccount
     * @param hfMonth
     * @param newList
     * @param inList
     * @param outList
     */
    public void setBigStorageNetBankChangeData(Map<String, String> changeMap,
                                                  String hfComAccount,
                                                  Integer hfType,
                                                  String hfMonth,
                                                  List<HFNetBankExportBO> newList,
                                                  List<HFNetBankExportBO> inList,
                                                  List<HFNetBankExportBO> outList) {
        if (hfType.equals(HfEmpTaskConstant.HF_TYPE_ADDED) && hfComAccount.endsWith("205")) {
            hfComAccount = hfComAccount.substring(0, hfComAccount.length() - 3);
        }
        if (hfComAccount.length() > 4) {
            hfComAccount = hfComAccount.substring(hfComAccount.length() - 4);
        }
        String changeStr = getEmpRepairData(newList);

        if (StringUtils.isNotEmpty(changeStr)) {
            String key = hfComAccount + "KH" + hfMonth.substring(4) + ".TXT";
            changeMap.put(key, changeStr);
        }
        changeStr = getEmpInData(inList);

        if (StringUtils.isNotEmpty(changeStr)) {
            String key = hfComAccount + "ZR" + hfMonth.substring(4) + ".TXT";
            changeMap.put(key, changeStr);
        }
        changeStr = getEmpOutData(outList);

        if (StringUtils.isNotEmpty(changeStr)) {
            String key = hfComAccount + "FC" + hfMonth.substring(4) + ".TXT";
            changeMap.put(key, changeStr);
        }
    }

    /**
     * 设置独立户补缴数据
     *
     * @param repairMap
     * @param hfComAccount
     * @param hfMonth
     * @param repairList
     */
    public void setIndependentNetBankRepairData(Map<String, String> repairMap,
                                                   String hfComAccount,
                                                   Integer hfType,
                                                   String hfMonth,
                                                   List<HFNetBankExportBO> repairList) {
        if (hfType.equals(HfEmpTaskConstant.HF_TYPE_ADDED) && hfComAccount.endsWith("205")) {
            hfComAccount = hfComAccount.substring(0, hfComAccount.length() - 3);
        }
        if (hfComAccount.length() > 4) {
            hfComAccount = hfComAccount.substring(hfComAccount.length() - 4);
        }
        String key = "BJ" + hfComAccount + hfMonth.substring(4) + ".TXT";

        String repairStr = getEmpRepairData(repairList);

        if (StringUtils.isNotEmpty(repairStr)) {
            repairMap.put(key, repairStr);
        }
    }

    /**
     * 设置独立户变更数据
     *
     * @param changeMap
     * @param hfComAccount
     * @param hfMonth
     * @param newList
     * @param inList
     * @param outList
     */
    public void setIndependentNetBankChangeData(Map<String, String> changeMap,
                                                   String hfComAccount,
                                                   Integer hfType,
                                                   String hfMonth,
                                                   List<HFNetBankExportBO> newList,
                                                   List<HFNetBankExportBO> inList,
                                                   List<HFNetBankExportBO> outList) {
        if (hfType.equals(HfEmpTaskConstant.HF_TYPE_ADDED) && hfComAccount.endsWith("205")) {
            hfComAccount = hfComAccount.substring(0, hfComAccount.length() - 3);
        }
        if (hfComAccount.length() > 4) {
            hfComAccount = hfComAccount.substring(hfComAccount.length() - 4);
        }
        String key = "BG" + hfComAccount + hfMonth.substring(4) + ".TXT";

        String changeStr = getIndependentChangeData(newList, inList, outList);

        if (StringUtils.isNotEmpty(changeStr)) {
            changeMap.put(key, changeStr);
        }
    }

    /**
     * 获取独立户的变更数据
     *
     * @param newList
     * @param inList
     * @param outList
     * @return
     */
    private String getIndependentChangeData(List<HFNetBankExportBO> newList,
                                              List<HFNetBankExportBO> inList,
                                              List<HFNetBankExportBO> outList) {
        StringBuilder stringBuilder = null;
        String str = getEmpNewData(newList);

        if (StringUtils.isNotEmpty(str)) {
            stringBuilder = new StringBuilder();
            stringBuilder.append(str);
        }
        str = getEmpInData(inList);

        if (StringUtils.isNotEmpty(str)) {
            if (stringBuilder == null) {
                stringBuilder = new StringBuilder();
            } else {
                stringBuilder.append("\r\n");
            }

            stringBuilder.append(str);
        }
        str = getEmpOutData(outList);

        if (StringUtils.isNotEmpty(str)) {
            if (stringBuilder == null) {
                stringBuilder = new StringBuilder();
            } else {
                stringBuilder.append("\r\n");
            }

            stringBuilder.append(str);
        }

        if (stringBuilder != null) {
            return stringBuilder.toString();
        }

        return null;
    }

    /**
     * 获取开户数据
     *
     * @param newList
     * @return
     */
    private String getEmpNewData(List<HFNetBankExportBO> newList) {
        if (CollectionUtils.isEmpty(newList)) {
            return null;
        }

        // 序号|1|||姓名|单边比例|单边比例|||缴费金额|1010|身份证号码|出生年月日|性别|单边金额|单边金额||基数|||||||||-1|
        String newTemplate = "|1|||%1$s|%2$s|%3$s|||%4$s|1010|%5$s|%6$s|%7$s|%8$s|%9$s||%10$s|||||||||-1|";

        StringBuilder stringBuilder = new StringBuilder();
        HFNetBankExportBO hfNetBankExportBO;
        int size = newList.size();

        if (size > 1) {
            for (int i = 0; i < size - 1; i++) {
                hfNetBankExportBO = newList.get(i);
                stringBuilder.append(i + 1);
                stringBuilder.append(
                    String.format(newTemplate,
                        hfNetBankExportBO.getEmployeeName(),
                        CalculateSocialUtils.digitInSimpleFormat(hfNetBankExportBO.getRatioCom()),
                        CalculateSocialUtils.digitInSimpleFormat(hfNetBankExportBO.getRatioEmp()),
                        CalculateSocialUtils.digitInSimpleFormat(hfNetBankExportBO.getAmount()),
                        hfNetBankExportBO.getIdNum(),
                        (hfNetBankExportBO.getBirthday() != null)? hfNetBankExportBO.getBirthday().format(dateFormatter) : "",
                        (hfNetBankExportBO.getGender())? "01" : "02",
                        CalculateSocialUtils.digitInSimpleFormat(hfNetBankExportBO.getComAmount()),
                        CalculateSocialUtils.digitInSimpleFormat(hfNetBankExportBO.getEmpAmount()),
                        CalculateSocialUtils.digitInSimpleFormat(hfNetBankExportBO.getBase())
                    )
                );
                stringBuilder.append("\r\n");
            }
        }

        hfNetBankExportBO = newList.get(size - 1);
        stringBuilder.append(size);
        stringBuilder.append(
            String.format(newTemplate,
                hfNetBankExportBO.getEmployeeName(),
                CalculateSocialUtils.digitInSimpleFormat(hfNetBankExportBO.getRatioCom()),
                CalculateSocialUtils.digitInSimpleFormat(hfNetBankExportBO.getRatioEmp()),
                CalculateSocialUtils.digitInSimpleFormat(hfNetBankExportBO.getAmount()),
                hfNetBankExportBO.getIdNum(),
                (hfNetBankExportBO.getBirthday() != null)? hfNetBankExportBO.getBirthday().format(dateFormatter) : "",
                (hfNetBankExportBO.getGender())? "01" : "02",
                CalculateSocialUtils.digitInSimpleFormat(hfNetBankExportBO.getComAmount()),
                CalculateSocialUtils.digitInSimpleFormat(hfNetBankExportBO.getEmpAmount()),
                CalculateSocialUtils.digitInSimpleFormat(hfNetBankExportBO.getBase())
            )
        );

        return stringBuilder.toString();
    }

    /**
     * 获取转入/启封数据
     *
     * @param inList
     * @return
     */
    private String getEmpInData(List<HFNetBankExportBO> inList) {
        if (CollectionUtils.isEmpty(inList)) {
            return null;
        }

        // 序号|3||公积金账号|姓名|单边比例|单边比例|||缴费金额|1010|身份证号码|||单边金额|单边金额||基数|||||||||-1|
        String inTemplate = "|3||%1$s|%2$s|%3$s|%4$s|||%5$s|1010|%6$s|||%7$s|%8$s||%9$s|||||||||-1|";

        StringBuilder stringBuilder = new StringBuilder();
        HFNetBankExportBO hfNetBankExportBO;
        int size = inList.size();

        if (size > 1) {
            for (int i = 0; i < size - 1; i++) {
                hfNetBankExportBO = inList.get(i);
                stringBuilder.append(i + 1);
                stringBuilder.append(
                    String.format(inTemplate,
                        hfNetBankExportBO.getHfEmpAccount(),
                        hfNetBankExportBO.getEmployeeName(),
                        CalculateSocialUtils.digitInSimpleFormat(hfNetBankExportBO.getRatioCom()),
                        CalculateSocialUtils.digitInSimpleFormat(hfNetBankExportBO.getRatioEmp()),
                        CalculateSocialUtils.digitInSimpleFormat(hfNetBankExportBO.getAmount()),
                        hfNetBankExportBO.getIdNum(),
                        CalculateSocialUtils.digitInSimpleFormat(hfNetBankExportBO.getComAmount()),
                        CalculateSocialUtils.digitInSimpleFormat(hfNetBankExportBO.getEmpAmount()),
                        CalculateSocialUtils.digitInSimpleFormat(hfNetBankExportBO.getBase())
                    )
                );
                stringBuilder.append("\r\n");
            }
        }

        hfNetBankExportBO = inList.get(size - 1);
        stringBuilder.append(size);
        stringBuilder.append(
            String.format(inTemplate,
                hfNetBankExportBO.getHfEmpAccount(),
                hfNetBankExportBO.getEmployeeName(),
                CalculateSocialUtils.digitInSimpleFormat(hfNetBankExportBO.getRatioCom()),
                CalculateSocialUtils.digitInSimpleFormat(hfNetBankExportBO.getRatioEmp()),
                CalculateSocialUtils.digitInSimpleFormat(hfNetBankExportBO.getAmount()),
                hfNetBankExportBO.getIdNum(),
                CalculateSocialUtils.digitInSimpleFormat(hfNetBankExportBO.getComAmount()),
                CalculateSocialUtils.digitInSimpleFormat(hfNetBankExportBO.getEmpAmount()),
                CalculateSocialUtils.digitInSimpleFormat(hfNetBankExportBO.getBase())
            )
        );

        return stringBuilder.toString();
    }

    /**
     * 获取封存数据
     *
     * @param outList
     * @return
     */
    private String getEmpOutData(List<HFNetBankExportBO> outList) {
        if (CollectionUtils.isEmpty(outList)) {
            return null;
        }

        // 序号|7||公积金账号|姓名|||||缴费金额||||||||||||||30|||6|
        String outTemplate = "|7||%1$s|%2$s|||||%3$s||||||||||||||30|||6|";

        StringBuilder stringBuilder = new StringBuilder();
        HFNetBankExportBO hfNetBankExportBO;
        int size = outList.size();

        if (size > 1) {
            for (int i = 0; i < size - 1; i++) {
                hfNetBankExportBO = outList.get(i);
                stringBuilder.append(i + 1);
                stringBuilder.append(
                    String.format(outTemplate,
                        hfNetBankExportBO.getHfEmpAccount(),
                        hfNetBankExportBO.getEmployeeName(),
                        CalculateSocialUtils.digitInSimpleFormat(hfNetBankExportBO.getAmount())
                    )
                );
                stringBuilder.append("\r\n");
            }
        }

        hfNetBankExportBO = outList.get(size - 1);
        stringBuilder.append(size);
        stringBuilder.append(
            String.format(outTemplate,
                hfNetBankExportBO.getHfEmpAccount(),
                hfNetBankExportBO.getEmployeeName(),
                CalculateSocialUtils.digitInSimpleFormat(hfNetBankExportBO.getAmount())
            )
        );

        return stringBuilder.toString();
    }

    /**
     * 获取补缴数据
     *
     * @param repairList
     * @return
     */
    private String getEmpRepairData(List<HFNetBankExportBO> repairList) {
        if (CollectionUtils.isEmpty(repairList)) {
            return null;
        }

        // 序号|公积金账号|姓名|补缴金额|补缴起始月|补缴结束月||||
        String template = "|%1$s|%2$s|%3$s|%4$s|%5$s||||";
        List<String> outList = new ArrayList<>();
        repairList.add(new HFNetBankExportBO());
        HFNetBankExportBO prevHfNetBankExportBO = repairList.get(0);

        for (int i = 1; i < repairList.size(); i++) {
            HFNetBankExportBO hfNetBankExportBO = repairList.get(i);
            if (!isContinue(prevHfNetBankExportBO, hfNetBankExportBO)) {
                outList.add(
                    String.format(template,
                        prevHfNetBankExportBO.getHfEmpAccount(),
                        prevHfNetBankExportBO.getEmployeeName(),
                        CalculateSocialUtils.digitInSimpleFormat(prevHfNetBankExportBO.getAmount()),
                        prevHfNetBankExportBO.getStartMonth(),
                        prevHfNetBankExportBO.getEndMonth()
                    )
                );
                prevHfNetBankExportBO = hfNetBankExportBO;
            }
        }

        if (CollectionUtils.isNotEmpty(outList)) {
            StringBuilder stringBuilder = new StringBuilder();
            int size = outList.size();

            if (size == 1) {
                stringBuilder.append(1);
                stringBuilder.append(outList.get(0));
            } else {
                for (int i = 0; i < size - 1; i++) {
                    stringBuilder.append(i + 1);
                    stringBuilder.append(outList.get(i));
                    stringBuilder.append("\r\n");
                }
                stringBuilder.append(size);
                stringBuilder.append(outList.get(size - 1));
            }

            return stringBuilder.toString();
        }

        return null;
    }

    /**
     * 判断补缴记录是否连续（所属年月相同，基数，比例相同）
     *
     * @param prevHfNetBankExportBO
     * @param hfNetBankExportBO
     * @return
     */
    private boolean isContinue(HFNetBankExportBO prevHfNetBankExportBO, HFNetBankExportBO hfNetBankExportBO) {
        if (prevHfNetBankExportBO.getCompanyId().equals(hfNetBankExportBO.getCompanyId()) &&
            prevHfNetBankExportBO.getEmployeeId().equals(hfNetBankExportBO.getEmployeeId()) &&
            prevHfNetBankExportBO.getHfType().equals(hfNetBankExportBO.getHfType()) &&
            prevHfNetBankExportBO.getBase().equals(hfNetBankExportBO.getBase()) &&
            prevHfNetBankExportBO.getRatioCom().equals(hfNetBankExportBO.getRatioCom()) &&
            prevHfNetBankExportBO.getRatioEmp().equals(hfNetBankExportBO.getRatioEmp())
            ) {
            YearMonth EndMonth = YearMonth.parse(prevHfNetBankExportBO.getEndMonth(), formatter);
            YearMonth StartMonth = YearMonth.parse(hfNetBankExportBO.getStartMonth(), formatter);

            if (EndMonth.plusMonths(1).equals(StartMonth)) {
                prevHfNetBankExportBO.setEndMonth(hfNetBankExportBO.getStartMonth());
                return true;
            }
        } else {
            hfNetBankExportBO.setEndMonth(hfNetBankExportBO.getStartMonth());
        }
        return false;
    }

}
