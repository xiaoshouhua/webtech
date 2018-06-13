package com.xsh.timer;

import java.util.Calendar;
import java.util.Timer;

public class ScheduleDemo {
	
	public static void main(String[] args) {

		schedule1("task_1",3000);
		
		schedule2("task_2", 4000 , 2000);
		
		schedule3("task_3",5000);
		
		schedule4("task_4", 4000 , 2000);
		
		
		scheduleAtFixedRate("schedule_AtFixedRate", 4000 , 2000);
		scheduleAtFixedRate2("schedule_AtFixedRate2", 4000 , 2000);
	}

	/**
	 * 1.在时间等于或超过time的时候执行且仅执行一次task
	 * @param taskName
	 * @param milliSecond
	 */
	private static void schedule1(String taskName,int milliSecond) {
		//1.创建一个Timer实例
		Timer timer = new Timer();
		
		//2.创建一个MyTimerTask实例
		MyTimerTask task = new MyTimerTask(taskName);
		
		//3.通过timer定时定频率调用MyTimerTask的业务逻辑 ，
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MILLISECOND, milliSecond);
		
		timer.schedule(task,calendar.getTime());
	}

	/**
	 * 2.时间等于或超过time时首次执行task，之后每隔period毫秒重复执行一次task
	 * @param taskName
	 * @param delay
	 * @param period
	 */
	private static void schedule2(String taskName,int delay, int period) {
		//1.创建一个Timer实例
		Timer timer = new Timer();
		
		//2.创建一个MyTimerTask实例
		MyTimerTask task = new MyTimerTask(taskName);
		
		//3.通过timer定时定频率调用MyTimerTask的业务逻辑 ，
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MILLISECOND, delay);
		
		timer.schedule(task, calendar.getTime()	,period);
	}
	
	/**
	 * 3.等待delay毫秒后执行且仅执行一次task
	 * @param taskName
	 * @param milliSecond
	 */
	private static void schedule3(String taskName,int delay) {
		//1.创建一个Timer实例
		Timer timer = new Timer();
		
		//2.创建一个MyTimerTask实例
		MyTimerTask task = new MyTimerTask(taskName);
		
		//3.通过timer定时定频率调用MyTimerTask的业务逻辑 ，
		timer.schedule(task,delay);
	}
	
	/**
	 * 4.等待delay毫秒后首次执行task，之后每隔period毫秒重复执行一次task
	 * @param taskName
	 * @param milliSecond
	 */
	private static void schedule4(String taskName,int delay,int period) {
		//1.创建一个Timer实例
		Timer timer = new Timer();
		
		//2.创建一个MyTimerTask实例
		MyTimerTask task = new MyTimerTask(taskName);
		
		//3.通过timer定时定频率调用MyTimerTask的业务逻辑 ，
		timer.schedule(task, delay, period);
	}
	
	/**
	 * 5.时间等于或超过time时首次执行task，之后每隔period毫秒重复执行一次task
	 * @param taskName
	 * @param delay
	 * @param period
	 */
	private static void scheduleAtFixedRate(String taskName,int delay, int period) {
		//1.创建一个Timer实例
		Timer timer = new Timer();
		
		//2.创建一个MyTimerTask实例
		MyTimerTask task = new MyTimerTask(taskName);
		
		//3.通过timer定时定频率调用MyTimerTask的业务逻辑 ，
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MILLISECOND, delay);
		
		timer.schedule(task, calendar.getTime()	,period);
	}
	
	/**
	 * 6.等待delay毫秒后首次执行task，之后每隔period毫秒重复执行一次task
	 * @param taskName
	 * @param milliSecond
	 */
	private static void scheduleAtFixedRate2(String taskName,int delay,int period) {
		//1.创建一个Timer实例
		Timer timer = new Timer();
		
		//2.创建一个MyTimerTask实例
		MyTimerTask task = new MyTimerTask(taskName);
		
		//3.通过timer定时定频率调用MyTimerTask的业务逻辑 ，
		timer.scheduleAtFixedRate(task, delay, period);
	}
}
