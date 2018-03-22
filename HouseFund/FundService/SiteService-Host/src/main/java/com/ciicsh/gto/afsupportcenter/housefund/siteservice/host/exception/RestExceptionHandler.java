package com.ciicsh.gto.afsupportcenter.housefund.siteservice.host.exception;

import com.ciicsh.gto.afsupportcenter.util.exception.AuthException;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 异常以及业务错误处理
 * 1.业务异常，可以在controller中抛出自定义的业务异常，将业务描述放入message中，这里将统一处理
 * 2.系统异常处理：异常一般是不可预知的，需要以server err的方式返回给前台
 *
 * @author sunjian
 */
@ControllerAdvice(annotations = RestController.class)
public class RestExceptionHandler {
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
        jsonResult.setCode(2);
        jsonResult.setMessage(ex.getMessage());
        return jsonResult;
    }
}
