package com.ciicsh.gto.afsupportcenter.housefund.siteservice.host.controller;


import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.TemplateExportParams;
import com.baomidou.mybatisplus.toolkit.CollectionUtils;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.transfer.*;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.business.*;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.constant.HfEmpTaskConstant;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.HfEmpTask;
import com.ciicsh.gto.afsupportcenter.housefund.siteservice.host.util.FeedbackDateVerifyHandler;
import com.ciicsh.gto.afsupportcenter.util.ExcelUtil;
import com.ciicsh.gto.afsupportcenter.util.PdfUtil;
import com.ciicsh.gto.afsupportcenter.util.exception.BusinessException;
import com.ciicsh.gto.afsupportcenter.util.interceptor.authenticate.UserContext;
import com.ciicsh.gto.afsupportcenter.util.logservice.LogApiUtil;
import com.ciicsh.gto.afsupportcenter.util.logservice.LogMessage;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
import com.ciicsh.gto.afsupportcenter.util.web.controller.BasicController;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResult;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResultKit;
import org.apache.commons.lang.ArrayUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/fundcommandservice/hfEmpTask")
public class HfEmpTaskTransferController extends BasicController<HfEmpTaskTransferService> {
    @Autowired
    HfEmpTaskHandleService hfEmpTaskHandleService;
    @Autowired
    EmpEmployeeService empEmployeeService;
    @Autowired
    HfFileImportService hfFileImportService;
    @Autowired
    HFImportFeedbackDateService hfImportFeedbackDateService;
    @Autowired
    LogApiUtil logApiUtil;


    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd");

    /**
     * 雇员公积金转移任务查询
     *
     * @param pageInfo
     * @return
     */
    @RequestMapping("/queryEmpTaskTransfer")
    public JsonResult<PageRows> queryEmpTaskTransfer(PageInfo pageInfo) {
        return JsonResultKit.of(business.queryEmpTaskTransferPage(pageInfo));
    }

    /**
     * 雇员公积金转移任务新建雇员查询
     *
     * @param pageInfo
     * @return
     */
    @RequestMapping("/queryEmpTaskTransferNewTask")
    public JsonResult<PageRows> queryEmpTaskTransferNewTask(PageInfo pageInfo) {
        return JsonResultKit.of(business.queryEmpTaskTransferNewTaskPage(pageInfo));
    }

    /**
     * 雇员公积金转移任务单表单查询
     *
     * @param employeeId
     * @param companyId
     * @param hfType
     * @param empTaskId
     * @return
     */
    @RequestMapping("/queryComEmpTransferForm")
    public JsonResult<HfEmpTaskHandleVo> queryComEmpTransferForm(@RequestParam("employeeId") String employeeId,
                                                                 @RequestParam("companyId") String companyId,
                                                                 @RequestParam(value = "hfType", required = false) String hfType,
                                                                 @RequestParam(value = "empTaskId", required = false) String empTaskId) {
        HfEmpTaskHandleVo hfEmpTaskHandleBo = new HfEmpTaskHandleVo();
        hfEmpTaskHandleBo.setHfType(Integer.parseInt(hfType));
        long employeeTaskId = 0;
        if (Optional.ofNullable(empTaskId).isPresent()) {
            employeeTaskId = Long.valueOf(empTaskId);
        }
        //获取企业账户和雇员信息
        hfEmpTaskHandleBo = business.queryComEmpTransferForm(employeeId, companyId, employeeTaskId);

        //获取转移任务单信息
        hfEmpTaskHandleBo.setProcessCategory(9);
        hfEmpTaskHandleBo.setTaskCategory(8);//转移任务单
        return JsonResultKit.of(hfEmpTaskHandleBo);
    }

    /**
     * 提交转移任务单
     */
    @RequestMapping("/submitTransferTask")
    public JsonResult submitTransferTask(@RequestBody EmpTaskTransferBo empTaskTransferBo) {
        try {
            return business.submitTransferTask(empTaskTransferBo);
        } catch (BusinessException e) {
            return JsonResultKit.ofError(e.getMessage());
        }
    }

    /**
     * 打印转移任务单
     */
    @RequestMapping("/printTransferTask")
    public void printTransferTask(HttpServletResponse response, EmpTaskTransferBo empTaskTransferBo) throws Exception {
        try {
            String templateFilePath;
            List<Map<String, Object>> printList = business.printTransferTask(empTaskTransferBo);
            templateFilePath = "/template/SH_HF_TRANSFER_TMP.pdf";
            String fileName = URLEncoder.encode("上海市公积金转移通知书.pdf", "UTF-8");
            response.reset();
            response.setCharacterEncoding("UTF-8");
            response.setHeader("content-Type", "application/pdf");
            response.setHeader("Content-Disposition",
                "attachment;filename=" + fileName);
            PdfUtil.createPdfByTemplate(templateFilePath,
                PdfUtil.DEFAULT_FONT_NAME,
                PdfUtil.DEFAULT_FONT_ENCODING,
                false,
                true,
                printList,
                response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
            response.reset();
            response.setCharacterEncoding("UTF-8");
            response.setHeader("content-Type", "text/plain");
            response.getWriter().write(e.getMessage());
        }

    }


    /**
     * 不需处理转移任务单
     */
    @RequestMapping("/notHandleTransfer")
    public JsonResult notHandleTransfer(@RequestBody EmpTaskTransferBo empTaskTransferBo) {
        return business.notHandleTransfer(empTaskTransferBo);
    }

    /**
     * 雇员公积金转移清册导出
     *
     * @param pageInfo
     * @return
     */
    @RequestMapping("/multiEmpTaskTransferExport")
    public void multiEmpTaskTransferExport(HttpServletResponse response, PageInfo pageInfo) throws Exception {
        EmpTaskTransferBo empTaskTransferBo = pageInfo.toJavaObject(EmpTaskTransferBo.class);
        List<EmpTaskTransferBo> empTaskTransferBoList = business.queryEmpTaskTransfer(empTaskTransferBo);

        Map<Integer, Map<String, Object>> alMap = new HashMap<>();
        String[] transferOutUnitAccounts = {"", ""};
        String[] transferInUnitAccounts = {"", ""};

        for (int i = 0; i < 2; i++) {
            Map<String, Object> map = new HashMap<>();
            List<Map<String, Object>> mapList = new ArrayList<>();
            final int m = i;
            List<EmpTaskTransferBo> list = empTaskTransferBoList.stream().filter(e -> e.getHfType() == m + 1).collect(Collectors.toList());

            if (CollectionUtils.isNotEmpty(list)) {
                String account = list.get(0).getTransferOutUnitAccount();
                if (account != null) {
                    transferOutUnitAccounts[i] = account;
                }
                account = list.get(0).getTransferInUnitAccount();
                if (account != null) {
                    transferInUnitAccounts[i] = account;
                }

                for (int j = 0; j < list.size(); j++) {
                    if (!transferOutUnitAccounts[i].equals(list.get(j).getTransferOutUnitAccount())) {
                        throw new BusinessException("仅支持一次导出相同转出单位公积金账号的信息");
                    }
                    if (!transferInUnitAccounts[i].equals(list.get(j).getTransferInUnitAccount())) {
                        throw new BusinessException("仅支持一次导出相同转入单位公积金账号的信息");
                    }

                    Map<String, Object> lm = new HashMap<>();
                    lm.put("hfEmpAccount", list.get(j).getHfEmpAccount());
                    lm.put("employeeName", list.get(j).getEmployeeName());
                    lm.put("rowNo", j + 1);
                    mapList.add(lm);
                }
            }
            map.put("maplist", mapList);
            alMap.put(i, map);
        }

        TemplateExportParams params = new TemplateExportParams("/template/SH_HF_MULTI_TRANSFER_TMP.xlsx", 0, 1);
        Workbook workbook = ExcelExportUtil.exportExcel(alMap, params);
        for (int i = 0; i < 2; i++) {
            String left = workbook.getSheetAt(i).getHeader().getLeft();
            left = left.replace("{{transferOutUnitAccount}}", transferOutUnitAccounts[i]);
            left = left.replace("{{transferInUnitAccount}}", transferInUnitAccounts[i]);
            workbook.getSheetAt(i).getHeader().setLeft(left);
        }
        try {
            String fileName = URLEncoder.encode("上海市公积金雇员转移清册.xlsx", "UTF-8");

            response.reset();
            response.setCharacterEncoding("UTF-8");
//            response.setHeader("content-Type", "application/vnd.ms-excel");
            response.setHeader("content-Type", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
            workbook.write(response.getOutputStream());
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 雇员公积金转移TXT导出
     *
     * @param response
     * @param pageInfo
     * @throws Exception
     */
    @RequestMapping("/empTaskTransferTxtExport")
    public void empTaskTransferTxtExport(HttpServletResponse response, PageInfo pageInfo) throws Exception {
        EmpTaskTransferBo empTaskTransferBo = pageInfo.toJavaObject(EmpTaskTransferBo.class);
        List<EmpTaskTransferBo> empTaskTransferBoList = business.queryEmpTaskTransfer(empTaskTransferBo);

        Writer writer = new OutputStreamWriter(response.getOutputStream(), "UTF-8");
        String title = "序号|公积金账号||||";
        String template = "%1$d|%2$s||||";
        List<String> outputList = new ArrayList<>();

        writer.append(title);
        if (CollectionUtils.isNotEmpty(empTaskTransferBoList)) {
            for (int i = 0; i < empTaskTransferBoList.size(); i++) {
                empTaskTransferBo = empTaskTransferBoList.get(i);
                outputList.add(String.format(template, i + 1, empTaskTransferBo.getHfEmpAccount()));
            }

            for (String output : outputList) {
                writer.append("\r\n");
                writer.append(output);
            }
        }
        String fileName = URLEncoder.encode("上海市公积金雇员转移TXT.txt", "UTF-8");

        response.setCharacterEncoding("UTF-8");
        response.setHeader("content-Type", "text/plain");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
        writer.close();
    }

    @RequestMapping(value = "/feedbackDateUpload", consumes = "multipart/form-data")
    public JsonResult feedbackDateUpload(HttpServletRequest request) {
        EmpTaskTransferBo empTaskTransferBo = new EmpTaskTransferBo();
        empTaskTransferBo.setTaskStatus(HfEmpTaskConstant.TASK_STATUS_COMPLETED); // TODO 2 or 3?
        List<EmpTaskTransferBo> empTaskTransferBoList = business.queryEmpTaskTransfer(empTaskTransferBo);

        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultiValueMap<String, MultipartFile> files = multipartRequest.getMultiFileMap();//得到文件map对象
        ImportParams importParams = new ImportParams();
        List<ImportFeedbackDateBO> successList = new ArrayList<>();
        List<ImportFeedbackDateBO> failList = new ArrayList<>();

        try {
            importParams.setKeyIndex(null);
            importParams.setNeedVerfiy(true);
            importParams.setVerifyHanlder(new FeedbackDateVerifyHandler(empTaskTransferBoList));
            hfFileImportService.executeExcelImport(hfFileImportService.IMPORT_TYPE_EMP_TASK_TRANS_FEEDBACK_DATE, hfFileImportService.DEFAULT_RELATED_UNIT_ID,
                null, hfImportFeedbackDateService, successList, failList,
                importParams, files, UserContext.getUserId());
        } catch (Exception e) {
            LogMessage logMessage = LogMessage.create().setTitle("文件上传")
                .setContent("根据客户编号上传该客户所属雇员的年调收集信息" + e.getMessage());
            logApiUtil.error(logMessage);
            return JsonResultKit.ofError("文件读取失败，请先检查文件格式是否正确");
        }

        return JsonResultKit.of(failList);
    }

    /**
     * 雇员公积金转移任务批量更新回单日期
     *
     * @param feedbackDateBatchUpdateBO
     * @return
     */
    @RequestMapping("/batchUpdateFeedbackDate")
    public JsonResult batchUpdateFeedbackDate(@RequestBody FeedbackDateBatchUpdateBO feedbackDateBatchUpdateBO) {
        Long[] selectedData = feedbackDateBatchUpdateBO.getSelectedData();
        if (!ArrayUtils.isEmpty(selectedData)) {
            List<HfEmpTask> list = new ArrayList<>();
            for (Long empTaskId : selectedData) {
                HfEmpTask hfEmpTask = new HfEmpTask();
                hfEmpTask.setEmpTaskId(empTaskId);
                hfEmpTask.setFeedbackDate(feedbackDateBatchUpdateBO.getFeedbackDate());
                hfEmpTask.setModifiedTime(LocalDateTime.now());
                hfEmpTask.setModifiedBy(UserContext.getUserId());
                hfEmpTask.setModifiedDisplayName(UserContext.getUser().getDisplayName());
                list.add(hfEmpTask);
            }

            if (!business.updateBatchById(list)) {
                return JsonResultKit.ofError("数据库批量更新失败");
            }
        }

        return JsonResultKit.of();
    }

    /**
     * 雇员公积金转移导出
     */
    @RequestMapping("/queryEmpTaskTransferExp")
    public JsonResult<PageRows> queryEmpTaskTransferExp(PageInfo pageInfo) {
        return null;
        //return JsonResultKit.of(business.queryEmpTaskTransferPage(pageInfo));
    }

    /**
     * 雇员公积金转移导出
     */
    @RequestMapping("/downloadTransferTemplateFile")
    public void downloadTransferTemplateFile(HttpServletResponse response) {
        String fileNme = "雇员公积金转移导入模板.xls";
        List<EmpTransferTemplateImpXsl> opts = new ArrayList();
        ExcelUtil.exportExcel(opts, EmpTransferTemplateImpXsl.class, fileNme, response);
    }

}
