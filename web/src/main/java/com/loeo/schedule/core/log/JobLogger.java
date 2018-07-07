package com.loeo.schedule.core.log;

import java.util.Date;

/**
 * 功能：
 *
 * @author ：Tony.L(286269159@qq.com)
 * @create ：2017-11-20 09:40:22
 * @version ：2017 Version：1.0

 */
public interface JobLogger {
	String getId();

	String getDescription();

	Date getStarted();

	Date getEnded();

	Boolean success();
}
