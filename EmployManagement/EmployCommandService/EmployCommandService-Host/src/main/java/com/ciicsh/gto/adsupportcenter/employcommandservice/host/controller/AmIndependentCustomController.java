package com.ciicsh.gto.adsupportcenter.employcommandservice.host.controller;

import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.bo.AmCompanySetBO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.bo.SalCompanyBO;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.business.IAmCompanySetService;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.business.ISalCompanyService;
import com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.entity.AmCompanySet;
import com.ciicsh.gto.afsupportcenter.util.page.PageInfo;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
import com.ciicsh.gto.afsupportcenter.util.web.controller.BasicController;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResult;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResultKit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhangzhiwen on 2018/3/20.
 */

@RestController
@RequestMapping("/api/employcommandservice/salCompany")
public class AmIndependentCustomController extends BasicController<IAmCompanySetService> {

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
        if(amCompanySet.getKey() == 1){
            is_special = true;
        }
        if((amCompanySet.getRefuseSpecial()!=null && !"".equals(amCompanySet.getRefuseSpecial().trim()))||
            (amCompanySet.getArchiveSpecial()!=null && !"".equals(amCompanySet.getArchiveSpecial().trim()))||
                ( amCompanySet.getEmploySpecial()!=null && !"".equals(amCompanySet.getEmploySpecial().trim()) ))
        {
            is_special = true;
        }
        if((null!=amCompanySet.getCompanySpecial20()&&amCompanySet.getCompanySpecial20()==1)||(null!=amCompanySet.getCompanySpecial21()&&amCompanySet.getCompanySpecial21()==1)||(null!=amCompanySet.getCompanySpecial22()&&amCompanySet.getCompanySpecial22()==1))
        {
            is_special = true;
        }
        if(is_special){
            amCompanySet.setSpecial("有");
        }else {
            amCompanySet.setSpecial("无");
        }

        if(amCompanySet.getKeyCode()==null){
            amCompanySet.setKeyCode(" ");
        }
        if(amCompanySet.getKeyPwd()==null){
            amCompanySet.setKeyPwd(" ");
        }
        if(amCompanySet.getKeyStatus()==null){
            amCompanySet.setKeyStatus(" ");
        }
        if(amCompanySet.getEmploySpecial()==null){
            amCompanySet.setEmploySpecial(" ");
        }
        if(amCompanySet.getRefuseSpecial()==null){
            amCompanySet.setRefuseSpecial(" ");
        }
        if(amCompanySet.getArchiveSpecial()==null){
            amCompanySet.setArchiveSpecial(" ");
        }
        boolean result =  business.insertOrUpdateAllColumn(amCompanySet);
        return JsonResultKit.of(result);
    }

    @RequestMapping("/queryCompanySetDetail")
    public  JsonResult  queryCompanySetDetail(AmCompanySetBO amCompanySetBO){

        AmCompanySetBO amCompanySetBO1 = business.queryAmCompanySet(amCompanySetBO);

        SalCompanyBO salCompanyBO = new SalCompanyBO();
        salCompanyBO.setCompanyId(amCompanySetBO.getCompanyId());

        List<SalCompanyBO> salCompanyBOList = salCompanyService.getSalCompanyList(salCompanyBO);

        Map<String, Object> resultMap = new HashMap<String, Object>();


        if(amCompanySetBO1!=null){
            resultMap.put("amCompanySetBO",amCompanySetBO1);
        }

        if(null!=salCompanyBOList&&salCompanyBOList.size()>0){
            resultMap.put("salCompanyBO",salCompanyBOList.get(0));
        }

        return JsonResultKit.of(resultMap);
    }

}
