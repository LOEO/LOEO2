package com.loeo.schedule.core.factory;

import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;

import com.loeo.schedule.core.JobExecutorType;
import com.loeo.schedule.core.JobWrapper;
import com.loeo.schedule.core.exception.NoSuchExecutorException;
import com.loeo.schedule.core.jobs.InnerJob;

/**
 * 功能：创建JobDetail的工厂类
 *
 * @author：LT(286269159@qq.com)
 * @create：2017-11-18 10:14:43
 * @version：2017 Version：1.0
 * @company：创海科技 Created with IntelliJ IDEA
 */
public abstract class JobDetailFactory {
	public static JobDetail createJobDetail(JobWrapper jobWrapper) {
		Class<? extends Job> jobClass = getJobClass(jobWrapper.getExecutingInfo().getJobExecutorType());
		return newJobDetail(jobWrapper.getJobKey(), jobClass);
	}

	private static Class<? extends Job> getJobClass(JobExecutorType executorType) {
		switch (executorType) {
			case INNER:
				return InnerJob.class;
			case HTTP:
				break;
			case SQL:
				break;
			case SCRIPT:
				break;
			case COMMAND:
				break;
			default:
		}
		throw new NoSuchExecutorException("没有找到对应的executor:" + executorType);
	}

	private static JobDetail newJobDetail(JobKey jobKey, Class<? extends Job> jobClass) {
		return JobBuilder.newJob(jobClass)
				.withIdentity(jobKey)
				.build();
	}
}
