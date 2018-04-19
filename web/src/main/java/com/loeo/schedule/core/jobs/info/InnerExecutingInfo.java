package com.loeo.schedule.core.jobs.info;


import com.loeo.schedule.core.JobExecutorType;

/**
 * 功能：
 *
 * @author ：LT(286269159@qq.com)
 * @create ：2017-11-18 11:31:55
 * @version ：2017 Version：1.0

 */
public class InnerExecutingInfo implements JobExecutingInfo {
	private String className;
	private JobExecutorType jobExecutorType;

	public InnerExecutingInfo(String className, JobExecutorType jobExecutorType) {
		this.className = className;
		this.jobExecutorType = jobExecutorType;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public void setJobExecutorType(JobExecutorType jobExecutorType) {
		this.jobExecutorType = jobExecutorType;
	}

	@Override
	public JobExecutorType getJobExecutorType() {
		return jobExecutorType;
	}
}
