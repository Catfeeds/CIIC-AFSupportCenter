package com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbqueryservice.entity.bo;

/**
 * Created by xi on 2017年11月27日14:47:52
 */
public class JsonResult {

    public enum Code {
        SUCCESS("200", "SUCCESS"),
        REMOTEINVOCATIONERROR("300", "远程服务接口调用异常"),
        ILLEGAL_ARGUMENT("400", "非法的请求参数");
        private String code;
        private String msg;

        Code(String code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        public String getCode() {
            return code;
        }

        public String getMsg(JsonResult jr) {
            return msg;
        }
    }

    public JsonResult() {
    }

    public JsonResult(Code errorCode) {
        this.code = errorCode.code;
        this.msg = errorCode.msg;
    }

    public void setCode(Code errorCode) {
        this.code = errorCode.code;
        this.msg = errorCode.msg;
    }

    public JsonResult(String code) {
        this.code = code;
    }

    public JsonResult(Object data) {
        this.data = data;
    }

    public JsonResult(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public JsonResult(String code, Object data) {
        this.code = code;
        this.data = data;
    }

    public JsonResult(String code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    private String code = "0";
    private String msg = "";
    private Object data = "";

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

    public void setData(Object data) {
        this.data = data;
    }
}
