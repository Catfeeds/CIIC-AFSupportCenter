package com.ciicsh.gto.afsupportcenter.util.kit;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.List;
import java.util.Map;

/**
 * json 工具箱
 */
public class JsonKit {

    /**
     * 将 Object 序列化为 JSON 字符串
     *
     * @param object
     * @return JSON字符串
     */
    public static String toJSONString(Object object) {
        return JSON.toJSONString(object);
    }

    /**
     * 将 JSON 字符串反序列化为对象 Object
     *
     * @return JSON字符串
     */
    public static <T> T parseObject(String json, Class<T> typeRef) {
        return JSON.parseObject(json, typeRef);
    }

    /**
     * 将 JSON 字符串反序列化为 List
     *
     * @param json
     * @param typeRef
     * @param <T>
     * @return
     */
    public static <T> List<T> parseArray(String json, Class<T> typeRef) {
        return JSON.parseArray(json, typeRef);
    }

    /**
     * 将 List 转换化为 指定类型 List
     *
     * @param jsonArr
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> List<T> castToList(List<?> jsonArr, Class<T> clazz) {
        return parseArray(toJSONString(jsonArr), clazz);
    }

    /**
     * 将 Object 转换化为 指定类型 Object
     *
     * @param jsonObj
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T castToObj(Object jsonObj, Class<T> clazz) {
        return parseObject(toJSONString(jsonObj), clazz);
    }

    /**
     * 将 Object 转换化为 指定类型 Object
     *
     * @param map
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T castToObj(Map<String, Object> map, Class<T> clazz) {
        return JSON.toJavaObject(new JSONObject(map), clazz);
    }
}
