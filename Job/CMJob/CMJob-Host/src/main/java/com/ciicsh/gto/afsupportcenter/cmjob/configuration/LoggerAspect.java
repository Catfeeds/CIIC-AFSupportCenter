package com.ciicsh.gto.afsupportcenter.cmjob.configuration;

import com.ciicsh.gto.logservice.api.LogServiceProxy;
import com.ciicsh.gto.logservice.api.dto.LogDTO;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;


@Aspect
@Component
public class LoggerAspect {

    @Autowired
    private LogServiceProxy logger;

    @Value("${app.id}")
    private String appId;

    ThreadLocal<Long> startTime = new ThreadLocal<>();

    @Pointcut("execution (* com.ciicsh.gto.afsupportcenter.cmjob.task..*.*(..))")
    public void controllerPointcut() {
    }

    @Before("controllerPointcut()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {

          startTime.set(System.currentTimeMillis());

        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if(attributes ==null) return;

        HttpServletRequest request = attributes.getRequest();

        // 记录下请求内容
        String msg = "HTTP_METHOD : " + request.getMethod() + "\n"
                + "IP : " + request.getRemoteAddr() + "\n"
                + "CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName() + "\n"
                + "ARGS : " + Arrays.toString(joinPoint.getArgs()) + "\n";

        logger.info(LogDTO.of().setAppId(appId).setTitle(request.getRequestURL().toString()).setContent(msg));

    }

    @AfterReturning(returning = "ret", pointcut = "controllerPointcut()")
    public void doAfterReturning(Object ret) throws Throwable {
        // 处理完请求，返回内容
        String msg = ret + "\n"
                + "SPEND TIME : " + (System.currentTimeMillis() - startTime.get());
        logger.info(LogDTO.of().setAppId(appId).setTitle("RESPONSE").setContent(msg));
    }

    @AfterThrowing(value = "controllerPointcut()", throwing = "e")
    public void doAfterThrow(JoinPoint joinPoint, Throwable e) {
        //获取类名
        String className = joinPoint.getTarget().getClass().getSimpleName();
        //获取方法名
        String methodName = joinPoint.getSignature().getName();
        String msg = String.format("%1$s.%2$s:%3$s",className ,methodName , e.toString());
        logger.error(LogDTO.of().setAppId(appId).setTitle(className + "." + methodName).setContent(msg));

    }


}
