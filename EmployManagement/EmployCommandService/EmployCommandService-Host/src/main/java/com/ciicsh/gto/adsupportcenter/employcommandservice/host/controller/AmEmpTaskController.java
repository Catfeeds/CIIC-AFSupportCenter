package com.ciicsh.gto.adsupportcenter.employcommandservice.host.controller;


import com.alibaba.fastjson.JSONObject;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.bo.*;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.business.*;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.business.utils.CommonApiUtils;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.entity.*;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.entity.custom.employSearchExportOpt;
import com.ciicsh.gto.afsupportcenter.util.ExcelUtil;
import com.ciicsh.gto.afsupportcenter.util.StringUtil;
import com.ciicsh.gto.afsupportcenter.util.aspect.log.Log;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
import com.ciicsh.gto.afsupportcenter.util.web.controller.BasicController;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResult;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResultKit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

/**
 * <p>
 * 用工退工任务单 前端控制器
 * </p>
 */
@RestController
@RequestMapping("/api/employcommandservice/amEmpTask")
public class AmEmpTaskController extends BasicController<IAmEmpTaskService> {

    @Autowired
    private IAmEmpMaterialService iAmEmpMaterialService;

    @Autowired
    private IAmEmploymentService amEmploymentService;

    @Autowired
    private IAmArchiveService amArchiveService;

    @Autowired
    private IAmRemarkService amRemarkService;


    @Autowired
    private CommonApiUtils employeeInfoProxy;


    /**
     *用工资料任务单查询
     * @param pageInfo
     * @return
     */
    @Log("用工资料任务单查询")
    @RequestMapping("/queryAmEmpTask")
    public JsonResult<PageRows> queryAmEmpTask(PageInfo pageInfo) {

        PageRows<AmEmpTaskBO> result = business.queryAmEmpTask(pageInfo);

        return JsonResultKit.of(result);

    }

    /**
     * 用工任务汇总统计
     * @param pageInfo
     * @return
     */
    @Log("用工任务汇总统计")
    @RequestMapping("/queryAmEmpTaskCount")
    public  JsonResult<AmEmpTaskCollection>  taskCount(PageInfo pageInfo){

        List<AmEmpTaskBO> list = business.taskCount(pageInfo);

        AmEmpTaskCountBO amEmpTaskCountBO = new AmEmpTaskCountBO();
        List<AmEmpTaskCountBO>  temp = new ArrayList<>();
        amEmpTaskCountBO.setAmount(list.size());
        int num =0;
        for(int i=0;i<list.size();i++)
        {
            AmEmpTaskBO amEmpTaskBO = list.get(i);
             int status = amEmpTaskBO.getTaskStatus();
            if(1==status){
                amEmpTaskCountBO.setNoSign(amEmpTaskBO.getCount());
                num = num + amEmpTaskBO.getCount();
            }else if(2==status){
                amEmpTaskCountBO.setFinished(amEmpTaskBO.getCount());
                num = num + amEmpTaskBO.getCount();
            }else if(3==status){
                amEmpTaskCountBO.setEmploySuccess(amEmpTaskBO.getCount());
                num = num + amEmpTaskBO.getCount();
            }else if(4==status){
                amEmpTaskCountBO.setEmployFailed(amEmpTaskBO.getCount());
                num = num + amEmpTaskBO.getCount();
            }else if(5==status){
                amEmpTaskCountBO.setEmployCancel(amEmpTaskBO.getCount());
                num = num + amEmpTaskBO.getCount();
            }else{
                amEmpTaskCountBO.setOther(amEmpTaskBO.getCount());
                num = num + amEmpTaskBO.getCount();
            }
            amEmpTaskCountBO.setAmount(num);

        }
        temp.add(amEmpTaskCountBO);
        AmEmpTaskCollection amEmpTaskCollection = new AmEmpTaskCollection();
        amEmpTaskCollection.setRow(temp);
        return  JsonResultKit.of(amEmpTaskCollection);
    }

    /**
     * 用工办理查询
     * @param employeeId
     * @return
     */
    @Log("用工办理查询")
    @RequestMapping("/employeeDetailInfoQuery")
    public JsonResult employeeDetailInfoQuery(AmTaskParamBO amTaskParamBO) {

        Map<String,Object>  map = business.getInformation(amTaskParamBO);

        AmEmpTaskBO customBO = (AmEmpTaskBO)map.get("customBO");//客户信息
        AmEmpTaskBO employeeBO = (AmEmpTaskBO)map.get("employeeBO");//雇佣信息

        AmEmpTaskBO bo = new AmEmpTaskBO();
        bo.setEmployeeId(amTaskParamBO.getEmployeeId());
        bo.setCompanyId(amTaskParamBO.getCompanyId());
        Map<String,Object> param = new HashMap<>();
        param.put("employeeId",amTaskParamBO.getEmployeeId());
        param.put("companyId",amTaskParamBO.getCompanyId());

        //用工材料
        PageInfo pageInfo = new PageInfo();
        JSONObject params = new JSONObject();
        params.put("employeeId",amTaskParamBO.getEmployeeId());
        params.put("remarkType",amTaskParamBO.getRemarkType());
        params.put("companyId",amTaskParamBO.getCompanyId());
        pageInfo.setParams(params);

        //用工材料
        List<AmEmpMaterialBO> empMaterialList = new ArrayList<>();
        PageRows<AmEmpMaterialBO> result = iAmEmpMaterialService.queryAmEmpMaterial(pageInfo);
        if(result!=null&&result.getRows().size()>0)
        {
            empMaterialList.addAll(result.getRows());
        }

        //用工信息
        List<AmEmploymentBO> resultEmployList = amEmploymentService.queryAmEmployment(param);
        //用工档案
        List<AmArchiveBO> amArchiveBOList = null;

        if(null!=resultEmployList&&resultEmployList.size()>0)
        {
            params.put("employmentId",resultEmployList.get(0).getEmploymentId());
            amArchiveBOList = amArchiveService.queryAmArchiveList(params);
        }

        //用工备注
        PageRows<AmRemarkBO> amRemarkBOPageRows = amRemarkService.queryAmRemark(pageInfo);
        //客户信息
        List<AmEmpTaskBO>  listCompany = business.queryCustom(amTaskParamBO.getCompanyId());

        Map<String, Object> resultMap = new HashMap<String, Object>();

        //客户信息
        resultMap.put("customerInfo",customBO);
        //雇员信息
        resultMap.put("amEmpTaskBO",employeeBO);

        resultMap.put("materialList",empMaterialList);

        if(null!= resultEmployList&&resultEmployList.size()>0)
        {
            resultMap.put("amEmploymentBO",resultEmployList.get(0));
        }
        if(null!=amArchiveBOList&&amArchiveBOList.size()>0)
        {
            resultMap.put("amArchaiveBo",amArchiveBOList.get(0));
        }
        if(null!=amRemarkBOPageRows)
        {
            resultMap.put("amRemarkBo",amRemarkBOPageRows);
        }

        if(null!=listCompany&&listCompany.size()>0)
        {
            resultMap.put("company",listCompany.get(0));
        }

        return JsonResultKit.of(resultMap);

    }

    /**
     * 保存用工信息
     */
    @Log("保存用工信息")
    @RequestMapping("/saveEmployee")
    public JsonResult<Boolean> saveEmployee(AmEmployment entity) {
        LocalDateTime now = LocalDateTime.now();
        if(entity.getEmploymentId()==null){
            entity.setCreatedTime(now);
            entity.setModifiedTime(now);
            entity.setCreatedBy("sys");
            entity.setModifiedBy("sys");
            entity.setIsActive(1);
        }else{
            entity.setModifiedTime(now);
            entity.setModifiedBy("sys");
        }
        boolean result =  amEmploymentService.insertOrUpdate(entity);
        return JsonResultKit.of(result);
    }

    /**
     * 保存用工档案
     * @param bo
     * @return
     */
    @Log("保存用工档案")
    @RequestMapping("/saveAmArchive")
    public  JsonResult<Boolean>  saveAmArchive(AmArchive entity){
        LocalDateTime now = LocalDateTime.now();
        if(entity.getArchiveId()==null){
            entity.setCreatedTime(now);
            entity.setModifiedTime(now);
            entity.setCreatedBy("sys");
            entity.setModifiedBy("sys");
            entity.setIsActive(1);
        }else{
            entity.setModifiedTime(now);
            entity.setModifiedBy("sys");
        }
        if(!StringUtil.isEmpty(entity.getEmployFeedback())){
            AmEmployment amEmployment = amEmploymentService.selectById(entity.getEmploymentId());
            AmEmpTask amEmpTask = business.selectById(amEmployment.getEmpTaskId());
            amEmpTask.setTaskStatus(Integer.parseInt(entity.getEmployFeedback()));
            business.insertOrUpdate(amEmpTask);
        }

        boolean result = amArchiveService.insertOrUpdate(entity);
        return JsonResultKit.of(result);
    }

    @PostMapping("/saveAmRemark")
    @Log("保存用工备注信息")
    public JsonResult<Boolean>  saveAmRemark(@RequestBody List<AmRemark> list) {

        List<AmRemark>  data = new ArrayList<AmRemark>();
         for(AmRemark bo:list)
         {
             LocalDateTime now = LocalDateTime.now();
             bo.setCreatedTime(now);
             bo.setModifiedTime(now);
             bo.setCreatedBy("sys");
             bo.setModifiedBy("sys");
             if(bo.getRemarkId()==null){
                 data.add(bo);
             }
         }

        boolean result = false;
        try {
            result = amRemarkService.insertOrUpdateBatch(data);
        } catch (Exception e) {

        }
        return JsonResultKit.of(result);

    }

    @Log("用工备注查询")
    @RequestMapping("/queryAmRemark")
    public JsonResult queryAmRemark(PageInfo pageInfo) {
        PageRows<AmRemarkBO> result = amRemarkService.queryAmRemark(pageInfo);
        return JsonResultKit.of(result);
    }

    @RequestMapping("/deleteAmRemark")
    public JsonResult<Boolean>  deleteAmRemark(Long amRemarkId){
       boolean  result = amRemarkService.deleteAmRemark(amRemarkId);
        return JsonResultKit.of(result);
    }

    @PostMapping("/receiveMaterial")
    public JsonResult<Boolean> receiveMaterial(@RequestBody List<AmEmpMaterial> list){
        for(AmEmpMaterial material:list)
        {
            material.setReceiveDate(LocalDate.now());
            material.setReceiveMan("sys");
        }

        boolean result =  iAmEmpMaterialService.updateBatchById(list);
        return JsonResultKit.of(result);
    }

    @PostMapping("/rejectMaterial")
    public JsonResult<Boolean> rejectMaterial(@RequestBody List<AmEmpMaterial> list){
        for(AmEmpMaterial material:list)
        {
            material.setRejectDate(LocalDate.now());
            material.setRejectMan("sys");
        }

        boolean result =  iAmEmpMaterialService.updateBatchById(list);
        return JsonResultKit.of(result);
    }

    @RequestMapping("/updateTaskStatus")
    public  JsonResult<Boolean>  updateTaskStatus(String employmentId){
        Map<String,Object>  param = new HashMap<>();
        param.put("employmentId",employmentId);
       boolean result = business.updateTaskStatus(param);
        return JsonResultKit.of(result);
    }

    /**
     * 雇员社保查询查询导出
     */
    @RequestMapping("/employSearchExportOpt")
    public void employSearchExportOpt(HttpServletResponse response, AmEmpTaskBO amEmpTaskBO) {

        List<String> param = new ArrayList<String>();

        if (!StringUtil.isEmpty(amEmpTaskBO.getParams())) {
            String arr[] = amEmpTaskBO.getParams().split(",");
            for (int i = 0; i < arr.length; i++) {
                param.add(arr[i]);
            }
        }

        amEmpTaskBO.setParam(param);

        if (null != amEmpTaskBO.getTaskStatus() && amEmpTaskBO.getTaskStatus() == 0) {
            amEmpTaskBO.setTaskStatus(null);
        }

        Date date = new Date();
        String fileNme = "用工任务单_"+ StringUtil.getDateString(date)+".xls";

        List<employSearchExportOpt> opts = business.queryAmEmpTaskList(amEmpTaskBO);

        ExcelUtil.exportExcel(opts,employSearchExportOpt.class,fileNme,response);
    }


}

