package com.loeo.schedule.domain.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * 功能：schedule_trigger 实体类
 *
 * @author：LT(286269159@qq.com)
 * @created：2017-11-17 03:11 
 * @version：2017 Version：1.0 
 * @company：创海科技 Created with IntelliJ IDEA 
 */ 
@TableName("schedule_trigger")
public class ScheduleTrigger extends Model<ScheduleTrigger> {
	private String id;
	private String name;
	private Integer priority;
	private String cron;
	private String creator;
	private Date created;
	private String updater;
	private Date updated;

    public ScheduleTrigger() {  
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

	public void setName(String name){
		this.name=name;
	}

	public String getName(){
		return name;
	}

	public void setPriority(Integer priority){
		this.priority=priority;
	}

	public Integer getPriority(){
		return priority;
	}

	public void setCron(String cron){
		this.cron=cron;
	}

	public String getCron(){
		return cron;
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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;

		ScheduleTrigger that = (ScheduleTrigger) o;

		return id != null ? id.equals(that.id) : that.id == null;
	}

	@Override
	public int hashCode() {
		int result = super.hashCode();
		return  31 * result + (id != null ? id.hashCode() : 0);
	}
}
