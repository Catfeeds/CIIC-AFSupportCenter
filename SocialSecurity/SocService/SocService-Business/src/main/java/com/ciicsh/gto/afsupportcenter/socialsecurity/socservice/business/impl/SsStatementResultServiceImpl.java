package com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business.impl;

import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo.SsMonthEmpChangeDetailBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo.SsStatementResultBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo.SsStatementResultCompareBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.dao.*;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.SsStatement;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.SsStatementImp;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.SsStatementResult;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business.SsStatementResultService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ciicsh.gto.afsupportcenter.util.interceptor.authenticate.UserContext;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

/**
 * <p>
 * 对账差异结果表 服务实现类
 * </p>
 *
 * @author HuangXing
 * @since 2017-12-01
 */
@Service
public class SsStatementResultServiceImpl extends ServiceImpl<SsStatementResultMapper, SsStatementResult> implements SsStatementResultService {

//    @Autowired
//    SsMonthEmpChangeDetailMapper ssMonthEmpChangeDetailMapper;

    @Autowired
    SsMonthChargeItemMapper ssMonthChargeItemMapper;

    @Autowired
    SsStatementImpMapper ssStatementImpMapper;

    @Autowired
    SsStatementMapper ssStatementMapper;

    @Override
    public List<SsStatementResultBO> statementResultQuery(SsStatementResultBO ssStatementResultBO) {
        return baseMapper.statementResultQuery(ssStatementResultBO);
    }


    @Override
    public SsStatementResult newSsStatementResult(){
        SsStatementResult ssStatementResult = new SsStatementResult();
        ssStatementResult.setSsAmount(new BigDecimal(0));
        ssStatementResult.setImpAmount(new BigDecimal(0));
        ssStatementResult.setDiffAmount(new BigDecimal(0));
        return ssStatementResult;
    }

    @Override
    public void calculateSstatementResult(Long statementId){
        //操作时间
        LocalDateTime dealTime = LocalDateTime.now();

        //清除历史结果
        SsStatementResultBO ssStatementResultBO = new SsStatementResultBO();
        ssStatementResultBO.setStatementId(statementId);
        ssStatementResultBO.setModifiedBy(UserContext.getUser().getDisplayName());
        ssStatementResultBO.setModifiedTime(dealTime);
        baseMapper.cleanResultByStatementId(ssStatementResultBO);

        //用于存放合并节点的map
        Map<String,SsStatementResult> resultPOMap = new HashMap<>();
        //取出导入结果
        List<SsStatementImp> impDetailPOList = ssStatementImpMapper.getImpDetailByStatementId(statementId);
        //将导入结果进行拆解
        dealImpDetailToResultModle(resultPOMap,impDetailPOList);

        SsStatement ssStatement = new SsStatement();
        ssStatement.setStatementId(statementId);
        ssStatement = ssStatementMapper.selectOne(ssStatement);

        //取出汇总结果
        List<SsMonthEmpChangeDetailBO> changeDetailBOList = ssMonthChargeItemMapper.searchYysDetailByComAccountIdAndSsMonth(ssStatement.getComAccountId(), ssStatement.getSsMonth());
//        List<SsMonthEmpChangeDetailBO> changeDetailBOList = ssMonthEmpChangeDetailMapper.serachMonthEmpChangeDetailPOByStatementId(statementId);

        //将汇总结果进行拆解
        dealChangeDetailToResultModle(resultPOMap,changeDetailBOList);


        //对比数据产生对比结果
        List<SsStatementResult> resultPoList = calculateResultDiff(resultPOMap);

        //统计差异人头数
        Map<String,SsStatementResultCompareBO> diffHeadMap = calculateDiffHead(impDetailPOList,changeDetailBOList);


        //批量插入对比结果
        if(Optional.ofNullable(resultPoList).isPresent()){
            for(int i = 0;i < resultPoList.size();i++){
                SsStatementResult result = resultPoList.get(i);
                SsStatementResultCompareBO ssStatementResultCompareBO=diffHeadMap.get(result.getSsSerial());
                //放入人头差异
                result.setDiffHeadcount(ssStatementResultCompareBO.getDiffHeadcount());

                //放入基本信息
              //  if(diffHeadMap.get(result.getEmployeeId()).getDiffHeadcount()==1){
                   // result.setEmployeeId(ssStatementResultCompareBO.getEmployeeName());
               // }
                result.setEmployeeId(ssStatementResultCompareBO.getEmployeeId());
                result.setEmployeeName(ssStatementResultCompareBO.getEmployeeName());
                result.setStatementId(statementId);
                result.setActive(true);
                result.setModifiedBy(null);
                result.setModifiedTime(null);
                result.setCreatedBy(UserContext.getUser().getDisplayName());
                result.setCreatedTime(dealTime);
                baseMapper.insert(result);
            }
        }

        //更新操作人字段
        ssStatement.setStatementUserId(UserContext.getUser().getDisplayName());
        ssStatement.setStatementTime(dealTime);
        //统计字段
        //差异项目数
        if(Optional.ofNullable(resultPoList).isPresent()){
            ssStatement.setDiffSumByItem(resultPoList.size());
        }else{
            ssStatement.setDiffSumByItem(0);
        }
        //差异员工数

        if(Optional.ofNullable(resultPoList).isPresent()){
            Map<String,String> map = new HashMap<>();
            int diffSumByEmp = 0;
            for(int i = 0;i < resultPoList.size();i++){
                String ssSerial = resultPoList.get(i).getSsSerial();
                if(!map.containsKey(ssSerial)){
                    diffSumByEmp++;
                    map.put(ssSerial,null);
                }
             }
            ssStatement.setDiffSumByEmp(diffSumByEmp);
        }else{
            ssStatement.setDiffSumByEmp(0);
        }



        ssStatementMapper.updateById(ssStatement);
    }

    /**
     * <p>Description: 将导入结果进行拆解</p>
     *
     * @author wengxk
     * @date 2017-12-15
     * @param resultPOMap 拆分结果暂存Map
     * @param impDetailPOList 导入结果List
     * @return dealImpDetailToResultModle 对账单主表ID
     */
    private void dealImpDetailToResultModle(Map<String,SsStatementResult> resultPOMap, List<SsStatementImp> impDetailPOList){
        if(!Optional.ofNullable(impDetailPOList).isPresent()){
            return;
        }
        //循环拆解
        for(int i = 0;i < impDetailPOList.size(); i++){
            SsStatementImp impPO = impDetailPOList.get(i);
            //个人缴费部分
            if(BigDecimal.ZERO.compareTo(impPO.getEmpAmount()) != 0){
                //费用不为0则拆出一份对比节点
                SsStatementResult resultPO;
                //key的结构  ssSerial-changeType-ssType-projectType
                String key = impPO.getSsSerial() + "-" + impPO.getChangeType() + "-" + impPO.getSsType() + "-" + "1";
                //从map中取出节点,如果没有则new一个
                if(resultPOMap.containsKey(key)){
                    resultPO = resultPOMap.get(key);
                }else{
                    resultPO = newSsStatementResult();
                    //写基础值
                    BeanUtils.copyProperties(impPO,resultPO);
                    //将项目ID和名字写入
                    resultPO.setProjectType(1);
                    resultPO.setProjectTypeName("个人缴费额");
                }
                //将金额加入导入金额字段
                resultPO.setImpAmount(resultPO.getImpAmount().add(impPO.getEmpAmount()));
                //将节点放入map
                resultPOMap.put(key,resultPO);
            }

            //企业缴费
            if(BigDecimal.ZERO.compareTo(impPO.getComAmount()) != 0){
                //费用不为0则拆出一份对比节点
                SsStatementResult resultPO;
                //key的结构  ssSerial-changeType-ssType-projectType
                String key = impPO.getSsSerial() + "-" + impPO.getChangeType() + "-" + impPO.getSsType() + "-" + "2";
                //从map中取出节点,如果没有则new一个
                if(resultPOMap.containsKey(key)){
                    resultPO = resultPOMap.get(key);
                }else{
                    resultPO = newSsStatementResult();
                    //写基础值
                    BeanUtils.copyProperties(impPO,resultPO);
                    //将项目ID和名字写入
                    resultPO.setProjectType(2);
                    resultPO.setProjectTypeName("单位缴费额");
                }
                //将金额加入导入金额字段
                resultPO.setImpAmount(resultPO.getImpAmount().add(impPO.getComAmount()));
                //将节点放入map
                resultPOMap.put(key,resultPO);
            }

            //个人补缴
            if(BigDecimal.ZERO.compareTo(impPO.getEmpCompensateAmount()) != 0){
                //费用不为0则拆出一份对比节点
                SsStatementResult resultPO;
                //key的结构  ssSerial-changeType-ssType-projectType
                String key = impPO.getSsSerial() + "-" + impPO.getChangeType() + "-" + impPO.getSsType() + "-" + "3";
                //从map中取出节点,如果没有则new一个
                if(resultPOMap.containsKey(key)){
                    resultPO = resultPOMap.get(key);
                }else{
                    resultPO = newSsStatementResult();
                    //写基础值
                    BeanUtils.copyProperties(impPO,resultPO);
                    //将项目ID和名字写入
                    resultPO.setProjectType(3);
                    resultPO.setProjectTypeName("个人补缴");
                }
                //将金额加入导入金额字段
                resultPO.setImpAmount(resultPO.getImpAmount().add(impPO.getEmpCompensateAmount()));
                //将节点放入map
                resultPOMap.put(key,resultPO);
            }

            //企业补缴
            if(BigDecimal.ZERO.compareTo(impPO.getComCompensateAmount()) != 0){
                //费用不为0则拆出一份对比节点
                SsStatementResult resultPO;
                //key的结构  ssSerial-changeType-ssType-projectType
                String key = impPO.getSsSerial() + "-" + impPO.getChangeType() + "-" + impPO.getSsType() + "-" + "4";
                //从map中取出节点,如果没有则new一个
                if(resultPOMap.containsKey(key)){
                    resultPO = resultPOMap.get(key);
                }else{
                    resultPO = newSsStatementResult();
                    //写基础值
                    BeanUtils.copyProperties(impPO,resultPO);
                    //将项目ID和名字写入
                    resultPO.setProjectType(4);
                    resultPO.setProjectTypeName("单位补缴");
                }
                //将金额加入导入金额字段
                resultPO.setImpAmount(resultPO.getImpAmount().add(impPO.getComCompensateAmount()));
                //将节点放入map
                resultPOMap.put(key,resultPO);
            }

            //一次性收费
            if(BigDecimal.ZERO.compareTo(impPO.getOnePayment()) != 0){
                //费用不为0则拆出一份对比节点
                SsStatementResult resultPO;
                //key的结构  ssSerial-changeType-ssType-projectType
                String key = impPO.getSsSerial() + "-" + impPO.getChangeType() + "-" + impPO.getSsType() + "-" + "5";
                //从map中取出节点,如果没有则new一个
                if(resultPOMap.containsKey(key)){
                    resultPO = resultPOMap.get(key);
                }else{
                    resultPO = newSsStatementResult();
                    //写基础值
                    BeanUtils.copyProperties(impPO,resultPO);
                    //将项目ID和名字写入
                    resultPO.setProjectType(5);
                    resultPO.setProjectTypeName("一次性支付");
                }
                //将金额加入导入金额字段
                resultPO.setImpAmount(resultPO.getImpAmount().add(impPO.getOnePayment()));
                //将节点放入map
                resultPOMap.put(key,resultPO);
            }
        }

        return;
    }


    /**
     * <p>Description: 将系统结果进行拆解</p>
     *
     * @author wengxk
     * @date 2017-12-15
     * @param resultPOMap 拆分结果暂存Map
     * @param changeDetailPOList 系统结果List
     * @return dealImpDetailToResultModle 对账单主表ID
     */
    private void dealChangeDetailToResultModle(Map<String,SsStatementResult> resultPOMap, List<SsMonthEmpChangeDetailBO> changeDetailPOList){
        if(!Optional.ofNullable(changeDetailPOList).isPresent()){
            return;
        }
        //循环拆解
        for(int i = 0;i < changeDetailPOList.size(); i++){
            SsMonthEmpChangeDetailBO changePO = changeDetailPOList.get(i);
            //个人缴费部分
            if(BigDecimal.ZERO.compareTo(changePO.getEmpAmount()) != 0){
                //费用不为0则拆出一份对比节点
                SsStatementResult resultPO;
                //key的结构  ssSerial-changeType-ssType-projectType
                String key = changePO.getSsSerial() + "-" + changePO.getChangeType() + "-" + changePO.getSsType() + "-" + "1";
                //从map中取出节点,如果没有则new一个
                if(resultPOMap.containsKey(key)){
                    resultPO = resultPOMap.get(key);
                }else{
                    resultPO = newSsStatementResult();
                    //写基础值
                    BeanUtils.copyProperties(changePO,resultPO);
                    //将项目ID和名字写入
                    resultPO.setProjectType(1);
                    resultPO.setProjectTypeName("个人缴费额");
                }
                //将金额加入导入金额字段
                resultPO.setSsAmount(resultPO.getSsAmount().add(changePO.getEmpAmount()));
                //将节点放入map
                resultPOMap.put(key,resultPO);
            }

            //企业缴费
            if(BigDecimal.ZERO.compareTo(changePO.getComAmount()) != 0){
                //费用不为0则拆出一份对比节点
                SsStatementResult resultPO;
                //key的结构  ssSerial-changeType-ssType-projectType
                String key = changePO.getSsSerial() + "-" + changePO.getChangeType() + "-" + changePO.getSsType() + "-" + "2";
                //从map中取出节点,如果没有则new一个
                if(resultPOMap.containsKey(key)){
                    resultPO = resultPOMap.get(key);
                }else{
                    resultPO = newSsStatementResult();
                    //写基础值
                    BeanUtils.copyProperties(changePO,resultPO);
                    //将项目ID和名字写入
                    resultPO.setProjectType(2);
                    resultPO.setProjectTypeName("单位缴费额");
                }
                //将金额加入导入金额字段
                resultPO.setSsAmount(resultPO.getSsAmount().add(changePO.getComAmount()));
                //将节点放入map
                resultPOMap.put(key,resultPO);
            }

            //个人补缴
            if(BigDecimal.ZERO.compareTo(changePO.getEmpCompensateAmount()) != 0){
                //费用不为0则拆出一份对比节点
                SsStatementResult resultPO;
                //key的结构  ssSerial-changeType-ssType-projectType
                String key = changePO.getSsSerial() + "-" + changePO.getChangeType() + "-" + changePO.getSsType() + "-" + "3";
                //从map中取出节点,如果没有则new一个
                if(resultPOMap.containsKey(key)){
                    resultPO = resultPOMap.get(key);
                }else{
                    resultPO = newSsStatementResult();
                    //写基础值
                    BeanUtils.copyProperties(changePO,resultPO);
                    //将项目ID和名字写入
                    resultPO.setProjectType(3);
                    resultPO.setProjectTypeName("个人补缴");
                }
                //将金额加入导入金额字段
                resultPO.setSsAmount(resultPO.getSsAmount().add(changePO.getEmpCompensateAmount()));
                //将节点放入map
                resultPOMap.put(key,resultPO);
            }

            //企业补缴
            if(BigDecimal.ZERO.compareTo(changePO.getComCompensateAmount()) != 0){
                //费用不为0则拆出一份对比节点
                SsStatementResult resultPO;
                //key的结构  ssSerial-changeType-ssType-projectType
                String key = changePO.getSsSerial() + "-" + changePO.getChangeType() + "-" + changePO.getSsType() + "-" + "4";
                //从map中取出节点,如果没有则new一个
                if(resultPOMap.containsKey(key)){
                    resultPO = resultPOMap.get(key);
                }else{
                    resultPO = newSsStatementResult();
                    //写基础值
                    BeanUtils.copyProperties(changePO,resultPO);
                    //将项目ID和名字写入
                    resultPO.setProjectType(4);
                    resultPO.setProjectTypeName("单位补缴");
                }
                //将金额加入导入金额字段
                resultPO.setSsAmount(resultPO.getSsAmount().add(changePO.getComCompensateAmount()));
                //将节点放入map
                resultPOMap.put(key,resultPO);
            }

            //一次性收费
            if(BigDecimal.ZERO.compareTo(changePO.getOnePayment()) != 0){
                //费用不为0则拆出一份对比节点
                SsStatementResult resultPO;
                //key的结构  ssSerial-changeType-ssType-projectType
                String key = changePO.getSsSerial() + "-" + changePO.getChangeType() + "-" + changePO.getSsType() + "-" + "5";
                //从map中取出节点,如果没有则new一个
                if(resultPOMap.containsKey(key)){
                    resultPO = resultPOMap.get(key);
                }else{
                    resultPO =  newSsStatementResult();
                    //写基础值
                    BeanUtils.copyProperties(changePO,resultPO);
                    //将项目ID和名字写入
                    resultPO.setProjectType(5);
                    resultPO.setProjectTypeName("一次性支付");
                }
                //将金额加入导入金额字段
                resultPO.setSsAmount(resultPO.getSsAmount().add(changePO.getOnePayment()));
                //将节点放入map
                resultPOMap.put(key,resultPO);
            }
        }

        return;

    }

    /**
     * <p>Description: 将拆分结果进行计算</p>
     *
     * @author wengxk
     * @date 2017-12-15
     * @param resultPOMap 拆分结果暂存Map
     * @return List<SsStatementResult> 计算差异结果
     */
    private List<SsStatementResult> calculateResultDiff(Map<String,SsStatementResult> resultPOMap){
        //计算后需要存储的List
        List<SsStatementResult> resultPoList = new ArrayList<>();

        //遍历Map计算
        for (SsStatementResult resultPO : resultPOMap.values()) {
            //计算出差值
            BigDecimal diffAmount = resultPO.getImpAmount().subtract(resultPO.getSsAmount());
            resultPO.setDiffAmount(diffAmount);

            //若差值不为0,则放入计算后需要存储的List中
            if(BigDecimal.ZERO.compareTo(diffAmount) != 0){
                //存入List
                resultPoList.add(resultPO);
            }
        }
        return resultPoList;

    }
    /**
     * <p>Description: 计算人头差异</p>
     *
     * @author wengxk
     * @date 2017-12-20
     * @param impDetailPOList 导入数据
     * @param changeDetailPOList 汇总数据
     * @return Map<String,String> 人头差异结果 key:employeeId value:0 正常差异 1 系统不存在  2 导入不存在
     */
    private Map<String,SsStatementResultCompareBO> calculateDiffHead(List<SsStatementImp> impDetailPOList, List<SsMonthEmpChangeDetailBO> changeDetailPOList){
        Map<String,SsStatementResultCompareBO> diffHeadMap = new HashMap<>();

        //先循环处理导入的信息
        if(Optional.ofNullable(impDetailPOList).isPresent()) {
            for (int i = 0; i < impDetailPOList.size(); i++) {
                SsStatementImp ssStatementImp=impDetailPOList.get(i);
                //String employeeId = ssStatementImp.getEmployeeId();
                String ssSerial = ssStatementImp.getSsSerial();

                //将员工ID去重加入map
                if(!diffHeadMap.containsKey(ssSerial)){
                    SsStatementResultCompareBO cbo=new SsStatementResultCompareBO();
                    cbo.setDiffHeadcount(1);
                    cbo.setEmployeeId(ssStatementImp.getEmployeeId());
                    cbo.setEmployeeName(ssStatementImp.getEmployeeName());
                    diffHeadMap.put(ssSerial,cbo);
                }
            }
        }
        //再循环处理系统汇总信息
        if(Optional.ofNullable(changeDetailPOList).isPresent()) {
            for (int i = 0; i < changeDetailPOList.size(); i++) {
                String ssSerial = changeDetailPOList.get(i).getSsSerial();
                //判断是否已存在
                if(!diffHeadMap.containsKey(ssSerial)) {
                    //不存在的话直接加入map,
                    SsStatementResultCompareBO cbo=new SsStatementResultCompareBO();
                    cbo.setEmployeeId(changeDetailPOList.get(i).getEmployeeId());
                    cbo.setDiffHeadcount(2);
                    diffHeadMap.put(ssSerial,cbo);
                }else{
                    //存在的话判断是系统数据还是导入数据还是2者都有
                    SsStatementResultCompareBO cbo = diffHeadMap.get(ssSerial);
                    Integer diffHead=cbo.getDiffHeadcount();
                    //只有当原先状态是1 系统不存在情况下,变更map中的状态,别的时候保持不变
                    if(diffHead == 1 ){
                        cbo.setDiffHeadcount(0);
                        diffHeadMap.put(ssSerial,cbo);
                    }
                }
            }
        }
        return diffHeadMap;
    }
}
