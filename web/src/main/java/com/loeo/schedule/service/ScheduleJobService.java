package com.loeo.schedule.service;


import java.util.List;

import com.baomidou.mybatisplus.service.IService;
import com.loeo.schedule.domain.dto.ScheduleJobDto;
import com.loeo.schedule.domain.entity.ScheduleJob;
import com.loeo.schedule.domain.entity.ScheduleJobDepend;


/**
 * @author LT(286269159 @ qq.com)
 */
public interface ScheduleJobService extends IService<ScheduleJob> {

	void addScheduleJob(ScheduleJobDto scheduleJobDto);

	List<ScheduleJob> findAllEnabledJob();

	List<ScheduleJob> findAllEnabledJobsAndTriggers();

	boolean hasTriggerById(String id);

	List<ScheduleJob> findByIdList(List<String> jobIds);

	List<ScheduleJob> findByScheduleId(String id);

	void bindTriggers(String scheduleJobId, String[] triggerIds);

	void setDepends(String scheduleJobId, List<ScheduleJobDepend> scheduleJobDependList);

	List<ScheduleJob> findEnabledJobsByTriggerId(String triggerId);

	boolean isExistByIdList(List<String> dependIds);
}