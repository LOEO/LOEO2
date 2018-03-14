package com.loeo.base.schedule.exception;

/**
 * 功能：
 *
 * @author：LT(286269159@qq.com)
 * @create：2017-11-18 15:03:13
 * @version：2017 Version：1.0
 * @company：创海科技 Created with IntelliJ IDEA
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
