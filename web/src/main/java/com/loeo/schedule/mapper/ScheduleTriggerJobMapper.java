package com.loeo.schedule.mapper;


import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.loeo.schedule.domain.entity.ScheduleTrigger;
import com.loeo.schedule.domain.entity.ScheduleTriggerJob;

/**
 * @author LT(286269159 @ qq.com)
 */
@Mapper
public interface ScheduleTriggerJobMapper extends BaseMapper<ScheduleTriggerJob> {

	void deleteByJobId(String id);

	ScheduleTriggerJob findByTriggerAndJobId(@Param("triggerId") String triggerId, @Param("jobId") String jobId);

	void deleteByTriggerIdAndJobId(@Param("triggerId") String triggerId, @Param("jobId") String jobId);

	void batchInsert(List<ScheduleTriggerJob> scheduleTriggerJobs);

	Set<ScheduleTrigger> findTriggersByJobId(String id);
}