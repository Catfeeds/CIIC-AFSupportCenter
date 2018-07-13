package com.ciicsh.gto.afsupportcenter.util.constant;

import java.util.*;

/**
 * 常量类
 */
public interface HouseFundConst {

    public static final String HF_TYPE_BASE = "1"; //基本公积金
    public static final String HF_TYPE_ADD = "2"; //补充公积金

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
