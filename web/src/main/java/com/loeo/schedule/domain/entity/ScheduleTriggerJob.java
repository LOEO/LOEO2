package com.loeo.schedule.domain.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * 功能：schedule_trigger_job 实体类
 *
 * @author：LT(286269159@qq.com)
 * @created：2017-11-17 03:11 
 * @version：2017 Version：1.0 
 * @company：创海科技 Created with IntelliJ IDEA 
 */ 
@TableName("schedule_trigger_job")
public class ScheduleTriggerJob extends Model<ScheduleTriggerJob> {
	private String id;
	private String triggerId;
	private String jobId;

    public ScheduleTriggerJob() {  
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

	public void setTriggerId(String triggerId){
		this.triggerId=triggerId;
	}

	public String getTriggerId(){
		return triggerId;
	}

	public void setJobId(String jobId){
		this.jobId=jobId;
	}

	public String getJobId(){
		return jobId;
	}
}
