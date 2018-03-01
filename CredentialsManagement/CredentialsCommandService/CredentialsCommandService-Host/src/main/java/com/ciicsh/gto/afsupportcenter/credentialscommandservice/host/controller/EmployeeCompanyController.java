package com.ciicsh.gto.afsupportcenter.credentialscommandservice.host.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.business.EmployeeCompanyService;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.business.EmployeeOtherService;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.business.EmployeeService;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.entity.dto.EmployeeCompanyDTO;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.entity.dto.EmployeeDTO;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.entity.po.Employee;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.entity.po.EmployeeCompany;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.entity.po.EmployeeOther;
import com.ciicsh.gto.afsupportcenter.credentialscommandservice.host.utils.SelectionUtils;
import com.ciicsh.gto.afsupportcenter.util.page.PageUtil;
import com.ciicsh.gto.afsupportcenter.util.result.JsonResult;
import com.ciicsh.gto.employeecenter.apiservice.api.dto.EmployeeCommonInfoDTO;
import com.ciicsh.gto.employeecenter.apiservice.api.dto.EmployeeHireInfoDTO;
import com.ciicsh.gto.employeecenter.apiservice.api.dto.EmployeeHireInfoQueryDTO;
import com.ciicsh.gto.employeecenter.apiservice.api.dto.EmployeeIdQueryDTO;
import com.ciicsh.gto.employeecenter.apiservice.api.dto.EmployeeInfoDTO;
import com.ciicsh.gto.employeecenter.apiservice.api.dto.EmployeeQueryDTO;
import com.ciicsh.gto.employeecenter.apiservice.api.proxy.EmployeeInfoProxy;
import com.ciicsh.gto.salecenter.apiservice.api.dto.company.AfCompanyDetailResponseDTO;
import com.ciicsh.gto.salecenter.apiservice.api.proxy.CompanyProxy;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: guwei
 * @Description:
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
    private EmployeeInfoProxy employeeInfoProxy;
    @Autowired
    private CompanyProxy companyProxy;

    /**
     * 获取雇员列表
     * @param pageNum
     * @param pageSize
     * @param companyId
     * @param employeeId
     * @param employeeName
     * @param idNum
     * @param status
     * @return
     */
    @GetMapping("/find")
    public JsonResult getEmpPage(Integer pageNum, Integer pageSize, String companyId, String employeeId,
                                 String employeeName, String idNum, Integer status, String type) {
        Page page = new Page(PageUtil.setPageNum(pageNum), PageUtil.setPageSize(pageSize));
        EmployeeCompany employeeCompany = new EmployeeCompany();
        employeeCompany.setCompanyId(companyId);
        employeeCompany.setEmployeeId(employeeId);
        employeeCompany.setEmployeeName(employeeName);
        employeeCompany.setIdNum(idNum);
        employeeCompany.setType(type);
        employeeCompany.setStatus(status);
        List<EmployeeCompany> list = employeeCompanyService.select(page, employeeCompany);
        List<EmployeeCompanyDTO> employeeCompanyDTOS = new ArrayList<>();
        list.stream().forEach(i -> {
            EmployeeCompanyDTO employeeCompanyDTO = new EmployeeCompanyDTO();
            if ("1".equals(i.getType())){
                employeeCompanyDTO.setStatusUI(SelectionUtils.afStatus(i.getStatus()));
            }
            if ("2".equals(i.getType())){
                employeeCompanyDTO.setStatusUI(SelectionUtils.bpoStatus(i.getStatus()));
            }
            if ("3".equals(i.getType())){
                employeeCompanyDTO.setStatusUI(SelectionUtils.fcStatus(i.getStatus()));
            }
            BeanUtils.copyProperties(i,employeeCompanyDTO);
            employeeCompanyDTOS.add(employeeCompanyDTO);
        });
        page.setRecords(employeeCompanyDTOS);
        return JsonResult.success(page);
    }

    /**
     * 添加单项雇员
     * @param employeeDTO
     * @return
     */
    @PostMapping("/add")
    public JsonResult addEmployee(@RequestBody EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeDTO,employee);
        employee.setEmployeeId(getEmpId()+"");
        EmployeeOther employeeOther = new EmployeeOther();
        BeanUtils.copyProperties(employeeDTO,employeeOther);
        employeeOther.setEmployeeId(employee.getEmployeeId());
        employeeOther.setCompanyId(employeeDTO.getCompanyId());
        if (employeeDTO.getIdCardType() != null && StringUtils.isNotBlank(employeeDTO.getIdNum()) && StringUtils.isNotBlank(employeeDTO.getEmployeeName())) {
            boolean b = employeeService.findEmpByIdCard(employeeDTO.getIdCardType(),employeeDTO.getIdNum());
            if (b) {
                employeeService.addEmployee(employee);
                employeeOtherService.addEmployeeOther(employeeOther);
            } else {
                return JsonResult.errorsInfo("1","雇员已存在，保存失败！");
            }
            return JsonResult.success(null);
        } else {
            return JsonResult.faultMessage("参数检验失败");
        }
    }

    public String getEmpId() {
        EmployeeIdQueryDTO employeeIdQueryDTO = new EmployeeIdQueryDTO();
        EmployeeCommonInfoDTO employeeInfoDTO = employeeInfoProxy.getEmployeeCommon(employeeIdQueryDTO).getData();
        return employeeInfoDTO.getEmployeeId();
    }

    /**
     * 查询雇员详情
     * @param companyId
     * @param employeeId
     * @param type
     * @return
     */
    @GetMapping("/getEmpInfo")
    public JsonResult getEmpInfo(Integer idCardType,String idNum,String companyId,String employeeId,Integer type){
        if (type == 1 || type == 2 || type==3) {
            EmployeeHireInfoQueryDTO employeeHireInfoQueryDTO = new EmployeeHireInfoQueryDTO();
            employeeHireInfoQueryDTO.setCompanyId(companyId);
            employeeHireInfoQueryDTO.setEmployeeId(employeeId);
            EmployeeHireInfoDTO data = employeeInfoProxy.getEmployeeHireInfo(employeeHireInfoQueryDTO).getData();
            return JsonResult.success(data);
        } else {
            EmployeeIdQueryDTO employeeIdQueryDTO = new EmployeeIdQueryDTO();
            employeeIdQueryDTO.setIdCardType(idCardType);
            employeeIdQueryDTO.setIdNum(idNum);
            EmployeeCommonInfoDTO employeeInfo = employeeInfoProxy.getEmployeeCommon(employeeIdQueryDTO).getData();
            return JsonResult.success(employeeInfo);
        }
    }

    /**
     * 查询客户详情
     * @param companyId
     * @return
     */
    @GetMapping("/getCompanyInfo")
    public JsonResult getCompanyInfo(String companyId){
        AfCompanyDetailResponseDTO afCompanyInfo = companyProxy.afDetail(companyId).getObject();
        return JsonResult.success(afCompanyInfo);
    }

}
