package com.loeo.schedule.web;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.loeo.base.Result;
import com.loeo.base.constant.Message;
import com.loeo.schedule.domain.entity.Schedule;
import com.loeo.schedule.service.ScheduleService;


/**
 * 功能：
 *
 * @author ：LT(286269159@qq.com)
 * @create ：2017-11-21 09:28:43
 * @version ：2017 Version：1.0

 */
@RestController
@RequestMapping("/api/schedules")
public class ScheduleController {

	@Resource
	private ScheduleService scheduleService;
	@PostMapping
	public Result add(@RequestBody Schedule schedule) {
		schedule.setEnabled(Boolean.TRUE);
		scheduleService.insert(schedule);
		return Result.success(Message.SAVE_SUCCESS);
	}

	@DeleteMapping("/{scheduleId}")
	public Result delete(@PathVariable String scheduleId) {
		scheduleService.deleteById(scheduleId);
		return Result.success(Message.DELETE_SUCCESS);
	}
}
