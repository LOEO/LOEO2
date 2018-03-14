package com.loeo.schedule.service.impl;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.loeo.base.service.BaseServiceImpl;
import com.loeo.schedule.JobWrapperImpl;
import com.loeo.schedule.core.JobWrapper;
import com.loeo.schedule.core.depend.JobDependManager;
import com.loeo.schedule.core.factory.JobExecutingInfoFactory;
import com.loeo.schedule.domain.entity.ScheduleJob;
import com.loeo.schedule.domain.entity.ScheduleJobDepend;
import com.loeo.schedule.exception.ScheduleJobException;
import com.loeo.schedule.mapper.ScheduleJobDependMapper;
import com.loeo.schedule.service.ScheduleJobDependService;
import com.loeo.schedule.service.ScheduleJobService;
import com.loeo.schedule.service.ScheduleTriggerJobService;

/**
 * @author LT(286269159 @ qq.com)
 */
@Service("scheduleJobDependService")
public class ScheduleJobDependServiceImpl extends BaseServiceImpl<ScheduleJobDependMapper, ScheduleJobDepend> implements ScheduleJobDependService, JobDependManager {
	@Resource
	private ScheduleJobDependMapper scheduleJobDependMapper;
	@Resource
	private ScheduleJobService scheduleJobService;
	@Resource
	private ScheduleTriggerJobService scheduleTriggerJobService;
	@Resource
	private JobExecutingInfoFactory jobExecutingInfoFactory;

	@Override
	public void clearActualJobResult(JobWrapper jobWrapper) {
		ScheduleJob scheduleJob = (ScheduleJob) jobWrapper.getJobData();
		scheduleJobDependMapper.clearActualResultByDependJobId(scheduleJob.getId());
	}

	@Override
	public List<JobWrapper> getNextJobs(JobWrapper jobWrapper) {
		ScheduleJob scheduleJob = (ScheduleJob) jobWrapper.getJobData();

		List<ScheduleJobDepend> scheduleJobList = scheduleJobDependMapper.findByDependJobId(scheduleJob.getId());

		if (scheduleJobList.isEmpty()) {
			return Collections.emptyList();
		}

		//过滤掉不需要执行的job
		List<String> jobIds = scheduleJobList.stream().filter(scheduleJobDepend ->
				!scheduleJobService.hasTriggerById(scheduleJobDepend.getJobId()) &&
						(scheduleJobDepend.getExpectResult() == null
								|| scheduleJobDepend.getExpectResult().equals(scheduleJobDepend.getActualResult())))
				.map(ScheduleJobDepend::getJobId).collect(Collectors.toList());

		if (jobIds.size() > 0) {
			List<ScheduleJob> scheduleJobs = scheduleJobService.findByIdList(jobIds);

			List<JobWrapper> jobWrappers = new ArrayList<>();
			for (ScheduleJob job : scheduleJobs) {
				jobWrappers.add(new JobWrapperImpl(job, jobExecutingInfoFactory));
			}
			return jobWrappers;
		}
		return Collections.emptyList();
	}

	@Override
	public void setDependActualResult(JobWrapper jobWrapper, Object result) {
		ScheduleJob scheduleJob = (ScheduleJob) jobWrapper.getJobData();
		scheduleJobDependMapper.updateActualResultByDependJobId(scheduleJob.getId(), result);
	}

	@Override
	public void add(ScheduleJobDepend scheduleJobDepend) {
		ScheduleJobDepend sjd = scheduleJobDependMapper.findByJobIdAndDependJobId(scheduleJobDepend.getJobId(), scheduleJobDepend.getDependJobId());
		if (sjd != null) {
			throw new ScheduleJobException("不能重复添加依赖");
		}
		ScheduleJob scheduleJob = scheduleJobService.selectById(scheduleJobDepend.getJobId());
		if (scheduleJob == null) {
			throw new ScheduleJobException("Job不存在");
		}
		scheduleJob = scheduleJobService.selectById(scheduleJobDepend.getDependJobId());
		if (scheduleJob == null) {
			throw new ScheduleJobException("依赖的Job不存在");
		}

		if (scheduleJobService.hasTriggerById(scheduleJobDepend.getJobId())) {
			throw new ScheduleJobException("设置过触发器，不能设置依赖");
		}

		scheduleJobDependMapper.insert(scheduleJobDepend);
	}

	@Override
	public void delete(ScheduleJobDepend scheduleJobDepend) {
		scheduleJobDependMapper.deleteByJobIdAndDependJobId(scheduleJobDepend.getJobId(), scheduleJobDepend.getDependJobId());
	}

	@Override
	public boolean hasDependByJobId(String jobId) {
		return scheduleJobDependMapper.hasDependByJobId(jobId);
	}

	@Override
	public void batchAdd(List<ScheduleJobDepend> scheduleJobDepends) {
		scheduleJobDependMapper.batchAdd(scheduleJobDepends);
	}

	@Override
	public void deleteByDependJobId(String scheduleJobId) {
		scheduleJobDependMapper.deleteByDependJobId(scheduleJobId);
	}
}