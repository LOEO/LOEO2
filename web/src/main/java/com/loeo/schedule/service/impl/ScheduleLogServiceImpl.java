package com.loeo.schedule.service.impl;


import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.loeo.base.service.BaseServiceImpl;
import com.loeo.schedule.core.JobWrapper;
import com.loeo.schedule.core.log.JobLogger;
import com.loeo.schedule.core.log.JobLoggerManager;
import com.loeo.schedule.domain.entity.ScheduleJob;
import com.loeo.schedule.domain.entity.ScheduleLog;
import com.loeo.schedule.domain.entity.ScheduleTrigger;
import com.loeo.schedule.mapper.ScheduleLogMapper;
import com.loeo.schedule.service.ScheduleLogService;
import com.loeo.utils.DateUtils;


/**
 * @author LT(286269159 @ qq.com)
 */
@Service("scheduleLogService")
public class ScheduleLogServiceImpl extends BaseServiceImpl<ScheduleLogMapper,ScheduleLog> implements ScheduleLogService, JobLoggerManager {

	@Resource
	private ScheduleLogMapper scheduleLogMapper;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public JobLogger logStart(JobWrapper jobWrapper) {
		ScheduleJob scheduleJob = (ScheduleJob) jobWrapper.getJobData();
		ScheduleTrigger scheduleTrigger = (ScheduleTrigger) jobWrapper.getCurrentTriggerData();
		ScheduleLog scheduleLog = new ScheduleLog();
		scheduleLog.setJobId(scheduleJob.getId());
		if (scheduleTrigger != null) {
			scheduleLog.setTriggerId(scheduleTrigger.getId());
		}
		scheduleLog.setStarted(new Date());
		scheduleLog.setSuccess(Boolean.FALSE);
		scheduleLog.setDescription("");
		System.err.println(scheduleLog.toString());
		insert(scheduleLog);
		return scheduleLog;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void logEnd(JobLogger jobLogger, JobWrapper jobWrapper, String errMsg) {
		ScheduleLog scheduleLog = new ScheduleLog();
		scheduleLog.setId(jobLogger.getId());
		scheduleLog.setDescription(errMsg);
		if (errMsg != null && !"".equals(errMsg)) {
			scheduleLog.setDescription(errMsg);
		}else{
			scheduleLog.setSuccess(Boolean.TRUE);
		}
		scheduleLog.setEnded(DateUtils.now());
		this.scheduleLogMapper.updateById(scheduleLog);
	}

	@Override
	public void logVetoed(JobLogger jobLogger, JobWrapper jobWrapper) {

	}
}