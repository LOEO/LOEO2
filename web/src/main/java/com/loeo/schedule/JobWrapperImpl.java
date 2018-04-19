package com.loeo.schedule;

import java.util.HashSet;
import java.util.Set;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobKey;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;

import com.loeo.schedule.core.AbstractJobWrapper;
import com.loeo.schedule.core.factory.JobExecutingInfoFactory;
import com.loeo.schedule.domain.entity.ScheduleJob;
import com.loeo.schedule.domain.entity.ScheduleTrigger;


/**
 * 功能：
 *
 * @author ：LT(286269159@qq.com)
 * @create ：2017-11-18 10:13:31
 * @version ：2017 Version：1.0

 */

public class JobWrapperImpl extends AbstractJobWrapper {
	private ScheduleJob scheduleJob;

	public JobWrapperImpl(ScheduleJob scheduleJob, JobExecutingInfoFactory jobExecutingInfoFactory) {
		super(jobExecutingInfoFactory);
		this.scheduleJob = scheduleJob;
	}

	@Override
	public Set<Trigger> getTriggers() {
		if (triggers == null) {
			triggers = new HashSet<>();
			Set<ScheduleTrigger> scheduleTriggers = scheduleJob.getTriggers();
			if (scheduleTriggers != null) {
				for (ScheduleTrigger scheduleTrigger : scheduleTriggers) {
					Trigger trigger = TriggerBuilder.newTrigger()
							.withIdentity(scheduleTrigger.getId() + "." + getJobKey())
							.withSchedule(CronScheduleBuilder.cronSchedule(scheduleTrigger.getCron()))
							.build();
					triggers.add(trigger);
				}
			}
		}
		return triggers;
	}


	@Override
	public ScheduleJob getJobData() {
		return scheduleJob;
	}


	@Override
	protected JobKey createJobKey() {
		return new JobKey(scheduleJob.getId(), scheduleJob.getScheduleId());
	}

	@Override
	protected ScheduleTrigger getCurrentTriggerData(Trigger trigger) {
		String triggerName = trigger.getKey().getName();
		if (triggerName == null) {
			return null;
		}
		String[] triggerNameArr = triggerName.split("\\.");
		if (triggerNameArr.length != 3) {
			return null;
		}
		String triggerId = triggerNameArr[0];
		Set<ScheduleTrigger> scheduleTriggers = scheduleJob.getTriggers();
		if (scheduleTriggers == null || scheduleTriggers.isEmpty()) {
			return null;
		}
		return scheduleTriggers.stream()
				.filter(st -> st.getId().equals(triggerId))
				.findAny().orElse(null);
	}
}
