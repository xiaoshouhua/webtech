package com.xsh.quartz.trigger.simple;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.quartz.Trigger;

public class HelloJob implements Job{
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println("HelloJob date is "+sdf.format(new Date()));

		Trigger trigger = context.getTrigger();
		Date startTime = trigger.getStartTime();
		System.out.println("HelloJob startTime is "+sdf.format(startTime));
		
		Date endTime = trigger.getEndTime();
		System.out.println("HelloJob endTime is "+sdf.format(endTime));
		
		JobKey jobKey = trigger.getJobKey();
		
		System.out.println("JobKey info --- jobName "+jobKey.getName()+",Group : "+jobKey.getGroup());
	}

	
}
