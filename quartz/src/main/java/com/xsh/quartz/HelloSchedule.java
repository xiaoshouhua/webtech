package com.xsh.quartz;

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

public class HelloSchedule {
	
	public static void println(JobDetail jobDetail) {
		System.out.println("jobDetail's name :" +jobDetail.getKey().getName());
		System.out.println("jobDetail's group :"+jobDetail.getKey().getGroup());
		System.out.println("jobDetail's jobClass :"+jobDetail.getJobClass().getName());
	}

	public static void main(String[] args) throws SchedulerException {

		//1.创建一个JobDetail实例,将该实例与HelloJob Class绑定
		JobDetail jobDetail = JobBuilder.newJob(HelloJob.class).withIdentity("myJob", "group1")
				//设置自定义参数
				.usingJobData("name","xsh")
				.usingJobData("money",235.0d)
				.usingJobData("books","射雕英雄传")
				.build();
	
		println(jobDetail);
		
		//2.创建一个Trigger实例，定义该Job立即执行，并且每隔两秒钟重复执行一次，直到永远
		Trigger trigger = TriggerBuilder.newTrigger().withIdentity("myTrigger", "group1")
				.startNow().withSchedule(
						SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(2).repeatForever()
				)
				//设置自定义参数
				.usingJobData("nick","java_xsh")
				.usingJobData("balance",299.0d)
				.usingJobData("books","射雕英雄传")
				.build();
		
		//3.创建Schedule实例
		SchedulerFactory scheduleFactory = new StdSchedulerFactory();
		Scheduler scheduler = scheduleFactory.getScheduler();
		scheduler.start();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println("HelloSchedule date is "+sdf.format(new Date()));
		
		//4.
		scheduler.scheduleJob(jobDetail,trigger);
	}

}
