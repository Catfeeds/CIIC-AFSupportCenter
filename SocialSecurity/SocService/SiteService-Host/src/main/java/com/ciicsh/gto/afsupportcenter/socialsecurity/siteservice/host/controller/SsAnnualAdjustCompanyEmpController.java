package com.ciicsh.gto.afsupportcenter.socialsecurity.siteservice.host.controller;


import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.toolkit.CollectionUtils;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo.SsAnnualAdjustCompanyEmpBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business.SsAnnualAdjustCompanyEmpService;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business.SsAnnualAdjustCompanyEmpTempService;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business.SsAnnualAdjustEmployeeService;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.dto.SsAnnualAdjustCompanyEmpDTO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.dto.SsAnnualAdjustCompanyEmpTempDTO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.dto.SsAnnualAdjustEmployeeDTO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.SsAnnualAdjustCompanyEmp;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.SsAnnualAdjustCompanyEmpTemp;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.SsAnnualAdjustEmployee;
import com.ciicsh.gto.afsupportcenter.util.interceptor.authenticate.UserContext;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageKit;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
import com.ciicsh.gto.afsupportcenter.util.web.controller.BasicController;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResult;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResultKit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *  年调客户雇员信息管理前端控制器
 * </p>
 */
@RestController
@RequestMapping("/api/soccommandservice/ssAnnualAdjustCompanyEmp")
public class SsAnnualAdjustCompanyEmpController extends BasicController<SsAnnualAdjustCompanyEmpService> {

    @Autowired
    private SsAnnualAdjustEmployeeService ssAnnualAdjustEmployeeService;
    @Autowired
    private SsAnnualAdjustCompanyEmpTempService ssAnnualAdjustCompanyEmpTempService;

    /**
     * 分页查询年调客户雇员信息
     * @param pageInfo
     * @return
     */
    @RequestMapping("/annualAdjustCompanyEmpQuery")
    public JsonResult<PageRows> annualAdjustCompanyEmpQuery(PageInfo pageInfo) {
        PageRows<SsAnnualAdjustCompanyEmp> result = business.queryAnnualAdjustCompanyEmpInPage(pageInfo);
        return JsonResultKit.of(result);
    }

    /**
     * 验证年调客户雇员信息是否存在
     * @param pageInfo
     * @return
     */
    @RequestMapping("/checkExistsEmployee")
    public JsonResult checkExistsEmployee(PageInfo pageInfo) {
        SsAnnualAdjustCompanyEmpDTO ssAnnualAdjustCompanyEmpDTO = pageInfo.toJavaObject(SsAnnualAdjustCompanyEmpDTO.class);
        List<SsAnnualAdjustCompanyEmp> existsList = business.queryAnnualAdjustCompanyEmp(ssAnnualAdjustCompanyEmpDTO);
        if (CollectionUtils.isNotEmpty(existsList)) {
            if (existsList.size() == 1) {
                SsAnnualAdjustCompanyEmp ssAnnualAdjustCompanyEmp = existsList.get(0);
                return JsonResultKit.of(ssAnnualAdjustCompanyEmp);
            } else {
                return JsonResultKit.ofError("数据异常：当年仅允许一条年调客户记录[Table名称: ss_annual_adjust_company_emp]");
            }
        } else {
            SsAnnualAdjustEmployeeDTO ssAnnualAdjustEmployeeDTO = new SsAnnualAdjustEmployeeDTO();
            ssAnnualAdjustEmployeeDTO.setCompanyId(ssAnnualAdjustCompanyEmpDTO.getCompanyId());
            ssAnnualAdjustEmployeeDTO.setEmployeeId(ssAnnualAdjustCompanyEmpDTO.getEmployeeId());
            ssAnnualAdjustEmployeeDTO.setIdNum(ssAnnualAdjustCompanyEmpDTO.getIdNum());
            ssAnnualAdjustEmployeeDTO.setSsSerial(ssAnnualAdjustCompanyEmpDTO.getSsSerial());
            List<SsAnnualAdjustEmployee> originList = ssAnnualAdjustEmployeeService.queryAnnualAdjustEmployee(ssAnnualAdjustEmployeeDTO);

            if (CollectionUtils.isNotEmpty(originList)) {
                if (originList.size() == 1) {
                    SsAnnualAdjustEmployee ssAnnualAdjustEmployee = originList.get(0);
                    return JsonResultKit.of(ssAnnualAdjustEmployee);
                } else {
                    return JsonResultKit.ofError("数据异常：当年仅允许一条年调客户记录[Table名称: ss_annual_adjust_employee]");
                }
            }
        }
        return JsonResultKit.ofError("根据所设定的信息，未能查找到任何雇员信息");
    }

    @RequestMapping("/annualAdjustCompanyEmpInsertOrUpdate")
    public JsonResult annualAdjustCompanyEmpInsertOrUpdate(PageInfo pageInfo) {
        SsAnnualAdjustCompanyEmpBO ssAnnualAdjustCompanyEmpBO = pageInfo.toJavaObject(SsAnnualAdjustCompanyEmpBO.class);
        SsAnnualAdjustCompanyEmpDTO ssAnnualAdjustCompanyEmpDTO = new SsAnnualAdjustCompanyEmpDTO();
        ssAnnualAdjustCompanyEmpDTO.setCompanyId(ssAnnualAdjustCompanyEmpBO.getCompanyId());
        ssAnnualAdjustCompanyEmpDTO.setEmployeeId(ssAnnualAdjustCompanyEmpBO.getEmployeeId());
        List<SsAnnualAdjustCompanyEmp> existsList = business.queryAnnualAdjustCompanyEmp(ssAnnualAdjustCompanyEmpDTO);
        SsAnnualAdjustCompanyEmp ssAnnualAdjustCompanyEmp = new SsAnnualAdjustCompanyEmp();
        if (CollectionUtils.isNotEmpty(existsList)) {
            if (existsList.size() == 1) {
                SsAnnualAdjustCompanyEmp existsSsAnnualAdjustCompanyEmp = existsList.get(0);
                ssAnnualAdjustCompanyEmp.setAnnualAdjustCompanyEmpId(existsSsAnnualAdjustCompanyEmp.getAnnualAdjustCompanyEmpId());
                ssAnnualAdjustCompanyEmp.setChgSalary(ssAnnualAdjustCompanyEmpBO.getChgSalary());
                ssAnnualAdjustCompanyEmp.setModifiedTime(LocalDateTime.now());
                ssAnnualAdjustCompanyEmp.setModifiedBy(UserContext.getUserId());
            } else {
                return JsonResultKit.ofError("数据异常：当年仅允许一条年调客户记录[Table名称: ss_annual_adjust_company_emp]");
            }
        } else {
            ssAnnualAdjustCompanyEmp.setAnnualAdjustCompanyId(ssAnnualAdjustCompanyEmpBO.getAnnualAdjustCompanyId());
            ssAnnualAdjustCompanyEmp.setEmployeeId(ssAnnualAdjustCompanyEmpBO.getEmployeeId());
            ssAnnualAdjustCompanyEmp.setEmployeeName(ssAnnualAdjustCompanyEmpBO.getEmployeeName());
            ssAnnualAdjustCompanyEmp.setIdNum(ssAnnualAdjustCompanyEmpBO.getIdNum());
            ssAnnualAdjustCompanyEmp.setSsSerial(ssAnnualAdjustCompanyEmpBO.getSsSerial());
            ssAnnualAdjustCompanyEmp.setSalary(ssAnnualAdjustCompanyEmpBO.getSalary());
            ssAnnualAdjustCompanyEmp.setChgSalary(ssAnnualAdjustCompanyEmpBO.getChgSalary());
            ssAnnualAdjustCompanyEmp.setArchiveStatus(ssAnnualAdjustCompanyEmpBO.getArchiveStatus());
            ssAnnualAdjustCompanyEmp.setBaseAmount(ssAnnualAdjustCompanyEmpBO.getBaseAmount());
            ssAnnualAdjustCompanyEmp.setEmpClassify(ssAnnualAdjustCompanyEmpBO.getEmpClassify());
            ssAnnualAdjustCompanyEmp.setSettlementArea(ssAnnualAdjustCompanyEmpBO.getSettlementArea());
            ssAnnualAdjustCompanyEmp.setSsAccountType(ssAnnualAdjustCompanyEmpBO.getSsAccountType());
            ssAnnualAdjustCompanyEmp.setSsAccount(ssAnnualAdjustCompanyEmpBO.getSsAccount());
            ssAnnualAdjustCompanyEmp.setSsUsername(ssAnnualAdjustCompanyEmpBO.getSsUsername());
            ssAnnualAdjustCompanyEmp.setSsPwd(ssAnnualAdjustCompanyEmpBO.getSsPwd());
            ssAnnualAdjustCompanyEmp.setLowDepartmentName(ssAnnualAdjustCompanyEmpBO.getLowDepartmentName());
            ssAnnualAdjustCompanyEmp.setHighDepartmentName(ssAnnualAdjustCompanyEmpBO.getHighDepartmentName());
            ssAnnualAdjustCompanyEmp.setCreatedBy(UserContext.getUserId());
            ssAnnualAdjustCompanyEmp.setModifiedBy(UserContext.getUserId());
        }

        business.insertOrUpdate(ssAnnualAdjustCompanyEmp);
        return JsonResultKit.of();
    }

    @RequestMapping("/annualAdjustCompanyEmpsUpdate")
    public JsonResult annualAdjustCompanyEmpsUpdate(@RequestBody JSONArray array) {
        List<SsAnnualAdjustCompanyEmp> list =  array.toJavaList(SsAnnualAdjustCompanyEmp.class);
        if (!business.updateBatchById(list)) {
            return JsonResultKit.ofError("数据库更新操作失败");
        }

        return JsonResultKit.of();
    }

    @RequestMapping("/annualAdjustCompanyEmpsDelete")
    public JsonResult annualAdjustCompanyEmpsDelete(@RequestBody JSONArray array) {
        List<Long> list = array.toJavaList(Long.class);
        if (!business.deleteBatchIds(list)) {
            return JsonResultKit.ofError("数据库批量删除失败");
        }
        return JsonResultKit.of();
    }

    @RequestMapping("/annualAdjustCompanyEmpTempQuery")
    public JsonResult<PageRows> annualAdjustCompanyEmpTempQuery(PageInfo pageInfo) {
        SsAnnualAdjustCompanyEmpTempDTO ssAnnualAdjustCompanyEmpTempDTO = pageInfo.toJavaObject(SsAnnualAdjustCompanyEmpTempDTO.class);
        EntityWrapper<SsAnnualAdjustCompanyEmpTemp> condition = new EntityWrapper<>();
        condition.where("annual_adjust_company_id={0}", ssAnnualAdjustCompanyEmpTempDTO.getAnnualAdjustCompanyId());
        condition.orderBy("order_num", true);
        PageRows<SsAnnualAdjustCompanyEmpTemp> result = PageKit.doSelectPage(pageInfo, () -> ssAnnualAdjustCompanyEmpTempService.selectList(condition));
        return JsonResultKit.of(result);
    }

    @RequestMapping("/annualAdjustCompanyEmpInsert")
    public JsonResult annualAdjustCompanyEmpInsert(PageInfo pageInfo) {
        SsAnnualAdjustCompanyEmpTempDTO ssAnnualAdjustCompanyEmpTempDTO = pageInfo.toJavaObject(SsAnnualAdjustCompanyEmpTempDTO.class);
        ssAnnualAdjustCompanyEmpTempDTO.setCreatedBy(UserContext.getUserId());
        business.insertDataWithoutErrorMsg(ssAnnualAdjustCompanyEmpTempDTO);
        return JsonResultKit.of();
    }
}

