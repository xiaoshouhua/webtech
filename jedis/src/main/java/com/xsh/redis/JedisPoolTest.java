package com.xsh.redis;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * 客户端连接Redis使用的是TCP协议，直连的方式每次需要建立TCP连接，
 * 而连接池的方式是可以预先初始化好Jedis连接，所以每次只需要从Jedis连接池借用即可，
 * 而借用和归还操作是在本地进行的，只有少量的并发同步开销，远远小于新建TCP连接的开销。
 * 另外直连的方式无法限制Jedis对象的个数，在极端情况下可能会造成连接泄露，
 * 而连接池的形式可以有效的保护和控制资源的使用。
 * @author shouhua.xiao
 *
 */
public class JedisPoolTest {
	
	private static JedisPool jedisPool = null;
	
	static{
		init();
	}
	
	public static void init(){
		GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
		
		//设置最大连接数为默认值的5倍
		poolConfig.setMaxTotal(GenericObjectPoolConfig.DEFAULT_MAX_TOTAL * 5);
		
		//设置最大空闲连接数为默认值的3倍
		poolConfig.setMaxIdle(GenericObjectPoolConfig.DEFAULT_MAX_IDLE * 3);
		
		//设置最小空闲连接数为默认值的2倍
		poolConfig.setMinIdle(GenericObjectPoolConfig.DEFAULT_MIN_IDLE * 2);
		
		//当连接池用尽后，调用者是否要等待，这个参数是和maxWaitMillis对应的，只有当次参数为true时，maxWaitMillis才会生效.默认为true
		poolConfig.setBlockWhenExhausted(GenericObjectPoolConfig.DEFAULT_BLOCK_WHEN_EXHAUSTED);
		
		//设置连接池没有连接后客户端的最大等待时间(单位为毫秒)
		poolConfig.setMaxWaitMillis(3000);
		
		//设置开启JMX功能
		poolConfig.setJmxEnabled(true);
		
		//连接的最小空余时间,达到此值后空闲连接将被移除,默认30分钟 1000L * 60L * 30L
		poolConfig.setMinEvictableIdleTimeMillis(GenericObjectPoolConfig.DEFAULT_MIN_EVICTABLE_IDLE_TIME_MILLIS);
		
		//空闲连接的检测周期,单位为毫秒,默认-1:表示不做检测
		poolConfig.setTimeBetweenEvictionRunsMillis(GenericObjectPoolConfig.DEFAULT_TIME_BETWEEN_EVICTION_RUNS_MILLIS);
		
		//做空闲连接检测时,每次的采样数,默认3
		poolConfig.setNumTestsPerEvictionRun(GenericObjectPoolConfig.DEFAULT_NUM_TESTS_PER_EVICTION_RUN);
		
		//向连接池【创建】连接时是否做连接有效性检测(ping),无效连接会被移除,每次【创建】多执行一次ping命令,默认false
		poolConfig.setTestOnCreate(GenericObjectPoolConfig.DEFAULT_TEST_ON_CREATE);
		//向连接池【借用】连接时是否做连接有效性检测(ping),无效连接会被移除,每次【借用】多执行一次ping命令,默认false
		poolConfig.setTestOnBorrow(GenericObjectPoolConfig.DEFAULT_TEST_ON_BORROW);
		//向连接池【归还】连接时是否做连接有效性检测(ping),无效连接会被移除,每次【归还】多执行一次ping命令,默认false
		poolConfig.setTestOnReturn(GenericObjectPoolConfig.DEFAULT_TEST_ON_RETURN);
		
		jedisPool = new JedisPool(poolConfig, "192.168.1.111",6379, 10000);
	}

	public static void main(String[] args) {
		Jedis jedis = null;
		try {
			//从连接池获取jedis对象
			jedis = jedisPool.getResource();
			 
			System.out.println(jedis.get("hello"));;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			//如果使用JedisPool,close操作不是关闭连接，代表归还连接
			if(null != jedis){
				//dataSource！=null代表使用的是连接池，所以jedis.close（）代表归还连接给连接池，而且Jedis会判断当前连接是否已经断开。
				//dataSource=null代表直连，jedis.close（）代表关闭连接
				jedis.close();
			}
		}
	}

}
