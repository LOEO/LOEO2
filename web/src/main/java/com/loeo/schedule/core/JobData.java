package com.loeo.schedule.core;

/**
 * 功能：
 *
 * @author ：LT(286269159@qq.com)
 * @create ：2017-11-18 15:44:16
 * @version ：2017 Version：1.0

 */
public interface JobData {

	/**
	 * 获取当前的Trigger数据
	 * @return TriggerData
	 */
	Object getCurrentTriggerData();

	/**
	 * 获取job数据
	 * @return JobData
	 */
	Object getJobData();

}
