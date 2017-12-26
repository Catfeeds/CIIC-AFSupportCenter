package com.ciicsh.gto.afsupportcenter.util.web.response;

import com.ciicsh.gto.afsupportcenter.util.kit.JsonKit;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;
import org.springframework.util.ConcurrentReferenceHashMap;
import org.springframework.util.ReflectionUtils;

import java.util.List;
import java.util.Map;

/**
 * JsonResult Kit
 */
public class JsonResultKit {

    private static final Map<Class<?>, Boolean> declaredClassCache = new ConcurrentReferenceHashMap<>(256);

    // ------------------------------------
    // ---------- 已下是自定义函数----------
    // ------------------------------------
    public static <T> JsonResult<T> of(int code, String message, T data) {
        JsonResult<T> jsonResult = new JsonResult();
        jsonResult.setData(data);
        jsonResult.setCode(code);
        jsonResult.setMessage(message);
        return jsonResult;
    }

    public static <T> JsonResult<T> of(int code, String message) {
        return of(code, message, (T) null);
    }

    public static <T> JsonResult<List<T>> of(int code, String message, List<T> list) {
        JsonResult<List<T>> jsonResult = new JsonResult();
        jsonResult.setData(list);
        jsonResult.setCode(code);
        jsonResult.setMessage(message);
        return jsonResult;
    }

    public static <T> JsonResult<List<T>> of(int code, String message, PageRows<T> pageRows) {
        JsonResult<List<T>> jsonResult = of(code, message, pageRows.getRows());
        jsonResult.setTotal(pageRows.getTotal());
        return jsonResult;
    }

    public static <T> JsonResult<T> ofError(String message) {
        return of(500, message);
    }

    public static <T> JsonResult<T> of() {
        return of((T) null);
    }

    public static <T> JsonResult<T> of(T data) {
        return of(200, "操作成功", data);
    }

    public static <T> JsonResult<List<T>> ofPage(PageRows<T> pageRows) {
        return of(200, "操作成功", pageRows);
    }

    public static <T> JsonResult<List<T>> ofList(List<T> list) {
        return of(200, "操作成功", list);
    }

    // ------------------------------------
    // ---------- 已下是自定义函数 jsonResult 结构转换 ----------
    // ------------------------------------
    public static <J, T> J ofPage(PageRows<T> pageRows, Class<J> jsonResultClass) {
        JsonResult<List<T>> jsonResult = ofPage(pageRows);
        return of(jsonResult, jsonResultClass);
    }

    public static <J, T> J ofList(List<T> list, Class<J> jsonResultClass) {
        JsonResult<List<T>> jsonResult = ofList(list);
        return of(jsonResult, jsonResultClass);
    }

    public static <J, T> J of(T data, Class<J> jsonResultClass) {
        if (data instanceof JsonResult) {
            return JsonKit.castToObject(data, jsonResultClass);
        }
        return JsonKit.castToObject(of(data), jsonResultClass);
    }

    public static <J> J of(Class<J> jsonResultClass) {
        return of(null, jsonResultClass);
    }


    /**
     * 判断返回结构是否是 JsonResult
     *
     * @param clazz
     * @return
     */
    public static boolean isJsonResult(Class<?> clazz) {
        Boolean hasJsonResult = declaredClassCache.get(clazz);

        if (hasJsonResult == null) {
            // 判断返回结构是否是 JsonResult
            if (JsonResult.class.isAssignableFrom(clazz)
                || (JsonResult.class.getSimpleName().equals(clazz.getSimpleName())
                && ReflectionUtils.findField(clazz, "code") != null
                && ReflectionUtils.findField(clazz, "message") != null
                && ReflectionUtils.findField(clazz, "data") != null)
                ) {
                hasJsonResult = true;
            } else {
                hasJsonResult = false;
            }
            declaredClassCache.put(clazz, hasJsonResult);
        }

        return hasJsonResult;
    }
}
