package com.ciicsh.gto.afsupportcenter.credentialscommandservice.host.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.business.EmployeeCompanyService;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.business.EmployeeOtherService;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.business.EmployeeService;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.business.TaskTypeService;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.entity.dto.EmpQueryDTO;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.entity.dto.EmployeeCompanyDTO;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.entity.dto.EmployeeDTO;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.entity.dto.TaskTypeDTO;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.entity.po.Employee;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.entity.po.EmployeeCompany;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.entity.po.EmployeeOther;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.entity.po.TaskType;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.host.utils.SelectionUtils;
import com.ciicsh.gto.afsupportcenter.util.CommonTransform;
import com.ciicsh.gto.afsupportcenter.util.page.PageUtil;
import com.ciicsh.gto.afsupportcenter.util.result.JsonResult;
import com.ciicsh.gto.employeecenter.apiservice.api.dto.EmployeeCommonInfoDTO;
import com.ciicsh.gto.employeecenter.apiservice.api.dto.EmployeeIdQueryDTO;
import com.ciicsh.gto.employeecenter.apiservice.api.proxy.EmployeeInfoProxy;
import com.ciicsh.gto.entityidservice.api.EntityIdServiceProxy;
import com.ciicsh.gto.salecenter.apiservice.api.dto.quotation.QuotationProductResponseDTO;
import com.ciicsh.gto.salecenter.apiservice.api.proxy.QuotationProxy;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @Author: guwei
 * @Description: 雇员控制器
 * @Date: Created in 16:36 2018/2/12
 */
@RestController
@RequestMapping("/api/emp")
public class EmployeeCompanyController {

    @Autowired
    private EmployeeCompanyService employeeCompanyService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployeeOtherService employeeOtherService;

    @Autowired
    private TaskTypeService taskTypeService;

    @Autowired
    private EntityIdServiceProxy entityIdServiceProxy;

    @Autowired
    private QuotationProxy quotationProxy;

    /**
     * 获取雇员列表
     *
     * @return
     */
    @PostMapping("/find")
    public JsonResult getEmpPage(@RequestBody EmpQueryDTO empQueryDTO) {
        Page page = new Page(PageUtil.setPageNum(empQueryDTO.getPageNum()), PageUtil.setPageSize(empQueryDTO.getPageSize()));
        EmployeeCompany employeeCompany = CommonTransform.convertToEntity(empQueryDTO,EmployeeCompany.class);
        List<EmployeeCompany> list = employeeCompanyService.select(page, employeeCompany);
        List<EmployeeCompanyDTO> employeeCompanyDTOS = new ArrayList<>();
        list.stream().forEach(i -> {
            EmployeeCompanyDTO employeeCompanyDTO = new EmployeeCompanyDTO();
            if ("1".equals(i.getType())) {
                employeeCompanyDTO.setStatusUI(SelectionUtils.afStatus(i.getStatus()));
            }
            if ("2".equals(i.getType())) {
                employeeCompanyDTO.setStatusUI(SelectionUtils.bpoStatus(i.getStatus()));
            }
            if ("3".equals(i.getType())) {
                employeeCompanyDTO.setStatusUI(SelectionUtils.fcStatus(i.getStatus()));
            }
            BeanUtils.copyProperties(i, employeeCompanyDTO);
            employeeCompanyDTOS.add(employeeCompanyDTO);
        });
        page.setRecords(employeeCompanyDTOS);
        return JsonResult.success(page);
    }

    /**
     * 添加单项雇员
     *
     * @param employeeDTO
     * @return
     */
    @PostMapping("/add")
    public JsonResult addEmployee(@RequestBody EmployeeDTO employeeDTO) {
        if (employeeDTO.getIdCardType() != null && StringUtils.isNotBlank(employeeDTO.getIdNum()) && StringUtils.isNotBlank(employeeDTO.getEmployeeName()) && StringUtils.isNotBlank(employeeDTO.getCompanyId())) {
            boolean b = employeeService.findEmpByIdCard(employeeDTO.getIdCardType(), employeeDTO.getIdNum());
            if (b) {
                Employee employee = new Employee();
                EmployeeOther employeeOther = new EmployeeOther();
                BeanUtils.copyProperties(employeeDTO, employee);
                BeanUtils.copyProperties(employeeDTO, employeeOther);
                //通过公共服务获取雇员编号
                String employeeId = getEmpId();
                employee.setEmployeeId(employeeId);
                employeeOther.setEmployeeId(employeeId);
                employeeOther.setCompanyId(employeeDTO.getCompanyId());
                employeeService.addEmployee(employee);
                employeeOtherService.addEmployeeOther(employeeOther);
            } else {
                return JsonResult.errorsInfo("1", "雇员已存在，保存失败！");
            }
            return JsonResult.success(null);
        } else {
            return JsonResult.faultMessage("参数检验失败");
        }
    }

    /**
     * 获取雇员编号
     *
     * @return
     */
    public String getEmpId() {
        return entityIdServiceProxy.getEntityId("ZSGY");
    }

    /**
     * 查询证件类型
     *
     * @param pid
     * @return
     */
    @GetMapping("/findTaskType")
    public JsonResult findTaskType(@RequestParam("pid") String pid, @RequestParam("companyId") String companyId) {
        List<TaskType> list = taskTypeService.findTaskType(pid);
        ArrayList<String> baseProductIds = new ArrayList<>();
        baseProductIds.add("CPJSW1800007");
        baseProductIds.add("CPJSW1800006");
        List<QuotationProductResponseDTO> object = quotationProxy.getQuotationProductsByCompanyId(companyId, baseProductIds).getObject();
        List<String> productIds = object.stream().map(dto -> dto.getProductId()).collect(Collectors.toList());
        List<TaskTypeDTO> result = list.stream()
            .filter(i -> productIds.contains(i.getProductId()))
            .map(item -> {
            TaskTypeDTO taskTypeDTO = new TaskTypeDTO();
            BeanUtils.copyProperties(item, taskTypeDTO);
            taskTypeDTO.setTaskTypeId(String.valueOf(item.getTaskTypeId()));
            taskTypeDTO.setLevel(String.valueOf(item.getLevel()));
            taskTypeDTO.setPid(String.valueOf(item.getPid()));
            return taskTypeDTO;
        }).collect(Collectors.toList());
        if ("0".equals(pid)) {
            if (taskTypeService.findTaskType("2").stream().anyMatch(i -> productIds.contains(i.getProductId()))) {
                TaskType taskType = list.get(1);
                TaskTypeDTO taskTypeDTO = CommonTransform.convertToDTO(taskType, TaskTypeDTO.class);
                taskTypeDTO.setTaskTypeId(String.valueOf(taskType.getTaskTypeId()));
                taskTypeDTO.setLevel(String.valueOf(taskType.getLevel()));
                taskTypeDTO.setPid(String.valueOf(taskType.getPid()));
                result.add(0,taskTypeDTO);
            }
            if (taskTypeService.findTaskType("1").stream().anyMatch(i -> productIds.contains(i.getProductId()))) {
                TaskType taskType = list.get(0);
                TaskTypeDTO taskTypeDTO = CommonTransform.convertToDTO(taskType, TaskTypeDTO.class);
                taskTypeDTO.setTaskTypeId(String.valueOf(taskType.getTaskTypeId()));
                taskTypeDTO.setLevel(String.valueOf(taskType.getLevel()));
                taskTypeDTO.setPid(String.valueOf(taskType.getPid()));
                result.add(0,taskTypeDTO);
            }
        }
        return JsonResult.success(result);
    }

}
