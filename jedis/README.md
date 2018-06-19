### Redis入门

#### NoSQL（Not Only SQL ,非关系型数据库）
	 为什么用NoSQL?
	    High performance - 高并发读写
	    Huge Storage - 海量数据的高效率存储和访问
	    High Scalability && High Availability - 高可扩展性和高可用性
	    
	 NoSQL数据库的四大分类；
	    * 键值（Key-Value）存储 
	    * 列存储 
	    * 文档数据库 
	    * 图形数据库	
	    
	 NoSQL的特点：
	    1.易扩展
	    2.大数据量，高性能 
	    3.灵活的数据模型 
	    4.高可用	 
	
#### Redis概述？
	概述：
        Redis 是完全开源免费的，遵守BSD协议，是一个高性能的key-value数据库
		
	特点：
        1.Redis支持数据的持久化，可以将内存中的数据保存在磁盘中，重启的时候可以再次加载进行使用
        2.Redis不仅仅支持简单的key-value类型的数据，同时还提供list，set，zset，hash等数据结构的存储
        3.Redis支持数据的备份，即master-slave模式的数据备份
	
	应用场景：
        1.缓存 
        2.任务队列 
        3.应用排行榜 
        4.网址访问统计
        5.数据过期处理 
        6.分布式集群架构中的session分离
        7.。。。
		
	支持的键值对数据类型：
        字符串类型(string)
            get/set/del/append/rename/exists/incr/decr/expire
        列表类型(list)
            lpush,rpush,lpushx,rpushx/lrange/lpop,bpop,rpop/llen/lrem/linsert
        散列类型(hash)
            hset,hmset,hgetall/hget,hmget/hdel/hlen,hkeys hvalus
        集合类型(set-不允许出现重复的元素,set可包含的最大元素数量是4294967295)
            sadd/srem/smembers/sismember/sinter,sinterstore/sdiff,sdiffstore/sunion,sunionstore/scard,srandmember
            使用场景：1.跟踪一些唯一性数据 2.用于维护数据对象之间的关联关系
        有序集合类型(sortset) 
            zadd/zscore/zcount/zcard/zrem/zrange/zrevrange/zremrangebyrank/zremrangebyscore
			
#### Redis的安装和使用
	1.下载安装包
        cd /opt/redis	
        wget http://download.redis.io/releases/redis-3.0.7.tar.gz
	2.解压缩
        tar -zxvf redis-3.0.7.tar.gz
	3.编译
        cd redis-3.0.7
        执行make
	4.安装到指定目录
        make PREFIX=/usr/local/redis/ install
	5.启动服务,进入/usr/local/redis/src执行命令 ,可以修改redis.conf的daemonize为yes,以后台进程的形式运行.
        ./redis-server 
	6.进入/usr/local/redis/src执行命令,
        ./redis-cli
	7.关闭redis服务的方法
        可以采用ps|redis的方式找到pid，然后通过kill -9 pid的方式关闭redis服务(不建议采用,会导致RDB或AOF备份数据丢失)
					
	8.常用的可执行文件
        ./redis-benchmark //用于进行redis性能测试的工具
        ./redis-check-dump //用于修复出问题的dump.rdb文件
        ./redis-cli //redis的客户端
        ./redis-server //redis的服务端
        ./redis-check-aof //用于修复出问题的AOF文件
        ./redis-sentinel //用于集群管理	
		
	9.安装出错常用问题:
	
        1.gcc: Command not found
            yum命令安装gcc 命令如下yum install gcc
            
        2.tcl: Command not found
            yum命令安装tcl 命令如下yum install tcl
            
        3.出现make[1]: Leaving directory `/root/redis-3.0.3/src',
            执行make MALLOC=libc
             
        4.是否安装成功可以利用src目录下的runtest工具查看异常项.
            cd redis-3.0.7/src/
            ./runtest
            
        5.redis.conf默认开放的127.0.0.1地址需要修改为0.0.0.0地址才能被其他机器所使用
            编辑配置文件vim /usr/local/redis/redis.conf
            bind 127.0.0.1修改为bind 0.0.0.0	
            输入netstat -tnlp查询端口是否正常.
            
        6.Connection refused: connect
            将服务器防火墙关闭
                /etc/init.d/iptables stop
            将端口开放：
                -A INPUT -p tcp -m state --state NEW -m tcp --dport 6379 -j ACCEPT	
					
	10.参考文档：https://blog.csdn.net/itchuangyi/article/details/79609811

#### Jedis的使用和入门 
	1.直接获取连接
	2.连接池模式

#### key定义的注意点：
	1.不要太长(浪费空间)
	2.不要过短 (影响可读性)
	3.统一的命名规范
	
#### Redis的特性 
	1.多数据库(默认提供16个,下标从0到15,3.x版本不建议使用了)
        移动该db下的key到db1中
        move key 1
	2.支持事务
        multi -- 开启事务 
        exec  -- 提交事务
        discard -- 回滚事务
#### Redis的持久化
	1.持久化策略
        1.RDB(默认打开)
            优势：
			
            劣势：
				
        2.AOF(默认关闭,需要修改redis.conf的appendonly为yes和同步策略appendfsync)
            优势：
			
            劣势：
			
	2.持久化使用的方式：
        1.RDB持久化 
        2.AOF持久化 
        3.无持久化 
        4.同时使用RDB和AOF