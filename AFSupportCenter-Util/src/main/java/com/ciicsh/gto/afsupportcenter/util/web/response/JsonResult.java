package com.ciicsh.gto.afsupportcenter.util.web.response;

/**
 * 返回给前台的结果（最终转化为 json 形式）
 */
public class JsonResult<T> {

    // 状态码
    protected int code;
    // 信息
    protected String message;
    // 数据
    protected T data;

    // 总数，返回分页数据的时候使用
    protected Long total;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }
}
