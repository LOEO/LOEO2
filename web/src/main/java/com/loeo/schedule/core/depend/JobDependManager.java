package com.loeo.schedule.core.depend;

import java.util.List;

import com.loeo.schedule.core.JobWrapper;


/**
 * 功能：
 *
 * @author ：Tony.L(286269159@qq.com)
 * @create ：2017-11-20 09:12:21
 * @version ：2017 Version：1.0

 */
public interface JobDependManager {

	void clearActualJobResult(JobWrapper jobWrapper);

	List<JobWrapper> getNextJobs(JobWrapper jobWrapper);

	void setDependActualResult(JobWrapper jobWrapper, Object result);
}
