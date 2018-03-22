package com.ciicsh.gto.afsupportcenter.util.interceptor.authenticate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.security.auth.message.AuthException;

/**
 * 获取登录信息异常处理
 * @author linhaihai
 */
@ControllerAdvice(annotations = RestController.class)
public class RestExceptionHandler {
    private static Logger log = LoggerFactory.getLogger(RestExceptionHandler.class);

    /**
     * 验证异常
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(AuthException.class)
    @ResponseBody
    public Object handleAuthException(Exception ex) {
        JsonResult jsonResult = new JsonResult();
        //2
        jsonResult.setCode(JsonResult.MsgCode.NO_PERMISSION);
        jsonResult.setMsg(ex.getMessage());
        return jsonResult;
    }

}
