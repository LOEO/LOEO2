package com.loeo.schedule.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.loeo.schedule.domain.entity.ScheduleJob;

@Mapper
public interface ScheduleJobMapper extends BaseMapper<ScheduleJob> {

	List<ScheduleJob> findAllEnabledJob();

	List<ScheduleJob> findAllEnabledJobsAndTriggers();

	boolean hasTriggerById(String id);

	List<ScheduleJob> findByIdList(List<String> jobIds);

	ScheduleJob findById(String id);

	List<ScheduleJob> findByScheduleId(String id);

	boolean isExistByIdList(List<String> dependIds);

	List<ScheduleJob> findEnabledJobsByTriggerId(String triggerId);
}