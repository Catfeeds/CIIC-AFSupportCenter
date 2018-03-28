package com.ciicsh.gto.afsupportcenter.employmanagement.employcommandservice.business.utils;

import ch.qos.logback.core.util.StringCollectionUtil;
import com.ciicsh.gto.afsupportcenter.util.StringUtil;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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

        tg_param.put("1","退工成功");
        tg_param.put("2","档案未退先退工");
        tg_param.put("3","退工盖章未返回");
        tg_param.put("4","退工失败");
        tg_param.put("5","前道要求批退");
        tg_param.put("6","自开退工单,未交");
        tg_param.put("7","用工已办未反馈");
        tg_param.put("8","等翻牌联系单");
        tg_param.put("9","退工Ukey外借");
        tg_param.put("10","单项服务,原退工成功");
        tg_param.put("11","转外地社保,原退工成功");
        tg_param.put("12","转人员性质无需退工");
        tg_param.put("13","退工成功,改社保");
        tg_param.put("14","重复任务单");
        tg_param.put("15","等修改备案表");


        yg_param.put("0","空");
        yg_param.put("1","用工成功");
        yg_param.put("2","用工已办查无档");
        yg_param.put("3","用工失败");
        yg_param.put("4","用工Ukey外借");
        yg_param.put("5","前道要求撤销用工");
        yg_param.put("6","用工成功,重复任务单");
        yg_param.put("7","用工已办,前道已中止");


        yw_param.put("1","全日制");
        yw_param.put("2","其他");

        istj_param.put("0","已交");
        istj_param.put("1","未交");

        ygsx_param.put("1","派遣");
        ygsx_param.put("2","代理");
        ygsx_param.put("3","业务外包");


    }

    public  static  String  getReasonOut(String code){
        return param.get(code);

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

        if("1".equals(code)||"2".equals(code)||"6".equals(code)||"7".equals(code))
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

    public static String removeMark(String str){
        if (StringUtil.isEmpty(str))
        {
           return "";
        }
        int last = str.lastIndexOf(",");
        return  str.substring(0,last);
    }

    public  static  String  getCondemnationYears(Date start,Date end)
    {
        long time = end.getTime()-start.getTime();

        double d = (double) time;
        long year = 365*24*60*60*1000l;
        double d_year = (double)year;
        double d1 = d/d_year;

        DecimalFormat df = new DecimalFormat("0.00");
         return  df.format(d1);
    }

}
