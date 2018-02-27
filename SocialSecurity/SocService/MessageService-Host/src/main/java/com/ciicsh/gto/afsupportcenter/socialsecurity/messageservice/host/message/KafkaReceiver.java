package com.ciicsh.gto.afsupportcenter.socialsecurity.messageservice.host.message;

import com.alibaba.fastjson.JSON;
import com.ciicsh.gto.afcompanycenter.queryservice.api.dto.employee.AfEmployeeInfoDTO;
import com.ciicsh.gto.afcompanycenter.queryservice.api.dto.employee.AfEmployeeQueryDTO;
import com.ciicsh.gto.afcompanycenter.queryservice.api.proxy.AfEmployeeCompanyProxy;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo.SsEmpTaskBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business.SsComTaskService;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business.SsEmpTaskFrontService;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business.SsEmpTaskService;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business.SsPaymentComService;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.SsComTask;
import com.ciicsh.gto.afsupportcenter.util.constant.SocialSecurityConst;
import com.ciicsh.gto.settlementcenter.payment.cmdapi.dto.PayApplyPayStatusDTO;
import com.ciicsh.gto.sheetservice.api.dto.TaskCreateMsgDTO;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Created by houwanhua on 2018/2/24
 */
@EnableBinding(value = TaskSink.class)
@Component
public class KafkaReceiver {
    private final static Logger logger = LoggerFactory.getLogger(KafkaReceiver.class);

    @Autowired
    private SsEmpTaskService ssEmpTaskService;
    @Autowired
    private SsEmpTaskFrontService ssEmpTaskFrontService;
    @Autowired
    private SsComTaskService ssComTaskService;
    @Autowired
    private SsPaymentComService ssPaymentComService;
    @Autowired
    private AfEmployeeCompanyProxy afEmployeeCompanyProxy;

    /**
     * 订阅社保新进任务单
     *
     * @param message
     */
    @StreamListener(TaskSink.AF_EMP_IN)
    public void receiveSocialNew(Message<TaskCreateMsgDTO> message) {
        TaskCreateMsgDTO taskMsgDTO = message.getPayload();
        //判断taskType是否是社保新进(social_new)，如果不是则无需处理
        if (TaskSink.SOCIAL_NEW.equals(taskMsgDTO.getTaskType())) {
            //获取任务单参数
            Map<String, Object> paramMap = taskMsgDTO.getVariables();
            if (paramMap != null && paramMap.get("socialType") != null) {
                String socialType = paramMap.get("socialType").toString();
                saveSsEmpTask(taskMsgDTO, Integer.parseInt(socialType));
                logger.info("receiveSocialNew: " + JSON.toJSONString(taskMsgDTO));
            }
        }
    }

    /**
     * 订阅雇员终止任务单
     *
     * @param message
     */
    @StreamListener(TaskSink.AF_EMP_OUT)
    public void receiveEmpOut(Message<TaskCreateMsgDTO> message) {
        TaskCreateMsgDTO taskMsgDTO = message.getPayload();
        //判断taskType是否是社保新进(social_stop)，如果不是则无需处理
        if (TaskSink.SOCIAL_STOP.equals(taskMsgDTO.getTaskType())) {
            saveSsEmpTask(taskMsgDTO, Integer.parseInt(SocialSecurityConst.TASK_TYPE_5));
            logger.info("receiveEmpOut: " + JSON.toJSONString(taskMsgDTO));
        }
    }

    /**
     * 订阅雇员补缴任务单
     *
     * @param message
     */
    @StreamListener(TaskSink.AF_EMP_MAKE_UP)
    public void receiveChargeResume(Message<TaskCreateMsgDTO> message) {
        TaskCreateMsgDTO taskMsgDTO = message.getPayload();
        //判断taskType是否是社保新进(social_make_up)，如果不是则无需处理
        if (TaskSink.SOCIAL_MAKE_UP.equals(taskMsgDTO.getTaskType())) {
            saveSsEmpTask(taskMsgDTO, Integer.parseInt(SocialSecurityConst.TASK_TYPE_4));
            logger.info("entering receiveChargeResume: " + JSON.toJSONString(taskMsgDTO));
        }
    }

    /**
     * 订阅雇员翻牌任务单
     *
     * @param message
     */
//    @StreamListener(TaskSink.AF_EMP_COMPANY_CHANGE)
//    public void receiveEmpCompanyChange(Message<TaskCreateMsgDTO> message) {
//        TaskCreateMsgDTO taskMsgDTO = message.getPayload();
//        //社保
//        if (TaskSink.SOCIAL_STOP.equals(taskMsgDTO.getTaskType())) {
//            saveSsEmpTask(taskMsgDTO, 12);
//            logger.info("receiveEmpCompanyChange: " + JSON.toJSONString(taskMsgDTO));
//        }
//    }

    /**
     * 订阅雇员服务协议调整任务单
     *
     * @param message
     */
    @StreamListener(TaskSink.AF_EMP_AGREEMENT_ADJUST)
    public void receiveAdjust(Message<TaskCreateMsgDTO> message) {
        TaskCreateMsgDTO taskMsgDTO = message.getPayload();
        //判断taskType是否是社保新进或停办(social_new或social_stop)，如果不是则无需处理
        //注：只有当社保缴纳地从上海变成外地时，才会收到social_stop类型，其他都是social_new
        if (TaskSink.SOCIAL_NEW.equals(taskMsgDTO.getTaskType()) || TaskSink.SOCIAL_STOP.equals(taskMsgDTO.getTaskType())) {
            saveSsEmpTask(taskMsgDTO, Integer.parseInt(SocialSecurityConst.TASK_TYPE_3));
            logger.info("receiveAdjust: " + JSON.toJSONString(taskMsgDTO));
        }
    }

    /**
     * 订阅雇员服务协议更正任务单
     *
     * @param message
     * @return
     */
    @StreamListener(TaskSink.AF_EMP_AGREEMENT_UPDATE)
    public void receiveSh(Message<TaskCreateMsgDTO> message) {
        TaskCreateMsgDTO taskMsgDTO = message.getPayload();

        //社保
        if (TaskSink.SOCIAL_NEW.equals(taskMsgDTO.getTaskType()) || TaskSink.SOCIAL_STOP.equals(taskMsgDTO.getTaskType())) {
            try {
                //调用接口-调用客服中心接口，获取任务单表单信息
                AfEmployeeInfoDTO dto = callEmployeeCompany(taskMsgDTO);
                //未办理任务单
                if (StringUtils.isBlank(taskMsgDTO.getTaskId())) {
                    ssEmpTaskFrontService.updateEmpTaskTc(taskMsgDTO, dto);
                    logger.info("entering receiveSh:" + JSON.toJSONString(taskMsgDTO));
                    //已办理任务单
                } else {
                    Integer taskCategory = 0;
                    Map<String, Object> paramMap = taskMsgDTO.getVariables();
                    SsEmpTaskBO qd = new SsEmpTaskBO();
                    qd.setTaskId(paramMap.get("oldEmpAgreementId").toString());
                    qd.setEmployeeId(paramMap.get("employeeId").toString());
                    qd.setCompanyId(paramMap.get("companyId").toString());

                    //查询旧的任务类型保存到新的任务单
                    List<SsEmpTaskBO> resList = ssEmpTaskService.queryByTaskId(qd);
                    if (resList.size() > 0) {
                        SsEmpTaskBO ssEmpTaskBO = resList.get(0);
                        taskCategory = ssEmpTaskBO.getTaskCategory();
                    }
                    ssEmpTaskFrontService.saveEmpTaskTc(taskMsgDTO, taskCategory, 1, dto);
                    logger.info("entering receiveSh:" + JSON.toJSONString(taskMsgDTO));
                }
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
        }
    }

    /**
     * 订阅财务付款申请回调任务单
     *
     * @param message
     * @return
     */
    @StreamListener(TaskSink.PAY_APPLY_PAY_STATUS_STREAM)
    public void rejectPayApplyIdStream(Message<PayApplyPayStatusDTO> message) {
        PayApplyPayStatusDTO taskMsgDTO = message.getPayload();
        //社保
        if (taskMsgDTO.getBusinessType() == 1) {
            try {
                ssPaymentComService.saveRejectResult(taskMsgDTO.getBusinessPkId(), taskMsgDTO.getRemark(),taskMsgDTO.getPayStatus());
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
            logger.info("entering rejectPayApplyIdStream:" + JSON.toJSONString(taskMsgDTO));
        }
    }

    /**
     * 订阅客服中心调用更新企业任务单
     *
     * @param message
     * @return
     */
    @StreamListener(TaskSink.AF_COMPANY_SOCIAL_ACCOUNT_ONCE)
    public void receiveComTask(Message<TaskCreateMsgDTO> message) {
        TaskCreateMsgDTO taskMsgDTO = message.getPayload();
        //社保
        try {
            SsComTask ele = ssComTaskService.selectById(taskMsgDTO.getMissionId());
            ele.setTaskId(taskMsgDTO.getTaskId());
            ssComTaskService.updateById(ele);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        logger.info("entering receiveComTask: " + JSON.toJSONString(taskMsgDTO));
    }

    /**
     * 从接口获取数据并保存到社保雇员任务单表
     *
     * @param taskCreateMsgDTO
     * @return
     */
    private AfEmployeeInfoDTO callEmployeeCompany(TaskCreateMsgDTO taskCreateMsgDTO) {
        logger.info("entering callEmployeeCompany：" + JSON.toJSONString(taskCreateMsgDTO));
        AfEmployeeInfoDTO afEmployeeInfoDTO = null;
        try {
            AfEmployeeQueryDTO taskRequestDTO = new AfEmployeeQueryDTO();
            taskRequestDTO.setEmpAgreementId(Long.parseLong(taskCreateMsgDTO.getMissionId()));
            afEmployeeInfoDTO = afEmployeeCompanyProxy.getEmployeeCompany(taskRequestDTO);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return afEmployeeInfoDTO;
    }

    /**
     * 从接口获取数据并保存到社保雇员任务单表
     *
     * @param taskMsgDTO
     * @param socialType
     * @return
     */
    private void saveSsEmpTask(TaskCreateMsgDTO taskMsgDTO, Integer socialType) {
        try {
            //调用客服中心接口获取任务单表单信息
            AfEmployeeInfoDTO dto = callEmployeeCompany(taskMsgDTO);
            //保存雇员任务单表数据
            ssEmpTaskFrontService.saveSsEmpTask(taskMsgDTO, socialType, 0, dto);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * 判断是否存在任务单ID
     *
     * @param taskMsgDTO
     * @return
     */
//    private boolean checkDupSsEmpTask(TaskCreateMsgDTO taskMsgDTO) {
//        boolean result = false;
//
//        SsEmpTaskBO qd = new SsEmpTaskBO();
//        qd.setTaskId(taskMsgDTO.getTaskId());
//
//        //查询任务单
//        List<SsEmpTaskBO> resList = ssEmpTaskService.queryByTaskId(qd);
//        if (resList.size() == 0) {
//            result = true;
//        }
//        return result;
//    }
}
