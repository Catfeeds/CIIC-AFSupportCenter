package com.ciicsh.gto.afsupportcenter.employmanagement.employservice.business.utils;

import com.ciicsh.gto.afsupportcenter.util.StringUtil;
import com.ciicsh.gto.afsupportcenter.util.interceptor.authenticate.UserContext;

import java.util.*;

/**
 * Created by zhangzhiwen on 2018/3/5.
 */
public class ReasonUtil {

    private static Map<String,String> param = new HashMap<>();

    private static Map<String,String> tg_param = new HashMap<>();

    private static Map<String,String> yg_param = new HashMap<>();

    private static Map<String,String> yw_param = new HashMap<>();

    private static Map<String,String> istj_param = new HashMap<>();
    /**
     * 用工属性
     */
    private static Map<String,String> ygsx_param = new HashMap<>();

    static {
        param.put("1","辞职");
        param.put("2","协商解除");
        param.put("3","公司解聘");
        param.put("4","合同到期");
        param.put("5","关闭");
        param.put("6","出国");
        param.put("7","退休");
        param.put("8","工伤死亡");
        param.put("9","取消入职通知");
        param.put("10","死亡");
        param.put("11","转科技人才");
        param.put("12","转用工单位（翻牌）");
        param.put("13","公司自行管理, 无需退工");
        param.put("14","公司自行管理, 需办退工");
        param.put("15","转其他公司管理, 无需退工");
        param.put("16","转其他公司管理, 需办退工");

        tg_param.put("1","退工任务单签收");
        tg_param.put("2","档未到先退工");
        tg_param.put("3","退工盖章未返回");
        tg_param.put("4","退工失败");
        tg_param.put("5","前道要求批退");
        tg_param.put("6","撤销退工");
        tg_param.put("7","等修改备案表");
        tg_param.put("8","自开退工单,未交");
        tg_param.put("9","用工已办未反馈");
        tg_param.put("10","等翻牌联系单");
        tg_param.put("11","退工Ukey外借");
        tg_param.put("12","单项服务,原退工成功");
        tg_param.put("13","转外地社保,原退工成功");
        tg_param.put("14","转人员性质无需退工");
        tg_param.put("15","退工成功,改社保");
        tg_param.put("16","重复任务单");
        tg_param.put("17","退工自办");
        tg_param.put("18","等修改材料");


        yg_param.put("0","空");
        yg_param.put("3","用工成功");
        yg_param.put("10","用工已办查无档");
        yg_param.put("4","用工失败");
        yg_param.put("11","Ukey外借");
        yg_param.put("5","前道要求撤销用工");
        yg_param.put("12","用工成功,重复任务单");
        yg_param.put("13","用工已办,前道已中止");
        yg_param.put("14","等修改材料");

        yw_param.put("1","全日制");
        yw_param.put("2","其他");

        istj_param.put("0","已交");
        istj_param.put("1","未交");

        ygsx_param.put("1","独立");
        ygsx_param.put("2","中智");

    }

    public  static  String  getReasonOut(String code){
        if(StringUtil.isEmpty(code)||!param.containsKey(code))
        {
            return  "";
        }
        try {
          return param.get(code);
        } catch (Exception e) {

        }
        return  "";
    }

    /**
     * 退工反馈
     * @param code
     * @return
     */
    public static  String  getTgfk(String code){
        return  tg_param.get(code);
    }

    /**
     * 用工反馈
     * @param code
     * @return
     */
    public static  String  getYgfk(String code){

        if(StringUtil.isEmpty(code)||!yg_param.containsKey(code))
        {
            return  "";
        }

        return  yg_param.get(code);
    }

    public static  String getYgfs(String code){ return  yw_param.get(code); }

    public static  String getIsTj(String code){ return  istj_param.get(code); }

    public static  boolean  getYgResult(String code){

        if("3".equals(code)||"10".equals(code)||"12".equals(code)||"13".equals(code))
        {
            return  true;
        }else {
            return  false;
        }

    }

    public static  String getYgsx(String code){

        if(ygsx_param.containsKey(code))
        {
            return  ygsx_param.get(code);
        }

        return "";

    }

    public  static  String  getCondemnationYears(Date start,Date end)
    {
        if(start==null||end==null){
            return "";
        }
        long time = end.getTime()-start.getTime();

        double d = (double) time;
        long year = 365*24*60*60*1000l;
        double d_year = (double)year;
        double d1 = d/d_year;

        if(d1<=1.0){
            return  "1年(含)及以下";
        }else if(d1>1.0&&d1<=2.0){
            return  "1年-2年(含)";
        }else if(d1>2.0&&d1<=5.0){
            return  "2年-5年(含)";
        }else{
            return  "5年以上";
        }

//        DecimalFormat df = new DecimalFormat("0.00");
//         return  df.format(d1);
    }

    public static  String getUserId(){
        String userId = "System";
        try {
            userId = UserContext.getUserId();
        } catch (Exception e) {

        }
        return  userId;
    }

    public static  String getUserName(){
        String userName = "System";
        try {
            userName = UserContext.getUser().getDisplayName();
        } catch (Exception e) {

        }
        return  userName;
    }

    public static List<String> getMaterialDic(){
        List<String> list = new ArrayList<>();
        list.add("劳动手册");
        list.add("职工登记表");
        list.add("退工单绿联");
        list.add("退工单红联");
        list.add("退工单黄联");
        list.add("退工单白联");
        list.add("存档卡");
        list.add("综保劳动手册");
        list.add("养老补贴凭证");
        list.add("报到证");
        list.add("劳动力登记表");
        list.add("劳动合同期限情况表");
        list.add("告知书");
        list.add("照片");
        list.add("离职证明");
        list.add("劳动手册复印件");
        list.add("退工单复印件");
        list.add("外来从业人员退工备案登记表");

        return  list;
    }

}
