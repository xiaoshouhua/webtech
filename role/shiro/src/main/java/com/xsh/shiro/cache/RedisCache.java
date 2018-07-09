package com.xsh.shiro.cache;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.stereotype.Component;
import org.springframework.util.SerializationUtils;

import com.xsh.shiro.util.JedisUtil;

@Component(value="redisCache")
public class RedisCache<K, V> implements Cache<K, V> {

	@Resource(name="jedisUtil")
	private JedisUtil jedisUtil;
	
	private static final String CACHE_PREFIX="xsh:cache:";
	
	private byte[] getKey(K k) {
		if(k instanceof String) {
			return (CACHE_PREFIX + k).getBytes();
		}
		return SerializationUtils.serialize(k);
	}
	
	@Override
	public void clear() throws CacheException {
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public V get(K k) throws CacheException {
		System.out.println("do RedisCache...");
		byte[] value = jedisUtil.get(getKey(k));
		if(null != value) {
			return (V)SerializationUtils.deserialize(value);
		}
		return null;
	}

	@Override
	public Set<K> keys() {
		Set<String> keys = jedisUtil.keys(CACHE_PREFIX);
		Set<K> kSet = new HashSet<K>(1);
		if(null != null && keys.size() > 0) {
			for (String key : keys) {
				kSet.add((K)key);
			}
		}
		return kSet;
	}

	@Override
	public V put(K k, V v) throws CacheException {
		byte[] kkey = getKey(k);
		byte[] value = SerializationUtils.serialize(v);
		jedisUtil.set(kkey,value);
		jedisUtil.expire(kkey, 3600);
		return v;
	}

	@SuppressWarnings("unchecked")
	@Override
	public V remove(K k) throws CacheException {
		byte[] kkey = getKey(k);
		byte[] value = jedisUtil.get(kkey);
		if(value != null) {
			jedisUtil.del(kkey);
			return (V)SerializationUtils.deserialize(value);
		}
		return null;
	}

	@Override
	public int size() {
		Set<String> keys = jedisUtil.keys(CACHE_PREFIX);
		if(null != null && keys.size() > 0) {
			return keys.size();
		}
		return 0;
	}

	@Override
	public Collection<V> values() {
		return null;
	}

}
