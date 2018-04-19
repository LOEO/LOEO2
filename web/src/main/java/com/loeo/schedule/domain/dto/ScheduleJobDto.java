package com.loeo.schedule.domain.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.loeo.schedule.domain.entity.ScheduleJob;

/**
 * 功能：
 *
 * @author ：LT(286269159@qq.com)
 * @create ：2017-11-21 10:54:24
 * @version ：2017 Version：1.0

 */
public class ScheduleJobDto {
	private String id;
	private String scheduleId;
	private String name;
	private String parent;
	private Integer type;
	private Boolean enabled;
	private String data;
	private String creator;
	private Date created;
	private String updater;
	private Date updated;
	private String className;

	@JsonIgnore
	public ScheduleJob getScheduleJob() {
		ScheduleJob scheduleJob = new ScheduleJob();
		scheduleJob.setId(id);
		scheduleJob.setType(type);
		scheduleJob.setName(name);
		scheduleJob.setParent(parent);
		scheduleJob.setScheduleId(scheduleId);
		scheduleJob.setData(data);
		scheduleJob.setEnabled(enabled);
		scheduleJob.setCreator(creator);
		scheduleJob.setCreated(created);
		scheduleJob.setUpdater(updater);
		scheduleJob.setUpdated(updated);
		return scheduleJob;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(String scheduleId) {
		this.scheduleId = scheduleId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public String getUpdater() {
		return updater;
	}

	public void setUpdater(String updater) {
		this.updater = updater;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}
}
