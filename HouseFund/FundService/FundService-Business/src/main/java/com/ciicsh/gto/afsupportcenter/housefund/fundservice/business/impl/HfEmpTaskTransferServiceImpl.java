package com.ciicsh.gto.afsupportcenter.housefund.fundservice.business.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.HfEmpTaskHandleBo;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.customer.AccountInfoBO;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.transfer.EmpTaskTransferBo;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.business.HfEmpTaskTransferService;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.dao.HfComAccountMapper;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.dao.HfEmpTaskMapper;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.HfEmpTask;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageKit;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResult;
import jdk.nashorn.internal.runtime.regexp.joni.constants.OPCode;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 * <p>
 * 雇员任务单总表 服务实现类
 * </p>
 */
@Service
public class HfEmpTaskTransferServiceImpl extends ServiceImpl<HfEmpTaskMapper, HfEmpTask> implements HfEmpTaskTransferService {
    @Autowired
    HfComAccountMapper hfComAccountMapper;

    @Override
    public PageRows<EmpTaskTransferBo> queryEmpTaskTransferPage(PageInfo pageInfo) {
        EmpTaskTransferBo empTaskTransferBo=pageInfo.toJavaObject(EmpTaskTransferBo.class);
        return PageKit.doSelectPage(pageInfo, () -> baseMapper.queryEmpTaskTransfer(empTaskTransferBo));
    }

    @Override
    public PageRows<EmpTaskTransferBo> queryEmpTaskTransferNewTaskPage(PageInfo pageInfo) {
        EmpTaskTransferBo empTaskTransferBo=pageInfo.toJavaObject(EmpTaskTransferBo.class);
        return PageKit.doSelectPage(pageInfo, () -> baseMapper.queryEmpTaskTransferNewTask(empTaskTransferBo));
    }
    @Override
    public HfEmpTaskHandleBo queryComEmpTransferForm(String employeeId, String companyId,Long empTaskId){
        HfEmpTaskHandleBo hfEmpTaskHandleBo=new HfEmpTaskHandleBo();

        List<AccountInfoBO> listAccount1 = hfComAccountMapper.getAccountsByCompany(companyId,1);
        List<AccountInfoBO> listAccount2 = hfComAccountMapper.getAccountsByCompany(companyId,2);
        EmpTaskTransferBo empTaskTransferBo=new EmpTaskTransferBo();
        empTaskTransferBo.setEmployeeId(employeeId);
        empTaskTransferBo.setCompanyId(companyId);
        List<EmpTaskTransferBo> employeeBo= baseMapper.queryEmpTaskTransferNewTask(empTaskTransferBo);
        BeanUtils.copyProperties(employeeBo.get(0),hfEmpTaskHandleBo);
        if(listAccount1!=null && listAccount1.size()>0){
            BeanUtils.copyProperties(listAccount1.get(0),hfEmpTaskHandleBo);
        }
        if(listAccount2!=null && listAccount2.size()>0){
            BeanUtils.copyProperties(listAccount2.get(0),hfEmpTaskHandleBo);
        }
        HfEmpTask hfEmpTask = new HfEmpTask();
        if(empTaskId>0){
            hfEmpTask.setEmpTaskId(empTaskId);
            hfEmpTask = baseMapper.selectOne( hfEmpTask);
            BeanUtils.copyProperties(hfEmpTask,hfEmpTaskHandleBo);
        }
        return  hfEmpTaskHandleBo;
    }

    @Override
    public JsonResult<Object> submitTransferTask(EmpTaskTransferBo empTaskTransferBo) {
        return null;
    }
}
