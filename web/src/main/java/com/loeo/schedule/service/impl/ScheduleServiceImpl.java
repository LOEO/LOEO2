package com.loeo.schedule.service.impl;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.loeo.base.service.BaseServiceImpl;
import com.loeo.schedule.domain.entity.Schedule;
import com.loeo.schedule.domain.entity.ScheduleJob;
import com.loeo.schedule.mapper.ScheduleMapper;
import com.loeo.schedule.service.ScheduleJobService;
import com.loeo.schedule.service.ScheduleService;


@Service("scheduleService")
public class ScheduleServiceImpl extends BaseServiceImpl<ScheduleMapper,Schedule> implements ScheduleService {
	@Resource
	private ScheduleJobService scheduleJobService;

	@Resource
	private ScheduleMapper scheduleMapper;

	public int deleteById(String id) {
		List<ScheduleJob> scheduleJobList = scheduleJobService.findByScheduleId(id);
		for (ScheduleJob scheduleJob : scheduleJobList) {
			scheduleJobService.deleteById(scheduleJob.getId());
		}
		return this.scheduleMapper.deleteById(id);
	}

}