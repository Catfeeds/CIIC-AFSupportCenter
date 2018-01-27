package com.ciicsh.gto.afsupportcenter.util.aspect;

import com.ciicsh.gto.afsupportcenter.util.exception.BusinessException;
import com.ciicsh.gto.afsupportcenter.util.kit.JsonKit;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResult;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResultKit;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

/**
 * 基础 Aspect
 */
public class BasicAspect {
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * Get value of annotated method parameter
     */
    public final Method getMethod(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        return methodSignature.getMethod();
    }

    @Pointcut("execution (* com.ciicsh.gto.afsupportcenter.*.*.host.controller..*.*Query(..))")
    public void controllerPointcut() {
    }


    /**
     * 获得异常信息
     *
     * @param e
     * @return
     */
    public String getMessage(Throwable e) {
        String message = "服务器错误";
        if (e instanceof BusinessException) {
            message = e.getMessage();
        }
        return message;
    }

    /**
     * 创建错误 JsonResult
     *
     * @param clazz
     * @return
     */
    public Object createErrorJsonResult(Class<?> clazz, String message) {
        JsonResult jsonResult = JsonResultKit.ofError(message);
        return JsonKit.castToObject(jsonResult, clazz);
    }

}
