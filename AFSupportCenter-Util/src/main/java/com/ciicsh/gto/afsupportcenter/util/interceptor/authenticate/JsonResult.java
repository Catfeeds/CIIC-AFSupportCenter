package com.ciicsh.gto.afsupportcenter.util.interceptor.authenticate;


public class JsonResult<T> {
    private String code = "0";
    private String msg = "";
    private T data;
    private Object dto;

    public enum MsgCode {
        SUCCESS("0", "SUCCESS"),
        NO_PERMISSION("2", "NO_PERMISSION"),
        REMOTE_INVOCATION_ERROR("300", "远程服务接口调用异常"),
        SERVICE_BUSY("201", "服务器繁忙，请稍后再试！"),
        ILLEGAL_ARGUMENT("400", "非法的请求参数");
        private String code;
        private String msg;

        MsgCode(String code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        public String getCode() {
            return code;
        }

        public String getMsg() {
            return msg;
        }
    }

    public JsonResult() {
    }

    public JsonResult(String code) {
        this.code = code;
    }

    public JsonResult(T data) {
        this.data = data;
    }

    public JsonResult(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public JsonResult(MsgCode msgCode) {
        this.code = msgCode.code;
        this.msg = msgCode.msg;
    }

    public JsonResult(String errorcode, T data) {
        this.code = errorcode;
        this.data = data;
    }

    public JsonResult(String errorcode, String errormsg, T data) {
        this.code = errorcode;
        this.msg = errormsg;
        this.data = data;
    }

    public void setCode(MsgCode msgCode) {
        this.code = msgCode.code;
        this.msg = msgCode.msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Object getDto() {
        return dto;
    }

    public void setDto(Object dto) {
        this.dto = dto;
    }
}
