package com.loeo.schedule.exception;

/**
 * 功能：
 *
 * @author：LT(286269159@qq.com)
 * @create：2017-11-20 18:50:35
 * @version：2017 Version：1.0
 * @company：创海科技 Created with IntelliJ IDEA
 */
public class ScheduleJobException extends RuntimeException {
	public ScheduleJobException() {
	}

	public ScheduleJobException(String message) {
		super(message);
	}

	public ScheduleJobException(String message, Throwable cause) {
		super(message, cause);
	}
}
