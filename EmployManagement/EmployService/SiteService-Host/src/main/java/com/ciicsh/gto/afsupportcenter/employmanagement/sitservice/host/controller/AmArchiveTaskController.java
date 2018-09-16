package com.ciicsh.gto.afsupportcenter.employmanagement.sitservice.host.controller;

import com.alibaba.fastjson.JSONObject;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.bo.*;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.business.*;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.business.utils.ReasonUtil;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.custom.archiveSearchExportOpt;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.dto.*;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.entity.AmArchiveUse;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.entity.AmEmpMaterial;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.entity.AmEmpTask;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.entity.AmInjury;
import com.ciicsh.gto.afsupportcenter.employmanagement.sitservice.host.util.WordUtils;
import com.ciicsh.gto.afsupportcenter.util.ExcelUtil;
import com.ciicsh.gto.afsupportcenter.util.StringUtil;
import com.ciicsh.gto.afsupportcenter.util.interceptor.authenticate.UserContext;
import com.ciicsh.gto.afsupportcenter.util.logService.LogApiUtil;
import com.ciicsh.gto.afsupportcenter.util.logService.LogMessage;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
import com.ciicsh.gto.afsupportcenter.util.web.controller.BasicController;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResult;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResultKit;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Created by zhangzhiwen on 2018/2/6.
 */

@RestController
@RequestMapping("/api/employservice/amArchiveTask")
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

    @Autowired
    private  AmEmpEmployeeService amEmpEmployeeService;

    @Autowired
    private  IAmEmpCustomService amEmpCustomService;

    @Autowired
    private LogApiUtil logApiUtil;

    @RequestMapping("/queryAmArchive")
    public JsonResult<PageRows> queryAmArchive(PageInfo pageInfo){
        PageRows<AmEmploymentBO> result = business.queryAmArchive(pageInfo);
        /*AmEmploymentBO param = pageInfo.toJavaObject(AmEmploymentBO.class);
        if(param.getLuyongHandleEnd()!=null)
        {
            List<AmEmploymentBO> temp = result.getRows();
            if(param.getLuyongHandleEnd())
            {
                for(AmEmploymentBO amEmploymentBO:temp)
                {
                    if(amEmploymentBO.getLuyongHandleEnd()==null||amEmploymentBO.getLuyongHandleEnd()==false){
                        temp.remove(amEmploymentBO);
                    }
                }
            }else{
                for(AmEmploymentBO amEmploymentBO:temp)
                {
                    if(null!=amEmploymentBO.getLuyongHandleEnd()&&amEmploymentBO.getLuyongHandleEnd())
                    {
                        temp.remove(amEmploymentBO);
                    }
                }
            }
            result.setRows(temp);
        }*/

        List<AmEmploymentBO> data = result.getRows();
        for(AmEmploymentBO amEmploymentBO:data)
        {
            if(!StringUtil.isEmpty(amEmploymentBO.getResignFeedback1())){
                amEmploymentBO.setResignFeedback1(ReasonUtil.getTgfk(amEmploymentBO.getResignFeedback1()));
            }

            if(!StringUtil.isEmpty(amEmploymentBO.getEmployFeedback())){
                amEmploymentBO.setEmployFeedback(ReasonUtil.getYgfk(amEmploymentBO.getEmployFeedback()));
            }

            if(amEmploymentBO!=null&&amEmploymentBO.getEmployCode()!=null)
            {
                if(amEmploymentBO.getEmployCode()==1){//是独立

                }else if(amEmploymentBO.getEmployCode()==2){
                    amEmploymentBO.setTitle("中智上海经济技术合作公司");
                }else if(amEmploymentBO.getEmployCode()==3){
                    amEmploymentBO.setCici("上海中智项目外包咨询服务有限公司");
                }
            }

            StringBuffer buf = new StringBuffer();
            buf.append(amEmploymentBO.getEmploySpecial()==null?"":amEmploymentBO.getEmploySpecial());
            buf.append(amEmploymentBO.getRefuseSpecial()==null?"":amEmploymentBO.getRefuseSpecial());
            buf.append(amEmploymentBO.getArchiveSpecial()==null?"":amEmploymentBO.getArchiveSpecial());
            if(!StringUtil.isEmpty(buf.toString()))
            {
                amEmploymentBO.setArchiveSpecial("有");
            }
        }

        return JsonResultKit.of(result);
    }

    @RequestMapping("/queryAmEmpTaskCount")
    public  JsonResult<AmEmpTaskCollection>  taskCountEmployee(PageInfo pageInfo){

        List<AmEmploymentBO> list = business.taskCountEmployee(pageInfo);

        AmEmpTaskCountBO amEmpTaskCountBO = new AmEmpTaskCountBO();
        List<AmEmpTaskCountBO>  temp = new ArrayList<>();
        amEmpTaskCountBO.setAmount(list.size());
        int num =0;
        int otherNum =0;
        for(int i=0;i<list.size();i++)
        {
            AmEmploymentBO amEmploymentBO = list.get(i);
            int status = amEmploymentBO.getTaskStatus();
            if(1==status){
                amEmpTaskCountBO.setNoSign(amEmploymentBO.getCount());
                num = num + amEmploymentBO.getCount();
            }else if(10==status){
                amEmpTaskCountBO.setNoRecord(amEmploymentBO.getCount());
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
            }else{
                otherNum = otherNum + amEmploymentBO.getCount();
                amEmpTaskCountBO.setOther(otherNum);
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
        int otherNum =0;
        for(int i=0;i<list.size();i++)
        {
            AmEmploymentBO amEmploymentBO = list.get(i);
            int status = amEmploymentBO.getTaskStatus();
            if(99==status){
                amEmpTaskCountBO.setNoFeedback(amEmploymentBO.getCount());
                num = num + amEmploymentBO.getCount();
            }else if(98==status){
                amEmpTaskCountBO.setRefuseWaitFinished(amEmploymentBO.getCount());
                num = num + amEmploymentBO.getCount();
            }else if(1==status){
                amEmpTaskCountBO.setRefuseFinished(amEmploymentBO.getCount());
                num = num + amEmploymentBO.getCount();
            }else if(2==status){
                amEmpTaskCountBO.setRefuseBeforeWithFile(amEmploymentBO.getCount());
                num = num + amEmploymentBO.getCount();
            }else if(3==status){
                amEmpTaskCountBO.setRefuseTicketStampNoReturn(amEmploymentBO.getCount());
                num = num + amEmploymentBO.getCount();
            }else if(4==status){
                amEmpTaskCountBO.setRefuseFailed(amEmploymentBO.getCount());
                num = num + amEmploymentBO.getCount();
            }else if(5==status){
                amEmpTaskCountBO.setBeforeBatchNeedRefuse(amEmploymentBO.getCount());
                num = num + amEmploymentBO.getCount();
            }else{
                otherNum = otherNum+amEmploymentBO.getCount();
                amEmpTaskCountBO.setOther(otherNum);
                num = num + amEmploymentBO.getCount();
            }
            amEmpTaskCountBO.setAmount(num);

        }
        temp.add(amEmpTaskCountBO);
        AmResignCollection  amResignCollection = new AmResignCollection ();
        amResignCollection.setRow(temp);
        return  JsonResultKit.of(amResignCollection);
    }

    @RequestMapping("/queryDocSeqByDocType")
    public JsonResult queryDocSeqByDocType(AmArchiveDocSeqBO bo){
        Map<String, Object> map = new HashMap<>();
        if(bo.getDocType()==null){
            map.put("docBo",bo);
            return JsonResultKit.of(map);
        }
        AmArchiveDocSeqBO result = amArchiveService.queryAmArchiveDocTypeByTypeAndDocType(bo.getType(),bo.getDocType());
        map.put("docBo", result);
        return JsonResultKit.of(map);
    }

    @RequestMapping("/queryDocSeqList")
    public JsonResult queryDocSeqList(AmArchiveDocSeqBO bo){
        List<AmArchiveDocSeqBO> boList = amArchiveService.queryAmArchiveDocTypeByType(bo.getType());
        Map<String, Object> map = new HashMap<>();
        map.put("docList", boList);
        return JsonResultKit.of(map);
    }

    @RequestMapping("/archiveDetailInfoQuery")
    public JsonResult archiveDetailInfoQuery(AmTaskParamBO amTaskParamBO){

        /**
         * 获取雇员信息
         */
        AmEmpEmployeeBO amEmpEmployeeBO = amEmpEmployeeService.queryAmEmployeeByTaskId(amTaskParamBO.getEmpTaskId(),2);

        AmCustomBO amCustomBO = amEmpCustomService.getCustom(amTaskParamBO.getEmpTaskId());

        AmResignBO amResignBO = new AmResignBO();

        Map<String,Object> params = new HashMap<>();
        params.put("employeeId",amTaskParamBO.getEmployeeId());
        params.put("remarkType",amTaskParamBO.getRemarkType());
        params.put("empTaskId",amTaskParamBO.getEmpTaskId());
        params.put("employmentId",amTaskParamBO.getEmploymentId());
        params.put("companyId",amTaskParamBO.getCompanyId());
        params.put("operateType",new Integer(2));
        params.put("empTaskResignId",amTaskParamBO.getEmpTaskResignId());

        //用工档案
        List<AmArchiveBO> amArchiveBOList = amArchiveService.queryAmArchiveList(params);

        //用工备注
        AmRemarkBO queryBo = new AmRemarkBO();
        queryBo.setRemarkType(1);
        queryBo.setEmpTaskId(amTaskParamBO.getEmpTaskId());
        List<AmRemarkBO> amRemarkBOList = amRemarkService.getAmRemakList(queryBo);

        //档案备注
        queryBo.setRemarkType(2);
        List<AmRemarkBO> archiveAmRemarkBOList = amRemarkService.getAmRemakList(queryBo);

        //退工材料字典
        List<AmEmpMaterialBO> resultMaterial = iAmEmpMaterialService.queryMaterialDicList();

        //用工信息
        List<AmEmploymentBO> resultEmployList = amEmploymentService.queryAmEmployment(params);

        List<AmResignBO> listResignBO = amResignService.queryAmResignDetail(params);

        //退工信息
        if(null!=listResignBO&&listResignBO.size()>0){
            AmResignBO resignBO = listResignBO.get(0);
            if(resignBO.getIsFinish()!=null&&1==resignBO.getIsFinish())
            {
                amResignBO = listResignBO.get(0);
                if(!StringUtil.isEmpty(amResignBO.getResignFeedback())){
                    amResignBO.setResignFeedback(ReasonUtil.getTgfk(amResignBO.getResignFeedback()));
                }
                if(!StringUtil.isEmpty(amResignBO.getIfLaborManualReturn())){
                    amResignBO.setIfLaborManualReturnStr(ReasonUtil.getIsTj(amResignBO.getIfLaborManualReturn().toString()));
                }

                AmEmpTask amEmpTask = taskService.selectById(amTaskParamBO.getEmpTaskResignId());

                if(null!=amEmpTask){
                    java.text.DateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
                    if(null!=amEmpTask.getOutDate()){
                        amResignBO.setOutDate(sdf.format(amEmpTask.getOutDate()));
                    }
                    amResignBO.setOutReason(amEmpTask.getOutReason());
                }
            }

        }

        AmEmploymentBO amEmploymentBO = new AmEmploymentBO();
        if(null!=resultEmployList&&resultEmployList.size()>0){
            amEmploymentBO = resultEmployList.get(0);
        }

        if(!StringUtil.isEmpty(amEmploymentBO.getEmployStyle()))
        {
            amEmploymentBO.setEmployStyle(ReasonUtil.getYgfs(amEmploymentBO.getEmployStyle()));
        }

        //用工信息里边的用工备注  取最新一条
        if(null!= amRemarkBOList&& amRemarkBOList.size()>0)
        {
            amEmploymentBO.setEmployNotes(amRemarkBOList.get(0).getRemarkContent());
        }

        Map<String, Object> resultMap = new HashMap<>();
        //客户信息
        if(null!=amCustomBO){
            resultMap.put("customerInfo",amCustomBO);
        }
        //雇员信息
        if(null!=amEmpEmployeeBO){
            resultMap.put("amEmpTaskBO",amEmpEmployeeBO);
        }

        resultMap.put("resignBO",amResignBO);

        // 预留档案类别
        List<AmArchiveDocSeqBO> boList = amArchiveService.queryAmArchiveDocTypeByType(1);
        List<AmArchiveDocSeqBO> boList2 = amArchiveService.queryAmArchiveDocTypeByType(2);
        resultMap.put("docSeqList",boList);
        resultMap.put("docSeqList2",boList2);
        if(null!=amArchiveBOList&&amArchiveBOList.size()>0)
        {
            AmArchiveBO  amArchiveBO = amArchiveBOList.get(0);
            AmArchiveDTO amArchiveDTO = new AmArchiveDTO();
            resultMap.put("amArchaiveBo",amArchiveBO);
            BeanUtils.copyProperties(amArchiveBO,amArchiveDTO);

        }
        try {
            AmInjuryBO amInjuryBO = new AmInjuryBO();
            amInjuryBO.setEmpTaskId(amTaskParamBO.getEmpTaskId());
            List<AmInjuryBO>  amInjuryBOList = amInjuryService.queryAmInjury(amInjuryBO);
            if(null!=amInjuryBOList&&amInjuryBOList.size()>0)
            {
                resultMap.put("amInjuryBOList",amInjuryBOList);
            }
        } catch (Exception e) {

        }

        resultMap.put("amEmploymentBO",amEmploymentBO);

        //档案备注
        if(null!=archiveAmRemarkBOList&&archiveAmRemarkBOList.size()>0)
        {
            resultMap.put("amRemarkBo",archiveAmRemarkBOList);
        }

        //退工归还材料签收
        AmEmpMaterialBO amEmpMaterialBO = new AmEmpMaterialBO();
        amEmpMaterialBO.setEmpTaskId(amTaskParamBO.getEmpTaskResignId());
        amEmpMaterialBO.setOperateType(2);
        List<AmEmpMaterialBO> amEmpMaterialBOList = amEmpMaterialService.queryAmEmpMaterialList(amEmpMaterialBO);
        //退工归还材料签收
        if(null!=amEmpMaterialBOList&&amEmpMaterialBOList.size()>0){
            resultMap.put("materialList",amEmpMaterialBOList);
            // 材料流转记录
            List<AmEmpMaterialOperationLogBO> materialLogList = amEmpMaterialService.queryAmEmpMaterialLogList(amEmpMaterialBO);
            resultMap.put("materialLogList",materialLogList);
        }
        //退工材料字典
        resultMap.put("resultMaterial",resultMaterial);

        UserInfoBO userInfoBO = new UserInfoBO();
        userInfoBO.setUserName(ReasonUtil.getUserName());
        resultMap.put("userInfo",userInfoBO);

        return  JsonResultKit.of(resultMap);
    }

    @Transactional(
        rollbackFor = {Exception.class}
    )
    @PostMapping("/saveAmEmpMaterial")
    public JsonResult  saveAmEmpMaterial(@RequestBody List<AmEmpMaterial> list) {
        String userId = "System";
        String userName = "System";
        try {
            userId = UserContext.getUserId();
            userName = UserContext.getUser().getDisplayName();
        } catch (Exception e) {

        }
        Map<String, Object> resultMap = new HashMap<>();
        if(null!=list&&list.size()>0){
            // 有签收人就不用保存了
            AmEmpMaterial amEmpMaterial = list.get(0);
            AmEmpMaterialBO amEmpMaterialBO = new AmEmpMaterialBO();
            BeanUtils.copyProperties(amEmpMaterial,amEmpMaterialBO);
//            List<AmEmpMaterialBO> list1 = amEmpMaterialService.queryAmEmpMaterialList(amEmpMaterialBO);
//            for(AmEmpMaterialBO amEmpMaterialBO1:list1){
//                if(!StringUtil.isEmpty(amEmpMaterialBO1.getReceiveName()))
//                {
//                    resultMap.put("data",2);
//                    resultMap.put("result",list1);
//                    return JsonResultKit.of(resultMap);
//                }
//            }
        }
        List<AmEmpMaterial>  data = new ArrayList<AmEmpMaterial>();
        for(AmEmpMaterial bo:list)
        {
//            if(bo.getReceiveName() != null){
//                return JsonResultKit.of(2);
//            }
            bo.setOperateType(2);
            LocalDateTime now = LocalDateTime.now();
            bo.setCreatedTime(now);
            bo.setModifiedTime(now);
            bo.setCreatedBy(userId);
            bo.setModifiedBy(userId);
            bo.setSubmitterDate(now);
            if(bo.getEmpMaterialId()==null){
                data.add(bo);
            }

        }
        boolean result = false;
        try {
            result = amEmpMaterialService.insertOrUpdateBatch(data);
            // 退工归还材料签收 提交材料操作流水日志
            if(result){
                AmEmpMaterialOperationLogBO bo = new AmEmpMaterialOperationLogBO();
                bo.setEmpTaskId(data.get(0).getEmpTaskId());
                bo.setOperationTime(LocalDateTime.now());
                bo.setOperationType(3);
                bo.setOperationBy(UserContext.getUserId());
                bo.setOperationName(UserContext.getUser().getDisplayName());
                amEmpMaterialService.insertAmEmpMaterialOperationLog(bo);
            }
        } catch (Exception e) {

        }
        AmEmpMaterial amEmpMaterial = list.get(0);
        AmEmpMaterialBO amEmpMaterialBO = new AmEmpMaterialBO();
        BeanUtils.copyProperties(amEmpMaterial,amEmpMaterialBO);
        List<AmEmpMaterialBO> list1 = amEmpMaterialService.queryAmEmpMaterialList(amEmpMaterialBO);

        List<AmEmpMaterialOperationLogBO> materialLogList = amEmpMaterialService.queryAmEmpMaterialLogList(amEmpMaterialBO);

        resultMap.put("data",result?1:0);
        resultMap.put("result",list1);
        resultMap.put("logList",materialLogList);
        return  JsonResultKit.of(resultMap);
    }

    @RequestMapping("/deleteAmInjury")
    public JsonResult<Boolean>  deleteAmInjury(Long injuryId){
        AmInjury amInjury = amInjuryService.selectById(injuryId);
        amInjury.setActive(false);
        boolean  result = amInjuryService.insertOrUpdate(amInjury);
        return JsonResultKit.of(result);
    }

    @RequestMapping("/deleteAmEmpMaterial")
    public JsonResult<Boolean>  deleteAmEmpMaterial(AmEmpMaterial amEmpMaterial){
        amEmpMaterial.setActive(false);
        boolean  result = amEmpMaterialService.insertOrUpdate(amEmpMaterial);
        return JsonResultKit.of(result);
    }


    @RequestMapping("/saveAmArchiveUse")
    public JsonResult  saveAmArchiveUse(AmArchiveUse amArchiveUse) {


        LocalDateTime now = LocalDateTime.now();
        if(amArchiveUse.getArchiveUseId()==null){
            amArchiveUse.setCreatedTime(now);
            amArchiveUse.setModifiedTime(now);
            amArchiveUse.setCreatedBy(ReasonUtil.getUserId());
            amArchiveUse.setModifiedBy(ReasonUtil.getUserId());
            amArchiveUse.setHandleMan(ReasonUtil.getUserName());
            amArchiveUse.setActive(true);
        }else{
            AmArchiveUse temp = iAmArchiveUseService.selectById(amArchiveUse.getArchiveUseId());
            amArchiveUse.setCreatedTime(temp.getCreatedTime());
            amArchiveUse.setCreatedBy(temp.getCreatedBy());
            amArchiveUse.setActive(true);
            amArchiveUse.setHandleMan(ReasonUtil.getUserName());
            amArchiveUse.setActive(true);
            amArchiveUse.setModifiedTime(now);
            amArchiveUse.setModifiedBy(ReasonUtil.getUserId());
        }

        boolean result = iAmArchiveUseService.insertOrUpdateAllColumn(amArchiveUse);

        PageInfo pageInfo = new PageInfo();
        JSONObject params = new JSONObject();
        params.put("archiveId",amArchiveUse.getArchiveId());
        params.put("useBorrow",amArchiveUse.getUseBorrow());
        pageInfo.setParams(params);
        Map<String, Object> resultMap = new HashMap<>();
        PageRows<AmArchiveUse>  amArchiveUsePageRows  = iAmArchiveUseService.queryAmArchiveUse(pageInfo);
        if(null!=amArchiveUsePageRows&&amArchiveUsePageRows.getRows().size()>0){
            resultMap.put("amArchiveUsePageRows",amArchiveUsePageRows);
        }
        return JsonResultKit.of(resultMap);
    }

    @RequestMapping("/queryArchiveUse")
    public JsonResult queryArchiveUse(AmArchiveUse archiveUse){

        PageInfo pageInfo = new PageInfo();
        JSONObject params = new JSONObject();
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

        String userName = "System";
        try {
            userName = UserContext.getUser().getDisplayName();
        } catch (Exception e) {

        }
        resultMap.put("userName",userName);
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

        for(archiveSearchExportOpt temp:opts)
        {
            temp.setEmployFeedback(ReasonUtil.getYgfk(temp.getEmployFeedback()));
            temp.setResignFeedback1(ReasonUtil.getTgfk(temp.getResignFeedback1()));

            if(!StringUtil.isEmpty(temp.getArchiveSpecial()))
            {
                int last = temp.getArchiveSpecial().lastIndexOf(",");
                temp.setArchiveSpecial(temp.getArchiveSpecial().substring(0,last));
            }
        }

        ExcelUtil.exportExcel(opts,archiveSearchExportOpt.class,fileNme,response);
    }

    @RequestMapping("/saveAmInjury")
    public JsonResult  saveAmInjury(AmInjury amInjury){
        LocalDateTime now = LocalDateTime.now();
        if(amInjury.getInjuryId()==null){
            amInjury.setCreatedTime(now);
            amInjury.setModifiedTime(now);
            amInjury.setCreatedBy(ReasonUtil.getUserId());
            amInjury.setModifiedBy(ReasonUtil.getUserId());
        }else{
            amInjury.setModifiedTime(now);
            amInjury.setModifiedBy(ReasonUtil.getUserId());
        }
        boolean result = false;
        try {
            result = amInjuryService.insertOrUpdate(amInjury);
        } catch (Exception e) {

        }

        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("result",result);
        resultMap.put("data",amInjury);

        return JsonResultKit.of(resultMap);

    }

    @RequestMapping("/queryAmInjury")
    public JsonResult  queryAmInjury(AmInjury amInjury){
        AmInjury amInjury1 = amInjuryService.selectById(amInjury);
        return JsonResultKit.of( amInjury1);

    }

    /**
     * 批量打印退工单
     * @param response
     */
    @RequestMapping("/archiveSearchExportReturnList")
    public void archiveSearchExportReturnList(HttpServletResponse response, PageInfo pageInfo) {

        List<AmArchiveReturnPrintDTO> list = business.queryAmArchiveForeignerPritDate(pageInfo);


        Map<String,Object> map = new HashMap<>();
        map.put("list",list);
        try {
            WordUtils.exportMillCertificateWord(response,map,"外来退工备案登记表","AM_RETURN_TEMP.ftl");
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @RequestMapping("/taskCountArchive")
    public  JsonResult<AmEmpTaskCollection>  taskCountArchive(AmEmploymentBO amEmploymentBO){
        AmEmpTaskCollection amEmpTaskCollection = business.queryArchiveTaskCount(amEmploymentBO);

        return  JsonResultKit.of(amEmpTaskCollection);
    }

    @RequestMapping("/impTemplateFile")
    public void impTemplateFile(HttpServletResponse response) {
        String fileNme = "档案配对导入模板.xls";
        List<AmEmpArchiveAdvanceXsl> opts = new ArrayList();
        ExcelUtil.exportExcel(opts,AmEmpArchiveAdvanceXsl.class,fileNme,response);
    }

    /**
     * 档案配对导入
     * @param file
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/xlsImportEmpAdvance",consumes = {"multipart/form-data"})
    public JsonResult xlsImportEmpAdvance(MultipartFile file) throws Exception {
        if(file.getOriginalFilename().endsWith(".xlsx") == false && file.getOriginalFilename().endsWith(".xls") == false){
            JsonResult error = new JsonResult();
            error.setCode(1);
            error.setMessage("档案配对上传文件格式仅支持.xlsx 和.xls");
            return error;
        }
        List<AmEmpArchiveAdvanceXsl> optList = ExcelUtil.importExcel(file,0,1,AmEmpArchiveAdvanceXsl.class,false);
        JsonResult result = business.xlsImportAmEmpAdvance(optList,file.getOriginalFilename());
        return  result;

    }

    /**
     * 档案用工录用名册打印导出Word
     */
    @RequestMapping("/employSearchExportOptUseWord")
    public @ResponseBody
    void employSearchExportOptUseWord(AmEmploymentBO bo,HttpServletResponse response){

        try {

            logApiUtil.info(LogMessage.create().setTitle("employSearchExportOptUseWord").setContent("用工录用名册打印 start"));

            // 中智大库
            List<AmEmpDispatchExportPageDTO> dtoList1 = business.queryExportOptDispatch(bo,2,12);

            // 外包
            List<AmEmpDispatchExportPageDTO> dtoList2 = business.queryExportOptDispatch(bo,3,12);

            //独立户
            List<AmEmpDispatchExportPageDTO> dtoList3 = business.queryExportOptDispatch(bo,12);

            Map<String, Object> map = new HashMap<>();

            map.put("list",dtoList1);
            map.put("list2",dtoList2);
            map.put("list3",dtoList3);

            WordUtils.exportMillCertificateWord(response,map,"用工录用名册","AM_USE_TEMP.ftl");

        } catch (Exception e) {
            logApiUtil.error(LogMessage.create().setTitle("employSearchExportOptUseWord").setContent(e.getMessage()));
            e.printStackTrace();
        }
    }

    /**
     * 档案派遣录用名册打印导出Word
     */
    @RequestMapping("/employSearchExportOptDispatchWord")
    public @ResponseBody
    void employSearchExportOptDispatchWord(AmEmploymentBO bo,HttpServletResponse response){

        try {
            // 中智大库
            List<AmEmpDispatchExportPageDTO> dtoList = business.queryExportOptDispatch(bo,2,9);

            // 外包
            List<AmEmpDispatchExportPageDTO> dtoList2 = business.queryExportOptDispatch(bo,3,9);

            //独立户
            List<AmEmpDispatchExportPageDTO> dtoList3 = business.queryExportOptDispatch(bo,9);


            Map<String, Object> map = new HashMap<>();

            map.put("list",dtoList);
            map.put("list2",dtoList2);
            map.put("list3",dtoList3);
            WordUtils.exportMillCertificateWord(response,map,"派遣录用名册","AM_DISPATCH_TEMP.ftl");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     *  档案外来独立打印导出Word
     */
    @RequestMapping("/employSearchExportOptAlonehWord")
    public @ResponseBody
    void employSearchExportOptAlonehWord(AmEmploymentBO bo,HttpServletResponse response){

        // 中智大库
        List<AmEmpDispatchExportPageDTO> dtoList = business.queryExportOptDispatch(bo,2,10);

        // 外包
        List<AmEmpDispatchExportPageDTO> dtoList2 = business.queryExportOptDispatch(bo,3,10);

        //独立户
        List<AmEmpDispatchExportPageDTO> dtoList3 = business.queryExportOptDispatch(bo,10);

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
            WordUtils.exportMillCertificateWord(response,map,"外来独立","AM_ALONE_TEMP.ftl");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 档案外来派遣导出Word
     */
    @RequestMapping("/employSearchExportOptExtDispatchWord")
    public @ResponseBody
    void employSearchExportOptExtDispatchWord(AmEmploymentBO bo,HttpServletResponse response){

        // 中智大库
        List<AmEmpDispatchExportPageDTO> dtoList = business.queryExportOptDispatch(bo,2,9);

        // 外包
        List<AmEmpDispatchExportPageDTO> dtoList2 = business.queryExportOptDispatch(bo,3,9);

        //独立户
        List<AmEmpDispatchExportPageDTO> dtoList3 = business.queryExportOptDispatch(bo,9);

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
            WordUtils.exportMillCertificateWord(response,map,"外来派遣","AM_EXT_DISPATCH_TEMP.ftl");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 档案采集表汇总表导出Word
     */
    @RequestMapping("/employSearchExportOptExtCollectWord")
    public @ResponseBody
    void employSearchExportOptExtCollectWord(HttpServletResponse response, AmEmploymentBO bo){
        // 中智大库
        List<AmEmpCollectExportPageDTO> dtoList = business.queryExportOptCollect(bo,2);

        // 外包
        List<AmEmpCollectExportPageDTO> dtoList2 = business.queryExportOptCollect(bo,3);

        //独立户
        List<AmEmpCollectExportPageDTO> dtoList3 = business.queryExportOptCollect(bo);

        Integer sum = 0;

        for (AmEmpCollectExportPageDTO dto:dtoList) {
            sum += dto.getList1().size();
            sum += dto.getList2().size();
            sum += dto.getList3().size();
        }
        for (AmEmpCollectExportPageDTO dto:dtoList2) {
            sum += dto.getList1().size();
            sum += dto.getList2().size();
            sum += dto.getList3().size();
        }
        for (AmEmpCollectExportPageDTO dto:dtoList3) {
            sum += dto.getList1().size();
            sum += dto.getList2().size();
            sum += dto.getList3().size();
        }

        Map<String, Object> map = new HashMap<>();

        map.put("list",dtoList);
        map.put("list2",dtoList2);
        map.put("list3",dtoList3);
        map.put("sum",sum);

        try {
            WordUtils.exportMillCertificateWord(response,map,"采集表汇总表","AM_COLLECT_TEMP.ftl");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 档案外来情况说明导出Word
     */
    @RequestMapping("/archiveSearchExportOptExtExplainWord")
    public @ResponseBody
    void archiveSearchExportOptExtExplainWord(HttpServletResponse response, AmEmploymentBO bo){
        // 中智大库
        List<AmEmpExplainExportPageDTO> dtoList = business.queryExportOptExplain(bo,2,bo.getIsEntry());

        // 外包
        List<AmEmpExplainExportPageDTO> dtoList2 = business.queryExportOptExplain(bo,3,bo.getIsEntry());

        //独立户
        List<AmEmpExplainExportPageDTO> dtoList3 = business.queryExportOptExplain(bo,bo.getIsEntry());

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

}
