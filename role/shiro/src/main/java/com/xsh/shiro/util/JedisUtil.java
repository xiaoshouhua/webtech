package com.xsh.shiro.util;

import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * 纳入spring管理，单例模式
 * @author shouhua.xiao
 */
@Component("jedisUtil")
public class JedisUtil {

	Log log = LogFactory.getLog(JedisUtil.class);

	@Resource(name="jedisPool")
	private JedisPool jedisPool;

	private Jedis getResource() {
		Jedis jedis = jedisPool.getResource();
		return jedis;
	}

	/**
	 * 通过key获取储存在redis中的value 并释放连接
	 * 
	 * @param key
	 * @return 成功返回value 失败返回null
	 */
	public String get(String key) {
		Jedis jedis = null;
		String value = null;
		try {
			jedis = getResource();
			value = jedis.get(key);
		} catch (Exception e) {
			log.error("get error", e);
		} finally {
			close(jedis);
		}
		return value;
	}
	
	public byte[] get(byte[] key) {
		Jedis jedis = null;
		byte[] value = null;
		try {
			jedis = getResource();
			value = jedis.get(key);
		} catch (Exception e) {
			log.error("get error", e);
		} finally {
			close(jedis);
		}
		return value;
	}

	public void expire(String key, int seconds) {
		Jedis jedis = null;
		try {
			jedis = getResource();
			jedis.expire(key, seconds);
		} catch (Exception e) {
			log.error("expire error", e);
		} finally {
			close(jedis);
		}
	}
	
	public void expire(byte[] key, int seconds) {
		Jedis jedis = null;
		try {
			jedis = getResource();
			jedis.expire(key, seconds);
		} catch (Exception e) {
			log.error("expire error", e);
		} finally {
			close(jedis);
		}
	}

	/**
	 * 向redis存入key和value,并释放连接资源 如果key已经存在 则覆盖
	 * 
	 * @param key
	 * @param value
	 * @return 成功 返回OK 失败返回 0
	 */
	public String set(String key, String value) {
		Jedis jedis = null;
		try {
			jedis = getResource();
			return jedis.set(key, value);
		} catch (Exception e) {
			log.error("set error", e);
		} finally {
			close(jedis);
		}
		return "0";
	}
	
	public String set(byte[] key, byte[] value) {
		Jedis jedis = null;
		try {
			jedis = getResource();
			return jedis.set(key, value);
		} catch (Exception e) {
			log.error("set error", e);
		} finally {
			close(jedis);
		}
		return "0";
	}

	/**
	 * 删除指定的key,也可以传入一个包含key的数组
	 * 
	 * @param keys
	 *            一个key 也可以使 string 数组
	 * @return 返回删除成功的个数
	 */
	public Long del(String... keys) {
		Jedis jedis = null;
		try {
			jedis = getResource();
			return jedis.del(keys);
		} catch (Exception e) {
			log.error("del error", e);
		} finally {
			close(jedis);
		}
		return 0L;
	}
	
	public Long del(byte[]... keys) {
		Jedis jedis = null;
		try {
			jedis = getResource();
			return jedis.del(keys);
		} catch (Exception e) {
			log.error("del error", e);
		} finally {
			close(jedis);
		}
		return 0L;
	}

	/**
	 * 判断key是否存在
	 * 
	 * @param key
	 * @return true OR false
	 */
	public Boolean exists(String key) {
		Jedis jedis = null;
		Boolean exists = false;
		try {
			jedis = jedisPool.getResource();
			exists = jedis.exists(key);
		} catch (Exception e) {
			log.error("exists error", e);
		} finally {
			close(jedis);
		}
		return exists;
	}

	/**
	 * 设置key value,如果key已经存在则返回0,nx==> not exist
	 * 
	 * @param key
	 * @param value
	 * @return 成功返回1 如果存在 和 发生异常 返回 0
	 */
	public Long setnx(String key, String value) {
		Jedis jedis = null;
		Long result = 0L;
		try {
			jedis = jedisPool.getResource();
			result = jedis.setnx(key, value);
		} catch (Exception e) {
			log.error("setnx error", e);
		} finally {
			close(jedis);
		}
		return result;
	}

	/**
	 * 设置key value并制定这个键值的有效期
	 * 
	 * @param key
	 * @param value
	 * @param seconds
	 *            单位:秒
	 * @return 成功返回OK 失败和异常返回null
	 */
	public String setex(String key, String value, int seconds) {
		Jedis jedis = null;
		String res = null;
		try {
			jedis = jedisPool.getResource();
			res = jedis.setex(key, seconds, value);
		} catch (Exception e) {
			log.error("setex error", e);
		} finally {
			close(jedis);
		}
		return res;
	}
	
	/**
     * 返回满足pattern表达式的所有key keys(*) 返回所有的key
     * @param pattern
     * @return
     */
    public Set<String> keys(String pattern) {
		Jedis jedis = null;
		Set<String> res = null;
		try {
		    jedis = jedisPool.getResource();
		    res = jedis.keys(pattern);
		}catch (Exception e) {
		    log.error(" keys error", e);
		} finally {
		    close(jedis);
		} 
		return res;
    }

	/**
	 * 关闭jedis
	 * @param jedis
	 */
	private void close(Jedis jedis) {
		if (null != jedis) {
			try {
				jedis.close();
			} catch (Exception e) {
				log.error(" jedis close error ", e);
			}
		}
	}

}
