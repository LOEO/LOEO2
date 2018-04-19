package com.loeo.schedule.core;

import java.util.Set;

import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Trigger;

import com.loeo.schedule.core.jobs.info.JobExecutingInfo;


/**
 * 功能：对Job数据做一次包装，对quartz提供统一调用接口
 *
 * @author ：Tony.L(286269159@qq.com)
 * @create ：2017-11-18 09:56:10
 * @version ：2017 Version：1.0

 */
public interface JobWrapper extends JobData {
	String JOB_DATA_MAP_KEY_JOB_WRAPPER = "JOB_DATA_MAP_KEY_JOB_WRAPPER";


	/**
	 * 由Job数据获取JobKey
	 * @return JobKey
	 */
	JobKey getJobKey();

	/**
	 * 由Job数据获取JobDetail
	 * @return JobDetail
	 */
	JobDetail getJobDetail();

	/**
	 * 由Job数据获取Trigger，一个job可以使用多个trigger
	 * @return Triggers
	 */
	Set<Trigger> getTriggers();

	/**
	 * 由Job数据获取JobExecutingInfo,不同的job会有不同的执行信息
	 * @return JobExecutingInfo
	 */
	JobExecutingInfo getExecutingInfo();

	/**
	 * 设置当前的trigger
	 * @param trigger trigger
	 */
	void setCurrentTrigger(Trigger trigger);

	/**
	 * 获取当前的trigger
	 * @return Trigger
	 */
	Trigger getCurrentTrigger();
}
