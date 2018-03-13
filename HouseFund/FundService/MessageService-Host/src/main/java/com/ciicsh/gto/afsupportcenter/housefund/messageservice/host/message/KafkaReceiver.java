package com.ciicsh.gto.afsupportcenter.housefund.messageservice.host.message;

import com.alibaba.fastjson.JSON;
import com.ciicsh.gto.afcompanycenter.queryservice.api.dto.employee.AfEmployeeInfoDTO;
import com.ciicsh.gto.afcompanycenter.queryservice.api.dto.employee.AfEmployeeQueryDTO;
import com.ciicsh.gto.afcompanycenter.queryservice.api.proxy.AfEmployeeCompanyProxy;
import com.ciicsh.gto.afcompanycenter.queryservice.api.proxy.AfEmployeeSocialProxy;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.business.HfComTaskService;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.business.HfEmpTaskService;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.business.HfPaymentAccountService;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.HfComTask;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.HfEmpTask;
import com.ciicsh.gto.afsupportcenter.housefund.messageservice.host.enumeration.FundCategory;
import com.ciicsh.gto.afsupportcenter.housefund.messageservice.host.enumeration.TaskCategory;
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
    @Autowired
    private AfEmployeeSocialProxy employeeSocialProxy;

    /**
     * 雇员公积金新进任务单
     * @param message
     */
    @StreamListener(TaskSink.AF_EMP_IN)
    public void fundEmpIn(Message<TaskCreateMsgDTO> message) {
        TaskCreateMsgDTO taskMsgDTO = message.getPayload();
        //判断是否是公积金或者补充公积金
        if (TaskSink.FUND_NEW.equals(taskMsgDTO.getTaskType()) || TaskSink.ADD_FUND_NEW.equals(taskMsgDTO.getTaskType())) {
            logger.info("start fundEmpIn: " + JSON.toJSONString(taskMsgDTO));
            Map<String, Object> paramMap = taskMsgDTO.getVariables();
            if (null != paramMap && paramMap.get("fundType") != null) {
                String taskCategory = paramMap.get("fundType").toString();
                String fundCategory = TaskSink.FUND_NEW.equals(taskMsgDTO.getTaskType()) ? FundCategory.BASICFUND.getCategory() : FundCategory.ADDFUND.getCategory();
                boolean result = saveEmpTask(taskMsgDTO, fundCategory, Integer.parseInt(taskCategory),0);
                logger.info("end fundEmpIn: " + JSON.toJSONString(taskMsgDTO) + "，result：" + (result ? "Success!" : "Fail!"));
            }
        }
    }

    /**
     * 雇员公积金终止任务单
     * @param message
     */
    @StreamListener(TaskSink.AF_EMP_OUT)
    public void fundEmpOut(Message<TaskCreateMsgDTO> message) {
        TaskCreateMsgDTO taskMsgDTO = message.getPayload();
        if (TaskSink.FUND_STOP.equals(taskMsgDTO.getTaskType()) || TaskSink.ADD_FUND_STOP.equals(taskMsgDTO.getTaskType())) {
            logger.info("start fundEmpOut: " + JSON.toJSONString(taskMsgDTO));
            String fundCategory = TaskSink.FUND_STOP.equals(taskMsgDTO.getTaskType()) ? FundCategory.BASICFUND.getCategory() : FundCategory.ADDFUND.getCategory();
            boolean res = saveEmpTask(taskMsgDTO, fundCategory,TaskCategory.LEAVETURNOUT.getCategory(),0);
            logger.info("end fundEmpOut:" + JSON.toJSONString(taskMsgDTO) + "，result：" + (res ? "Success!" : "Fail!"));
        }
    }

    /**
     * 雇员公积金补缴任务单
     * @param message
     */
    @StreamListener(TaskSink.AF_EMP_MAKE_UP)
    public void funEmpRepay(Message<TaskCreateMsgDTO> message) {
        TaskCreateMsgDTO taskMsgDTO = message.getPayload();
        if (TaskSink.FUND_MAKE_UP.equals(taskMsgDTO.getTaskType()) || TaskSink.ADD_FUND_MAKE_UP.equals(taskMsgDTO.getTaskType())) {
            logger.info("start funEmpRepay: " + JSON.toJSONString(taskMsgDTO));
            String fundCategory = TaskSink.FUND_MAKE_UP.equals(taskMsgDTO.getTaskType()) ? FundCategory.BASICFUND.getCategory() : FundCategory.ADDFUND.getCategory();
            boolean res = saveEmpTask(taskMsgDTO, fundCategory, TaskCategory.REPAY.getCategory(),0);
            logger.info("end funEmpRepay: " + JSON.toJSONString(taskMsgDTO) + "，result：" + (res ? "Success!" : "Fail!"));
        }
    }

    /**
     * 雇员公积金翻牌任务单
     * @param message
     */
    @StreamListener(TaskSink.AF_EMP_COMPANY_CHANGE)
    public void fundEmpFlop(Message<TaskCreateMsgDTO> message) {
        TaskCreateMsgDTO taskMsgDTO = message.getPayload();
        if (TaskSink.FUND_STOP.equals(taskMsgDTO.getTaskType()) || TaskSink.ADD_FUND_STOP.equals(taskMsgDTO.getTaskType())) {
            logger.info("start fundEmpFlop: " + JSON.toJSONString(taskMsgDTO));
            String fundCategory = TaskSink.FUND_STOP.equals(taskMsgDTO.getTaskType()) ? FundCategory.BASICFUND.getCategory() : FundCategory.ADDFUND.getCategory();
            boolean res = saveEmpTask(taskMsgDTO, fundCategory, TaskCategory.FLOP.getCategory(),0);
            logger.info("end fundEmpFlop:  " + JSON.toJSONString(taskMsgDTO) + "，result：" + (res ? "Success!" : "Fail!"));
        }
    }

    /**
     * 雇员公积金服务协议调整任务单
     * @param message
     */
    @StreamListener(TaskSink.AF_EMP_AGREEMENT_ADJUST)
    public void fundEmpAgreementAdjust(Message<TaskCreateMsgDTO> message) {
        TaskCreateMsgDTO taskMsgDTO = message.getPayload();
        if (TaskSink.FUND_NEW.equals(taskMsgDTO.getTaskType())
            || TaskSink.ADD_FUND_NEW.equals(taskMsgDTO.getTaskType())
            || TaskSink.FUND_STOP.equals(taskMsgDTO.getTaskType())
            || TaskSink.ADD_FUND_STOP.equals(taskMsgDTO.getTaskType())) {
            logger.info("start fundEmpAgreementAdjust: " + JSON.toJSONString(taskMsgDTO));
            String fundCategory = (TaskSink.FUND_NEW.equals(taskMsgDTO.getTaskType())|| TaskSink.FUND_STOP.equals(taskMsgDTO.getTaskType())) ? FundCategory.BASICFUND.getCategory() : FundCategory.ADDFUND.getCategory();
            boolean res = saveEmpTask(taskMsgDTO, fundCategory, TaskCategory.ADJUSTSEALED.getCategory(),0);
            logger.info("end fundEmpAgreementAdjust: " + JSON.toJSONString(taskMsgDTO) + "，result：" + (res ? "Success!" : "Fail!"));
        }
    }

    /**
     * 雇员公积金服务协议更正任务单
     * @param message
     * @return
     */
    @StreamListener(TaskSink.AF_EMP_AGREEMENT_UPDATE)
    public void fundEmpAgreementCorrect(Message<TaskCreateMsgDTO> message) {
        TaskCreateMsgDTO taskMsgDTO = message.getPayload();
        if (TaskSink.FUND_NEW.equals(taskMsgDTO.getTaskType())
            || TaskSink.ADD_FUND_NEW.equals(taskMsgDTO.getTaskType())
            || TaskSink.FUND_STOP.equals(taskMsgDTO.getTaskType())
            || TaskSink.ADD_FUND_STOP.equals(taskMsgDTO.getTaskType())) {

            logger.info("start fundEmpAgreementCorrect: " + JSON.toJSONString(taskMsgDTO));
            try {
                Map<String, Object> paramMap = taskMsgDTO.getVariables();
                Integer taskCategory = 0;
                String fundCategory = (TaskSink.FUND_NEW.equals(taskMsgDTO.getTaskType())|| TaskSink.FUND_STOP.equals(taskMsgDTO.getTaskType())) ? FundCategory.BASICFUND.getCategory() : FundCategory.ADDFUND.getCategory();
                //未办理任务单
                if (StringUtils.isBlank(taskMsgDTO.getTaskId())) {
                    logger.info("start fundEmpAgreementCorrect(not handled): " + JSON.toJSONString(taskMsgDTO));
                    if (null != paramMap && paramMap.get("fundType") != null) {
                        taskCategory = Integer.parseInt(paramMap.get("fundType").toString());
                    }
                    boolean res = updateEmpTask(taskMsgDTO,fundCategory,taskCategory);
                    logger.info("end fundEmpAgreementCorrect(not handled):" + JSON.toJSONString(taskMsgDTO) + "，result：" + (res ? "Success!" : "Fail!"));
                } //已办理任务单
                else {
                    logger.info("start fundEmpAgreementCorrect(already handled): " + JSON.toJSONString(taskMsgDTO));
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
                    boolean res = saveEmpTask(taskMsgDTO, fundCategory, taskCategory, 1);
                    logger.info("end fundEmpAgreementCorrect(already handled): " + JSON.toJSONString(taskMsgDTO) + "，result：" + (res ? "Success!" : "Fail!"));
                }
            } catch (Exception e) {
                logger.error("fundEmpAgreementCorrect exception: " + e.getMessage(),e);
            }
            logger.info("end fundEmpAgreementCorrect!");
        }
    }

    /**
     * 公积金财务付款申请回调任务单
     * @param message
     * @return
     */
    @StreamListener(TaskSink.PAY_APPLY_PAY_STATUS_STREAM)
    public void applyFinancePayment(Message<PayApplyPayStatusDTO> message) {
        PayApplyPayStatusDTO taskMsgDTO = message.getPayload();
        logger.info("start applyFinancePayment: " + JSON.toJSONString(taskMsgDTO));
        try{
            if(taskMsgDTO.getBusinessType() == 2){
                boolean res = hfPaymentAccountService.updatePaymentInfo(taskMsgDTO.getBusinessPkId(), taskMsgDTO.getRemark(),taskMsgDTO.getPayStatus());
                logger.info("applyFinancePayment result: " + JSON.toJSONString(taskMsgDTO) + "，result：" + (res ? "Success!" : "Fail!"));
            }
        }
        catch (Exception e){
            logger.error("applyFinancePayment exception: " + e.getMessage(),e);
        }
        logger.info("end applyFinancePayment!");
    }

    /**
     * 客服中心调用更新企业任务单
     * @param message
     * @return
     */
    @StreamListener(TaskSink.AF_COMPANY_SOCIAL_ACCOUNT_ONCE11)
    public void updateComTask(Message<TaskCreateMsgDTO> message) {
        TaskCreateMsgDTO taskMsgDTO = message.getPayload();
        logger.info("start updateComTask: " + JSON.toJSONString(taskMsgDTO));
        //公积金
        try {
            HfComTask ele = hfComTaskService.selectById(taskMsgDTO.getMissionId());
            ele.setTaskId(taskMsgDTO.getTaskId());
            boolean res = hfComTaskService.updateById(ele);
            logger.info("updateComTask result: " + JSON.toJSONString(taskMsgDTO) + "，result：" + (res ? "Success!" : "Fail!"));
        } catch (Exception e) {
            logger.error("updateComTask exception: "+e.getMessage(), e);
        }
        logger.info("end updateComTask!");
    }

    /**
     * 获取当前雇员信息接口
     * @param taskMsgDTO
     * @return
     */
    private AfEmployeeInfoDTO getEmpInfo(TaskCreateMsgDTO taskMsgDTO,Integer taskCategory) {
        AfEmployeeInfoDTO resDto = null;
        try {
            Thread.sleep(500);
            logger.info("fund get employee info start, request:" + JSON.toJSONString(taskMsgDTO));
            AfEmployeeQueryDTO taskRequestDTO = new AfEmployeeQueryDTO();
            taskRequestDTO.setEmpAgreementId(Long.parseLong(taskMsgDTO.getMissionId()));
            if(TaskCategory.REPAY.getCategory().equals(taskCategory)){
                resDto = employeeSocialProxy.getByEmpAgreement(Long.parseLong(taskMsgDTO.getMissionId()));
            }
            else{
                resDto = afEmployeeCompanyProxy.getEmployeeCompany(taskRequestDTO);
            }
            logger.info("fund get employee info end, response:" + JSON.toJSONString(resDto));
        } catch (Exception e) {
            logger.error("fund get employee info exception:" + e.getMessage(),e);
        }
        return resDto;
    }



    /**
     * 保存公积金雇员任务单
     * @param taskMsgDTO
     * @param taskCategory
     * @return
     */
    private boolean saveEmpTask(TaskCreateMsgDTO taskMsgDTO, String fundCategory, Integer taskCategory,Integer isChange) {
        try {
            //调用当前雇员信息获取接口
            AfEmployeeInfoDTO dto = getEmpInfo(taskMsgDTO,taskCategory);
            if (dto != null) {
                //插入数据到雇员任务单表
                return hfEmpTaskService.addEmpTask(taskMsgDTO, fundCategory, taskCategory, isChange, dto);
            }
            else {
                logger.error("error:公积金雇员信息获取失败！");
                return false;
            }
        } catch (Exception e) {
            logger.error("exception:" + e.getMessage(), e);
            return false;
        }
    }


    /**
     * 修改公积金雇员任务单
     * @param taskMsgDTO
     * @param fundCategory
     * @return
     */
    private boolean updateEmpTask(TaskCreateMsgDTO taskMsgDTO, String fundCategory, Integer taskCategory){
        try {
            //调用当前雇员信息获取接口
            AfEmployeeInfoDTO dto = getEmpInfo(taskMsgDTO,taskCategory);
            if (dto != null) {
                //更新任务单表信息
                return hfEmpTaskService.updateEmpTask(taskMsgDTO, fundCategory, dto);
            }
            else {
                logger.error("error:公积金雇员信息获取失败！");
                return false;
            }
        } catch (Exception e) {
            logger.error("exception:" + e.getMessage(), e);
            return false;
        }
    }
}
