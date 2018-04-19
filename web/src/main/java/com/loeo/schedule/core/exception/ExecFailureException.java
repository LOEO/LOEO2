package com.loeo.schedule.core.exception;

/**
 * 功能：
 *
 * @author ：Tony.L(286269159@qq.com)
 * @create ：2017-11-18 15:03:13
 * @version ：2017 Version：1.0

 */
public class ExecFailureException extends RuntimeException {
	public ExecFailureException() {
	}

	public ExecFailureException(String message) {
		super(message);
	}

	public ExecFailureException(String message, Throwable cause) {
		super(message, cause);
	}
}
