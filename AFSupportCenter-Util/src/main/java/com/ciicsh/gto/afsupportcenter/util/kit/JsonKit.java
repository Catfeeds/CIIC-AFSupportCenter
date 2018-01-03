package com.ciicsh.gto.afsupportcenter.util.kit;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

import java.lang.reflect.Type;
import java.util.List;

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
    public static String toStr(Object object) {
        return toStr(object, null);
    }

    /**
     * 将 Object 序列化为 JSON 字符串
     *
     * @param object
     * @param defaultValue
     * @return JSON字符串
     */
    public static String toStr(Object object, String defaultValue) {
        if (object == null) {
            return defaultValue;
        }
        return JSON.toJSONString(object);
    }

    /**
     * 将 JSON 字符串反序列化为对象 Object
     *
     * @return JSON字符串
     */
    public static <T> T parseObject(String json, Class<T> clazz) {
        return parseObject(json, (Type) clazz);
    }

    /**
     * 将 JSON 字符串反序列化为对象 Object
     *
     * @return JSON字符串
     */
    public static <T> T parseObject(String json, TypeReference<T> typeReference) {
        return parseObject(json, typeReference.getType());
    }

    /**
     * 将 JSON 字符串反序列化为对象 Object
     *
     * @return JSON字符串
     */
    public static <T> T parseObject(String json, Type type) {
        return JSON.parseObject(json, type);
    }

    /**
     * 将 JSON 字符串反序列化为 List
     *
     * @param json
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> List<T> parseList(String json, Class<T> clazz) {
        return JSON.parseArray(json, clazz);
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
        return parseList(toStr(jsonArr, "[]"), clazz);
    }

    /**
     * 转 JSONArray
     *
     * @param jsonArr
     * @return
     */
    public static JSONArray castToList(List<?> jsonArr) {
        return JSON.parseArray(toStr(jsonArr, "[]"));
    }


    /**
     * 将 Object 转换化为 指定类型 Object
     *
     * @param jsonObj
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T castToObject(Object jsonObj, Class<T> clazz) {
        return parseObject(toStr(jsonObj, "{}"), clazz);
    }

    /**
     * 转 JSONObject
     *
     * @param jsonObj
     * @return
     */
    public static JSONObject castToObject(Object jsonObj) {
        return parseObject(toStr(jsonObj, "{}"), JSONObject.class);
    }

}
