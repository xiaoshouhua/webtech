package com.xsh.redis;

import java.util.List;
import java.util.Set;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

public class PipelineTest {
	
	private static void setKeys(){
		Jedis jedis = null;
		try {
			//1.
			jedis = new Jedis("192.168.2.217",6379,10000,10000);
			
			//2.获取Pipeline对象
			Pipeline pipeline = jedis.pipelined();
			
			//3.拼接命令
			for (int i = 0; i < 1000; i++) {
				pipeline.set("love:"+i, "me"+i);
			}
			
			//4.执行命令
			pipeline.sync();
			
			List<Object> commands = pipeline.syncAndReturnAll();
			for (Object command : commands) {
				System.out.println(command);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(null != jedis){
				jedis.close();
			}
		}
	}
	
	private static Set<String> getKeys(){
		Jedis jedis = null;
		try {
			//1.
			jedis = new Jedis("192.168.2.217",6379,10000,10000);
			return jedis.keys("love:*");
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(null != jedis){
				jedis.close();
			}
		}
		return null;
	}

	private static void mdel(Set<String> keys) {
		
		if(null == keys || keys.isEmpty()){
			return;
		}
		
		Jedis jedis = null;
		try {
			//1.
			jedis = new Jedis("192.168.2.217",6379,10000,10000);
			
			//2.获取Pipeline对象
			Pipeline pipeline = jedis.pipelined();
			
			//3.拼接命令
			for (String key : keys) {
				pipeline.del(key);	
			}
			//4.执行命令
			//pipeline.sync();
			
			//4.执行命令(可以将命令执行结果也返回)
			List<Object> commands = pipeline.syncAndReturnAll();
			for (Object command : commands) {
				System.out.println(command);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(null != jedis){
				jedis.close();
			}
		}
	}
	
	public static void main(String[] args) {
		setKeys();
		
		Set<String> keys = getKeys();
		
		mdel(keys);
	}

}
