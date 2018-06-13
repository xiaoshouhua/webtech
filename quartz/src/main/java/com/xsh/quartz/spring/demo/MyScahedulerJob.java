package com.xsh.quartz.spring.demo;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class MyScahedulerJob extends QuartzJobBean {
	
	private String taskName;

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println("MyScahedulerJob start !!! date is "+sdf.format(new Date()));
		System.out.println("MyScahedulerJob taskName ..." +taskName);
		System.out.println("MyScahedulerJob end ..." + this.hashCode());
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	
}
