package com.ciicsh.gto.afsupportcenter.housefund.siteservice.host.controller;


import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.HFMonthChargeQueryBO;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.transfer.EmpTaskTransferBo;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.transfer.HfEmpTaskHandleVo;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.business.EmpEmployeeService;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.business.HfEmpTaskHandleService;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.business.HfEmpTaskTransferService;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.constant.HfEmpTaskConstant;
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
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.time.format.DateTimeFormatter;
import java.util.*;


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
}
