package com.loeo.schedule.domain.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * 功能：schedule_job_inner 实体类
 *
 * @author：LT(286269159@qq.com)
 * @created：2017-11-17 03:11 
 * @version：2017 Version：1.0 
 * @company：创海科技 Created with IntelliJ IDEA 
 */ 
@TableName("schedule_job_inner")
public class ScheduleJobInner extends Model<ScheduleJobInner> {
	private String id;
	private String jobId;
	private String className;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ScheduleJobInner() { }

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	public void setJobId(String jobId){
		this.jobId=jobId;
	}

	public String getJobId(){
		return jobId;
	}

	public void setClassName(String className){
		this.className=className;
	}

	public String getClassName(){
		return className;
	}
}
