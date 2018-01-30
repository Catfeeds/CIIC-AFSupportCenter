package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.host.messageBus;

import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.business.SsComTaskService;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.business.SsEmpTaskFrontService;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.business.SsEmpTaskService;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.business.SsPaymentComService;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dto.AFEmpSocialDTO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dto.AFEmployeeCompanyDTO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.dto.EmployeeInfoDTO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.entity.SsComTask;
import com.ciicsh.gto.settlementcenter.payment.cmdapi.dto.PayApplyPayStatusDTO;
import com.ciicsh.gto.sheetservice.api.dto.TaskCreateMsgDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

//    @Autowired
//    private SheetServiceProxy sheetServiceProxy;

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

        logger.info("收到消息 雇员新增TOPIC: " + taskMsgDTO.toString() + "，处理结果：" + (res ? "成功" : "失败"));
    }

    @StreamListener(TaskSink.AF_EMP_OUT)
    public void receiveEmpOut(Message<TaskCreateMsgDTO> message) {
        TaskCreateMsgDTO taskMsgDTO = message.getPayload();
        //社保
        boolean res = false;
        if (TaskSink.SOCIAL_NEW.equals(taskMsgDTO.getTaskType())
            || TaskSink.SOCIAL_STOP.equals(taskMsgDTO.getTaskType())) {
            res = insertTaskTb(taskMsgDTO, 1);
        }
        logger.info("收到消息 雇员终止TOPIC: " + taskMsgDTO.toString() + "，处理结果：" + (res ? "成功" : "失败"));
    }

    @StreamListener(TaskSink.CHARGE_RESUME)
    public void receiveChargeResume(Message<TaskCreateMsgDTO> message) {
        TaskCreateMsgDTO taskMsgDTO = message.getPayload();
        //社保
        boolean res = false;
        if (TaskSink.SOCIAL_NEW.equals(taskMsgDTO.getTaskType())
            || TaskSink.SOCIAL_STOP.equals(taskMsgDTO.getTaskType())) {
            res = insertTaskTb(taskMsgDTO, 1);
        }
        logger.info("收到消息 恢复缴费TOPIC: " + taskMsgDTO.toString() + "，处理结果：" + (res ? "成功" : "失败"));
    }

    @StreamListener(TaskSink.CHARGE_STOP)
    public void receiveChargeStop(Message<TaskCreateMsgDTO> message) {
        TaskCreateMsgDTO taskMsgDTO = message.getPayload();
        //社保
        boolean res = false;
        if (TaskSink.SOCIAL_NEW.equals(taskMsgDTO.getTaskType())
            || TaskSink.SOCIAL_STOP.equals(taskMsgDTO.getTaskType())) {
            res = insertTaskTb(taskMsgDTO, 1);
        }
        logger.info("收到消息 暂停缴费TOPIC: " + taskMsgDTO.toString() + "，处理结果：" + (res ? "成功" : "失败"));
    }

    @StreamListener(TaskSink.EMP_COMPANY_CHANGE)
    public void receiveEmpCompanyChange(Message<TaskCreateMsgDTO> message) {
        TaskCreateMsgDTO taskMsgDTO = message.getPayload();
        //社保
        boolean res = false;
        if (TaskSink.SOCIAL_NEW.equals(taskMsgDTO.getTaskType())
            || TaskSink.SOCIAL_STOP.equals(taskMsgDTO.getTaskType())) {
            res = insertTaskTb(taskMsgDTO, 1);
        }
        logger.info("收到消息 雇员翻牌TOPIC: " + taskMsgDTO.toString() + "，处理结果：" + (res ? "成功" : "失败"));
    }

    @StreamListener(TaskSink.NONLOCAL_TO_SH)
    public void receiveNonlocalToSh(Message<TaskCreateMsgDTO> message) {
        TaskCreateMsgDTO taskMsgDTO = message.getPayload();

        //社保
        boolean res = false;
        if (TaskSink.SOCIAL_NEW.equals(taskMsgDTO.getTaskType())
            || TaskSink.SOCIAL_STOP.equals(taskMsgDTO.getTaskType())) {
            res = insertTaskTb(taskMsgDTO, 1);
        }

        logger.info("收到消息 外地转上海TOPIC: " + taskMsgDTO.toString() + "，处理结果：" + (res ? "成功" : "失败"));
    }

    @StreamListener(TaskSink.SH_TO_NONLOCAL)
    public void receiveSh(Message<TaskCreateMsgDTO> message) {
        TaskCreateMsgDTO taskMsgDTO = message.getPayload();
        //社保
        boolean res = false;
        if (TaskSink.SOCIAL_NEW.equals(taskMsgDTO.getTaskType())
            || TaskSink.SOCIAL_STOP.equals(taskMsgDTO.getTaskType())) {
            res = insertTaskTb(taskMsgDTO, 1);
        }
        logger.info("收到消息上海转外地TOPIC: " + taskMsgDTO.toString() + "，处理结果：" + (res ? "成功" : "失败"));
    }

    @StreamListener(TaskSink.BASE_ADJUST_YEARLY_SH)
    public void receiveBaseAdjustYearlySh(Message<TaskCreateMsgDTO> message) {
        TaskCreateMsgDTO taskMsgDTO = message.getPayload();
        //社保
        boolean res = false;
        if (TaskSink.SOCIAL_ADJUST.equals(taskMsgDTO.getTaskType())) {
            res = insertTaskTb(taskMsgDTO, 1);
        }
        logger.info("收到消息 上海基数年调TOPIC: " + taskMsgDTO.toString() + "，处理结果：" + (res ? "成功" : "失败"));
    }

    @StreamListener(TaskSink.PAY_APPLY_PAY_STATUS_STREAM)
    public void rejectPayApplyIdStream(Message<PayApplyPayStatusDTO> message) {
        PayApplyPayStatusDTO taskMsgDTO = message.getPayload();
        boolean res = false;
        //社保
        if (taskMsgDTO.getBusinessType() == 1) {
            res = ssPaymentComService.saveRejectResult(taskMsgDTO.getBusinessPkId(), taskMsgDTO.getRemark());
        }
        logger.info("收到消息 付款申请拒绝: " + taskMsgDTO.toString() + "，处理结果：" + (res ? "成功" : "失败"));
    }

    //todo 接受客服中心调用更新企业任务单
    public void receiveComTask(Message<TaskCreateMsgDTO> message) {
        TaskCreateMsgDTO taskMsgDTO = message.getPayload();
        //社保
        boolean res = false;
        if (TaskSink.SOCIAL_ADJUST.equals(taskMsgDTO.getTaskType())) {
            SsComTask ele = ssComTaskService.selectById(taskMsgDTO.getTaskId());
            ele.setTaskId(taskMsgDTO.getTaskId());
            res = ssComTaskService.updateById(ele);
        }
        logger.info("收到消息 客服中心调用更新企业任务单: " + taskMsgDTO.toString() + "，处理结果：" + (res ? "成功" : "失败"));
    }

    private EmployeeInfoDTO callInf(TaskCreateMsgDTO taskMsgDTO) {
//        logger.info("数据获取接口开始调用："+taskSheetRequestDTO.toString());
//        try {
//            TaskRequestDTO taskRequestDTO = new TaskRequestDTO();
//            taskRequestDTO.setTaskId(taskSheetRequestDTO.getTaskId());
//            taskRequestDTO.setAssignee(taskSheetRequestDTO.getAssignee());
//            taskRequestDTO.setVariable(taskSheetRequestDTO.getVariable());
//            //
//            Result restResult = sheetServiceProxy.completeTask(taskRequestDTO);
//            logger.info("数据获取接口返回："+String.valueOf("code:"+restResult.getCode()+"message:")+restResult.getMessage());
//            return ResultGenerator.genSuccessResult(true);
//        } catch (Exception e) {
//            logger.error(e.getMessage(), e);
//            return ResultGenerator.genServerFailResult();
//        }
        EmployeeInfoDTO dto = new EmployeeInfoDTO();
        AFEmployeeCompanyDTO d1 = new AFEmployeeCompanyDTO();
        d1.setCompanyId("1");
        d1.setInDate(new Date());
        d1.setOutDate(new Date());
        dto.setEmployeeCompany(d1);

        List<AFEmpSocialDTO> empSocial = new ArrayList<>();
        AFEmpSocialDTO dd2 = new AFEmpSocialDTO();
        dd2.setEmpBase(new BigDecimal(12));
        empSocial.add(dd2);
        dd2 = new AFEmpSocialDTO();
        dd2.setEmpBase(new BigDecimal(13));
        empSocial.add(dd2);
        dto.setEmpSocial(empSocial);
        return dto;
    }

    /**
     * <p>Description: 从接口获取数据并保存到雇员任务单表</p>
     *
     * @return
     * @author zhangxj
     * @date 2017-12-28
     */
    private boolean insertTaskTb(TaskCreateMsgDTO taskMsgDTO, Integer taskCategory) {
        //调用接口
        EmployeeInfoDTO dto = callInf(taskMsgDTO);

        boolean result = ssEmpTaskFrontService.insertTaskTb(taskMsgDTO, taskCategory, dto);

        return result;
    }
}
