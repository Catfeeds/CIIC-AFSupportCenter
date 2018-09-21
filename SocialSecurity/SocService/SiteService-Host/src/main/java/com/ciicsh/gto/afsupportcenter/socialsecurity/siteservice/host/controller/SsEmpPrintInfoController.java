package com.ciicsh.gto.afsupportcenter.socialsecurity.siteservice.host.controller;


import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo.SsEmpPrintInfoBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business.SsEmpPrintInfoService;
import com.ciicsh.gto.afsupportcenter.util.StringUtil;
import com.ciicsh.gto.afsupportcenter.util.WordUtils;
import com.ciicsh.gto.afsupportcenter.util.interceptor.authenticate.UserContext;
import com.ciicsh.gto.afsupportcenter.util.web.controller.BasicController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 雇员日常操作-打印信息
 *
 * @author sunjian
 * @since 2018-9-7
 */
@RestController
@RequestMapping("/api/soccommandservice/ssEmpPrintInfo")
public class SsEmpPrintInfoController extends BasicController<SsEmpPrintInfoService> {
    @Autowired
    private SsEmpPrintInfoService ssEmpPrintInfoService;


    //个人社保登记表
    @GetMapping("/ssExpEmpRegisterFormPrint")
    public void ssExpEmpRegisterFormPrint(HttpServletResponse response, SsEmpPrintInfoBO ssEmpPrintInfoBO) throws Exception {
        List<Map> userList = new ArrayList<>();
        userList = ssEmpPrintInfoService.ssExpEmpRegisterFormPrint(ssEmpPrintInfoBO);
        Map resultMap = new HashMap();
        resultMap.put("userList", userList);
        WordUtils.exportWord(response, resultMap, "个人社会保险登记表", "个人社会保险登记表.ftl");



//        Map resultMap = new HashMap();
//        resultMap.put("userList", userList);
//
//        WordUtils.exportWord(response, resultMap, "个人社会保险登记表", "个人社会保险登记表.ftl");

    }
    //社保业务变更项目申报
    @GetMapping("/ssExpChangeItemDeclarationFormPrint")
    public void ssExpChangeItemDeclarationFormPrint(HttpServletResponse response, SsEmpPrintInfoBO ssEmpPrintInfoBO) throws Exception {
        List<List<Map>> pagedUserList = new ArrayList<>();
        List<Map> userList = new ArrayList<>();
        Map map = new HashMap<>();
       /* //姓名
        map.put("displayName", "张三");
        //身份证号
        map.put("idNumber", "330225198908262278");
        //序号或编号
        map.put("serialNumber", "0001");
        //个人状态
        map.put("status", "在职");
        //办事项目
        map.put("epsProject", "转出");
        //缴费起始年月
        map.put("paymentBegin", "2018.09");
        //月平均工资性收入
        map.put("income", 15000);
        //备注
        map.put("remark", "备注1");
        userList.add(map);

        map = new HashMap<>();
        //姓名
        map.put("displayName", "李四");
        //身份证号
        map.put("idNumber", "330225198908262279");
        //序号或编号
        map.put("serialNumber", "0002");
        //个人状态
        map.put("status", "养老");
        //办事项目
        map.put("epsProject", "转入");
        //缴费起始年月
        map.put("paymentBegin", "2016.09");
        //月平均工资性收入
        map.put("income", 6000);
        //备注
        map.put("remark", "备注2");
        userList.add(map);
        pagedUserList.add(userList);

        userList = new ArrayList<>();
        map = new HashMap<>();
        //姓名
        map.put("displayName", "王五");
        //身份证号
        map.put("idNumber", "330225198908262278");
        //序号或编号
        map.put("serialNumber", "0001");
        //个人状态
        map.put("status", "在职");
        //办事项目
        map.put("epsProject", "转出");
        //缴费起始年月
        map.put("paymentBegin", "2018.09");
        //月平均工资性收入
        map.put("income", 15000);
        //备注
        map.put("remark", "备注1");
        userList.add(map);

        map = new HashMap<>();
        //姓名
        map.put("displayName", "孙六");
        //身份证号
        map.put("idNumber", "330225198908262279");
        //序号或编号
        map.put("serialNumber", "0002");
        //个人状态
        map.put("status", "养老");
        //办事项目
        map.put("epsProject", "转入");
        //缴费起始年月
        map.put("paymentBegin", "2016.09");
        //月平均工资性收入
        map.put("income", 6000);
        //备注
        map.put("remark", "备注2");
        userList.add(map);
        pagedUserList.add(userList);
*/

        userList = ssEmpPrintInfoService.ssExpChangeItemDeclarationFormPrint(ssEmpPrintInfoBO);
        userList.forEach(map1 -> {
            if(map1.get("epsProject").equals("转出") || map1.get("epsProject").equals("封存")){
                map1.put("paymentBegin","");
            }
            if(map1.get("epsProject").equals("转入") && !StringUtil.isEmpty(map1.get("paymentBegin"))){
                map1.put("paymentBegin",map1.get("paymentBegin").toString().split("-")[0]);
            }
            if(map1.get("epsProject").equals("其他") && !StringUtil.isEmpty(map1.get("paymentBegin"))){
                String[] month =map1.get("paymentBegin").toString().split("-");
                if(month.length == 2){
                    if(month[0].equals(month[1])){
                        map1.put("paymentBegin", month[0]);
                    }
                }
            }
        });
        int count=userList.size();
        int page = (count/10) + (count%10>0?1:0);

        if(userList.size() < 10*page){
            while(userList.size() < 10*page){
                Map m= new HashMap();
                m.put("status","");
                m.put("epsProject","");
                m.put("remark","");
                userList.add(m);
            }
        }

        int pEnd=0,pStart=0;
        for(int i=0;i<page;i++){
            pEnd=i*10+10;
            if(pEnd==count){
                pEnd=count;
            }
            pStart =i*10;
            if(pStart==count){
                pStart=count;
            }
            pagedUserList.add(userList.subList(pStart,pEnd));
        }

        Map resultMap = new HashMap();
        resultMap.put("pagedUserList", pagedUserList);
        if(!userList.isEmpty()){
            resultMap.put("comAccountName", userList.get(0).get("com_account_name"));
            resultMap.put("registrationCode", userList.get(0).get("ss_account"));
        }else {
            resultMap.put("comAccountName", "");
            resultMap.put("registrationCode", "        ");
        }
        resultMap.put("applicant", UserContext.getUser().getDisplayName());
        DateTimeFormatter formatter =DateTimeFormatter.ofPattern("yyyy年MM月dd日") ;
        resultMap.put("applicantDate", LocalDate.now().format(formatter));


        WordUtils.exportWord(response, resultMap, "社会保险业务变更项目申报表", "社会保险业务变更项目申报表.ftl");

    }
}

