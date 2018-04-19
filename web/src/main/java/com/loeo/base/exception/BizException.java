package com.loeo.base.exception;

/**
 * @author ：Tony.L(286269159@qq.com)
 * @create ：2017/06/14 22:44
 * @version ：2018 Version：1.0
 */
public class BizException extends RuntimeException {
    public BizException() {
        super();
    }

    public BizException(String message) {
        super(message);
    }

    public BizException(String message, Throwable cause) {
        super(message, cause);
    }

    public BizException(Throwable cause) {
        super(cause);
    }

    protected BizException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
