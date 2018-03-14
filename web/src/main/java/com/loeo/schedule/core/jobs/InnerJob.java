package com.loeo.schedule.core.jobs;


import com.loeo.schedule.core.JobWrapper;
import com.loeo.schedule.core.exception.ExecFailureException;
import com.loeo.schedule.core.executor.JobExecutor;
import com.loeo.schedule.core.jobs.info.InnerExecutingInfo;

/**
 * 功能：
 *
 * @author：LT(286269159@qq.com)
 * @create：2017-11-17 19:04:13
 * @version：2017 Version：1.0
 * @company：创海科技 Created with IntelliJ IDEA
 */
public class InnerJob extends BaseJob {
	@Override
	protected Object doExecute(JobWrapper jobWrapper) {
		try {
			InnerExecutingInfo innerExecutingInfo = (InnerExecutingInfo) jobWrapper.getExecutingInfo();
			Class<? extends JobExecutor> cls = (Class<? extends JobExecutor>) Class.forName(innerExecutingInfo.getClassName());
			JobExecutor jobExecutor = cls.newInstance();
			return jobExecutor.execute(jobWrapper);
		} catch (Exception e) {
			throw new ExecFailureException("执行失败", e);
		}
	}
}
