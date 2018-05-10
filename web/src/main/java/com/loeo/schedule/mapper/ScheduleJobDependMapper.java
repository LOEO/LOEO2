package com.loeo.schedule.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.loeo.schedule.domain.entity.ScheduleJobDepend;

/**
 * @author LT(286269159 @ qq.com)
 */
@Mapper
public interface ScheduleJobDependMapper extends BaseMapper<ScheduleJobDepend> {

	List<ScheduleJobDepend> findByDependJobId(String dependId);

	void clearActualResultByDependJobId(String dependJobId);

	void updateActualResultByDependJobId(@Param("dependJobId") String dependJobId, @Param("actualResult") Object actualResult);

	ScheduleJobDepend findByJobIdAndDependJobId(@Param("jobId") String jobId, @Param("dependJobId") String dependJobId);

	void deleteByJobIdAndDependJobId(@Param("jobId") String jobId, @Param("dependJobId") String dependJobId);

	boolean hasDependByJobId(String jobId);

	void batchAdd(List<ScheduleJobDepend> scheduleJobDepends);

	void deleteByDependJobId(String scheduleJobId);
}