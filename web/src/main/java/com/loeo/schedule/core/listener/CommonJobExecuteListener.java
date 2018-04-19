package com.loeo.schedule.core.listener;

import java.util.List;

import javax.annotation.Resource;

import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.loeo.schedule.core.JobWrapper;
import com.loeo.schedule.core.depend.JobDependManager;
import com.loeo.schedule.core.log.JobLogger;
import com.loeo.schedule.core.log.JobLoggerManager;


/**
 * 功能：
 *
 * @author ：Tony.L(286269159@qq.com)
 * @create ：2017-11-18 16:48:19
 * @version ：2017 Version：1.0

 */
@Component
public class CommonJobExecuteListener implements JobListener {
	private static final Logger logger = LoggerFactory.getLogger(CommonJobExecuteListener.class);
	private String name = CommonJobExecuteListener.class.getName();
	private static final String JOB_DATA_MAP_LOG_KEY = "JOB_DATA_MAP_LOG_KEY";
	@Resource
	private JobLoggerManager jobLoggerManager;
	@Resource
	private JobDependManager jobDependManager;


	@Override
	public String getName() {
		return name;
	}

	@Override
	public void jobToBeExecuted(JobExecutionContext context) {
		JobWrapper jobWrapper = getJobWrapper(context);
		jobWrapper.setCurrentTrigger(context.getTrigger());
		jobDependManager.clearActualJobResult(jobWrapper);
		JobLogger jobLogger = jobLoggerManager.logStart(jobWrapper);
		context.getJobDetail().getJobDataMap().put(JOB_DATA_MAP_LOG_KEY, jobLogger);
	}


	@Override
	public void jobExecutionVetoed(JobExecutionContext context) {
		System.out.println("记录日志：jobExecutionVetoed");

	}

	@Override
	public void jobWasExecuted(JobExecutionContext context, JobExecutionException jobException) {
		JobWrapper jobWrapper = getJobWrapper(context);

		JobLogger jobLogger = (JobLogger) context.getJobDetail().getJobDataMap().get(JOB_DATA_MAP_LOG_KEY);
		jobLoggerManager.logEnd(jobLogger, jobWrapper, jobException == null ? null : jobException.getMessage());

		jobDependManager.setDependActualResult(jobWrapper, context.getResult());

		List<JobWrapper> nextJobs = jobDependManager.getNextJobs(jobWrapper);
		for (JobWrapper nextJob : nextJobs) {
			JobDetail jobDetail = nextJob.getJobDetail();
			Trigger trigger = TriggerBuilder.newTrigger().withIdentity(nextJob.getJobKey().getName())
					.startNow().build();
			try {
				context.getScheduler().scheduleJob(jobDetail, trigger);
			} catch (SchedulerException e) {
				logger.error("执行任务依赖失败",e);
			}
		}
	}

	private JobWrapper getJobWrapper(JobExecutionContext context) {
		return (JobWrapper) context.getJobDetail().getJobDataMap().get(JobWrapper.JOB_DATA_MAP_KEY_JOB_WRAPPER);
	}


}
