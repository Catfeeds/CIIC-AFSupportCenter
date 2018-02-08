package com.ciicsh.gto.afsupportcenter.util.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * 常量类
 */
public interface SocialSecurityConst {

    public static final String PROCESS_PERIOD_KEY = "ProcessPeriod";
    public static final String PAY_BANK_KEY = "PayBank";
    public static final String FUND_TYPE_KEY = "FundType";
    public static final String COM_PAYMENT_WAY_KEY = "ComPaymentWay";
    public static final String COM_UKEY_STORE_KEY = "ComUkeyStore";
    public static final String OPERATION_REMIND_KEY = "OperationRemind";
    public static final String REPAIR_REASON_KEY = "RepairReason";

    //结算区县
    public static final Map<String, String> DISTRICT_MAP = new HashMap<String, String>() {
        {
            put("1", "徐汇");
            put("2", "长宁");
            put("3", "浦东");
            put("4", "卢湾");
            put("5", "静安");
            put("6", "黄浦");
        }
    };

    //账户类型(已创建字典，请从字典服务取得)
    @Deprecated
    public static final Map<String, String> ACCOUNT_TYPE_MAP = new HashMap<String, String>() {
        {
            put("1", "中智大库");
            put("2", "中智外包");
            put("3", "独立户");
        }
    };

    //社保账户状态(已创建字典，请从字典服务取得)
    @Deprecated
    public static final Map<String, String> ACCOUNT_STATUS_MAP = new HashMap<String, String>() {
        {
            put("0", "初始");
            put("1", "有效");
            put("2", "终止");
            put("3", "封存");
        }
    };

    //办理状态(已创建字典，请从字典服务取得)
    @Deprecated
    public static final Map<String, String> PROCESS_STATUS_MAP = new HashMap<String, String>() {
        {
            put("1", "未处理");
            put("2", "处理中");
            put("3", "已完成");
            put("4", "批退");
            put("5", "不需处理");
        }
    };

    //任务类型(已创建字典，请从字典服务取得)
    @Deprecated
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

    // 人员属性(已创建字典，请从字典服务取得)
    @Deprecated
    public static final Map<String, String> EMP_CLASSIFY_MAP = new HashMap<String, String>() {
        {
            put("1", "本地");
            put("2", "外地");
            put("3", "外籍三险");
            put("4", "外籍五险");
            put("5", "延迟退休人员");
        }
    };

    // 处理周期
    public static final Map<String, String> PROCESS_PERIOD_MAP = new HashMap<String, String>() {
        {
            put("1", "本月处理");
            put("2", "下月处理");
        }
    };

    // 缴费银行
    public static final Map<String, String> PAY_BANK_MAP = new HashMap<String, String>() {
        {
            put("0", "徐汇");
            put("1", "长宁");
            put("2", "浦东");
            put("3", "卢湾");
            put("4", "静安");
            put("5", "黄浦");
        }
    };

    // 公积金类型
    public static final Map<String, String> FUND_TYPE_MAP = new HashMap<String, String>() {
        {
            put("0", "基本公积金");
            put("1", "补充公积金");
        }
    };

    // 企业公积金账户付款方式
    public static final Map<String, String> COM_PAYMENT_WAY_MAP = new HashMap<String, String>() {
        {
            put("1", "自付");
            put("2", "我司付款");
            put("3", "垫付");
        }
    };

    // 企业公积金U盾
    public static final Map<String, String> COM_UKEY_STORE_MAP = new HashMap<String, String>() {
        {
            put("0", "无");
            put("1", "有(中智代办)");
        }
    };

    // 操作提示
    public static final Map<String, String> OPERATION_REMIND_MAP = new HashMap<String, String>() {
        {
            put("1", "要做");
            put("2", "中心");
            put("3", "中智");
            put("4", "原单位");
            put("5", "外服");
            put("6", "不做");
            put("7", "外包");
            put("8", "其他独立开户公司");
        }
    };

    // 补缴类型
    public static final Map<String, String> REPAIR_REASON_MAP = new HashMap<String, String>() {
        {
            put("1", "漏缴补缴");
            put("2", "少缴补缴");
            put("3", "欠款单位补缴");
            put("4", "外省市转入补缴");
            put("5", "错缴更正补缴");
            put("6", "特殊补缴");
            put("7", "账外补缴");
        }
    };
}
