package com.ciicsh.gto.afsupportcenter.socialsecurity.siteservice.host.controller;


import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.bo.SsEmpPrintInfoBO;
import com.ciicsh.gto.afsupportcenter.socialsecurity.socservice.business.SsEmpPrintInfoService;
import com.ciicsh.gto.afsupportcenter.util.StringUtil;
import com.ciicsh.gto.afsupportcenter.util.WordUtils;
import com.ciicsh.gto.afsupportcenter.util.web.controller.BasicController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
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

        Map map = new HashMap<>();
        //姓名
        map.put("employee_name", "张三");
        //身份证号
        map.put("idNumber", "330225198908262278");
        //户籍户别
        map.put("householdCityType", "外省市");
        map.put("householdType", "城镇");
        //联系电话
        map.put("tel", "56768909");
        map.put("mobile", "13678987654");
        //文化程度
        map.put("education_status", "大学本科");
        //政治面貌
        map.put("politicsStatus", "中共党员");
        //个人序号
        map.put("serialNumber", 123456);
        //缴费起始年月
        map.put("paymentBegin", "2018.09");
        //缴费基数
        map.put("paymentBase", 15000);
        //联系地址及邮政编码
        map.put("contactProvince", "上海");
        map.put("contactCity", "上海");
        map.put("contactDistrict", "徐汇");
        map.put("contactStreet", "康健新村");
        map.put("contactNeighborhood", "月季百藤");
        map.put("contactRoad", "桂平");
        map.put("contactLane", 67);
        map.put("contactNumber", 59);
        map.put("contactRoom", 402);
        map.put("contactPostcode", "200233");
        //户籍地址及邮政编码
        map.put("householdProvince", "浙江");
        map.put("householdCity", "宁波");
        map.put("householdDistrict", "象山");
        map.put("householdStreet", "丹东");
        map.put("householdNeighborhood", "百花");
        map.put("householdRoad", "靖南");
        map.put("householdLane", 114);
        map.put("householdNumber", 59);
        map.put("householdRoom", 1402);
        map.put("householdPostcode", "315700");
        userList.add(map);

        map = new HashMap<>();
        //姓名
        map.put("displayName", "李四");
        //身份证号
        map.put("idNumber", "330225199908262278");
        //户籍户别
        map.put("householdCityType", "本市");
        map.put("householdType", "城镇");
        //联系电话
        map.put("tel", "11234");
        map.put("mobile", "13621941789");
        //文化程度
        map.put("educationDegree", "小学");
        //政治面貌
        map.put("politicsStatus", "群众");
        //个人序号
        map.put("serialNumber", 15566);
        //缴费起始年月
        map.put("paymentBegin", "2018.08");
        //缴费基数
        map.put("paymentBase", 3000);
        //联系地址及邮政编码
        map.put("contactProvince", "上海");
        map.put("contactCity", "上海");
        map.put("contactDistrict", "徐汇");
        map.put("contactStreet", "康健新村");
        map.put("contactNeighborhood", "月季百藤");
        map.put("contactRoad", "桂平");
        map.put("contactLane", 67);
        map.put("contactNumber", 59);
        map.put("contactRoom", 402);
        map.put("contactPostcode", "200233");
        //户籍地址及邮政编码
        map.put("householdProvince", "浙江");
        map.put("householdCity", "宁波");
        map.put("householdDistrict", "象山");
        map.put("householdStreet", "丹东");
        map.put("householdNeighborhood", "百花");
        map.put("householdRoad", "靖南");
        map.put("householdLane", 114);
        map.put("householdNumber", 59);
        map.put("householdRoom", 1402);
        map.put("householdPostcode", "315700");
        userList.add(map);

        map = new HashMap<>();
        //姓名
        map.put("displayName", "王五");
        //身份证号
        map.put("idNumber", "330225199908262278");
        //户籍户别
        map.put("householdCityType", "本市");
        map.put("householdType", "城镇");
        //联系电话
        map.put("tel", "11234");
        map.put("mobile", "13621941789");
        //文化程度
        map.put("educationDegree", "小学");
        //政治面貌
        map.put("politicsStatus", "群众");
        //个人序号
        map.put("serialNumber", 15566);
        //缴费起始年月
        map.put("paymentBegin", "2018.08");
        //缴费基数
        map.put("paymentBase", 3000);
        //联系地址及邮政编码
        map.put("contactProvince", "上海");
        map.put("contactCity", "上海");
        map.put("contactDistrict", "徐汇");
        map.put("contactStreet", "康健新村");
        map.put("contactNeighborhood", "月季百藤");
        map.put("contactRoad", "桂平");
        map.put("contactLane", 67);
        map.put("contactNumber", 59);
        map.put("contactRoom", 402);
        map.put("contactPostcode", "200233");
        //户籍地址及邮政编码
        map.put("householdProvince", "浙江");
        map.put("householdCity", "宁波");
        map.put("householdDistrict", "象山");
        map.put("householdStreet", "丹东");
        map.put("householdNeighborhood", "百花");
        map.put("householdRoad", "靖南");
        map.put("householdLane", 114);
        map.put("householdNumber", 59);
        map.put("householdRoom", 1402);
        map.put("householdPostcode", "315700");
        userList.add(map);

        map = new HashMap<>();
        //姓名
        map.put("displayName", "文台");
        //身份证号
        map.put("idNumber", "330225199908262278");
        //户籍户别
        map.put("householdCityType", "本市");
        map.put("householdType", "城镇");
        //联系电话
        map.put("tel", "11234");
        map.put("mobile", "13621941789");
        //文化程度
        map.put("educationDegree", "小学");
        //政治面貌
        map.put("politicsStatus", "群众");
        //个人序号
        map.put("serialNumber", 15566);
        //缴费起始年月
        map.put("paymentBegin", "2018.08");
        //缴费基数
        map.put("paymentBase", 3000);
        //联系地址及邮政编码
        map.put("contactProvince", "上海");
        map.put("contactCity", "上海");
        map.put("contactDistrict", "徐汇");
        map.put("contactStreet", "康健新村");
        map.put("contactNeighborhood", "月季百藤");
        map.put("contactRoad", "桂平");
        map.put("contactLane", 67);
        map.put("contactNumber", 59);
        map.put("contactRoom", 402);
        map.put("contactPostcode", "200233");
        //户籍地址及邮政编码
        map.put("householdProvince", "浙江");
        map.put("householdCity", "宁波");
        map.put("householdDistrict", "象山");
        map.put("householdStreet", "丹东");
        map.put("householdNeighborhood", "百花");
        map.put("householdRoad", "靖南");
        map.put("householdLane", 114);
        map.put("householdNumber", 59);
        map.put("householdRoom", 1402);
        map.put("householdPostcode", "315700");
        userList.add(map);

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
        });
        if(userList.size()<10){
            while(userList.size() < 10){
                Map m= new HashMap();
                m.put("status","");
                m.put("epsProject","");
                m.put("remark","");
                userList.add(m);
            }
        }
        int count=userList.size();
        int page = count/10 + count%10>0?1:0;
        int pEnd=0,pStart=0;
        for(int i=0;i<page;i++){
            pEnd=i*10+10;
            if(pEnd>=count){
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
        WordUtils.exportWord(response, resultMap, "社会保险业务变更项目申报表", "社会保险业务变更项目申报表.ftl");

    }
}

