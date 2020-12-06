package com.studyhub.crowd.exception;

/**
 * 检测到用户的账号重复异常
 * @author haoren
 * @create 2020-12-06 16:36
 */
public class LoginAccountAlreadyInUseException extends RuntimeException{
    private static final long serialVersionUID = 70348971L;

    public LoginAccountAlreadyInUseException() {
        super();
    }

    public LoginAccountAlreadyInUseException(String message) {
        super(message);
    }

    public LoginAccountAlreadyInUseException(String message, Throwable cause) {
        super(message, cause);
    }

    public LoginAccountAlreadyInUseException(Throwable cause) {
        super(cause);
    }

    protected LoginAccountAlreadyInUseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
