package com.loeo.schedule.domain.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * 功能：schedule 实体类
 *
 * @author ：Tony.L(286269159@qq.com)
 * @created：2017-11-17 03:11 
 * @version ：2017 Version：1.0

 */ 
@TableName("schedule")
public class Schedule extends Model<Schedule> {
	private String id;
	private String name;
	@TableField("is_enabled")
	private Boolean enabled;
	private String description;
	private String creator;
	private Date created;
	private String updater;
	private Date updated;

    public Schedule() {  
     }

	@Override
	protected Serializable pkVal() {
		return id;
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

	public void setEnabled(Boolean enabled){
		this.enabled=enabled;
	}

	public Boolean getEnabled(){
		return enabled;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public String getDescription(){
		return description;
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
}
