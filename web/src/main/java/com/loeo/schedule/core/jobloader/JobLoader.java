package com.loeo.schedule.core.jobloader;

import java.util.List;

import com.loeo.schedule.core.JobWrapper;


/**
 * 功能：
 *
 * @author ：Tony.L(286269159@qq.com)
 * @create ：2017-11-18 09:04:49
 * @version ：2017 Version：1.0

 */
public interface JobLoader {

	/**
	 * 加载job数据，具体加载逻辑由子类实现，可从数据库，redis，文件等位置加载
	 * @return
	 */
	List<JobWrapper> loadJobs();

}
