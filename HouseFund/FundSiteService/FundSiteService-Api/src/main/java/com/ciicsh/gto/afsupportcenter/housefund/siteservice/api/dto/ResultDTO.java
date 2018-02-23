package com.ciicsh.gto.afsupportcenter.housefund.siteservice.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by houwanhua on 2018/2/23.
 */
@Data
@AllArgsConstructor
public class ResultDTO<T> implements Serializable {

    /**
     * 操作是否成功，默认为true
     */
    private boolean isSuccess = true;
    /**
     * 提示给用户看的信息，不管是成功还是失败的
     */
    private String message;
    /**
     * 错误码
     */
    private String errCode;
    /**
     * 返回给前端的数据对象
     */
    private T data;

    public ResultDTO(boolean isSuccess, String message) {
        this.isSuccess = isSuccess;
        this.message = message;
    }

    /**
     * 返回一条信息，自定义成功或失败状态
     * @param success
     * @param message
     * @return
     */
    public static ResultDTO message(boolean success, String message) {
        return new ResultDTO(success, message);
    }

    /**
     * 失败，自定义错误提示
     * @param message
     * @return
     */
    public static ResultDTO faultMessage(String message) {
        return new ResultDTO(false, message = message);
    }

    /**
     * 失败，使用默认提示
     * @return
     */
    public static ResultDTO faultMessage() {
        return new ResultDTO(false, "操作失败");
    }

    /**
     * 成功：有需要返回Date信息,自定义成功提示
     * @param data
     * @param message
     * @return
     */
    public static ResultDTO success(Object data, String message) {
        return new ResultDTO(true, message, null, data);
    }

    /**
     * 成功：有需要返回Date信息
     * @param data
     * @return
     */
    public static ResultDTO success(Object data) {
        return new ResultDTO(true, "操作成功", null, data);
    }

    /**
     * 失败：返回错误信息
     * @param errCode
     * @param message
     * @return
     */
    public static ResultDTO errorsInfo(String errCode, String message) {
        return new ResultDTO(false, message, errCode, null);
    }
}
