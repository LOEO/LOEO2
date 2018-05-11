package com.loeo.schedule.core.executor;


import com.loeo.schedule.core.JobInfo;

/**
 * 功能：job执行器接口，外部要实现这个类才能在被执行
 *
 * @author ：Tony.L(286269159@qq.com)
 * @create ：2017-11-17 19:04:27
 * @version ：2017 Version：1.0

 */
public interface JobExecutor {

	Object execute(JobInfo jobInfo);

}
