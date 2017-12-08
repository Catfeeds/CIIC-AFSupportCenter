package com.ciicsh.gto.demo;

import com.alibaba.fastjson.JSON;
import org.apache.ibatis.scripting.xmltags.ExpressionEvaluator;
import org.apache.ibatis.scripting.xmltags.OgnlCache;

import java.util.HashMap;
import java.util.Map;

public class TestOGNL {
    public static void main(String[] args) {
//        ExpressionEvaluator evaluator = null;
        ExpressionEvaluator evaluator = null;
        Map<String, String> params = new HashMap<>();
        params.put("ids", "1,2,3");
//        Object value = OgnlCache.getValue("ids.split(',')", params);
        Object value = OgnlCache.getValue("ids.indexOf(',') > 0", params);
        System.out.println(JSON.toJSONString(value));
    }
}
