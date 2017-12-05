package com.ciicsh.gto.afsupportcenter.util.tips;

import com.ciicsh.gto.afsupportcenter.util.exception.BusinessException;
import com.ciicsh.gto.afsupportcenter.util.page.PageRows;

import java.util.List;

/**
 * Tip Kit
 */
public class TipKit {

    // ------------------------------------
    // ---------- 已下是自定义函数----------
    // ------------------------------------

    public static <T> Tip<T> of(int code, String message, T data) {
        Tip<T> tip = new Tip();
        tip.setData(data);
        tip.setCode(code);
        tip.setMessage(message);
        return tip;
    }

    public static <T> Tip<T> of(int code, String message) {
        return of(code, message, (T) null);
    }

    public static <T> ListTip<T> of(int code, String message, List<T> list) {
        ListTip<T> tip = new ListTip();
        tip.setData(list);
        tip.setCode(code);
        tip.setMessage(message);
        return tip;
    }

    public static <T> PageTip<T> of(int code, String message, PageRows<T> pageRows) {
        PageTip<T> tip = new PageTip();
        tip.setData(pageRows.getRows());
        tip.setCode(code);
        tip.setMessage(message);
        tip.setTotal(pageRows.getTotal());
        return tip;
    }

    public static <T> Tip<T> ofError(String message) {
        return of(-1, message);
    }

    public static <T> Tip<T> ofError(Throwable e) {
        String message = "服务器错误";
        if (e instanceof BusinessException) {
            message = e.getMessage();
        }
        return of(500, message);
    }

    public static <T> Tip<T> of() {
        return of(null);
    }

    public static <T> Tip<T> of(T data) {
        return of(200, "操作成功", data);
    }

    public static <T> PageTip<T> ofPage(PageRows<T> pageRows) {
        return of(200, "操作成功", pageRows);
    }

    public static <T> ListTip<T> ofList(List<T> list) {
        return of(200, "操作成功", list);
    }

}
