package com.ciicsh.gto.afsupportcenter.credentialscommandservice.business.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.business.TaskMaterialService;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.business.TaskService;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.business.TaskTypeService;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.dao.TaskMapper;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.entity.dto.TaskDetialDTO;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.entity.po.Task;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.entity.po.TaskType;
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
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 证件办理任务单 服务实现类
 * </p>
 *
 * @author guwei
 * @since 2018-01-15
 */
@Service
public class TaskServiceImpl extends ServiceImpl<TaskMapper, Task> implements TaskService {

    @Autowired
    private TaskMapper taskMapper;

    @Autowired
    private TaskMaterialService taskMaterialService;

    @Autowired
    private CommandAfDisposableChargeProxy commandAfDisposableChargeProxy;

    @Autowired
    private EmployeeInfoProxy employeeInfoProxy;

    @Autowired
    private CompanyProxy companyProxy;

    @Autowired
    private TaskTypeService taskTypeService;

    @Autowired
    private ProductProxy productProxy;

    @Override
    public List<Task> selectByempId(String empId) {
        return taskMapper.selectByempId(empId);
    }

    @Override
    public Task selectItem(long taskId) {
        Task task = new Task();
        task.setTaskId(taskId);
        return taskMapper.selectOne(task);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int saveOrUpdateTask(TaskDetialDTO taskDetialDTO) {
        Task task = new Task();
        BeanUtils.copyProperties(taskDetialDTO,task);
        if (StringUtils.isNotBlank(taskDetialDTO.getCredentialsType())) {
            task.setCredentialsType(Integer.parseInt(taskDetialDTO.getCredentialsType()));
        }
        if (StringUtils.isNotBlank(taskDetialDTO.getCredentialsDealType())) {
            task.setCredentialsDealType(Integer.parseInt(taskDetialDTO.getCredentialsDealType()));
        }
        if (taskDetialDTO.getTaskId() == null) {
            task.setCreatedBy(UserContext.getUser().getDisplayName());
            task.setCreatedTime(new Date());
        }
        task.setModifiedBy(UserContext.getUser().getDisplayName());
        task.setModifiedTime(new Date());
        boolean b = this.insertOrUpdate(task);
        if (b) {
            boolean isSuccess = taskMaterialService.insertOrUpdateTaskMaterial(taskDetialDTO, task.getTaskId());
            if (isSuccess) {
                if (!(taskDetialDTO.getCredentialsType().equals(5)) && taskDetialDTO.getPayType() != null && taskDetialDTO.getPayType() == 1) {
                    boolean b1 = this.saveCommandAfDisposableCharge(taskDetialDTO);
                    if (!b1) {
                        throw new BusinessException("账单生成失败");
                    }
                }
            } else {
                throw new BusinessException("材料收缴信息保存失败");
            }
        } else {
            throw new BusinessException("任务单保存失败");
        }
        return 0;
    }

    /**
     * 调用账单中心接口
     * @param taskDetialDTO
     * @return
     */
    @Override
    public boolean saveCommandAfDisposableCharge(TaskDetialDTO taskDetialDTO) {
        List<AfDisposableChargeDTO> list = new ArrayList<>();
        AfDisposableChargeDTO afDisposableChargeDTO = new AfDisposableChargeDTO();
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
        String date = sdf.format(now);
        afDisposableChargeDTO.setBillMonth(Integer.parseInt(date));
        afDisposableChargeDTO.setActualChargeMonth(Integer.parseInt(date));
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
        String templateType = taskDetialDTO.getTemplateType();
        afDisposableChargeDTO.setEmployeeType(StringUtils.isBlank(templateType) ? 2 : Integer.parseInt(templateType));
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
        afDisposableChargeDTO.setCreatedBy(UserContext.getUser().getDisplayName());
        list.add(afDisposableChargeDTO);
        Result result = commandAfDisposableChargeProxy.saveList(list);
        return result.getStatusCode() == 0 ? true : false;
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
