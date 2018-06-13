package com.xsh.timer;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;

public class MyTimerDemo {

	public static void main(String[] args) {

		//1.创建一个Timer实例
		Timer timer = new Timer();
		
		//2.创建一个MyTimerTask实例
		MyTimerTask task = new MyTimerTask("task_1");
		
		//3.通过timer定时定频率调用MyTimerTask的业务逻辑 ，
		timer.schedule(task,2000l,3000l);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(sdf.format(new Date()));
		
		//最近发生此任务执行安排的时间
		System.out.println(sdf.format(task.scheduledExecutionTime()));
		
		//取消所有任务（终止此计时器，丢弃所有当前已安排的任务）
		timer.cancel();
	}
}
