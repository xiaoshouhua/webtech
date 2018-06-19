package com.xsh.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisMonitor;

/**
 * Redis监控
 * 监控系统所监控的关键指标有很多，如命令耗时、慢查询、持久化阻塞、连接拒绝、CPU/内存/网络/磁盘使用过载等
 * @author shouhua.xiao
 */
public class JedisMonitorTest {

	public static void main(String[] args) {
		Jedis jedis = null;
		try {
			// host 主机 port 端口 connectionTimeout：客户端连接超时 soTimeout：客户端读写超时
			jedis = new Jedis("192.168.1.111", 6379, 10000, 10000);
			
			//设置客户端名字,方便监控,容易识别客户端来源
			jedis.clientSetname("hotel-client");
			System.out.println(jedis.clientGetname());
			
			//1.查看Redis 服务器的各种信息和统计数值
			String info = jedis.info();
			System.out.println(info);
			
			//2.查看客户端列表
			String clientList = jedis.clientList();
			System.out.println(clientList);
			
			//3.实时打印出 Redis 服务器接收到的命令，调试用
			JedisMonitor jedisMonitor = new JedisMonitor() {
				@Override
				public void onCommand(String command) {
					System.out.println(command);
				}
			};
			jedis.monitor(jedisMonitor);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 如果使用JedisPool,close操作不是关闭连接，代表归还连接
			if (null != jedis) {
				// dataSource！=null代表使用的是连接池，所以jedis.close（）代表归还连接给连接池，而且Jedis会判断当前连接是否已经断开。
				// dataSource=null代表直连，jedis.close（）代表关闭连接
				jedis.close();
			}
		}
	}

}
