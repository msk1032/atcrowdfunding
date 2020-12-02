package com.studyhub.crowd.exception;

/**
 * 没有访问权限时抛出的异常
 * @author haoren
 * @create 2020-12-02 13:44
 */
public class AccessForbiddenException extends RuntimeException{

    private static final long serialVersionUID = -7034L;

    public AccessForbiddenException() {
        super();
    }

    public AccessForbiddenException(String message) {
        super(message);
    }

    public AccessForbiddenException(String message, Throwable cause) {
        super(message, cause);
    }

    public AccessForbiddenException(Throwable cause) {
        super(cause);
    }

    protected AccessForbiddenException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
