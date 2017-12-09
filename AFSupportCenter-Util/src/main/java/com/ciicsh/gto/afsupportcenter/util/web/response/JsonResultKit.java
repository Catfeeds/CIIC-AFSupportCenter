package com.ciicsh.gto.afsupportcenter.util.web.response;

import com.ciicsh.gto.afsupportcenter.util.page.PageRows;

import java.util.List;

/**
 * JsonResult Kit
 */
public class JsonResultKit {

    // ------------------------------------
    // ---------- 已下是自定义函数----------
    // ------------------------------------

    public static <T> JsonResult<T> of(int code, String message, T data) {
        JsonResult<T> result = new JsonResult();
        result.setData(data);
        result.setCode(code);
        result.setMessage(message);
        return result;
    }

    public static <T> JsonResult<T> of(int code, String message) {
        return of(code, message, (T) null);
    }

    public static <T> JsonResult<List<T>> of(int code, String message, List<T> list) {
        JsonResult<List<T>> result = new JsonResult();
        result.setData(list);
        result.setCode(code);
        result.setMessage(message);
        return result;
    }

    public static <T> JsonResult<List<T>> of(int code, String message, PageRows<T> pageRows) {
        JsonResult<List<T>> result = of(code, message, pageRows.getRows());
        result.setTotal(pageRows.getTotal());
        return result;
    }

    public static <T> JsonResult<T> of() {
        return of(null);
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

    public static <T> JsonResult<T> ofError(String message) {
        return of(500, message);
    }

}
