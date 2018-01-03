package com.ciicsh.gto.demo;

import com.alibaba.fastjson.JSON;
import org.apache.ibatis.scripting.xmltags.ExpressionEvaluator;
import org.apache.ibatis.scripting.xmltags.OgnlCache;

import java.util.HashMap;
import java.util.Map;

public class TestOGNL {
    public static void main(String[] args) {
//        ExpressionEvaluator evaluator = null;
        Map<String, Object> params = new HashMap<>();
        params.put("taskStatus", 3);
        String expression = "taskStatus == 3 or taskStatus == 4 or taskStatus == 5";
        Object root = params;
        System.out.println(JSON.toJSONString(OgnlCache.getValue(expression, root)));
    }
}
