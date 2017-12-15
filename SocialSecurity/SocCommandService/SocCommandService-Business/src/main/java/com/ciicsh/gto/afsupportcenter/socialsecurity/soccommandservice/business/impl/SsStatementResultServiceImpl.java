package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.business.impl;

import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dao.SsMonthEmpChangeDetailMapper;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dao.SsStatementImpMapper;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dto.SsStatementDTO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dto.SsStatementResultDTO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.SsMonthEmpChangeDetailPO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.SsStatementImpPO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.SsStatementResultPO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dao.SsStatementResultMapper;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.business.ISsStatementResultService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageKit;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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
public class SsStatementResultServiceImpl extends ServiceImpl<SsStatementResultMapper, SsStatementResultPO> implements ISsStatementResultService {

    @Autowired
    SsMonthEmpChangeDetailMapper ssMonthEmpChangeDetailMapper;

    @Autowired
    SsStatementImpMapper ssStatementImpMapper;

    @Override
    public List<SsStatementResultDTO> statementResultQuery(SsStatementResultDTO ssStatementResultDTO) {
        return baseMapper.statementResultQuery(ssStatementResultDTO);
    }


    private void calculateSstatementResult(String statementId){
        //清除历史结果

        //用于存放合并节点的map
        Map<String,SsStatementResultPO> resultPOMap = new HashMap<>();
        //取出导入结果
        List<SsStatementImpPO> impDetailPOList = ssStatementImpMapper.getImpDetailByStatementId(statementId);
        //将导入结果进行拆解
        dealImpDetailToResultModle(resultPOMap,impDetailPOList);


        //取出汇总结果
        List<SsMonthEmpChangeDetailPO> changeDetailPOList = ssMonthEmpChangeDetailMapper.serachMonthEmpChangeDetailPOByStatementId(statementId);

        //将汇总结果进行拆解
        dealChangeDetailToResultModle(resultPOMap,changeDetailPOList);


        //对比数据产生对比结果
        List<SsStatementResultPO> resultPoList = calculateResultDiff(resultPOMap);


        //批量插入对比结果


        //更新主表字段

    }

    private void dealImpDetailToResultModle(Map<String,SsStatementResultPO> resultPOMap,List<SsStatementImpPO> impDetailPOList){
        if(!Optional.ofNullable(impDetailPOList).isPresent()){
            return;
        }
        //循环拆解
        for(int i = 0;i < impDetailPOList.size(); i++){
            SsStatementImpPO impPO = impDetailPOList.get(i);
            //个人缴费部分
            if(BigDecimal.ZERO.compareTo(impPO.getEmpAmount()) != 0){
                //费用不为0则拆出一份对比节点
                SsStatementResultPO resultPO;
                //key的结构  employeeId-changeType-ssType-projectType
                String key = impPO.getEmployeeId() + "-" + impPO.getChangeType() + "-" + impPO.getSsType() + "-" + "1";
                //从map中取出节点,如果没有则new一个
                if(resultPOMap.containsKey(key)){
                    resultPO = resultPOMap.get(key);
                }else{
                    resultPO = new SsStatementResultPO();
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
                SsStatementResultPO resultPO;
                //key的结构  employeeId-changeType-ssType-projectType
                String key = impPO.getEmployeeId() + "-" + impPO.getChangeType() + "-" + impPO.getSsType() + "-" + "2";
                //从map中取出节点,如果没有则new一个
                if(resultPOMap.containsKey(key)){
                    resultPO = resultPOMap.get(key);
                }else{
                    resultPO = new SsStatementResultPO();
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
                SsStatementResultPO resultPO;
                //key的结构  employeeId-changeType-ssType-projectType
                String key = impPO.getEmployeeId() + "-" + impPO.getChangeType() + "-" + impPO.getSsType() + "-" + "3";
                //从map中取出节点,如果没有则new一个
                if(resultPOMap.containsKey(key)){
                    resultPO = resultPOMap.get(key);
                }else{
                    resultPO = new SsStatementResultPO();
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
                SsStatementResultPO resultPO;
                //key的结构  employeeId-changeType-ssType-projectType
                String key = impPO.getEmployeeId() + "-" + impPO.getChangeType() + "-" + impPO.getSsType() + "-" + "4";
                //从map中取出节点,如果没有则new一个
                if(resultPOMap.containsKey(key)){
                    resultPO = resultPOMap.get(key);
                }else{
                    resultPO = new SsStatementResultPO();
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
                SsStatementResultPO resultPO;
                //key的结构  employeeId-changeType-ssType-projectType
                String key = impPO.getEmployeeId() + "-" + impPO.getChangeType() + "-" + impPO.getSsType() + "-" + "5";
                //从map中取出节点,如果没有则new一个
                if(resultPOMap.containsKey(key)){
                    resultPO = resultPOMap.get(key);
                }else{
                    resultPO = new SsStatementResultPO();
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



    private void dealChangeDetailToResultModle(Map<String,SsStatementResultPO> resultPOMap,List<SsMonthEmpChangeDetailPO> changeDetailPOList){
        if(!Optional.ofNullable(changeDetailPOList).isPresent()){
            return;
        }
        //循环拆解
        for(int i = 0;i < changeDetailPOList.size(); i++){
            SsMonthEmpChangeDetailPO changePO = changeDetailPOList.get(i);
            //个人缴费部分
            if(BigDecimal.ZERO.compareTo(changePO.getEmpAmount()) != 0){
                //费用不为0则拆出一份对比节点
                SsStatementResultPO resultPO;
                //key的结构  employeeId-changeType-ssType-projectType
                String key = changePO.getEmployeeId() + "-" + changePO.getChangeType() + "-" + changePO.getSsType() + "-" + "1";
                //从map中取出节点,如果没有则new一个
                if(resultPOMap.containsKey(key)){
                    resultPO = resultPOMap.get(key);
                }else{
                    resultPO = new SsStatementResultPO();
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
                SsStatementResultPO resultPO;
                //key的结构  employeeId-changeType-ssType-projectType
                String key = changePO.getEmployeeId() + "-" + changePO.getChangeType() + "-" + changePO.getSsType() + "-" + "2";
                //从map中取出节点,如果没有则new一个
                if(resultPOMap.containsKey(key)){
                    resultPO = resultPOMap.get(key);
                }else{
                    resultPO = new SsStatementResultPO();
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
                SsStatementResultPO resultPO;
                //key的结构  employeeId-changeType-ssType-projectType
                String key = changePO.getEmployeeId() + "-" + changePO.getChangeType() + "-" + changePO.getSsType() + "-" + "3";
                //从map中取出节点,如果没有则new一个
                if(resultPOMap.containsKey(key)){
                    resultPO = resultPOMap.get(key);
                }else{
                    resultPO = new SsStatementResultPO();
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
                SsStatementResultPO resultPO;
                //key的结构  employeeId-changeType-ssType-projectType
                String key = changePO.getEmployeeId() + "-" + changePO.getChangeType() + "-" + changePO.getSsType() + "-" + "4";
                //从map中取出节点,如果没有则new一个
                if(resultPOMap.containsKey(key)){
                    resultPO = resultPOMap.get(key);
                }else{
                    resultPO = new SsStatementResultPO();
                    //写基础值
                    BeanUtils.copyProperties(changePO,resultPO);
                    //将项目ID和名字写入
                    resultPO.setProjectType(4);
                    resultPO.setProjectTypeName("单位补缴");
                }
                //将金额加入导入金额字段
                resultPO.getSsAmount().add(changePO.getComCompensateAmount());
                //将节点放入map
                resultPOMap.put(key,resultPO);
            }

            //一次性收费
            if(BigDecimal.ZERO.compareTo(changePO.getOnePayment()) != 0){
                //费用不为0则拆出一份对比节点
                SsStatementResultPO resultPO;
                //key的结构  employeeId-changeType-ssType-projectType
                String key = changePO.getEmployeeId() + "-" + changePO.getChangeType() + "-" + changePO.getSsType() + "-" + "5";
                //从map中取出节点,如果没有则new一个
                if(resultPOMap.containsKey(key)){
                    resultPO = resultPOMap.get(key);
                }else{
                    resultPO = new SsStatementResultPO();
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

    private List<SsStatementResultPO> calculateResultDiff(Map<String,SsStatementResultPO> resultPOMap){
        //计算后需要存储的List
        List<SsStatementResultPO> resultPoList = new ArrayList<>();

        //遍历Map计算
        for (SsStatementResultPO resultPO : resultPOMap.values()) {
            //计算出差值
            BigDecimal diffAmount = resultPO.getImpAmount().subtract(resultPO.getSsAmount());
            resultPO.setDiffAmount(diffAmount);

            //若差值不为0,则放入计算后需要存储的List中
            if(BigDecimal.ZERO.compareTo(diffAmount) != 0){
                //放入基本信息
                resultPO.setIsActive(true);
                resultPO.setCreatedBy("对账操作人");
                resultPO.setModifiedBy(null);
                resultPO.setModifiedTime(null);
                //存入List
                resultPoList.add(resultPO);
            }
        }
        return resultPoList;

    }

}
