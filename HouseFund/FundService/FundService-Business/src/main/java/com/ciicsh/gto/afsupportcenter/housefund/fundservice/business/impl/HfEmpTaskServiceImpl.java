package com.ciicsh.gto.afsupportcenter.housefund.fundservice.business.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ciicsh.gto.afcompanycenter.queryservice.api.dto.employee.AfEmpSocialDTO;
import com.ciicsh.gto.afcompanycenter.queryservice.api.dto.employee.AfEmployeeCompanyDTO;
import com.ciicsh.gto.afcompanycenter.queryservice.api.dto.employee.AfEmployeeInfoDTO;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.HfEmpTaskExportBo;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.business.*;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.dao.HfEmpTaskMapper;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.HfEmpTaskBo;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.*;
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
import java.util.stream.Collectors;

/**
 * <p>
 * 雇员任务单总表 服务实现类
 * </p>
 */
@Service
public class HfEmpTaskServiceImpl extends ServiceImpl<HfEmpTaskMapper, HfEmpTask> implements HfEmpTaskService {

    @Override
    public PageRows<HfEmpTaskExportBo> queryHfEmpTaskInPage(PageInfo pageInfo) {
        return queryHfEmpTaskInPage(pageInfo, null);
    }

    @Override
    public PageRows<HfEmpTaskExportBo> queryHfEmpTaskInPage(PageInfo pageInfo, String exceptTaskCategories) {
        HfEmpTaskBo hfEmpTaskDTO = pageInfo.toJavaObject(HfEmpTaskBo.class);
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
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public boolean saveEmpTaskTc(TaskCreateMsgDTO taskMsgDTO, String fundCategory, Integer taskCategory, Integer isChange,
                                 AfEmployeeInfoDTO dto) {
        boolean result = false;
        try {
            //插入数据到雇员任务单表
            addEmpTask(taskMsgDTO, fundCategory, taskCategory, isChange, dto);

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
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public boolean updateEmpTaskTc(TaskCreateMsgDTO taskMsgDTO, String fundCategory, AfEmployeeInfoDTO dto) {
        //更新旧的雇员任务单
        return updateEmpTask(taskMsgDTO, fundCategory, dto);
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
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public boolean addEmpTask(TaskCreateMsgDTO taskMsgDTO, String fundCategory, Integer taskCategory, Integer isChange,
                                AfEmployeeInfoDTO dto) throws Exception {
        AfEmployeeCompanyDTO companyDto = dto.getEmployeeCompany();

        HfEmpTask hfEmpTask = new HfEmpTask();
        hfEmpTask.setTaskId(taskMsgDTO.getTaskId());
        hfEmpTask.setBusinessInterfaceId(taskMsgDTO.getMissionId());
        if(null != companyDto){
            hfEmpTask.setCompanyId(companyDto.getCompanyId());
            hfEmpTask.setEmployeeId(companyDto.getEmployeeId());
            hfEmpTask.setSubmitterId(companyDto.getCreatedBy());
            hfEmpTask.setSubmitterRemark(companyDto.getRemark());
        }
        hfEmpTask.setSubmitTime(LocalDate.now());
        Map<String, Object> paramMap = taskMsgDTO.getVariables();
        //转出单位(来源地)
        hfEmpTask.setTransferOutUnit(this.getTransUnit(paramMap));
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
        hfEmpTask.setAmount(new BigDecimal(0));
        List<AfEmpSocialDTO> socialList = dto.getEmpSocialList();
        if(null != socialList && socialList.size() > 0){
            this.setEmpTask(hfEmpTask,socialList,fundCategory);
        }

        //公积金类型:1 基本 2 补充
        Integer hfType = fundCategory.equals("DIT00057") ? 1 : 2;
        hfEmpTask.setHfType(hfType);
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
    private boolean updateEmpTask(TaskCreateMsgDTO taskMsgDTO, String fundCategory, AfEmployeeInfoDTO dto) {
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
        hfEmpTask.setTransferOutUnit(this.getTransUnit(paramMap));

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

        if(null != socialList && socialList.size() > 0){
            this.setEmpTask(hfEmpTask,socialList,fundCategory);
        }
        baseMapper.updateById(hfEmpTask);

        return true;
    }


    /**
     * 设置EmpTask的值
     * @param empTask
     * @param socialDTOS
     * @param fundCategory
     */
    private void setEmpTask(HfEmpTask empTask,List<AfEmpSocialDTO> socialDTOS,String fundCategory){
        AfEmpSocialDTO socialDTO = this.getAfEmpSocialByType(socialDTOS,fundCategory);
        if(null != socialDTO){
            empTask.setEmpBase(socialDTO.getPersonalBase());
            //缴费段开始月份YYYYMM
            if(null != socialDTO.getStartDate()){
                empTask.setStartMonth(StringUtil.dateToString(socialDTO.getStartDate(),"yyyyMM"));
            }
            if(null != socialDTO.getEndDate()){
                empTask.setEndMonth(StringUtil.dateToString(socialDTO.getEndDate(),"yyyyMM"));
            }
            if(null != socialDTO.getTotal()){
                empTask.setAmount(socialDTO.getTotal());
            }
        }
    }

    /**
     * 根据公积金类型获取AfEmpSocialDTO
     * @param socialDTOS
     * @param fundCategory
     * @return
     */
    private AfEmpSocialDTO getAfEmpSocialByType(List<AfEmpSocialDTO> socialDTOS,String fundCategory){
        AfEmpSocialDTO socialDTO = null;
        List<AfEmpSocialDTO> fundInfos = socialDTOS
            .stream()
            .filter(x->null != x.getPolicyType() && x.getPolicyType().equals(2) && x.getItemCode().equals(fundCategory))
            .collect(Collectors.toList());
        if(null != fundInfos && fundInfos.size() > 0){
            socialDTO = fundInfos.get(0);
        }
        return socialDTO;
    }


    /**
     * 转出单位(来源地)
     * @param paramMap
     * @return
     */
    private String getTransUnit(Map<String, Object> paramMap){
        String transUnit = "";
        if (null != paramMap.get("source")) {
            String source = paramMap.get("source").toString();
            if ("1".equals(source)) {
                transUnit = "中心";
            }
            else if ("2".equals(source)) {
                transUnit ="原单位";
            }
        }
        return transUnit;
    }
}
