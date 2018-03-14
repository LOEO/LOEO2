package com.loeo.base.schedule.executor;

import com.chuanghai.easypm.webapp.baseservice.schedule.core.JobData;
import com.chuanghai.easypm.webapp.baseservice.schedule.entity.ScheduleJob;
import com.chuanghai.easypm.webapp.baseservice.schedule.entity.ScheduleTrigger;

/**
 * 功能：
 *
 * @author：LT(286269159@qq.com)
 * @create：2017-11-17 18:15:57
 * @version：2017 Version：1.0
 * @company：创海科技 Created with IntelliJ IDEA
 */
public class HelloJobExecutor implements JobExecutor {

	@Override
	public String execute(JobData jobData) {
		ScheduleJob scheduleJob = (ScheduleJob) jobData.getJobData();
		ScheduleTrigger scheduleTrigger = (ScheduleTrigger) jobData.getCurrentTriggerData();
		System.out.println("===========================执行任务："+scheduleJob.getName()+"===========================");
		return "Hello JobExecutor";
	}
}
