package com.ciicsh.gto.afsupportcenter.util.aspect.json;

import com.ciicsh.gto.afsupportcenter.util.aspect.BasicAspect;
import com.ciicsh.gto.afsupportcenter.util.exception.BusinessException;
import com.ciicsh.gto.afsupportcenter.util.kit.JsonKit;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResult;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResultKit;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.util.ConcurrentReferenceHashMap;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * api 拦截
 */
@Aspect
public class JsonResultAspect extends BasicAspect {

    private static final Map<Class<?>, Boolean> declaredClassCache = new ConcurrentReferenceHashMap<>(256);

    // 配置 controller 环绕通知
    @Around("execution (* com.ciicsh.gto.afsupportcenter.*.*.host.controller..*.*(..))")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Method method = getMethod(joinPoint);

        long start = System.currentTimeMillis();
        try {
            return joinPoint.proceed();
        } catch (Throwable e) {
            // 处理返回数据结构是 JsonResult 异常
            if (isJsonResult(method)) {
                long time = System.currentTimeMillis() - start;
                String name = joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName();
                String message = getMessage(e);
                logger.error("API method:{} , time:{}ms , error:{}", name, time, message);
                return JsonResultFactory.createErrorJsonResult(method, message);
            }
            throw e;
        }
    }

    /**
     * 获得异常信息
     *
     * @param e
     * @return
     */
    private String getMessage(Throwable e) {
        String message = "服务器错误";
        if (e instanceof BusinessException) {
            message = e.getMessage();
        }
        return message;
    }

    /**
     * 判断返回结构是否是 JsonResult
     *
     * @param method
     * @return
     */
    private boolean isJsonResult(Method method) {
        Class<?> clazz = method.getReturnType();
        Boolean hasJsonResult = declaredClassCache.get(clazz);

        if (hasJsonResult == null) {
            // 判断返回结构是否是 JsonResult
            if (JsonResult.class.isAssignableFrom(clazz)
                || (JsonResult.class.getSimpleName().equals(clazz.getSimpleName())
                && ReflectionUtils.findField(clazz, "code") != null
                && ReflectionUtils.findField(clazz, "message") != null
                && ReflectionUtils.findField(clazz, "data") != null)
                ) {
                hasJsonResult = true;
            } else {
                hasJsonResult = false;
            }
            declaredClassCache.put(clazz, hasJsonResult);
        }

        return hasJsonResult;
    }

    /**
     * JsonResult 工厂
     */
    private static class JsonResultFactory {

        /**
         * 创建错误 JsonResult
         *
         * @param method
         * @return
         */
        public static Object createErrorJsonResult(Method method, String message) {
            JsonResult jsonResult = JsonResultKit.ofError(message);
            return JsonKit.castToObject(jsonResult, method.getReturnType());
        }
    }

}
