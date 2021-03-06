package com.ciicsh.gto.afsupportcenter.socialsecurity.siteservice.host.controller;


import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.TemplateExportParams;
import com.baomidou.mybatisplus.toolkit.CollectionUtils;
import com.ciicsh.gto.afsupportcenter.socialsecurity.siteservice.host.util.MyExcelVerifyHandler;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo.SsAnnualAdjustAccountBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo.SsAnnualAdjustAccountEmpBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business.*;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.dto.SsAnnualAdjustAccountDTO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.dto.SsAnnualAdjustAccountEmpDTO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.dto.SsAnnualAdjustAccountEmpTempDTO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.SsAnnualAdjustAccount;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.SsComAccount;
import com.ciicsh.gto.afsupportcenter.util.core.Result;
import com.ciicsh.gto.afsupportcenter.util.core.ResultGenerator;
import com.ciicsh.gto.afsupportcenter.util.interceptor.authenticate.UserContext;
import com.ciicsh.gto.afsupportcenter.util.logService.LogApiUtil;
import com.ciicsh.gto.afsupportcenter.util.logService.LogMessage;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
import com.ciicsh.gto.afsupportcenter.util.web.controller.BasicController;
import com.ciicsh.gto.afsupportcenter.util.web.response.ExportResponseUtil;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResult;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResultKit;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * <p>
 * 年调社保账户信息管理前端控制器
 * </p>
 */
@RestController
@RequestMapping("/api/soccommandservice/ssAnnualAdjustAccount")
public class SsAnnualAdjustAccountController extends BasicController<SsAnnualAdjustAccountService> {
    @Autowired
    private SsComAccountService ssComAccountService;
    @Autowired
    private SsAnnualAdjustAccountEmpTempService ssAnnualAdjustAccountEmpTempService;
    @Autowired
    private SsAnnualAdjustAccountEmpService ssAnnualAdjustAccountEmpService;
    @Autowired
    private SsFileImportService ssFileImportService;
    @Autowired
    private LogApiUtil logApiUtil;

    /**
     * 分页查询年调社保账户信息
     *
     * @param pageInfo
     * @return
     */
    @RequestMapping("/annualAdjustAccountQuery")
    public JsonResult<PageRows> annualAdjustAccountQuery(PageInfo pageInfo) {
        PageRows<SsAnnualAdjustAccount> result = business.queryAnnualAdjustAccountInPage(pageInfo);
        return JsonResultKit.of(result);
    }

    /**
     * 根据客户编号上传该企业社保账户所属雇员的年调收集信息
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/annualAdjustAccountEmpUpload", consumes = "multipart/form-data")
    public Result annualAdjustAccountEmpUpload(HttpServletRequest request) {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        String ssAccount = multipartRequest.getParameter("ssAccount");
        Map<String, Object> map = new HashMap<>();
        map.put("ss_account", ssAccount);
        map.put("is_active", 1);
        SsComAccount ssComAccount;
        Long comAccountId;

        List<SsComAccount> ssComAccountList = ssComAccountService.selectByMap(map);
        if (CollectionUtils.isEmpty(ssComAccountList)) {
            return ResultGenerator.genServerFailResult("社保账户记录不存在[社保账户编号：" + ssAccount + "]");
        } else {
            if (ssComAccountList.size() > 1) {
                return ResultGenerator.genServerFailResult("社保账户编号重复[社保账户编号：" + ssAccount + "]");
            }
            ssComAccount = ssComAccountList.get(0);
            comAccountId = ssComAccount.getComAccountId();
        }

        SsAnnualAdjustAccountDTO ssAnnualAdjustAccountDTO = new SsAnnualAdjustAccountDTO();
        ssAnnualAdjustAccountDTO.setComAccountId(comAccountId); // TODO current operator, operation rights?

        List<SsAnnualAdjustAccount> list = business.queryAnnualAdjustAccount(ssAnnualAdjustAccountDTO);
        SsAnnualAdjustAccount ssAnnualAdjustAccount = null;

        if (CollectionUtils.isEmpty(list)) {
            ssAnnualAdjustAccount = new SsAnnualAdjustAccount();
            ssAnnualAdjustAccount.setComAccountId(comAccountId);
            ssAnnualAdjustAccount.setComAccountName(ssComAccount.getComAccountName());
            ssAnnualAdjustAccount.setSsAccount(ssComAccount.getSsAccount());
            ssAnnualAdjustAccount.setActive(true);
            Calendar calendar = Calendar.getInstance();
            ssAnnualAdjustAccount.setAdjustYear(String.valueOf(calendar.get(Calendar.YEAR)));
            ssAnnualAdjustAccount.setCreatedBy(UserContext.getUserId());
            ssAnnualAdjustAccount.setModifiedBy(UserContext.getUserId());
            business.insert(ssAnnualAdjustAccount);
        }

        if (CollectionUtils.isNotEmpty(list)) {
            if (list.size() > 1) {
                return ResultGenerator.genServerFailResult("数据异常：当年仅允许一条年调社保账户记录");
            }
            ssAnnualAdjustAccount = list.get(0);
        }

        // delete data from temporary table by last upload
        Map<String, Object> condition = new HashMap<>();
        int importType = ssFileImportService.IMPORT_TYPE_SS_ANNUAL_ADJUST_ACCOUNT_EMP;
        String conditionKey = "annual_adjust_account_id";
        Long annualAdjustAccountId = ssAnnualAdjustAccount.getAnnualAdjustAccountId();
        condition.put(conditionKey, annualAdjustAccountId);

        // read the imported file, and insert data into the temporary table, then merge to real table
        MultiValueMap<String, MultipartFile> files = multipartRequest.getMultiFileMap();//得到文件map对象

        // TODO check the head data, ssAccount

        Map<String, Integer> fieldLengthMap = new HashMap<>();
        fieldLengthMap.put("employeeName", 100);

        List<String> skipFields = new ArrayList<>();
        skipFields.add("serialVersionUID");
        skipFields.add("annualAdjustAccountEmpTempId");
        skipFields.add("errorMsg");

        Map<String, Object> setValueMap = new HashMap<>();
        setValueMap.put("annualAdjustAccountId", annualAdjustAccountId);

        ImportParams importParams = new ImportParams();
        importParams.setKeyIndex(null);
        importParams.setNeedVerfiy(true);
        importParams.setTitleRows(5);
        importParams.setLastOfInvalidRow(2);
        importParams.setVerifyHanlder(new MyExcelVerifyHandler(logApiUtil, fieldLengthMap, setValueMap, skipFields, null));

        try {
            ssFileImportService.deleteExistData(ssAnnualAdjustAccountEmpTempService, condition);

            for (int i = 0; i < 2; i++) {
                importParams.setStartSheetIndex(i);
                setValueMap.put("accountStatus", i + 1);
                ssFileImportService.executeExcelImport(importType, annualAdjustAccountId,
                    ssAnnualAdjustAccountEmpTempService, null, null, null,
                    importParams, files, UserContext.getUserId());
            }
            afterInsert(annualAdjustAccountId);
        } catch (Exception e) {
            LogMessage logMessage = LogMessage.create().setTitle("文件上传")
                .setContent("根据客户编号上传该企业社保账户所属雇员的平均工资收集信息" + e.getMessage());
            logApiUtil.error(logMessage);
            return ResultGenerator.genServerFailResult("文件读取失败，请先检查文件格式是否正确");
        }

        return ResultGenerator.genSuccessResult(condition);
    }

    private void afterInsert(Long annualAdjustAccountId) {
        SsAnnualAdjustAccountEmpTempDTO ssAnnualAdjustAccountEmpTempDTO = new SsAnnualAdjustAccountEmpTempDTO();
        ssAnnualAdjustAccountEmpTempDTO.setAnnualAdjustAccountId(annualAdjustAccountId);
        ssAnnualAdjustAccountEmpTempDTO.setRepeatingColumn("ss_serial");
        ssAnnualAdjustAccountEmpTempDTO.setColumnCN("社保序号");
        ssAnnualAdjustAccountEmpTempService.updateErrorMsgForRepeatingEmployeeId(ssAnnualAdjustAccountEmpTempDTO);
    }

    /**
     * 导出社保账户平均月工资
     *
     * @param response
     * @param pageInfo
     * @throws Exception
     */
    @RequestMapping("/accountEmpAvgMonthSalaryExport")
    public void accountEmpAvgMonthSalaryExport(HttpServletResponse response, PageInfo pageInfo) throws Exception {
        try {
            SsAnnualAdjustAccountDTO ssAnnualAdjustAccountDTO = pageInfo.toJavaObject(SsAnnualAdjustAccountDTO.class);
            Map<String, Object> queryCondition = new HashMap<>();
            queryCondition.put("annual_adjust_account_id", ssAnnualAdjustAccountDTO.getAnnualAdjustAccountId());
            SsAnnualAdjustAccountEmpDTO ssAnnualAdjustAccountEmpDTO = new SsAnnualAdjustAccountEmpDTO();
            ssAnnualAdjustAccountEmpDTO.setAnnualAdjustAccountId(ssAnnualAdjustAccountDTO.getAnnualAdjustAccountId());
            SsAnnualAdjustAccount ssAnnualAdjustAccount = business.selectById(ssAnnualAdjustAccountDTO.getAnnualAdjustAccountId());
            if (ssAnnualAdjustAccount != null && ssAnnualAdjustAccount.getActive()) {
                List<SsAnnualAdjustAccountBO> boList = business.getUnitAvgMonthSalaryByAnnualAdjustAccountId(ssAnnualAdjustAccountDTO.getAnnualAdjustAccountId());
                if (CollectionUtils.isNotEmpty(boList)) {
                    if (boList.size() > 1) {
//                    throw new Exception("存在不同的单位平均工资信息，数据不正确");
                        response.setCharacterEncoding("UTF-8");
                        response.setHeader("content-Type", "text/html");
                        response.getWriter().write("存在不同的单位平均工资信息，数据不正确<a href=\"javascript:history.go(-1)\">返回</a>");
                        return;
                    }
                } else {
                    response.setCharacterEncoding("UTF-8");
                    response.setHeader("content-Type", "text/html");
                    response.getWriter().write("该企业账户不存在单位平均工资信息，请先导入数据<a href=\"javascript:history.go(-1)\">返回</a>");
                    return;
                }

                List<SsAnnualAdjustAccountEmpBO> list;

                Calendar calendar = Calendar.getInstance();
                int reportYear = calendar.get(Calendar.YEAR) - 1;
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
                String printDate = sdf.format(new Date());
                Map<Integer, Map<String, Object>> alMap = new HashMap<>();

                for (int i = 0; i < 2; i++) {
                    Map<String, Object> map = new HashMap<>();
                    map.put("reportYear", reportYear);
                    map.put("comAccountName", ssAnnualAdjustAccount.getComAccountName());
                    map.put("ssAccount", ssAnnualAdjustAccount.getSsAccount());
                    map.put("printDate", printDate);

                    List<Map<String, Object>> mapList = null;
                    ssAnnualAdjustAccountEmpDTO.setAccountStatus(i + 1);
                    list = ssAnnualAdjustAccountEmpService.queryAnnualAdjustAccountEmp(ssAnnualAdjustAccountEmpDTO);

                    if (CollectionUtils.isEmpty(list)) {
                        map.put("empTotal", 0);
                    } else {
                        map.put("empTotal", list.size());
                        mapList = new ArrayList<>();
                        for (SsAnnualAdjustAccountEmpBO bo : list) {
                            Map<String, Object> lm = new HashMap<>();
                            lm.put("ssSerial", bo.getSsSerial());
                            lm.put("employeeName", bo.getEmployeeName());
                            lm.put("idNum", bo.getIdNum() == null ? "" : bo.getIdNum());
                            lm.put("emp", "");
                            lm.put("paymentMonths", bo.getPaymentMonths() == null ? "" : bo.getPaymentMonths());
                            lm.put("avgMonthSalary", bo.getAvgMonthSalary() == null ? "" : bo.getAvgMonthSalary());
                            mapList.add(lm);
                        }
                    }
                    map.put("maplist", mapList);
                    alMap.put(i, map);
                }
                Map<String, Object> map = new HashMap<>();
                map.put("reportYear", reportYear);
                map.put("comAccountName", ssAnnualAdjustAccount.getComAccountName());
                map.put("ssAccount", ssAnnualAdjustAccount.getSsAccount());
                map.put("printDate", printDate);
                BigDecimal accountAvgMonthSalary = boList.get(0).getAccountAvgMonthSalary();
                BigDecimal allTotalAmount = boList.get(0).getAccountSalaryAmount();
                BigDecimal allEmpTotal = boList.get(0).getAccountEmpCount();
                map.put("accountAvgMonthSalary", accountAvgMonthSalary == null ? "" : accountAvgMonthSalary);
                map.put("allTotalAmount", allTotalAmount == null ? "" : allTotalAmount);
                map.put("allEmpTotal", allEmpTotal == null ? "" : allEmpTotal);
                alMap.put(2, map);

                TemplateExportParams params = new TemplateExportParams("/template/ssAccount_reportYear.xls", 0, 1, 2);
                Workbook workbook = ExcelExportUtil.exportExcel(alMap, params);
                String fileName = "ssAccount_reportYear.xls"
                    .replace("ssAccount", ssAnnualAdjustAccount.getSsAccount())
                    .replace("reportYear", String.valueOf(reportYear));

                response.reset();
                response.setCharacterEncoding("UTF-8");
                response.setHeader("content-Type", "application/vnd.ms-excel");
//                response.setHeader("content-Type", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
//            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
                ExportResponseUtil.encodeExportFileName(response, fileName);
                workbook.write(response.getOutputStream());
                workbook.close();
            }
        } catch (Exception e) {
            logApiUtil.error(LogMessage.create().setContent("SsAnnualAdjustAccountController#accountEmpAvgMonthSalaryExport").setContent(e.getMessage()));
            response.setCharacterEncoding("UTF-8");
            response.setHeader("content-Type", "text/html");
            response.getWriter().write("服务器异常[" + e.getMessage() + "]<a href=\"javascript:history.go(-1)\">返回</a>");
            return;
        }
    }

    @RequestMapping("/annualAdjustAccountDelete")
    @Transactional(rollbackFor = Exception.class)
    public Result annualAdjustAccountDelete(PageInfo pageInfo) {
        SsAnnualAdjustAccountDTO ssAnnualAdjustAccountDTO = pageInfo.toJavaObject(SsAnnualAdjustAccountDTO.class);
        Long annualAdjustAccountId = ssAnnualAdjustAccountDTO.getAnnualAdjustAccountId();
        Map<String, Object> map = new HashMap<>();
        map.put("annual_adjust_account_id", annualAdjustAccountId);
        ssAnnualAdjustAccountEmpService.deleteByMap(map);
        business.deleteById(annualAdjustAccountId);
        return ResultGenerator.genSuccessResult();
    }
}

