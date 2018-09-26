package com.ciicsh.gto.afsupportcenter.cmjob.configuration;

import com.ciicsh.gto.afsupportcenter.util.exception.BusinessException;
import com.ciicsh.gto.afsupportcenter.util.result.JsonResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import javax.security.auth.message.AuthException;

/**
 * 获取登录信息异常处理
 * @author linhaihai
 */
@ControllerAdvice(annotations = RestController.class)
public class RestExceptionHandler {

    /**
     * 捕捉业务异常
     *
     * @param ex
     * @param req
     * @return
     */
    @ExceptionHandler(value = {BusinessException.class})
    @ResponseBody
    public Object handleSysIllegalArgumentException(BusinessException ex, WebRequest req) {
        return JsonResult.errorsInfo("3",ex.getMessage());
    }

    /**
     * 验证异常
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(AuthException.class)
    @ResponseBody
    public Object handleAuthException(Exception ex) {
        return JsonResult.errorsInfo("2", ex.getMessage());
    }

    /**
     * 捕捉系统异常
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Object handleException(Exception ex) {
        return JsonResult.errorsInfo("1", ex.getMessage());
    }
}
