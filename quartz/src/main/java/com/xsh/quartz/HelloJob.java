package com.xsh.quartz;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Trigger;

public class HelloJob implements Job{
	
	private String name;
	private Double money;
	private String books;
	private String nick;
	private Double balance;
	

	public  void println(JobDetail jobDetail) {
		System.out.println("---------------JobDetail start ---------------");
		System.out.println("jobDetail's name :" +jobDetail.getKey().getName());
		System.out.println("jobDetail's group :"+jobDetail.getKey().getGroup());
		System.out.println("jobDetail's jobClass :"+jobDetail.getJobClass().getName());
		JobDataMap jobDataMap = jobDetail.getJobDataMap();
		String name = jobDataMap.getString("name");
		Double money = jobDataMap.getDouble("money");
		String books = jobDataMap.getString("books");
		System.out.println(name+":::"+money+":::"+books);
		System.out.println("---------------JobDetail start ---------------");
	}
	
	public void println(Trigger trigger) {
		System.out.println("---------------Trigger start ---------------");
		JobDataMap tJobDataMap = trigger.getJobDataMap();
		String nick = tJobDataMap.getString("nick");
		Double balance = tJobDataMap.getDouble("balance");
		String books = tJobDataMap.getString("books");
		System.out.println(nick+":::"+balance+":::"+books);
		System.out.println("---------------Trigger start ---------------");
	}
	
	public void println(JobDataMap jobDataMap) {
		System.out.println("---------------MergedJobDataMap start ---------------");
		String name = jobDataMap.getString("name");
		Double money = jobDataMap.getDouble("money");
		String nick = jobDataMap.getString("nick");
		Double balance = jobDataMap.getDouble("balance");
		String books = jobDataMap.getString("books");
		System.out.println(name+":::"+money+":::"+nick+":::"+balance+":::"+books);
		System.out.println("--------------- MergedJobDataMap end --------------");
	}
	
	public void println() {
		System.out.println("---------------setter start ---------------");
		System.out.println(this.name+":::"+this.money+":::"+this.nick+":::"+this.balance+":::"+this.books);
		System.out.println("--------------- setter end --------------");
	}
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println("HelloJob date is "+sdf.format(new Date()));
		System.out.println("Hello World!");
		
		//同名属性会被Trigger中的覆盖
		println(context.getJobDetail());
		
		println(context.getTrigger());
		
		println(context.getMergedJobDataMap());
		
		println();
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public void setBooks(String books) {
		this.books = books;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	
}
