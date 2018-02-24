package com.ciicsh.gto.afsupportcenter.util.web.convert;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
  * @Description: json处理工具类
  *
  * @Author: ningkuilong/宁奎龙
  * @Date: 2017/12/26 17:46
  */
public class JsonUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(JsonUtil.class);
	
	/**
	 * 对象转换成JSON字符串
	 * @param obj
	 * @return
	 */
	public static String toJson(Object obj) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			return mapper.writeValueAsString(obj);
		} catch (Exception e) {
			logger.error("convert obj to json error. obj="+obj, e);
		}
		return null;
	}
	
	public static <T> T fromJsonToObject(String json, Class<T> clazz) {
		if(null==json || "".equals(json.trim())) {
			return null;
		}
		try {
			ObjectMapper mapper = new ObjectMapper();
			return mapper.readValue(json.trim(), clazz);
		} catch (Exception e) {
			logger.error("convert json to object error. json="+json, e);
		}
		return null;
	}
	
	/**
	 * 将json字符串转换成List
	 * @param value
	 * @param listClass
	 * @param objClass
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static <T> List<T> fromJsonToList(String value, Class<List> listClass, Class<T> objClass) {
		List<T> list = null;
		try {
			ObjectMapper mapper = new ObjectMapper();
			list = mapper.readValue(value, getCollectionType(mapper, listClass, objClass));
		} catch (Exception e) {
			logger.error("convert json to list error. value="+value, e);
		}
		return list;
	}

	/**
	 * 将对象(DTO/PO/BO) 转换 为Map
	 * @param object
	 * @return
	 */
	public static Map<String, Object> fromObjectToMap(Object object) {
		if(null==object) {
			return null;
		}
		try {
			ObjectMapper mapper = new ObjectMapper();
			return mapper.convertValue(object, Map.class);
		} catch (Exception e) {
			logger.error("convert object to map error. object="+object, e);
		}
		return null;
	}
	
	/**
	 * 获取类型
	 * @param mapper
	 * @param collectionClass
	 * @param elementClasses
	 * @return
	 */
	private static JavaType getCollectionType(ObjectMapper mapper, Class<?> collectionClass, Class<?>... elementClasses) {
		return mapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
	}


}
