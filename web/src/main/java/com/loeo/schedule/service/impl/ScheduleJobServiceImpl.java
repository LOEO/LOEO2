package com.loeo.schedule.service.impl;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.loeo.base.service.BaseServiceImpl;
import com.loeo.schedule.JobWrapperImpl;
import com.loeo.schedule.ScheduleJobType;
import com.loeo.schedule.core.JobExecutorType;
import com.loeo.schedule.core.JobWrapper;
import com.loeo.schedule.core.SchedulerContext;
import com.loeo.schedule.core.exception.NoSuchExecutorException;
import com.loeo.schedule.core.factory.JobExecutingInfoFactory;
import com.loeo.schedule.core.jobloader.JobLoader;
import com.loeo.schedule.core.jobs.info.InnerExecutingInfo;
import com.loeo.schedule.core.jobs.info.JobExecutingInfo;
import com.loeo.schedule.domain.dto.ScheduleJobDto;
import com.loeo.schedule.domain.entity.ScheduleJob;
import com.loeo.schedule.domain.entity.ScheduleJobDepend;
import com.loeo.schedule.domain.entity.ScheduleJobInner;
import com.loeo.schedule.domain.entity.ScheduleTrigger;
import com.loeo.schedule.domain.entity.ScheduleTriggerJob;
import com.loeo.schedule.exception.ScheduleJobException;
import com.loeo.schedule.mapper.ScheduleJobMapper;
import com.loeo.schedule.service.ScheduleJobDependService;
import com.loeo.schedule.service.ScheduleJobInnerService;
import com.loeo.schedule.service.ScheduleJobService;
import com.loeo.schedule.service.ScheduleTriggerJobService;


/**
 * @author LT(286269159 @ qq.com)
 */
@Service("scheduleJobService")
public class ScheduleJobServiceImpl extends BaseServiceImpl<ScheduleJobMapper, ScheduleJob> implements ScheduleJobService, JobExecutingInfoFactory, JobLoader {
	private static final Logger logger = LoggerFactory.getLogger(ScheduleJobServiceImpl.class);
	@Resource
	private ScheduleJobMapper scheduleJobMapper;
	@Resource
	private ScheduleJobInnerService scheduleJobInnerService;
	@Resource
	private ScheduleTriggerJobService scheduleTriggerJobService;
	@Resource
	private ScheduleJobDependService scheduleJobDependService;
	@Resource
	private SchedulerContext schedulerContext;

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void addScheduleJob(ScheduleJobDto scheduleJobDto) {
		if (scheduleJobDto.getClassName() == null || "".equals(scheduleJobDto.getClassName())) {
			throw new ScheduleJobException("没有设置ClassName");
		}
		ScheduleJob scheduleJob = scheduleJobDto.getScheduleJob();
		insert(scheduleJob);
		ScheduleJobInner scheduleJobInner = new ScheduleJobInner();
		scheduleJobInner.setJobId(scheduleJob.getId());
		scheduleJobInner.setClassName(scheduleJobDto.getClassName());
		scheduleJobInnerService.insert(scheduleJobInner);
	}

	@Transactional(rollbackFor = Exception.class)
	public int deleteById(String id) {
		ScheduleJob scheduleJob = this.scheduleJobMapper.findById(id);
		if (scheduleJob != null) {
			switch (ScheduleJobType.values()[scheduleJob.getType()]) {
				case INNER:
					scheduleJobInnerService.deleteByJobId(scheduleJob.getId());
					scheduleTriggerJobService.deleteByJobId(scheduleJob.getId());
					this.scheduleJobMapper.deleteById(scheduleJob.getId());
					break;
				default:
			}
			JobWrapper jobWrapper = new JobWrapperImpl(scheduleJob, this);
			try {
				schedulerContext.deleteJob(jobWrapper.getJobKey());
			} catch (SchedulerException e) {
				throw new ScheduleJobException("删除job失败", e);
			}
		}
		return 0;
	}

	@Override
	public List<ScheduleJob> findAllEnabledJob() {
		return this.scheduleJobMapper.findAllEnabledJob();
	}

	@Override
	public List<ScheduleJob> findAllEnabledJobsAndTriggers() {
		return this.scheduleJobMapper.findAllEnabledJobsAndTriggers();
	}

	@Override
	public boolean hasTriggerById(String id) {
		return this.scheduleJobMapper.hasTriggerById(id);
	}

	@Override
	public List<ScheduleJob> findByIdList(List<String> jobIds) {
		return this.scheduleJobMapper.findByIdList(jobIds);
	}

	@Override
	public List<ScheduleJob> findByScheduleId(String id) {
		return this.scheduleJobMapper.findByScheduleId(id);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void bindTriggers(String scheduleJobId, String[] triggerIds) {
		ScheduleJob scheduleJob = selectById(scheduleJobId);
		if (scheduleJob == null) {
			throw new ScheduleJobException("Job不存在");
		}
		scheduleTriggerJobService.deleteByJobId(scheduleJobId);

		if (triggerIds == null || triggerIds.length == 0) {
			try {
				schedulerContext.deleteJob(new JobWrapperImpl(scheduleJob, this).getJobKey());
				return;
			} catch (SchedulerException e) {
				throw new ScheduleJobException("绑定trigger失败");
			}
		}

		List<ScheduleTriggerJob> scheduleTriggers = new ArrayList<>();
		for (String triggerId : triggerIds) {
			ScheduleTriggerJob scheduleTriggerJob = new ScheduleTriggerJob();
			scheduleTriggerJob.setJobId(scheduleJobId);
			scheduleTriggerJob.setTriggerId(triggerId);
			scheduleTriggers.add(scheduleTriggerJob);
		}
		scheduleTriggerJobService.batchInsert(scheduleTriggers);

		Set<ScheduleTrigger> triggers = scheduleTriggerJobService.findTriggersByJobId(scheduleJob.getId());
		scheduleJob.setTriggers(triggers);

		try {
			schedulerContext.updateJob(new JobWrapperImpl(scheduleJob, this));
		} catch (SchedulerException e) {
			throw new ScheduleJobException("绑定trigger失败");
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void setDepends(String scheduleJobId, List<ScheduleJobDepend> scheduleJobDepends) {
		ScheduleJob scheduleJob = selectById(scheduleJobId);
		if (scheduleJob == null) {
			throw new ScheduleJobException("Job不存在");
		}
		if (CollectionUtils.isEmpty(scheduleJobDepends)) {
			scheduleJobDependService.deleteByDependJobId(scheduleJobId);
			return;
		}
		List<String> jobIds = new ArrayList<>();
		for (ScheduleJobDepend scheduleJobDepend : scheduleJobDepends) {
			jobIds.add(scheduleJobDepend.getJobId());
			scheduleJobDepend.setDependJobId(scheduleJobId);
		}
		if (isExistByIdList(jobIds)) {
			scheduleJobDependService.deleteByDependJobId(scheduleJobId);
			scheduleJobDependService.batchAdd(scheduleJobDepends);
		} else {
			throw new ScheduleJobException("Job不存在");
		}
	}

	@Override
	public List<ScheduleJob> findEnabledJobsByTriggerId(String triggerId) {
		return this.scheduleJobMapper.findEnabledJobsByTriggerId(triggerId);
	}

	@Override
	public boolean isExistByIdList(List<String> dependIds) {
		return this.scheduleJobMapper.isExistByIdList(dependIds);
	}

	@Override
	public JobExecutingInfo createExecutingInfo(JobWrapper jobWrapper) {
		ScheduleJob scheduleJob = (ScheduleJob) jobWrapper.getJobData();
		switch (ScheduleJobType.values()[scheduleJob.getType()]) {
			case INNER:
				ScheduleJobInner scheduleJobInner = scheduleJobInnerService.findByJobId(scheduleJob.getId());
				return new InnerExecutingInfo(scheduleJobInner.getClassName(), JobExecutorType.INNER);
			default:
		}
		throw new NoSuchExecutorException();
	}

	@Override
	public List<JobWrapper> loadJobs() {
		List<JobWrapper> jobWrappers = new ArrayList<>();
		List<ScheduleJob> scheduleJobs = findAllEnabledJobsAndTriggers();
		for (ScheduleJob scheduleJob : scheduleJobs) {
			try {
				JobWrapperImpl jobWrapper = new JobWrapperImpl(scheduleJob, this);
				jobWrappers.add(jobWrapper);
			} catch (Exception e) {
				logger.error("任务：{}_{}，加载出错", scheduleJob.getId(), scheduleJob.getName(), e);
			}
		}
		return jobWrappers;
	}
}