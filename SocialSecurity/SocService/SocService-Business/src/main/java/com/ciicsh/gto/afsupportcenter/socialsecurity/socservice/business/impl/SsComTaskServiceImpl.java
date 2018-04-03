package com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo.SsComTaskBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo.customer.ComAccountExtBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo.customer.ComTaskParamBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business.SsComTaskService;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business.utils.CommonApiUtils;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business.utils.TaskCommonUtils;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.dao.SsAccountComRelationMapper;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.dao.SsAccountRatioMapper;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.dao.SsComAccountMapper;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.dao.SsComTaskMapper;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.SsAccountComRelation;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.SsAccountRatio;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.SsComAccount;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.SsComTask;
import com.ciicsh.gto.afsupportcenter.util.enumeration.ComTaskStatus;
import com.ciicsh.gto.afsupportcenter.util.exception.BusinessException;
import com.ciicsh.gto.afsupportcenter.util.interceptor.authenticate.UserContext;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageKit;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;

/**
 * <p>
 * 独立库客户任务单 服务实现类
 * </p>
 *
 * @author HuangXing
 * @since 2017-12-01
 */
@Service
public class SsComTaskServiceImpl extends ServiceImpl<SsComTaskMapper, SsComTask> implements SsComTaskService {

    @Autowired
    public SsComTaskMapper ssComTaskMapper;
    @Autowired
    public SsComAccountMapper sComAccountMapper;
    @Autowired
    public SsAccountRatioMapper ssAccountRatioMapper;
    @Autowired
    public SsAccountComRelationMapper ssAccountComRelationMapper;
    @Autowired
    public CommonApiUtils commonApiUtils;

    /**
     * 获得企业任务单 未处理
     * xsj
     *
     * @return
     */
    @Override
    public PageRows<SsComTaskBO> queryNoProgressCompanyTask(PageInfo pageInfo) {
        //将json对象转 DTO对象
        SsComTaskBO ssComTaskBO = pageInfo.toJavaObject(SsComTaskBO.class);
        return PageKit.doSelectPage(pageInfo, () -> baseMapper.queryNoProgressCompanyTask(ssComTaskBO));
    }

    @Override
    public List<SsComTaskBO> getNoProgressCompanyTasks(SsComTaskBO taskBo) {
        return baseMapper.queryNoProgressCompanyTask(taskBo);
    }

    /**
     * 获得企业任务单 处理中
     * xsj
     *
     * @return
     */
    @Override
    public PageRows<SsComTaskBO> queryProgressingCompanyTask(PageInfo pageInfo) {
        //将json对象转 DTO对象
        SsComTaskBO ssComTaskBO = pageInfo.toJavaObject(SsComTaskBO.class);
        return PageKit.doSelectPage(pageInfo, () -> baseMapper.queryProgressingCompanyTask(ssComTaskBO));
    }

    @Override
    public List<SsComTaskBO> getProgressingCompanyTasks(SsComTaskBO taskBo) {
        return baseMapper.queryProgressingCompanyTask(taskBo);
    }

    /**
     * 获得企业任务单 处理中
     * xsj
     *
     * @return
     */
    @Override
    public PageRows<SsComTaskBO> queryFinshedCompanyTask(PageInfo pageInfo) {
        //将json对象转 DTO对象
        SsComTaskBO ssComTaskBO = pageInfo.toJavaObject(SsComTaskBO.class);
        return PageKit.doSelectPage(pageInfo, () -> baseMapper.queryFinshedCompanyTask(ssComTaskBO));
    }

    /**
     * 获得企业任务单 批退
     * xsj
     *
     * @return
     */
    @Override
    public PageRows<SsComTaskBO> queryRefusedCompanyTask(PageInfo pageInfo) {
        //将json对象转 DTO对象
        SsComTaskBO ssComTaskBO = pageInfo.toJavaObject(SsComTaskBO.class);
        return PageKit.doSelectPage(pageInfo, () -> baseMapper.queryRefusedCompanyTask(ssComTaskBO));
    }

    /**
     * 批量修改批退任务
     * xsj
     *
     * @return
     */
    @Override
    public boolean updatePatchRefuseTask(List<SsComTask> ssComTaskList) {
        //
        return baseMapper.updatePatchRefuseTask(ssComTaskList);
    }

    //查询企业信息和材料
    public SsComTaskBO queryComInfoAndMaterial(SsComTaskBO ssComTaskBO) {
        //
        return baseMapper.queryComInfoAndMaterial(ssComTaskBO);
    }

    /**
     * 查询企业信息和 前道传过来的JSON（包含社保截止和付款方式）
     *
     * @param ssComTaskBO
     * @return
     */
    public SsComTaskBO queryComInfoAndPayWay(SsComTaskBO ssComTaskBO) {

        return baseMapper.queryComInfoAndPayWay(ssComTaskBO);
    }

    /**
     * 企业任务开户办理 在内做事物
     */
    @Transactional(
        rollbackFor = {Exception.class}
    )
    public String addOrUpdateCompanyTask(SsComTask ssComTask, SsComAccount ssComAccount, SsAccountRatio ssAccountRatio,SsAccountComRelation ssAccountComRelation) {
        //如果 账户ID为空 则添加  否则修改
        if (null == ssComAccount.getComAccountId()) {
            ssComAccount.setActive(true);
            ssComAccount.setCreatedTime(LocalDateTime.now());
            ssComAccount.setCreatedBy(UserContext.getUserId());
            sComAccountMapper.insert(ssComAccount);
            //将账户ID 赋给 任务单
            ssComTask.setComAccountId(ssComAccount.getComAccountId());
        } else {
            sComAccountMapper.updateById(ssComAccount);
        }
        //修改任务详情
        baseMapper.updateById(ssComTask);

        if (null == ssAccountRatio.getSsAccountRatioId()) {
            //工伤比例变更历史表 如果没有则添加
            ssAccountRatio.setActive(true);
            ssAccountRatio.setCreatedTime(LocalDateTime.now());
            ssAccountRatio.setComAccountId(ssComAccount.getComAccountId());
            ssAccountRatio.setCreatedBy(UserContext.getUserId());
            ssAccountRatioMapper.insert(ssAccountRatio);
        } else {
            ssAccountRatioMapper.updateById(ssAccountRatio);
        }
        //表示完成
        if(3 == ssComTask.getTaskStatus()){
            //添加账户下对应的公司
            ssAccountComRelation.setComAccountId(ssComAccount.getComAccountId());
            ssAccountComRelationMapper.insert(ssAccountComRelation);
            Map<String,Object> bankAccountMap =new HashMap<String,Object>();
            bankAccountMap.put("com_account_id", ssComAccount.getComAccountId());
            bankAccountMap.put("account", ssComAccount.getBankAccount());
            bankAccountMap.put("account_name", ssComAccount.getComAccountName());
            bankAccountMap.put("bank_name", ssComAccount.getPaymentBank());
            bankAccountMap.put("bank_id", "2");//默认工商银行
            bankAccountMap.put("company_id",ssComTask.getCompanyId() );//客户ID
//          bankAccountMap.put("province_code", "002");
//          bankAccountMap.put("city_code", "01");
            bankAccountMap.put("account_type", "4");
            //bankAccountMap.put("subject_no", "1");
            //插入银行账号信息并返回结果，如果接口返回0 表示 接口调用失败，正常返回 bankAccountId 主键
            Map<String,String> mp=new HashMap<>();
            mp= commonApiUtils.addBankAccount(bankAccountMap);
            if(Integer.parseInt(mp.get("code")) == 0){//成功
                ssComAccount.setBankAccountId(Long.valueOf(mp.get("ret")));
                //任务单为已完成状态 账户设置为可用
                ssComAccount.setState(new Integer(1));
                try {
                    //调用工作流
                    String taskId = baseMapper.selectById(ssComTask.getComTaskId()).getTaskId();
                    TaskCommonUtils.completeTask(taskId, commonApiUtils, UserContext.getUserId());
                }catch (BusinessException e){
                    //如果工作流异常，则跳过
                }
                sComAccountMapper.updateById(ssComAccount);
            }else{
                throw new BusinessException("调用财务银行信息接口反馈的信息："+mp.get("ret"));
               // return "办理失败！调用银行信息接口出现异常。";
            }

        }
        return "SUCC";
    }


    /**
     * 判断企业社保账户和名称是否重复
     */
    public String checkComAccountDuplicate(SsComAccount ssComAccount) {
        int accountC = 0,accountNameC = 0;
        String retStr="";
        if (Optional.ofNullable(ssComAccount.getSsAccount()).isPresent() ){
            accountC=sComAccountMapper.checkComAccountDuplicateaSSAccount(ssComAccount);
        }
        if (Optional.ofNullable(ssComAccount.getComAccountName()).isPresent() ){
            accountNameC=sComAccountMapper.checkComAccountDuplicateaSSAccountName(ssComAccount);
        }
        if (accountC > 0){
            retStr="参保户登记码在系统中已重复，";
        }

        if (accountNameC > 0){
            retStr="养老金用公司名称在系统中已重复，";
        }

        return retStr;
    }

    /**
     * 查询账户信息和材料信息
     *
     * @param ssComTaskBO
     * @return
     */
    public SsComTaskBO queryAccountInfoAndMaterial(SsComTaskBO ssComTaskBO) {

        return baseMapper.queryAccountInfoAndMaterial(ssComTaskBO);
    }

    /**
     * 更新或者处理任务 终止 转移 变更
     *
     * @param ssComTaskBO
     * @param object
     * @return
     */
    @Transactional(
        rollbackFor = {Exception.class}
    )
    public boolean updateOrHandlerTask(SsComTaskBO ssComTaskBO, Object object) {
        boolean result = false;
        try {
            if (object instanceof SsComAccount) {//修改账户表
                sComAccountMapper.updateById((SsComAccount) object);
            } else if (object instanceof List) {//行业比例变更
                List<SsAccountRatio> ssAccountRatioList = (ArrayList<SsAccountRatio>) object;
                //第二个做修改 先将endMonth 补上
                ssAccountRatioMapper.updateEndMonthByAccId(ssAccountRatioList.get(1));
                //第一个做插入
                ssAccountRatioMapper.insert(ssAccountRatioList.get(0));
            }
            ssComTaskBO.setComAccountId(null);
            SsComTask comTask = new SsComTask();
            BeanUtils.copyProperties(ssComTaskBO,comTask);
            baseMapper.updateById(comTask);
            result = true;
        } catch (Exception e) {
            result = false;
            throw new RuntimeException("终止任务办理异常");
        }
        return result;
    }

    /**
     * 任务单撤销
     * @param ssComTask
     * @return
     */
    public int updateTaskStatusForRevoke(SsComTask ssComTask){
        return baseMapper.updateTaskStatusForRevoke(ssComTask);
    }

    /**
     * 判断企业任务单是否存在
     * @param companyId
     * @return
     */
    public boolean isExistComTask(String companyId) {
        return baseMapper.isExistComTask(companyId) > 0 ? true : false;
    }

    public boolean insertComTask(SsComTask ssComTask) {
        return baseMapper.insertComTask(ssComTask);
    }

    @Override
    public int countFinishComTaskByCond(SsComTaskBO ssComTask) {
        return baseMapper.countFinishComTaskByCond(ssComTask);
    }

    @Override
    public ComAccountExtBO getComAccountInfo(ComTaskParamBO paramBO) {
        ComAccountExtBO accountExtBO = null;
        //查询社保账户与客户关系表，是否有数据
        Integer result = ssAccountComRelationMapper.isExistCompany(paramBO.getCompanyId());
        //如有关系数据，则表示该客户已经开户过，返回开户信息即可；如没有关系数据则从任务单表中获取开户信息；
        List<ComAccountExtBO> extBOS = result > 0 ? sComAccountMapper.getComAccountByCompanyId(paramBO) : baseMapper.getComTaskByCompanyId(paramBO);

        if(null != extBOS && extBOS.size() > 0){
            accountExtBO = extBOS.get(0);
            //处理状态的转换，客服中心只显示用
            accountExtBO.setTaskStatus(ComTaskStatus.getValue(Integer.parseInt(accountExtBO.getTaskStatus())));
        }
        return accountExtBO;
    }


}
