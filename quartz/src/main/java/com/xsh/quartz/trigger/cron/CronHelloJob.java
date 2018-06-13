package com.xsh.quartz.trigger.cron;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.quartz.Trigger;

public class CronHelloJob implements Job{
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println("CronHelloJob date is "+sdf.format(new Date()));

		Trigger trigger = context.getTrigger();
		JobKey jobKey = trigger.getJobKey();
		
		System.out.println("JobKey info --- jobName "+jobKey.getName()+",Group : "+jobKey.getGroup());
	}

	
}
