package com.ciicsh.gto.adsupportcenter.employcommandservice.host.controller;

import com.alibaba.fastjson.JSONObject;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.bo.*;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.business.*;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.entity.AmResign;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
import com.ciicsh.gto.afsupportcenter.util.web.controller.BasicController;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResult;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResultKit;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
                amEmpTaskCountBO.setRefuseFailed(amResignBO.getCount());
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
            }else if(7==status){
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
}
