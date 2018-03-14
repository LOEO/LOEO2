package com.loeo.base.schedule.depend;

import java.util.List;

import com.chuanghai.easypm.webapp.baseservice.schedule.core.JobWrapper;

/**
 * 功能：
 *
 * @author：LT(286269159@qq.com)
 * @create：2017-11-20 09:12:21
 * @version：2017 Version：1.0
 * @company：创海科技 Created with IntelliJ IDEA
 */
public interface JobDependManager {

	void clearActualJobResult(JobWrapper jobWrapper);

	List<JobWrapper> getNextJobs(JobWrapper jobWrapper);

	void setDependActualResult(JobWrapper jobWrapper, Object result);
}
