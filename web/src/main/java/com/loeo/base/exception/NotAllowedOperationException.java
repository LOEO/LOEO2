package com.loeo.base.exception;

/**
 * 功能：
 *
 * @author ：LT(286269159@qq.com)
 * @version ：2018 Version：1.0
 * @create ：2018-05-08 15:19:32
 */
public class NotAllowedOperationException extends BizException {
	public NotAllowedOperationException() {
	}

	public NotAllowedOperationException(String message) {
		super(message);
	}

	public NotAllowedOperationException(String message, Throwable cause) {
		super(message, cause);
	}

	public NotAllowedOperationException(Throwable cause) {
		super(cause);
	}

	public NotAllowedOperationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
