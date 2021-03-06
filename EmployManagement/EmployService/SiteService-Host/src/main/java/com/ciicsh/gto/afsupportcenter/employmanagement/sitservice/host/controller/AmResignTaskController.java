package com.ciicsh.gto.afsupportcenter.employmanagement.sitservice.host.controller;

import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.bo.*;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.business.*;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.business.utils.CommonApiUtils;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.business.utils.ReasonUtil;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.business.utils.TaskCommonUtils;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.custom.resignSearchExportOpt;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.dto.AmEmpDispatchExportPageDTO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.dto.AmEmpExplainExportDTO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.dto.AmEmpExplainExportPageDTO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.entity.AmEmpTask;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.entity.AmEmployeChange;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.entity.AmEmployment;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.entity.AmResign;
import com.ciicsh.gto.afsupportcenter.employmanagement.sitservice.host.util.WordUtils;
import com.ciicsh.gto.afsupportcenter.util.ExcelUtil;
import com.ciicsh.gto.afsupportcenter.util.StringUtil;
import com.ciicsh.gto.afsupportcenter.util.interceptor.authenticate.UserContext;
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
@RequestMapping("/api/employservice/amResignTask")
public class AmResignTaskController extends BasicController<IAmResignService> {

    @Autowired
    private IAmRemarkService amRemarkService;

    @Autowired
    private IAmEmploymentService amEmploymentService;

    @Autowired
    private IAmArchiveService amArchiveService;

    @Autowired
    private IAmEmpTaskService taskService;

    @Autowired
    private IAmEmpMaterialService amEmpMaterialService;

    @Autowired
    private  AmEmpEmployeeService amEmpEmployeeService;

    @Autowired
    private  IAmEmpCustomService amEmpCustomService;

    @Autowired
    private CommonApiUtils employeeInfoProxy;

    @Autowired
    private IAmEmployeChangeService  amEmployeChangeService;



    @RequestMapping("/queryAmResign")
    public JsonResult<PageRows>  queryAmResign(PageInfo pageInfo){
        PageRows<AmResignBO> result = business.queryAmResign(pageInfo);

       List<AmResignBO> data = result.getRows();

       for(AmResignBO amResignBO:data)
       {
           if(!StringUtil.isEmpty(amResignBO.getResignFeedback())){
               amResignBO.setResignFeedback(ReasonUtil.getTgfk(amResignBO.getResignFeedback()));
           }
           if(!StringUtil.isEmpty(amResignBO.getEmployFeedback())){
               amResignBO.setEmployFeedback(ReasonUtil.getYgfk(amResignBO.getEmployFeedback()));
           }
           if(amResignBO!=null&&amResignBO.getEmployCode()!=null)
           {
               if(amResignBO.getEmployCode()==1){//是独立

               }else if(amResignBO.getEmployCode()==2){
                   amResignBO.setTitle("中智上海经济技术合作公司");
               }else if(amResignBO.getEmployCode()==3){
                   amResignBO.setCici("上海中智项目外包咨询服务有限公司");
               }
           }
           if(!StringUtil.isEmpty(amResignBO.getRefuseSpecial()))
           {
               amResignBO.setRefuseSpecial("有");

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
        int otherNum =0;
        for(int i=0;i<list.size();i++)
        {
            AmResignBO amResignBO = list.get(i);
            int status = amResignBO.getTaskStatus();
            if(99==status){
                amEmpTaskCountBO.setNoFeedback(amResignBO.getCount());
                num = num + amResignBO.getCount();
            }else if(98==status){
                amEmpTaskCountBO.setRefuseWaitFinished(amResignBO.getCount());
                num = num + amResignBO.getCount();
            }else if(1==status){
                amEmpTaskCountBO.setRefuseFinished(amResignBO.getCount());
                num = num + amResignBO.getCount();
            }else if(2==status){
                amEmpTaskCountBO.setRefuseBeforeWithFile(amResignBO.getCount());
                num = num + amResignBO.getCount();
            }else if(3==status){
                amEmpTaskCountBO.setRefuseTicketStampNoReturn(amResignBO.getCount());
                num = num + amResignBO.getCount();
            }else if(4==status){
                amEmpTaskCountBO.setRefuseFailed(amResignBO.getCount());
                num = num + amResignBO.getCount();
            }else if(5==status){
                amEmpTaskCountBO.setBeforeBatchNeedRefuse(amResignBO.getCount());
                num = num + amResignBO.getCount();
            }else if(66==status){
                amEmpTaskCountBO.setSystemCancel(amResignBO.getCount());
                num = num + amResignBO.getCount();
            }else{
                otherNum = otherNum+amResignBO.getCount();
                amEmpTaskCountBO.setOther(otherNum);
                num = num + amResignBO.getCount();
            }
            amEmpTaskCountBO.setAmount(num);

        }
        temp.add(amEmpTaskCountBO);
        AmResignCollection  amResignCollection = new AmResignCollection ();
        amResignCollection.setRow(temp);

        AmResignBO amResignBOCount = pageInfo.toJavaObject(AmResignBO.class);
        AmTaskStatusBO amTaskStatusBO = new AmTaskStatusBO();
        List<String> param = new ArrayList<String>();
        if (!StringUtil.isEmpty(amResignBOCount.getParams())) {
            String arr[] = amResignBOCount.getParams().split(",");
            for (int i = 0; i < arr.length; i++) {
                param.add(arr[i]);
            }
        }
        amResignBOCount.setParam(param);
        if(StringUtil.isEmpty(amResignBOCount.getJob()))
        {
            amResignBOCount.setJob("Y");
            List<AmResignBO> jobList = business.jobCount(amResignBOCount);
            amTaskStatusBO.setJob(jobList.get(0).getCount());
            amResignBOCount.setJob("N");
            List<AmResignBO> jobListOther = business.jobCount(amResignBOCount);
            amTaskStatusBO.setNoJob(jobListOther.get(0).getCount());
        }else{
            List<AmResignBO> jobList = business.jobCount(amResignBOCount);
            if("Y".equals(amResignBOCount.getJob()))
            {
                amTaskStatusBO.setJob(jobList.get(0).getCount());
                amResignBOCount.setJob("N");
                List<AmResignBO> jobListOther = business.jobCount(amResignBOCount);
                amTaskStatusBO.setNoJob(jobListOther.get(0).getCount());
            }else{
                amTaskStatusBO.setNoJob(jobList.get(0).getCount());
                amResignBOCount.setJob("Y");
                List<AmResignBO> jobListOther = business.jobCount(amResignBOCount);
                amTaskStatusBO.setJob(jobListOther.get(0).getCount());
            }
        }
        amResignCollection.setAmTaskStatusBO(amTaskStatusBO);

        return  JsonResultKit.of(amResignCollection);
    }

    @RequestMapping("/queryAmResignDetail")
    public JsonResult queryAmResignDetail(AmTaskParamBO amTaskParamBO){

        amTaskParamBO.setResign(true);

        AmEmpEmployeeBO amEmpEmployeeBO = null;

        AmCustomBO amCustomBO = null;

        AmEmployment amEmployment = null;

        if(amTaskParamBO.getEmploymentId()!=null){
            /**
             * 如果重在用工主键，则通过用工主键找对应的任务
             * 单主键查询雇佣信息
             */
            amEmployment = amEmploymentService.selectById(amTaskParamBO.getEmploymentId());

            amEmpEmployeeBO = amEmpEmployeeService.queryAmEmployeeByTaskId(amTaskParamBO.getEmpTaskId(),1);
            if(amEmpEmployeeBO==null){
                amEmpEmployeeBO = amEmpEmployeeService.queryAmEmployeeByTaskId(amEmployment.getEmpTaskId(),1);
            }

            if(null!=amEmpEmployeeBO)
            {
                amCustomBO = amEmpCustomService.getCustom(amEmpEmployeeBO.getEmpTaskId());
            }

        }else {

            amEmpEmployeeBO = amEmpEmployeeService.queryAmEmployeeByTaskId(amTaskParamBO.getEmpTaskId(),1);

            amCustomBO = amEmpCustomService.getCustom(amTaskParamBO.getEmpTaskId());
        }

        Map<String,Object> params = new HashMap<>();
        params.put("employeeId",amTaskParamBO.getEmployeeId());
        params.put("companyId",amTaskParamBO.getCompanyId());
        params.put("remarkType",amTaskParamBO.getRemarkType());
        params.put("empTaskId",amTaskParamBO.getEmpTaskId());
        params.put("empTaskResignId",amTaskParamBO.getEmpTaskId());

        AmRemarkBO amRemarkBO = new AmRemarkBO();

        List<AmResignBO> listResignBO = business.queryAmResignDetail(params);

        //退工备注
        amRemarkBO.setRemarkType(3);
        amRemarkBO.setEmpTaskId(amTaskParamBO.getEmpTaskId());//退工任务单id
        List<AmRemarkBO>  amRemarkBOList = amRemarkService.getAmRemakList(amRemarkBO);

        List<AmRemarkBO>  amRemarkBOList1 = null;
        List<AmRemarkBO>  amRemarkBOList2 = null;
        //用工备注
        if(amEmployment!=null){
            amRemarkBO.setRemarkType(1);
            amRemarkBO.setEmpTaskId(amEmployment.getEmpTaskId());//用工任务单Id
            amRemarkBOList1 = amRemarkService.getAmRemakList(amRemarkBO);

            //档案备注
            amRemarkBO.setRemarkType(2);
            amRemarkBOList2 = amRemarkService.getAmRemakList(amRemarkBO);
        }


        //用工信息
        AmEmploymentBO amEmploymentBO = new AmEmploymentBO();
        if(amEmployment!=null){
            BeanUtils.copyProperties(amEmployment,amEmploymentBO);

        }else{
            //否则取第一条
            List<AmEmploymentBO> resultEmployList = amEmploymentService.queryAmEmploymentResign(params);
            if(null!= resultEmployList&&resultEmployList.size()>0)
            {
                amEmploymentBO = resultEmployList.get(0);
            }
        }

        //用工档案
        List<AmArchiveBO> amArchiveBOList = null;
        AmArchiveBO amArchiveBO = new AmArchiveBO();
        params.put("employmentId",amEmploymentBO.getEmploymentId());

        amArchiveBOList = amArchiveService.queryAmArchiveList(params);
        if(amArchiveBOList!=null&&amArchiveBOList.size()>0){
            amArchiveBO = amArchiveBOList.get(0);
        }

        Map<String, Object> resultMap = new HashMap<String, Object>();

        //客户信息
        if(null!=amCustomBO){
            resultMap.put("customerInfo",amCustomBO);
        }

        //雇员信息
        if(null!=amEmpEmployeeBO){
            resultMap.put("amEmpTaskBO",amEmpEmployeeBO);
        }

        //退工备注
        if(null!=amRemarkBOList&&amRemarkBOList.size()>0)
        {
            resultMap.put("amRemarkBo",amRemarkBOList);
        }
        //用工备注
        if(null!=amRemarkBOList1&&amRemarkBOList1.size()>0)
        {
            resultMap.put("amRemarkBo1",amRemarkBOList1);
        }
        //档案备注
        if(null!=amRemarkBOList2&&amRemarkBOList2.size()>0)
        {
            resultMap.put("amRemarkBo2",amRemarkBOList2);
        }

        //用工信息
        if(null!=amEmploymentBO&&!StringUtil.isEmpty(amEmploymentBO.getEmployStyle()))
        {
            amEmploymentBO.setEmployStyle(ReasonUtil.getYgfs(amEmploymentBO.getEmployStyle()));
        }
        //用工信息里边的用工备注  取最新一条
        if(null!= amRemarkBOList1&& amRemarkBOList1.size()>0)
        {
            amEmploymentBO.setEmployNotes(amRemarkBOList1.get(0).getRemarkContent());
        }
        //用工信息
        resultMap.put("amEmploymentBO",amEmploymentBO);
        //档案
        resultMap.put("amArchaiveBo",amArchiveBO);

        //退工信息
        AmResignBO amResignBO = new AmResignBO();
        AmEmpTask amEmpTask = taskService.selectById(amTaskParamBO.getEmpTaskId());
        AmEmployeChange amEmployeChange = amEmployeChangeService.getEmployeeChange(amTaskParamBO.getEmpTaskId());
        java.text.DateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
        if(null!=amEmpTask){
            if(null!=amEmpTask.getOutDate()){
                amResignBO.setOutDate(sdf.format(amEmpTask.getOutDate()));
            }
            amResignBO.setOutReason(amEmpTask.getOutReason());
        }
        if(null!=listResignBO&&listResignBO.size()>0){
            amResignBO = listResignBO.get(0);

            amResignBO.setHandleType(amEmploymentBO.getHandleType());
            amResignBO.setEmployFeedback(amEmploymentBO.getEmployFeedback());

            amResignBO.setYuliuDocNum(amArchiveBO.getYuliuDocNum());
            amResignBO.setDocNum(amArchiveBO.getDocNum());
            amResignBO.setDocType(amArchiveBO.getDocType());
            amResignBO.setYuliuDocType(amArchiveBO.getYuliuDocType());
            amResignBO.setArchiveCardState(amArchiveBO.getArchiveCardState());
            amResignBO.setArchivePlace(amArchiveBO.getArchivePlace());
            amResignBO.setArchivePlaceAdditional(amArchiveBO.getArchivePlaceAdditional());
            amResignBO.setEmployDocPaymentTo(amArchiveBO.getEmployDocPaymentTo());
            amResignBO.setStorageDate(amArchiveBO.getStorageDate());
            amResignBO.setDiaodangFeedback(amArchiveBO.getDiaodangFeedback());

            amResignBO.setArchiveDirection(amEmpEmployeeBO==null?"":amEmpEmployeeBO.getArchiveDirection());
            if(amResignBO.getJobCentreFeedbackDate()!=null){
                amResignBO.setHandRead(true);
            }
            /**
             * 如果存在更新则更新
             */
            if(amEmployeChange!=null)
            {
                amResignBO.setIsChange(1);
                if("all".equals(amEmployeChange.getType()))
                {
                    if(null!=amEmployeChange.getOutDate()){
                        amResignBO.setOutDate(sdf.format(amEmployeChange.getOutDate()));
                    }
                    amResignBO.setOutReason(amEmployeChange.getOutReason());
                }else if("time".equals(amEmployeChange.getType())){
                    if(null!=amEmployeChange.getOutDate()){
                        amResignBO.setOutDate(sdf.format(amEmployeChange.getOutDate()));
                    }
                }else{
                    amResignBO.setOutReason(amEmployeChange.getOutReason());
                }
            }

        }else{

            amResignBO.setHandleType(amEmploymentBO.getHandleType());
            amResignBO.setEmployFeedback(amEmploymentBO.getEmployFeedback());
            amResignBO.setEmploymentId(amEmploymentBO.getEmploymentId());

            amResignBO.setYuliuDocNum(amArchiveBO.getYuliuDocNum());
            amResignBO.setDocNum(amArchiveBO.getDocNum());
            amResignBO.setDocType(amArchiveBO.getDocType());
            amResignBO.setYuliuDocType(amArchiveBO.getYuliuDocType());
            amResignBO.setArchiveCardState(amArchiveBO.getArchiveCardState());
            amResignBO.setArchivePlace(amArchiveBO.getArchivePlace());
            amResignBO.setArchivePlaceAdditional(amArchiveBO.getArchivePlaceAdditional());

            amResignBO.setEmployDocPaymentTo(amArchiveBO.getEmployDocPaymentTo());
            amResignBO.setStorageDate(amArchiveBO.getStorageDate());
            amResignBO.setDiaodangFeedback(amArchiveBO.getDiaodangFeedback());

            amResignBO.setArchiveDirection(amEmpEmployeeBO==null?"":amEmpEmployeeBO.getArchiveDirection());



        }
        amResignBO.setFirstInDate(amEmpEmployeeBO==null?"":amEmpEmployeeBO.getFirstInDateStr());
        String code = amArchiveBO.getEmployFeedback();
        if(!StringUtil.isEmpty(code)){
            amResignBO.setEmployFeedback(ReasonUtil.getYgfk(code));
        }

        if(amResignBO.getEmploymentId()!=null){
            amResignBO.setMatchEmployIndex(amResignBO.getEmploymentId().toString());
        }
        amResignBO.setOldResignFeedback(amResignBO.getResignFeedback());
        amResignBO.setPost(amArchiveBO.getPost());
        amResignBO.setPostSaver(amArchiveBO.getPostSaver());
        resultMap.put("resignBO",amResignBO);

        UserInfoBO userInfoBO = new UserInfoBO();
        userInfoBO.setUserName(ReasonUtil.getUserName());

        resultMap.put("userInfo",userInfoBO);

        return JsonResultKit.of(resultMap);
    }

    @RequestMapping("/saveAmResign")
    public JsonResult<AmResign> saveAmResign(AmResignBO bo) {

        AmResign result =  business.saveAmResign(bo);

        return JsonResultKit.of(result);
    }

    @RequestMapping("/saveAmReturn")
    public JsonResult<AmResign> saveAmReturn(AmResignBO bo) {

        AmResign result =  business.saveAmReturn(bo);

        return JsonResultKit.of(result);
    }

    @RequestMapping("/saveAmSend")
    public JsonResult<Boolean> saveAmSend(AmPostBO amPostBO) {

        Boolean result =  business.saveAmSend(amPostBO);

        return JsonResultKit.of(result);
    }

    @RequestMapping("/bindEmploymentId")
    public JsonResult  bindEmploymentId(AmResignBO bo) {

        Map<String, Object> resultMap = new HashMap<String, Object>();
        AmResign entity = new AmResign();
        BeanUtils.copyProperties(bo,entity);

        AmEmployment amEmployment = amEmploymentService.selectById(Long.parseLong(bo.getMatchEmployIndex()));

        if(amEmployment==null)
        {
            resultMap.put("result","用工序号查询不到");
            return JsonResultKit.of(resultMap);
        }

        AmEmpTask amEmpTask = taskService.selectById(bo.getEmpTaskId());

        if(!(amEmployment.getEmployeeId().equals(amEmpTask.getEmployeeId())))
        {
            resultMap.put("result","用工序号错误");
            return JsonResultKit.of(resultMap);
        }


        try {
            entity.setEmploymentId(Long.parseLong(bo.getMatchEmployIndex()));
        } catch (NumberFormatException e) {
            resultMap.put("result","用工序号格式不对");
            return JsonResultKit.of(resultMap);
        }

        boolean result = false;

        LocalDateTime now = LocalDateTime.now();
        if(entity.getResignId()==null){
            entity.setCreatedTime(now);
            entity.setModifiedTime(now);
            entity.setCreatedBy(ReasonUtil.getUserId());
            entity.setModifiedBy(ReasonUtil.getUserId());
        }else{
            entity.setModifiedTime(now);
            entity.setModifiedBy(ReasonUtil.getUserId());
        }

        result =  business.insertOrUpdate(entity);

        if(result){
            resultMap.put("result",result);
            resultMap.put("entity",entity);
        }else{
            resultMap.put("result","绑定失败");
        }

        return JsonResultKit.of(resultMap);

    }


    /**
     * 雇员社保查询查询导出
     */
    @RequestMapping("/resignSearchExportOpt")
    public void resignSearchExportOpt(HttpServletResponse response, AmResignBO amResignBO) {

        List<String> param = new ArrayList<String>();
        List<String> orderParam = new ArrayList<String>();
        if(!StringUtil.isEmpty(amResignBO.getParams()))
        {
            String arr[] = amResignBO.getParams().split(",");
            for(int i=0;i<arr.length;i++) {
                if(arr[i].indexOf("desc")>0||arr[i].indexOf("asc")>0){
                    orderParam.add(arr[i]);
                }else {
                    param.add(arr[i]);
                }
            }
        }
        amResignBO.setParam(param);
        amResignBO.setOrderParam(orderParam);
        if(null!=amResignBO.getTaskStatus()&&amResignBO.getTaskStatus()==0){
            amResignBO.setTaskStatus(null);
        }

        if(null!=amResignBO.getTaskStatus()&&amResignBO.getTaskStatus()==6)
        {
            amResignBO.setTaskStatusOther(0);
        }

        Date date = new Date();
        String fileNme = "退工任务单_"+ StringUtil.getDateString(date)+".xls";

        List<resignSearchExportOpt> opts = business.queryAmResignList(amResignBO);

        for(resignSearchExportOpt temp:opts)
        {

            if(!StringUtil.isEmpty(temp.getResignFeedback())){
                temp.setResignFeedback(ReasonUtil.getTgfk(temp.getResignFeedback()));
            }
            if(!StringUtil.isEmpty(temp.getEmployFeedback())){
                temp.setEmployFeedback(ReasonUtil.getYgfk(temp.getEmployFeedback()));
            }
            if(temp!=null&&temp.getEmployCode()!=null)
            {
                if(temp.getEmployCode()==1){//是独立

                }else if(temp.getEmployCode()==2){
                    temp.setTitle("中智上海经济技术合作公司");
                }else if(temp.getEmployCode()==3){
                    temp.setTitle(temp.getTitle()+",上海中智项目外包咨询服务有限公司");
                }
            }
            if(!StringUtil.isEmpty(temp.getRefuseSpecial()))
            {
                temp.setRefuseSpecial("有");

            }
        }

        ExcelUtil.exportExcel(opts,resignSearchExportOpt.class,fileNme,response);
    }


    @RequestMapping("/saveAmResignBatch")
    public JsonResult<AmResignBO> saveAmResignBatch(AmResignBO bo) {

        Map<String,Object>  param = business.batchSaveResign(bo);
        List<AmEmpTask> amEmpTaskList = (List<AmEmpTask>)param.get("taskList");
        Boolean result = (Boolean) param.get("result");
        if(null!=result&&result)
        {
            for(AmEmpTask amEmpTask:amEmpTaskList)
            {
                Map<String,Object> variables = new HashMap<>();
                variables.put("status", true);
                variables.put("remark",ReasonUtil.getTgfk(bo.getResignFeedback()));
                String userName = "system";
                try {
                    userName = UserContext.getUser().getDisplayName();
                } catch (Exception e) {

                }
                variables.put("assignee",userName);
                if(null!=amEmpTask&&!"是".equals(amEmpTask.getChangeCompany()))
                {
                    variables.put("fire_material",true);
                }
                variables.put("empTaskId",amEmpTask.getEmpTaskId());
                try {
                    TaskCommonUtils.completeTask(amEmpTask.getTaskId(),employeeInfoProxy,variables);
                } catch (Exception e) {

                }
            }
        }
        if(param.get("message")!=null){
            bo.setRemark(param.get("message").toString());
        }
        return JsonResultKit.of(bo);
    }

    @RequestMapping("/batchResignCheck")
    public JsonResult  batchResignCheck(AmResignBO bo){
        Map<String,Object>  map = business.batchCheck(bo);
        return  JsonResultKit.of(map);
    }

    /**
     * 退工外来情况说明导出Word
     */
    @RequestMapping("/resignSearchExplainWord")
    public void resignSearchExplainWord(HttpServletResponse response, AmResignBO amResignBO) {
        // 中智大库
        List<AmEmpExplainExportPageDTO> dtoList = business.queryExportOptExplain(amResignBO,2);

        // 外包
        List<AmEmpExplainExportPageDTO> dtoList2 = business.queryExportOptExplain(amResignBO,3);

        //独立户
        List<AmEmpExplainExportPageDTO> dtoList3 = business.queryExportOptExplain(amResignBO);

        Integer count = 0;
        for (AmEmpExplainExportPageDTO dto:dtoList) {
            for (AmEmpExplainExportDTO d:dto.getList()) {
                if(d.getEmployeeName()!=null){
                    count++;
                }
            }
        }
        for (AmEmpExplainExportPageDTO dto:dtoList2) {
            for (AmEmpExplainExportDTO d:dto.getList()) {
                if(d.getEmployeeName()!=null){
                    count++;
                }
            }
        }
        for (AmEmpExplainExportPageDTO dto:dtoList3) {
            for (AmEmpExplainExportDTO d:dto.getList()) {
                if(d.getEmployeeName()!=null){
                    count++;
                }
            }
        }

        Map<String, Object> map = new HashMap<>();

        map.put("list",dtoList);
        map.put("list2",dtoList2);
        map.put("list3",dtoList3);
        map.put("count",count);

        try {
            WordUtils.exportMillCertificateWord(response,map,"外来情况说明","AM_EXPLAIN_TEMP.ftl");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 退工打印外来退工单导出Word
     */
    @RequestMapping("/resignSearchPrintReturnWord")
    public void resignSearchPrintReturnWord(HttpServletResponse response, AmResignBO amResignBO) {
        // 中智大库
        List<AmEmpDispatchExportPageDTO> dtoList = business.queryExportOptReturn(amResignBO,2,10);

        // 外包
        List<AmEmpDispatchExportPageDTO> dtoList2 = business.queryExportOptReturn(amResignBO,3,10);

        //独立户
        List<AmEmpDispatchExportPageDTO> dtoList3 = business.queryExportOptReturn(amResignBO,10);

        Integer count = 0;
        for (AmEmpDispatchExportPageDTO dto:dtoList) {
            count += dto.getList().size();
        }

        for (AmEmpDispatchExportPageDTO dto:dtoList2) {
            count += dto.getList().size();
        }

        for (AmEmpDispatchExportPageDTO dto:dtoList3) {
            count += dto.getList().size();
        }

        Map<String, Object> map = new HashMap<>();

        map.put("list",dtoList);
        map.put("list2",dtoList2);
        map.put("list3",dtoList3);
        map.put("count",count);

        try {
            WordUtils.exportMillCertificateWord(response,map,"退工档案登记表","AM_RETURN_FOREIGN_TEMP.ftl");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
