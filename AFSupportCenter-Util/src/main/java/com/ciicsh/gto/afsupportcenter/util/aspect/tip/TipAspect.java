package com.ciicsh.gto.afsupportcenter.util.aspect.tip;

import com.ciicsh.gto.afsupportcenter.util.aspect.BasicAspect;
import com.ciicsh.gto.afsupportcenter.util.tips.Tip;
import com.ciicsh.gto.afsupportcenter.util.tips.TipKit;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import java.lang.reflect.Method;

/**
 * api 拦截
 */
@Aspect
public class TipAspect extends BasicAspect {

    // 配置 controller 环绕通知
    @Around("execution (* com.ciicsh.gto.afsupportcenter.*.*.host.controller..*.*(..))")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Method method = getMethod(joinPoint);

        // 返回类型不是 Tip 跳过
        if (!Tip.class.isAssignableFrom(method.getReturnType())) {
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
            return TipKit.ofError(e);
        }
    }
}
