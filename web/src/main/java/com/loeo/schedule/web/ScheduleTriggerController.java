package com.loeo.schedule.web;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.loeo.base.Result;
import com.loeo.base.constant.Message;
import com.loeo.schedule.domain.entity.ScheduleTrigger;
import com.loeo.schedule.service.ScheduleTriggerService;


/**
 * 功能：
 *
 * @author ：LT(286269159@qq.com)
 * @create ：2017-11-21 09:40:35
 * @version ：2017 Version：1.0

 */
@RestController
@RequestMapping("/api/triggers")
public class ScheduleTriggerController {

	@Resource
	private ScheduleTriggerService scheduleTriggerService;

	@PostMapping
	public Result add(@RequestBody ScheduleTrigger scheduleTrigger) {
		scheduleTriggerService.insert(scheduleTrigger);
		return Result.success(Message.SAVE_SUCCESS);
	}

	@PutMapping("/{triggerId}")
	public Result update(@PathVariable String triggerId, @RequestBody ScheduleTrigger scheduleTrigger) {
		scheduleTriggerService.updateById(scheduleTrigger);
		return Result.success(Message.UPDATE_SUCCESS);
	}

	@DeleteMapping("/{triggerId}")
	public Result delete(@PathVariable String triggerId) {
		scheduleTriggerService.deleteById(triggerId);
		return Result.success(Message.DELETE_SUCCESS);
	}

}
