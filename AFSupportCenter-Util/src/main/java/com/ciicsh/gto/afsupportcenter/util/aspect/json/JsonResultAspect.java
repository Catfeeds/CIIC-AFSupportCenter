package com.ciicsh.gto.afsupportcenter.util.aspect.json;

import com.ciicsh.gto.afsupportcenter.util.aspect.BasicAspect;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResultKit;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

/**
 * api 拦截
 */
@Aspect
public class JsonResultAspect extends BasicAspect {

    // 配置 controller 环绕通知
    @Around("controllerPointcut()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        // 开始执行 controller 环绕通知
        long start = System.currentTimeMillis();
        try {
            return joinPoint.proceed();
            // 结束执行 controller 环绕通知
        } catch (Throwable e) {
            Class<?> returnType = getMethod(joinPoint).getReturnType();

            // 返回类型是 JsonResult 包装异常信息
            if (!JsonResultKit.isJsonResult(returnType)) {
                long time = System.currentTimeMillis() - start;
                String name = joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName();
                String message = getMessage(e);
                logger.error("API method:{} , time:{}ms , error:{}", name, time, e.getMessage());
                return createErrorJsonResult(returnType, message);
            }
            throw e;
        }
    }
}
