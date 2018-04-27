package com.ciicsh.gto.afsupportcenter.socialsecurity.apiservice.host.configuration;

import com.ciicsh.gto.afsupportcenter.util.enumeration.LogInfo;
import com.ciicsh.gto.afsupportcenter.util.logService.LogContext;
import com.ciicsh.gto.afsupportcenter.util.logService.LogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class LoggerAspect {

    @Autowired
    LogService logService;

    @Pointcut("execution (* com.ciicsh.gto.afsupportcenter.socialsecurity.apiservice.host.controller..*.*(..))")
    public void controllerPointcut() {
    }

    @AfterThrowing(value = "controllerPointcut()", throwing = "e")
    public void doAfterThrow(JoinPoint joinPoint, Throwable e) {
        //获取类名
        String className = joinPoint.getTarget().getClass().getSimpleName();
        //获取方法名
        String methodName = joinPoint.getSignature().getName();
        String msg = String.format("%1$s.%2$s:%3$s",className ,methodName , e.toString());
        logService.error(LogContext.of().setSource(LogInfo.SOURCE_API.getKey()).setTitle(className + "." + methodName).setTextContent(msg));
    }
}
