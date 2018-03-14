package com.loeo.base.schedule.jobs.info;

import com.chuanghai.easypm.webapp.baseservice.schedule.core.JobExecutorType;

/**
 * 功能：需要子类封装执行器所需要的信息，不同的执行器需要的信息不同
 *
 * @author：LT(286269159@qq.com)
 * @create：2017-11-18 11:38:52
 * @version：2017 Version：1.0
 * @company：创海科技 Created with IntelliJ IDEA
 */
public interface JobExecutingInfo {
	/**
	 * 获取执行类型
	 * @return JobExecutorType
	 */
	JobExecutorType getJobExecutorType();
}
