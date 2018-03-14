package com.loeo.schedule.service;


import com.baomidou.mybatisplus.service.IService;
import com.loeo.schedule.domain.entity.ScheduleJobInner;

public interface ScheduleJobInnerService extends IService<ScheduleJobInner>{
	ScheduleJobInner findByJobId(String jobId);

	void deleteByJobId(String id);

}