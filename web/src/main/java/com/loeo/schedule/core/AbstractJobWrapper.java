package com.loeo.schedule.core;

import java.util.Set;

import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Trigger;

import com.loeo.schedule.core.factory.JobDetailFactory;
import com.loeo.schedule.core.factory.JobExecutingInfoFactory;
import com.loeo.schedule.core.jobs.info.JobExecutingInfo;

/**
 * 功能：
 *
 * @author ：LT(286269159@qq.com)
 * @create ：2017-11-20 11:34:15
 * @version ：2017 Version：1.0

 */
public abstract class AbstractJobWrapper implements JobWrapper {
	public static final String JOB_DATA_MAP_KEY_CURRENT_TRIGGER_DATA = "JOB_DATA_MAP_KEY_CURRENT_TRIGGER_DATA";
	private JobKey jobKey;
	private JobExecutingInfo jobExecuteInfo;
	private JobExecutingInfoFactory jobExecutingInfoFactory;
	protected Set<Trigger> triggers;
	protected Trigger currentTrigger;
	protected JobDetail jobDetail;

	public AbstractJobWrapper(JobExecutingInfoFactory jobExecutingInfoFactory) {
		this.jobExecutingInfoFactory = jobExecutingInfoFactory;
	}

	@Override
	public JobKey getJobKey() {
		if (jobKey == null) {
			jobKey = createJobKey();
		}
		return jobKey;
	}


	@Override
	public JobDetail getJobDetail() {
		if (jobDetail == null) {
			jobDetail = JobDetailFactory.createJobDetail(this);
			JobDataMap jobDataMap = jobDetail.getJobDataMap();
			jobDataMap.putIfAbsent(JOB_DATA_MAP_KEY_JOB_WRAPPER, this);
		}
		return jobDetail;
	}


	@Override
	public JobExecutingInfo getExecutingInfo() {
		if (jobExecuteInfo == null) {
			jobExecuteInfo = jobExecutingInfoFactory.createExecutingInfo(this);
		}
		return jobExecuteInfo;
	}

	@Override
	public void setCurrentTrigger(Trigger trigger) {
		this.currentTrigger = trigger;
		JobDataMap jobDataMap = trigger.getJobDataMap();
		jobDataMap.put(JOB_DATA_MAP_KEY_CURRENT_TRIGGER_DATA, getCurrentTriggerData(trigger));
	}

	@Override
	public Trigger getCurrentTrigger() {
		return this.currentTrigger;
	}

	@Override
	public Object getCurrentTriggerData() {
		if (this.currentTrigger == null) {
			return null;
		}
		JobDataMap jobDataMap = this.currentTrigger.getJobDataMap();
		return jobDataMap.get(JOB_DATA_MAP_KEY_CURRENT_TRIGGER_DATA);
	}

	/**
	 * 创建JobKey由子类实现
	 *
	 * @return JobKey
	 */
	protected abstract JobKey createJobKey();

	/**
	 * 得到当前的trigger的业务数据
	 *
	 * @param trigger 当前的trigger
	 * @return Object
	 */
	protected abstract Object getCurrentTriggerData(Trigger trigger);


}
