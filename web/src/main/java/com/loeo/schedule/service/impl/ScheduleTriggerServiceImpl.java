package com.loeo.schedule.service.impl;


import org.springframework.stereotype.Service;

import com.loeo.base.service.BaseServiceImpl;
import com.loeo.schedule.domain.entity.ScheduleTrigger;
import com.loeo.schedule.mapper.ScheduleTriggerMapper;
import com.loeo.schedule.service.ScheduleTriggerService;


/**
 * @author LT(286269159 @ qq.com)
 */
@Service("scheduleTriggerService")
public class ScheduleTriggerServiceImpl extends BaseServiceImpl<ScheduleTriggerMapper,ScheduleTrigger> implements ScheduleTriggerService {

}