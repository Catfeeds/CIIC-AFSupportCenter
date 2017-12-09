package com.ciicsh.gto.afsupportcenter.util.aspect;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 基础 Aspect
 */
public class BasicAspect {
  protected final Logger logger = LoggerFactory.getLogger(getClass());

  /**
   * Get value of annotated method parameter
   */
  public Method getMethod(ProceedingJoinPoint joinPoint) {
    MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
    return methodSignature.getMethod();
  }

}
