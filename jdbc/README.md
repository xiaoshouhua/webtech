####  
	概述：
	    JDBC全称为Java Data Base Connectivity(Java数据库连接）
	    是连接数据库的桥梁，由java语言编写的类和接口组成，可以为多种数据库提供统一的访问，体现了Java“编写一次，处处运行”的高大上精神.
	
##### JDBC各种连接方式的对比 
	
	1.JDBC + ODBC桥的方法
		   特点：
		    需要数据库的ODBC驱动，仅适用于微软的系统
		
	2.JDBC + 厂商API的形式
		   特点：
		    厂商API一般使用C编写
	    
	3.JDBC + 厂商Database Connection Server + DataBase的形式 
		   特点：
		    在Java与DATABASE之间架起了一台专门用于与数据库连接的服务器（一般由数据库厂商提供）
        
	4.JDBC + DATABASE的连接方式 
		   特点：
		    这使得Application与数据库分开开发者秩序关心内部逻辑的实现而不需注重数据库连接的具体实现
		
##### 一.JDBC常用的API深入详解以及存储过程的调用
	1.调用无参存储过程 
		 CallableStatement c = conn.prepaeCall("call sp_select_nofilter1()");
		 c.execute();
	2.调用含入参存储过程 
		 CallableStatement cs = conn.prepaeCall("call sp_select_nofilter2(?,?)");
		 cs.setString(1,"xsh");
		 cs.setString(2,"13633777888");
		 cs.execute();
	3.调用含输出参数存储过程
		 CallableStatement cs = conn.prepaeCall("call sp_select_nofilter3(?)");
		 cs.registerOutParameter(1,Types.INTEGER);//参数位置,参数类型
		 cs.execute();
	4.调用含输入，输出参存储过程 (第一个入参,第二个出参)
		 CallableStatement cs = conn.prepaeCall("call sp_select_nofilter3(?,?)");
		 cs.setString(1,"xsh");
		 cs.registerOutParameter(2,Types.INTEGER);//参数位置,参数类型
		 cs.execute();
		 
##### 二.JDBC的事务管理 
	概念：
	    事务（TRANSACTION）是作为单个逻辑工作单元执行的一系列操作，这些操作作为一个整体一起向系统提交，要么都执行，要么都不执行
		
	特点：
	    
		   1.原子性（Atomicity）
		     事务是一个完整的操作
		
		   2.一致性（Consistency）
		     当事务完成时，数据必须处于一致状态
		
		   3.隔离性（Isolation）        
		     对数据进行修改的所有并发事务是彼此隔离的 
		
		   4.永久性（Durability）
		     事务完成后，它对数据库的修改被永久保持.
				
	JDBC对事务管理的支持
	 
		  1.通过提交commit()或者回滚rollback()来管理事务的操作
		 
		  2.事务操作默认是自动提交
		    
		  3.可以通过调用setAutoCommit(false)来禁止自动提交.
		
##### 三.数据库连接池
 
    	背景：
       		1.数据库连接是一种重要的资源 
       		2.频繁的连接数据库会增加数据库的压力
    		
    	分类：
		    DBCP:
		    	    DBCP（DataBase Connection Pool）数据库连接池，是java数据库连接池的一种，
		    	            由Apache开发，通过数据库连接池，可以让程序自动管理数据库连接的释放和断开
			 
		    C3P0：
		        C3P0是一个开源的JDBC连接池，它实现了数据源和JNDI绑定，支持JDBC3和JDBC2的标准扩展.
	
##### 四.JDBC的替代产品		
	1.Commons-dbutils
	
	   概述：
		    Apache组织提供的一个开源JDBC工具类库，对传统操作数据库的类进行二次封装，可以把结果集转化为List
			
	   特点：
		    1.杜绝资源泄露。修正JDBC代码并不困难，但是这通常导致连接泄露并且难以跟踪到 
		    2.大段的持久化数据到数据库代码彻底精简，剩下的代码清晰的表达了编码的意图 
		    3.不需要手工从ResultSet里set值到JavaBean中，每一行数据都将以一个Bean实例的形式出现.
			
	2.Hibernate
	
	   概述：
		    一种Java语言下的对象关系映射解决方案。它是一种自由，开源的框架.
			
	   优点：
		    1.轻量级的ORM框架 
		    2.对JDBC进行了很好的封装，使用了ORM做了映射那么就可以通过面向对象的方式很容易的操作数据库了 
		    3.它还提供了缓存机制，可以提高效率
			
	   缺点：
		    如果对大量的数据进行频繁地操作，性能效率比较低，不如直接使用JDBC.
			
	3.Mybatis
	   概述：
		    Mybatis是支持普通SQL查询，存储过程和高级映射的优秀持久层框架.
			
	   优点：
		    1.易于上手和掌握 
		    2.sql写在xml里，便于统一管理和优化 
		    3.解除sql与程序代码的耦合 
		    4.提供映射标签，支持对象与数据库的ORM字段关系映射 
		    5.提供对象关系映射标签，支持对象关系组件维护 
		    6.提供xml标签，支持编写动态sql
				
		缺点:
			1. SQL语句的编写工作量较大，尤其是字段多、关联表多时，更是如此，对开发人员编写SQL语句有一定要求。
			2. SQL语句依赖于数据库，导致数据库移植性差，不能随意更换数据库。	