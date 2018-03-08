package com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business.utils;

import com.alibaba.fastjson.JSONObject;
import com.ciicsh.gto.afcompanycenter.commandservice.api.dto.employee.AfEmpSocialUpdateDateDTO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo.SsEmpTaskBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.SsEmpBasePeriod;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.enumeration.ItemCode;
import com.ciicsh.gto.afsupportcenter.util.exception.BusinessException;
import com.ciicsh.gto.afsystemmanagecenter.apiservice.api.dto.item.GetSSPItemsRequestDTO;
import com.ciicsh.gto.afsystemmanagecenter.apiservice.api.dto.item.GetSSPItemsResposeDTO;
import com.ciicsh.gto.afsystemmanagecenter.apiservice.api.dto.item.SSPItemDTO;
import com.ciicsh.gto.commonservice.util.dto.Result;
import com.ciicsh.gto.employeecenter.apiservice.api.dto.EmployeeInfoDTO;
import com.ciicsh.gto.employeecenter.apiservice.api.dto.EmployeeQueryDTO;
import com.ciicsh.gto.sheetservice.api.dto.request.TaskRequestDTO;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class TaskCommonUtils {

    private final static Logger logger = LoggerFactory.getLogger(TaskCommonUtils.class);
    /**
     * 处理工作流结果
     * @param result
     */
    private static void handleWorkflowResult(Result result){
        //int code，接口调用成功=0，错误码=其他值
        //T object，具体返回值  TRUE,FALSE
        //String error，字符串错误码，可选
        //String message，错误消息
        Assert.notNull(result,"任务单操作调用工作流结果为空");
        if(0!=result.getCode())throw new BusinessException(result.getMessage());
    }

    /**
     * 任务单处理调用工作流
     * @param taskId
     * @param commonApiUtils
     * @param assignee
     * @return
     */
    public static void completeTask(String taskId,CommonApiUtils commonApiUtils,String assignee){

        TaskRequestDTO taskRequestDTO = new TaskRequestDTO();
        taskRequestDTO.setTaskId(taskId);
        taskRequestDTO.setAssignee(assignee);
        try {
            Result result =commonApiUtils.completeTask(taskRequestDTO);
            handleWorkflowResult(result);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException("调用工作流异常");
        }
    }

    /**
     * 获得进位方式
     */
    public static List<SSPItemDTO> getRoundTypeFromApi(CommonApiUtils commonApiUtils, GetSSPItemsRequestDTO getSSPItemsRequestDTO) {
        try {
            com.ciicsh.gto.afsystemmanagecenter.apiservice.api.dto.JsonResult<GetSSPItemsResposeDTO> jsonResult = commonApiUtils.getRoundingType(getSSPItemsRequestDTO);
            GetSSPItemsResposeDTO getSSPItemsResposeDTO = jsonResult.getData();
            if(null==getSSPItemsResposeDTO)return null;
            return getSSPItemsResposeDTO.getItems();
        } catch (Exception e) {
            e.printStackTrace();
            //throw new BusinessException("调用进位方式接口异常");
        }
        return null;
    }

    /**
     * 实缴金额 回调
     * @param commonApiUtils
     * @param ssEmpTaskBO
     */
    public static void updateConfirmDate(CommonApiUtils commonApiUtils, SsEmpTaskBO ssEmpTaskBO){
        System.out.println("--------------------------------------------进入调用实缴金额接口");
        //根据不同类型 组装不同参数
        List<AfEmpSocialUpdateDateDTO> paramsList = confirmDateGetParams(ssEmpTaskBO);
        try {
            logger.info("任务单ID{"+ssEmpTaskBO.getEmpTaskId()+"}:调用实缴金额接口start");
            int result =commonApiUtils.updateConfirmDate(paramsList);
             logger.info("实缴金额结果:{"+result+"}");
            logger.info("任务单ID{"+ssEmpTaskBO.getEmpTaskId()+"}:调用实缴金额接口end");
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException("实缴金额回调异常");
        }
    }

    /**
     *
     * @param ssEmpTaskBO
     * @return
     */
    private static List<AfEmpSocialUpdateDateDTO> confirmDateGetParams(SsEmpTaskBO ssEmpTaskBO) {
        return taskCallBackParam(ssEmpTaskBO);
    }
    /**
     * 批退
     * @param ssEmpTaskBO
     * @return
     */
    private static List<AfEmpSocialUpdateDateDTO> taskCallBackParam(SsEmpTaskBO ssEmpTaskBO) {
        List<AfEmpSocialUpdateDateDTO> paramsList = new ArrayList<>();

        if(StringUtils.isBlank(ssEmpTaskBO.getBusinessInterfaceId())){
            throw new BusinessException("messionId 为空.");
        }

        if(ssEmpTaskBO.getListEmpBasePeriod()==null){//说明是逆向调整，没有福利段
            return paramsList;
        }
        //回调前道合同协议，迭代每一个险种,目前的需求福利段只有一条福利段
        ssEmpTaskBO.getListEmpBasePeriod().get(0).getListEmpBaseDetail().forEach(SsEmpBaseDetail->{
                AfEmpSocialUpdateDateDTO afEmpSocialUpdateDateDTO = new AfEmpSocialUpdateDateDTO();
                afEmpSocialUpdateDateDTO.setEmpAgreementId(Long.valueOf(ssEmpTaskBO.getBusinessInterfaceId())); //messionId
                afEmpSocialUpdateDateDTO.setCompanyId(ssEmpTaskBO.getCompanyId());//企业Id

                switch (ssEmpTaskBO.getTaskStatus()) {
                    case 4: //批退
                        afEmpSocialUpdateDateDTO.setCompanyConfirmAmount(new BigDecimal(0));
                        afEmpSocialUpdateDateDTO.setPersonalConfirmAmount(new BigDecimal(0));
                        break;
                    default: //任务办理
                        afEmpSocialUpdateDateDTO.setItemCode( SsEmpBaseDetail.getSsType());//社保险种
                        afEmpSocialUpdateDateDTO.setCompanyConfirmAmount(SsEmpBaseDetail.getComAmount());
                        afEmpSocialUpdateDateDTO.setPersonalConfirmAmount(SsEmpBaseDetail.getEmpAmount());
                        //afEmpSocialUpdateDateDTO.setStartConfirmDate(stringTranserDate(ssEmpTaskBO.getEmpTaskPeriods().get(0).getStartMonth()));
                        afEmpSocialUpdateDateDTO.setStartConfirmDate(stringTranserDate(ssEmpTaskBO.getListEmpBasePeriod().get(0).getStartMonth()));
                        afEmpSocialUpdateDateDTO.setEndConfirmDate(stringTranserDateEndMonth(ssEmpTaskBO.getListEmpBasePeriod().get(0).getEndMonth()));
                        break;
                }
                paramsList.add(afEmpSocialUpdateDateDTO);
            }
        );
        return paramsList;
    }

    /**
     * 获取雇员信息（支持中心调用雇员中心）
     * @param commonApiUtils
     * @param idNum 传入证件号码
     * @param idCardType 传入证件类型
     * @param businessType
     * @return
     */
    public static EmployeeInfoDTO getEmployeeInfo(CommonApiUtils commonApiUtils, String idNum, Integer idCardType, Integer businessType){
        EmployeeQueryDTO var1 = new EmployeeQueryDTO();
        var1.setIdNum(idNum);
        var1.setIdCardType(idCardType);
        var1.setBusinessType(businessType);
        try {
            com.ciicsh.gto.employeecenter.util.JsonResult<EmployeeInfoDTO> result = commonApiUtils.getEmployeeInfo(var1);
            EmployeeInfoDTO employeeInfoDTO =result.getData();
            if (null==employeeInfoDTO)return new EmployeeInfoDTO();
            return employeeInfoDTO;
        } catch (Exception e) {
            e.printStackTrace();
            //throw new BusinessException("雇员信息查询异常");
        }
        return new EmployeeInfoDTO();
    }


    /**
     * 字符串转date
     * @param startMonth
     * @return
     */
    private static Date stringTranserDate(String startMonth) {
        if(StringUtils.isBlank(startMonth)) return null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        try {
            Date date =simpleDateFormat.parse(startMonth+"01");
            return date;
        } catch (ParseException e) {
            throw new BusinessException();
        }
    }
    private static Date stringTranserDateEndMonth(String endMonth) {
        if(StringUtils.isBlank(endMonth)) return null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        try {
            Date date =simpleDateFormat.parse(endMonth+"28");//临时做法，应取最后一天
            return date;
        } catch (ParseException e) {
            throw new BusinessException();
        }
    }
    /**
     *  任务单 完成 实缴金额  回调
     * @param ssEmpTaskBO
     * @return
     */
    private static List<AfEmpSocialUpdateDateDTO> completeTaskCallBackParam(SsEmpTaskBO ssEmpTaskBO) {
        //1新进  2  转入 3  调整 4 补缴 5 转出 6封存 7退账  9 特殊操作  10 集体转入   11 集体转出 12 翻牌
        switch (ssEmpTaskBO.getTaskCategory()){
            case 1:
            case 2:
            case 10:
            case 3:
            case 4:
                break;
            case 5:
            case 11:
                break;
            case 6:
                break;
            case 7:
                break;
        }
        return null;
    }

    /**
     *获得年月日 的字符串
     * @param now
     * @return
     */
    public static StringBuffer getMonthStr(LocalDate now) {
        StringBuffer sb = new StringBuffer();
        if (now.getMonth().getValue() <= 9) {
            return sb.append(now.getYear()).append(0).append(now.getMonth().getValue());
        } else {
            return sb.append(now.getYear()).append(now.getMonth().getValue());
        }
    }
    /**
     * 获得下个月的int 型
     *
     * 在循环年月的时候可以使用
     * @param i
     * @return
     */
    public static int getNextMonthInt(int i) {
        return Integer.parseInt(getNextMonth(i));
    }

    /**
     * 获得参数的下个月 字符串
     * @param date
     * @return
     */
    public static String getNextMonth(Integer date) {
        StringBuffer sb = new StringBuffer();
        String dateStr = date.toString();
        int length = dateStr.length();
        String year = dateStr.substring(0,4);
        String day = dateStr.substring(length - 2,length);
        if (12 == Integer.parseInt(day)) {
            return sb.append(Integer.valueOf(year) + 1).append("01").toString();
        }  else {
            return sb.append(date + 1).toString();
        }
    }

    /**
     * 获得截断月份
     * 参数月的上月
     *
     * @param date
     */
    public static String getLastMonth(Integer date) {
        StringBuffer sb = new StringBuffer();
        String dateStr = date.toString();
        int length = dateStr.length();
        String year = dateStr.substring(0, 4);
        String day = dateStr.substring(length - 2, length);
        if (1 == Integer.parseInt(day)) {
            return sb.append(Integer.valueOf(year) - 1).append(12).toString();
        } else {
            return sb.append(date - 1).toString();
        }
    }

    /**
     * 克隆对象
     * @param object 要克隆的对象
     * @param z 要克隆的类型
     * @return 克隆的对象
     */
    public static <T> T cloneObjet(T object, Class z) {
        return (T) JSONObject.parseObject(JSONObject.toJSON(object).toString(), z);
    }
}
