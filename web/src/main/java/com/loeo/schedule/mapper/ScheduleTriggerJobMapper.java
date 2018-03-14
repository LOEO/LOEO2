package com.loeo.schedule.mapper;


import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.loeo.schedule.domain.entity.ScheduleTrigger;
import com.loeo.schedule.domain.entity.ScheduleTriggerJob;

@Mapper
public interface ScheduleTriggerJobMapper extends BaseMapper<ScheduleTriggerJob> {

	void deleteByJobId(String id);

	ScheduleTriggerJob findByTriggerAndJobId(String triggerId, String jobId);

	void deleteByTriggerIdAndJobId(String triggerId, String jobId);

	void batchInsert(List<ScheduleTriggerJob> scheduleTriggerJobs);

	Set<ScheduleTrigger> findTriggersByJobId(String id);
}