package com.xiaobai.scheduler;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import com.xiaobai.job.HelloJob;

public class HelloScheduler {

	public static void main(String args[]) throws SchedulerException {

		// 创建 JobDetail 实例 ，与 HelloJob class 绑定

		JobDetail jobDetail = JobBuilder.newJob(HelloJob.class)
				.withIdentity("myJob")
//				.usingJobData("message", "hello job1")
//				.usingJobData("f", 3.14F)
				.build();
		/*
		 * 打印 JobDetail 的一些信息
		 */
		// System.out.println("jobDetial's name is : " +
		// jobDetail.getKey().getName());
		// System.out.println("jobDetial's group is : " +
		// jobDetail.getKey().getGroup());
		// System.out.println("jobDetial's class is : " + jobDetail.getClass());

		// 创建 Trigger ：触发器 ，触发Job任务执行  设计模式：Builder
		
		/*
		 * 属性：
		 * 1. JobKey
		 * 2. StartTime 首次触发的时间 值类型Java.util.Date
		 * 3. EndTime 触发器不在触发的时间
		 */
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println("Current time is :" + sdf.format(date));
		// 获取距离当前时间3秒后的时间
		date.setTime(date.getTime() + 3000);
		// 获取距离当前时间6秒后的时间
		Date endDate = new Date();
		endDate.setTime(endDate.getTime() + 6000);
		
		
		Trigger trigger = TriggerBuilder
				.newTrigger()
				.withIdentity("myTrigger", "group1")
//				.usingJobData("message", "mytrigger1")
				// .usingJobData("d", 2.0D)
//				.startNow()
				.startAt(date)
				.endAt(endDate)
				.withSchedule(
						SimpleScheduleBuilder.simpleSchedule()
								.withIntervalInSeconds(2).repeatForever())
				.build();

		
		
		// 创建 Scheduler 实例
		SchedulerFactory sf = new StdSchedulerFactory();

		Scheduler sch = sf.getScheduler();
		sch.start();
		sch.scheduleJob(jobDetail, trigger);

	}

}
