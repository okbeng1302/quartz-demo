package com.xiaobai.scheduler;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import com.xiaobai.job.HelloJob;

/*
 * @desc:基于日历的作业调度器
 * 
 * 		Cron 表达式：7 个子表达式组成的字符串
 * 					格式： [秒] [分] [小时] [日] [月] [周] [年]
 */
public class CronTriggerTest {

	public static void main(String[] args) throws SchedulerException {

		// 创建 JobDetail 实例 ，与 HelloJob class 绑定

		JobDetail jobDetail = JobBuilder.newJob(HelloJob.class)
				.withIdentity("myJob").build();

		CronTrigger trigger = (CronTrigger) TriggerBuilder
				.newTrigger()
				.withIdentity("myTrigger", "group1")
				.withSchedule(CronScheduleBuilder.cronSchedule("* * * * * ? *"))
				.build();
		/*
		 * 1. 2018 年内每天 10 点 15 分执行
		 * 		0 15 10 ？ * * 2018
		 * 2. 每天的 14:00-14:59:55，18:00-18:59:55 ,每五秒钟执行一次
		 * 		0/5 * 14,18 * * ?
		 * 3. 每月的周一至周五的上午 10:15 开始执行
		 * 		0 15 10 ? * MON-FRI
		 * 4. 每月的第三周的星期五开始触发
		 * 		0 15 10 ？ * 6#3
		 * 5. 从 2017-2018 年每月最后一周的星期五的 10:15分触发
		 * 		0 15 10 ？ 6L 2017-2018
		 */
		
		// 创建 Scheduler 实例
		SchedulerFactory sf = new StdSchedulerFactory();

		Scheduler sch = sf.getScheduler();
		sch.start();
		sch.scheduleJob(jobDetail, trigger);

	}

}
