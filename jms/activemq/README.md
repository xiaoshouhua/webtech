1.ActiveMQ安装
 
	windows:
	    1. 下载安装包 https://archive.apache.org/dist/activemq/5.14.4/apache-activemq-5.14.4-bin.zip
	    2. 直接启动
	    3. 使用服务启动 
	    
	linux:
	    1.下载安装包
	        wget https://archive.apache.org/dist/activemq/5.14.4/apache-activemq-5.14.4-bin.tar.gz
	        
	    2.解压缩 
	        tar -zxvf apache-activemq-5.14.4-bin.tar.gz
	     
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

3.ActiveMQ集群

	3.1 为什么要对消息中间件集群？
		1.实现高可用，排除单点故障引起的服中断 
		2.实现负载均衡，提升效率为更多客户提供服务.	
		
	3.2  集群方式: 
		1.客户端集群 : 让多个消费者消费同一个队列 
            客户端配置:
                ActiveMQ失效转移(failover):允许当其中一台消息服务器宕机时,客户端在传输层上重新连接到其他消息服务器.
                
                语法:
                    failover:(uri1,...,uriN)?transportOptions
                   
                transportOptions参数说明: 
                    .randomize默认为true,表示在URI列表中选择URI连接时是否采用随机策略 
                    .initialReconnectDelay 默认为10，单位毫秒，表示第一次尝试重连之间等待的时间 
                    .maxReconnectDelay 默认30000，单位毫秒，最长重连的时间间隔	  
		       
		2.Broker clusters : 多个Broker之间同步消息
		
            NetworkConnector（网络连接器）
			
                  网络连接器主要用于配置ActiveMQ服务器与服务器之间的网络通讯方式,用于服务器透传消息.
                 
                  网络连接器分为静态连接器和动态连接器.
                  
                  静态连接器：
                  
                         <networkConnectors>
                            <networkConnector uri="static:(tcp://127.0.0.1:61617,tcp://127.0.0.1:61618)"/>
                         </networkConnectors>
                     
                 动态连接器：
                     
                         <networkConnectors>
                            <networkConnector uri="multicast://default"/>
                         </networkConnectors>
                         
                         <transportConnectors>
                            <transportConnector uri="tcp://localhost:0" discoveryUri="multicast://default">
                            </transportConnector>
                         </transportConnectors>
                     
		3.Master Slave : 实现高可用
			集群方案:
		     1.Share nothing storage master/slave（已过时,5.8+后移除）
		     2.Shared storage master/slave 共享存储
		     3.Replicated LevelDB Store 基于复制的LevelDB Store
	
	3.3 集群？		