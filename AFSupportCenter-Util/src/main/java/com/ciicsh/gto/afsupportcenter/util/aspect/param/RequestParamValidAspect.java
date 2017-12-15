package com.ciicsh.gto.afsupportcenter.util.aspect.param;

import com.ciicsh.gto.afsupportcenter.util.aspect.BasicAspect;
import com.ciicsh.gto.afsupportcenter.util.exception.BusinessException;
import com.ciicsh.gto.afsupportcenter.util.web.response.JsonResultKit;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.executable.ExecutableValidator;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * 参数校验
 *
 * @see org.springframework.validation.beanvalidation.MethodValidationPostProcessor
 */
@Aspect
@Order(1)// 1 校验，2 处理 JsonResult，99 log
public class RequestParamValidAspect extends BasicAspect {
    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private final ExecutableValidator executableValidator = factory.getValidator().forExecutables();
    private final Validator validator = factory.getValidator();

    private <T> Set<ConstraintViolation<T>> validateParameters(T obj, Method method, Object[] parameterValues) {
        return executableValidator.validateParameters(obj, method, parameterValues);
    }

    private <T> Set<ConstraintViolation<T>> validateReturnValue(T obj, Method method, Object returnValue) {
        return executableValidator.validateReturnValue(obj, method, returnValue);
    }

    private <T> Set<ConstraintViolation<T>> validate(T object) {
        return validator.validate(object);
    }

    // 配置 controller 环绕通知
    @Around("controllerPointcut()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        //  获得切入目标对象
        Object target = joinPoint.getThis();
        // 获得切入方法参数
        Object[] args = joinPoint.getArgs();
        // 获得切入的方法
        Method method = getMethod(joinPoint);

        // 返回值
        Object returnValue = null;
        // 返回类型
        Class<?> returnType = method.getReturnType();

        // 返回类型是 JsonResult
        if (JsonResultKit.isJsonResult(returnType)) {
            try {
                // 检查参数
                validateBefore(target, method, args);
                returnValue = joinPoint.proceed();
                // 检查返回值
                validateAfter(target, method, returnValue);
            } catch (Throwable e) {
                return createErrorJsonResult(returnType, getMessage(e));
            }
        } else {// 不是 JsonResult 直接校验
            // 检查参数
            validateBefore(target, method, args);
            returnValue = joinPoint.proceed();
            // 检查返回值
            validateAfter(target, method, returnValue);
        }

        return returnValue;
    }

    private void validateBefore(Object target, Method method, Object[] args) {
        Set<ConstraintViolation<Object>> violations = validateParameters(target, method, args);
        this.rethrow(violations);
    }

    private <T> void validateAfter(T obj, Method method, Object returnValue) {
        Set<ConstraintViolation<Object>> violations = validateReturnValue(obj, method, returnValue);
        this.rethrow(violations);
    }

    private void rethrow(Set<ConstraintViolation<Object>> violations) {
        Iterator<ConstraintViolation<Object>> violationIterator = violations.iterator();
        List<String> list = new ArrayList<>();
        while (violationIterator.hasNext()) {
            list.add(violationIterator.next().getMessage());
        }

        if (list.size() > 0) {
            BusinessException.rethrow(StringUtils.join(list, "；"));
        }
    }
}

