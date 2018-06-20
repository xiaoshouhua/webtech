package com.xsh.jms.activemq.queue;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

import com.xsh.jms.activemq.Constant;
/**
 * ActiveMQ-队列模式 消费者服务
 * @author shouhua.xiao
 * 队列模式:
 * 	  1.客户端包括生产者和消费者 
 * 	  2.队列中的消息只能被一个消费者消费 
 * 	  3.消费者可以随时消费队列中的消息
 */
public class QueueConsumer {

	public static void main(String[] args) throws JMSException {
		// 1.创建连接工厂
		ConnectionFactory factory = new ActiveMQConnectionFactory(Constant.URL);

		// 2.获得连接
		Connection connection = factory.createConnection();

		// 3.启动连接
		connection.start();

		// 4.创建会话
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

		// 5.创建一个目标
		Destination destination = session.createQueue(Constant.QUEUENAME);

		// 6.创建一个消费者
		MessageConsumer messageConsumer = session.createConsumer(destination);
		
		// 7.创建一个监听器
		messageConsumer.setMessageListener(new MessageListener() {
			
			@Override
			public void onMessage(Message message) {
				
				TextMessage txtMsg = (TextMessage)message;
				try {
					System.out.println("接收消息:" + txtMsg.getText());
				} catch (JMSException e) {
					e.printStackTrace();
				}
			}
		});	
		
		// 9.关闭连接
		//connection.close();
	}

}
