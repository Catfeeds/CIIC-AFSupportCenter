package com.ciicsh.gto.afsupportcenter.util.aspect.log;

import com.alibaba.fastjson.JSON;
import com.ciicsh.gto.afsupportcenter.util.aspect.BasicAspect;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;

import java.lang.reflect.Method;

/**
 * 日志记录
 *
 * @interface Log
 */
@Aspect
@Order(99)// 1 校验，2 处理 JsonResult，99 log
public class LogAspect extends BasicAspect {

  // 配置controller环绕通知,使用在方法aspect()上注册的切入点
  @Around("controllerPointcut()")
  public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
    Method method = getMethod(joinPoint);

    // 日志信息
    Log info = method.getAnnotation(Log.class);
    // 没有日志信息跳过
    if (info == null) {
      return joinPoint.proceed();
    }

    // 开始执行 controller 环绕通知
    long start = System.currentTimeMillis();
    Object returnValue = null;
    Throwable throwable = null;

    Object arg = joinPoint.getArgs().length == 1 ? joinPoint.getArgs()[0] : joinPoint.getArgs();
    try {
      returnValue = joinPoint.proceed();
    } catch (Throwable e) {
      throwable = e;
    }
    // 结束执行 controller 环绕通知
    long end = System.currentTimeMillis();


    String title = getTitle(method) + info.value();
    String name = joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName();
    String param = JSON.toJSONString(arg);
    long time = end - start;

    if (throwable != null) {
      logger.error("title:{} , method:{} , param:{} , time:{}ms , error: {}", title, name, param, time,
          throwable.getMessage());
      throw throwable;
    }
    logger.info("title:{} , method:{} , param:{} , time:{}ms", title, name, param, time);
    return returnValue;
  }

  /**
   * 获得 class 上的 info
   *
   * @param method
   * @return
   */
  String getTitle(Method method) {
    // 日志信息
    Log info = method.getDeclaringClass().getAnnotation(Log.class);
    if (info == null) {
      return "";
    }
    return info.value();
  }
}
