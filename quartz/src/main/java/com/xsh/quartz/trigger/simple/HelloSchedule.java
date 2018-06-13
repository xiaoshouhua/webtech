package com.xsh.quartz.trigger.simple;

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

public class HelloSchedule {
	
	public static void println(JobDetail jobDetail) {
		System.out.println("jobDetail's name :" +jobDetail.getKey().getName());
		System.out.println("jobDetail's group :"+jobDetail.getKey().getGroup());
		System.out.println("jobDetail's jobClass :"+jobDetail.getJobClass().getName());
	}

	public static void main(String[] args) throws SchedulerException {

		//1.创建一个JobDetail实例,将该实例与HelloJob Class绑定
		JobDetail jobDetail = JobBuilder.newJob(HelloJob.class).withIdentity("myJob", "group1").build();
		//println(jobDetail);
		
		
		Date date = new Date();
		final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println("CurrenctName date is " + sdf.format(date));
		
		//XXX 1.1 获取距离当前时间3秒后的时间
		date.setTime(date.getTime() + 3000L);
		
		Date endDate = new Date();
		//XXX 1.2 获取距离当前时间6秒后的时间
		endDate.setTime(endDate.getTime() + 6000l);

		//2.创建一个Trigger实例，定义该Job立即执行，并且每隔两秒钟重复执行一次，直到永远
		Trigger trigger = TriggerBuilder.newTrigger().withIdentity("myTrigger", "group1").
				/****设置开始和结束时间*******/
				startAt(date).endAt(endDate).
				/****每隔两秒重复执行,不重复执行可省略 ****/
				withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(2).repeatForever()).
				/****每隔两秒执行,执行次数为无限次,不重复执行可省略,作用跟repeatForever()一致 ****/
				//withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(2).withRepeatCount(SimpleTrigger.REPEAT_INDEFINITELY)).
				/**** builder模式创建Trigger ****/
				build();
		
		//3.创建Schedule实例
		SchedulerFactory scheduleFactory = new StdSchedulerFactory();
		Scheduler scheduler = scheduleFactory.getScheduler();
		scheduler.start();
		
		//4.
		scheduler.scheduleJob(jobDetail,trigger);
	}

}
