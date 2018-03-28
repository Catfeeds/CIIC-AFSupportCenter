package com.ciicsh.gto.afsupportcenter.housefund.siteservice.host.controller;


import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.TemplateExportParams;
import com.baomidou.mybatisplus.toolkit.CollectionUtils;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.transfer.EmpTaskTransferBo;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.transfer.HfEmpTaskHandleVo;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.business.EmpEmployeeService;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.business.HfEmpTaskHandleService;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.business.HfEmpTaskTransferService;
import com.ciicsh.gto.afsupportcenter.util.PdfUtil;
import com.ciicsh.gto.afsupportcenter.util.aspect.log.Log;
import com.ciicsh.gto.afsupportcenter.util.exception.BusinessException;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
import com.ciicsh.gto.afsupportcenter.util.web.controller.BasicController;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResult;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResultKit;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.URLEncoder;
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

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd");

    /**
     * 雇员公积金转移任务查询
     *
     * @param pageInfo
     * @return
     */
    @RequestMapping("/queryEmpTaskTransfer")
    @Log("雇员公积金转移任务查询")
    public JsonResult<PageRows> queryEmpTaskTransfer( PageInfo pageInfo) {
        return JsonResultKit.of(business.queryEmpTaskTransferPage(pageInfo));
    }
    /**
     * 雇员公积金转移任务新建雇员查询
     *
     * @param pageInfo
     * @return
     */
    @RequestMapping("/queryEmpTaskTransferNewTask")
    @Log("雇员公积金转移任务新建雇员查询")
    public JsonResult<PageRows> queryEmpTaskTransferNewTask( PageInfo pageInfo) {
        return JsonResultKit.of(business.queryEmpTaskTransferNewTaskPage(pageInfo));
    }

    @Log("雇员公积金转移任务单表单查询")
    @RequestMapping("/queryComEmpTransferForm")
    public JsonResult<HfEmpTaskHandleVo> queryComEmpTransferForm(@RequestParam("employeeId") String employeeId,
                                                                 @RequestParam("companyId") String companyId,
                                                                 @RequestParam(value ="hfType",required = false) String hfType,
                                                                 @RequestParam(value ="empTaskId" ,required = false) String empTaskId){
        HfEmpTaskHandleVo hfEmpTaskHandleBo=new HfEmpTaskHandleVo();

         hfEmpTaskHandleBo.setHfType(Integer.parseInt(hfType));
        long employeeTaskId=0;
        if(Optional.ofNullable(empTaskId).isPresent()){
            employeeTaskId=Long.valueOf(empTaskId);
        }
        //获取企业账户和雇员信息
        hfEmpTaskHandleBo= business.queryComEmpTransferForm(employeeId,companyId,employeeTaskId);

        //获取转移任务单信息
        hfEmpTaskHandleBo.setProcessCategory(9);
        hfEmpTaskHandleBo.setTaskCategory(8);//转移任务单
        return JsonResultKit.of(hfEmpTaskHandleBo);
    }
    /**
     * 提交转移任务单
     */
    @RequestMapping("/submitTransferTask")
    public JsonResult submitTransferTask(@RequestBody EmpTaskTransferBo empTaskTransferBo){
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
    public void printTransferTask(HttpServletResponse response, EmpTaskTransferBo empTaskTransferBo)throws Exception {
        try {
            String templateFilePath;
            List<Map<String, Object>> printList = business.printTransferTask(empTaskTransferBo);
            templateFilePath = "/template/SH_HF_TRANSFER_TMP.pdf";
            String fileName = URLEncoder.encode( "上海市公积金转移通知书.pdf", "UTF-8");
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
            response.reset();
            response.setCharacterEncoding("UTF-8");
            response.setHeader("content-Type", "text/plain");
            response.getWriter().write(e.getMessage());
        }

    }


    /**
     * 不需处理任务单
     */
    @RequestMapping("/notHandleTransfer")
    public JsonResult notHandleTransfer(@RequestBody EmpTaskTransferBo empTaskTransferBo){
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
        String[] transferOutUnitAccounts = { "", "" };
        String[] transferInUnitAccounts = { "", "" };

        for (int i = 0; i < 2; i++) {
            Map<String, Object> map = new HashMap<>();
            List<Map<String, Object>>  mapList = new ArrayList<>();
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
            response.setHeader("Content-Disposition","attachment;filename=" + fileName);
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
            for(int i = 0; i < empTaskTransferBoList.size(); i++) {
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
}