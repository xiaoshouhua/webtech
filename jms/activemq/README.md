1.ActiveMQ安装
 
	windows:
	    1. 下载安装包 https://archive.apache.org/dist/activemq/5.14.4/apache-activemq-5.14.4-bin.zip
	    2. 直接启动
	    3. 使用服务启动 
	    
	linux:
	    1.下载安装包
	    	wget https://archive.apache.org/dist/activemq/5.14.4/apache-activemq-5.14.4-bin.tar.gz
	    	
	    2.解压缩 tar -zxvf apache-activemq-5.14.4-bin.tar.gz
	     
	    3.进入bin目录,
	    	执行./activemq start
	
2.使用Spring集成JMS连接ActiveMQ 
	
	ConnectionFactory 用于管理连接的连接工厂 
	    1.一个Spring为我们提供的连接池
	    2.JmsTemplate每次发消息都会重新创建连接，会话和productor
	    3.spring中提供了SingleConnectionFactory和CachingConnectionFactory
	    
	JmsTemplate 用于发送和接收消息的模板类
	    1.由spring提供,只需向spring容器中注册这个类就可以使用JsmTemplate方便的操作jms
	    2.JmsTemplate是线程安全的，可以在整个应用范围使用.
	    
	MessageListerner 消息监听器
	    实现一个onMessage方法,该方法只接收一个Message参数.
	