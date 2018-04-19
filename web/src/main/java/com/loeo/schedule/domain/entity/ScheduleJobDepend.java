package com.loeo.schedule.domain.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * 功能：schedule_job_depend 实体类
 *
 * @author ：Tony.L(286269159@qq.com)
 * @created：2017-11-17 03:32 
 * @version ：2017 Version：1.0

 */ 
@TableName("schedule_job_depend")
public class ScheduleJobDepend extends Model<ScheduleJobDepend>{
	private String id;
	private String jobId;
	private String dependJobId;
	private String expectResult;
	private String actualResult;

    public ScheduleJobDepend() {  
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

	public void setJobId(String jobId){
		this.jobId=jobId;
	}

	public String getJobId(){
		return jobId;
	}

	public void setDependJobId(String dependJobId){
		this.dependJobId=dependJobId;
	}

	public String getDependJobId(){
		return dependJobId;
	}

	public void setExpectResult(String expectResult){
		this.expectResult=expectResult;
	}
	public String getExpectResult(){
		return expectResult;
	}

	public void setActualResult(String actualResult){
		this.actualResult=actualResult;
	}

	public String getActualResult(){
		return actualResult;
	}
}
