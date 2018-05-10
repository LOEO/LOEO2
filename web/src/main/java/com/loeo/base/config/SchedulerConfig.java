package com.loeo.base.config;

import javax.annotation.Resource;

import org.quartz.JobListener;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.loeo.base.config.properties.ScheduleProperties;
import com.loeo.schedule.core.SchedulerContext;
import com.loeo.schedule.core.jobloader.JobLoader;


/**
 * 功能：
 *
 * @author ：LT(286269159@qq.com)
 * @version ：2018 Version：1.0
 * @create ：2018-05-10 10:41:37
 */
@Configuration
@EnableConfigurationProperties(ScheduleProperties.class)
public class SchedulerConfig {
	@Resource
	private ScheduleProperties scheduleProperties;

	@Bean
	public SchedulerContext schedulerContext(JobLoader jobLoader, JobListener jobListener) {
		return new SchedulerContext(scheduleProperties.isEnable(), jobLoader, jobListener);
	}
}
