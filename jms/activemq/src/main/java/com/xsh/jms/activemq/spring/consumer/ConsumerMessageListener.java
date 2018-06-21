package com.xsh.jms.activemq.spring.consumer;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * 消息监听者
 * @author shouhua.xiao
 *
 */
public class ConsumerMessageListener implements MessageListener {

	@Override
	public void onMessage(Message message) {
		TextMessage txtMessage = (TextMessage)message;
		try {
			System.out.println("接收消息:"+txtMessage.getText());
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

}
