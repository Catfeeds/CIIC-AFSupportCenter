package com.ciicsh.gto.afsupportcenter.housefund.fundservice.business.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
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
import com.ciicsh.gto.afsupportcenter.util.DateUtil;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageKit;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResult;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResultKit;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
        comAccountParamExtBo.setHfType(1);
        List<ComAccountExtBo> listAccount1 = hfComAccountMapper.getHfComAccountList(comAccountParamExtBo);
        comAccountParamExtBo.setHfType(2);
        List<ComAccountExtBo> listAccount2 = hfComAccountMapper.getHfComAccountList(comAccountParamExtBo);
        if(listAccount1!=null && listAccount1.size()>0){//基本公积金
            ComAccountExtBo comAccountExtBo=listAccount1.get(0);
            hfEmpTaskHandleBo.setBasicHfComAccount(comAccountExtBo.getHfComAccount());
            hfEmpTaskHandleBo.setBasicComHfMonth(Integer.parseInt(comAccountExtBo.getComHfMonth()));
            BeanUtils.copyProperties(comAccountExtBo,hfEmpTaskHandleBo);
        }
        if(listAccount2!=null && listAccount2.size()>0){//补充公积金
            ComAccountExtBo comAccountExtBo=listAccount2.get(0);
            hfEmpTaskHandleBo.setAddedHfComAccount(comAccountExtBo.getHfComAccount());
            hfEmpTaskHandleBo.setAddedComHfMonth(Integer.parseInt(comAccountExtBo.getComHfMonth()));
            BeanUtils.copyProperties(comAccountExtBo,hfEmpTaskHandleBo);
        }
        //雇员信息
        EmpTaskTransferBo empTaskTransferBo=new EmpTaskTransferBo();
        empTaskTransferBo.setEmployeeId(employeeId);
        empTaskTransferBo.setCompanyId(companyId);
        List<EmpTaskTransferBo> employeeBo= baseMapper.queryEmpTaskTransferNewTask(empTaskTransferBo);
        BeanUtils.copyProperties(employeeBo.get(0),hfEmpTaskHandleBo);
        hfEmpTaskHandleBo.setCompanyName(employeeBo.get(0).getTitle());
        //任务单信息
        HfEmpTask hfEmpTask = new HfEmpTask();
        if(empTaskId>0){
            hfEmpTask.setEmpTaskId(empTaskId);
            hfEmpTask = baseMapper.selectOne( hfEmpTask);
            BeanUtils.copyProperties(hfEmpTask,empTaskTransferBo);
            empTaskTransferBo.setFeedbackDate(DateUtil.localDateToDate(hfEmpTask.getFeedbackDate()));
            empTaskTransferBo.setOperateDate(DateUtil.localDateToDate(hfEmpTask.getOperateDate()));
            empTaskTransferBo.setTransferDate(DateUtil.localDateToDate(hfEmpTask.getTransferDate()));
            hfEmpTaskHandleBo.setEmpTaskTransferBo(empTaskTransferBo);
        }
        return  hfEmpTaskHandleBo;
    }
    @Override
    public JsonResult submitTransferTask(EmpTaskTransferBo empTaskTransferBo) {
        HfEmpTask hfEmpTask =new HfEmpTask();
        Long empTaskId=empTaskTransferBo.getEmpTaskId();
        List<AccountInfoBO> listAccount1 = hfComAccountMapper.getAccountsByCompany(empTaskTransferBo.getCompanyId(),1);
        if (listAccount1.size()==0){
            return JsonResultKit.ofError("该雇员所在公司没有开户！");
        }
        Wrapper<HfEmpArchive> wrapper = new EntityWrapper<>();
        wrapper.eq("company_id", empTaskTransferBo.getCompanyId());
        wrapper.eq("employee_id", empTaskTransferBo.getEmployeeId());
        wrapper.eq("hf_type",empTaskTransferBo.getHfType());
        wrapper.eq("is_active",1);
        List<HfEmpArchive> empArchiveList= hfEmpArchiveMapper.selectList(wrapper);
        if(empArchiveList.size()==0){
            return JsonResultKit.ofError("该雇员没有转入或新开的公积金档案信息！");
        }
        empTaskTransferBo.setEmpArchiveId(empArchiveList.get(0).getEmpArchiveId());//设置雇员公积金档案ID

        BeanUtils.copyProperties(empTaskTransferBo,hfEmpTask);
        hfEmpTask.setTransferDate(DateUtil.dateToLocaleDate(empTaskTransferBo.getTransferDate()));
        hfEmpTask.setFeedbackDate(DateUtil.dateToLocaleDate(empTaskTransferBo.getFeedbackDate()));
        hfEmpTask.setOperateDate(DateUtil.dateToLocaleDate(empTaskTransferBo.getOperateDate()));
        hfEmpTask.setProcessCategory(9);//大类：其他
        hfEmpTask.setTaskCategory(8);
        hfEmpTask.setTaskStatus(1);
        if(Optional.ofNullable(empTaskId).isPresent()==false){ //公积金专员创建任务单
            baseMapper.insert(hfEmpTask);
        }else{
            baseMapper.updateById(hfEmpTask);
        }
        return JsonResultKit.of("转移办理操作成功！");
    }

    @Override
    public JsonResult notHandleTransfer(EmpTaskTransferBo empTaskTransferBo) {
        HfEmpTask hfEmpTask =new HfEmpTask();
        hfEmpTask.setEmpTaskId(empTaskTransferBo.getEmpTaskId());
        hfEmpTask.setTaskStatus(5);//不需办理
        baseMapper.updateById(hfEmpTask);
        return JsonResultKit.of("不需办理操作成功！");
    }

    @Override
    public List<Map<String, Object>> printTransferTask(EmpTaskTransferBo empTaskTransferBo) {
        HfEmpTask hfEmpTask =new HfEmpTask();
        List<Map<String, Object>> listP= new ArrayList();
        Map<String, Object> mapP=new HashMap<>();
        hfEmpTask.setEmpTaskId(empTaskTransferBo.getEmpTaskId());
        hfEmpTask=baseMapper.selectOne(hfEmpTask);

        //empTaskTransferBo.getEmployeeId();
        mapP.put("createdByYYYY", LocalDate.now().getYear());
        mapP.put("createdByMM", LocalDate.now().getMonthValue());
        mapP.put("createdByDD", LocalDate.now().getDayOfMonth());
        mapP.put("employeeName", "");
        mapP.put("hfEmpAccount", "");
        mapP.put("inUnitName", "");
        mapP.put("outUnitName", "");
        mapP.put("inComAccount", "");
        mapP.put("outComAccount", "");
        mapP.put("transCount", "");
        listP.add(mapP);
        return  listP;
    }
}