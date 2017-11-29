package com.ciicsh.gto.afsupportcenter.util.kit;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.ParserConfig;

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
        return JSON.toJSONString(object);
    }


    /**
     * 将 JSON 字符串反序列化为对象 Object
     *
     * @return JSON字符串
     */
    public static <T> T parseObject(String json, Class<T> typeRef) {
        return jsonParser(json).parseObject(typeRef);
    }

    /**
     * 将 JSON 字符串反序列化为 List
     *
     * @param json
     * @param typeRef
     * @param <T>
     * @return
     */
    public static <T> List<T> parseList(String json, Class<T> typeRef) {
        return jsonParser(json).parseArray(typeRef);
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
        return parseList(toStr(jsonArr), clazz);
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
        return parseObject(toStr(jsonObj), clazz);
    }

    /**
     * json 解析器
     *
     * @param text
     * @return
     */
    private static DefaultJSONParser jsonParser(String text) {
        return new DefaultJSONParser(text, ParserConfig.getGlobalInstance());
    }
}
