package com.ciicsh.gto.afsupportcenter.cmjob.service.impl;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ciicsh.gto.afsupportcenter.cmjob.bo.TimedTaskBO;
import com.ciicsh.gto.afsupportcenter.cmjob.dao.TimedTaskMapper;
import com.ciicsh.gto.afsupportcenter.cmjob.po.TaskType;
import com.ciicsh.gto.afsupportcenter.cmjob.po.TimedTask;
import com.ciicsh.gto.afsupportcenter.cmjob.service.TaskTypeService;
import com.ciicsh.gto.afsupportcenter.cmjob.service.TimedTaskService;
import com.ciicsh.gto.afsupportcenter.util.exception.BusinessException;
import com.ciicsh.gto.afsupportcenter.util.interceptor.authenticate.UserContext;
import com.ciicsh.gto.billcenter.afmodule.cmd.api.dto.AfDisposableChargeDTO;
import com.ciicsh.gto.billcenter.afmodule.cmd.api.dto.AfDisposableChargeProductDTO;
import com.ciicsh.gto.billcenter.afmodule.cmd.api.proxy.CommandAfDisposableChargeProxy;
import com.ciicsh.gto.billcenter.afmodule.cmd.api.result.Result;
import com.ciicsh.gto.employeecenter.apiservice.api.dto.EmployeeHireInfoQueryDTO;
import com.ciicsh.gto.employeecenter.apiservice.api.dto.EmployeeInfoForCredentialsDTO;
import com.ciicsh.gto.employeecenter.apiservice.api.proxy.EmployeeInfoProxy;
import com.ciicsh.gto.productcenter.apiservice.api.dto.ProductSubjectDTO;
import com.ciicsh.gto.productcenter.apiservice.api.proxy.ProductProxy;
import com.ciicsh.gto.salecenter.apiservice.api.dto.company.AfCompanyDetailResponseDTO;
import com.ciicsh.gto.salecenter.apiservice.api.proxy.CompanyProxy;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 定时任务表 服务实现类
 * </p>
 *
 * @author guwei
 * @since 2018-09-18
 */
@Service
public class TimedTaskServiceImpl extends ServiceImpl<TimedTaskMapper, TimedTask> implements TimedTaskService {

    @Autowired
    private TaskTypeService taskTypeService;

    @Autowired
    private CommandAfDisposableChargeProxy commandAfDisposableChargeProxy;

    @Autowired
    private EmployeeInfoProxy employeeInfoProxy;

    @Autowired
    private CompanyProxy companyProxy;

    @Autowired
    private ProductProxy productProxy;

    @Override
    public void doTimedTask() {
        List<TimedTaskBO> timedTaskBOs = baseMapper.selectTimeTaskList();
        timedTaskBOs.stream().forEach(item -> {
            String taskType = item.getCredentialsType();
            String taskDealType = item.getCredentialsDealType();
            TaskType taskTypeInfo = taskTypeService.selectById(StringUtils.isBlank(taskDealType) ? taskType : taskDealType);
            if (StringUtils.isNotBlank(taskTypeInfo.getBasicProductId())) {
                item.setBasicProductId(taskTypeInfo.getBasicProductId());
            }
            if (StringUtils.isNotBlank(taskTypeInfo.getProductId())) {
                item.setProductId(taskTypeInfo.getProductId());
            }
            this.saveCommandAfDisposableCharge(item);
        });
    }

    /**
     * 调用账单中心接口
     * @param taskDetialDTO
     * @return
     */
    public boolean saveCommandAfDisposableCharge(TimedTaskBO taskDetialDTO) {
        try {
            List<AfDisposableChargeDTO> list = new ArrayList<>();
            AfDisposableChargeDTO afDisposableChargeDTO = new AfDisposableChargeDTO();
            Date now = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
            String date = sdf.format(now);
            afDisposableChargeDTO.setBillMonth(Integer.parseInt(date));
            if (taskDetialDTO.getChargeTime() != null) {
                afDisposableChargeDTO.setActualChargeMonth(Integer.parseInt(sdf.format(taskDetialDTO.getChargeTime())));
            } else {
                afDisposableChargeDTO.setActualChargeMonth(Integer.parseInt(date));
            }
            afDisposableChargeDTO.setChargeObject(2);
            if (taskDetialDTO.getChargeAmount() != null) {
                int i = taskDetialDTO.getChargeAmount().compareTo(BigDecimal.ZERO);
                afDisposableChargeDTO.setApprovalStatus( i==-1 ? 1 : 2);
            }
            afDisposableChargeDTO.setCompanyId(taskDetialDTO.getCompanyId());
            afDisposableChargeDTO.setCompanyName(this.getcompInfo(taskDetialDTO.getCompanyId()).getCompanyName());
            afDisposableChargeDTO.setEmployeeId(taskDetialDTO.getEmployeeId());
            EmployeeInfoForCredentialsDTO empInfo = this.getempInfo(taskDetialDTO.getCompanyId(), taskDetialDTO.getEmployeeId());
            afDisposableChargeDTO.setEmployeeName(empInfo.getEmployeeName());
            afDisposableChargeDTO.setEmployeeType(StringUtils.isBlank(taskDetialDTO.getTemplateType()) ? 2 : Integer.parseInt(taskDetialDTO.getTemplateType()));
            ProductSubjectDTO data = productProxy.getByBasicProductId(taskDetialDTO.getBasicProductId()).getData();
            afDisposableChargeDTO.setSubjectCodeId(Integer.parseInt(data.getSubjectCodeId()));
            afDisposableChargeDTO.setInvoiceType(1);
            /**收费产品列表*/
            List<AfDisposableChargeProductDTO> productList = new ArrayList<>();
            AfDisposableChargeProductDTO product = new AfDisposableChargeProductDTO();
            product.setProductId(taskDetialDTO.getProductId());
            TaskType taskType =
                taskTypeService.selectById(StringUtils.isBlank(taskDetialDTO.getCredentialsDealType()) ?
                    taskDetialDTO.getCredentialsType() : taskDetialDTO.getCredentialsDealType());
            if (0 != taskType.getPid()) {
                TaskType pTaskType = taskTypeService.selectById(taskType.getPid());
                product.setProductName(pTaskType.getTaskTypeName()+"-"+taskType.getTaskTypeName());
            } else {
                product.setProductName(taskType.getTaskTypeName());
            }
            if (taskDetialDTO.getPeopleNum() != null && taskDetialDTO.getChargeAmount() != null) {
                BigDecimal amount = taskDetialDTO.getChargeAmount().multiply(new BigDecimal(taskDetialDTO.getPeopleNum()));
                product.setChargeAmount(amount);
            } else {
                product.setChargeAmount(new BigDecimal(1));
            }
            productList.add(product);
            afDisposableChargeDTO.setProductList(productList);

            afDisposableChargeDTO.setActive(true);
            afDisposableChargeDTO.setCreatedBy(taskDetialDTO.getCreatedBy());
            list.add(afDisposableChargeDTO);
            Result result = commandAfDisposableChargeProxy.saveList(list);
            if (result.getStatusCode() == 0) {
                TimedTask timedTask = new TimedTask();
                timedTask.setTaskId(taskDetialDTO.getTaskId());
                timedTask.setImplement(true);
                timedTask.setActive(false);
                baseMapper.update(timedTask, new EntityWrapper<TimedTask>().eq("task_id", timedTask.getTaskId()));
            }
            return result.getStatusCode() == 0 ? true : false;
        } catch (Exception e) {
            throw new BusinessException("编号"+taskDetialDTO.getTaskId()+"账单生成异常");
        }
    }

    /**
     * 获取客户信息
     * @param companyId
     * @return
     */
    private AfCompanyDetailResponseDTO getcompInfo(String companyId) {
        return companyProxy.afDetail(companyId).getObject();
    }

    /**
     * 获取雇员信息
     * @param employeeId
     * @return
     */
    private EmployeeInfoForCredentialsDTO getempInfo(String companyId,String employeeId) {
        EmployeeHireInfoQueryDTO employeeHireInfoQueryDTO = new EmployeeHireInfoQueryDTO();
        employeeHireInfoQueryDTO.setEmployeeId(employeeId);
        employeeHireInfoQueryDTO.setCompanyId(companyId);
        return employeeInfoProxy.getEmployeeInfoForCredentials(employeeHireInfoQueryDTO).getData();
    }

}
