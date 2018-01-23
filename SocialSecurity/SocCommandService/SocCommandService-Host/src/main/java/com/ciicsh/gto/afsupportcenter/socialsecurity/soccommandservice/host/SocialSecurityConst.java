package com.ciicsh.gto.afsupportcenter.socialsecurity.soccommandservice.host;

import java.util.HashMap;
import java.util.Map;

/**
 * 常量类
 */
public interface SocialSecurityConst {

    //结算区县
    public static final Map<String, String> DISTRICT_MAP = new HashMap<String, String>() {
        {
            put("1", "徐汇");
            put("2", "长宁");
            put("3", "浦东");
            put("4", "卢湾");
            put("5", "静安");
            put("6", "黄埔");
        }
    };

    //账户类型
    public static final Map<String, String> ACCOUNT_TYPE_MAP = new HashMap<String, String>() {
        {
            put("1", "中智大库");
            put("2", "中智外包");
            put("3", "独立户");
        }
    };

    //社保账户状态
    public static final Map<String, String> ACCOUNT_STATUS_MAP = new HashMap<String, String>() {
        {
            put("0", "初始");
            put("1", "有效");
            put("2", "终止");
            put("3", "封存");
        }
    };

    //办理状态
    public static final Map<String, String> PROCESS_STATUS_MAP = new HashMap<String, String>() {
        {
            put("1", "未处理");
            put("2", "处理中");
            put("3", "已完成");
            put("4", "批退");
            put("5", "不需处理");
        }
    };
    //任务类型
    public static final Map<String, String> TASK_TYPE_MAP = new HashMap<String, String>() {
        {
            put("1", "新进");
            put("2", "转入");
            put("3", "调整");
            put("4", "补缴");
            put("5", "转出");
            put("6", "封存");
            put("7", "退账");
            put("9", "特殊操作");
        }
    };

    //办理方式
    public static final Map<String, String> HANDLE_WAY_MAP = new HashMap<String, String>() {
        {
            put("1", "网上申报");
            put("2", "柜面办理");
        }
    };

    //特殊操作任务类型
    public static final Map<String, String> SPECIAL_TASK_TYPE_MAP = new HashMap<String, String>() {
        {
            put("1", "死亡终止");
            put("2", "修改个人信息");
            put("3", "工龄认定");
            put("4", "特殊工种认定");
            put("5", "账户合并");
            put("6", "遗属");
            put("7", "退休");
            put("8", "因病丧劳提前退休");
            put("9", "特殊工种提前退休");
            put("10", "退休高级专家增资");
            put("11", "退休军转增资");
            put("12", "外国人终止提取");
            put("13", "外来人员医保卡领取");
            put("14", "医保帐户提取");
            put("15", "生育津贴领取");
        }
    };
}
