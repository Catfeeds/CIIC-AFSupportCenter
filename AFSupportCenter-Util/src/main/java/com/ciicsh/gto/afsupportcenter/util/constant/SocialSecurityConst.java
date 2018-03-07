package com.ciicsh.gto.afsupportcenter.util.constant;

import java.util.*;

/**
 * 常量类
 */
public interface SocialSecurityConst {

    public static final String PROCESS_PERIOD_KEY = "ProcessPeriod";
    public static final String PAY_BANK_KEY = "PayBank";
    public static final String FUND_TYPE_KEY = "FundType";
    public static final String COM_ACCOUNT_STATE_KEY = "ComAccountState";
    public static final String COM_PAYMENT_WAY_KEY = "ComPaymentWay";
    public static final String COM_UKEY_STORE_KEY = "ComUkeyStore";
    public static final String OPERATION_REMIND_KEY = "OperationRemind";
    public static final String REPAIR_REASON_KEY = "RepairReason";
    public static final String HANDLE_STATUS_KEY = "HandleStatus";
    public static final String FUND_OUT_UNIT_KEY = "FundOutUnit";
    public static final String REMIT_WAY_KEY = "RemitWay";

    //结算区县
    public static final Map<String, String> DISTRICT_MAP = new HashMap<String, String>() {
        {
            put("0", "徐汇");
            put("1", "长宁");
            put("2", "浦东");
            put("3", "静安");
            put("4", "黄浦");
            put("5", "杨浦");
            put("6", "普陀");
            put("7", "宝山");
            put("8", "虹口");
            put("9", "闵行");
            put("10", "松江");
            put("11", "嘉定");
            put("12", "青浦");
            put("13", "奉贤");
            put("14", "崇明");
            put("15", "金山");
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
    /**
     * 未处理
     */
    String PROCESS_STATUS_1 = "1";
    /**
     * 处理中
     */
    String PROCESS_STATUS_2 = "2";
    /**
     * 已完成
     */
    String PROCESS_STATUS_3 = "3";
    /**
     * 批退
     */
    String PROCESS_STATUS_4 = "4";
    /**
     * 不需处理
     */
    String PROCESS_STATUS_5 = "5";
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

    /**
     * 新进
     */
    String TASK_TYPE_1 = "1";
    /**
     *转入
     */
    String TASK_TYPE_2 = "2";
    /**
     *调整
     */
    String TASK_TYPE_3 = "3";
    /**
     *补缴
     */
    String TASK_TYPE_4 = "4";
    /**
     *转出
     */
    String TASK_TYPE_5 = "5";
    /**
     *封存
     */
    String TASK_TYPE_6 = "6";
    /**
     *退账
     */
    String TASK_TYPE_7 = "7";
    /**
     *特殊操作
     */
    String TASK_TYPE_9 = "9";
    /**
     *翻牌新进
     */
    String TASK_TYPE_12 = "12";
    /**
     *翻牌转入
     */
    String TASK_TYPE_13 = "13";
    /**
     *翻牌转出
     */
    String TASK_TYPE_14 = "14";
    /**
     *翻牌封存
     */
    String TASK_TYPE_15 = "15";


    //任务类型(已创建字典，请从字典服务取得)
    @Deprecated
    public static final Map<String, String> TASK_TYPE_MAP = new LinkedHashMap<String, String>() {
        {
            put(TASK_TYPE_1, "新进");
            put(TASK_TYPE_2, "转入");
            put(TASK_TYPE_3, "调整");
            put(TASK_TYPE_4, "补缴");
            put(TASK_TYPE_5, "转出");
            put(TASK_TYPE_6, "封存");
            put(TASK_TYPE_7, "退账");
            put(TASK_TYPE_9, "特殊操作");
            put(TASK_TYPE_12, "翻牌新进");
            put(TASK_TYPE_13, "翻牌转入");
            put(TASK_TYPE_14, "翻牌转出");
            put(TASK_TYPE_15, "翻牌封存");
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
            put("1", "基本公积金");
            put("2", "补充公积金");
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

    public static final Map<String, String> COM_ACCOUNT_STATE_MAP = new HashMap<String, String>() {
        {
            put("0", "初始");
            put("1", "有效");
            put("2", "终止");
        }
    };

    public static final Map<String, String> HANDLE_STATUS_MAP = new HashMap<String, String>() {
        {
            put("1", "材料收缴");
            put("2", "受理中");
            put("3", "送审中");
            put("4", "已完成");
        }
    };

    public static final List<String> FUND_OUT_UNIT_LIST = new ArrayList<String>() {
        {
//            add("原单位");
            add("市公积金封存办(中心)");
            add("中智大库");
            add("中智外包");
        }
    };

    Map<String, String> REMIT_WAY_MAP = new HashMap<String, String>() {
        {
            put("1", "正常");
            put("2", "补缴");
            put("3", "调整");
        }
    };

    /**
     * 政策类型 1-社保 2-公积金 3-补充公积金（此定义从内控得来）
     */
    int POLICY_TYPE_SOCIAL_SECURITY = 1;
    /**
     * 政策类型 1-社保 2-公积金 3-补充公积金（此定义从内控得来）
     */
    int POLICY_TYPE_HOUSING_FUND = 2;
    /**
     * 政策类型 1-社保 2-公积金 3-补充公积金（此定义从内控得来）
     */
    int POLICY_TYPE_SUPPLEMENTARY_HOUSING_FUND = 3;

    /**
     * 任务单传递过来的社保办理类型 1-新进 2-转入
     */
    int SOCIAL_TYPE_1 = 1;
    /**
     * 任务单传递过来的社保办理类型 1-新进 2-转入
     */
    int SOCIAL_TYPE_2 = 2;
}
