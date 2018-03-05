package com.ciicsh.gto.afsupportcenter.housefund.messageservice.host.message;

import com.alibaba.fastjson.JSON;
import com.ciicsh.gto.afcompanycenter.queryservice.api.dto.employee.AfEmployeeInfoDTO;
import com.ciicsh.gto.afcompanycenter.queryservice.api.dto.employee.AfEmployeeQueryDTO;
import com.ciicsh.gto.afcompanycenter.queryservice.api.proxy.AfEmployeeCompanyProxy;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.business.HfComTaskService;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.business.HfEmpTaskService;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.business.HfPaymentAccountService;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.HfComTask;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.HfEmpTask;
import com.ciicsh.gto.settlementcenter.payment.cmdapi.dto.PayApplyPayStatusDTO;
import com.ciicsh.gto.sheetservice.api.dto.TaskCreateMsgDTO;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@EnableBinding(value = TaskSink.class)
@Component
public class KafkaReceiver {
    private final static Logger logger = LoggerFactory.getLogger(KafkaReceiver.class);

    @Autowired
    private HfEmpTaskService hfEmpTaskService;
    @Autowired
    private HfComTaskService hfComTaskService;
    @Autowired
    private HfPaymentAccountService hfPaymentAccountService;
    @Autowired
    private AfEmployeeCompanyProxy afEmployeeCompanyProxy;

    /**
     * 公积金雇员新进任务单
     * @param message
     */
    @StreamListener(TaskSink.AF_EMP_IN)
    public void receiveEmpIn(Message<TaskCreateMsgDTO> message) {
        TaskCreateMsgDTO taskMsgDTO = message.getPayload();
        //公积金
        boolean res = false;
        if (TaskSink.FUND_NEW.equals(taskMsgDTO.getTaskType())
            || TaskSink.ADD_FUND_NEW.equals(taskMsgDTO.getTaskType())) {
            Map<String, Object> paramMap = taskMsgDTO.getVariables();
            if (paramMap.get("fundType") != null) {
                boolean bolRes = checkDupSsEmpTask(taskMsgDTO);
                if (bolRes) {
                    String sType = paramMap.get("fundType").toString();
                    res = insertHfEmpTaskTb(taskMsgDTO, Integer.parseInt(sType));
                    logger.info("收到消息 公积金雇员新增: " + JSON.toJSONString(taskMsgDTO) + "，处理结果：" + (res ? "成功" : "失败"));
                }
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
        //公积金
        boolean res = false;
        if (TaskSink.FUND_STOP.equals(taskMsgDTO.getTaskType())
            || TaskSink.ADD_FUND_STOP.equals(taskMsgDTO.getTaskType())) {
            boolean bolRes = checkDupSsEmpTask(taskMsgDTO);
            if (bolRes) {
                res = insertHfEmpTaskTb(taskMsgDTO, 7);
                logger.info("收到消息 公积金雇员终止: " + JSON.toJSONString(taskMsgDTO) + "，处理结果：" + (res ? "成功" : "失败"));
            }
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
        //公积金
        boolean res = false;
        if (TaskSink.FUND_MAKE_UP.equals(taskMsgDTO.getTaskType())
            || TaskSink.ADD_FUND_MAKE_UP.equals(taskMsgDTO.getTaskType())) {
            boolean bolRes = checkDupSsEmpTask(taskMsgDTO);
            if (bolRes) {
                res = insertHfEmpTaskTb(taskMsgDTO, 6);
                logger.info("收到消息 公积金雇员补缴: " + JSON.toJSONString(taskMsgDTO) + "，处理结果：" + (res ? "成功" : "失败"));
            }
        }
    }

    /**
     * 订阅雇员翻牌任务单
     *
     * @param message
     */
    @StreamListener(TaskSink.AF_EMP_COMPANY_CHANGE)
    public void receiveEmpCompanyChange(Message<TaskCreateMsgDTO> message) {
        TaskCreateMsgDTO taskMsgDTO = message.getPayload();
        //公积金
        boolean res = false;
        if (TaskSink.FUND_STOP.equals(taskMsgDTO.getTaskType())
            || TaskSink.ADD_FUND_STOP.equals(taskMsgDTO.getTaskType())) {
            boolean bolRes = checkDupSsEmpTask(taskMsgDTO);
            if (bolRes) {
                res = insertHfEmpTaskTb(taskMsgDTO, 13);
                logger.info("收到消息 公积金雇员翻牌: " + JSON.toJSONString(taskMsgDTO) + "，处理结果：" + (res ? "成功" : "失败"));
            }
        }
    }

    /**
     * 订阅雇员服务协议调整任务单
     *
     * @param message
     */
    @StreamListener(TaskSink.AF_EMP_AGREEMENT_ADJUST)
    public void receiveNonlocalToSh(Message<TaskCreateMsgDTO> message) {
        TaskCreateMsgDTO taskMsgDTO = message.getPayload();

        //公积金
        boolean res = false;
        if (TaskSink.FUND_NEW.equals(taskMsgDTO.getTaskType())
            || TaskSink.ADD_FUND_NEW.equals(taskMsgDTO.getTaskType())
            || TaskSink.FUND_STOP.equals(taskMsgDTO.getTaskType())
            || TaskSink.ADD_FUND_STOP.equals(taskMsgDTO.getTaskType())) {
            boolean bolRes = checkDupSsEmpTask(taskMsgDTO);
            if (bolRes) {
                res = insertHfEmpTaskTb(taskMsgDTO, 4);
                logger.info("收到消息 公积金雇员服务协议调整: " + JSON.toJSONString(taskMsgDTO) + "，处理结果：" + (res ? "成功" : "失败"));
            }
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

        //公积金
        boolean res = false;
        if (TaskSink.FUND_NEW.equals(taskMsgDTO.getTaskType())
            || TaskSink.ADD_FUND_NEW.equals(taskMsgDTO.getTaskType())
            || TaskSink.FUND_STOP.equals(taskMsgDTO.getTaskType())
            || TaskSink.ADD_FUND_STOP.equals(taskMsgDTO.getTaskType())) {
            try {
                //调用接口
                AfEmployeeInfoDTO dto = callInf(taskMsgDTO);

                //未办理任务单
                if (StringUtils.isBlank(taskMsgDTO.getTaskId())) {
                    res = hfEmpTaskService.updateEmpTaskTc(taskMsgDTO, dto);
                    logger.info("收到消息 公积金雇员服务协议更正:" + JSON.toJSONString(taskMsgDTO) + "，处理结果：" + (res ? "成功" : "失败"));

                    //已办理任务单
                } else {
                    boolean bolRes = checkDupSsEmpTask(taskMsgDTO);
                    if (bolRes) {
                        Integer taskCategory = 0;
                        Map<String, Object> paramMap = taskMsgDTO.getVariables();
                        HfEmpTask qd = new HfEmpTask();
                        qd.setTaskId(paramMap.get("oldEmpAgreementId").toString());
                        qd.setEmployeeId(paramMap.get("employeeId").toString());
                        qd.setCompanyId(paramMap.get("companyId").toString());

                        //查询旧的任务类型保存到新的任务单
                        List<HfEmpTask> resList = hfEmpTaskService.queryByTaskId(qd);
                        if (resList.size() > 0) {
                            HfEmpTask hfEmpTask = resList.get(0);
                            taskCategory = hfEmpTask.getTaskCategory();
                        }
                        res = hfEmpTaskService.saveEmpTaskTc(taskMsgDTO, taskCategory, 1, dto);
                        logger.info("收到消息 公积金雇员服务协议更正:" + JSON.toJSONString(taskMsgDTO) + "，处理结果：" + (res ? "成功" :
                            "失败"));
                    }
                }
            } catch (Exception e) {
                res = false;
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
        boolean res = false;
        //公积金
        if (taskMsgDTO.getBusinessType() == 2) {
            try {
                res = hfPaymentAccountService.saveRejectResult(taskMsgDTO.getBusinessPkId(), taskMsgDTO.getRemark(),
                    taskMsgDTO.getPayStatus());
            } catch (Exception e) {
                res = false;
                logger.error(e.getMessage(), e);
            }
            logger.info("收到消息 公积金财务付款申请回调:" + JSON.toJSONString(taskMsgDTO) + "，处理结果：" + (res ? "成功" : "失败"));
        }
    }

    /**
     * 订阅客服中心调用更新企业任务单
     *
     * @param message
     * @return
     */
    @StreamListener(TaskSink.AF_COMPANY_SOCIAL_ACCOUNT_ONCE11)
    public void receiveComTask(Message<TaskCreateMsgDTO> message) {
        TaskCreateMsgDTO taskMsgDTO = message.getPayload();
        //公积金
        boolean res = false;
        try {
            HfComTask ele = hfComTaskService.selectById(taskMsgDTO.getMissionId());
            ele.setTaskId(taskMsgDTO.getTaskId());
            res = hfComTaskService.updateById(ele);
        } catch (Exception e) {
            res = false;
            logger.error(e.getMessage(), e);
        }
        logger.info("收到消息 公积金客服中心调用更新企业任务单: " + JSON.toJSONString(taskMsgDTO) + "，处理结果：" + (res ? "成功" : "失败"));
    }

    /**
     * 调用获取当前雇员信息接口
     *
     * @param taskMsgDTO
     * @return
     */
    private AfEmployeeInfoDTO callInf(TaskCreateMsgDTO taskMsgDTO) {
        logger.info("公积金 当前雇员信息获取接口 开始调用：" + JSON.toJSONString(taskMsgDTO));
        AfEmployeeInfoDTO resDto = null;
        try {
            AfEmployeeQueryDTO taskRequestDTO = new AfEmployeeQueryDTO();
            taskRequestDTO.setEmpAgreementId(Long.parseLong(taskMsgDTO.getMissionId()));

            resDto = afEmployeeCompanyProxy.getEmployeeCompany(taskRequestDTO);

            logger.info("公积金 当前雇员信息获取接口 结束调用");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return resDto;
    }

    /**
     * 从接口获取数据并保存到社保雇员任务单表
     *
     * @param taskMsgDTO
     * @param taskCategory
     * @return
     */
    private boolean insertHfEmpTaskTb(TaskCreateMsgDTO taskMsgDTO, Integer taskCategory) {
        boolean result = false;

        try {
            //调用当前雇员信息获取接口
            AfEmployeeInfoDTO dto = callInf(taskMsgDTO);

            if (dto != null) {
                //插入数据到雇员任务单表
                result = hfEmpTaskService.insertTaskTb(taskMsgDTO, taskCategory, 0, dto);
            } else {
                result = false;
                logger.error("公积金 雇员信息获取失败！");
            }
        } catch (Exception e) {
            result = false;
            logger.error(e.getMessage(), e);
        }
        return result;
    }

    /**
     * 判断是否存在任务单ID
     *
     * @param taskMsgDTO
     * @return
     */
    private boolean checkDupSsEmpTask(TaskCreateMsgDTO taskMsgDTO) {
        boolean result = false;

        HfEmpTask qd = new HfEmpTask();
        qd.setTaskId(taskMsgDTO.getTaskId());

        //查询任务单
        List<HfEmpTask> resList = hfEmpTaskService.queryByTaskId(qd);
        if (resList.size() == 0) {
            result = true;
        }
        return result;
    }
}
