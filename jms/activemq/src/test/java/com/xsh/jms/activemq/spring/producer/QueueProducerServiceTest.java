package com.xsh.jms.activemq.spring.producer;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class QueueProducerServiceTest {
	
	ClassPathXmlApplicationContext applicationContext = null;
	
	@Before
	public void setUp() {
		applicationContext = new ClassPathXmlApplicationContext("classpath:producer.xml");
	}
	
	@Test
	public void testSendMessage() {
		ProducerService producerService  = (ProducerService) applicationContext.getBean("queueProducerService");
		for (int i = 0; i < 100 ; i++) {
			producerService.sendMessage("sp_jms_test_"+i);
		}
	}
	
	@After
	public void setDown() {
		applicationContext.close();
	}

}
