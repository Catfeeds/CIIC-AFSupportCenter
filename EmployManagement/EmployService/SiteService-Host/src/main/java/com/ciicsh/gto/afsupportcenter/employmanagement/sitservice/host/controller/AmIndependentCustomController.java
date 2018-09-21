package com.ciicsh.gto.afsupportcenter.employmanagement.sitservice.host.controller;

import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.bo.AmCompanySetBO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.bo.AmInDePentCountBO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.bo.SalCompanyBO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.business.IAmCompanySetService;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.business.ISalCompanyService;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.custom.IndependentExportOpt;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.custom.employSearchExportOpt;
import com.ciicsh.gto.afsupportcenter.employmanagement.employservice.entity.AmCompanySet;
import com.ciicsh.gto.afsupportcenter.util.ExcelUtil;
import com.ciicsh.gto.afsupportcenter.util.StringUtil;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
import com.ciicsh.gto.afsupportcenter.util.web.controller.BasicController;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResult;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResultKit;
import com.ciicsh.gto.salecenter.apiservice.api.dto.company.CompanyNameHistoryDTO;
import com.ciicsh.gto.salecenter.apiservice.api.proxy.CompanyNameHistoryProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Created by zhangzhiwen on 2018/3/20.
 */

@RestController
@RequestMapping("/api/employservice/salCompany")
public class AmIndependentCustomController extends BasicController<IAmCompanySetService> {

    @Autowired
    private CompanyNameHistoryProxy companyNameHistoryProxy;

    @Autowired
    private  ISalCompanyService salCompanyService;

    @RequestMapping("/querySalCompany")
    public JsonResult<PageRows> querySalCompany(PageInfo pageInfo) {

        PageRows<SalCompanyBO> result = salCompanyService.querySalCompanyList(pageInfo);

        return JsonResultKit.of(result);

    }

    @RequestMapping("/saveCompanySet")
    public JsonResult<Boolean> saveCompanySet(AmCompanySet amCompanySet){
        LocalDateTime now = LocalDateTime.now();
        if(amCompanySet.getCompanySetId()==null){
            amCompanySet.setCreatedTime(now);
            amCompanySet.setModifiedTime(now);
            amCompanySet.setCreatedBy("sys");
            amCompanySet.setModifiedBy("sys");
            amCompanySet.setActive(true);
        }else {
            AmCompanySet amCompanySet1 = business.selectById(amCompanySet.getCompanySetId());
            amCompanySet.setActive(true);
            amCompanySet.setCreatedBy(amCompanySet1.getCreatedBy());
            amCompanySet.setCreatedTime(amCompanySet1.getCreatedTime());
            amCompanySet.setModifiedTime(now);
            amCompanySet.setModifiedBy("sys");
        }
        boolean is_special = false;
        if((null!=amCompanySet.getCompanySpecial0()&&amCompanySet.getCompanySpecial0()==1)||(null!=amCompanySet.getCompanySpecial1()&&amCompanySet.getCompanySpecial1()==1)||(null!=amCompanySet.getCompanySpecial2()&&amCompanySet.getCompanySpecial2()==1)||(null!=amCompanySet.getCompanySpecial3()&&amCompanySet.getCompanySpecial3()==1))
        {
            is_special = true;
        }
        if((null!=amCompanySet.getCompanySpecial4()&&amCompanySet.getCompanySpecial4()==1)||(null!=amCompanySet.getCompanySpecial5()&&amCompanySet.getCompanySpecial5()==1)||(null!=amCompanySet.getCompanySpecial6()&&amCompanySet.getCompanySpecial6()==1)||(null!=amCompanySet.getCompanySpecial7()&&amCompanySet.getCompanySpecial7()==1))
        {
            is_special = true;
        }
        if((null!=amCompanySet.getCompanySpecial8()&&amCompanySet.getCompanySpecial8()==1)||(null!=amCompanySet.getCompanySpecial9()&&amCompanySet.getCompanySpecial9()==1)||(null!=amCompanySet.getCompanySpecial10()&&amCompanySet.getCompanySpecial10()==1)||(null!=amCompanySet.getCompanySpecial11()&&amCompanySet.getCompanySpecial11()==1))
        {
            is_special = true;
        }
        if((null!=amCompanySet.getCompanySpecial12()&&amCompanySet.getCompanySpecial12()==1)||(null!=amCompanySet.getCompanySpecial13()&&amCompanySet.getCompanySpecial13()==1)||(null!=amCompanySet.getCompanySpecial14()&&amCompanySet.getCompanySpecial14()==1)||(null!=amCompanySet.getCompanySpecial15()&&amCompanySet.getCompanySpecial15()==1))
        {
            is_special = true;
        }
        if((null!=amCompanySet.getCompanySpecial16()&&amCompanySet.getCompanySpecial16()==1)||(null!=amCompanySet.getCompanySpecial17()&&amCompanySet.getCompanySpecial17()==1)||(null!=amCompanySet.getCompanySpecial18()&&amCompanySet.getCompanySpecial18()==1)||(null!=amCompanySet.getCompanySpecial19()&&amCompanySet.getCompanySpecial19()==1))
        {
            is_special = true;
        }
//        if(null!=amCompanySet.getKey()&&amCompanySet.getKey() == 1){
//            is_special = true;
//        }
        if((amCompanySet.getRefuseSpecial()!=null && !"".equals(amCompanySet.getRefuseSpecial().trim()))||
            (amCompanySet.getArchiveSpecial()!=null && !"".equals(amCompanySet.getArchiveSpecial().trim()))||
                ( amCompanySet.getEmploySpecial()!=null && !"".equals(amCompanySet.getEmploySpecial().trim()) ))
        {
            is_special = true;
        }
        if((null!=amCompanySet.getCompanySpecial21()&&amCompanySet.getCompanySpecial21()==1)||(null!=amCompanySet.getCompanySpecial22()&&amCompanySet.getCompanySpecial22()==1))
        {
            is_special = true;
        }
        if(is_special){
            amCompanySet.setSpecial("有");
        }else {
            amCompanySet.setSpecial("无");
        }

        boolean result =  business.insertOrUpdateAllColumn(amCompanySet);
        return JsonResultKit.of(result);
    }

    @RequestMapping("/queryCompanySetDetail")
    @ResponseBody
    public  JsonResult  queryCompanySetDetail(AmCompanySetBO amCompanySetBO){

        AmCompanySetBO amCompanySetBO1 = business.queryAmCompanySet(amCompanySetBO);

        SalCompanyBO salCompanyBO = new SalCompanyBO();
        salCompanyBO.setCompanyId(amCompanySetBO.getCompanyId());

        List<SalCompanyBO> salCompanyBOList = salCompanyService.getSalCompanyList(salCompanyBO);

        com.ciicsh.gto.salecenter.apiservice.api.dto.core.JsonResult<List<CompanyNameHistoryDTO>>
        companyListResult = companyNameHistoryProxy.getCompanyNameHistoryListByCompanyId(salCompanyBO.getCompanyId());

        Map<String, Object> resultMap = new HashMap<String, Object>();


        if(amCompanySetBO1!=null){
            resultMap.put("amCompanySetBO",amCompanySetBO1);
        }

        if(null!=salCompanyBOList&&salCompanyBOList.size()>0){
            resultMap.put("salCompanyBO",salCompanyBOList.get(0));
        }

        if(null!=companyListResult.getObject()){
            resultMap.put("companyNameList",companyListResult.getObject());
        }

        return JsonResultKit.of(resultMap);
    }

    @RequestMapping("/queryTaskCount")
    public  JsonResult<AmInDePentCountBO>  taskCount(PageInfo pageInfo){

        AmInDePentCountBO amInDePentCountBO = new AmInDePentCountBO();

        List<SalCompanyBO> list = salCompanyService.taskCount(pageInfo);

        Integer jobNum =0;
        Integer total = 0;
        for(SalCompanyBO salCompanyBO:list)
        {
            if(salCompanyBO.getStatus()==2){
                jobNum = jobNum + salCompanyBO.getCount();
                total = total + salCompanyBO.getCount();
            }else if(salCompanyBO.getStatus()==3){
                amInDePentCountBO.setNoJob(salCompanyBO.getCount());
                total = total + salCompanyBO.getCount();
            }else{
                jobNum = jobNum + salCompanyBO.getCount();
                total = total + salCompanyBO.getCount();
            }
        }
        amInDePentCountBO.setJob(jobNum);
        amInDePentCountBO.setTotal(total);
        return  JsonResultKit.of(amInDePentCountBO);

    }

    @RequestMapping("/indSearchExportOpt")
    public void indSearchExportOpt(HttpServletResponse response, SalCompanyBO salCompanyBO) {

        List<String> param = new ArrayList<String>();
        List<String> orderParam = new ArrayList<String>();
        if (!StringUtil.isEmpty(salCompanyBO.getParams())) {
            String arr[] = salCompanyBO.getParams().split(",");
            for (int i = 0; i < arr.length; i++) {
                if(!StringUtil.isEmpty(arr[i]))
                {
                    if(arr[i].indexOf("desc")>0||arr[i].indexOf("asc")>0){
                        orderParam.add(arr[i]);
                    }else {
                        param.add(arr[i]);
                    }
                }

            }
        }

        salCompanyBO.setParam(param);

        Date date = new Date();
        String fileNme = "独立户客户单_"+ StringUtil.getDateString(date)+".xls";

        List<IndependentExportOpt> opts = salCompanyService.querySalOptList(salCompanyBO);

        ExcelUtil.exportExcel(opts,IndependentExportOpt.class,fileNme,response);
    }

}
