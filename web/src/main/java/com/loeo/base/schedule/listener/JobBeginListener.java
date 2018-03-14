package com.loeo.base.schedule.listener;

import com.chuanghai.easypm.webapp.baseservice.schedule.core.JobWrapper;

/**
 * 功能：
 *
 * @author：LT(286269159@qq.com)
 * @create：2017-11-20 16:08:08
 * @version：2017 Version：1.0
 * @company：创海科技 Created with IntelliJ IDEA
 */
public interface JobBeginListener {
	void onBegin(JobWrapper jobWrapper);
}
