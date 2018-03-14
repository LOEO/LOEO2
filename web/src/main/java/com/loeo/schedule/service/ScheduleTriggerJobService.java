package com.loeo.schedule.service;


import java.util.List;
import java.util.Set;

import com.baomidou.mybatisplus.service.IService;
import com.loeo.schedule.domain.entity.ScheduleTrigger;
import com.loeo.schedule.domain.entity.ScheduleTriggerJob;


public interface ScheduleTriggerJobService extends IService<ScheduleTriggerJob>{

	void deleteByJobId(String id);

	void add(ScheduleTriggerJob triggerJob);

	void delete(ScheduleTriggerJob triggerJob);

	void batchInsert(List<ScheduleTriggerJob> scheduleTriggers);

	Set<ScheduleTrigger> findTriggersByJobId(String id);
}