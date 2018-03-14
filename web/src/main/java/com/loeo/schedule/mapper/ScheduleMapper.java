package com.loeo.schedule.mapper;


import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.loeo.schedule.domain.entity.Schedule;
@Mapper
public interface ScheduleMapper extends BaseMapper<Schedule> {

}