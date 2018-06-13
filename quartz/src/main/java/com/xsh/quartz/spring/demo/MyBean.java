package com.xsh.quartz.spring.demo;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component("myBean")
public class MyBean {

	public void printMessage(){
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println("MyBean Executes !!! date is "+sdf.format(new Date()));
		
	}
	
}
