package com.loeo.schedule.service;


import java.util.List;

import com.baomidou.mybatisplus.service.IService;
import com.loeo.schedule.domain.entity.ScheduleJobDepend;


/**
 * @author LT(286269159 @ qq.com)
 */
public interface ScheduleJobDependService extends IService<ScheduleJobDepend>{
	void add(ScheduleJobDepend scheduleJobDepend);

	void delete(ScheduleJobDepend scheduleJobDepend);

	boolean hasDependByJobId(String jobId);

	void batchAdd(List<ScheduleJobDepend> scheduleJobDepends);

	void deleteByDependJobId(String scheduleJobId);
}