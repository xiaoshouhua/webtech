package com.xsh.timer;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimerTask;

public class MyTimerTask extends TimerTask {

	private String name;
	
	private Integer count = 0;
	
	public MyTimerTask(String name) {
		this.name = name;
	}
	
	@Override
	public void run() {
		if(count < 3) {
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar calendar = Calendar.getInstance();
			System.out.println("CurrenctName is "+this.name+",date is "+sdf.format(calendar.getTime()));
			
		}else {
			System.out.println("CurrenctName is "+this.name +" close ...");
			//取消当前TimerTask里的任务
			cancel();
		}
		
		count++;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
}
