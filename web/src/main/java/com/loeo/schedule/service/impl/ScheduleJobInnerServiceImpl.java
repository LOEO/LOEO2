package com.loeo.schedule.service.impl;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.loeo.base.service.BaseServiceImpl;
import com.loeo.schedule.domain.entity.ScheduleJobInner;
import com.loeo.schedule.mapper.ScheduleJobInnerMapper;
import com.loeo.schedule.service.ScheduleJobInnerService;


/**
 * @author LT(286269159 @ qq.com)
 */
@Service("scheduleJobInnerService")
public class ScheduleJobInnerServiceImpl extends BaseServiceImpl<ScheduleJobInnerMapper,ScheduleJobInner> implements ScheduleJobInnerService {
	@Resource
	private ScheduleJobInnerMapper scheduleJobInnerMapper;

	@Override
	public ScheduleJobInner findByJobId(String jobId) {
		return scheduleJobInnerMapper.findByJobId(jobId);
	}

	@Override
	public void deleteByJobId(String id) {
		scheduleJobInnerMapper.deleteByJobId(id);
	}

}