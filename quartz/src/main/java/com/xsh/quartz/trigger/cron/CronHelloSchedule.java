package com.xsh.quartz.trigger.cron;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class CronHelloSchedule {
	
	public static void main(String[] args) throws SchedulerException {

		//1.创建一个JobDetail实例,将该实例与HelloJob Class绑定
		JobDetail jobDetail = JobBuilder.newJob(CronHelloJob.class).withIdentity("myJob", "group1").build();
		
		//2.创建一个Trigger实例，定义该Job立即执行，并且每隔两秒钟重复执行一次，直到永远
		Trigger trigger = TriggerBuilder.newTrigger().withIdentity("myTrigger", "group1").
				withSchedule(CronScheduleBuilder.cronSchedule("0/10 * * * * ? ")).
				/**** builder模式创建Trigger ****/
				build();
		
		//3.创建Schedule实例
		SchedulerFactory scheduleFactory = new StdSchedulerFactory();
		Scheduler scheduler = scheduleFactory.getScheduler();
		scheduler.start();
		
		//4.返回最近一次即将要执行的时间
		System.out.println("scheduled time is "+scheduler.scheduleJob(jobDetail,trigger));
		
		try {
			//scheduler执行2s后挂起
			Thread.sleep(2000l);
			scheduler.standby();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		//
		try {
			//scheduler挂起3s后继续执行
			Thread.sleep(2000l);
			scheduler.start();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
		//shutdown(true)表示等待所有正在执行的job执行完毕之后，再关闭
		//scheduler(false)即shutdown()表示直接关闭scheduler
//		scheduler.shutdown(true);
		
		System.out.println("scheduler is shut down ?"+scheduler.isShutdown());
	}

}
