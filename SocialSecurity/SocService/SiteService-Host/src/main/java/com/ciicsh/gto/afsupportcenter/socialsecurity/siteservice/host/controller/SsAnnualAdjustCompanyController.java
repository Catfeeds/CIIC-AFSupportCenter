package com.ciicsh.gto.afsupportcenter.socialsecurity.siteservice.host.controller;


import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.toolkit.CollectionUtils;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business.SalCompanyService;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business.SsAnnualAdjustCompanyEmpTempService;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business.SsAnnualAdjustCompanyService;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business.SsFileImportService;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.dto.SsAnnualAdjustCompanyDTO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.dto.SsAnnualAdjustCompanyEmpTempDTO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.*;
import com.ciicsh.gto.afsupportcenter.socialsecurity.siteservice.host.util.MyExcelVerifyHandler;
import com.ciicsh.gto.afsupportcenter.util.core.Result;
import com.ciicsh.gto.afsupportcenter.util.core.ResultGenerator;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
import com.ciicsh.gto.afsupportcenter.util.web.controller.BasicController;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResult;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResultKit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * <p>
 *  年调客户信息管理前端控制器
 * </p>
 */
@RestController
@RequestMapping("/api/soccommandservice/ssAnnualAdjustCompany")
public class SsAnnualAdjustCompanyController extends BasicController<SsAnnualAdjustCompanyService> {
    @Autowired
    private SalCompanyService salCompanyService;
    @Autowired
    private SsAnnualAdjustCompanyEmpTempService ssAnnualAdjustCompanyEmpTempService;
    @Autowired
    private SsFileImportService ssFileImportService;

    /**
     * 根据客户编号上传该客户所属雇员的年调收集信息
     * @param request
     * @return
     */
    @RequestMapping(value="/annualAdjustCompanyEmpUpload", consumes="multipart/form-data")
    public Result annualAdjustCompanyEmpUpload(HttpServletRequest request) {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        String companyId = multipartRequest.getParameter("companyId");

        SalCompany company = salCompanyService.selectById(companyId);
        if (company == null || !company.getActive()) {
            return ResultGenerator.genServerFailResult("客户记录不存在[客户编号：" + companyId + "]");
        }
        SsAnnualAdjustCompanyDTO ssAnnualAdjustCompanyDTO = new SsAnnualAdjustCompanyDTO();
        ssAnnualAdjustCompanyDTO.setCompanyId(companyId); // TODO current operator, operation rights?
        SsAnnualAdjustCompany ssAnnualAdjustCompany = new SsAnnualAdjustCompany();
        ssAnnualAdjustCompany.setCompanyId(companyId);
        ssAnnualAdjustCompany.setCompanyName(company.getTitle());
        ssAnnualAdjustCompany.setActive(true);
        Calendar calendar = Calendar.getInstance();
        ssAnnualAdjustCompany.setAdjustYear(String.valueOf(calendar.get(Calendar.YEAR)));
        ssAnnualAdjustCompany.setCreatedBy("12"); // TODO current operator,how to get it?
        ssAnnualAdjustCompany.setModifiedBy("12"); // TODO current operator,how to get it?

        List<SsAnnualAdjustCompany> ssAnnualAdjustCompanyList = null;
        ssAnnualAdjustCompanyList = business.queryAnnualAdjustCompany(ssAnnualAdjustCompanyDTO);
        if (CollectionUtils.isEmpty(ssAnnualAdjustCompanyList)) {
            business.insert(ssAnnualAdjustCompany);
            System.out.println("AnnualAdjustCompanyId: " + ssAnnualAdjustCompany.getAnnualAdjustCompanyId()); // TODO log
        }
        if (ssAnnualAdjustCompanyList != null) {
            if (ssAnnualAdjustCompanyList.size() > 1) {
                return ResultGenerator.genServerFailResult("数据异常：当年仅允许一条年调客户记录");
            }
            ssAnnualAdjustCompany = ssAnnualAdjustCompanyList.get(0);
        }

        Map<String, Object> condition = new HashMap<>();
        int importType = 1; // TODO Constants
        String conditionKey = "annual_adjust_company_id";
        Long annualAdjustCompanyId = ssAnnualAdjustCompany.getAnnualAdjustCompanyId();
        condition.put(conditionKey, annualAdjustCompanyId);

        // read the imported file, and insert data into the temporary table, then merge to real table
        MultiValueMap<String, MultipartFile> files = multipartRequest.getMultiFileMap();//得到文件map对象

        Map<String, Integer> fieldLengthMap = new HashMap<>();
        fieldLengthMap.put("employeeName", 100);
        fieldLengthMap.put("lowDepartmentName", 50);
        fieldLengthMap.put("highDepartmentName", 50);

        Map<String, Object> setValueMap = new HashMap<>();
        setValueMap.put("annualAdjustCompanyId", annualAdjustCompanyId);

        Map<String, Object> verifyConfigMap = new HashMap<>();
        verifyConfigMap.put("companyId", companyId);
        verifyConfigMap.put("msg", "不是当前客户的客户编码");

        List<String> skipFields = new ArrayList<>();
        skipFields.add("serialVersionUID");
        skipFields.add("annualAdjustCompanyEmpTempId");
        skipFields.add("errorMsg");

        ImportParams importParams = new ImportParams();
        try {
            importParams.setKeyIndex(null);
            importParams.setNeedVerfiy(true);
            importParams.setVerifyHanlder(new MyExcelVerifyHandler(fieldLengthMap, setValueMap, skipFields, verifyConfigMap));

            ssFileImportService.executeExcelImport(true, conditionKey, importType, annualAdjustCompanyId,
                importParams, ssAnnualAdjustCompanyEmpTempService, files, "12"); //TODO createdBy
            afterInsert(annualAdjustCompanyId, companyId);
        } catch (Exception e) {
            e.printStackTrace(); // TODO log
            return ResultGenerator.genServerFailResult("文件读取失败，请先检查文件格式是否正确");
        }

        return ResultGenerator.genSuccessResult(condition);
    }

    /**
     * 分页查询年调客户信息
     * @param pageInfo
     * @return
     */
    @RequestMapping("/annualAdjustCompanyQuery")
    public JsonResult<PageRows> annualAdjustCompanyQuery(PageInfo pageInfo) {
        PageRows<SsAnnualAdjustCompany> result = business.queryAnnualAdjustCompanyInPage(pageInfo);
        return JsonResultKit.of(result);
    }

    protected void afterInsert(Long annualAdjustCompanyId, String companyId) {
        String[] repeatingColumns = { "employee_id", "ss_serial" };
        String[] columnCNs = { "雇员编号", "社保序号" };
        SsAnnualAdjustCompanyEmpTempDTO ssAnnualAdjustCompanyEmpTempDTO = new SsAnnualAdjustCompanyEmpTempDTO();
        ssAnnualAdjustCompanyEmpTempDTO.setAnnualAdjustCompanyId(annualAdjustCompanyId);

        for (int i = 0; i < repeatingColumns.length; i++) {
            ssAnnualAdjustCompanyEmpTempDTO.setRepeatingColumn(repeatingColumns[i]);
            ssAnnualAdjustCompanyEmpTempDTO.setColumnCN(columnCNs[i]);
            ssAnnualAdjustCompanyEmpTempService.updateErrorMsgForRepeatingEmployeeId(ssAnnualAdjustCompanyEmpTempDTO);
        }

        ssAnnualAdjustCompanyEmpTempDTO.setCompanyId(companyId);
        ssAnnualAdjustCompanyEmpTempDTO.setColumnCN("雇员识别信息(客户编号、雇员编号、雇员姓名、社保序号)与年调库不符");
        ssAnnualAdjustCompanyEmpTempService.updateErrorMsgForNotExistsEmployee(ssAnnualAdjustCompanyEmpTempDTO);
    }

    @RequestMapping("/annualAdjustCompanysUpdate")
    @Transactional(rollbackFor = Exception.class)
    public JsonResult annualAdjustCompanysUpdate(@RequestBody JSONArray array) {
        List<SsAnnualAdjustCompany> list =  array.toJavaList(SsAnnualAdjustCompany.class);
        list.stream().forEach((e) -> {
            e.setModifiedBy("11"); // TODO modifiedBy
            business.updateAnnualAdjustCompanysByComAccountId(e);
        });

        return JsonResultKit.of();
    }
}

