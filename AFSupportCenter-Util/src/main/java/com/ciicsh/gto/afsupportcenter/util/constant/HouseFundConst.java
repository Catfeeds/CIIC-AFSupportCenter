package com.ciicsh.gto.afsupportcenter.util.constant;

import java.util.*;

/**
 * 常量类
 */
public interface HouseFundConst {

    public static final String HF_TYPE_BASE = "1"; //基本公积金
    public static final String HF_TYPE_ADD = "2"; //补充公积金

    int DEP_HOUSE_FUND_TEAM_ID = 514;
    String CENTER_ACCOUNT_NAME = "市公积金封存办(中心)";

    Map<String, String> BANK_MAP = new HashMap<String, String>() {
        {
            put("15", "徐汇—X");
            put("16", "西郊—C");
            put("17", "东方路—P");
            put("18", "卢湾—L");
            put("0", "黄浦—H");
        }
    };
}
