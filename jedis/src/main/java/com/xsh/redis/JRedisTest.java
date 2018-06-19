package com.xsh.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;
import com.google.common.collect.Maps;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisMonitor;

/**
 * @author shouhua.xiao
 */
public class JRedisTest {

	public static void config(){
		Jedis jedis = null;
		try {
			//host 主机 port 端口 connectionTimeout：客户端连接超时 soTimeout：客户端读写超时
			jedis = new Jedis("192.168.1.111",6379,10000,10000);
			
			//设置密码,如果有的话
			//jedis.auth("fcpwd1305");
			
			//执行ping命令
			String ping = jedis.ping();
			System.out.println(ping);
			
			//返回当前服务器时间,size为2(第一个是当前时间(以 UNIX 时间戳格式表示),第二个是当前这一秒钟已经逝去的微秒数)
			List<String> times = jedis.time();
			System.out.println(times);
			
			List<String> configList = jedis.configGet("*");
			System.out.println(configList);
			
			//获取键列表
			Set<String> keys = jedis.keys("love:*");
			for (String key : keys) {
				System.out.println(key);
			}
			
			//获得键总数
			Long size = jedis.dbSize();
			System.out.println(size);
			
			//随机返回一个key
			System.out.println(jedis.randomKey());
			
			//获得客户端列表
			String clientList = jedis.clientList();
			System.out.println(clientList);
			
			String info = jedis.info();
			System.out.println(info);
			
			System.out.println("-------------Server start------------------");
			String info2 = jedis.info("Server");
			System.out.println(info2);
			System.out.println("--------------Server end-----------------");
			
			System.out.println("\n-------------Clients start------------------");
			String info3 = jedis.info("Clients");
			System.out.println(info3);
			System.out.println("--------------Clients end-----------------");
			
			System.out.println("\n--------------Memory start-----------------");
			String info4 = jedis.info("Memory");
			System.out.println(info4);
			System.out.println("--------------Memory end-----------------");
			
			//实时打印出 Redis 服务器接收到的命令，调试用
			JedisMonitor jedisMonitor = new JedisMonitor() {
				@Override
				public void onCommand(String command) {
					System.out.println(command);
				}
			};
			jedis.monitor(jedisMonitor);

			//清空当前db的所有键和值
			 //jedis.flushDB();
			
			//清除所有db的所有键和值
			//jedis.flushAll();
			
			//关闭服务器与客户端之间的连接
			String quit = jedis.quit();
			System.out.println(quit);
			
		} catch (Exception e) {
			System.err.println("获取Jedis链接失败:"+e);
		}finally{
			if(null != jedis){
				jedis.close();
			}
		}
	}
	
	public static void stringOperation(){

		System.out.println("------------------------- string -------------------------------");
		Jedis jedis = null;
		try {
			//host 主机 port 端口 connectionTimeout：客户端连接超时 soTimeout：客户端读写超时
			jedis = new Jedis("192.168.1.111",6379,10000,10000);
			
			//设置密码,如果有的话
			//jedis.auth("fcpwd1305");
			
			//获得键总数
			Long size = jedis.dbSize();
			System.out.println(size);
			
			String key = "users:xsh";
			
			//输出键
			System.out.println(jedis.echo(key));
			
			//设置键和值
			String setResult = jedis.set(key,"love");
			System.out.println(setResult);
			
			boolean isExists = jedis.exists(key);
			System.out.println(isExists);
			
			//获得键值类型
			String type = jedis.type(key);
			System.out.println(type);
			
			//获取键对应的值
			String getVal = jedis.get(key);
			System.out.println(getVal);
			
			//自增
			Long incr = jedis.incr("users:count");
			System.out.println(incr);
			
			//自减
			Long decr = jedis.decr("users:count");
			System.out.println(decr);
			
			//给对应的键所对应的值拼接内容
			Long appendLen = jedis.append(key, " qcj");
			System.out.println(appendLen);
			
			//设置存活时间
			Long expire = jedis.expire(key, 20);
			System.out.println(expire);
			
			Thread.sleep(3000);
			
			//获得剩余存活时间
			Long ttl = jedis.ttl(key);
			System.out.println("ttl:"+ttl);
			
			//删除键
			Long delLen = jedis.del(key);
			System.out.println(delLen);
			
			//关闭服务器与客户端之间的连接
			String quit = jedis.quit();
			System.out.println(quit);
			
		} catch (Exception e) {
			System.err.println("获取Jedis链接失败:"+e);
		}finally{
			if(null != jedis){
				jedis.close();
			}
		}
	}
	
	private static void hashOperation() {
		System.out.println("------------------------- hash -------------------------------");
		Jedis jedis = null;
		try {
			jedis = new Jedis("192.168.1.111",6379,10000,10000);
			
			String key = "myhash";
			
			System.out.println(jedis.hset(key,"name","xiaoshouhua"));
			System.out.println(jedis.hset(key, "nickName", "刚刚好"));;
			
			Map<String,String> hMap = jedis.hgetAll(key);
			for (String field : hMap.keySet()) {
				System.out.println(field+":"+hMap.get(field));
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(null != jedis){
				jedis.close();
			}
		}
	}
	
	private static void listOperation() {
		System.out.println("------------------------- list -------------------------------");
		Jedis jedis = null;
		try {
			jedis = new Jedis("192.168.1.111",6379,10000,10000);
			
			String key = "books";
			
			jedis.del(key);
			
			String lpop = jedis.lpop(key);
			System.out.println(lpop);
			
			jedis.rpush(key,"笑傲江湖","天龙八部","楚留香","天蚕变","孔雀翎");
			
			//获取list下的记录数
			Long len = jedis.llen(key);
			System.out.println(len);
			
			//遍历list
			List<String> books = jedis.lrange(key, 0, -1);
			for (String book : books) {
				System.out.println(book);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(null != jedis){
				jedis.close();
			}
		}
	}
	
	private static void setOperation() {
		System.out.println("------------------------- set -------------------------------");
		Jedis jedis = null;
		try {
			jedis = new Jedis("192.168.1.111",6379,10000,10000);
			
			String key = "bookSets";
			
			//添加元素
			jedis.sadd(key, "<<射雕英雄传>>","<<神雕侠侣>>","<<倚天屠龙记>>","<<碧血剑>>","<<鹿鼎记>>");
			
			//获取set下的记录数
			Long len = jedis.scard(key);
			System.out.println(len);
			
			//判断是否是集合中的元素
			System.out.println(jedis.sismember(key, "<<射雕英雄传>>"));
			
			//移除元素
			System.out.println(jedis.srem(key, "<<射雕英雄传>>"));
			
			//遍历set
			Set<String> books = jedis.smembers(key);
			for (String book : books) {
				System.out.println(book);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(null != jedis){
				jedis.close();
			}
		}
	}
	
	private static void sortedSetOperation() {
		System.out.println("------------------------- Sorted Set -------------------------------");
		Jedis jedis = null;
		try {
			jedis = new Jedis("192.168.1.111",6379,10000,10000);
			
			String key = "bookZSets";
			
			//添加元素
			Map<String,Double> zMap = Maps.newHashMap();
			zMap.put("<<射雕英雄传>>", 261.0d);
			zMap.put("<<神雕侠侣>>", 249.0d);
			zMap.put("<<倚天屠龙记>>", 245.0d);
			zMap.put("<<碧血剑>>", 242.0d);
			zMap.put("<<鹿鼎记>>", 259.0d);
			jedis.zadd(key,zMap);
			
			//获取sorted set下的记录数
			Long len = jedis.zcard(key);
			System.out.println(len);
			
			//返回有序集 key中，成员<<射雕英雄传>>的 score值
			System.out.println(jedis.zscore(key, "<<射雕英雄传>>"));
			
			//移除元素
			System.out.println(jedis.zrem(key, "<<射雕英雄传>>"));
			
			//遍历set
			Set<String> books = jedis.zrange(key, 0, -1);
			for (String book : books) {
				System.out.println(book);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(null != jedis){
				jedis.close();
			}
		}
	}
	
	public static void main(String[] args) {
		
		stringOperation();
		
		hashOperation();
		
		listOperation();
		
		setOperation();
		
		sortedSetOperation();
		
		config();
	}

}
