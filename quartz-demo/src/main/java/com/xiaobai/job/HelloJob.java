package com.xiaobai.job;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.quartz.Trigger;
import org.quartz.TriggerKey;

public class HelloJob implements Job {
	
	
	private String message;
	private Float f;
	private Double d;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Float getF() {
		return f;
	}

	public void setF(Float f) {
		this.f = f;
	}

	public Double getD() {
		return d;
	}

	public void setD(Double d) {
		this.d = d;
	}

	// 编写业务逻辑
	public void execute(JobExecutionContext context)
			throws JobExecutionException {

		Date date = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println("Current time is :" + sf.format(date));

//		Trigger trigger = context.getTrigger();
//		System.out.println("Start time is :" + trigger.getStartTime());
//		System.out.println("End time is :" +trigger.getEndTime());
		
		
		
		
		
		/*
		 * 获取 JobDataMap 信息方法
		 */
		// Strat.. 
//		JobKey jobKey = context.getJobDetail().getKey();
//		System.out.println("My JobDetail name and group is " + jobKey.getName()
//				+ " " + jobKey.getGroup());
//
//		TriggerKey triggerKey = context.getTrigger().getKey();
//
//		System.out.println("My Trigger name and group is "
//				+ triggerKey.getName() + " " + triggerKey.getGroup());

		
		
		
		// 接收 JobDetail 和 Trigger 中的信息
		
		/*
		 * 两种方法 获取 JobDataMap 中的信息
		 * 1. 下面注释的这种
		 * 2. 使用 setter 方法，即没有注释的这种
		 */
//		JobDataMap datamap = context.getJobDetail().getJobDataMap();
//		JobDataMap tdatamap = context.getTrigger().getJobDataMap();
//
//		String jobMsg = datamap.getString("message");
//		Float f = datamap.getFloat("f");
//
//		String tMsg = tdatamap.getString("message");
//		Double d = tdatamap.getDouble("d");
		
//		
//		System.out.println("my JobDetail datamap is :" + message + " "
//				+ f.toString());
//
//		System.out.println("my Trigger datamap is :" + message + " "
//				+ d.toString());
// End ...
	}

}
