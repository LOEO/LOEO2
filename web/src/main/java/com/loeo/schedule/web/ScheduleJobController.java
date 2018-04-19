package com.loeo.schedule.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.loeo.base.Result;
import com.loeo.base.constant.Message;
import com.loeo.schedule.domain.dto.ScheduleJobDto;
import com.loeo.schedule.domain.entity.ScheduleJobDepend;
import com.loeo.schedule.service.ScheduleJobService;

/**
 * 功能：
 *
 * @author ：LT(286269159@qq.com)
 * @create ：2017-11-21 09:30:58
 * @version ：2017 Version：1.0

 */
@RestController
@RequestMapping("/api/schedules/{scheduleId}/jobs")
public class ScheduleJobController {

	@Resource
	private ScheduleJobService scheduleJobService;
	@PostMapping
	public Result add(@PathVariable String scheduleId, @RequestBody ScheduleJobDto scheduleJobDto) {
		scheduleJobDto.setEnabled(Boolean.TRUE);
		scheduleJobDto.setScheduleId(scheduleId);
		scheduleJobService.addScheduleJob(scheduleJobDto);
		return Result.success(Message.SAVE_SUCCESS);
	}

	@DeleteMapping("/{scheduleJobId}")
	public Result deleteById(@PathVariable String scheduleJobId) {
		scheduleJobService.deleteById(scheduleJobId);
		return Result.success(Message.SAVE_SUCCESS);
	}

	@PostMapping("/{scheduleJobId}/triggers")
	public Result bindTriggers(@PathVariable String scheduleJobId, @RequestBody String[] triggerIds) {
		scheduleJobService.bindTriggers(scheduleJobId,triggerIds);
		return Result.success(Message.SAVE_SUCCESS);
	}

	@PostMapping("/{scheduleJobId}/depends")
	public Result setDepends(@PathVariable String scheduleJobId, @RequestBody List<ScheduleJobDepend> scheduleJobDependList) {
		scheduleJobService.setDepends(scheduleJobId,scheduleJobDependList);
		return Result.success(Message.SAVE_SUCCESS);
	}
}
