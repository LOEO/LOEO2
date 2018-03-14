package com.loeo.schedule.service;


import com.baomidou.mybatisplus.service.IService;
import com.loeo.schedule.domain.entity.ScheduleJobInner;

/**
 * @author LT(286269159 @ qq.com)
 */
public interface ScheduleJobInnerService extends IService<ScheduleJobInner>{
	ScheduleJobInner findByJobId(String jobId);

	void deleteByJobId(String id);

}