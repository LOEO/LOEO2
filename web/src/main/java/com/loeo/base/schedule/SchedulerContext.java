package com.loeo.base.schedule;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.quartz.JobKey;
import org.quartz.JobListener;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.chuanghai.easypm.webapp.baseservice.schedule.core.jobloader.JobLoader;

/**
 * 功能：
 *
 * @author：LT(286269159@qq.com)
 * @create：2017-11-16 16:50:59
 * @version：2017 Version：1.0
 * @company：创海科技 Created with IntelliJ IDEA
 */
@Component
public class SchedulerContext implements ApplicationListener<ApplicationReadyEvent> {
	private static final Logger logger = LoggerFactory.getLogger(SchedulerContext.class);
	private SchedulerFactory schedulerFactory = new StdSchedulerFactory();
	private Scheduler scheduler;
	@Value("${ch.schedule.enable:#{false}}")
	private boolean enable;
	@Resource
	private JobLoader jobLoader;
	@Resource
	private JobListener jobListener;

	public void init() throws SchedulerException, ClassNotFoundException {
		scheduler = schedulerFactory.getScheduler();
		scheduler.getListenerManager().addJobListener(jobListener);
		scheduler.start();
		List<JobWrapper> jobWrappers = jobLoader.loadJobs();
		for (JobWrapper jobWrapper : jobWrappers) {
			scheduleJob(jobWrapper, false);
		}
	}

	public void addJob(JobWrapper jobWrapper) throws SchedulerException {
		scheduleJob(jobWrapper, false);
	}

	public void deleteJob(JobKey jobKey) throws SchedulerException {
		System.out.println(scheduler.deleteJob(jobKey));
	}


	public void updateJob(JobWrapper jobWrapper) throws SchedulerException {
		deleteJob(jobWrapper.getJobKey());
		scheduleJob(jobWrapper, true);
	}

	public Scheduler getScheduler() {
		return scheduler;
	}

	private void scheduleJob(JobWrapper jobWrapper, boolean replace) throws SchedulerException {
		Set<Trigger> triggers = jobWrapper.getTriggers();
		if (triggers != null && triggers.size() > 0) {
			scheduler.scheduleJob(jobWrapper.getJobDetail(), jobWrapper.getTriggers(), replace);
		}
	}

	@Override
	public void onApplicationEvent(ApplicationReadyEvent event) {
		try {
			if (enable) {
				init();
			}
		} catch (SchedulerException | ClassNotFoundException e) {
			logger.error("初始化任务失败",e);
		}
	}
}
