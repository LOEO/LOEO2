package com.loeo.schedule.service.impl;


import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.quartz.SchedulerException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.loeo.base.service.BaseServiceImpl;
import com.loeo.schedule.JobWrapperImpl;
import com.loeo.schedule.core.JobWrapper;
import com.loeo.schedule.core.SchedulerContext;
import com.loeo.schedule.core.factory.JobExecutingInfoFactory;
import com.loeo.schedule.domain.entity.ScheduleJob;
import com.loeo.schedule.domain.entity.ScheduleTrigger;
import com.loeo.schedule.domain.entity.ScheduleTriggerJob;
import com.loeo.schedule.exception.ScheduleJobException;
import com.loeo.schedule.mapper.ScheduleTriggerJobMapper;
import com.loeo.schedule.service.ScheduleJobDependService;
import com.loeo.schedule.service.ScheduleJobService;
import com.loeo.schedule.service.ScheduleTriggerJobService;
import com.loeo.schedule.service.ScheduleTriggerService;


/**
 * @author LT(286269159 @ qq.com)
 */
@Service("scheduleTriggerJobService")
public class ScheduleTriggerJobServiceImpl extends BaseServiceImpl<ScheduleTriggerJobMapper,ScheduleTriggerJob> implements ScheduleTriggerJobService {
	@Resource
	private ScheduleTriggerJobMapper scheduleTriggerJobMapper;
	@Resource
	private ScheduleJobService scheduleJobService;
	@Resource
	private ScheduleTriggerService scheduleTriggerService;
	@Resource
	private ScheduleJobDependService scheduleJobDependService;
	@Resource
	private SchedulerContext schedulerContext;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void deleteByJobId(String id) {
		scheduleTriggerJobMapper.deleteByJobId(id);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void add(ScheduleTriggerJob triggerJob) {
		ScheduleTriggerJob scheduleTriggerJob = scheduleTriggerJobMapper.findByTriggerAndJobId(triggerJob.getTriggerId(),triggerJob.getJobId());
		if (scheduleTriggerJob != null) {
			throw new ScheduleJobException("任务和触发器已经绑定过");
		}
		ScheduleJob scheduleJob = scheduleJobService.selectById(triggerJob.getJobId());
		if (scheduleJob == null) {
			throw new ScheduleJobException("任务不存在");
		}
		if (scheduleJobDependService.hasDependByJobId(triggerJob.getJobId())) {
			throw new ScheduleJobException("任务已经设置过依赖，不能再设置触发器");
		}

		ScheduleTrigger scheduleTrigger = scheduleTriggerService.selectById(triggerJob.getTriggerId());
		if (scheduleTrigger == null) {
			throw new ScheduleJobException("触发器不存在");
		}
		scheduleTriggerJobMapper.insert(triggerJob);
		JobWrapper jobWrapper = new JobWrapperImpl(scheduleJob, (JobExecutingInfoFactory) scheduleJobService);
		try {
			schedulerContext.updateJob(jobWrapper);
		} catch (SchedulerException e) {
			throw new ScheduleJobException("绑定触发器失败");
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void delete(ScheduleTriggerJob triggerJob) {
		ScheduleTriggerJob scheduleTriggerJob = scheduleTriggerJobMapper.findByTriggerAndJobId(triggerJob.getTriggerId(),triggerJob.getJobId());
		if (scheduleTriggerJob == null) {
			return;
		}
		ScheduleJob scheduleJob = scheduleJobService.selectById(triggerJob.getJobId());
		if (scheduleJob == null) {
			throw new ScheduleJobException("任务不存在");
		}
		ScheduleTrigger scheduleTrigger = scheduleTriggerService.selectById(triggerJob.getTriggerId());
		if (scheduleTrigger == null) {
			throw new ScheduleJobException("触发器不存在");
		}
		scheduleTriggerJobMapper.deleteByTriggerIdAndJobId(triggerJob.getTriggerId(), triggerJob.getJobId());
		JobWrapper jobWrapper = new JobWrapperImpl(scheduleJob, (JobExecutingInfoFactory) scheduleJobService);
		try {
			schedulerContext.updateJob(jobWrapper);
		} catch (SchedulerException e) {
			throw new ScheduleJobException("删除触发器失败");
		}

	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void batchInsert(List<ScheduleTriggerJob> scheduleTriggerJobs) {
		scheduleTriggerJobMapper.batchInsert(scheduleTriggerJobs);
	}

	@Override
	public Set<ScheduleTrigger> findTriggersByJobId(String id) {
		return scheduleTriggerJobMapper.findTriggersByJobId(id);
	}

}