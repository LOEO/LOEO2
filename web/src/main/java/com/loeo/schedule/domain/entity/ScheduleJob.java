package com.loeo.schedule.domain.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * 功能：schedule_job 实体类
 *
 * @author ：Tony.L(286269159@qq.com)
 * @created：2017-11-17 03:11 
 * @version ：2017 Version：1.0

 */ 
@TableName("schedule_job")
public class ScheduleJob extends Model<ScheduleJob> {
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

	private Set<ScheduleTrigger> triggers = new HashSet<>();

    public ScheduleJob() {  
     }

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setScheduleId(String scheduleId){
		this.scheduleId=scheduleId;
	}

	public String getScheduleId(){
		return scheduleId;
	}

	public void setName(String name){
		this.name=name;
	}

	public String getName(){
		return name;
	}

	public void setParent(String parent){
		this.parent=parent;
	}

	public String getParent(){
		return parent;
	}

	public void setType(Integer type){
		this.type=type;
	}

	public Integer getType(){
		return type;
	}

	public void setEnabled(Boolean enabled){
		this.enabled=enabled;
	}

	public Boolean getEnabled(){
		return enabled;
	}

	public void setData(String data){
		this.data=data;
	}

	public String getData(){
		return data;
	}

	public void setCreator(String creator){
		this.creator=creator;
	}

	public String getCreator(){
		return creator;
	}

	public void setCreated(Date created){
		this.created=created;
	}

	public Date getCreated(){
		return created;
	}

	public void setUpdater(String updater){
		this.updater=updater;
	}

	public String getUpdater(){
		return updater;
	}

	public void setUpdated(Date updated){
		this.updated=updated;
	}

	public Date getUpdated(){
		return updated;
	}

	public Set<ScheduleTrigger> getTriggers() {
		return triggers;
	}

	public void setTriggers(Set<ScheduleTrigger> triggers) {
		this.triggers = triggers;
	}


	@Override
	public String toString() {
		return "ScheduleJob{" +
				"scheduleId='" + scheduleId + '\'' +
				", name='" + name + '\'' +
				", parent='" + parent + '\'' +
				", type=" + type +
				", enabled=" + enabled +
				", data='" + data + '\'' +
				", creator='" + creator + '\'' +
				", created=" + created +
				", updater='" + updater + '\'' +
				", updated=" + updated +
				", triggers=" + triggers +
				'}';
	}
}
