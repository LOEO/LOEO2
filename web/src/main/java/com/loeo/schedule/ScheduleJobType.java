package com.loeo.schedule;

/**
 * 功能：
 *
 * @author ：Tony.L(286269159@qq.com)
 * @create ：2017-11-18 10:38:00
 * @version ：2017 Version：1.0

 */
public enum ScheduleJobType {
	INNER(0),
	HTTP(1),
	SQL(2),
	SCRIPT(3),
	COMMAND(4);
	int value;

	ScheduleJobType(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
}
