package com.ciicsh.gto.afsupportcenter.util;

import com.alibaba.fastjson.JSON;

import java.util.List;

public final class JSONConverter {
    private JSONConverter() { }

    public static <T> List<T> convertToEntityArr(Object jsonObj, Class<T> clazz) {
        return JSON.parseArray(JSON.toJSONString(jsonObj), clazz);
    }

    public static <T> T convertToEntity(Object jsonObj, Class<T> clazz) {
        return JSON.parseObject(JSON.toJSONString(jsonObj), clazz);
    }
}
