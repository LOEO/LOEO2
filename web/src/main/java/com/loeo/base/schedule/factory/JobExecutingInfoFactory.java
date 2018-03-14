package com.loeo.base.schedule.factory;

import com.loeo.base.schedule.JobWrapper;
import com.loeo.base.schedule.jobs.info.JobExecutingInfo;

/**
 * 功能：根据传入的job信息创建job的执行信息类
 *
 * @author：LT(286269159@qq.com)
 * @create：2017-11-18 18:33:56
 * @version：2017 Version：1.0
 * @company：创海科技 Created with IntelliJ IDEA
 */
public interface JobExecutingInfoFactory{
	JobExecutingInfo createExecutingInfo(JobWrapper jobWrapper);
}
