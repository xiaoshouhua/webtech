package com.xsh.jms.activemq.spring.producer;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

public class TopicProducerServiceImpl implements ProducerService {

	@Autowired
	private JmsTemplate jmsTemplate;
	
	@Resource(name="topicDestination")
	private Destination topic;
	
	@Override
	public void sendMessage(final String message) {

		jmsTemplate.send(topic, new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				//创建一个消息
				TextMessage textMsg = session.createTextMessage(message);
				System.out.println("主题模式下,发送消息:"+textMsg.getText());
				return textMsg;
			}
		});
		
	}

}
