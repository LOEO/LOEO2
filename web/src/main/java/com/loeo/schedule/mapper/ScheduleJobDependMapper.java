package com.loeo.schedule.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.loeo.schedule.domain.entity.ScheduleJobDepend;

/**
 * @author LT(286269159 @ qq.com)
 */
@Mapper
public interface ScheduleJobDependMapper extends BaseMapper<ScheduleJobDepend> {

	List<ScheduleJobDepend> findByDependJobId(String dependId);

	void clearActualResultByDependJobId(String dependJobId);

	void updateActualResultByDependJobId(String dependJobId, Object result);

	ScheduleJobDepend findByJobIdAndDependJobId(String jobId, String dependJobId);

	void deleteByJobIdAndDependJobId(String jobId, String dependJobId);

	boolean hasDependByJobId(String jobId);

	void batchAdd(List<ScheduleJobDepend> scheduleJobDepends);

	void deleteByDependJobId(String scheduleJobId);
}