package com.xsh.jms.activemq.queue;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import org.apache.activemq.ActiveMQConnectionFactory;

import com.xsh.jms.activemq.Constant;

/**
 * ActiveMQ-队列模式 生产者服务
 * @author shouhua.xiao
 * 队列模式:
 * 	  1.客户端包括生产者和消费者 
 * 	  2.队列中的消息只能被一个消费者消费 
 * 	  3.消费者可以随时消费队列中的消息
 */
public class QueueProducer {
	
	public static void main(String[] args) throws JMSException {
		
		//1.创建连接工厂
		ConnectionFactory factory = new ActiveMQConnectionFactory(Constant.URL);
		
		//2.获得连接
		Connection connection = factory.createConnection();
		
		//3.启动连接
		connection.start();
		
		//4.创建会话
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		
		//5.创建一个目标
		Destination destination = session.createQueue(Constant.QUEUENAME);
		
		//6.创建一个生产者
		MessageProducer messageProducer = session.createProducer(destination);
		
		for (int i = 0; i < 100 ; i++) {
			
			//7.创建一个消息
			TextMessage textMsg = session.createTextMessage("test_"+i);
			//8.发布消息
			messageProducer.send(textMsg);
			
			//System.out.println("发送消息:"+textMsg);
			System.out.println("发送消息:"+textMsg.getText());
		}
		
		//9.关闭连接
		connection.close();
	}

}
