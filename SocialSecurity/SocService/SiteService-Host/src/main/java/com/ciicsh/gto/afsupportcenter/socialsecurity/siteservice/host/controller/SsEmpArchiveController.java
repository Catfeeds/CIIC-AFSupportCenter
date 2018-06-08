package com.ciicsh.gto.afsupportcenter.socialsecurity.siteservice.host.controller;


import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo.SsEmpArchiveBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business.AmEmpTaskOfSsService;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business.SsEmpArchiveService;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business.SsEmpBasePeriodService;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business.SsEmpTaskService;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.dto.AmEmpTaskDTO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.SsEmpBasePeriod;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.SsEmpTask;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.entity.custom.empSSSearchExportOpt;
import com.ciicsh.gto.afsupportcenter.util.ExcelUtil;
import com.ciicsh.gto.afsupportcenter.util.StringUtil;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
import com.ciicsh.gto.afsupportcenter.util.web.controller.BasicController;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResult;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResultKit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 雇员本地社保档案主表,
 * 由中智代缴过社保的雇员在此表必有一条记录，如果雇员跳槽到另外一家客户，就会在此表产生 前端控制器
 * </p>
 *
 * @author HuangXing
 * @since 2017-12-01
 */
@RestController
@RequestMapping("/api/soccommandservice/ssEmpArchive")
public class SsEmpArchiveController extends BasicController<SsEmpArchiveService> {
    @Autowired
    private SsEmpBasePeriodService ssEmpBasePeriodService;
    @Autowired
    private SsEmpTaskService ssEmpTaskService;
    @Autowired
    private AmEmpTaskOfSsService amEmpTaskOfSsService;

    /**
     * 根据雇员任务 ID 查询 雇员本地社保档案信息
     *
     * @param empTaskId
     * @return
     */
    @RequestMapping("/queryByEmpTaskId")
    public JsonResult<SsEmpArchiveBO> queryByEmpTaskId(@RequestParam("empTaskId") String empTaskId,
                                                       @RequestParam("operatorType") String operatorType) {
        SsEmpArchiveBO dto = business.queryByEmpTaskId(empTaskId,operatorType);
        return JsonResultKit.of(dto);
    }

    /**
     * 查询雇员列表信息
     *
     * @param
     * @return
     */
    @RequestMapping("/employeeQuery")
    public JsonResult<PageRows> employeeQuery(PageInfo pageInfo) {

        PageRows<SsEmpArchiveBO> result = business.queryEmployee(pageInfo);

        return JsonResultKit.of(result);
    }

    /**
     * 雇员社保查询查询导出
     */
    @RequestMapping("/empSSSearchExport")
    public void empSSSearchExport(HttpServletResponse response,SsEmpArchiveBO ssEmpArchiveBO) {
        Date date = new Date();
        String fileNme = "雇员社保查询_"+ StringUtil.getDateString(date)+".xls";
        List<empSSSearchExportOpt> opts = business.empSSSearchExport(ssEmpArchiveBO);
        ExcelUtil.exportExcel(opts,empSSSearchExportOpt.class,fileNme,response);
    }


    /**
     * 雇员详情信息查询
     *
     * @param
     * @return
     */
    @RequestMapping("/employeeDetailInfoQuery")
    public JsonResult employeeDetailInfoQuery(@RequestParam(required = false) String empArchiveId,
                                              @RequestParam(required = false)String companyId,
                                              @RequestParam(required = false)String employeeId) {
        //if(null==empArchiveId)return JsonResultKit.ofError("ID为空");
        Map<String, Object> resultMap = new HashMap<String, Object>();
        //查询客户基本信息和雇员信息
        SsEmpArchiveBO ssEmpArchiveBO =  business.queryEmployeeDetailInfo(empArchiveId,companyId,employeeId);
        resultMap.put("ssEmpArchive",ssEmpArchiveBO);
        if(null!=empArchiveId){
            //查询社保汇缴信息
            List<SsEmpBasePeriod> empBasePeriodList= ssEmpBasePeriodService.queryPeriodByEmpArchiveId(empArchiveId);
            resultMap.put("empBasePeriod",empBasePeriodList);
            //查询变动历史(任务单)
            List<SsEmpTask> ssEmpTasksList = ssEmpTaskService.queryTaskByEmpArchiveId(empArchiveId);
            resultMap.put("ssEmpTasks",ssEmpTasksList);
        }
        //用工信息
        AmEmpTaskDTO amEmpTaskDTO = null;
        amEmpTaskDTO = amEmpTaskOfSsService.queryReworkInfo(ssEmpArchiveBO.getEmployeeId(), ssEmpArchiveBO.getCompanyId(),  1);
        if (amEmpTaskDTO == null) {
            amEmpTaskDTO = new AmEmpTaskDTO();
            amEmpTaskDTO.setTaskCategory(1);
        }
        resultMap.put("amEmpTask",amEmpTaskDTO);
        return JsonResultKit.of(resultMap);
    }
    /**
     * 修改社保序号
     * */
    @RequestMapping("/saveEmpSerial")
    public JsonResult<Object> saveEmpSerial(@RequestParam Map<String,String> map) {
       String ret= business.saveEmpSerial(map);
       if(ret.equals("SUCC")){
           return JsonResultKit.of(200,ret);
       }else{
           return JsonResultKit.of(-1,ret);
       }

    }

}

