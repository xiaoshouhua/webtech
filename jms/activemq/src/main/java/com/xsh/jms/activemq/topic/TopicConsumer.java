package com.xsh.jms.activemq.topic;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnectionFactory;

import com.xsh.jms.activemq.Constant;
/**
 * ActiveMQ-【主题模式】 消费者服务
 * @author shouhua.xiao
 * 主题模式:
 *    1.客户端包含发布者和订阅者	
 *    2.主题中的消息被所有订阅者消费 
 *    3.消费者不能消费订阅之前就发送到主题中的消息
 */
public class TopicConsumer {

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
		Topic destination = session.createTopic(Constant.TOPICNAME);

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
