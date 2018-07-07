package com.loeo.schedule.domain.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.loeo.schedule.core.log.JobLogger;

/**
 * 功能：schedule_log 实体类
 *
 * @author ：Tony.L(286269159@qq.com)
 * @created：2017-11-17 03:11 
 * @version ：2017 Version：1.0

 */ 
@TableName("schedule_log")
public class ScheduleLog extends Model<ScheduleLog> implements JobLogger {
	private String id;
	private String jobId;
	private String triggerId;
	@TableField("is_success")
	private Boolean success;
	private String description;
	private Date started;
	private Date ended;

    public ScheduleLog() {  
     }

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setJobId(String jobId){
		this.jobId=jobId;
	}

	public String getJobId(){
		return jobId;
	}

	public void setTriggerId(String triggerId){
		this.triggerId=triggerId;
	}

	public String getTriggerId(){
		return triggerId;
	}

	public void setSuccess(Boolean success){
		this.success=success;
	}

	public Boolean getSuccess(){
		return success;
	}

	public void setDescription(String description){
		this.description = description;
	}

	@Override
	public String getDescription(){
		return description;
	}

	public void setStarted(Date started){
		this.started=started;
	}

	@Override
	public Date getStarted(){
		return started;
	}

	public void setEnded(Date ended) {
		this.ended = ended;
	}

	@Override
	public Date getEnded() {
		return ended;
	}

	@Override
	public Boolean success() {
		return success;
	}

	@Override
	public String toString() {
		return "ScheduleLog{" +
				"jobId='" + jobId + '\'' +
				", triggerId='" + triggerId + '\'' +
				", success=" + success +
				", description='" + description + '\'' +
				", started=" + started +
				", ended=" + ended +
				'}';
	}
}
