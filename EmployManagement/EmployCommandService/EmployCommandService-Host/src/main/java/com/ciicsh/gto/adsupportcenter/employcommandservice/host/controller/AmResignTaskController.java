package com.ciicsh.gto.adsupportcenter.employcommandservice.host.controller;

import com.alibaba.fastjson.JSONObject;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.bo.*;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.business.*;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.business.utils.ReasonUtil;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.entity.AmEmpTask;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.entity.AmResign;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.entity.custom.resignSearchExportOpt;
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

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Created by zhangzhiwen on 2018/2/1.
 */

@RestController
@RequestMapping("/api/employcommandservice/amResignTask")
public class AmResignTaskController extends BasicController<IAmResignService> {

    @Autowired
    private IAmRemarkService amRemarkService;

    @Autowired
    private IAmEmploymentService amEmploymentService;

    @Autowired
    private IAmArchiveService amArchiveService;

    @Autowired
    private IAmEmpTaskService taskService;

    @RequestMapping("/queryAmResign")
    public JsonResult<PageRows>  queryAmResign(PageInfo pageInfo){
        PageRows<AmResignBO> result = business.queryAmResign(pageInfo);

       List<AmResignBO> data = result.getRows();

       for(AmResignBO amResignBO:data)
       {
           if(!StringUtil.isEmpty(amResignBO.getLuyongHandleEnd())){
               if("1".equals(amResignBO.getLuyongHandleEnd())){
                   amResignBO.setLuyongHandleEnd("是");
               }else {
                   amResignBO.setLuyongHandleEnd("否");
               }
           }

           if(!StringUtil.isEmpty(amResignBO.getResignFeedback1())){
               amResignBO.setResignFeedback1(ReasonUtil.getTgfk(amResignBO.getResignFeedback1()));
           }
       }

        return JsonResultKit.of(result);
    }

    @RequestMapping("/queryResignTaskCount")
    public  JsonResult<AmResignCollection>  taskCount(PageInfo pageInfo){

        List<AmResignBO> list = business.taskCount(pageInfo);

        AmResTaskCountBO amEmpTaskCountBO = new AmResTaskCountBO();
        List<AmResTaskCountBO>  temp = new ArrayList<>();
        amEmpTaskCountBO.setAmount(list.size());
        int num =0;
        for(int i=0;i<list.size();i++)
        {
            AmResignBO amResignBO = list.get(i);
            int status = amResignBO.getTaskStatus();
            if(1==status){
                amEmpTaskCountBO.setNoFeedback(amResignBO.getCount());
                num = num + amResignBO.getCount();
            }else if(2==status){
                amEmpTaskCountBO.setRefuseFinished(amResignBO.getCount());
                num = num + amResignBO.getCount();
            }else if(3==status){
                amEmpTaskCountBO.setRefuseBeforeWithFile(amResignBO.getCount());
                num = num + amResignBO.getCount();
            }else if(4==status){
                amEmpTaskCountBO.setRefuseTicketStampNoReturn(amResignBO.getCount());
                num = num + amResignBO.getCount();
            }else if(5==status){
                amEmpTaskCountBO.setRefuseFailed(amResignBO.getCount());
                num = num + amResignBO.getCount();
            }else if(6==status){
                amEmpTaskCountBO.setBeforeBatchNeedRefuse(amResignBO.getCount());
            }else{
                amEmpTaskCountBO.setOther(amResignBO.getCount());
                num = num + amResignBO.getCount();
            }
            amEmpTaskCountBO.setAmount(num);

        }
        temp.add(amEmpTaskCountBO);
        AmResignCollection  amResignCollection = new AmResignCollection ();
        amResignCollection.setRow(temp);
        return  JsonResultKit.of(amResignCollection);
    }

    @RequestMapping("/queryAmResignDetail")
    public JsonResult queryAmResignDetail(AmTaskParamBO amTaskParamBO){

        Map<String,Object>  map = taskService.getInformation(amTaskParamBO);

        AmEmpTaskBO customBO = (AmEmpTaskBO)map.get("customBO");//客户信息
        AmEmpTaskBO employeeBO = (AmEmpTaskBO)map.get("employeeBO");//雇佣信息


        Map<String,Object> param = new HashMap<>();
        param.put("employeeId",amTaskParamBO.getEmployeeId());
        param.put("companyId",amTaskParamBO.getCompanyId());

        //退工信息
        AmResignBO amResignBO = new AmResignBO();
        amResignBO.setFirstInDate(employeeBO.getFirstInDate());

        List<AmResignBO> listResignBO = business.queryAmResignDetail(param);

        PageInfo pageInfo = new PageInfo();
        JSONObject params = new JSONObject();
        params.put("employeeId",amTaskParamBO.getEmployeeId());
        params.put("remarkType",amTaskParamBO.getRemarkType());
        pageInfo.setParams(params);

        //退工备注
        PageRows<AmRemarkBO> amRemarkBOPageRows = amRemarkService.queryAmRemark(pageInfo);

        params.put("remarkType","1");
        pageInfo.setParams(params);
        PageRows<AmRemarkBO> amRemarkBOPageRows1 = amRemarkService.queryAmRemark(pageInfo);
        //档案备注
        params.put("remarkType","2");
        pageInfo.setParams(params);
        PageRows<AmRemarkBO> amRemarkBOPageRows2 = amRemarkService.queryAmRemark(pageInfo);
        //用工信息
        List<AmEmploymentBO> resultEmployList = amEmploymentService.queryAmEmployment(param);

        //用工档案
        List<AmArchiveBO> amArchiveBOList = null;

        if(null!=resultEmployList&&resultEmployList.size()>0){
            param.put("employmentId",resultEmployList.get(0).getEmploymentId());
            amArchiveBOList = amArchiveService.queryAmArchiveList(param);
        }

        Map<String, Object> resultMap = new HashMap<String, Object>();

        //客户信息
        resultMap.put("customerInfo",customBO);
        //雇员信息
        resultMap.put("amEmpTaskBO",employeeBO);

        //退工信息
        if(null!=listResignBO&&listResignBO.size()>0){
            amResignBO = listResignBO.get(0);
        }
        resultMap.put("resignBO",amResignBO);

        if(null!=amRemarkBOPageRows)
        {
            resultMap.put("amRemarkBo",amRemarkBOPageRows);
        }

        if(null!=amRemarkBOPageRows1&&amRemarkBOPageRows1.getRows().size()>0)
        {
            resultMap.put("amRemarkBo1",amRemarkBOPageRows1);
        }

        if(null!=amRemarkBOPageRows2&&amRemarkBOPageRows2.getRows().size()>0)
        {
            resultMap.put("amRemarkBo2",amRemarkBOPageRows2);
        }

        if(null!= resultEmployList&&resultEmployList.size()>0)
        {
            resultMap.put("amEmploymentBO",resultEmployList.get(0));
        }

        if(null!=amArchiveBOList&&amArchiveBOList.size()>0)
        {
            resultMap.put("amArchaiveBo",amArchiveBOList.get(0));
        }

        return JsonResultKit.of(resultMap);
    }

    @RequestMapping("/saveAmResign")
    public JsonResult<Boolean> saveAmResign(AmResignBO bo) {
        AmResign entity = new AmResign();
        BeanUtils.copyProperties(bo,entity);

        LocalDateTime now = LocalDateTime.now();
        if(entity.getResignId()==null){
            entity.setCreatedTime(now);
            entity.setModifiedTime(now);
            entity.setCreatedBy("sys");
            entity.setModifiedBy("sys");
        }else{
            entity.setModifiedTime(now);
            entity.setModifiedBy("sys");
        }

        if(!StringUtil.isEmpty(bo.getResignFeedback1()))
        {
            AmEmpTask amEmpTask = taskService.selectById(bo.getEmpTaskId());
            amEmpTask.setTaskStatus(Integer.parseInt(bo.getResignFeedback1()));
            taskService.insertOrUpdate(amEmpTask);
        }


        boolean result =  business.insertOrUpdate(entity);
        return JsonResultKit.of(result);
    }

    @RequestMapping("/bindEmploymentId")
    public JsonResult  bindEmploymentId(AmResignBO bo) {

        Map<String, Object> resultMap = new HashMap<String, Object>();
        AmResign entity = new AmResign();
        BeanUtils.copyProperties(bo,entity);

        if(bo.getEmploymentId()==null)
        {
            boolean result = false;
            Map<String,Object> param = new HashMap<>();
            param.put("employmentId",bo.getMatchEmployIndex());
            List<AmEmploymentBO> list =  amEmploymentService.queryAmEmployment(param);

            if(null!=list&&list.size()>0)
            {
                try {
                    entity.setEmploymentId(Long.parseLong(bo.getMatchEmployIndex()));
                } catch (NumberFormatException e) {
                    resultMap.put("result","用工序号格式不对");
                    return JsonResultKit.of(resultMap);
                }

                LocalDateTime now = LocalDateTime.now();
                if(entity.getResignId()==null){
                    entity.setCreatedTime(now);
                    entity.setModifiedTime(now);
                    entity.setCreatedBy("sys");
                    entity.setModifiedBy("sys");
                }else{
                    entity.setModifiedTime(now);
                    entity.setModifiedBy("sys");
                }

                result =  business.insertOrUpdate(entity);

                if(result){
                    resultMap.put("result",result);
                }else{
                    resultMap.put("result","绑定失败");
                }
            }else {
                resultMap.put("result","对应用工序号不重在");
            }

        }else{
            resultMap.put("result","对应用工序号已经重在");
        }

        return JsonResultKit.of(resultMap);

    }


    /**
     * 雇员社保查询查询导出
     */
    @RequestMapping("/resignSearchExportOpt")
    public void resignSearchExportOpt(HttpServletResponse response, AmResignBO amResignBO) {

        List<String> param = new ArrayList<String>();

        if (!StringUtil.isEmpty(amResignBO.getParams())) {
            String arr[] = amResignBO.getParams().split(",");
            for (int i = 0; i < arr.length; i++) {
                param.add(arr[i]);
            }
        }

        amResignBO.setParam(param);

        if (null != amResignBO.getTaskStatus() && amResignBO.getTaskStatus() == 0) {
            amResignBO.setTaskStatus(null);
        }

        Date date = new Date();
        String fileNme = "退工任务单_"+ StringUtil.getDateString(date)+".xls";

        List<resignSearchExportOpt> opts = business.queryAmResignList(amResignBO);

        ExcelUtil.exportExcel(opts,resignSearchExportOpt.class,fileNme,response);
    }
}
