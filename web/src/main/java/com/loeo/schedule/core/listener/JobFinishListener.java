package com.loeo.schedule.core.listener;


import com.loeo.schedule.core.JobWrapper;

/**
 * 功能：
 *
 * @author ：Tony.L(286269159@qq.com)
 * @create ：2017-11-20 16:09:41
 * @version ：2017 Version：1.0

 */
public interface JobFinishListener {
	void onFinish(JobWrapper jobWrapper);
}
