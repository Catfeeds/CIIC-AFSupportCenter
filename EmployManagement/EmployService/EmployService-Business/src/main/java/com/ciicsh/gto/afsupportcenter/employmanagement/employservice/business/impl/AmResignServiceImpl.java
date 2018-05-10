package com.ciicsh.gto.afsupportcenter.employmanagement.employservice.business.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.bo.AmResignBO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.business.AmResignLinkService;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.business.IAmEmpTaskService;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.business.IAmResignService;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.business.utils.CommonApiUtils;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.business.utils.ReasonUtil;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.business.utils.TaskCommonUtils;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.dao.AmResignMapper;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.entity.AmEmpTask;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.entity.AmResign;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.entity.AmResignLink;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.custom.resignSearchExportOpt;
import com.ciicsh.gto.afsupportcenter.util.StringUtil;
import com.ciicsh.gto.afsupportcenter.util.interceptor.authenticate.UserContext;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageKit;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用工表 服务实现类
 * </p>
 */
@Service
public class AmResignServiceImpl extends ServiceImpl<AmResignMapper, AmResign> implements IAmResignService {

    @Autowired
    private IAmEmpTaskService taskService;

    @Autowired
    private AmResignLinkService amResignLinkService;

    @Autowired
    private CommonApiUtils employeeInfoProxy;

    public PageRows<AmResignBO> queryAmResign(PageInfo pageInfo){

        AmResignBO  amResignBO = pageInfo.toJavaObject(AmResignBO.class);

        List<String> param = new ArrayList<String>();

        if(!StringUtil.isEmpty(amResignBO.getParams()))
        {
            String arr[] = amResignBO.getParams().split(",");
            for(int i=0;i<arr.length;i++) {
                param.add(arr[i]);
            }
        }

        amResignBO.setParam(param);

        if(null!=amResignBO.getTaskStatus()&&amResignBO.getTaskStatus()==0){
            amResignBO.setTaskStatus(null);
        }

        if(null!=amResignBO.getTaskStatus()&&amResignBO.getTaskStatus()==6)
        {
            return PageKit.doSelectPage(pageInfo,() -> baseMapper.queryAmResignOther(amResignBO));
        }else{
            return PageKit.doSelectPage(pageInfo,() -> baseMapper.queryAmResign(amResignBO));
        }

    }

    @Override
    public List<AmResignBO> taskCount(PageInfo pageInfo) {
        AmResignBO amResignBO = pageInfo.toJavaObject(AmResignBO.class);
        List<String> param = new ArrayList<String>();

        if(!StringUtil.isEmpty(amResignBO.getParams()))
        {
            String arr[] = amResignBO.getParams().split(",");
            for(int i=0;i<arr.length;i++) {
                param.add(arr[i]);
            }
        }
        amResignBO.setParam(param);

        return baseMapper.taskCount(amResignBO);
    }

    @Override
    public List<AmResignBO> queryAmResignDetail(Map<String,Object> param) {
        return  baseMapper.queryAmResignDetail(param);
    }

    @Override
    public List<resignSearchExportOpt> queryAmResignList(AmResignBO amResignBO) {
        return baseMapper.queryAmResignList(amResignBO);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public AmResign saveAmResign(AmResignBO bo) {
        AmResign entity = new AmResign();
        BeanUtils.copyProperties(bo,entity);
        String userId = "sys";
        try {
            userId = UserContext.getUserId();
        } catch (Exception e) {

        }

        LocalDateTime now = LocalDateTime.now();
        if(entity.getResignId()==null){
            entity.setCreatedTime(now);
            entity.setModifiedTime(now);
            entity.setCreatedBy(userId);
            entity.setModifiedBy(userId);
            entity.setActive(true);
        }else{
            AmResign entity1 = this.selectById(entity.getResignId());
            entity.setCreatedBy(entity1.getCreatedBy());
            entity.setCreatedTime(entity1.getCreatedTime());
            entity.setActive(true);
            entity.setModifiedTime(now);
            entity.setModifiedBy(userId);
        }
        entity.setResignOperateMan(ReasonUtil.getUserName());

        Integer isFinish = 0;
        AmEmpTask amEmpTask = null;
        if(!StringUtil.isEmpty(bo.getResignFeedback()))
        {
            amEmpTask = taskService.selectById(bo.getEmpTaskId());
            if("1".equals(bo.getResignFeedback())&&bo.getJobCentreFeedbackDate()==null)
            {
                /**
                 * 退工任务单签收 但退工成功日期为空
                 */
                amEmpTask.setTaskStatus(98);
            }else if("1".equals(bo.getResignFeedback())&&bo.getJobCentreFeedbackDate()!=null){
                /**
                 * 更加v6文档有退工成功返回日期 并且 退工任务单签收 代表退工成功
                 */
                amEmpTask.setTaskStatus(1);
                isFinish = 1;
            }else {
                amEmpTask.setTaskStatus(Integer.parseInt(bo.getResignFeedback()));
            }

            taskService.insertOrUpdate(amEmpTask);
            if(isFinish==0){
                isFinish = this.isResginFinish(bo,amEmpTask);
            }
            entity.setIsFinish(isFinish);

            AmResignLink amResignLink = new AmResignLink();
            amResignLink.setTaskId(amEmpTask.getTaskId());
            amResignLink.setResignFeedback(ReasonUtil.getTgfk(bo.getResignFeedback()));
            amResignLink.setJobCentreFeedbackDate(bo.getJobCentreFeedbackDate());
            amResignLink.setResignFeedbackDate(bo.getResignFeedbackDate());
            amResignLink.setCreatedTime(now);
            amResignLink.setModifiedTime(now);

            amResignLink.setCreatedBy(userId);
            amResignLink.setModifiedBy(userId);

            amResignLinkService.insert(amResignLink);
        }

        boolean result = super.insertOrUpdateAllColumn(entity);

        if(isFinish==1&&result)
        {
            Map<String,Object> variables = new HashMap<>();
            variables.put("status", true);
            variables.put("remark",ReasonUtil.getTgfk(bo.getResignFeedback()));
            String userName = "system";
            try {
                userName = UserContext.getUser().getDisplayName();
            } catch (Exception e) {

            }
            variables.put("assignee",userName);
            variables.put("fire_material",true);
            variables.put("empTaskId",bo.getEmpTaskId());
            TaskCommonUtils.completeTask(amEmpTask.getTaskId(),employeeInfoProxy,variables);
        }

        return  entity;
    }

    /**
     *判断是否完成退工详情见v6版退工反馈
     * @param bo
     * @param amEmpTask
     * @return
     */
    Integer  isResginFinish(AmResignBO bo,AmEmpTask amEmpTask){
        String outReasonCode = amEmpTask.getOutReasonCode();
        String resignFeedback = bo.getResignFeedback();
        if(StringUtil.isEmpty(outReasonCode))
        {
            return  0;
        }
        /**
         * 13 15 代表无需退工(退工原因)，转其他城市缴纳按照辞职的原因来看待
         */
        if("13".equals(outReasonCode)||"15".equals(outReasonCode))
        {
            if("1".equals(resignFeedback))
            {
                return 1;
            }
        }else{
            if("6".equals(resignFeedback)||"12".equals(resignFeedback))
            {
                return 1;
            }
            if("13".equals(resignFeedback)||"14".equals(resignFeedback))
            {
                return 1;
            }
            if("15".equals(resignFeedback)||"16".equals(resignFeedback))
            {
                return 1;
            }
            if("17".equals(resignFeedback))
            {
                return 1;
            }
        }

        return 0;
    }
}