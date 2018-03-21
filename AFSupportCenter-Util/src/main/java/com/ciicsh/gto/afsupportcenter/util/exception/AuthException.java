package com.ciicsh.gto.afsupportcenter.util.exception;

/**
 * 用户验证异常
 *
 * @author sunjian
 * @since 2018-3-5
 */
public class AuthException extends RuntimeException {

    public AuthException(String message) {
        super(message);
    }

    public AuthException(String message, Exception e) {
        super(message, e);
    }
}
