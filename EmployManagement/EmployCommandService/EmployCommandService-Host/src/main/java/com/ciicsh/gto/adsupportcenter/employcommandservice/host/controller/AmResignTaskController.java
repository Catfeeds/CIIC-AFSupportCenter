package com.ciicsh.gto.adsupportcenter.employcommandservice.host.controller;

import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.bo.*;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.business.*;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.business.utils.ReasonUtil;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.entity.AmEmpTask;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.entity.AmEmployment;
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

    @Autowired
    private  AmResignLinkService amResignLinkService;

    @Autowired
    private IAmEmpMaterialService amEmpMaterialService;

    @Autowired
    private  AmEmpEmployeeService amEmpEmployeeService;

    @Autowired
    private  IAmEmpCustomService amEmpCustomService;



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

           if(!StringUtil.isEmpty(amResignBO.getResignFeedback())){
               amResignBO.setResignFeedback(ReasonUtil.getTgfk(amResignBO.getResignFeedback()));
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
               int last = amResignBO.getRefuseSpecial().lastIndexOf(",");
               amResignBO.setRefuseSpecial(amResignBO.getRefuseSpecial().substring(0,last));

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

            amEmpEmployeeBO = amEmpEmployeeService.queryAmEmployeeByTaskId(amEmployment.getEmpTaskId());

            amCustomBO = amEmpCustomService.getCustom(amEmpEmployeeBO.getEmpTaskId());
        }else {
            /**
             * 否则通过雇佣id和公司id查询最新的雇员信息绑定退工
             */
            amEmpEmployeeBO = amEmpEmployeeService.queryAmEmployee(amTaskParamBO);

            if(amEmpEmployeeBO!=null){
                amCustomBO = amEmpCustomService.getCustom(amEmpEmployeeBO.getEmpTaskId());
            }
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
        resultMap.put("customerInfo",amCustomBO);
        //雇员信息
        resultMap.put("amEmpTaskBO",amEmpEmployeeBO);


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
        if(null!=listResignBO&&listResignBO.size()>0){
            amResignBO = listResignBO.get(0);

            amResignBO.setHandleType(amEmploymentBO.getHandleType());
            amResignBO.setEmployFeedback(amEmploymentBO.getEmployFeedback());

            amResignBO.setYuliuDocNum(amArchiveBO.getYuliuDocNum());
            amResignBO.setDocNum(amArchiveBO.getDocCode());
            amResignBO.setArchiveCardState(amArchiveBO.getArchiveCardState());
            amResignBO.setArchivePlace(amArchiveBO.getArchivePlace());
            amResignBO.setArchivePlaceAdditional(amArchiveBO.getArchivePlaceAdditional());
            amResignBO.setEmployDocPaymentTo(amArchiveBO.getEmployDocPaymentTo());
            amResignBO.setStorageDate(amArchiveBO.getStorageDate());
            amResignBO.setDiaodangFeedback(amArchiveBO.getDiaodangFeedback());

            amResignBO.setArchiveDirection(amEmpEmployeeBO==null?"":amEmpEmployeeBO.getArchiveDirection());

        }else{

            amResignBO.setHandleType(amEmploymentBO.getHandleType());
            amResignBO.setEmployFeedback(amEmploymentBO.getEmployFeedback());
            amResignBO.setEmploymentId(amEmploymentBO.getEmploymentId());

            amResignBO.setYuliuDocNum(amArchiveBO.getYuliuDocNum());
            amResignBO.setDocNum(amArchiveBO.getDocCode());
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
        AmEmpTask amEmpTask = taskService.selectById(amTaskParamBO.getEmpTaskId());
        if(null!=amEmpTask){
            java.text.DateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
            if(null!=amEmpTask.getOutDate()){
                amResignBO.setOutDate(sdf.format(amEmpTask.getOutDate()));
            }
            amResignBO.setOutReason(amEmpTask.getOutReason());
        }
        if(amResignBO.getEmploymentId()!=null){
            amResignBO.setMatchEmployIndex(amResignBO.getEmploymentId().toString());
        }
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

        for(resignSearchExportOpt temp:opts){
            if(!StringUtil.isEmpty(temp.getRefuseSpecial()))
            {
                int last = temp.getRefuseSpecial().lastIndexOf(",");
                temp.setRefuseSpecial(temp.getRefuseSpecial().substring(0,last));

            }
        }

        ExcelUtil.exportExcel(opts,resignSearchExportOpt.class,fileNme,response);
    }

}
