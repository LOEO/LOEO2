package com.loeo.base.schedule.exception;

/**
 * 功能：
 *
 * @author：LT(286269159@qq.com)
 * @create：2017-11-18 10:18:50
 * @version：2017 Version：1.0
 * @company：创海科技 Created with IntelliJ IDEA
 */
public class NoSuchExecutorException extends RuntimeException {
	public NoSuchExecutorException() {
	}

	public NoSuchExecutorException(String message) {
		super(message);
	}
}
