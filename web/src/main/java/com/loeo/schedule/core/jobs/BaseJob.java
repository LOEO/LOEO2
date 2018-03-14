package com.loeo.schedule.core.jobs;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.loeo.schedule.core.JobWrapper;


/**
 * 功能：
 *
 * @author：LT(286269159@qq.com)
 * @create：2017-11-18 14:38:08
 * @version：2017 Version：1.0
 * @company：创海科技 Created with IntelliJ IDEA
 */
public abstract class BaseJob implements Job {

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
		JobWrapper jobWrapper = (JobWrapper) jobDataMap.get(JobWrapper.JOB_DATA_MAP_KEY_JOB_WRAPPER);
		if (jobWrapper.getCurrentTrigger() == null) {
			jobWrapper.setCurrentTrigger(context.getTrigger());
		}
		Object result = doExecute(jobWrapper);
		context.setResult(result);
	}

	protected abstract Object doExecute(JobWrapper jobWrapper);
}
