package com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbcommandservice.api.dto;

/**
 * Created by shil on 2017/9/20.
 */
public class JsonResult {

    public enum ErrorCode {
        SUCCESS("0", "SUCCESS"),
        REMOTEINVOCATIONERROR("300", "远程服务接口调用异常"),
        ILLEGAL_ARGUMENT("400", "非法的请求参数")
        ;
        private String code;
        private String msg;
        ErrorCode(String code, String msg) {
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

    public JsonResult(){}
    public JsonResult(ErrorCode errorCode) {
        this.errorcode = errorCode.code;
        this.errormsg = errorCode.msg;
    }
    public void setCode(ErrorCode errorCode) {
        this.errorcode = errorCode.code;
        this.errormsg = errorCode.msg;
    }
    public JsonResult(String errorcode) {
        this.errorcode = errorcode;
    }

    public JsonResult(Object data){
        this.data = data;
    }

    public JsonResult(String errorcode, String errormsg) {
        this.errorcode = errorcode;
        this.errormsg = errormsg;
    }

    public JsonResult(String errorcode, Object data){
        this.errorcode = errorcode;
        this.data = data;
    }

    public JsonResult(String errorcode, String errormsg, Object data){
        this.errorcode = errorcode;
        this.errormsg = errormsg;
        this.data = data;
    }

    private String errorcode = "0";
    private String errormsg = "";
    private Object data = "";

    public String getErrorcode(){
        return errorcode;
    }

    public void setErrorcode(String errorcode){
        this.errorcode = errorcode;
    }

    public String getErrormsg() {
        return errormsg;
    }

    public void setErrormsg(String errormsg) {
        this.errormsg = errormsg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
