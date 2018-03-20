package com.ciicsh.gto.afsupportcenter.housefund.siteservice.host.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.toolkit.CollectionUtils;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.HfEmpTaskBatchRejectBo;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.HfEmpTaskExportBo;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.HfEmpTaskHandleBo;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.HfEmpTaskRejectExportBo;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.bo.transfer.EmpTaskTransferBo;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.business.EmpEmployeeService;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.business.HfEmpTaskHandleService;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.business.HfEmpTaskService;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.business.HfEmpTaskTransferService;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.constant.HfEmpTaskConstant;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.EmpEmployee;
import com.ciicsh.gto.afsupportcenter.housefund.fundservice.entity.HfEmpTask;
import com.ciicsh.gto.afsupportcenter.util.CalculateSocialUtils;
import com.ciicsh.gto.afsupportcenter.util.aspect.log.Log;
import com.ciicsh.gto.afsupportcenter.util.constant.DictUtil;
import com.ciicsh.gto.afsupportcenter.util.exception.BusinessException;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
import com.ciicsh.gto.afsupportcenter.util.web.controller.BasicController;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResult;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResultKit;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.time.LocalDateTime;
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

    @RequestMapping("/queryComEmpTransferForm")
    @Log("雇员公积金转移任务单表单查询")
    public JsonResult<HfEmpTaskHandleBo> queryComEmpTransferForm(@RequestParam("employeeId") String employeeId,
                                                                 @RequestParam("companyId") String companyId,
                                                                 @RequestParam("hfType") int hfType,@RequestParam("empTaskId") Long empTaskId){
        HfEmpTaskHandleBo hfEmpTaskHandleBo=new HfEmpTaskHandleBo();
        //获取企业账户和雇员信息
        hfEmpTaskHandleBo= business.queryComEmpTransferForm(employeeId,companyId,empTaskId);

        //获取转移任务单信息

        return JsonResultKit.of(hfEmpTaskHandleBo);
    }
    /**
     * 提交转移任务单
     */
    @RequestMapping("/submitTransferTask")
    public JsonResult<Object> submitTransferTask(@RequestBody EmpTaskTransferBo empTaskTransferBo){
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
    public JsonResult<Object> printTransferTask(){
        return JsonResultKit.ofError("");
    }
    /**
     * 不需处理任务单
     */
    @RequestMapping("/notHandleTransfer")
    public JsonResult<Object>  notHandleTransfer(){
        return JsonResultKit.ofError("");
    }
}
