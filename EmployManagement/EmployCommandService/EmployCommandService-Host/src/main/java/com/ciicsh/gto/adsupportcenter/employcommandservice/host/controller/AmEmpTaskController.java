package com.ciicsh.gto.adsupportcenter.employcommandservice.host.controller;


import com.alibaba.fastjson.JSONObject;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.bo.*;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.business.*;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.business.utils.CommonApiUtils;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.entity.AmArchive;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.entity.AmEmployment;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.entity.AmRemark;
import com.ciicsh.gto.afsupportcenter.util.aspect.log.Log;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
import com.ciicsh.gto.afsupportcenter.util.web.controller.BasicController;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResult;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResultKit;
import com.ciicsh.gto.employeecenter.apiservice.api.dto.EmployeeInfoDTO;
import com.ciicsh.gto.employeecenter.apiservice.api.dto.EmployeeQueryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
            }else if(6==status){
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
    public JsonResult employeeDetailInfoQuery(AmEmpTaskBO amEmpTaskBO) {
        EmployeeQueryDTO var1 = new EmployeeQueryDTO();
        var1.setIdNum(amEmpTaskBO.getIdNum());
        var1.setIdCardType(amEmpTaskBO.getIdCardType());
        var1.setBusinessType(1);

       com.ciicsh.gto.employeecenter.util.JsonResult<EmployeeInfoDTO> jsonResult = employeeInfoProxy.getEmployeeInfo(var1);

        EmployeeInfoDTO employeeInfoDTO = jsonResult.getData();

        AmEmpTaskBO amEmpTaskBO1 = new AmEmpTaskBO();
        if(null!=employeeInfoDTO){
            amEmpTaskBO1.setEmployeeId(employeeInfoDTO.getEmployeeId());
            amEmpTaskBO1.setIdNum(employeeInfoDTO.getIdNum());
            amEmpTaskBO1.setEmployeeName(employeeInfoDTO.getEmployeeName());
            amEmpTaskBO1.setSex(employeeInfoDTO.getGender()==0?"男":"女");
            amEmpTaskBO1.setMobile(employeeInfoDTO.getMobile());
            amEmpTaskBO1.setResidenceAddress(employeeInfoDTO.getResidenceAddress());
        }

        AmEmpTaskBO accout = business.queryAccout(amEmpTaskBO.getCompanyId());

        if(null!=accout){
            amEmpTaskBO1.setUkey(accout.getUkey());
            amEmpTaskBO1.setAccoutModified(accout.getAccoutModified());
            amEmpTaskBO1.setSettlementArea(accout.getSettlementArea());
            amEmpTaskBO1.setSsAccount(accout.getSsAccount());
            amEmpTaskBO1.setSsPwd(accout.getSsPwd());
        }

        AmEmpTaskBO bo = new AmEmpTaskBO();
        bo.setEmployeeId(amEmpTaskBO.getEmployeeId());
        bo.setCompanyId(amEmpTaskBO.getCompanyId());
        Map<String,Object> param = new HashMap<>();
        param.put("employeeId",amEmpTaskBO.getEmployeeId());
        param.put("companyId",amEmpTaskBO.getCompanyId());

//      List<AmEmpTaskBO> list = business.queryAmEmpTaskById(param);

        //用工材料
        PageInfo pageInfo = new PageInfo();
        JSONObject params = new JSONObject();
        params.put("employeeId",amEmpTaskBO.getEmployeeId());
        params.put("remarkType",amEmpTaskBO.getRemarkType());
        params.put("companyId",amEmpTaskBO.getCompanyId());
        pageInfo.setParams(params);

        PageRows<AmEmpMaterialBO> result = iAmEmpMaterialService.queryAmEmpMaterial(pageInfo);
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
        List<AmEmpTaskBO>  listCompany = business.queryCustom(amEmpTaskBO.getCompanyId());

        Map<String, Object> resultMap = new HashMap<String, Object>();
        //雇员信息
        resultMap.put("amEmpTaskBO",amEmpTaskBO1);

        resultMap.put("materialList",result);

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


}

