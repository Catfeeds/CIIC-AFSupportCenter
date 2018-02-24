package com.ciicsh.gto.afsupportcenter.housefund.siteservice.business.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ciicsh.gto.afcompanycenter.queryservice.api.dto.employee.AfEmpSocialDTO;
import com.ciicsh.gto.afcompanycenter.queryservice.api.dto.employee.AfEmployeeCompanyDTO;
import com.ciicsh.gto.afcompanycenter.queryservice.api.dto.employee.AfEmployeeInfoDTO;
import com.ciicsh.gto.afsupportcenter.housefund.siteservice.bo.HfEmpTaskBo;
import com.ciicsh.gto.afsupportcenter.housefund.siteservice.business.HfEmpTaskService;
import com.ciicsh.gto.afsupportcenter.housefund.siteservice.dao.HfEmpTaskMapper;
import com.ciicsh.gto.afsupportcenter.housefund.siteservice.dto.HfEmpTaskDTO;
import com.ciicsh.gto.afsupportcenter.housefund.siteservice.entity.HfEmpTask;
import com.ciicsh.gto.afsupportcenter.util.StringUtil;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageKit;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
import com.ciicsh.gto.sheetservice.api.dto.TaskCreateMsgDTO;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 雇员任务单总表 服务实现类
 * </p>
 */
@Service
public class HfEmpTaskServiceImpl extends ServiceImpl<HfEmpTaskMapper, HfEmpTask> implements HfEmpTaskService {

    @Override
    public PageRows<HfEmpTaskBo> queryHfEmpTaskInPage(PageInfo pageInfo) {
        return queryHfEmpTaskInPage(pageInfo, null);
    }

    @Override
    public PageRows<HfEmpTaskBo> queryHfEmpTaskInPage(PageInfo pageInfo, String exceptTaskCategories) {
        HfEmpTaskDTO hfEmpTaskDTO = pageInfo.toJavaObject(HfEmpTaskDTO.class);
        if (StringUtils.isNotBlank(exceptTaskCategories)) {
            hfEmpTaskDTO.setExceptTaskCategories(exceptTaskCategories);
        }
        return PageKit.doSelectPage(pageInfo, () -> baseMapper.queryHfEmpTask(hfEmpTaskDTO));
    }

    /**
     * 查询任务单信息
     *
     * @param hfEmpTask
     */
    @Override
    public List<HfEmpTask> queryByTaskId(HfEmpTask hfEmpTask) {
        return baseMapper.queryByTaskId(hfEmpTask);
    }

    /**
     * <p>Description: 保存数据到雇员任务单表</p>
     *
     * @param taskMsgDTO   消息队列接受的对象
     * @param taskCategory 任务类型
     * @param isChange     是否更正任务单1 是 0 否
     * @param dto          取得的雇员信息
     * @return
     * @author zhangxj
     * @date 2017-12-28
     */
    @Transactional(
        rollbackFor = {Exception.class}
    )
    @Override
    public boolean saveEmpTaskTc(TaskCreateMsgDTO taskMsgDTO, Integer taskCategory, Integer isChange,
                                 AfEmployeeInfoDTO dto) {
        boolean result = false;
        try {
            //插入数据到雇员任务单表
            insertTaskTb(taskMsgDTO, taskCategory, isChange, dto);

            //更新旧的雇员任务单
//            updateEmpTaskTb(taskMsgDTO, dto);

            result = true;
        } catch (Exception e) {
            result = false;
            throw new RuntimeException("保存到雇员任务单表处理异常");
        }
        return result;
    }

    /**
     * 更新旧的雇员任务单
     *
     * @param taskMsgDTO 消息队列接受的对象
     * @param dto        取得的雇员信息
     * @return
     * @author zhangxj
     * @date 2017-12-28
     */
    @Transactional(
        rollbackFor = {Exception.class}
    )
    @Override
    public boolean updateEmpTaskTc(TaskCreateMsgDTO taskMsgDTO,
                                   AfEmployeeInfoDTO dto) {
        //更新旧的雇员任务单
        return updateEmpTaskTb(taskMsgDTO, dto);
    }

    /**
     * 保存数据到雇员任务单表
     *
     * @param taskMsgDTO
     * @param taskCategory
     * @param isChange
     * @param dto
     * @return
     * @throws Exception
     */
    @Transactional(
        rollbackFor = {Exception.class}
    )
    @Override
    public boolean insertTaskTb(TaskCreateMsgDTO taskMsgDTO, Integer taskCategory, Integer isChange,
                                AfEmployeeInfoDTO dto) throws Exception {
        AfEmployeeCompanyDTO companyDto = dto.getEmployeeCompany();

        HfEmpTask hfEmpTask = new HfEmpTask();
        hfEmpTask.setTaskId(taskMsgDTO.getTaskId());
        hfEmpTask.setCompanyId(companyDto.getCompanyId());
        hfEmpTask.setEmployeeId(companyDto.getEmployeeId());
        hfEmpTask.setBusinessInterfaceId(taskMsgDTO.getMissionId());
        hfEmpTask.setSubmitterId(companyDto.getCreatedBy());
        hfEmpTask.setSubmitterRemark(companyDto.getRemark());
        hfEmpTask.setSubmitTime(LocalDate.now());

        Map<String, Object> paramMap = taskMsgDTO.getVariables();
        //转出单位(来源地)
        if (paramMap.get("source") != null) {
            String sSource = paramMap.get("source").toString();
            if ("1".equals(sSource)) {
                hfEmpTask.setTransferOutUnit("中心");
            } else if ("2".equals(sSource)) {
                hfEmpTask.setTransferOutUnit("原单位");
            }
        }
        //任务类型
        hfEmpTask.setTaskCategory(taskCategory);
        //是否更正 1 是 0 否
        hfEmpTask.setIsChange(isChange);
        hfEmpTask.setTaskFormContent(JSON.toJSONString(dto));

        //福利办理方
        hfEmpTask.setWelfareUnit(companyDto.getFundUnit());

        //前道传递的政策明细ID,用它调用系统中心获取进位方式
        if (dto.getNowAgreement() != null && dto.getNowAgreement().getFundSocialRuleId() != null) {
            hfEmpTask.setPolicyDetailId(dto.getNowAgreement().getFundSocialRuleId().intValue());
        }
        //TODO 表中加字段
//        hfEmpTask.setProcessId(taskMsgDTO.getProcessId());
        //办理状态：1、未处理 2 、处理中(已办)  3 已完成(已做) 4、批退 5、不需处理
        hfEmpTask.setTaskStatus(1);
        //入职日期
        if (companyDto.getInDate() != null) {
            hfEmpTask.setInDate(LocalDateTime.ofInstant(companyDto.getInDate().toInstant(), ZoneId.systemDefault()));
        }
        hfEmpTask.setActive(true);
        hfEmpTask.setModifiedBy(companyDto.getCreatedBy());
        hfEmpTask.setModifiedTime(LocalDateTime.now());
        hfEmpTask.setCreatedBy(companyDto.getCreatedBy());
        hfEmpTask.setCreatedTime(LocalDateTime.now());

        List<AfEmpSocialDTO> socialList = dto.getEmpSocialList();
        //缴费段开始月份YYYYMM
        for (AfEmpSocialDTO socialDto : socialList) {
            if (socialDto.getPolicyName() != null && socialDto.getPolicyName().contains("公积金")) {
                hfEmpTask.setEmpBase(socialDto.getPersonalBase());
                if (socialDto.getStartDate() != null) {
                    hfEmpTask.setStartMonth(StringUtil.dateToString(socialDto
                            .getStartDate(),
                        "yyyyMM"));
                }
                if (socialDto.getEndDate() != null) {
                    hfEmpTask.setEndMonth(StringUtil.dateToString(socialDto.getEndDate(),
                        "yyyyMM"));
                }
                break;
            }
        }
        hfEmpTask.setAmount(new BigDecimal(0));
        for (AfEmpSocialDTO socialDto : socialList) {
            if (socialDto.getPolicyName() != null) {
                if (taskMsgDTO.getTaskType().startsWith("fund") && socialDto.getPolicyName().contains("基本公积金")
                    || (taskMsgDTO.getTaskType().startsWith("add_fund") && socialDto.getPolicyName().contains
                    ("补充公积金"))) {
                    hfEmpTask.setAmount(socialDto.getTotal());
                    break;
                }
            }
        }
        //公积金类型:1 基本 2 补充
        if (taskMsgDTO.getTaskType().startsWith("fund")) {
            hfEmpTask.setHfType(1);
        } else if (taskMsgDTO.getTaskType().startsWith("add_fund")) {
            hfEmpTask.setHfType(2);
        }

        baseMapper.insertHfEmpTask(hfEmpTask);

        return true;
    }

    /**
     * 更新旧的雇员任务单
     *
     * @param taskMsgDTO
     * @param dto
     * @return
     */
    private boolean updateEmpTaskTb(TaskCreateMsgDTO taskMsgDTO,
                                    AfEmployeeInfoDTO dto) {
        Map<String, Object> paramMap = taskMsgDTO.getVariables();

        AfEmployeeCompanyDTO companyDto = dto.getEmployeeCompany();

        HfEmpTask hfEmpTask = new HfEmpTask();
        hfEmpTask.setTaskId(paramMap.get("oldEmpAgreementId").toString());
        hfEmpTask.setCompanyId(companyDto.getCompanyId());
        hfEmpTask.setEmployeeId(companyDto.getEmployeeId());
//        hfEmpTask.setBusinessInterfaceId(taskMsgDTO.getMissionId());
        hfEmpTask.setSubmitterId(companyDto.getCreatedBy());
        hfEmpTask.setSubmitterRemark(companyDto.getRemark());

        hfEmpTask.setTaskFormContent(JSON.toJSONString(dto));

        //转出单位(来源地)
        if (paramMap.get("source") != null) {
            String sSource = paramMap.get("source").toString();
            if ("1".equals(sSource)) {
                hfEmpTask.setTransferOutUnit("中心");
            } else if ("2".equals(sSource)) {
                hfEmpTask.setTransferOutUnit("原单位");
            }
        }
        //福利办理方
        hfEmpTask.setWelfareUnit(companyDto.getFundUnit());

        //前道传递的政策明细ID,用它调用系统中心获取进位方式
        if (dto.getNowAgreement() != null && dto.getNowAgreement().getFundSocialRuleId() != null) {
            hfEmpTask.setPolicyDetailId(dto.getNowAgreement().getFundSocialRuleId().intValue());
        }
        //TODO 表中加字段
//        hfEmpTask.setProcessId(taskMsgDTO.getProcessId());
        //入职日期
        if (companyDto.getInDate() != null) {
            hfEmpTask.setInDate(LocalDateTime.ofInstant(companyDto.getInDate().toInstant(), ZoneId.systemDefault()));
        }

        hfEmpTask.setModifiedBy(companyDto.getCreatedBy());
        hfEmpTask.setModifiedTime(LocalDateTime.now());

        List<AfEmpSocialDTO> socialList = dto.getEmpSocialList();
        //缴费段开始月份YYYYMM
        for (AfEmpSocialDTO socialDto : socialList) {
            if (socialDto.getPolicyName() != null && socialDto.getPolicyName().contains("公积金")) {
                hfEmpTask.setEmpBase(socialDto.getPersonalBase());
                if (socialDto.getStartDate() != null) {
                    hfEmpTask.setStartMonth(StringUtil.dateToString(socialDto
                            .getStartDate(),
                        "yyyyMM"));
                }
                if (socialDto.getEndDate() != null) {
                    hfEmpTask.setEndMonth(StringUtil.dateToString(socialDto.getEndDate(),
                        "yyyyMM"));
                }
                break;
            }
        }
        hfEmpTask.setAmount(new BigDecimal(0));
        for (AfEmpSocialDTO socialDto : socialList) {
            if (socialDto.getPolicyName() != null) {
                if (taskMsgDTO.getTaskType().startsWith("fund") && socialDto.getPolicyName().contains("基本公积金")
                    || (taskMsgDTO.getTaskType().startsWith("add_fund") && socialDto.getPolicyName().contains
                    ("补充公积金"))) {
                    hfEmpTask.setAmount(socialDto.getTotal());
                    break;
                }
            }
        }
        baseMapper.updateById(hfEmpTask);

        return true;
    }
}
