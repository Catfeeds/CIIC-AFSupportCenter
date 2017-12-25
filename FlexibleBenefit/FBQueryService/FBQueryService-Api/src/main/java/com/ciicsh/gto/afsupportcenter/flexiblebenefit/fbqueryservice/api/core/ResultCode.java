package com.ciicsh.gto.afsupportcenter.flexiblebenefit.fbqueryservice.api.core;

/**
 * 响应码枚举，参考HTTP状态码的语义
 */
public enum ResultCode {
    //成功
    SUCCESS(200, "成功"),
    FAIL_CLIENT(400, "客户端错误"),
    NOT_FOUND(404, "接口不存在"),
    FAIL_SERVER(600, "服务端错误");
    public int code;
    private String description;

    ResultCode(int code, String description) {
        this.code = code;
        this.description = description;
    }
}
