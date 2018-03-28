package com.xiaobai.scheduler;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import com.xiaobai.job.HelloJob;

public class SimpleTriggerTest {

	public static void main(String[] args) throws SchedulerException {

		JobDetail jobDetail = JobBuilder.newJob(HelloJob.class)
				.withIdentity("myJob")
				.build();
		
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println("Current time is :" + sdf.format(date));
		// 获取距离当前时间4秒后的时间
		date.setTime(date.getTime() + 4000);
		 
		/* 
		 * 1. 距离当前时间4秒钟后执行 一次  
		 */
//		SimpleTrigger trigger = (SimpleTrigger) TriggerBuilder
//				.newTrigger()
//				.withIdentity("myTrigger", "group1")
//				.startAt(date)
//				.build();
		/*
		 * 2. 距离当前时间 4 秒钟 首次执行，之后每隔两秒执行一次
		 */
		Trigger trigger = TriggerBuilder
				.newTrigger()
				.withIdentity("myTrigger", "group1")
				.startAt(date)
				.withSchedule(
						SimpleScheduleBuilder.simpleSchedule()
								.withIntervalInSeconds(2).withRepeatCount(3))
				.build();
		
		// 创建 Scheduler 实例
				SchedulerFactory sf = new StdSchedulerFactory();

				Scheduler sch = sf.getScheduler();
				sch.start();
				sch.scheduleJob(jobDetail, trigger);
	}

}
