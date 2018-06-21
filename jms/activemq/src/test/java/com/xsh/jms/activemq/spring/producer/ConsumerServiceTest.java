package com.xsh.jms.activemq.spring.producer;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ConsumerServiceTest {
	
	public static void main(String[] args) {
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:consumer.xml");
	}
	

}
