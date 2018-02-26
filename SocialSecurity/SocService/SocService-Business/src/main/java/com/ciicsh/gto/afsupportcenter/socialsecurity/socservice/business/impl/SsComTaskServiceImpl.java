package com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo.SsComTaskBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business.SsComTaskService;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.dao.SsAccountComRelationMapper;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.dao.SsAccountRatioMapper;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.dao.SsComAccountMapper;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.dao.SsComTaskMapper;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.SsAccountComRelation;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.SsAccountRatio;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.SsComAccount;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.SsComTask;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageKit;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public boolean addOrUpdateCompanyTask(SsComTask ssComTask, SsComAccount ssComAccount, SsAccountRatio ssAccountRatio,SsAccountComRelation ssAccountComRelation) {
        //如果 账户ID为空 则添加  否则修改
        if (null == ssComAccount.getComAccountId()) {
            ssComAccount.setActive(true);
            ssComAccount.setCreatedTime(LocalDateTime.now());
            ssComAccount.setCreatedBy("xsj");
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
            ssAccountRatio.setCreatedBy("xsj");
            ssAccountRatioMapper.insert(ssAccountRatio);
        } else {
            ssAccountRatioMapper.updateById(ssAccountRatio);
        }
        //表示完成
        if(null!=ssAccountComRelation){
            //添加账户下对应的公司
            ssAccountComRelation.setComAccountId(ssComAccount.getComAccountId());
            ssAccountComRelationMapper.insert(ssAccountComRelation);
        }
        return true;
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
     *
     * @param ssComTask
     * @return
     */
    public int countComTaskByCond(SsComTaskBO ssComTask) {
        return baseMapper.countComTaskByCond(ssComTask);
    }

    public boolean insertComTask(SsComTask ssComTask) {
        return baseMapper.insertComTask(ssComTask);
    }
}