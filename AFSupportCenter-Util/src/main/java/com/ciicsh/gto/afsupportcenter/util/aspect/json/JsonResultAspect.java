package com.ciicsh.gto.afsupportcenter.util.aspect.json;

import com.ciicsh.gto.afsupportcenter.util.aspect.BasicAspect;
import com.ciicsh.gto.afsupportcenter.util.exception.BusinessException;
import com.ciicsh.gto.afsupportcenter.util.kit.JsonKit;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResult;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.util.ConcurrentReferenceHashMap;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

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

        // 返回数据结构不是 JsonResult 跳过
        if (!isJsonResult(method)) {
            return joinPoint.proceed();
        }

        // 开始执行 controller 环绕通知
        long start = System.currentTimeMillis();
        try {
            return joinPoint.proceed();
            // 结束执行 controller 环绕通知
        } catch (Throwable e) {
            long time = System.currentTimeMillis() - start;
            String name = joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName();
            logger.error("API method:{} , time:{}ms , error:{}", name, time, e.getMessage());
            return JsonResultFactory.createErrorJsonResult(method, e);
        }
    }

    /**
     * JsonResult 工厂
     */
    private static class JsonResultFactory {

        /**
         * 创建错误 JsonResult
         *
         * @param method
         * @param e
         * @return
         */
        public static Object createErrorJsonResult(Method method, Throwable e) {
            String message = "服务器错误";
            if (e instanceof BusinessException) {
                message = e.getMessage();
            }
            Map<String, Object> jsonResult = new HashMap<>(2);
            jsonResult.put("message", message);
            jsonResult.put("code", 500);

            return JsonKit.castToObject(jsonResult, method.getReturnType());
        }
    }

    /**
     * 判断返回结构是否是 JsonResult
     *
     * @param method
     * @return
     */
    private boolean isJsonResult(Method method) {
        Class<?> clazz = method.getReturnType();
        Boolean hasJsonResult = Optional.ofNullable(declaredClassCache.get(clazz)).orElse(false);

        if (hasJsonResult) {
            return true;
        }

        // 判断返回结构是否是 JsonResult
        if (JsonResult.class.isAssignableFrom(clazz)
            || (JsonResult.class.getSimpleName().equals(clazz.getSimpleName())
            && ReflectionUtils.findField(clazz, "code") != null
            && ReflectionUtils.findField(clazz, "message") != null
            && ReflectionUtils.findField(clazz, "data") != null)
            ) {
            declaredClassCache.put(clazz, hasJsonResult = true);
        }

        return hasJsonResult;
    }

}
