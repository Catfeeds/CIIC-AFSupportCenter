package com.ciicsh.gto.adsupportcenter.employcommandservice.host.controller;

import com.alibaba.fastjson.JSONObject;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.bo.*;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.business.*;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.dto.AmArchiveDTO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.entity.AmArchiveUse;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.entity.AmEmpMaterial;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.entity.AmInjury;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.entity.custom.archiveSearchExportOpt;
import com.ciicsh.gto.afsupportcenter.util.ExcelUtil;
import com.ciicsh.gto.afsupportcenter.util.StringUtil;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
import com.ciicsh.gto.afsupportcenter.util.web.controller.BasicController;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResult;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResultKit;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Created by zhangzhiwen on 2018/2/6.
 */

@RestController
@RequestMapping("/api/employcommandservice/amArchiveTask")
public class AmArchiveTaskController extends BasicController<IAmEmploymentService> {

    @Autowired
    private IAmArchiveService amArchiveService;

   @Autowired
   private IAmInjuryService amInjuryService;

   @Autowired
   private IAmEmpMaterialService amEmpMaterialService;

    @Autowired
    private IAmRemarkService amRemarkService;

    @Autowired
    private IAmEmpMaterialService iAmEmpMaterialService;

    @Autowired
    private  IAmArchiveUseService iAmArchiveUseService;

    @Autowired
    private IAmEmploymentService amEmploymentService;

    @Autowired
    private  IAmResignService amResignService;

    @Autowired
    private IAmEmpTaskService taskService;

    @RequestMapping("/queryAmArchive")
    public JsonResult<PageRows> queryAmArchive(PageInfo pageInfo){
        PageRows<AmEmploymentBO> result = business.queryAmArchive(pageInfo);
        return JsonResultKit.of(result);
    }

    @RequestMapping("/queryAmEmpTaskCount")
    public  JsonResult<AmEmpTaskCollection>  taskCountEmployee(PageInfo pageInfo){

        List<AmEmploymentBO> list = business.taskCountEmployee(pageInfo);

        AmEmpTaskCountBO amEmpTaskCountBO = new AmEmpTaskCountBO();
        List<AmEmpTaskCountBO>  temp = new ArrayList<>();
        amEmpTaskCountBO.setAmount(list.size());
        int num =0;
        for(int i=0;i<list.size();i++)
        {
            AmEmploymentBO amEmploymentBO = list.get(i);
            int status = amEmploymentBO.getTaskStatus();
            if(1==status){
                amEmpTaskCountBO.setNoSign(amEmploymentBO.getCount());
                num = num + amEmploymentBO.getCount();
            }else if(2==status){
                amEmpTaskCountBO.setFinished(amEmploymentBO.getCount());
                num = num + amEmploymentBO.getCount();
            }else if(3==status){
                amEmpTaskCountBO.setEmploySuccess(amEmploymentBO.getCount());
                num = num + amEmploymentBO.getCount();
            }else if(4==status){
                amEmpTaskCountBO.setEmployFailed(amEmploymentBO.getCount());
                num = num + amEmploymentBO.getCount();
            }else if(5==status){
                amEmpTaskCountBO.setEmployCancel(amEmploymentBO.getCount());
                num = num + amEmploymentBO.getCount();
            }else if(6==status){
                amEmpTaskCountBO.setOther(amEmploymentBO.getCount());
                num = num + amEmploymentBO.getCount();
            }
            amEmpTaskCountBO.setAmount(num);

        }
        temp.add(amEmpTaskCountBO);
        AmEmpTaskCollection amEmpTaskCollection = new AmEmpTaskCollection();
        amEmpTaskCollection.setRow(temp);
        return  JsonResultKit.of(amEmpTaskCollection);

    }

    @RequestMapping("/queryResignTaskCount")
    public  JsonResult<AmResignCollection>  taskCountResign(PageInfo pageInfo){
        
        List<AmEmploymentBO> list = business.taskCountResign(pageInfo);

        AmResTaskCountBO amEmpTaskCountBO = new AmResTaskCountBO();
        List<AmResTaskCountBO>  temp = new ArrayList<>();
        amEmpTaskCountBO.setAmount(list.size());
        int num =0;
        for(int i=0;i<list.size();i++)
        {
            AmEmploymentBO amEmploymentBO = list.get(i);
            int status = amEmploymentBO.getTaskStatus();
            if(1==status){
                amEmpTaskCountBO.setNoFeedback(amEmploymentBO.getCount());
                num = num + amEmploymentBO.getCount();
            }else if(2==status){
                amEmpTaskCountBO.setRefuseFailed(amEmploymentBO.getCount());
                num = num + amEmploymentBO.getCount();
            }else if(3==status){
                amEmpTaskCountBO.setRefuseBeforeWithFile(amEmploymentBO.getCount());
                num = num + amEmploymentBO.getCount();
            }else if(4==status){
                amEmpTaskCountBO.setRefuseTicketStampNoReturn(amEmploymentBO.getCount());
                num = num + amEmploymentBO.getCount();
            }else if(5==status){
                amEmpTaskCountBO.setRefuseFailed(amEmploymentBO.getCount());
                num = num + amEmploymentBO.getCount();
            }else if(6==status){
                amEmpTaskCountBO.setBeforeBatchNeedRefuse(amEmploymentBO.getCount());
            }else if(7==status){
                amEmpTaskCountBO.setOther(amEmploymentBO.getCount());
                num = num + amEmploymentBO.getCount();
            }
            amEmpTaskCountBO.setAmount(num);

        }
        temp.add(amEmpTaskCountBO);
        AmResignCollection  amResignCollection = new AmResignCollection ();
        amResignCollection.setRow(temp);
        return  JsonResultKit.of(amResignCollection);
    }

    @RequestMapping("/archiveDetailInfoQuery")
    public JsonResult archiveDetailInfoQuery(AmTaskParamBO amTaskParamBO){

        Map<String,Object>  map = taskService.getInformation(amTaskParamBO);
        AmEmpTaskBO customBO = (AmEmpTaskBO)map.get("customBO");//客户信息
        AmEmpTaskBO employeeBO = (AmEmpTaskBO)map.get("employeeBO");//雇佣信息

        PageInfo pageInfo = new PageInfo();
        JSONObject params = new JSONObject();
        params.put("employeeId",amTaskParamBO.getEmployeeId());
        params.put("remarkType",amTaskParamBO.getRemarkType());
        pageInfo.setParams(params);

        Map<String,Object>  param = new HashMap<>();
        param.put("employmentId",amTaskParamBO.getEmploymentId());
        param.put("employeeId",amTaskParamBO.getEmployeeId());
        param.put("companyId",amTaskParamBO.getCompanyId());
        //用工档案
        List<AmArchiveBO> amArchiveBOList = amArchiveService.queryAmArchiveList(param);
        //用工备注
        PageRows<AmRemarkBO> amRemarkBOPageRows = amRemarkService.queryAmRemark(pageInfo);
        //退工归还材料签收
        PageRows<AmEmpMaterialBO> result = iAmEmpMaterialService.queryAmEmpMaterial(pageInfo);
        //用工信息
        List<AmEmploymentBO> resultEmployList = amEmploymentService.queryAmEmployment(param);

        List<AmResignBO> listResignBO = amResignService.queryAmResignDetail(param);


        Map<String, Object> resultMap = new HashMap<>();
        //客户信息
        resultMap.put("customerInfo",customBO);
        //雇员信息
        resultMap.put("amEmpTaskBO",employeeBO);

        if(null!=amArchiveBOList&&amArchiveBOList.size()>0)
        {
            AmArchiveBO  amArchiveBO = amArchiveBOList.get(0);
            AmArchiveDTO amArchiveDTO = new AmArchiveDTO();
            BeanUtils.copyProperties(amArchiveBO,amArchiveDTO);
            resultMap.put("amArchaiveBo",amArchiveDTO);

            params.put("archiveId",amArchiveBO.getArchiveId());
            pageInfo.setParams(params);

            PageRows<AmInjury> amInjuryPageRows = amInjuryService.queryAmInjury(pageInfo);

            if(null!=amInjuryPageRows&&amInjuryPageRows.getRows().size()>0)
            {
                resultMap.put("amInjuryPageRows",amInjuryPageRows);
            }

        }

        if(null!= resultEmployList&&resultEmployList.size()>0)
        {
            resultMap.put("amEmploymentBO",resultEmployList.get(0));
        }

        if(null!=amRemarkBOPageRows)
        {
            resultMap.put("amRemarkBo",amRemarkBOPageRows);
        }

        if(null!=result&&result.getRows().size()>0){
            resultMap.put("materialList",result);
        }


        return  JsonResultKit.of(resultMap);
    }

    @PostMapping("/saveAmInjury")
    public JsonResult<Boolean>  saveAmInjury(@RequestBody List<AmInjury> list) {
        List<AmInjury>  data = new ArrayList<AmInjury>();
        for(AmInjury bo:list)
        {
            LocalDateTime now = LocalDateTime.now();
            bo.setCreatedTime(now);
            bo.setModifiedTime(now);
            bo.setCreatedBy("sys");
            bo.setModifiedBy("sys");
            if(bo.getInjuryId()==null){
                data.add(bo);
            }
        }

        boolean result = false;
        try {
            result = amInjuryService.insertBatch(data);
        } catch (Exception e) {

        }

        return JsonResultKit.of(result);
    }

    @PostMapping("/saveAmEmpMaterial")
    public JsonResult<Boolean>  saveAmEmpMaterial(@RequestBody List<AmEmpMaterial> list) {
        List<AmEmpMaterial>  data = new ArrayList<AmEmpMaterial>();
        for(AmEmpMaterial bo:list)
        {
            LocalDateTime now = LocalDateTime.now();
            bo.setCreatedTime(now);
            bo.setModifiedTime(now);
            bo.setCreatedBy("sys");
            bo.setModifiedBy("sys");
            if(bo.getEmpMaterialId()==null){
                data.add(bo);
            }

        }

        boolean result = false;
        try {
            result = amEmpMaterialService.insertBatch(data);
        } catch (Exception e) {

        }

        return JsonResultKit.of(result);
    }

    @RequestMapping("/deleteAmInjury")
    public JsonResult<Boolean>  deleteAmInjury(Long injuryId){
        boolean  result = amInjuryService.deleteAmInjury(injuryId);
        return JsonResultKit.of(result);
    }

    @RequestMapping("/deleteAmEmpMaterial")
    public JsonResult<Boolean>  deleteAmEmpMaterial(AmEmpMaterial amEmpMaterial){
        boolean  result = amEmpMaterialService.deleteById(amEmpMaterial);
        return JsonResultKit.of(result);
    }


    @PostMapping("/saveAmArchiveUse")
    public JsonResult<Boolean>  saveAmArchiveUse(@RequestBody List<AmArchiveUse> list) {

        for(AmArchiveUse bo:list)
        {
            LocalDateTime now = LocalDateTime.now();

            if(bo.getArchiveUseId()==null){
                bo.setCreatedTime(now);
                bo.setModifiedTime(now);
                bo.setCreatedBy("sys");
                bo.setModifiedBy("sys");
            }else {
                bo.setModifiedTime(now);
                bo.setModifiedBy("sys");
            }
        }

        boolean result = false;
        try {
            result = iAmArchiveUseService.insertOrUpdateBatch(list);
        } catch (Exception e) {

        }

        return JsonResultKit.of(result);
    }

    @RequestMapping("/queryArchiveUse")
    public JsonResult queryArchiveUse(AmArchiveUse archiveUse){

        PageInfo pageInfo = new PageInfo();
        JSONObject params = new JSONObject();
        params.put("employeeId",archiveUse.getEmployeeId());
        params.put("archiveId",archiveUse.getArchiveId());
        params.put("useBorrow",0);
        pageInfo.setParams(params);


        Map<String, Object> resultMap = new HashMap<>();

        PageRows<AmArchiveUse>  amArchiveUsePageRows  = iAmArchiveUseService.queryAmArchiveUse(pageInfo);

        params.put("useBorrow",1);
        pageInfo.setParams(params);

        PageRows<AmArchiveUse>  amArchiveUsePageRows1  = iAmArchiveUseService.queryAmArchiveUse(pageInfo);

        if(null!=amArchiveUsePageRows&&amArchiveUsePageRows.getRows().size()>0){
            resultMap.put("amArchiveUsePageRows",amArchiveUsePageRows);
        }

        if(null!=amArchiveUsePageRows1&&amArchiveUsePageRows1.getRows().size()>0){
            resultMap.put("amArchiveUsePageRows1",amArchiveUsePageRows1);
        }

        return  JsonResultKit.of(resultMap);
    }

    @RequestMapping("/deleteAmArchiveUse")
    public JsonResult<Boolean>  deleteAmArchiveUse(AmArchiveUse amArchiveUse){

        boolean result = iAmArchiveUseService.deleteAmArchiveUse(amArchiveUse);

        return JsonResultKit.of(result);

    }

    @RequestMapping("/archiveSearchExportOpt")
    public void archiveSearchExportOpt(HttpServletResponse response, AmEmploymentBO amEmploymentBO) {
        List<String> param = new ArrayList<String>();

        if(!StringUtil.isEmpty(amEmploymentBO.getParams()))
        {
            String arr[] = amEmploymentBO.getParams().split(",");
            for(int i=0;i<arr.length;i++) {
                param.add(arr[i]);
            }
        }

        amEmploymentBO.setParam(param);

        if(null!=amEmploymentBO.getTaskStatus()&&amEmploymentBO.getTaskStatus()==0){
            amEmploymentBO.setTaskStatus(null);
        }

        Date date = new Date();
        String fileNme = "用工档案任务单_"+ StringUtil.getDateString(date)+".xls";

        List<archiveSearchExportOpt> opts = business.queryAmArchiveList(amEmploymentBO);

        ExcelUtil.exportExcel(opts,archiveSearchExportOpt.class,fileNme,response);
    }

}
