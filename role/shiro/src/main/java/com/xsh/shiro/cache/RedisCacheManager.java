package com.xsh.shiro.cache;

import javax.annotation.Resource;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;

public class RedisCacheManager implements CacheManager {

	@Resource(name = "redisCache")
	private RedisCache redisCache;
	
	@Override
	public <K, V> Cache<K, V> getCache(String s) throws CacheException {
		System.out.println("do ReadCache");
		return redisCache;
	}

}
