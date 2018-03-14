package com.loeo.schedule.mapper;


import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.loeo.schedule.domain.entity.ScheduleJobInner;

@Mapper
public interface ScheduleJobInnerMapper extends BaseMapper<ScheduleJobInner>{

	ScheduleJobInner findByJobId(String jobId);

	void deleteByJobId(String id);

}