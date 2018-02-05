package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.host.messageBus;

import com.alibaba.fastjson.JSON;
import com.ciicsh.gto.afcompanycenter.queryservice.api.dto.employee.AfEmployeeInfoDTO;
import com.ciicsh.gto.afcompanycenter.queryservice.api.dto.employee.AfEmployeeQueryDTO;
import com.ciicsh.gto.afcompanycenter.queryservice.api.proxy.AfEmployeeCompanyProxy;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.bo.SsEmpTaskBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.business.SsComTaskService;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.business.SsEmpTaskFrontService;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.business.SsEmpTaskService;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.business.SsPaymentComService;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.SsComTask;
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
 * Created by songjt on 17/12/18.
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
     * 订阅雇员新增任务单
     *
     * @param message
     */
    @StreamListener(TaskSink.AF_EMP_IN)
    public void receiveEmpIn(Message<TaskCreateMsgDTO> message) {
        TaskCreateMsgDTO taskMsgDTO = message.getPayload();
        //社保
        boolean res = false;
        if (TaskSink.SOCIAL_NEW.equals(taskMsgDTO.getTaskType())
            || TaskSink.SOCIAL_STOP.equals(taskMsgDTO.getTaskType())) {

            //TODO 从刘玉庭那边取DTO再从中取任务类型
//            insertTaskTb(taskMsgDTO, ((TestDto)taskMsgDTO.getVariables().get("content")).getTaskCategory());
            res = insertTaskTb(taskMsgDTO, 1);
        }

        logger.info("收到消息 雇员新增: " + JSON.toJSONString(taskMsgDTO) + "，处理结果：" + (res ? "成功" : "失败"));
    }

    /**
     * 订阅雇员终止任务单
     *
     * @param message
     */
    @StreamListener(TaskSink.AF_EMP_OUT)
    public void receiveEmpOut(Message<TaskCreateMsgDTO> message) {
        TaskCreateMsgDTO taskMsgDTO = message.getPayload();
        //社保
        boolean res = false;
        if (TaskSink.SOCIAL_NEW.equals(taskMsgDTO.getTaskType())
            || TaskSink.SOCIAL_STOP.equals(taskMsgDTO.getTaskType())) {
            res = insertTaskTb(taskMsgDTO, 5);
        }
        logger.info("收到消息 雇员终止: " + JSON.toJSONString(taskMsgDTO) + "，处理结果：" + (res ? "成功" : "失败"));
    }

    /**
     * 订阅雇员补缴任务单
     *
     * @param message
     */
    @StreamListener(TaskSink.AF_EMP_MAKE_UP)
    public void receiveChargeResume(Message<TaskCreateMsgDTO> message) {
        TaskCreateMsgDTO taskMsgDTO = message.getPayload();
        //社保
        boolean res = false;
        if (TaskSink.SOCIAL_MAKE_UP.equals(taskMsgDTO.getTaskType())) {
            res = insertTaskTb(taskMsgDTO, 4);
        }
        logger.info("收到消息 雇员补缴: " + JSON.toJSONString(taskMsgDTO) + "，处理结果：" + (res ? "成功" : "失败"));
    }

    /**
     * 订阅雇员翻牌任务单
     *
     * @param message
     */
    @StreamListener(TaskSink.AF_EMP_COMPANY_CHANGE)
    public void receiveEmpCompanyChange(Message<TaskCreateMsgDTO> message) {
        TaskCreateMsgDTO taskMsgDTO = message.getPayload();
        //社保
        boolean res = false;
        if (TaskSink.SOCIAL_NEW.equals(taskMsgDTO.getTaskType())
            || TaskSink.SOCIAL_STOP.equals(taskMsgDTO.getTaskType())) {
            res = insertTaskTb(taskMsgDTO, 12);
        }
        logger.info("收到消息 雇员翻牌: " + JSON.toJSONString(taskMsgDTO) + "，处理结果：" + (res ? "成功" : "失败"));
    }

    /**
     * 订阅雇员服务协议调整任务单
     *
     * @param message
     */
    @StreamListener(TaskSink.AF_EMP_AGREEMENT_ADJUST)
    public void receiveNonlocalToSh(Message<TaskCreateMsgDTO> message) {
        TaskCreateMsgDTO taskMsgDTO = message.getPayload();

        //社保
        boolean res = false;
        if (TaskSink.SOCIAL_NEW.equals(taskMsgDTO.getTaskType())
            || TaskSink.SOCIAL_STOP.equals(taskMsgDTO.getTaskType())) {
            res = insertTaskTb(taskMsgDTO, 3);
        }
        logger.info("收到消息 雇员服务协议调整: " + JSON.toJSONString(taskMsgDTO) + "，处理结果：" + (res ? "成功" : "失败"));
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
        boolean res = false;
        if (TaskSink.SOCIAL_NEW.equals(taskMsgDTO.getTaskType())
            || TaskSink.SOCIAL_STOP.equals(taskMsgDTO.getTaskType())) {
            try {
                //调用接口
                AfEmployeeInfoDTO dto = callInf(taskMsgDTO);

                //未办理任务单
                if (StringUtils.isBlank(taskMsgDTO.getTaskId())) {
                    res = ssEmpTaskFrontService.updateEmpTaskTc(taskMsgDTO, dto);

                    //已办理任务单
                } else {
                    Integer taskCategory = 0;
                    Map<String, Object> paramMap = taskMsgDTO.getVariables();
                    SsEmpTaskBO qd = new SsEmpTaskBO();
                    qd.setTaskId(paramMap.get("oldTaskId").toString());
                    qd.setEmployeeId(paramMap.get("employeeId").toString());
                    qd.setCompanyId(paramMap.get("companyId").toString());

                    //查询旧的任务类型保存到新的任务单
                    List<SsEmpTaskBO> resList = ssEmpTaskService.queryByTaskId(qd);
                    if (resList.size() > 0) {
                        SsEmpTaskBO ssEmpTaskBO = resList.get(0);
                        taskCategory = ssEmpTaskBO.getTaskCategory();
                    }
                    res = ssEmpTaskFrontService.saveEmpTaskTc(taskMsgDTO, taskCategory, 1, dto);
                }
            } catch (Exception e) {
                res = false;
                logger.error(e.getMessage(), e);
            }
        }
        logger.info("收到消息 雇员服务协议更正:" + JSON.toJSONString(taskMsgDTO) + "，处理结果：" + (res ? "成功" : "失败"));
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
        boolean res = false;
        //社保
        if (taskMsgDTO.getBusinessType() == 1) {
            try {
                res = ssPaymentComService.saveRejectResult(taskMsgDTO.getBusinessPkId(), taskMsgDTO.getRemark(),
                    taskMsgDTO.getPayStatus());
            } catch (Exception e) {
                res = false;
                logger.error(e.getMessage(), e);
            }
        }
        logger.info("收到消息 财务付款申请回调:" + JSON.toJSONString(taskMsgDTO) + "，处理结果：" + (res ? "成功" : "失败"));
    }

    /**
     * 订阅客服中心调用更新企业任务单
     *
     * @param message
     * @return
     */
    //todo 接受客服中心调用更新企业任务单
    public void receiveComTask(Message<TaskCreateMsgDTO> message) {
        TaskCreateMsgDTO taskMsgDTO = message.getPayload();
        //社保
        boolean res = false;
        try {
//        if (TaskSink.SOCIAL_ADJUST.equals(taskMsgDTO.getTaskType())) {
            SsComTask ele = ssComTaskService.selectById(taskMsgDTO.getTaskId());
            ele.setTaskId(taskMsgDTO.getTaskId());
            res = ssComTaskService.updateById(ele);
//        }
        } catch (Exception e) {
            res = false;
            logger.error(e.getMessage(), e);
        }
        logger.info("收到消息 客服中心调用更新企业任务单: " + JSON.toJSONString(taskMsgDTO) + "，处理结果：" + (res ? "成功" : "失败"));
    }

    private AfEmployeeInfoDTO callInf(TaskCreateMsgDTO taskMsgDTO) {
        logger.info("当前雇员信息获取接口 开始调用：" + JSON.toJSONString(taskMsgDTO));
        AfEmployeeInfoDTO resDto = null;
        try {
            AfEmployeeQueryDTO taskRequestDTO = new AfEmployeeQueryDTO();
            taskRequestDTO.setEmpAgreementId(Long.parseLong(taskMsgDTO.getMissionId()));

            resDto = afEmployeeCompanyProxy.getEmployeeCompany(taskRequestDTO);

            logger.info("当前雇员信息获取接口 结束调用");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return resDto;
    }

    /**
     * <p>Description: 从接口获取数据并保存到雇员任务单表</p>
     *
     * @return 处理结果
     * @author zhangxj
     * @date 2017-12-28
     */
    private boolean insertTaskTb(TaskCreateMsgDTO taskMsgDTO, Integer taskCategory) {
        boolean result = false;

        try {
            //调用当前雇员信息获取接口
            AfEmployeeInfoDTO dto = callInf(taskMsgDTO);

            if (dto != null) {
                //插入数据到雇员任务单表
                result = ssEmpTaskFrontService.insertTaskTb(taskMsgDTO, taskCategory, 0, dto);
            } else {
                result = false;
                logger.error("雇员信息获取失败！");
            }
        } catch (Exception e) {
            result = false;
            logger.error(e.getMessage(), e);
        }
        return result;
    }
}
