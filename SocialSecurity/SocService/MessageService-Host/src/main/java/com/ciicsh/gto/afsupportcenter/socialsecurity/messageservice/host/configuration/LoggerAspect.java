package com.ciicsh.gto.afsupportcenter.socialsecurity.messageservice.host.configuration;

import com.ciicsh.gto.afsupportcenter.util.enumeration.LogInfo;
import com.ciicsh.gto.afsupportcenter.util.logservice.LogApiUtil;
import com.ciicsh.gto.afsupportcenter.util.logservice.LogMessage;
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
    LogApiUtil logApiUtil;

    @Pointcut("execution (* com.ciicsh.gto.afsupportcenter.socialsecurity.messageservice.host.message..*.*(..))")
    public void messagePointcut() {
    }

    @AfterThrowing(value = "messagePointcut()", throwing = "e")
    public void doAfterThrow(JoinPoint joinPoint, Throwable e) {
        //获取类名
        String className = joinPoint.getTarget().getClass().getSimpleName();
        //获取方法名
        String methodName = joinPoint.getSignature().getName();
        String msg = String.format("%1$s.%2$s:%3$s", className, methodName, e.toString());
        logApiUtil.info(LogMessage.create().setTitle(LogInfo.SOURCE_API.getKey()).setContent(className + "." + methodName + "#" + msg));
    }
}
