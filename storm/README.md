
#### 1.Storm起源背景

	1.Twitter开源的一个类似于Hadoop的【实时】数据处理框架
	
	2.Storm是由Nathan Marz在BackType工作时实现。
	
	3.2011年Twitter收购BackType,Storm名声大震.
	
#### 2.Storm应用场景

	1.推荐系统 : 实时推荐，根据下单或加入购物车推荐相关商品.
	
	2.网站统计 : 实时销量、流量统计.
	
	3.监控预警系统、金融系统.
	

#### 3.Storm特性

	1.使用场景广泛：实时处理和更新,持续并行化查询,满足大量场景.

	2.可伸缩性高 : 扩展计算任务，只需要加机器并提高并行度.
	
	3.保证数据无丢失：保证每条消息都会被处理.
	
	4.异常健壮 ：集群易管理，可轮流重启节点.
	
	5.容错性好：消息处理过程出现异常,会进行重试.
	
	6.语言无关系: 客户端可以用多种语言编写.

#### 4.架构类型
	
	主从架构:简单,高效,但主节点存在单点问题.类似hdfs,hbase,storm
	
	对称架构: 复杂,效率较低,但无单点问题,更加可靠.类似zookeeper,kafka

#### 5.Storm组件

	Nimbus:
	   1.接收客户端topo代码,拆分成多个task,将task信息存入zk
	   2.将task分配给Supervisor,将映射关系存入zk 
	   3.故障监测
	    
	Supervisor:
	   1.从Nimbus目录读取代码,从zk上读取Nimbus分配的task
	   2.启动工作进程Worker执行任务
	   3.监测运行的工作进程Worker
	    
	Zookeeper:
	   1.Nimbus与Supervisor进行通信(分配任务和心跳)
	   2.Supervisor与Worker进行通信(分配任务和心跳)
	   3.Nimbus高可用(HA机制)

#### 6.Zookeeper概述
	概述:
		    Zookeeper是一个开源的分布式协调服务框架,是Google的Chubby的开源实现.
		
	核心功能:
		
		1.文件系统
		    1.1 每个目录都是一个znode节点
		    1.2 znode节点可直接存储数据
		    1.3 类型:持久化，持久化顺序,临时,临时顺序
		
		2.通知机制
		    2.1客户端监听关心的znode节点
		    2.2 znode节点有变化(数据改变/删除/子目录添加删除)时,通知客户端.
	         
		3.等等