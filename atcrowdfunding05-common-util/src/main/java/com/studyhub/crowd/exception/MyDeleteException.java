package com.studyhub.crowd.exception;

/**
 * 我的删除错误异常类
 * @author haoren
 * @create 2020-12-07 18:35
 */
public class MyDeleteException extends RuntimeException{
    public MyDeleteException() {
        super();
    }

    public MyDeleteException(String message) {
        super(message);
    }

    public MyDeleteException(String message, Throwable cause) {
        super(message, cause);
    }

    public MyDeleteException(Throwable cause) {
        super(cause);
    }

    protected MyDeleteException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
