package com.ciicsh.gto.afsupportcenter.housefund.fundservice.business.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.HfEmpComBO;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.customer.AccountInfoBO;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.customer.ComAccountExtBo;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.customer.ComAccountParamExtBo;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.transfer.EmpTaskTransferBo;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.transfer.HfEmpTaskHandleVo;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.business.HfEmpTaskTransferService;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.dao.HfComAccountMapper;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.dao.HfEmpArchiveMapper;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.dao.HfEmpTaskMapper;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.HfEmpArchive;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.HfEmpTask;
import com.ciicsh.gto.afsupportcenter.util.interceptor.authenticate.UserContext;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageKit;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResult;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResultKit;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

/**
 * <p>
 * 雇员任务单总表 服务实现类
 * </p>
 */
@Service
public class HfEmpTaskTransferServiceImpl extends ServiceImpl<HfEmpTaskMapper, HfEmpTask> implements HfEmpTaskTransferService {
    @Autowired
    HfComAccountMapper hfComAccountMapper;
    @Autowired
    HfEmpArchiveMapper hfEmpArchiveMapper;

    @Override
    public PageRows<EmpTaskTransferBo> queryEmpTaskTransferPage(PageInfo pageInfo) {
        EmpTaskTransferBo empTaskTransferBo=pageInfo.toJavaObject(EmpTaskTransferBo.class);
        return PageKit.doSelectPage(pageInfo, () -> baseMapper.queryEmpTaskTransfer(empTaskTransferBo));
    }

    public List<EmpTaskTransferBo> queryEmpTaskTransfer(EmpTaskTransferBo empTaskTransferBo) {
        return baseMapper.queryEmpTaskTransfer(empTaskTransferBo);
    }

    @Override
    public PageRows<EmpTaskTransferBo> queryEmpTaskTransferNewTaskPage(PageInfo pageInfo) {
        EmpTaskTransferBo empTaskTransferBo=pageInfo.toJavaObject(EmpTaskTransferBo.class);
        return PageKit.doSelectPage(pageInfo, () -> baseMapper.queryEmpTaskTransferNewTask(empTaskTransferBo));
    }
    @Override
    public HfEmpTaskHandleVo queryComEmpTransferForm(String employeeId, String companyId, Long empTaskId){
        HfEmpTaskHandleVo hfEmpTaskHandleBo=new HfEmpTaskHandleVo();
        //企业账户信息
        ComAccountParamExtBo comAccountParamExtBo=new ComAccountParamExtBo();
        comAccountParamExtBo.setCompanyId(companyId);
        comAccountParamExtBo.setHfType(1);//基本
        List<ComAccountExtBo> listAccount1 = hfComAccountMapper.getHfComAccountList(comAccountParamExtBo);
        comAccountParamExtBo.setHfType(2);//补充
        List<ComAccountExtBo> listAccount2 = hfComAccountMapper.getHfComAccountList(comAccountParamExtBo);
        if(listAccount1!=null && listAccount1.size()>0){//基本公积金
            ComAccountExtBo comAccountExtBo=listAccount1.get(0);
            hfEmpTaskHandleBo.setBasicHfComAccount(comAccountExtBo.getHfComAccount());
            hfEmpTaskHandleBo.setBasicComHfMonth(Integer.parseInt(
                Optional.ofNullable(comAccountExtBo.getComHfMonth()).orElse("0")));
            BeanUtils.copyProperties(comAccountExtBo,hfEmpTaskHandleBo);
        }
        if(listAccount2!=null && listAccount2.size()>0){//补充公积金
            ComAccountExtBo comAccountExtBo=listAccount2.get(0);
            hfEmpTaskHandleBo.setAddedHfComAccount(comAccountExtBo.getHfComAccount());
            hfEmpTaskHandleBo.setAddedComHfMonth(Integer.parseInt(
                Optional.ofNullable(comAccountExtBo.getComHfMonth()).orElse("0")));
            BeanUtils.copyProperties(comAccountExtBo,hfEmpTaskHandleBo);
        }
        HfEmpComBO hfEmpComBO = hfEmpArchiveMapper.fetchManager(companyId,employeeId);
        if(hfEmpComBO!=null){
            hfEmpTaskHandleBo.setServiceCenter(hfEmpComBO.getServiceCenter());
            hfEmpTaskHandleBo.setServiceSpecialist(hfEmpComBO.getServiceSpecialist());
            hfEmpTaskHandleBo.setLeaderShipName(hfEmpComBO.getLeaderShipName());
        }

        //雇员信息
        EmpTaskTransferBo empTaskTransferBo=new EmpTaskTransferBo();
        empTaskTransferBo.setEmployeeId(employeeId);
        empTaskTransferBo.setCompanyId(companyId);
        List<EmpTaskTransferBo> employeeBo= baseMapper.queryEmpTaskTransferNewTask(empTaskTransferBo);
        empTaskTransferBo=employeeBo.get(0);
        hfEmpTaskHandleBo.setEmployeeName(empTaskTransferBo.getEmployeeName());
        hfEmpTaskHandleBo.setInDate(empTaskTransferBo.getInDate());
        hfEmpTaskHandleBo.setCompanyName(empTaskTransferBo.getTitle());
        hfEmpTaskHandleBo.setEmployeeId(employeeId);
        hfEmpTaskHandleBo.setCompanyId(companyId);
        hfEmpTaskHandleBo.setIdNum(empTaskTransferBo.getIdNum());
        //任务单信息
        HfEmpTask hfEmpTask = new HfEmpTask();
        empTaskTransferBo.setProcessCategory(9);
        empTaskTransferBo.setTaskCategory(8);
        if(empTaskId>0){
            hfEmpTask.setEmpTaskId(empTaskId);
            hfEmpTask = baseMapper.selectOne( hfEmpTask);
            BeanUtils.copyProperties(hfEmpTask,empTaskTransferBo);
            empTaskTransferBo.setFeedbackDate(hfEmpTask.getFeedbackDate());
            empTaskTransferBo.setOperateDate(hfEmpTask.getOperateDate());
            empTaskTransferBo.setTransferDate(hfEmpTask.getTransferDate());
            hfEmpTaskHandleBo.setEmpTaskTransferBo(empTaskTransferBo);
        }else{
            hfEmpTaskHandleBo.setEmpTaskTransferBo(empTaskTransferBo);
        }

        return  hfEmpTaskHandleBo;
    }
    @Override
    public JsonResult submitTransferTask(EmpTaskTransferBo empTaskTransferBo) {
        HfEmpTask hfEmpTask =new HfEmpTask();
        Integer result = hfComAccountMapper.isExistAccount(empTaskTransferBo.getCompanyId(),empTaskTransferBo.getHfType());
        if(result <= 0){
            return JsonResultKit.ofError("该雇员所在公司没有开户！");
        }
        empTaskTransferBo.setHandleUserId(UserContext.getUser().getUserId());
        empTaskTransferBo.setHandleUserName(UserContext.getUser().getDisplayName());
       /* Wrapper<HfEmpArchive> wrapper = new EntityWrapper<>();
        wrapper.eq("company_id", empTaskTransferBo.getCompanyId());
        wrapper.eq("employee_id", empTaskTransferBo.getEmployeeId());
        wrapper.eq("hf_type",empTaskTransferBo.getHfType());
        wrapper.eq("is_active",1);
        List<HfEmpArchive> empArchiveList= hfEmpArchiveMapper.selectList(wrapper);
        if(empArchiveList.size()==0){
            return JsonResultKit.ofError("该雇员没有转入或新开的公积金档案信息！");
        }*/
       // empTaskTransferBo.setEmpArchiveId(empArchiveList.get(0).getEmpArchiveId());//设置雇员公积金档案ID
        BeanUtils.copyProperties(empTaskTransferBo,hfEmpTask);
        saveEmpTask(hfEmpTask);
        return JsonResultKit.of(200,"转移数据保存成功！" ,hfEmpTask.getEmpTaskId());
    }

    /**
     * 保存雇员公积金任务单表
     * @param hfEmpTask
     */
    final Integer EMP_TASK_PRCESS_CATEGORY_9 = 9;
    final Integer EMP_TASK_TASK_CATEGORY_8 = 8;
    final Integer EMP_TASK_TASK_STATUS_1 = 1;

    private void saveEmpTask(HfEmpTask hfEmpTask) {
        hfEmpTask.setProcessCategory(EMP_TASK_PRCESS_CATEGORY_9);//大类：其他
        hfEmpTask.setTaskCategory(EMP_TASK_TASK_CATEGORY_8);
        hfEmpTask.setTaskStatus(EMP_TASK_TASK_STATUS_1);
        if (Optional.ofNullable(hfEmpTask.getEmpTaskId()).isPresent() == false) { //公积金专员创建任务单
            HfEmpComBO hfEmpComBO = hfEmpArchiveMapper.fetchManager(hfEmpTask.getCompanyId(),hfEmpTask.getEmployeeId());
            hfEmpTask.setServiceCenterId(hfEmpComBO.getServiceCenterId());
            hfEmpTask.setSubmitTime(LocalDate.now());
            hfEmpTask.setSubmitterId(UserContext.getUser().getDisplayName());
            hfEmpTask.setCreatedBy(UserContext.getUserId());
            hfEmpTask.setCreatedDisplayName(UserContext.getUser().getDisplayName());
            hfEmpTask.setModifiedBy(UserContext.getUserId());
            hfEmpTask.setModifiedDisplayName(UserContext.getUser().getDisplayName());
            baseMapper.insert(hfEmpTask);
        } else {
            hfEmpTask.setModifiedTime(LocalDateTime.now());
            hfEmpTask.setModifiedBy(UserContext.getUserId());
            hfEmpTask.setModifiedDisplayName(UserContext.getUser().getDisplayName());
            baseMapper.updateById(hfEmpTask);
        }
    }
    @Override
    public JsonResult notHandleTransfer(EmpTaskTransferBo empTaskTransferBo) {
        HfEmpTask hfEmpTask =new HfEmpTask();
        hfEmpTask.setEmpTaskId(empTaskTransferBo.getEmpTaskId());
        hfEmpTask.setTaskStatus(5);//不需办理
        hfEmpTask.setModifiedTime(LocalDateTime.now());
        hfEmpTask.setModifiedBy(UserContext.getUserId());
        hfEmpTask.setModifiedDisplayName(UserContext.getUser().getDisplayName());
        baseMapper.updateById(hfEmpTask);
        return JsonResultKit.of("不需办理操作成功！");
    }

    @Override
    public List<Map<String, Object>> printTransferTask(EmpTaskTransferBo empTaskTransferBo) {
        HfEmpTask hfEmpTask =new HfEmpTask();
        List<Map<String, Object>> listP= new ArrayList();
        Map<String, Object> mapP=new HashMap<>();
        hfEmpTask.setEmpTaskId(empTaskTransferBo.getEmpTaskId());
        hfEmpTask.setOperateDate(LocalDate.now());//设置操作日期
        hfEmpTask.setTaskStatus(3);//已处理
        hfEmpTask.setModifiedTime(LocalDateTime.now());
        hfEmpTask.setModifiedBy(UserContext.getUserId());
        hfEmpTask.setModifiedDisplayName(UserContext.getUser().getDisplayName());
        baseMapper.updateById(hfEmpTask);
        Map<String,String> mapPrint=baseMapper.fetchPrintInfo(empTaskTransferBo.getEmpTaskId());

        mapP.put("createdByYYYY", LocalDate.now().getYear());
        mapP.put("createdByMM", LocalDate.now().getMonthValue());
        mapP.put("createdByDD", LocalDate.now().getDayOfMonth());
        mapP.put("employeeName", mapPrint.get("employee_name"));
        mapP.put("hfEmpAccount", mapPrint.get("emp_account"));
        mapP.put("inUnitName", mapPrint.get("transfer_in_unit"));
        mapP.put("outUnitName", mapPrint.get("transfer_out_unit"));
        mapP.put("inComAccount", mapPrint.get("transfer_in_unit_account"));
        mapP.put("outComAccount", mapPrint.get("transfer_out_unit_account"));
        mapP.put("transCount", mapPrint.get(""));
        listP.add(mapP);
        return listP;
    }
}
