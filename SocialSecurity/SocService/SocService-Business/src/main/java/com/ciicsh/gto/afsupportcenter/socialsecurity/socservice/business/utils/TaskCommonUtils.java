package com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business.utils;

import com.alibaba.fastjson.JSONObject;
import com.ciicsh.gto.afcompanycenter.commandservice.api.dto.employee.AfEmpSocialUpdateDateDTO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo.SsEmpTaskBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.SsEmpBaseDetail;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.SsEmpBasePeriod;
import com.ciicsh.gto.afsupportcenter.util.DateUtil;
import com.ciicsh.gto.afsupportcenter.util.constant.SocialSecurityConst;
import com.ciicsh.gto.afsupportcenter.util.exception.BusinessException;
import com.ciicsh.gto.afsupportcenter.util.kit.DateKit;
import com.ciicsh.gto.afsystemmanagecenter.apiservice.api.dto.item.GetSSPItemsRequestDTO;
import com.ciicsh.gto.afsystemmanagecenter.apiservice.api.dto.item.GetSSPItemsResposeDTO;
import com.ciicsh.gto.afsystemmanagecenter.apiservice.api.dto.item.SSPItemDTO;
import com.ciicsh.gto.employeecenter.apiservice.api.dto.*;
import com.ciicsh.gto.sheetservice.api.dto.Result;
import com.ciicsh.gto.sheetservice.api.dto.request.TaskRequestDTO;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TaskCommonUtils {

    private final static Logger logger = LoggerFactory.getLogger(TaskCommonUtils.class);

    /**
     * 处理工作流结果
     *
     * @param result
     */
    private static void handleWorkflowResult(Result result) {
        //int code，接口调用成功=0，错误码=其他值
        //T object，具体返回值  TRUE,FALSE
        //String error，字符串错误码，可选
        //String message，错误消息
        Assert.notNull(result, "任务单操作调用工作流结果为空");
        if (0 != result.getCode()) {
            throw new BusinessException(result.getMessage());
        }
    }

    /**
     * 任务单处理调用工作流
     *
     * @param taskId
     * @param commonApiUtils
     * @param assignee
     * @return
     */
    public static void completeTask(String taskId, CommonApiUtils commonApiUtils, String assignee) {

        TaskRequestDTO taskRequestDTO = new TaskRequestDTO();
        taskRequestDTO.setTaskId(taskId);
        taskRequestDTO.setAssignee(assignee);
        try {
            Result result = commonApiUtils.completeTask(taskRequestDTO);
            handleWorkflowResult(result);
        } catch (Exception e) {
//            e.printStackTrace();

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
            if (null == getSSPItemsResposeDTO) {
                return null;
            }
            return getSSPItemsResposeDTO.getItems();
        } catch (Exception e) {
//            e.printStackTrace();
            //throw new BusinessException("调用进位方式接口异常");
        }
        return null;
    }

    /**
     * 实缴金额 回调
     *
     * @param commonApiUtils
     * @param ssEmpTaskBO
     */
    public static void updateConfirmDate(CommonApiUtils commonApiUtils, SsEmpTaskBO ssEmpTaskBO) {
        System.out.println("--------------------------------------------进入调用实缴金额接口");
        //根据不同类型 组装不同参数
        List<AfEmpSocialUpdateDateDTO> paramsList = confirmDateGetParams(ssEmpTaskBO);
        try {
            logger.debug("任务单ID{" + ssEmpTaskBO.getEmpTaskId() + "}:调用实缴金额接口start");
            int result = commonApiUtils.updateConfirmDate(paramsList);
            logger.debug("实缴金额结果:{" + result + "}");
            logger.debug("任务单ID{" + ssEmpTaskBO.getEmpTaskId() + "}:调用实缴金额接口end");
        } catch (Exception e) {
//            e.printStackTrace();
            throw new BusinessException("实缴金额回调异常");
        }
    }

    /**
     * @param ssEmpTaskBO
     * @return
     */
    private static List<AfEmpSocialUpdateDateDTO> confirmDateGetParams(SsEmpTaskBO ssEmpTaskBO) {
        return taskCallBackParam(ssEmpTaskBO);
    }

    /**
     * 批退
     *
     * @param ssEmpTaskBO
     * @return
     */
    private static List<AfEmpSocialUpdateDateDTO> taskCallBackParam(SsEmpTaskBO ssEmpTaskBO) {
        List<AfEmpSocialUpdateDateDTO> paramsList = new ArrayList<>();

        if (StringUtils.isBlank(ssEmpTaskBO.getBusinessInterfaceId())) {
            throw new BusinessException("missionId 为空.");
        }

        if (ssEmpTaskBO.getListEmpBasePeriod() == null) {//说明是逆向调整，没有福利段
            return paramsList;
        }
        AfEmpSocialUpdateDateDTO afEmpSocialUpdateDateDTO = null;

        String startMonth = "999912";
        String endMonth = "190001";
        String dataStartMonth = null;
        String dataEndMonth = null;

        if (ssEmpTaskBO.getListEmpBasePeriod() != null) {
            for (SsEmpBasePeriod ssEmpBasePeriod : ssEmpTaskBO.getListEmpBasePeriod()) {

                dataStartMonth = ssEmpBasePeriod.getStartMonth();
                dataEndMonth = ssEmpBasePeriod.getEndMonth();
                // 如果任务单费用段起始年月不为空
                if (StringUtils.isNotEmpty(dataStartMonth)) {
                    // 如果参照起始年月大于任务单费用段起始年月，则将参照起始年月更新为任务单费用段起始年月
                    // 目的是为了取得所有任务单费用段中的最早起始年月
                    if (Integer.valueOf(startMonth) > Integer.valueOf(dataStartMonth)) {
                        startMonth = dataStartMonth;
                    }
                } else {
                    // 如果任务单费用段起始年月为空，则暂定为一个理论最小值
                    startMonth = "190001";
                }

                // 如果任务单费用段截止年月不为空
                if (StringUtils.isNotEmpty(dataEndMonth)) {
                    // 如果参照截止年月小于任务单费用段截止年月，则将参照截止年月更新为任务单费用段截止年月
                    // 目的是为了取得所有任务单费用段中的最晚截止年月
                    if (Integer.valueOf(endMonth) < Integer.valueOf(dataEndMonth)) {
                        endMonth = dataEndMonth;
                    }
                } else {
                    // 如果任务单费用段截止年月为空，则暂定为一个理论最大值
                    endMonth = "999912";
                }
            }

            if (!"190001".equals(startMonth)) {
                dataStartMonth = startMonth;
            } else {
                dataStartMonth = null;
            }
            if (!"999912".equals(endMonth)) {
                dataEndMonth = endMonth;
            } else {
                dataEndMonth = null;
            }
        }

        DateTimeFormatter yyyyMMddFormatter = DateTimeFormatter.ofPattern("uuuuMMdd");
        DateKit.setDatePattern("yyyyMMdd");

        //回调前道合同协议，迭代每一个险种,目前的需求
        // 福利段只有一条福利段，因此get(0)
        for (SsEmpBaseDetail ssEmpBaseDetail : ssEmpTaskBO.getListEmpBasePeriod().get(0).getListEmpBaseDetail()) {
            switch (ssEmpTaskBO.getTaskStatus()) {
                case 4: //批退
                    afEmpSocialUpdateDateDTO = new AfEmpSocialUpdateDateDTO();
                    afEmpSocialUpdateDateDTO.setEmpAgreementId(Long.valueOf(ssEmpTaskBO.getBusinessInterfaceId())); //missionId
                    afEmpSocialUpdateDateDTO.setCompanyId(ssEmpTaskBO.getCompanyId());//企业Id
                    afEmpSocialUpdateDateDTO.setStartConfirmDate(DateKit.toDate(dataStartMonth + "01"));

                    if (StringUtils.isNotEmpty(dataStartMonth)) {
                        LocalDate endMonthDate = LocalDate.parse(dataStartMonth + "01", yyyyMMddFormatter);
                        // 关闭日期为开始月的前一个月的最后一天
                        afEmpSocialUpdateDateDTO.setEndConfirmDate(DateKit.toDate(endMonthDate.minusDays(1).format(yyyyMMddFormatter)));
                    } else {
                        throw new BusinessException("停办不能批退");
                    }

                    afEmpSocialUpdateDateDTO.setCompanyConfirmAmount(new BigDecimal(0));
                    afEmpSocialUpdateDateDTO.setPersonalConfirmAmount(new BigDecimal(0));
                    paramsList.add(afEmpSocialUpdateDateDTO);
                    break;
                default: //任务办理
                    boolean isNewChange = false;
                    // 判断是否新增更正
                    if (ssEmpTaskBO.getIsChange() == 1 && (
                        ssEmpTaskBO.getTaskCategory().equals(Integer.parseInt(SocialSecurityConst.TASK_TYPE_1))
                            || ssEmpTaskBO.getTaskCategory().equals(Integer.parseInt(SocialSecurityConst.TASK_TYPE_2))
                            || ssEmpTaskBO.getTaskCategory().equals(Integer.parseInt(SocialSecurityConst.TASK_TYPE_12))
                            || ssEmpTaskBO.getTaskCategory().equals(Integer.parseInt(SocialSecurityConst.TASK_TYPE_13))
                    )) {
                        isNewChange = true;
                        ssEmpTaskBO.setOldAgreementId(ssEmpTaskBO.getOldSsEmpTask().getBusinessInterfaceId());
                    }

                    // 以下为调整前的费用段处理：
                    // 如果oldAgreementId存在时，则要回调接口，通知前道关闭费用段
                    // 调整类别任务单，只发一个消息（新旧雇员协议在同一任务单中记录），oldAgreementId需记录，任务单回调时，同时需回调新旧雇员协议；
                    // 非调整类别的SOCIAL_NEW,FUND_NEW,ADDED_FUND_NEW类型的任务单，social_startAndStop为true，oldAgreementId一概不记录，任务单回调时，不回调旧雇员协议，仅回调新雇员协议；
                    // 不为true，则oldAgreementId需记录，任务单回调时，同时需回调新旧雇员协议；
                    // 当SOCIAL_STOP,FUND_STOP,ADDED_FUND_STOP类型的任务单，oldAgreementId需记录，任务单回调时，根据情况回调旧雇员协议（仅非0转0）；
                    if (StringUtils.isNotEmpty(ssEmpTaskBO.getOldAgreementId())) {
                        // 如果新进或转入，且险种数量改变，则不对旧协议进行关闭处理
//                        if ((ssEmpTaskBO.getTaskCategory().equals(Integer.parseInt(SocialSecurityConst.TASK_TYPE_1))
//                            || ssEmpTaskBO.getTaskCategory().equals(Integer.parseInt(SocialSecurityConst.TASK_TYPE_2)))
//                            && ssEmpTaskBO.isSocCountChange()) {
////                            continue;
//                        } else {
                            afEmpSocialUpdateDateDTO = new AfEmpSocialUpdateDateDTO();
                            afEmpSocialUpdateDateDTO.setCompanyId(ssEmpTaskBO.getCompanyId());//企业Id
                            afEmpSocialUpdateDateDTO.setItemCode(ssEmpBaseDetail.getSsType());//社保险种
//                        afEmpSocialUpdateDateDTO.setCompanyConfirmAmount(ssEmpBaseDetail.getComAmount());
//                        afEmpSocialUpdateDateDTO.setPersonalConfirmAmount(ssEmpBaseDetail.getEmpAmount());

                            // 如果是更正新增
                            if (isNewChange) {
                                LocalDate startMonthDate = LocalDate.parse(ssEmpTaskBO.getOldSsEmpTask().getStartMonth() + "01", yyyyMMddFormatter);
                                // 关闭日期为起缴月的前一个月的最后一天
                                afEmpSocialUpdateDateDTO.setEndConfirmDate(DateKit.toDate(startMonthDate.minusDays(1).format(yyyyMMddFormatter)));
                            } else if (ssEmpTaskBO.getTaskCategory().equals(Integer.parseInt(SocialSecurityConst.TASK_TYPE_3))) {
                                LocalDate startMonthDate = LocalDate.parse(dataStartMonth + "01", yyyyMMddFormatter);
                                // 关闭日期为起缴月的前一个月的最后一天
                                afEmpSocialUpdateDateDTO.setEndConfirmDate(DateKit.toDate(startMonthDate.minusDays(1).format(yyyyMMddFormatter)));
                            } else if (StringUtils.isNotEmpty(ssEmpTaskBO.getHandleMonth())) {
                                LocalDate handleMonthDate = LocalDate.parse(ssEmpTaskBO.getHandleMonth() + "01", yyyyMMddFormatter);
                                // 关闭日期为汇缴月的前一个月的最后一天
                                afEmpSocialUpdateDateDTO.setEndConfirmDate(DateKit.toDate(handleMonthDate.minusDays(1).format(yyyyMMddFormatter)));
                            }

                            afEmpSocialUpdateDateDTO.setEmpAgreementId(Long.valueOf(ssEmpTaskBO.getOldAgreementId()));
                            paramsList.add(afEmpSocialUpdateDateDTO);
//                        }

                        // 如果oldAgreementId存在，且是转出或封存时，说明是调整非0转0
//                        if (isNewChange
//                                || (
//                                    (
//                                        ((ssEmpTaskBO.getTaskCategory().equals(Integer.parseInt(SocialSecurityConst.TASK_TYPE_5))
//                                            || ssEmpTaskBO.getTaskCategory().equals(Integer.parseInt(SocialSecurityConst.TASK_TYPE_6)))
//                                            && !ssEmpTaskBO.isSocCountChange())  // 转出或封存，且险种数量未变时（非险种数量改变时的转出或封存，此时通常为0转0或上海转外地）
//                                        || ((ssEmpTaskBO.getTaskCategory().equals(Integer.parseInt(SocialSecurityConst.TASK_TYPE_1))
//                                            || ssEmpTaskBO.getTaskCategory().equals(Integer.parseInt(SocialSecurityConst.TASK_TYPE_2)))
//                                            && ssEmpTaskBO.isSocCountChange())   // 新进或转入，且险种数量改变时（险种数量改变时的新进或转入）
//                                        || ssEmpTaskBO.getTaskCategory().equals(Integer.parseInt(SocialSecurityConst.TASK_TYPE_3))
//                                    )
//                                     && SocialSecurityConst.SHANGHAI_CITY_CODE.equals(ssEmpTaskBO.getNewCityCode()) // 新城市编码为上海时，排除转外地任务单
//                                )
                    if (SocialSecurityConst.SHANGHAI_CITY_CODE.equals(ssEmpTaskBO.getNewCityCode()) // 新城市编码为上海时，排除转外地任务单
                        && (ssEmpTaskBO.getSocialStartAndStop() == null || !ssEmpTaskBO.getSocialStartAndStop()) // 只出不进（或只进不出）时（通常有调整基数或比例、非0转0、0转非0），排除一出一进的任务单(调整模板)
                            ) {
                            afEmpSocialUpdateDateDTO = new AfEmpSocialUpdateDateDTO();
                            afEmpSocialUpdateDateDTO.setEmpAgreementId(Long.valueOf(ssEmpTaskBO.getBusinessInterfaceId())); //missionId
                            afEmpSocialUpdateDateDTO.setCompanyId(ssEmpTaskBO.getCompanyId());//企业Id
                            afEmpSocialUpdateDateDTO.setItemCode(ssEmpBaseDetail.getSsType());//社保险种

                            if ( ssEmpTaskBO.getTaskCategory().equals(Integer.parseInt(SocialSecurityConst.TASK_TYPE_5))
                                || ssEmpTaskBO.getTaskCategory().equals(Integer.parseInt(SocialSecurityConst.TASK_TYPE_6))) {
                                // 此时需要回调一个只有开始确认时间的，金额为0的费用段
                                afEmpSocialUpdateDateDTO.setCompanyConfirmAmount(BigDecimal.ZERO);
                                afEmpSocialUpdateDateDTO.setPersonalConfirmAmount(BigDecimal.ZERO);

                                if (StringUtils.isNotEmpty(ssEmpTaskBO.getHandleMonth())) {
                                    afEmpSocialUpdateDateDTO.setStartConfirmDate(DateKit.toDate(ssEmpTaskBO.getHandleMonth() + "01"));
                                }
                            } else {
                                afEmpSocialUpdateDateDTO.setCompanyConfirmAmount(ssEmpBaseDetail.getComAmount());
                                afEmpSocialUpdateDateDTO.setPersonalConfirmAmount(ssEmpBaseDetail.getEmpAmount());

//                                if (ssEmpTaskBO.getTaskCategory().equals(Integer.parseInt(SocialSecurityConst.TASK_TYPE_3))
//                                    || isNewChange) {
                                afEmpSocialUpdateDateDTO.setStartConfirmDate(stringTranserDate(dataStartMonth));
//                                } else if (StringUtils.isNotEmpty(ssEmpTaskBO.getHandleMonth())) {
//                                    afEmpSocialUpdateDateDTO.setStartConfirmDate(DateKit.toDate(ssEmpTaskBO.getHandleMonth() + "01"));
//                                }
                            }
                            afEmpSocialUpdateDateDTO.setEmpAgreementId(Long.valueOf(ssEmpTaskBO.getBusinessInterfaceId()));
                            paramsList.add(afEmpSocialUpdateDateDTO);
                        }
                    } else {
                        // 通常的处理方式
                        afEmpSocialUpdateDateDTO = new AfEmpSocialUpdateDateDTO();
                        afEmpSocialUpdateDateDTO.setEmpAgreementId(Long.valueOf(ssEmpTaskBO.getBusinessInterfaceId())); //missionId
                        afEmpSocialUpdateDateDTO.setCompanyId(ssEmpTaskBO.getCompanyId());//企业Id
                        afEmpSocialUpdateDateDTO.setItemCode(ssEmpBaseDetail.getSsType());//社保险种
                        afEmpSocialUpdateDateDTO.setCompanyConfirmAmount(ssEmpBaseDetail.getComAmount());
                        afEmpSocialUpdateDateDTO.setPersonalConfirmAmount(ssEmpBaseDetail.getEmpAmount());
                        //反馈前道任务单的开始年月和结束年月

                        afEmpSocialUpdateDateDTO.setStartConfirmDate(stringTranserDate(dataStartMonth));
                        if (StringUtils.isNotEmpty(dataEndMonth)) {
                            LocalDate endMonthDate = LocalDate.parse(dataEndMonth + "01", yyyyMMddFormatter);
                            afEmpSocialUpdateDateDTO.setEndConfirmDate(DateKit.toDate(endMonthDate.plusMonths(1).minusDays(1).format(yyyyMMddFormatter)));
                        }
                        paramsList.add(afEmpSocialUpdateDateDTO);
                    }
                    break;
            }
        }

        return paramsList;
    }

    /**
     * 获取雇员信息（支持中心调用雇员中心）
     *
     * @param commonApiUtils
     * @param idNum          传入证件号码
     * @param idCardType     传入证件类型
     * @param businessType
     * @return
     */
    public static EmployeeInfoDTO getEmployeeInfo(CommonApiUtils commonApiUtils, String idNum, Integer idCardType, Integer businessType) {
        EmployeeQueryDTO var1 = new EmployeeQueryDTO();
        var1.setIdNum(idNum);
        var1.setIdCardType(idCardType);
        var1.setBusinessType(businessType);
        try {
            JsonResult<EmployeeInfoDTO> result = commonApiUtils.getEmployeeInfo(var1);
            EmployeeInfoDTO employeeInfoDTO = result.getData();
            if (null == employeeInfoDTO) {
                return new EmployeeInfoDTO();
            }
            return employeeInfoDTO;
        } catch (Exception e) {
//            e.printStackTrace();
            //throw new BusinessException("雇员信息查询异常");
        }
        return new EmployeeInfoDTO();
    }

    /**
     * 获取雇员信息（支持中心调用雇员中心）
     *
     * @param commonApiUtils
     * @param idNum          传入证件号码
     * @param idCardType     传入证件类型
     * @return
     */
    public static AfEmployeeDTO getAfEmployeeInfo(CommonApiUtils commonApiUtils, String idNum, Integer idCardType) {
        EmployeeIdQueryDTO employeeIdQueryDTO = new EmployeeIdQueryDTO();
        employeeIdQueryDTO.setIdCardType(idCardType);
        employeeIdQueryDTO.setIdNum(idNum);
        try {
            JsonResult<AfEmployeeDTO> result = commonApiUtils.getAfEmployeeInfo(employeeIdQueryDTO);
            AfEmployeeDTO afEmployeeDTO = result.getData();
            if (null == afEmployeeDTO) {
                return new AfEmployeeDTO();
            }
            return afEmployeeDTO;
        } catch (Exception e) {
//            e.printStackTrace();
            //throw new BusinessException("雇员信息查询异常");
        }
        return new AfEmployeeDTO();
    }

    /**
     * 字符串转date
     *
     * @param startMonth
     * @return
     */
    private static Date stringTranserDate(String startMonth) {
        if (StringUtils.isBlank(startMonth)) {
            return null;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        try {
            Date date = simpleDateFormat.parse(startMonth + "01");
            return date;
        } catch (ParseException e) {
            throw new BusinessException();
        }
    }

    private static Date stringTranserDateEndMonth(String endMonth) {
        if (StringUtils.isBlank(endMonth)) {
            return null;
        }
        LocalDate d = LocalDate.parse(endMonth + "28", DateTimeFormatter.ofPattern("yyyyMMdd")).with(TemporalAdjusters.lastDayOfMonth());
        return DateUtil.localDateToDate(d);
    }

    /**
     * 任务单 完成 实缴金额  回调
     *
     * @param ssEmpTaskBO
     * @return
     */
    private static List<AfEmpSocialUpdateDateDTO> completeTaskCallBackParam(SsEmpTaskBO ssEmpTaskBO) {
        //1新进  2  转入 3  调整 4 补缴 5 转出 6封存 7退账  9 特殊操作  10 集体转入   11 集体转出 12 翻牌
        switch (ssEmpTaskBO.getTaskCategory()) {
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
     * 获得年月日 的字符串
     *
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
     * <p>
     * 在循环年月的时候可以使用
     *
     * @param i
     * @return
     */
    public static int getNextMonthInt(int i) {
        return Integer.parseInt(getNextMonth(i));
    }

    /**
     * 获得参数的下个月 字符串
     *
     * @param date
     * @return
     */
    public static String getNextMonth(Integer date) {
        StringBuffer sb = new StringBuffer();
        String dateStr = date.toString();
        int length = dateStr.length();
        String year = dateStr.substring(0, 4);
        String day = dateStr.substring(length - 2, length);
        if (12 == Integer.parseInt(day)) {
            return sb.append(Integer.valueOf(year) + 1).append("01").toString();
        } else {
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
     *
     * @param object 要克隆的对象
     * @param z      要克隆的类型
     * @return 克隆的对象
     */
    public static <T> T cloneObjet(T object, Class z) {
        return (T) JSONObject.parseObject(JSONObject.toJSON(object).toString(), z);
    }
}
