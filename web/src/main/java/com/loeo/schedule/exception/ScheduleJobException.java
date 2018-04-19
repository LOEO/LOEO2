package com.loeo.schedule.exception;

/**
 * 功能：
 *
 * @author ：Tony.L(286269159@qq.com)
 * @create ：2017-11-20 18:50:35
 * @version ：2017 Version：1.0

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
