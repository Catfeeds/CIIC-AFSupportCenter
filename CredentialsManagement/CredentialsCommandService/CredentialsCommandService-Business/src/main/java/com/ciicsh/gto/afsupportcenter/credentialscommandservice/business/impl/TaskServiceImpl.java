package com.ciicsh.gto.afsupportcenter.credentialscommandservice.business.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.business.TaskMaterialService;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.business.TaskService;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.dao.TaskMapper;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.entity.dto.TaskDetialDTO;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.entity.po.Task;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.entity.po.TaskMaterial;
import com.ciicsh.gto.afsupportcenter.util.result.JsonResult;
import com.ciicsh.gto.billcenter.afmodule.cmd.api.dto.AfDisposableChargeDTO;
import com.ciicsh.gto.billcenter.afmodule.cmd.api.proxy.CommandAfDisposableChargeProxy;
import com.ciicsh.gto.billcenter.afmodule.cmd.api.result.Result;
import com.ciicsh.gto.employeecenter.apiservice.api.dto.EmployeeHireInfoQueryDTO;
import com.ciicsh.gto.employeecenter.apiservice.api.dto.EmployeeInfoForCredentialsDTO;
import com.ciicsh.gto.employeecenter.apiservice.api.proxy.EmployeeInfoProxy;
import com.ciicsh.gto.salecenter.apiservice.api.dto.company.AfCompanyDetailResponseDTO;
import com.ciicsh.gto.salecenter.apiservice.api.proxy.CompanyProxy;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

import java.math.BigDecimal;
import java.util.Calendar;
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
        //TODO
        if (taskDetialDTO.getTaskId() == null) {
            task.setCreatedBy("test");
            task.setCreatedTime(new Date());
        }
        task.setModifiedBy("test");
        task.setModifiedTime(new Date());
        boolean b = this.insertOrUpdate(task);
        if (b) {
            boolean isSuccess = taskMaterialService.insertOrUpdateTaskMaterial(taskDetialDTO, task.getTaskId());
            if (isSuccess) {
                if (taskDetialDTO.getPayType() != null && taskDetialDTO.getPayType() == 1) {
                    boolean b1 = this.saveCommandAfDisposableCharge(taskDetialDTO);
                    if (!b1) {
                        return 3;
                    }
                }
            } else {
                return 2;
            }
        } else {
            return 1;
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
        AfDisposableChargeDTO afDisposableChargeDTO = new AfDisposableChargeDTO();
        Calendar c = Calendar.getInstance();
        afDisposableChargeDTO.setBillMonth(c.get(Calendar.MONTH)+1);
        afDisposableChargeDTO.setActualChargeMonth(c.get(Calendar.MONTH)+1);
        afDisposableChargeDTO.setChargeObject(2);
        if (taskDetialDTO.getChargeAmount() != null) {
            int i = taskDetialDTO.getChargeAmount().compareTo(BigDecimal.ZERO);
            afDisposableChargeDTO.setApprovalStatus( i==-1 ? 1 : 2);
        }
        afDisposableChargeDTO.setCompanyId(taskDetialDTO.getCompanyId());
        afDisposableChargeDTO.setCompanyName(this.getcompInfo(taskDetialDTO.getCompanyId()).getCompanyName());
        afDisposableChargeDTO.setEmployeeId(taskDetialDTO.getEmployeeId());
        EmployeeInfoForCredentialsDTO empinfo = this.getempInfo(taskDetialDTO.getCompanyId(), taskDetialDTO.getEmployeeId());
        afDisposableChargeDTO.setEmployeeName(empinfo.getEmployeeName());
        //todo 雇员类型（应收类型）:1-派遣;2-代理;3-外包
        afDisposableChargeDTO.setEmployeeType(1);
//      afDisposableChargeDTO.setSubjectCodeId();
        afDisposableChargeDTO.setCreatedBy("test");
        Result result = commandAfDisposableChargeProxy.save(afDisposableChargeDTO);
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
