package com.xsh.timer;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

/**
 * schedule和scheduleAtFixedRate的区别 
 * 2.任务执行所需时间超出任务的执行周期间隔
 * @author shouhua.xiao
 *
 */
public class DifferenceTest2 {

	public static void main(String[] args) {
		final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar calendar = Calendar.getInstance();
		System.out.println("CurrenctName date is " + sdf.format(calendar.getTime()));

		// 设置成6秒前的时间，若当前时间为2018-06-07 00:00:06，
		// 那么设置之后时间变为2018-06-07 00:00:00
		calendar.add(Calendar.SECOND, -6);

		Timer timer = new Timer();

		// 第一次执行时间为6秒前,之后每隔2秒执行一次
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				try {
					Thread.sleep(4000l);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				// 打印当前计划时间
				System.out.println("scheduled exec Time is :" + sdf.format(scheduledExecutionTime()));
			}
		}, calendar.getTime(), 2000);

//		timer.scheduleAtFixedRate(new TimerTask() {
//			@Override
//			public void run() {
//				try {
//					Thread.sleep(4000l);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//				// 打印当前计划时间
//				System.out.println("scheduleAtFixedRate exec Time is :" + sdf.format(scheduledExecutionTime()));
//			}
//		}, calendar.getTime(), 2000);
	}

}
