package com.ciicsh.gto.afsupportcenter.util.constant;

import java.util.*;

/**
 * 常量类
 */
public interface HouseFundConst {

    public static final String HF_TYPE_BASE = "1"; //基本公积金
    public static final String HF_TYPE_ADD = "2"; //补充公积金

    int DEP_HOUSE_FUND_TEAM_ID = 514;

    Map<String, String> BANK_MAP = new HashMap<String, String>() {
        {
            put("15", "徐汇—X");
            put("16", "西郊—C");
            put("17", "东方路—P");
            put("18", "卢湾—L");
            put("0", "黄浦—H");
        }
    };

    // '\u2460' - 居民身份证, '\u2461' - 护照, '\u2462' - 港澳居民往来内地通行证（回乡证）,
    // '\u2463' - 港澳居民居住证, '\u2464' - 台湾居民往来大陆通行证（台胞证）, '\u2465' - 台湾居民居住证, '\u2466' - 外国人永久居留证
    Map<Integer, String> IDENTIFICATION_MAP = new HashMap<Integer, String>() {
        {
            put(1, "\u2460");
            put(2, "\u2461");
            put(3, "");
            put(4, "");
            put(5, "\u2464");
            put(6, "\u2462");
            put(8, "\u2463");
            put(9, "\u2465");
            put(10, "\u2466");
        }
    };
}
