package com.ciicsh.gto.afsupportcenter.util.exception;

import org.apache.commons.lang3.StringUtils;

/**
 * 业务异常
 */
public class BusinessException extends RuntimeException {

    public BusinessException() {
    }

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusinessException(Throwable cause) {
        super(cause);
    }

    public BusinessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public static BusinessException unchecked(Throwable e) {
        if (e instanceof BusinessException) {
            return (BusinessException) e;
        }
        return new BusinessException(e);
    }

    public static BusinessException unchecked(String message) {
        return new BusinessException(message);
    }

    public static void rethrow(String message) {
        ExceptionKit.<BusinessException>rethrow0(new BusinessException(message));
    }


    public static void rethrow(Throwable e) {
        ExceptionKit.<BusinessException>rethrow0(e);
    }

    // -----------------------------------------------------------
    // -------------------- assert (根据 spring Assert 改造)---------------------------
    // -----------------------------------------------------------

    public static void state(boolean expression, String message) {
        if (!expression) {
            throw new BusinessException(message);
        }
    }

    public static void isTrue(boolean expression, String message) {
        if (!expression) {
            throw new BusinessException(message);
        }
    }


    public static void isNull(Object object, String message) {
        if (object != null) {
            throw new BusinessException(message);
        }
    }

    public static void notNull(Object object, String message) {
        if (object == null) {
            throw new BusinessException(message);
        }
    }

    public static void hasText(String text, String message) {
        if (StringUtils.isBlank(text)) {
            throw new BusinessException(message);
        }
    }

}
