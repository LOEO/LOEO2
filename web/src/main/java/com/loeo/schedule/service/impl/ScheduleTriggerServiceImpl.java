package com.loeo.schedule.service.impl;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.loeo.base.service.BaseServiceImpl;
import com.loeo.schedule.core.SchedulerContext;
import com.loeo.schedule.core.factory.JobExecutingInfoFactory;
import com.loeo.schedule.domain.entity.ScheduleTrigger;
import com.loeo.schedule.mapper.ScheduleTriggerMapper;
import com.loeo.schedule.service.ScheduleJobService;
import com.loeo.schedule.service.ScheduleTriggerService;


@Service("scheduleTriggerService")
public class ScheduleTriggerServiceImpl extends BaseServiceImpl<ScheduleTriggerMapper,ScheduleTrigger> implements ScheduleTriggerService {
	@Resource
	private ScheduleJobService scheduleJobService;
	@Resource
	private JobExecutingInfoFactory jobExecutingInfoFactory;
	@Resource
	private SchedulerContext schedulerContext;
	@Resource
	private ScheduleTriggerMapper scheduleTriggerMapper;

}