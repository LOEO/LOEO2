package com.loeo.schedule.mapper;


import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.loeo.schedule.domain.entity.ScheduleJobInner;

/**
 * @author LT(286269159 @ qq.com)
 */
@Mapper
public interface ScheduleJobInnerMapper extends BaseMapper<ScheduleJobInner>{

	ScheduleJobInner findByJobId(String jobId);

	void deleteByJobId(String id);

}