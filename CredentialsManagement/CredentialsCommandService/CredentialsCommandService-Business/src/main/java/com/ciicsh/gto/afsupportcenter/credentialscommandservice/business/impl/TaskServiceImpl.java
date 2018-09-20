package com.ciicsh.gto.afsupportcenter.credentialscommandservice.business.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.business.TaskMaterialService;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.business.TaskService;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.business.TaskTypeService;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.business.TimedTaskService;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.dao.TaskMapper;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.entity.dto.TaskDetialDTO;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.entity.po.Task;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.entity.po.TaskType;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.entity.po.TimedTask;
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
    private TimedTaskService timedTaskService;

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
            taskDetialDTO.setTaskId(task.getTaskId());
            boolean isSuccess = taskMaterialService.insertOrUpdateTaskMaterial(taskDetialDTO, task.getTaskId());
            if (isSuccess) {
                if (!(taskDetialDTO.getCredentialsType().equals(5)) && taskDetialDTO.getPayType() == 1) {
                    this.insertTimeTask(taskDetialDTO);
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
     * 生成定时任务单
     * @param taskDetialDTO
     */
    private void insertTimeTask(TaskDetialDTO taskDetialDTO) {
        TimedTask timedTask = new TimedTask();
        timedTask.setTaskId(taskDetialDTO.getTaskId());
        timedTask.setImplement(false);
        timedTask.setActive(true);
        timedTask.setCreatedBy(UserContext.getUser().getDisplayName());
        timedTask.setModifiedBy(UserContext.getUser().getDisplayName());
        timedTaskService.insert(timedTask);
    }
}
