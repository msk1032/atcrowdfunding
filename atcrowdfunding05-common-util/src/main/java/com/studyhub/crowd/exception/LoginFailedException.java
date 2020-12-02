package com.studyhub.crowd.exception;

/**
 * 登录失败的异常
 * @author haoren
 * @create 2020-12-01 20:48
 */
public class LoginFailedException extends RuntimeException{

    private static final long serialVersionUID = -70348971907457L;

    public LoginFailedException() {
    }

    public LoginFailedException(String message) {
        super(message);
    }

    public LoginFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public LoginFailedException(Throwable cause) {
        super(cause);
    }

    public LoginFailedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
